package software.hsharp.core.orm

import kotliquery.queryOf
import org.compiere.model.Table
import org.compiere.model.TypedQuery
import org.compiere.orm.Query
import org.idempiere.common.exceptions.DBException
import org.idempiere.icommon.model.PersistentObject
import software.hsharp.core.util.DB
import software.hsharp.core.util.Environment
import software.hsharp.core.util.convert

abstract class BaseQuery<T:PersistentObject>(val table: Table) : TypedQuery<T> {
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
    override fun setParameters(vararg queryParameters: Any): TypedQuery<T> {
        this.parameters = arrayOf(*queryParameters)
        return this
    }

    /**
     * Set query parameters
     *
     * @param parameters collection of parameters
     */
    fun setParameters(queryParameters: List<Any>?): Query<T> {
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
    override fun setOnlyActiveRecords(onlyActiveRecords: Boolean): TypedQuery<T> {
        this.onlyActiveRecords = onlyActiveRecords
        return this
    }

    /** Set Client_ID true for WhereClause routine to include clientId  */
    override fun setClientId(): Query<T> {
        return setClientId(true)
    }

    /** Set include or not include clientId in where clause  */
    fun setClientId(isIncludeClient: Boolean): Query<T> {
        this.onlyClientId = isIncludeClient
        return this as Query
    }

    private fun <T : PersistentObject> doFindFirst(): List<T> {
        val sql = buildSQL(null, true)
        val params = getQueryParameters()
        val sqlQuery =
            (if (params == null) queryOf(sql) else queryOf(sql, *params)).map { row ->
                table.getPO<T>(row)
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
    override fun firstOnly(): T? {
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
    override fun first(): T? {
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
    override fun list(): List<T> {
        val sql = convert.convertAll(buildSQL(null, true))
        val params = getQueryParameters()
        val sqlQuery =
            (if (params == null) queryOf(sql) else software.hsharp.core.util.queryOf(
                sql,
                params.toList()
            )).map { row -> table.getPO<T>(row) }.asList
        return DB.current.run(sqlQuery)
    }
}