package software.hsharp.core.orm

import kotliquery.queryOf
import org.compiere.orm.MTable
import org.compiere.orm.PO
import org.compiere.orm.Query
import org.idempiere.common.exceptions.DBException
import org.idempiere.common.util.Env
import software.hsharp.core.util.DB
import java.math.BigDecimal
import java.sql.PreparedStatement
import java.sql.Timestamp
import java.util.*

abstract class BaseQuery(val ctx: Properties, val table: MTable) {
    protected abstract fun buildSQL(selectClause: StringBuilder?, useOrderByClause: Boolean): String

    protected var parameters: Array<Any>? = null
        private set
    protected var onlyActiveRecords = false
        private set
    protected var onlyClient_ID = false
        private set

    private fun convertParameter(param: Any?): Any? {
        if (param is Boolean)
            return if (param) "Y" else "N"
        else return param
    }

    private fun getQueryParameters(): Array<Any?>? {
        val activeRecordsParameter = if (onlyActiveRecords) listOf(true) else listOf()
        val clientIdParameter = if (onlyClient_ID) listOf(Env.getADClientID(ctx)) else listOf()
        val params = parameters ?: arrayOf()
        val result = params
            .toList()
            .plus(activeRecordsParameter)
            .plus(clientIdParameter)
            .map { convertParameter(it) }
            .toTypedArray()
        return if (result.isEmpty()) null else result
    }

    /**
     * Set query parameters
     *
     * @param parameters
     */
    fun setParameters(vararg queryParameters: Any): Query {
        this.parameters = arrayOf(*queryParameters)
        return this as Query
    }

    /**
     * Set query parameters
     *
     * @param parameters collection of parameters
     */
    fun setParameters(queryParameters: List<Any>?): Query {
        if (queryParameters == null) {
            this.parameters = null
            return this as Query
        }
        this.parameters = queryParameters.toTypedArray()
        return this as Query
    }

    /**
     * Select only active records (i.e. IsActive='Y')
     *
     * @param onlyActiveRecords
     */
    fun setOnlyActiveRecords(onlyActiveRecords: Boolean): Query {
        this.onlyActiveRecords = onlyActiveRecords
        return this as Query
    }

    /** Set Client_ID true for WhereClause routine to include AD_Client_ID  */
    fun setClient_ID(): Query {
        return setClient_ID(true)
    }

    /** Set include or not include AD_Client_ID in where clause  */
    fun setClient_ID(isIncludeClient: Boolean): Query {
        this.onlyClient_ID = isIncludeClient
        return this as Query
    }

    private fun <T : PO> doFindFirst(): List<T> {
        val sql = buildSQL(null, true)
        val params = getQueryParameters()
        val sqlQuery = (if (params == null) queryOf(sql) else queryOf(sql, *params)).map { row -> table.getPO(row) as T }.asList
        val result = DB.current.run(sqlQuery)
        return result
    }

    /**
     * Return first PO that match query criteria. If there are more records that match criteria an
     * exception will be throwed
     *
     * @return first PO
     * @throws DBException
     * @see {@link .first
     */
    @Throws(DBException::class)
    fun <T : PO> firstOnly(): T? {
        val result = doFindFirst<T>()
        if (result.count() > 1) throw DBException("QueryMoreThanOneRecordsFound")
        return result.first()
    }

    /**
     * Return first PO that match query criteria
     *
     * @return first PO
     * @throws DBException
     */
    @Throws(DBException::class)
    fun <T : PO> first(): T? {
        val result = doFindFirst<T>()
        if (result.count() > 1) throw DBException("QueryMoreThanOneRecordsFound")
        return result.first()
    }

    /**
     * Return a list of all po that match the query criteria.
     *
     * @return List
     * @throws DBException
     */
    @Throws(DBException::class)
    fun <T : PO> list(): List<T> {
        val sql = buildSQL(null, true)
        val params = getQueryParameters()
        val sqlQuery = (if (params == null) queryOf(sql) else queryOf(sql, *params)).map { row -> table.getPO(row) as T }.asList
        return DB.current.run(sqlQuery)
    }

}