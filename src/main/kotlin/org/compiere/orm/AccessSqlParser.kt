package org.compiere.orm

import org.compiere.model.SqlTableInfo
import org.idempiere.common.util.CLogger

import java.util.ArrayList
import java.util.StringTokenizer
import java.util.logging.Level

private const val FROM = " FROM "

private const val FROM_LENGTH = FROM.length
private const val WHERE = " WHERE "
private const val ON = " ON "

/**
 * Parse FROM in SQL WHERE clause
 *
 * @author Jorg Janke
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *  * BF [ 1652623 ] AccessSqlParser.getTableInfo(String) - tablename parsing bug
 *  * BF [ 1964496 ] AccessSqlParser is not parsing well JOIN CLAUSE
 *  * BF [ 2840157 ] AccessSqlParser is not parsing well ON keyword
 * https://sourceforge.net/tracker/?func=detail&aid=2840157&group_id=176962&atid=879332
 * @version $Id: AccessSqlParser.java,v 1.3 2006/07/30 00:58:36 jjanke Exp $
 */
class AccessSqlParser
/**
 * Full Constructor
 *
 * @param sql sql command
 */
    (sql: String) {
    /**
     * Logger
     */
    private val log = CLogger.getCLogger(javaClass)
    /**
     * Original SQL
     */
    private var m_sqlOriginal: String? = null
    /**
     * SQL Selects
     */
    private var m_sql: Array<String>? = null
    /**
     * List of Arrays
     */
    private val m_tableInfo = ArrayList<Array<SqlTableInfo>>()

    /**
     * Get index of main Statements
     *
     * @return index of main statement or -1 if not found
     */
    val mainSqlIndex: Int
        get() {
            if (m_sql == null)
                return -1
            else if (m_sql!!.size == 1) return 0
            for (i in m_sql!!.indices.reversed()) {
                if (m_sql!![i][0] != '(') return i
            }
            return -1
        } // 	getMainSqlIndex

    /**
     * Get main sql Statement
     *
     * @return main statement
     */
    val mainSql: String?
        get() {
            if (m_sql == null) return m_sqlOriginal

            if (m_sql!!.size == 1) return m_sql!![0]
            for (i in m_sql!!.indices.reversed()) {
                if (m_sql!![i][0] != '(') return m_sql!![i]
            }
            return ""
        } // 	getMainSql

    init {
        setSql(sql)
    } // 	AccessSqlParser

    /**
     * Set Sql and parse it
     *
     * @param sql sql
     */
    fun setSql(sql: String?) {
        if (sql == null) throw IllegalArgumentException("No SQL")
        var changedSql = sql
        var index = changedSql.indexOf("\nFROM ")
        if (index != -1) changedSql = changedSql.replace("\nFROM ", FROM)
        index = changedSql.indexOf("\nWHERE ")
        if (index != -1) changedSql = changedSql.replace("\nWHERE ", WHERE)
        //
        m_sqlOriginal = changedSql
        parse()
    } // 	setSQL

    /**
     * Parse Original SQL. Called from setSql or Constructor.
     *
     * @return true if pased
     */
    fun parse(): Boolean {
        if (m_sqlOriginal == null || m_sqlOriginal!!.length == 0)
            throw IllegalArgumentException("No SQL")
        //
        getSelectStatements()
        // 	analyse each select
        for (s in m_sql!!) {
            val info = getTableInfo(s.trim { it <= ' ' })
            m_tableInfo.add(info)
        }
        //
        return m_tableInfo.size > 0
    } // 	parse

    /**
     * Parses m_sqlOriginal and creates Array of m_sql statements
     */
    private fun getSelectStatements() {
        var sqlIn = arrayOf<String>(m_sqlOriginal!!)
        var sqlOut: Array<String>
        try {
            sqlOut = getSubSQL(sqlIn)
        } catch (e: Exception) {
            log.log(Level.SEVERE, m_sqlOriginal, e)
            throw IllegalArgumentException(m_sqlOriginal)
        }

        // 	a sub-query was found
        while (sqlIn.size != sqlOut.size) {
            sqlIn = sqlOut
            try {
                sqlOut = getSubSQL(sqlIn)
            } catch (e: Exception) {
                log.log(Level.SEVERE, m_sqlOriginal, e)
                throw IllegalArgumentException(sqlOut.size.toString() + ": " + m_sqlOriginal)
            }
        }
        m_sql = sqlOut
    } // 	getSelectStatements

    /**
     * Get Sub SQL of sql statements
     *
     * @param sqlIn array of input sql
     * @return array of resulting sql
     */
    private fun getSubSQL(sqlIn: Array<String>): Array<String> {
        val list = ArrayList<String>()
        for (s in sqlIn) {
            var sql = s
            var index = sql.indexOf("(SELECT ", 7)
            while (index != -1) {
                var endIndex = index + 1
                var parenthesisLevel = 0
                // 	search for the end of the sql
                while (endIndex++ < sql.length) {
                    val c = sql[endIndex]
                    if (c == ')') {
                        if (parenthesisLevel == 0)
                            break
                        else
                            parenthesisLevel--
                    } else if (c == '(') parenthesisLevel++
                }
                val subSQL = sql.substring(index, endIndex + 1)
                list.add(subSQL)
                // 	remove inner SQL (##)
                sql = sql.substring(0, index + 1) + "##" + sql.substring(endIndex)
                index = sql.indexOf("(SELECT ", 7)
            }
            list.add(sql) // 	last SQL
        }
        return list.toTypedArray()
    } // 	getSubSQL

    /**
     * Get Table Info for SQL
     *
     * @param sql sql
     * @return array of table info for sql
     */
    private fun getTableInfo(sql: String): Array<SqlTableInfo> {
        var localSql = sql
        val list = ArrayList<TableInfo>()
        // 	remove ()
        if (localSql.startsWith("(") && localSql.endsWith(")")) localSql = localSql.substring(1, localSql.length - 1)

        var fromIndex = localSql.indexOf(FROM)
        if (fromIndex != localSql.lastIndexOf(FROM))
            log.log(Level.WARNING, "More than one FROM clause - $localSql")
        while (fromIndex != -1) {
            var from = localSql.substring(fromIndex + FROM_LENGTH)
            var index = from.lastIndexOf(WHERE) // 	end at where
            if (index != -1) from = from.substring(0, index)
            from = from.replace("[\r\n\t ]+AS[\r\n\t ]+".toRegex(), " ")
            from = from.replace("[\r\n\t ]+as[\r\n\t ]+".toRegex(), " ")
            from = from.replace("[\r\n\t ]+INNER[\r\n\t ]+JOIN[\r\n\t ]+".toRegex(), ", ")
            from = from.replace("[\r\n\t ]+LEFT[\r\n\t ]+OUTER[\r\n\t ]+JOIN[\r\n\t ]+".toRegex(), ", ")
            from = from.replace("[\r\n\t ]+RIGHT[\r\n\t ]+OUTER[\r\n\t ]+JOIN[\r\n\t ]+".toRegex(), ", ")
            from = from.replace("[\r\n\t ]+FULL[\r\n\t ]+JOIN[\r\n\t ]+".toRegex(), ", ")
            from = from.replace("[\r\n\t ]+LEFT[\r\n\t ]+JOIN[\r\n\t ]+".toRegex(), ", ")
            from = from.replace("[\r\n\t ]+RIGHT[\r\n\t ]+JOIN[\r\n\t ]+".toRegex(), ", ")
            from = from.replace("[\r\n\t ]+JOIN[\r\n\t ]+".toRegex(), ", ")

            from = from.replace("[\r\n\t ]+[Oo][Nn][\r\n\t ]+".toRegex(), ON) // teo_sarca, BF [ 2840157 ]
            // 	Remove ON clause - assumes that there is no IN () in the clause
            index = from.indexOf(ON)
            while (index != -1) {
                var indexClose = getIndexClose(from) // 	does not catch "IN (1,2)" in ON
                val indexNextOn = from.indexOf(ON, index + 4)
                if (indexNextOn != -1) indexClose = from.lastIndexOf(')', indexNextOn)
                if (indexClose != -1) {
                    if (index > indexClose) {
                        throw IllegalStateException(
                            "Could not remove (index=$index > indexClose=$indexClose) - $from"
                        )
                    }
                    from = from.substring(0, index) + from.substring(indexClose + 1)
                } else {
                    log.log(Level.SEVERE, "Could not remove ON $from")
                    break
                }
                index = from.indexOf(ON)
            }

            val tableST = StringTokenizer(from, ",")
            while (tableST.hasMoreTokens()) {
                val tableString = tableST.nextToken().trim { it <= ' ' }
                val synST = StringTokenizer(
                    tableString,
                    " \r\n\t"
                )
                // tablename parsing bug
                val tableInfo: TableInfo
                if (synST.countTokens() > 1)
                    tableInfo = TableInfo(synST.nextToken(), synST.nextToken())
                else
                    tableInfo = TableInfo(tableString)
                list.add(tableInfo)
            }
            //
            localSql = localSql.substring(0, fromIndex)
            fromIndex = localSql.lastIndexOf(FROM)
        }
        return list.toTypedArray()
    } // 	getTableInfo

    /**
     * String Representation
     *
     * @return info
     */
    override fun toString(): String {
        val sb = StringBuilder("AccessSqlParser[")
        for (i in m_tableInfo.indices) {
            if (i > 0) sb.append("|")
            val info = m_tableInfo[i]
            for (ii in info.indices) {
                if (ii > 0) sb.append(",")
                sb.append(info[ii].toString())
            }
        }
        sb.append("|").append(mainSqlIndex)
        sb.append("]")
        return sb.toString()
    } // 	toString

    /**
     * Get Table Info.
     *
     * @param index record index
     * @return table info
     */
    fun getTableInfo(index: Int): Array<SqlTableInfo>? {
        return if (index < 0 || index > m_tableInfo.size) null else m_tableInfo[index]
    } // 	getTableInfo

    /**
     * Get index of ')'
     *
     * @return index of ')'
     */
    fun getIndexClose(from: String): Int {
        var parenthesisLevel = 0
        var indexOpen = from.indexOf('(')
        var index = -1
        while (indexOpen != -1) {
            parenthesisLevel++
            val indexNextOpen = from.indexOf('(', indexOpen + 1)
            val indexClose = from.indexOf(')', indexOpen + 1)
            if (indexNextOpen == -1 || indexClose < indexNextOpen) {
                break
            }
            indexOpen = from.indexOf('(', indexNextOpen)
        }
        while (parenthesisLevel > 0) {
            index = from.indexOf(')', index + 1)
            parenthesisLevel--
        }
        return index
    }

    /**
     * Table Info VO
     */
    class TableInfo
    /**
     * Constructor
     *
     * @param tableName table
     * @param synonym synonym
     */
    @JvmOverloads constructor(
        /**
         * Get TableName
         *
         * @return table name
         */
        // 	getTableName
        override val tableName: String,
        private val m_synonym: String? = null
    ) : SqlTableInfo {

        /**
         * Get Table Synonym
         *
         * @return synonym
         */
        override val synonym: String
            get() = m_synonym ?: "" // 	getSynonym

        /**
         * String Representation
         *
         * @return info
         */
        override fun toString(): String {
            val sb = StringBuilder(tableName)
            if (synonym.isNotEmpty()) sb.append("=").append(m_synonym)
            return sb.toString()
        } // 	toString
    }
} // 	AccessSqlParser
