package software.hsharp.core.util

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotliquery.Query
import kotliquery.TransactionalSession
import kotliquery.sessionOf
import kotliquery.using
import org.compiere.dbPort.Convert
import org.compiere.dbPort.Convert_PostgreSQL
import org.compiere.orm.PO
import org.idempiere.common.exceptions.AdempiereException
import org.idempiere.common.exceptions.DBException
import org.idempiere.icommon.model.IPO
import java.math.BigDecimal
import java.sql.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import javax.sql.RowSet

fun <T> String.asResource(work: (String) -> T): T {
    val content = PO::class.java.getResource(this).readText()
    return work(content)
}

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

fun getSQLValueBD(trxName: String?, sql: String, vararg params: Any): BigDecimal =
    throw IllegalArgumentException(NYI)

fun getSQLValueTS(trxName: String, sql: String, vararg params: Any): Timestamp? =
    throw IllegalArgumentException(NYI)

internal fun getSQLValueTSEx(trxName: String, sql: String, vararg params: Any): Timestamp? =
    throw IllegalArgumentException(NYI)

fun getSQLValueString(trxName: String?, sql: String, vararg params: Any): String? =
    throw IllegalArgumentException(NYI)

internal fun getSQLValueStringEx(trxName: String, sql: String, vararg params: Any): String? =
    throw IllegalArgumentException(NYI)

fun queryOf(statement: String, params: List<Any?>): Query {
    return Query(statement, params = params)
}

fun getSQLValueBDEx(trxName: String?, sql: String, params: Array<Any?>): BigDecimal? =
    throw IllegalArgumentException(NYI)

// INSERT/UPDATE
fun executeUpdate(sql: String, trxName: String?): Int =
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
    DB.current.run(queryOf(convert.convertAll(sql), objects.toList()).asUpdate)

fun executeUpdateEx(sql: String, objects: List<Any>, trxName: String?, timeOut: Int): Int =
    DB.current.run(queryOf(convert.convertAll(sql), objects).asUpdate)

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
): PreparedStatement? {
    return DB.current.connection.underlying.prepareStatement(sql)
}
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

/** Quote  */
private const val QUOTE = '\''

fun TO_STRING(txt: String?, maxLength: Int): String {
    if (txt == null || txt.isEmpty()) return "NULL"

    //  Length
    val text =
        if (maxLength != 0 && txt.length > maxLength) txt.substring(0, maxLength) else txt

    //  copy characters		(we need to look through anyway)
    val out = StringBuilder()
    out.append(QUOTE) // 	'
    for (i in 0 until text.length) {
        val c = text[i]
        if (c == QUOTE)
            out.append("''")
        else
            out.append(c)
    }
    out.append(QUOTE) // 	'
    //
    return out.toString()

}
fun TO_STRING(txt: String?): String = TO_STRING(txt, 0)
internal val convert: Convert = Convert_PostgreSQL()

// CONNECTION

internal fun getConnectionID(): Connection? = null
internal fun createConnection(autoCommit: Boolean, readOnly: Boolean, trxLevel: Int): Connection? =
    null

internal fun createConnection(autoCommit: Boolean, trxLevel: Int): Connection? = null
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

object HikariCPI {

    private val pools: ConcurrentMap<String, HikariDataSource> = ConcurrentHashMap()

    fun default(url: String, username: String, password: String): HikariDataSource {
        return init("default", url, username, password)
    }

    fun init(name: String, url: String, username: String, password: String): HikariDataSource {
        val config: HikariConfig = HikariConfig()
        config.jdbcUrl = url
        config.username = username
        config.password = password
        config.isAutoCommit = false
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
        val existing: HikariDataSource? = pools[name]
        if (existing != null && existing.isClosed) {
            existing.close()
        }
        val ds = HikariDataSource(config)
        pools[name] = ds
        return ds
    }

    fun dataSource(name: String = "default"): HikariDataSource {
        val ds: HikariDataSource? = pools[name]
        if (ds != null && !ds.isClosed) {
            return ds
        } else {
            throw IllegalStateException("DataSource ($name) is absent.")
        }
    }

}

// WRAPPER
class DB  {
    companion object {
        private val ds = HikariCPI.dataSource()

        private val context = object : InheritableThreadLocal<TransactionalSession>() {
            override fun initialValue(): TransactionalSession? {
                return null
            }
        }

        fun run(operation: () -> Unit) {
            val session = sessionOf(ds)
            using(session) {
                it.transaction { tx ->
                    context.set(tx)
                    operation()
                }
            }
        }

        val current: TransactionalSession
            get() {
                return context.get() ?: throw AdempiereException("Start transaction on the entry point first")
            }

        fun dispose() {
            context.remove()
        }
    }
}