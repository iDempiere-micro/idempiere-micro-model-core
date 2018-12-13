package software.hsharp.core.util

import kotliquery.HikariCP
import kotliquery.Query
import kotliquery.Session
import kotliquery.sessionOf
import org.compiere.dbPort.Convert
import org.compiere.dbPort.Convert_PostgreSQL
import org.idempiere.common.exceptions.DBException
import org.idempiere.icommon.model.IPO
import java.math.BigDecimal
import java.sql.*
import javax.sql.RowSet

// CONSTANTS
internal fun isGenerateUUIDSupported() = true

fun isPostgreSQL() = true
fun isOracle() = false
internal fun isPagingSupported() = true
internal fun isQueryTimeoutSupported() = true
internal const val SQLSTATEMENT_SEPARATOR = "; "
internal const val NATIVE_MARKER = ""

// QUERY HELPERS
internal fun getSQLValueEx(trxName: String?, sql: String, params: List<Any?>): Int {
    val loadQuery = queryOf(sql, params).map { row -> row.intOrNull(1) }.asSingle
    return DB.current.run(loadQuery) ?: -1
}

private const val NYI = "Not yet implemented"

fun getSQLValue(trxName: String?, sql: String, vararg params: Any): Int = getSQLValueEx(trxName, sql, listOf(*params))
fun getSQLValueEx(trxName: String?, sql: String, vararg params: Any): Int = getSQLValueEx(trxName, sql, listOf(*params))
fun getSQLValueEx(trxName: String?, sql: String): Int = getSQLValueEx(trxName, sql, listOf())

fun getSQLValueBD(trxName: String, sql: String, vararg params: Any): BigDecimal =
    throw IllegalArgumentException(NYI)

internal fun getSQLValueTS(trxName: String, sql: String, vararg params: Any): Timestamp? =
    throw IllegalArgumentException(NYI)

internal fun getSQLValueTSEx(trxName: String, sql: String, vararg params: Any): Timestamp? =
    throw IllegalArgumentException(NYI)

fun getSQLValueString(trxName: String, sql: String, vararg params: Any): String? =
    throw IllegalArgumentException(NYI)

internal fun getSQLValueStringEx(trxName: String, sql: String, vararg params: Any): String? =
    throw IllegalArgumentException(NYI)

fun queryOf(statement: String, params: List<Any?>): Query {
    return Query(statement, params = params)
}

fun getSQLValueBDEx(trxName: String?, sql: String, params: Array<Any?>): BigDecimal? =
    throw IllegalArgumentException(NYI)

// INSERT/UPDATE
fun executeUpdate(sql: String, trxName: String): Int =
    convert.convert(sql).map { DB.current.run(queryOf(it, listOf()).asUpdate) }.sum()

fun executeUpdate(
    sql: String,
    params: List<Any?>,
    ignoreError: Boolean,
    trxName: String?
): Int = DB.current.run(queryOf(sql, params).asUpdate)

fun executeUpdate(
    sql: String,
    params: Array<Any?>,
    ignoreError: Boolean,
    trxName: String?
): Int = DB.current.run(queryOf(sql, params.toList()).asUpdate)

fun executeUpdateEx(sql: String, trxName: String?): Int =
    executeUpdateEx(sql, arrayOf(), trxName, 0)

fun executeUpdateEx(sql: String, trxName: String?, timeOut: Int): Int =
    executeUpdateEx(sql, arrayOf(), trxName, timeOut)

fun executeUpdateEx(sql: String, objects: Array<Any>, trxName: String?): Int =
    executeUpdateEx(sql, objects, trxName, 0)

fun executeUpdateEx(sql: String, objects: Array<Any>, trxName: String?, timeOut: Int): Int =
    DB.current.run(queryOf(sql, objects.toList()).asUpdate)

fun executeUpdateEx(sql: String, objects: List<Any>, trxName: String?, timeOut: Int): Int =
    DB.current.run(queryOf(sql, objects).asUpdate)

fun executeUpdate(sql: String, param: Int, trxName: String?): Int = executeUpdateEx(sql, listOf(param), trxName, 0)
fun executeUpdate(sql: String, ignoreError: Boolean, trxName: String?): Int {
    return try {
        executeUpdateEx(sql, listOf(), trxName, 0)
    } catch (e: Exception) {
        if (ignoreError) {
            -1
        }
        throw e
    }
}

// STATEMENT
fun prepareStatement(
    sql: String?,
    trxName: String?
): PreparedStatement? = throw IllegalArgumentException(NYI)
fun prepareStatement(
    sql: String?,
    a: Int, b: Int,
    trxName: String?
): PreparedStatement? = throw IllegalArgumentException(NYI)

fun createStatement(): PreparedStatement? = throw IllegalArgumentException(NYI)

internal fun setParameter(pstmt: PreparedStatement, index: Int, param: Any?) {
    if (param == null)
        pstmt.setObject(index, null)
    else if (param is String)
        pstmt.setString(index, param)
    else if (param is Int)
        pstmt.setInt(index, param.toInt())
    else if (param is BigDecimal)
        pstmt.setBigDecimal(index, param)
    else if (param is Timestamp)
        pstmt.setTimestamp(index, param)
    else if (param is Boolean)
        pstmt.setString(index, if (param) "Y" else "N")
    else if (param is ByteArray)
        pstmt.setBytes(index, param)
    else
        throw DBException("Unknown parameter type $index - $param")
}

internal fun setParameters(stmt: PreparedStatement, params: Array<Any>) {
    for (i in 0 until params.size) {
        setParameter(stmt, i + 1, params.get(i))
    }
}

// CONVERTERS
fun TO_DATE(time: Timestamp, dayOnly: Boolean): String = throw IllegalArgumentException(NYI)
fun TO_DATE(time: Timestamp): String = throw IllegalArgumentException(NYI)

fun TO_STRING(txt: String?, maxLength: Int): String = throw IllegalArgumentException(NYI)
fun TO_STRING(txt: String?): String = throw IllegalArgumentException(NYI)
internal val convert: Convert = Convert_PostgreSQL()

// CONNECTION

internal fun getConnectionID(): Connection? = DB.current.connection.underlying
internal fun createConnection(autoCommit: Boolean, readOnly: Boolean, trxLevel: Int): Connection? =
    DB.current.connection.underlying

internal fun createConnection(autoCommit: Boolean, trxLevel: Int): Connection? = DB.current.connection.underlying
internal fun isConnected(createNew: Boolean): Boolean = !DB.current.connection.underlying.isClosed
internal fun isConnected() = isConnected(false)

// DUMMY
fun close(rs: ResultSet?) {}

fun close(st: Statement?) {}
fun close(rs: ResultSet?, st: Statement?) {}

// HELPERS
internal fun addPagingSQL(sql: String, start: Int, end: Int): String {
    return (sql +
            " " +
            NATIVE_MARKER +
            "LIMIT " +
            (end - start + 1) +
            "  " +
            NATIVE_MARKER +
            "OFFSET " +
            (start - 1))
}

internal fun getSQLException(e: Exception): Exception? {
    var e1: Throwable? = e
    while (e1 != null) {
        if (e1 is SQLException) return e1
        e1 = e1.cause
    }
    return e
}

// SEQUENCE

internal fun getNextID(name: String): Int {
    return getSQLValueEx(null, "SELECT nextval('" + name.toLowerCase() + "')")
        ?: throw Exception("Sequence $name not found")
}

internal fun createSequence(
    name: String,
    increment: Int,
    minvalue: Int,
    maxvalue: Int,
    start: Int,
    trxName: String
): Boolean = throw IllegalArgumentException(NYI)

/**
 * Lock PO for update
 *
 * @param po
 * @param timeout
 * @return true if lock is granted
 */
fun forUpdate(po: IPO, timeout: Int): Boolean = throw IllegalArgumentException(NYI)

/**
 * Get Row Set. When a Rowset is closed, it also closes the underlying connection. If the created
 * RowSet is transfered by RMI, closing it makes no difference
 *
 * @param sql sql
 * @param local local RowSet (own connection)
 * @return row set or null
 */
fun getRowSet(sql: String ): RowSet = throw IllegalArgumentException(NYI)

// WRAPPER

class DB private constructor(private val session: Session?) {
    companion object {
        private val ds = HikariCP.dataSource()

        private val context = object : InheritableThreadLocal<Session>() {
            override fun initialValue(): Session {
                return sessionOf(ds)
            }
        }

        val current: Session
            get() {
                val result = context.get() as Session
                if (result.connection.underlying.isClosed) {
                    val newResult = sessionOf(ds)
                    context.set(newResult)
                    return newResult
                }
                return result
            }

        fun dispose() {
            context.remove()
        }
    }
}
