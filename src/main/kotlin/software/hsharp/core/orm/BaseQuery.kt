package software.hsharp.core.orm

import kotliquery.queryOf
import org.compiere.orm.MTable
import org.compiere.orm.PO
import org.compiere.orm.Query
import org.idempiere.common.exceptions.DBException
import org.idempiere.icommon.model.IPO
import software.hsharp.core.util.DB
import software.hsharp.core.util.Environment
import software.hsharp.core.util.convert

abstract class BaseQuery(val table: MTable) {
    protected abstract fun buildSQL(selectClause: StringBuilder?, useOrderByClause: Boolean): String

    protected var parameters: Array<Any>? = null
        private set
    protected var onlyActiveRecords = false
        private set
    protected var onlyClientId = false
        private set

    private fun convertParameter(param: Any?): Any? {
        if (param is Boolean)
            return if (param) "Y" else "N"
        else return param
    }

    private fun getQueryParameters(): Array<Any?>? {
        val activeRecordsParameter = if (onlyActiveRecords) listOf(true) else listOf()
        val clientIdParameter = if (onlyClientId) listOf(Environment.current.clientId) else listOf()
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

    /** Set Client_ID true for WhereClause routine to include clientId  */
    fun setClientId(): Query {
        return setClientId(true)
    }

    /** Set include or not include clientId in where clause  */
    fun setClientId(isIncludeClient: Boolean): Query {
        this.onlyClientId = isIncludeClient
        return this as Query
    }

    private fun <T : PO> doFindFirst(): List<T> {
        val sql = buildSQL(null, true)
        val params = getQueryParameters()
        val sqlQuery =
            (if (params == null) queryOf(sql) else queryOf(sql, *params)).map { row ->
                @Suppress("UNCHECKED_CAST")
                table.getPO(row) as T
            }.asList
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
        return result.firstOrNull()
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
        return result.firstOrNull()
    }

    /**
     * Return a list of all po that match the query criteria.
     *
     * @return List
     * @throws DBException
     */
    @Throws(DBException::class)
    fun <T : IPO> list(): List<T> {
        val sql = convert.convertAll(buildSQL(null, true))
        val params = getQueryParameters()
        val sqlQuery =
            @Suppress("UNCHECKED_CAST")
            (if (params == null) queryOf(sql) else software.hsharp.core.util.queryOf(
                sql,
                params.toList()
            )).map { row -> table.getPO(row) as T }.asList
        return DB.current.run(sqlQuery)
    }
}