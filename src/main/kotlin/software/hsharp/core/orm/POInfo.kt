package software.hsharp.core.orm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.Table
import org.compiere.util.DisplayType
import org.idempiere.common.exceptions.AdempiereException
import org.idempiere.common.util.Env
import org.idempiere.orm.POInfoColumn
import software.hsharp.core.util.DB

data class POInfoDetail(
    val tableName: String,
    val hasKeyColumn: Boolean,
    val accessLevel: String,
    val isChangeLog: Boolean,
    val columnNameMap: MutableMap<String, Int>,
    val columnIdMap: MutableMap<Int, Int>
)

open class POInfo(val tableId: Int, baseLanguageOnly: Boolean) {
    private val detail: Pair<POInfoDetail, Array<POInfoColumn>> = loadInfo(
        if (baseLanguageOnly) true else Env.isBaseLanguage()
    )

    protected val columnNameMap: MutableMap<String, Int> = detail.first.columnNameMap
    protected val columnIdMap: MutableMap<Int, Int> = detail.first.columnIdMap
    /** Table Name  */
    val tableName: String = detail.first.tableName
    val hasKeyColumn = detail.first.hasKeyColumn
    val accessLevel = detail.first.accessLevel
    protected val columns: Array<POInfoColumn> = detail.second
    val columnCount = columns.size

    protected fun loadInfo(baseLanguage: Boolean): Pair<POInfoDetail, Array<POInfoColumn>> {
        val columnNameMap: MutableMap<String, Int> = mutableMapOf()
        val columnIdMap: MutableMap<Int, Int> = mutableMapOf()
        val toPOInfoColumn: (Row) -> Pair<POInfoDetail, POInfoColumn> = { row ->
            val tableName = row.string(1)
            val ColumnName = row.stringOrNull(2)
            val AD_Reference_ID = row.int(3)
            val IsMandatory = "Y" == row.stringOrNull(4)
            val IsUpdateable = "Y" == row.stringOrNull(5)
            val Name = row.stringOrNull(7)
            val Description = row.stringOrNull(8)
            val AD_Column_ID = row.int(9)
            val IsKey = "Y" == row.stringOrNull(10)
            val hasKeyColumn = IsKey
            val IsParent = "Y" == row.stringOrNull(11)
            val FieldLength = row.int(14)
            val ValueMin = row.stringOrNull(15)
            val ValueMax = row.stringOrNull(16)
            val IsTranslated = "Y" == row.stringOrNull(17)
            //
            val accessLevel = row.stringOrNull(18)
            var ColumnSQL: String? = row.stringOrNull(19)
            if (ColumnSQL != null && ColumnSQL.contains("@"))
                ColumnSQL = Env.parseContext(-1, ColumnSQL, false, true)
            val IsEncrypted = "Y" == row.stringOrNull(20)
            val isChangeLog = "Y" == row.stringOrNull(23)

            Pair(
                POInfoDetail(
                    tableName, hasKeyColumn, accessLevel ?: Table.ACCESSLEVEL_Organization,
                    isChangeLog, mutableMapOf(), mutableMapOf()
                ),
                POInfoColumn(
                    AD_Column_ID,
                    ColumnName,
                    ColumnSQL,
                    AD_Reference_ID,
                    IsMandatory,
                    IsUpdateable,
                    Name,
                    Description,
                    IsKey,
                    IsParent,
                    FieldLength,
                    ValueMin,
                    ValueMax,
                    IsTranslated,
                    IsEncrypted
                )
            )
        }

        val sql = """
            SELECT t.TableName, c.ColumnName,c.AD_Reference_ID,
            c.IsMandatory,c.IsUpdateable,c.DefaultValue,
            e.Name,e.Description, c.AD_Column_ID,
            c.IsKey,c.IsParent,
            c.AD_Reference_Value_ID, vr.Code,
            c.FieldLength, c.ValueMin, c.ValueMax, c.IsTranslated,
            t.AccessLevel, c.ColumnSQL, c.IsEncrypted,
            c.IsAllowLogging,c.IsAllowCopy,t.IsChangeLog
            FROM AD_Table t
            INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID)
            LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)
            INNER JOIN AD_Element${if (!baseLanguage) "_Trl" else ""} e
            ON (c.AD_Element_ID=e.AD_Element_ID)
            WHERE t.AD_Table_ID=?
            AND c.IsActive='Y'
        """.trimIndent()
        val allColumnsQuery = queryOf(sql, tableId).map(toPOInfoColumn).asList
        val result = DB.current.run(allColumnsQuery).toTypedArray()
        result.map { it.second }.forEachIndexed { index, column ->
            columnNameMap[column.ColumnName.toUpperCase()] = index; columnIdMap[column.AD_Column_ID] = index
        }
        val hasKeyColumn = result.filter { it.first.hasKeyColumn }.isNotEmpty()
        if (result.isEmpty()) throw AdempiereException("No info, check that you have correct TableId $tableId")
        return Pair(
            result.first().first.copy(
                columnNameMap = columnNameMap,
                columnIdMap = columnIdMap,
                hasKeyColumn = hasKeyColumn
            ), result.map { it.second }.toTypedArray()
        )
    }

    /**
     * Get Column Name
     *
     * @param index index
     * @return ColumnName column name
     */
    fun getColumnName(index: Int): String? {
        return if (index < 0 || index >= columns.size) null else columns[index].ColumnName
    } //  getColumnName

    /**
     * @param columnName
     * @return AD_Column_ID if found, -1 if not found
     */
    fun getColumnId(columnName: String?): Int {
        if (columnName == null) return -1

        for (i in 0 until columns.size) {
            if (columnName.equals(
                    columns[i].ColumnName, ignoreCase = true
                )
            )
            // globalqss : modified to compare ignoring case [ 1619179 ]
                return columns[i].AD_Column_ID
        }
        return -1
    }

    /**
     * Is Column (data) Encrypted
     *
     * @param index index
     * @return true if column is encrypted
     */
    fun isEncrypted(index: Int): Boolean {
        return if (index < 0 || index >= columns.size) false else columns[index].IsEncrypted
    } //  isEncrypted

    /**
     * Get Column SQL or Column Name
     *
     * @param index index
     * @return ColumnSQL column sql or name
     */
    fun getColumnSQL(index: Int): String? {
        if (index < 0 || index >= columns.size) return null
        return if (columns[index].ColumnSQL != null && columns[index].ColumnSQL.length > 0) columns[index].ColumnSQL + " AS " + columns[index].ColumnName else columns[index].ColumnName
    } //  getColumnSQL

    /**
     * Get Column Class
     *
     * @param index index
     * @return Class
     */
    fun getColumnClass(index: Int): Class<*>? {
        return if (index < 0 || index >= columns.size) null else columns[index].ColumnClass
    } //  getColumnClass

    /**
     * Get Column Display Type
     *
     * @param index index
     * @return DisplayType
     */
    fun getColumnDisplayType(index: Int): Int {
        return if (index < 0 || index >= columns.size) DisplayType.String else columns[index].DisplayType
    } //  getColumnDisplayType

    /**
     * Is Column Key
     *
     * @param index index
     * @return true if column is the key
     */
    fun isKey(index: Int): Boolean {
        val columns = columns
        return if (index < 0 || index >= columns.size) false else columns[index].IsKey
    } //  isKey

    /**
     * Is Column Parent
     *
     * @param index index
     * @return true if column is a Parent
     */
    fun isColumnParent(index: Int): Boolean {
        return if (index < 0 || index >= columns.size) false else columns[index].IsParent
    } //  isColumnParent
}