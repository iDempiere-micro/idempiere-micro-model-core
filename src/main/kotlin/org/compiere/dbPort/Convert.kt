package org.compiere.dbPort

import java.util.Vector
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Convert SQL to Target DB
 *
 * @author Jorg Janke, Victor Perez
 * @author Teo Sarca, www.arhipac.ro
 *  * BF [ 2782095 ] Do not log *Access records
 * https://sourceforge.net/tracker/?func=detail&aid=2782095&group_id=176962&atid=879332
 *  * TODO: BF [ 2782611 ] Migration scripts are not UTF8
 * https://sourceforge.net/tracker/?func=detail&aid=2782611&group_id=176962&atid=879332
 * @author Teo Sarca
 *  * BF [ 3137355 ] PG query not valid when contains quotes and backslashes
 * https://sourceforge.net/tracker/?func=detail&aid=3137355&group_id=176962&atid=879332
 * @version $Id: Convert.java,v 1.3 2006/07/30 00:55:04 jjanke Exp $
 */
abstract class Convert {

    /**
     * Last Conversion Error
     */
    protected var m_conversionError: String? = null
    /**
     * Verbose Messages
     */
    protected var m_verbose = true

    /**
     * Get convert map for use in sql convertion
     *
     * @return map
     */
    protected open val convertMap: Map<String, String>?
        get() = null

    /**
     * Convert SQL Statement (stops at first error). Statements are delimited by / If an error occured
     * hadError() returns true. You can get details via getConversionError()
     *
     * @param sqlStatements
     * @return converted statement as a string
     */
    fun convertAll(sqlStatements: String): String {
        val sql = convert(sqlStatements)
        val sb = StringBuilder(sqlStatements.length + 10)
        for (i in sql.indices) {
            //  line.separator
            sb.append(sql[i]).append("\n;\n")
            if (m_verbose) logger.info("Statement " + i + ": " + sql[i])
        }
        return sb.toString()
    } //  convertAll

    /**
     * Convert SQL Statement (stops at first error). If an error occured hadError() returns true. You
     * can get details via getConversionError()
     *
     * @param sqlStatements
     * @return Array of converted Statements
     */
    fun convert(sqlStatements: String): Array<String> {
        return convertStatement(sqlStatements)
    } //  convert

    /**
     * Clean up Statement. Remove trailing spaces, carrige return and tab
     *
     * @param statement
     * @return sql statement
     */
    protected fun cleanUpStatement(statement: String): String {
        var clean = statement.trim { it <= ' ' }

        // Convert cr/lf/tab to single space
        val m = Pattern.compile("\\s+").matcher(clean)
        clean = m.replaceAll(" ")

        clean = clean.trim { it <= ' ' }
        return clean
    } // removeComments

    /**
     * Utility method to replace quoted string with a predefined marker
     *
     * @param retValue
     * @param retVars
     * @return string
     */
    protected fun replaceQuotedStrings(inputValue: String, retVars: Vector<String>): String {
        var localInputValue = inputValue
        // save every value
        // Carlos Ruiz - globalqss - better matching regexp
        retVars.clear()

        // First we need to replace double quotes to not be matched by regexp - Teo Sarca BF [3137355 ]
        val quoteMarker = "<--QUOTE" + System.currentTimeMillis() + "-->"
        localInputValue = localInputValue.replace("''", quoteMarker)

        val p = Pattern.compile("'[[^']*]*'")
        val m = p.matcher(localInputValue)
        var i = 0
        val retValue = StringBuffer(localInputValue.length)
        while (m.find()) {
            val `var` = localInputValue
                .substring(m.start(), m.end())
                .replace(quoteMarker, "''") // Put back quotes, if any
            retVars.addElement(`var`)
            m.appendReplacement(retValue, "<--$i-->")
            i++
        }
        m.appendTail(retValue)
        return retValue.toString().replace(quoteMarker, "''") // Put back quotes, if any
    }

    /**
     * Utility method to recover quoted string store in retVars
     *
     * @param retValue
     * @param retVars
     * @return string
     */
    protected fun recoverQuotedStrings(retValue: String, retVars: Vector<String>): String {
        var returnValue = retValue
        for (i in retVars.indices) {
            // hengsin, special character in replacement can cause exception
            var replacement = retVars[i]
            replacement = escapeQuotedString(replacement)
            returnValue = returnValue.replace("<--$i-->", replacement)
        }
        return returnValue
    }

    /**
     * hook for database specific escape of quoted string ( if needed )
     *
     * @param in
     * @return string
     */
    protected open fun escapeQuotedString(`in`: String): String {
        return `in`
    }

    /**
     * Convert simple SQL Statement. Based on ConvertMap
     *
     * @param sqlStatement
     * @return converted Statement
     */
    private fun applyConvertMap(sqlStatement: String): String {
        // Error Checks
        if (sqlStatement.toUpperCase().contains("EXCEPTION WHEN")) {
            val error = "Exception clause needs to be converted: $sqlStatement"
            logger.info(error)
            m_conversionError = error
            return sqlStatement
        }

        // Carlos Ruiz - globalqss
        // Standard Statement -- change the keys in ConvertMap

        var retValue = sqlStatement

        var p: Pattern
        var m: Matcher

        // for each iteration in the conversion map
        val convertMap = convertMap
        if (convertMap != null) {
            for (regex in convertMap.keys) {

                // replace the key on convertmap (i.e.: number by numeric)
                val replacement = convertMap[regex]
                try {
                    p = Pattern.compile(regex, REGEX_FLAGS)
                    m = p.matcher(retValue)
                    retValue = m.replaceAll(replacement)
                } catch (e: Exception) {
                    val error = "Error expression: $regex - $e"
                    logger.info(error)
                    m_conversionError = error
                }
            }
        }
        return retValue
    } // convertSimpleStatement

    /**
     * do convert map base conversion
     *
     * @param sqlStatement
     * @return string
     */
    protected fun convertWithConvertMap(sqlStatement: String): String {
        var localSqlStatement = sqlStatement
        try {
            localSqlStatement = applyConvertMap(cleanUpStatement(localSqlStatement))
        } catch (e: RuntimeException) {
            logger.warn(e.localizedMessage)
        }

        return localSqlStatement
    }

    /**
     * Convert single Statements. - remove comments - process FUNCTION/TRIGGER/PROCEDURE - process
     * Statement
     *
     * @param sqlStatement
     * @return converted statement
     */
    protected abstract fun convertStatement(sqlStatement: String): Array<String>

    companion object {

        /**
         * RegEx: insensitive and dot to include line end characters
         */
        const val REGEX_FLAGS = Pattern.CASE_INSENSITIVE or Pattern.DOTALL
    }
} //  Convert
