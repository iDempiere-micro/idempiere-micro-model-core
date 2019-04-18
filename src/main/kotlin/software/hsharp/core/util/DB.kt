package software.hsharp.core.util

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotliquery.Query
import kotliquery.TransactionalSession
import kotliquery.sessionOf
import kotliquery.using
import org.compiere.dbPort.Convert
import org.compiere.dbPort.ConvertPostgreSQL
import org.compiere.orm.PO
import org.idempiere.common.exceptions.AdempiereException
import org.idempiere.common.exceptions.DBException
import org.idempiere.icommon.model.PersistentObject
import java.math.BigDecimal
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Timestamp
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
internal const val NATIVE_MARKER = ""

// QUERY HELPERS
internal fun getSQLValueEx(sql: String, params: List<Any?>): Int {
    val loadQuery = queryOf(sql, params).map { row -> row.intOrNull(1) }.asSingle
    return DB.current.run(loadQuery) ?: -1
}

const val NYI = "Not yet implemented"

fun getSQLValue(sql: String, vararg params: Any): Int = getSQLValueEx(sql, listOf(*params))
fun getSQLValueEx(sql: String, vararg params: Any): Int = getSQLValueEx(sql, listOf(*params))
fun getSQLValueEx(sql: String): Int = getSQLValueEx(sql, listOf())

internal fun getSQLValueBDEx(sql: String, params: List<Any?>): BigDecimal? {
    val loadQuery = queryOf(sql, params).map { row -> row.bigDecimalOrNull(1) }.asSingle
    return DB.current.run(loadQuery)
}

fun getSQLValueBD(sql: String, vararg params: Any): BigDecimal? = getSQLValueBDEx(sql, listOf(*params))

fun getSQLValueTS(sql: String, vararg params: Any): Timestamp? {
    val loadQuery = queryOf(sql, listOf(*params)).map { row -> row.sqlTimestampOrNull(1) }.asSingle
    return DB.current.run(loadQuery)
}

fun getSQLValueString(sql: String, vararg params: Any): String? {
    val loadQuery = queryOf(sql, listOf(*params)).map { row -> row.stringOrNull(1) }.asSingle
    return DB.current.run(loadQuery)
}

fun queryOf(statement: String, params: List<Any?>): Query {
    return Query(statement, params = params)
}

fun getSQLValueBDEx(sql: String, params: Array<Any?>): BigDecimal? =
    getSQLValueBD(sql, params)

// INSERT/UPDATE
fun executeUpdate(sql: String): Int =
    convert.convert(sql).map { DB.current.run(queryOf(it, listOf()).asUpdate) }.sum()

fun executeUpdate(
    sql: String,
    params: List<Any?>
): Int = DB.current.run(queryOf(sql, params).asUpdate)

fun executeUpdate(
    sql: String,
    params: Array<Any?>
): Int = DB.current.run(queryOf(sql, params.toList()).asUpdate)

fun executeUpdateEx(sql: String): Int =
    executeUpdateEx(sql, arrayOf())

fun executeUpdateEx(sql: String, objects: Array<Any>): Int =
    DB.current.run(queryOf(convert.convertAll(sql), objects.toList().map { param -> convertParameter(param) }).asUpdate)

fun executeUpdateEx(sql: String, objects: List<Any>): Int =
    DB.current.run(queryOf(convert.convertAll(sql), objects.map { param -> convertParameter(param) }).asUpdate)

fun executeUpdate(sql: String, param: Int): Int = executeUpdateEx(sql, listOf(param))
fun executeUpdate(sql: String, ignoreError: Boolean): Int {
    return try {
        executeUpdateEx(sql, listOf())
    } catch (e: Exception) {
        if (ignoreError) {
            -1
        }
        throw e
    }
}

// STATEMENT
fun prepareStatement(
    sql: String?
): PreparedStatement? {
    return DB.current.connection.underlying.prepareStatement(sql)
}

internal fun convertParameter(param: Any?): Any? {
    if (param == null)
        return null
    else if (param is String)
        return param
    else if (param is Int)
        return param.toInt()
    else if (param is BigDecimal)
        return param
    else if (param is Timestamp)
        return param
    else if (param is Boolean)
        return if (param) "Y" else "N"
    else if (param is ByteArray)
        return param
    else
        throw DBException("Unknown parameter type $param")
}

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

/**
 * Backwards compatible way to set parameters to a PreparedStatement.
 */
@Deprecated("Use the new queryOf instead")
fun setParameters(stmt: PreparedStatement, params: Array<Any>) {
    for (i in 0 until params.size) {
        setParameter(stmt, i + 1, params.get(i))
    }
}

// CONVERTERS
fun convertDate(time: Timestamp?, dayOnly: Boolean): String {
    if (time == null) {
        return if (dayOnly) "current_date()" else "current_date()"
    }

    val dateString = StringBuilder("TO_DATE('")
    //  YYYY-MM-DD HH24:MI:SS.mmmm  JDBC Timestamp format
    val myDate = time.toString()
    if (dayOnly) {
        dateString.append(myDate.substring(0, 10))
        dateString.append("','YYYY-MM-DD')")
    } else {
        dateString.append(myDate.substring(0, myDate.indexOf('.'))) // 	cut off miliseconds
        dateString.append("','YYYY-MM-DD HH24:MI:SS')")
    }
    return dateString.toString()
}

fun convertDate(time: Timestamp?): String = convertDate(time, true)

/** Quote  */
private const val QUOTE = '\''

fun convertString(txt: String?, maxLength: Int): String {
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

fun convertString(txt: String?): String = convertString(txt, 0)
internal val convert: Convert = ConvertPostgreSQL()

// CONNECTION

internal fun isConnected(): Boolean = !DB.current.connection.underlying.isClosed

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
    return getSQLValueEx("SELECT nextval('" + name.toLowerCase() + "')")
}

/**
 * Lock PO for update
 *
 * @param po
 * @param timeout
 * @return true if lock is granted
 */
fun forUpdate(po: PersistentObject): Boolean {
    val keyColumns = po.keyColumns
    val sqlBuffer = StringBuilder(" SELECT ")
    sqlBuffer.append(keyColumns[0]).append(" FROM ").append(po.tableName).append(" WHERE ")
    for (i in keyColumns.indices) {
        if (i > 0) sqlBuffer.append(" AND ")
        sqlBuffer.append(keyColumns[i]).append("=?")
    }
    sqlBuffer.append(" FOR UPDATE ")

    val parameters = arrayOfNulls<Any>(keyColumns.size)
    for (i in keyColumns.indices) {
        var parameter: Any? = po.getValue(keyColumns[i])
        if (parameter != null && parameter is Boolean) {
            if ((parameter as Boolean?)!!)
                parameter = "Y"
            else
                parameter = "N"
        }
        parameters[i] = parameter
    }

    val loadQuery = queryOf(sqlBuffer.toString(), parameters.toList()).map { 1 }.asSingle
    val result = DB.current.run(loadQuery)

    return result != null
}

/**
 * Get Row Set. When a Rowset is closed, it also closes the underlying connection. If the created
 * RowSet is transfered by RMI, closing it makes no difference
 *
 * @param sql sql
 * @param local local RowSet (own connection)
 * @return row set or null
 */
fun getRowSet(): RowSet = throw IllegalArgumentException(NYI)

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
class DB {
    companion object {
        private val ds = HikariCPI.dataSource()

        private val context = object : InheritableThreadLocal<TransactionalSession>() {
            override fun initialValue(): TransactionalSession? {
                return null
            }
        }

        /**
         * You need to start the transaction by wrapping the code that need it into the [operation] first.
         * Someone else whenever inside the code then can call [current] easily to call a query from [queryOf].
         */
        fun <T> run(operation: () -> T): T {
            val session = sessionOf(ds)
            return using(session) {
                it.transaction { tx ->
                    context.set(tx)
                    operation()
                }
            }
        }

        /**
         * You want to use this in the code when you need to execute a query from [queryOf].
         * Someone else on the top level e.g. when processing a GraphQL request had to start the transaction first.
         */
        val current: TransactionalSession
            get() {
                return context.get() ?: throw AdempiereException("Start transaction on the entry point first")
            }

        fun dispose() {
            context.remove()
        }
    }
}
