package software.hsharp.core.orm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.Column
import org.compiere.model.Table
import org.compiere.orm.ModelFactory
import software.hsharp.core.util.DB
import kotlin.collections.set
import org.compiere.orm.MTable
import org.compiere.orm.X_AD_Table
import org.compiere.orm.MColumn
import org.idempiere.common.exceptions.AdempiereException
import org.idempiere.common.util.factory
import org.idempiere.common.util.factoryString
import org.idempiere.common.util.loadUsing
import org.idempiere.icommon.model.PersistentObject
import org.idempiere.orm.POInfo
import software.hsharp.core.modules.BaseModule
import software.hsharp.core.util.Environment
import software.hsharp.core.util.asResource

private fun doLoadTable(tableName: String): Table {
    return "/sql/loadTableByName.sql".asResource { sql ->
        val loadQuery = queryOf(sql, tableName.toUpperCase()).map { MTable(it) }.asSingle
        DB.current.run(loadQuery) ?: throw AdempiereException("Table $tableName not found")
    }
}

private val tableFactoryString = factoryString { doLoadTable(it) }
private val tableFactory = factory { MTable(it) }

/**
 * Get Table by Name
 */
fun getTable(tableName: String) = tableName loadUsing tableFactoryString
/**
 * Get Table by Id
 */
fun getTable(id: Int) = id loadUsing tableFactory

internal fun getFactoryList(): Array<ModelFactory>? {
    return arrayOf(Environment<BaseModule>().module.modelFactory)
}

private data class MBaseTableDetail(
    val m_columns: List<MColumn>,
    val m_columnNameMap: MutableMap<String, Int>,
    val m_columnIdMap: MutableMap<Int, Int>
)

abstract class MBaseTable : X_AD_Table, Table {
    constructor(AD_Table_ID: Int) : super(AD_Table_ID)
    constructor(row: Row?) : super(row)

    private fun initDetail(): MBaseTableDetail {
        val sql = "SELECT * FROM AD_Column WHERE AD_Table_ID=? AND IsActive='Y' ORDER BY ColumnName"
        val loadQuery = queryOf(sql, this.id).map { MColumn(it) }.asList
        val r = DB.current.run(loadQuery)
        val columnNameMap: MutableMap<String, Int> = mutableMapOf()
        val columnIdMap: MutableMap<Int, Int> = mutableMapOf()
        r.forEachIndexed { index, column ->
            columnNameMap[column.columnName.toUpperCase()] = index
            columnIdMap[column.columnId] = index
        }
        return MBaseTableDetail(r, columnNameMap, columnIdMap)
    }

    private val detail: MBaseTableDetail = initDetail()

    /** Columns  */
    protected val columns: Array<MColumn> = detail.m_columns.toTypedArray()
    /** column name to index map *  */
    protected val columnNameMap: MutableMap<String, Int> = detail.m_columnNameMap

    @Synchronized
    override fun getColumns(requery: Boolean): Array<Column> {
        if (columns.isNotEmpty() && !requery) return columns.map { it as Column }.toTypedArray()
        return initDetail().m_columns.toTypedArray()
    } // 	getColumns

    override fun <T : PersistentObject> getPO(row: Row): T? {
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
    fun <T : PersistentObject> getPO(whereClause: String?, params: Array<Any?>?): T? {
        if (whereClause == null || whereClause.isEmpty()) return null

        val info = POInfo.getPOInfo(tableTableId) ?: return null
        val sqlBuffer = info.buildSelect()
        sqlBuffer.append(" WHERE ").append(whereClause)
        val sql = sqlBuffer.toString()

        val sqlQuery =
            (if (params == null) queryOf(sql) else software.hsharp.core.util.queryOf(
                sql,
                params.toList()
            )).map { row -> getPO<T>(row) }.asSingle
        return DB.current.run(sqlQuery)
    }
}