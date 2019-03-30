package org.compiere.dbPort

import org.idempiere.common.util.Util

import java.util.ArrayList
import java.util.HashMap

/**
 * * Convert from oracle syntax to sql 92 standard
 *
 * @author Low Heng Sin
 */
abstract class ConvertSQL92 : Convert() {

    /**
     * ************************************************************************ Convert Outer Join.
     * Converting joins can ve very complex when multiple tables/keys are involved. The main scenarios
     * supported are two tables with multiple key columns and multiple tables with single key columns.
     *
     * <pre>
     * SELECT a.Col1, b.Col2 FROM tableA a, tableB b WHERE a.ID=b.ID(+)
     * => SELECT a.Col1, b.Col2 FROM tableA a LEFT OUTER JOIN tableB b ON (a.ID=b.ID)
     *
     * SELECT a.Col1, b.Col2 FROM tableA a, tableB b WHERE a.ID(+)=b.ID
     * => SELECT a.Col1, b.Col2 FROM tableA a RIGHT OUTER JOIN tableB b ON (a.ID=b.ID)
     * Assumptions:
     * - No outer joins in sub queries (ignores sub-queries)
     * - OR condition ignored (not sure what to do, should not happen)
     * Limitations:
     * - Parameters for outer joins must be first - as sequence of parameters changes
    </pre> *
     *
     * @param sqlStatement
     * @return converted statement
     */
    protected fun convertOuterJoin(sqlStatement: String): String {
        //
        val fromIndex = Util.findIndexOf(sqlStatement.toUpperCase(), " FROM ")
        val whereIndex = Util.findIndexOf(sqlStatement.toUpperCase(), " WHERE ")
        var endWhereIndex = Util.findIndexOf(sqlStatement.toUpperCase(), " GROUP BY ")
        if (endWhereIndex == -1)
            endWhereIndex = Util.findIndexOf(sqlStatement.toUpperCase(), " ORDER BY ")
        if (endWhereIndex == -1) endWhereIndex = sqlStatement.length
        //
        logger.trace { "OuterJoin<== $sqlStatement" }
        //
        val selectPart = sqlStatement.substring(0, fromIndex)
        val fromPart = sqlStatement.substring(fromIndex, whereIndex)
        val wherePart = sqlStatement.substring(whereIndex, endWhereIndex)
        val rest = sqlStatement.substring(endWhereIndex)

        //  find/remove all (+) from WHERE clause ------------------------------
        var newWherePart = wherePart
        val joins = ArrayList<String>()
        var pos = newWherePart.indexOf("(+)")
        while (pos != -1) {
            //  find starting point
            var start = newWherePart.lastIndexOf(" AND ", pos)
            var startOffset = 5
            if (start == -1) {
                start = newWherePart.lastIndexOf(" OR ", pos)
                startOffset = 4
            }
            if (start == -1) {
                start = newWherePart.lastIndexOf("WHERE ", pos)
                startOffset = 6
            }
            if (start == -1) {
                val error = "Start point not found in clause $wherePart"
                logger.error(error)
                m_conversionError = error
                return sqlStatement
            }
            //  find end point
            var end = newWherePart.indexOf(" AND ", pos)
            if (end == -1) end = newWherePart.indexOf(" OR ", pos)
            if (end == -1) end = newWherePart.length
            // 	log.info("<= " + newWherePart + " - Start=" + start + "+" + startOffset + ", End=" + end);

            //  extract condition
            val condition = newWherePart.substring(start + startOffset, end)
            joins.add(condition)
            logger.trace { "->$condition" }
            //  new WHERE clause
            newWherePart = newWherePart.substring(0, start) + newWherePart.substring(end)
            // 	log.info("=> " + newWherePart);
            //
            pos = newWherePart.indexOf("(+)")
        }
        //  correct beginning
        newWherePart = newWherePart.trim { it <= ' ' }
        if (newWherePart.startsWith("AND "))
            newWherePart = "WHERE" + newWherePart.substring(3)
        else if (newWherePart.startsWith("OR ")) newWherePart = "WHERE" + newWherePart.substring(2)
        logger.info("=> $newWherePart")

        //  Correct FROM clause -----------------------------------------------
        //  Disassemble FROM
        val fromParts =
            fromPart.trim { it <= ' ' }.substring(4).split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val fromAlias = HashMap<String, String>() //  tables to be processed
        val fromLookup = HashMap<String, String>() //  used tabled
        for (fromPart1 in fromParts) {
            val entry = fromPart1.trim { it <= ' ' }
            var alias = entry //  no alias
            var table = entry
            val aPos = entry.lastIndexOf(' ')
            if (aPos != -1) {
                alias = entry.substring(aPos + 1)
                table = entry.substring(0, entry.indexOf(' ')) // may have AS
            }
            fromAlias[alias] = table
            fromLookup[alias] = table
            logger.info("Alias=$alias, Table=$table")
        }

        /*
         * Single column SELECT t.TableName, w.Name FROM AD_Table t, AD_Window w WHERE
         * t.AD_Window_ID=w.AD_WindowId(+) -- 275 rows SELECT t.TableName, w.Name FROM AD_Table t LEFT
         * OUTER JOIN AD_Window w ON (t.AD_Window_ID=w.AD_Window_ID)
         *
         * <p>SELECT t.TableName, w.Name FROM AD_Table t, AD_Window w WHERE
         * t.AD_WindowId(+)=w.AD_Window_ID -- 239 rows SELECT t.TableName, w.Name FROM AD_Table t RIGHT
         * OUTER JOIN AD_Window w ON (t.AD_Window_ID=w.AD_Window_ID)
         *
         * <p>* Multiple columns SELECT tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive FROM AD_TreeNode
         * tn, AD_TreeBar tb WHERE tn.AD_Tree_ID=tb.AD_TreeId(+) AND tn.Node_ID=tb.NodeId(+) AND
         * tn.AD_Tree_ID=10 -- 235 rows SELECT tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive FROM
         * AD_TreeNode tn LEFT OUTER JOIN AD_TreeBar tb ON (tn.Node_ID=tb.Node_ID AND
         * tn.AD_Tree_ID=tb.AD_Tree_ID AND tb.AD_User_ID=0) WHERE tn.AD_Tree_ID=10
         *
         * <p>SELECT tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive FROM AD_TreeNode tn, AD_TreeBar tb
         * WHERE tn.AD_Tree_ID=tb.AD_TreeId(+) AND tn.Node_ID=tb.NodeId(+) AND tn.AD_Tree_ID=10 AND
         * tb.AD_UserId(+)=0 -- 214 rows SELECT tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive FROM
         * AD_TreeNode tn LEFT OUTER JOIN AD_TreeBar tb ON (tn.Node_ID=tb.Node_ID AND
         * tn.AD_Tree_ID=tb.AD_Tree_ID AND tb.AD_User_ID=0) WHERE tn.AD_Tree_ID=10
         */
        val newFrom = StringBuilder()
        for (i in joins.indices) {
            val first = Join(joins[i])
            first.mainTable = fromLookup[first.mainAlias]
            fromAlias.remove(first.mainAlias) //  remove from list
            first.joinTable = fromLookup[first.joinAlias]
            fromAlias.remove(first.joinAlias) //  remove from list
            logger.trace { "-First: $first" }
            //
            if (newFrom.isEmpty())
                newFrom.append(" FROM ")
            else
                newFrom.append(", ")
            newFrom
                .append(first.mainTable)
                .append(" ")
                .append(first.mainAlias)
                .append(if (first.isLeft) " LEFT" else " RIGHT")
                .append(" OUTER JOIN ")
                .append(first.joinTable)
                .append(" ")
                .append(first.joinAlias)
                .append(" ON (")
                .append(first.condition)
            //  keep it open - check for other key comparisons
            for (j in i + 1 until joins.size) {
                val second = Join(joins[j])
                second.mainTable = fromLookup[second.mainAlias]
                second.joinTable = fromLookup[second.joinAlias]
                if (first.mainTable == second.mainTable && first.joinTable == second.joinTable || second.isConditionOf(
                        first
                    )
                ) {
                    logger.trace { "-Second/key: $second" }
                    newFrom.append(" AND ").append(second.condition)
                    joins.removeAt(j) //  remove from join list
                    fromAlias.remove(first.joinAlias) //  remove from table list
                    // ----
                    for (k in i + 1 until joins.size) {
                        val third = Join(joins[k])
                        third.mainTable = fromLookup[third.mainAlias]
                        third.joinTable = fromLookup[third.joinAlias]
                        if (third.isConditionOf(second)) {
                            logger.trace { "-Third/key: $third" }
                            newFrom.append(" AND ").append(third.condition)
                            joins.removeAt(k) //  remove from join list
                            fromAlias.remove(third.joinAlias) //  remove from table list
                        } else logger.trace { "-Third/key-skip: $third" }
                    }
                } else logger.trace { "-Second/key-skip: $second" }
            }
            newFrom.append(")") //  close ON
            //  check dependency on first table
            for (j in i + 1 until joins.size) {
                val second = Join(joins[j])
                second.mainTable = fromLookup[second.mainAlias]
                second.joinTable = fromLookup[second.joinAlias]
                if (first.mainTable == second.mainTable) {
                    logger.trace { "-Second/dep: $second" }
                    //   FROM (AD_Field f LEFT OUTER JOIN AD_Column c ON (f.AD_Column_ID = c.AD_Column_ID))
                    //  LEFT OUTER JOIN AD_FieldGroup fg ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID),
                    newFrom.insert(6, '(') //  _FROM ...
                    newFrom.append(')') //  add parenthesis on previous relation
                    //
                    newFrom
                        .append(if (second.isLeft) " LEFT" else " RIGHT")
                        .append(" OUTER JOIN ")
                        .append(second.joinTable)
                        .append(" ")
                        .append(second.joinAlias)
                        .append(" ON (")
                        .append(second.condition)
                    joins.removeAt(j) //  remove from join list
                    fromAlias.remove(second.joinAlias) //  remove from table list
                    //  additional join columns would come here
                    newFrom.append(")") //  close ON
                    // ----
                    for (k in i + 1 until joins.size) {
                        val third = Join(joins[k])
                        third.mainTable = fromLookup[third.mainAlias]
                        third.joinTable = fromLookup[third.joinAlias]
                        if (second.joinTable == third.mainTable) {
                            logger.trace { "-Third-dep: $third" }
                            //   FROM ((C_BPartner p LEFT OUTER JOIN AD_User c ON
                            // (p.C_BPartner_ID=c.C_BPartner_ID))
                            //  LEFT OUTER JOIN C_BPartner_Location l ON (p.C_BPartner_ID=l.C_BPartner_ID))
                            //  LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)
                            newFrom.insert(6, '(') //  _FROM ...
                            newFrom.append(')') //  add parenthesis on previous relation
                            //
                            newFrom
                                .append(if (third.isLeft) " LEFT" else " RIGHT")
                                .append(" OUTER JOIN ")
                                .append(third.joinTable)
                                .append(" ")
                                .append(third.joinAlias)
                                .append(" ON (")
                                .append(third.condition)
                            joins.removeAt(k) //  remove from join list
                            fromAlias.remove(third.joinAlias) //  remove from table list
                            //  additional join columns would come here
                            newFrom.append(")") //  close ON
                        } else logger.trace { "-Third-skip: $third" }
                    }
                } else logger.trace { "-Second/dep-skip: $second" }
            } //  dependency on first table
        }
        //  remaining Tables
        for (alias in fromAlias.keys) {
            val table = fromAlias[alias]
            newFrom.append(", ").append(table)
            if (table != alias) newFrom.append(" ").append(alias)
        }
        logger.trace { newFrom.toString() }
        //
        val retValue = StringBuilder(sqlStatement.length + 20)
        retValue.append(selectPart).append(newFrom).append(" ").append(newWherePart).append(rest)
        //
        logger.trace { "OuterJoin==> $retValue" }
        return retValue.toString()
    } //  convertOuterJoin

    /**
     * ************************************************************************ Converts Decode.
     *
     * <pre>
     * DECODE (a, 1, 'one', 2, 'two', 'none')
     * => CASE WHEN a = 1 THEN 'one' WHEN a = 2 THEN 'two' ELSE 'none' END
    </pre> *
     *
     * @param sqlStatement
     * @return converted statement
     */
    protected fun convertDecode(sqlStatement: String, fromIndex: Int): String {
        // 	log.info("DECODE<== " + sqlStatement);
        var statement = sqlStatement
        val sb = StringBuilder("CASE")

        var index = statement.toUpperCase().indexOf("DECODE", fromIndex)
        if (index <= 0) return sqlStatement

        val previousChar = statement[index - 1]
        if (!(Character.isWhitespace(previousChar) || isOperator(previousChar))) return sqlStatement

        val firstPart = statement.substring(0, index)

        //  find the opening (
        index += 6
        while (index < statement.length) {
            val c = statement[index]
            if (Character.isWhitespace(c)) {
                index++
                continue
            }
            if (c == '(') break
            return sqlStatement
        }

        statement = statement.substring(index + 1)

        //  find the expression "a" - find first , ignoring ()
        index = Util.findIndexOf(statement, ',')
        val expression = statement.substring(0, index).trim { it <= ' ' }
        // 	log.info("Expression=" + expression);

        //  Pairs "1, 'one',"
        statement = statement.substring(index + 1)
        index = Util.findIndexOf(statement, ',')
        while (index != -1) {
            val first = statement.substring(0, index)
            val cc = statement[index]
            statement = statement.substring(index + 1)
            // 	log.info("First=" + first + ", Char=" + cc);
            //
            var error = false
            if (cc == ',') {
                index = Util.findIndexOf(statement, ',', ')')
                if (index == -1)
                    error = true
                else {
                    val second = statement.substring(0, index)
                    sb.append(" WHEN ")
                        .append(expression)
                        .append("=")
                        .append(first.trim { it <= ' ' })
                        .append(" THEN ")
                        .append(second.trim { it <= ' ' })
                    // 			log.info(">>" + sb.toString());
                    statement = statement.substring(index + 1)
                    index = Util.findIndexOf(statement, ',', ')')
                }
            } else if (cc == ')') {
                sb.append(" ELSE ").append(first.trim { it <= ' ' }).append(" END")
                // 		log.info(">>" + sb.toString());
                index = -1
            } else
                error = true
            if (error) {
                logger.error(
                    "SQL=(" +
                            sqlStatement +
                            ")\n====Result=(" +
                            sb.toString() +
                            ")\n====Statement=(" +
                            statement +
                            ")\n====First=(" +
                            first +
                            ")\n====Index=" +
                            index
                )
                m_conversionError = "Decode conversion error"
            }
        }
        sb.append(statement)
        sb.insert(0, firstPart)
        // 	log.info("DECODE==> " + sb.toString());
        return sb.toString()
    } //  convertDecode

    /**
     * ************************************************************************* Converts Delete.
     *
     * <pre>
     * DELETE C_Order i WHERE
     * =&gt; DELETE FROM C_Order WHERE
    </pre> *
     *
     * @param sqlStatement
     * @return converted statement
     */
    protected fun convertDelete(sqlStatement: String): String {

        val index = sqlStatement.toUpperCase().indexOf("DELETE ")
        return if (index < 7) {
            "DELETE FROM " + sqlStatement.substring(index + 7)
        } else sqlStatement
    } // convertDelete

    /**
     * Is character a valid sql operator
     *
     * @param c
     * @return boolean
     */
    protected fun isOperator(c: Char): Boolean {
        return when (c) {
            '=' -> true
            '<' -> true
            '>' -> true
            '|' -> true
            '(' -> true
            ')' -> true
            '+' -> true
            '-' -> true
            '*' -> true
            '/' -> true
            '!' -> true
            ',' -> true
            '?' -> true
            '#' -> true
            '@' -> true
            '~' -> true
            '&' -> true
            else -> '^' == c
        }
    }
}
