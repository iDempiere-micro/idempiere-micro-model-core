package software.hsharp.core.orm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.I_AD_Table
import org.compiere.orm.DefaultModelFactory
import org.compiere.orm.IModelFactory
import org.idempiere.common.util.CCache
import software.hsharp.core.util.DB
import java.util.Properties
import kotlin.collections.set
import org.compiere.orm.MTable
import org.compiere.orm.X_AD_Table
import org.compiere.orm.MColumn
import org.compiere.orm.GenericPO
import org.idempiere.orm.POInfo

internal val tableCache = CCache<Int, MTable>(I_AD_Table.Table_Name, 20)

/**
 * Get Table from Cache
 *
 * @param ctx context
 * @param tableName case insensitive table name
 * @return Table
 */
internal fun get(ctx: Properties, tableName: String?): MTable? {
    if (tableName == null) return null
    for (retValue in tableCache.values) {
        if (tableName.equals(retValue.dbTableName, ignoreCase = true) && retValue.ctx === ctx) {
            return retValue
        }
    }
    //
    val sql = "SELECT * FROM AD_Table WHERE UPPER(TableName)=?"
    val loadQuery = queryOf(sql, tableName.toUpperCase()).map { MTable(ctx, it) }.asSingle
    val retValue = DB.current.run(loadQuery)

    if (retValue != null) {
        val key = retValue.tableTableId
        tableCache.put(key, retValue)
    }
    return retValue
} // 	get

internal fun getFactoryList(): Array<IModelFactory>? {
    return arrayOf(DefaultModelFactory())
}

private data class MBaseTableDetail(
    val m_columns: Array<MColumn>,
    val m_columnNameMap: MutableMap<String, Int>,
    val m_columnIdMap: MutableMap<Int, Int>
)

abstract class MBaseTable : X_AD_Table {
    constructor(ctx: Properties, AD_Table_ID: Int) : super(ctx, AD_Table_ID)
    constructor(ctx: Properties, row: Row?) : super(ctx, row)

    private fun initDetail(): MBaseTableDetail {
        val sql = "SELECT * FROM AD_Column WHERE AD_Table_ID=? AND IsActive='Y' ORDER BY ColumnName"
        val loadQuery = queryOf(sql, this.id).map { MColumn(ctx, it) }.asList
        val r = DB.current.run(loadQuery)
        val columnNameMap: MutableMap<String, Int> = mutableMapOf()
        val columnIdMap: MutableMap<Int, Int> = mutableMapOf()
        r.forEachIndexed { index, column ->
            columnNameMap[column.columnName.toUpperCase()] = index
            columnIdMap[column.columnId] = index
        }
        val columns = r.toTypedArray()
        return MBaseTableDetail(columns, columnNameMap, columnIdMap)
    }

    private val detail: MBaseTableDetail = initDetail()

    /** Columns  */
    protected val columns: Array<MColumn> = detail.m_columns
    /** column name to index map *  */
    protected val columnNameMap: MutableMap<String, Int> = detail.m_columnNameMap
    /** ad_column_id to index map *  */
    protected val columnIdMap: MutableMap<Int, Int> = detail.m_columnIdMap

    @Synchronized
    fun getColumns(requery: Boolean): Array<MColumn> {
        if (columns.isNotEmpty() && !requery) return columns
        return initDetail().m_columns
    } // 	getColumns

    fun getPO(row: Row): org.idempiere.orm.PO {
        val tableName = dbTableName

        var po: org.idempiere.orm.PO? = null
        val factoryList = getFactoryList()
        if (factoryList != null) {
            for (factory in factoryList) {
                po = factory.getPO(tableName, row)
                if (po != null) break
            }
        }

        if (po == null) {
            po = GenericPO(tableName, ctx, row)
        }

        return po
    } // 	getPO

    fun <T> getPO(row: Row): T? {
        val tableName = dbTableName

        val factoryList = getFactoryList()
        return factoryList?.map { it.getPO<T>(tableName, row) }?.first()
    } // 	getPO

    /**
     * Get PO class instance
     *
     * @param whereClause
     * @param params
     * @return
     */
    fun getPO(whereClause: String?, params: Array<Any?>?): org.idempiere.orm.PO? {
        if (whereClause == null || whereClause.isEmpty()) return null

        val info = POInfo.getPOInfo(ctx, tableTableId) ?: return null
        val sqlBuffer = info.buildSelect()
        sqlBuffer.append(" WHERE ").append(whereClause)
        val sql = sqlBuffer.toString()

        val sqlQuery =
            @Suppress("UNCHECKED_CAST")
            (if (params == null) queryOf(sql) else software.hsharp.core.util.queryOf(
                sql,
                params.toList()
            )).map { row -> getPO(row) }.asSingle
        return DB.current.run(sqlQuery)
    }
}