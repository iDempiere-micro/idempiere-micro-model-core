package org.compiere.dbPort

import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.Env
import org.idempiere.common.util.Util

import java.math.BigDecimal
import java.util.ArrayList
import java.util.StringTokenizer
import java.util.TreeMap
import java.util.Vector
import java.util.regex.Pattern

import software.hsharp.core.util.NATIVE_MARKER

/**
 * Convert Oracle SQL to PostgreSQL SQL
 *
 * @author Victor Perez, Low Heng Sin, Carlos Ruiz
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *  * BF [ 1824256 ] Convert sql casts
 */
class ConvertPostgreSQL : ConvertSQL92() {

    private val m_map: TreeMap<String, String> = ConvertMap_PostgreSQL.convertMap

    override val convertMap: Map<String, String>?
        get() = m_map

    /**
     * Convert single Statements. - remove comments - process FUNCTION/TRIGGER/PROCEDURE - process
     * Statement
     *
     * @param sqlStatement
     * @return converted statement
     */
    override fun convertStatement(sqlStatement: String): Array<String> {
        val result = ArrayList<String>()
        /* Vector to save previous values of quoted strings * */
        val retVars = Vector<String>()

        var statement = replaceQuotedStrings(sqlStatement, retVars)
        statement = convertWithConvertMap(statement)
        statement = convertSimilarTo(statement)
        statement = statement.replace(NATIVE_MARKER, "")

        val cmpString = statement.toUpperCase()
        val isCreate = cmpString.startsWith("CREATE ")

        // Process
        if (!isCreate || !cmpString.contains(" FUNCTION ")) {
            if (!isCreate || !cmpString.contains(" TRIGGER ")) {
                if (!isCreate || !cmpString.contains(" PROCEDURE ")) {
                    if (!isCreate || !cmpString.contains(" VIEW ")) {
                        if (cmpString.contains("ALTER TABLE")) {
                            statement = recoverQuotedStrings(statement, retVars)
                            retVars.clear()
                            statement = convertDDL(convertComplexStatement(statement))
                            /*
                      } else if (cmpString.indexOf("ROWNUM") != -1) {
                          result.add(convertRowNum(convertComplexStatement(convertAlias(statement))));*/
                        } else if (cmpString.contains("DELETE ") && !cmpString.contains("DELETE FROM")) {
                            statement = convertDelete(statement)
                            statement = convertComplexStatement(convertAlias(statement))
                        } else if (cmpString.contains("DELETE FROM")) {
                            statement = convertComplexStatement(convertAlias(statement))
                        } else if (cmpString.contains("UPDATE ")) {
                            statement = convertComplexStatement(convertUpdate(convertAlias(statement)))
                        } else {
                            statement = convertComplexStatement(convertAlias(statement))
                        }
                    }
                }
            }
        }
        if (retVars.size > 0) statement = recoverQuotedStrings(statement, retVars)
        result.add(statement)

        if ("true" == System.getProperty("org.idempiere.db.postgresql.debug")) {
            logger.warn("Oracle -> $sqlStatement")
            logger.warn("PgSQL  -> $statement")
        }
        return result.toTypedArray()
    } // convertStatement

    private fun convertSimilarTo(statement: String): String {
        var retValue = statement
        val useSimilarTo = "Y" == Env.getContext(Env.getCtx(), "P|IsUseSimilarTo")
        if (useSimilarTo) {
            val regex = "\\bLIKE\\b"
            val replacement = "SIMILAR TO"
            try {
                val p = Pattern.compile(regex, REGEX_FLAGS)
                val m = p.matcher(retValue)
                retValue = m.replaceAll(replacement)
            } catch (e: Exception) {
                val error = "Error expression: $regex - $e"
                logger.info(error)
                m_conversionError = error
            }
        }
        return retValue
    }

    override fun escapeQuotedString(`in`: String): String {
        val out = StringBuilder()
        var escape = false
        val size = `in`.length
        for (i in 0 until size) {
            val c = `in`[i]
            out.append(c)
            if (c == '\\') {
                escape = true
                out.append(c)
            }
        }
        return if (escape) {
            "E$out"
        } else {
            out.toString()
        }
    }

    /**
     * ************************************************************************* Converts Decode and
     * Outer Join.
     *
     * <pre>
     * DECODE (a, 1, 'one', 2, 'two', 'none')
     * =&gt; CASE WHEN a = 1 THEN 'one' WHEN a = 2 THEN 'two' ELSE 'none' END
     *
    </pre> *
     *
     * @param sqlStatement
     * @return converted statement
     */
    protected fun convertComplexStatement(sqlStatement: String): String {
        var retValue = sqlStatement

        // Convert all decode parts
        var found = retValue.toUpperCase().indexOf("DECODE")
        var fromIndex = 0
        while (found != -1) {
            retValue = convertDecode(retValue, fromIndex)
            fromIndex = found + 6
            found = retValue.toUpperCase().indexOf("DECODE", fromIndex)
        }

        // Outer Join Handling -----------------------------------------------
        val index = retValue.toUpperCase().indexOf("SELECT ")
        if (index != -1 && retValue.indexOf("(+)", index) != -1) retValue = convertOuterJoin(retValue)

        // Convert datatypes from CAST(.. as datatypes):
        retValue = convertCast(retValue)

        return retValue
    } // convertComplexStatement

    /**
     * Convert datatypes from CAST sentences
     *
     * <pre>
     * cast(NULL as NVARCHAR2(255))
     * =&gt;cast(NULL as VARCHAR)
    </pre> *
     */
    private fun convertCast(sqlStatement: String): String {
        val PATTERN_String = "\'([^']|(''))*\'"
        val PATTERN_DataType = "([\\w]+)(\\(\\d+\\))?"
        val pattern = ("\\bCAST\\b[\\s]*\\([\\s]*" + // CAST<sp>(<sp>

                "((" +
                PATTERN_String +
                ")|([^\\s]+))" + // 	arg1				1(2,3)

                "[\\s]*AS[\\s]*" + // 	<sp>AS<sp>

                "(" +
                PATTERN_DataType +
                ")" + // 	arg2 (datatype)		4

                "\\s*\\)") // 	<sp>)
        val gidx_arg1 = 1
        val gidx_arg2 = 7 // datatype w/o length
        val p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE)
        val m = p.matcher(sqlStatement)

        val convertMap = convertMap as TreeMap<String, String>?
        val retValue = StringBuffer(sqlStatement.length)
        while (m.find()) {
            val arg1 = m.group(gidx_arg1)
            val arg2 = m.group(gidx_arg2)
            //
            var datatype: String? = convertMap!!["\\b" + arg2.toUpperCase() + "\\b"]
            if (datatype == null) datatype = arg2
            m.appendReplacement(retValue, "cast($arg1 as $datatype)")
        }
        m.appendTail(retValue)
        return retValue.toString()
    }

    /**
     * ************************************************************************* Converts Update.
     *
     * <pre>
     * UPDATE C_Order i SET
     * =&gt; UPDATE C_Order SET
    </pre> *
     *
     * @param sqlStatement
     * @return converted statement
     */
    private fun convertUpdate(sqlStatement: String): String {
        var targetTable: String? = null
        var targetAlias: String? = null

        val sqlUpper = sqlStatement.toUpperCase()
        var token = StringBuilder()
        var previousToken: String? = null
        var charIndex = 0
        val sqlLength = sqlUpper.length
        var cnt = 0
        var isUpdate = false

        // get target table and alias
        while (charIndex < sqlLength) {
            val c = sqlStatement[charIndex]
            if (Character.isWhitespace(c)) {
                if (token.isNotEmpty()) {
                    cnt++
                    if (cnt == 1)
                        isUpdate = "UPDATE".equals(token.toString(), ignoreCase = true)
                    else if (cnt == 2)
                        targetTable = token.toString()
                    else if (cnt == 3) {
                        targetAlias = token.toString().trim { it <= ' ' }
                        if ("SET".equals(targetAlias, ignoreCase = true))
                        // no alias
                            targetAlias = targetTable
                    }
                    previousToken = token.toString()
                    token = StringBuilder()
                }
            } else {
                if ("SET".equals(previousToken, ignoreCase = true))
                    break
                else
                    token.append(c)
            }
            charIndex++
        }

        if (isUpdate && targetTable != null && sqlUpper[charIndex] == '(') {
            val updateFieldsBegin = charIndex
            var updateFields: String? = null

            var select = ""

            // get the sub query
            var beforePreviousToken: String? = null
            previousToken = null
            token = StringBuilder()
            while (charIndex < sqlLength) {
                val c = sqlUpper[charIndex]
                if (Character.isWhitespace(c)) {
                    if (token.isNotEmpty()) {
                        val currentToken = token.toString()
                        if ("(" == currentToken || currentToken.startsWith("(")) {
                            if ((")" == beforePreviousToken || beforePreviousToken != null && beforePreviousToken.endsWith(
                                    ")"
                                )) && "=" == previousToken
                            ) {
                                select = sqlStatement.substring(charIndex - currentToken.length)
                                updateFields = sqlStatement.substring(updateFieldsBegin, charIndex)
                                updateFields = updateFields.substring(0, updateFields.lastIndexOf(')'))
                                break
                            } else if (")=" == previousToken) {
                                select = sqlStatement.substring(charIndex - currentToken.length)
                                updateFields = sqlStatement.substring(updateFieldsBegin, charIndex)
                                updateFields = updateFields.substring(0, updateFields.lastIndexOf(')'))
                                break
                            } else if (previousToken != null && previousToken.endsWith(")=")) {
                                select = sqlStatement.substring(charIndex - currentToken.length)
                                updateFields = sqlStatement.substring(updateFieldsBegin, charIndex)
                                updateFields = updateFields.substring(0, updateFields.lastIndexOf(')'))
                                break
                            }
                        }
                        if (")=(" == currentToken) {
                            select = sqlStatement.substring(charIndex - 1)
                            updateFields = sqlStatement.substring(updateFieldsBegin, charIndex)
                            updateFields = updateFields.substring(0, updateFields.lastIndexOf(')'))
                            break
                        } else if (currentToken.endsWith(")=(SELECT")) {
                            select = sqlStatement.substring(charIndex - 7)
                            updateFields = sqlStatement.substring(updateFieldsBegin, charIndex)
                            updateFields = updateFields.substring(0, updateFields.lastIndexOf(')'))
                            break
                        } else if ("=(" == currentToken || currentToken.startsWith("=(")) {
                            if (")" == previousToken || previousToken != null && previousToken.endsWith(")")) {
                                select = sqlStatement.substring(charIndex - currentToken.length)
                                updateFields = sqlStatement.substring(updateFieldsBegin, charIndex)
                                updateFields = updateFields.substring(0, updateFields.lastIndexOf(')'))
                                break
                            }
                        }
                        beforePreviousToken = previousToken
                        previousToken = token.toString()
                        token = StringBuilder()
                    }
                } else {
                    token.append(c)
                }
                charIndex++
            }
            if (updateFields != null && updateFields.startsWith("("))
                updateFields = updateFields.substring(1)

            var subQueryEnd = 0
            val subQueryStart = select.indexOf('(')
            var subWhere: String? = null
            var open = -1
            for (i in subQueryStart until select.length) {
                val c = select[i]
                if (c == '(') open++

                if (c == ')') open--

                if (open == -1) {
                    subQueryEnd = i + 1
                    break
                }
            }

            var mainWhere: String? = ""
            var otherUpdateFields = ""
            // get update where clause
            token = StringBuilder()
            for (i in subQueryEnd until select.length) {
                val c = select[i]
                if (Character.isWhitespace(c)) {
                    if (token.isNotEmpty()) {
                        if ("WHERE".equals(token.toString(), ignoreCase = true)) {
                            otherUpdateFields = select.substring(subQueryEnd, i - 5).trim { it <= ' ' }
                            mainWhere = select.substring(i + 1)
                            break
                        }
                        token = StringBuilder()
                    }
                } else {
                    token.append(c)
                }
            }

            val subQuery = select.substring(subQueryStart, subQueryEnd)

            // get join table and alias
            var joinTable: String? = null
            var joinAlias: String? = null
            token = StringBuilder()
            previousToken = null
            var joinFieldsBegin = 0
            var joinFields: String? = null
            var joinFromClause: String? = null
            var joinFromClauseStart = 0
            open = -1
            for (i in 0 until subQuery.length) {
                val c = subQuery[i]
                if (Character.isWhitespace(c)) {
                    if (token.isNotEmpty() && open < 0) {
                        if ("FROM".equals(previousToken, ignoreCase = true)) {
                            joinTable = token.toString()
                        }
                        if ("WHERE".equals(token.toString(), ignoreCase = true)) {
                            subWhere = subQuery.substring(i + 1, subQuery.length - 1)
                            joinFromClause = subQuery.substring(joinFromClauseStart, i - 5).trim { it <= ' ' }
                            break
                        }
                        if ("FROM".equals(token.toString(), ignoreCase = true)) {
                            joinFields = subQuery.substring(joinFieldsBegin, i - 4)
                            joinFromClauseStart = i
                        }
                        if (previousToken != null && previousToken == joinTable) {
                            joinAlias = token.toString()
                        }
                        previousToken = token.toString()
                        token = StringBuilder()
                    }
                } else {
                    if (joinFieldsBegin == 0) {
                        if (token.isEmpty() && ("SELECT".equals(
                                previousToken,
                                ignoreCase = true
                            ) || previousToken != null && previousToken.toUpperCase().endsWith("SELECT"))
                        )
                            joinFieldsBegin = i
                    } else if (c == '(')
                        open++
                    else if (c == ')') open--
                    token.append(c)
                }
            }
            if (joinFromClause == null) joinFromClause = subQuery.substring(joinFromClauseStart).trim { it <= ' ' }
            if (joinAlias == null) joinAlias = joinTable

            // construct update clause
            val Update = StringBuilder("UPDATE ")
            Update.append(targetTable)
            if (targetAlias != targetTable) Update.append(" ").append(targetAlias)

            Update.append(" SET ")

            var f = updateFields?.length ?: throw AdempiereSystemError("No update fields!")
            var fj: Int
            var updateField: String
            var joinField: String

            var useSubQuery = false
            if (useAggregateFunction(joinFields!!)) useSubQuery = true

            while (f > 0) {
                f = Util.findIndexOf(updateFields, ',')
                if (f < 0) {
                    updateField = updateFields ?: throw AdempiereSystemError("No update fields!")
                    joinField = joinFields!!.trim { it <= ' ' }
                    if (joinField.indexOf('.') < 0 && isIdentifier(joinField)) {
                        joinField = "$joinAlias.$joinField"
                    }

                    Update.append(updateField.trim { it <= ' ' })
                    Update.append("=")
                    if (useSubQuery) {
                        Update.append("( SELECT ")
                        Update.append(joinField)
                        Update.append(" FROM ")
                        Update.append(joinFromClause)
                        Update.append(" WHERE ")
                        Update.append(subWhere!!.trim { it <= ' ' })
                        Update.append(" ) ")
                        Update.append(otherUpdateFields)
                        if (mainWhere != null) {
                            Update.append(" WHERE ")
                            Update.append(mainWhere)
                        }
                    } else {
                        Update.append(joinField)
                        Update.append(otherUpdateFields)
                        Update.append(" FROM ")
                        Update.append(joinFromClause)
                        Update.append(" WHERE ")
                        subWhere = addAliasToIdentifier(subWhere, joinAlias)
                        Update.append(subWhere.trim { it <= ' ' })

                        mainWhere = if (mainWhere != null)
                            " AND $mainWhere"
                        else
                            ""

                        mainWhere = addAliasToIdentifier(mainWhere, targetAlias)
                        Update.append(mainWhere)
                    }
                } else {

                    updateField = updateFields!!.substring(0, f)
                    fj = Util.findIndexOf(joinFields, ',')
                    // fieldsjoin.indexOf(',');

                    joinField = if (fj > 0) joinFields!!.substring(
                        0,
                        fj
                    ).trim { it <= ' ' } else joinFields!!.trim { it <= ' ' }
                    if (joinField.indexOf('.') < 0 && isIdentifier(joinField)) {
                        joinField = "$joinAlias.$joinField"
                    }
                    Update.append(updateField.trim { it <= ' ' })
                    Update.append("=")
                    if (useSubQuery) {
                        Update.append("( SELECT ")
                        Update.append(joinField)
                        Update.append(" FROM ")
                        Update.append(joinFromClause)
                        Update.append(" WHERE ")
                        Update.append(subWhere!!.trim { it <= ' ' })
                        Update.append(" ) ")
                    } else {
                        Update.append(joinField)
                    }
                    Update.append(",")
                    joinFields = joinFields.substring(fj + 1)
                }

                updateFields = updateFields.substring(f + 1)

                // System.out.println("Update" + Update);
            }

            return Update.toString()
        }
        // System.out.println("Convert Update:"+sqlUpdate);
        return sqlStatement
    } // convertDecode

    /**
     * Check if one of the field is using standard sql aggregate function
     *
     * @param fields
     * @return boolean
     */
    private fun useAggregateFunction(fields: String): Boolean {
        val fieldsUpper = fields.toUpperCase()
        val size = fieldsUpper.length
        var buffer = StringBuilder()
        var token: String?
        for (i in 0 until size) {
            val ch = fieldsUpper[i]
            if (Character.isWhitespace(ch)) {
                if (buffer.isNotEmpty()) {
                    buffer = StringBuilder()
                }
            } else {
                if (isOperator(ch)) {
                    if (buffer.isNotEmpty()) {
                        token = buffer.toString()
                        buffer = StringBuilder()
                    } else {
                        token = null
                    }
                    if (ch == '(' && token != null) {
                        if (token == "SUM" ||
                            token == "MAX" ||
                            token == "MIN" ||
                            token == "COUNT" ||
                            token == "AVG"
                        ) {
                            return true
                        }
                    }
                } else
                    buffer.append(ch)
            }
        }

        return false
    }

    /**
     * Add table alias to identifier in where clause
     *
     * @param where
     * @param alias
     * @return converted where clause
     */
    private fun addAliasToIdentifier(where: String?, alias: String?): String {
        val sqlkey =
            "AND,OR,FROM,WHERE,JOIN,BY,GROUP,IN,INTO,SELECT,NOT,SET,UPDATE,DELETE,HAVING,IS,NULL,EXISTS,BETWEEN,LIKE,INNER,OUTER"

        val st = StringTokenizer(where!!)
        var result = ""
        var token: String
        var o = -1
        do {
            token = st.nextToken()
            val test = if (token.startsWith("(")) token.substring(1) else token
            if (!sqlkey.contains(test)) {

                token = token.trim { it <= ' ' }
                // skip subquery, non identifier and fully qualified identifier
                if (o != -1)
                    result = "$result $token"
                else {
                    result = "$result "
                    var t = StringBuilder()
                    for (i in 0 until token.length) {
                        val c = token[i]
                        if (isOperator(c)) {
                            if (t.isNotEmpty()) {
                                result = if (c == '(')
                                    result + t.toString()
                                else if (isIdentifier(t.toString()) && t.toString().indexOf('.') == -1)
                                    "$result$alias.$t"
                                else
                                    result + t.toString()
                                t = StringBuilder()
                            }
                            result += c
                        } else {
                            t.append(c)
                        }
                    }
                    if (t.isNotEmpty()) {
                        if ("SELECT".equals(t.toString().toUpperCase(), ignoreCase = true)) {
                            o = 0
                            result += t.toString()
                        } else if (isIdentifier(t.toString()) && t.toString().indexOf('.') == -1)
                            result = "$result$alias.$t"
                        else
                            result += t.toString()
                    }
                }

                if (o != -1) {
                    for (i in 0 until token.length) {
                        val c = token[i]
                        if (c == '(') o++
                        if (c == ')') o--
                    }
                }
            } else {
                result = "$result $token"
                if ("SELECT".equals(test, ignoreCase = true)) {
                    o = 0
                }
            }
        } while (st.hasMoreElements())
        return result
    }

    /**
     * Check if token is a valid sql identifier
     *
     * @param token
     * @return True if token is a valid sql identifier, false otherwise
     */
    private fun isIdentifier(token: String): Boolean {
        val size = token.length
        for (i in 0 until size) {
            val c = token[i]
            if (isOperator(c)) return false
        }
        if (token.startsWith("'") && token.endsWith("'"))
            return false
        else {
            try {
                BigDecimal(token)
                return false
            } catch (e: NumberFormatException) {
            }
        }

        return !isSQLFunctions(token)
    }

    private fun isSQLFunctions(token: String): Boolean {
        return when {
            token.equals("current_timestamp", ignoreCase = true) -> true
            token.equals("current_time", ignoreCase = true) -> true
            token.equals("current_date", ignoreCase = true) -> true
            token.equals("localtime", ignoreCase = true) -> true
            else -> token.equals("localtimestamp", ignoreCase = true)
        }
    }

    // begin vpj-cd e-evolution 08/02/2005

    /**
     * ************************************************************************* convertAlias - for
     * compatibility with 8.1
     *
     * @param sqlStatement
     * @return converted statementf
     */
    private fun convertAlias(sqlStatement: String): String {
        val tokens = sqlStatement.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var table: String? = null
        var alias: String? = null
        if ("UPDATE".equals(tokens[0], ignoreCase = true)) {
            if ("SET".equals(tokens[2], ignoreCase = true)) return sqlStatement
            table = tokens[1]
            alias = tokens[2]
        } else if ("INSERT".equals(tokens[0], ignoreCase = true)) {
            if ("VALUES".equals(tokens[3], ignoreCase = true) || "SELECT".equals(tokens[3], ignoreCase = true))
                return sqlStatement
            if (tokens[2].indexOf('(') > 0)
                return sqlStatement
            else if (tokens[3].indexOf('(') < 0 || tokens[3].indexOf('(') > 0) {
                table = tokens[2]
                alias = tokens[3]
            } else {
                return sqlStatement
            }
        } else if ("DELETE".equals(tokens[0], ignoreCase = true)) {
            if (tokens.size < 4) return sqlStatement
            if ("WHERE".equals(tokens[3], ignoreCase = true)) return sqlStatement
            table = tokens[2]
            alias = tokens[3]
        }
        if (table != null && alias != null) {
            if (alias.indexOf('(') > 0) alias = alias.substring(0, alias.indexOf('('))
            var converted = sqlStatement.replaceFirst("\\s$alias\\s".toRegex(), " ")
            converted = converted.replace("\\b$alias\\.".toRegex(), "$table.")
            converted = converted.replace("[+]$alias\\.".toRegex(), "+$table.")
            converted = converted.replace("[-]$alias\\.".toRegex(), "-$table.")
            converted = converted.replace("[*]$alias\\.".toRegex(), "*$table.")
            converted = converted.replace("[/]$alias\\.".toRegex(), "/$table.")
            converted = converted.replace("[%]$alias\\.".toRegex(), "%$table.")
            converted = converted.replace("[<]$alias\\.".toRegex(), "<$table.")
            converted = converted.replace("[>]$alias\\.".toRegex(), ">$table.")
            converted = converted.replace("[=]$alias\\.".toRegex(), "=$table.")
            converted = converted.replace("[|]$alias\\.".toRegex(), "|$table.")
            converted = converted.replace("[(]$alias\\.".toRegex(), "($table.")
            converted = converted.replace("[)]$alias\\.".toRegex(), ")$table.")
            return converted
        } else {
            return sqlStatement
        }
    } //
    // end vpj-cd e-evolution 02/24/2005 PostgreSQL

    // begin vpj-cd 08/02/2005
    // ALTER TABLE AD_FieldGroup MODIFY IsTab CHAR(1) DEFAULT N;
    // ALTER TABLE AD_FieldGroup ALTER COLUMN IsTab TYPE CHAR(1); ALTER TABLE
    // AD_FieldGroup ALTER COLUMN SET DEFAULT 'N';
    private fun convertDDL(sqlStatement: String): String {
        if (sqlStatement.toUpperCase().indexOf("ALTER TABLE ") == 0) {
            var action: String? = null
            var beginCol = -1
            if (sqlStatement.toUpperCase().indexOf(" MODIFY ") > 0) {
                action = " MODIFY "
                beginCol = sqlStatement.toUpperCase().indexOf(" MODIFY ") + action.length
            } else if (sqlStatement.toUpperCase().indexOf(" ADD ") > 0) {
                if (!sqlStatement.toUpperCase().contains(" ADD CONSTRAINT ") && !sqlStatement.toUpperCase().contains(" ADD FOREIGN KEY ")) {
                    action = " ADD "
                    beginCol = sqlStatement.toUpperCase().indexOf(" ADD ") + action.length
                }
            }

            // System.out.println( "MODIFY :" +
            // sqlStatement.toUpperCase().indexOf(" MODIFY "));
            // System.out.println( "ADD :" +
            // sqlStatement.toUpperCase().indexOf(" ADD "));
            // System.out.println( "begincolumn:" + sqlStatement +
            // "begincolumn:" + begin_col );

            if (beginCol < 0) return sqlStatement

            val end_col: Int
            val begin_default: Int

            var column: String
            val type: String?
            var defaultvalue: String? = null
            var nullclause: String? = null
            var DDL: String?

            column = sqlStatement.substring(beginCol)
            end_col = beginCol + column.indexOf(' ')
            column = sqlStatement.substring(beginCol, end_col)
            // System.out.println(" column:" + column + " begincolumn:" +
            // begin_col + "en column:" + end_col );
            // System.out.println(" type " + sqlStatement.substring(end_col
            // + 1));
            var rest = sqlStatement.substring(end_col + 1)

            if (action == " ADD ") {
                if (rest.toUpperCase().contains(" DEFAULT ")) {
                    val beforeDefault = rest.substring(0, rest.toUpperCase().indexOf(" DEFAULT "))
                    begin_default = rest.toUpperCase().indexOf(" DEFAULT ") + 9
                    defaultvalue = rest.substring(begin_default)
                    val nextspace = defaultvalue.indexOf(' ')
                    if (nextspace > -1) {
                        rest = defaultvalue.substring(nextspace)
                        defaultvalue = defaultvalue.substring(0, defaultvalue.indexOf(' '))
                    } else {
                        rest = ""
                    }
                    if (defaultvalue.equals("NULL", ignoreCase = true) || defaultvalue.equals(
                            "statement_timestamp()",
                            ignoreCase = true
                        )
                    ) {
                        DDL = (sqlStatement.substring(0, beginCol - action.length) +
                                " ADD COLUMN " +
                                column +
                                " " +
                                beforeDefault.trim { it <= ' ' } +
                                " DEFAULT " +
                                defaultvalue.trim { it <= ' ' } +
                                " " +
                                rest.trim { it <= ' ' })
                    } else {
                        // Check if default value is already quoted, no need to double quote
                        if (defaultvalue.startsWith("'") && defaultvalue.endsWith("'")) {
                            DDL = (sqlStatement.substring(0, beginCol - action.length) +
                                    " ADD COLUMN " +
                                    column +
                                    " " +
                                    beforeDefault.trim { it <= ' ' } +
                                    " DEFAULT " +
                                    defaultvalue.trim { it <= ' ' } +
                                    " " +
                                    rest.trim { it <= ' ' })
                        } else {
                            DDL = (sqlStatement.substring(0, beginCol - action.length) +
                                    " ADD COLUMN " +
                                    column +
                                    " " +
                                    beforeDefault.trim { it <= ' ' } +
                                    " DEFAULT '" +
                                    defaultvalue.trim { it <= ' ' } +
                                    "' " +
                                    rest.trim { it <= ' ' })
                        }
                    }
                } else {
                    DDL = (sqlStatement.substring(0, beginCol - action.length) +
                            action +
                            "COLUMN " +
                            column +
                            " " +
                            rest.trim { it <= ' ' })
                }
            } else {
                rest = rest.trim { it <= ' ' }
                if (rest.toUpperCase().startsWith("NOT ") ||
                    rest.toUpperCase().startsWith("NULL ") ||
                    rest.toUpperCase() == "NULL" ||
                    rest.toUpperCase() == "NOT NULL"
                ) {
                    type = null
                } else {
                    val typeEnd = rest.indexOf(' ')
                    type = if (typeEnd > 0) rest.substring(0, typeEnd).trim { it <= ' ' } else rest
                    rest = if (typeEnd > 0) rest.substring(typeEnd) else ""
                }

                if (rest.toUpperCase().contains(" DEFAULT ")) {
                    begin_default = rest.toUpperCase().indexOf(" DEFAULT ") + 9
                    defaultvalue = rest.substring(begin_default)
                    val nextspace = defaultvalue.indexOf(' ')
                    if (nextspace > -1) {
                        rest = defaultvalue.substring(nextspace)
                        defaultvalue = defaultvalue.substring(0, defaultvalue.indexOf(' '))
                    } else {
                        rest = ""
                    }
                    // Check if default value is already quoted
                    defaultvalue = defaultvalue.trim { it <= ' ' }
                    if (defaultvalue.startsWith("'") && defaultvalue.endsWith("'"))
                        defaultvalue = defaultvalue.substring(1, defaultvalue.length - 1)

                    if (rest.toUpperCase().contains("NOT NULL"))
                        nullclause = "NOT NULL"
                    else if (rest.toUpperCase().contains("NULL")) nullclause = "NULL"

                    // return DDL;
                } else if (rest.toUpperCase().contains("NOT NULL")) {
                    nullclause = "NOT NULL"
                } else if (rest.toUpperCase().contains("NULL")) {
                    nullclause = "NULL"
                }

                DDL = "INSERT INTO t_alter_column values('"
                var tableName = sqlStatement.substring(0, beginCol - action!!.length)
                tableName = tableName.toUpperCase().replace("ALTER TABLE".toRegex(), "")
                tableName = tableName.trim { it <= ' ' }.toLowerCase()
                DDL = "$DDL$tableName','$column',"
                DDL = if (type != null)
                    "$DDL'$type',"
                else
                    DDL + "null,"
                DDL = if (nullclause != null)
                    "$DDL'$nullclause',"
                else
                    DDL + "null,"
                DDL = if (defaultvalue != null)
                    "$DDL'$defaultvalue'"
                else
                    DDL + "null"
                DDL = "$DDL)"
            }
            return DDL
        }

        return sqlStatement
    }

    companion object {
        /**
         * RegEx: insensitive and dot to include line end characters
         */
        private const val REGEX_FLAGS = Pattern.CASE_INSENSITIVE or Pattern.DOTALL
    }
} // Convert
