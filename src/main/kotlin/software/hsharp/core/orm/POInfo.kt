package software.hsharp.core.orm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.I_AD_Table
import org.idempiere.common.util.Env
import org.idempiere.orm.POInfoColumn
import software.hsharp.core.util.DB
import java.util.*

data class POInfoDetail(
    val tableName: String,
    val hasKeyColumn: Boolean,
    val accessLevel: String,
    val isChangeLog: Boolean,
    val columnNameMap: MutableMap<String, Int>,
    val columnIdMap: MutableMap<Int, Int>
)

open class POInfo(val ctx: Properties, val tableId: Int, val baseLanguageOnly: Boolean) {
    private val detail: Pair<POInfoDetail, Array<POInfoColumn>> = loadInfo(
        if (baseLanguageOnly) true else Env.isBaseLanguage(
            ctx,
            "AD_Table"
        )
    )

    protected val columnNameMap: MutableMap<String, Int> = detail.first.columnNameMap
    protected val columnIdMap: MutableMap<Int, Int> = detail.first.columnIdMap
    /** Table Name  */
    val tableName: String = detail.first.tableName
    val hasKeyColumn = detail.first.hasKeyColumn
    val accessLevel = detail.first.accessLevel
    val isChangeLog = detail.first.isChangeLog
    protected val columns: Array<POInfoColumn> = detail.second

    protected fun loadInfo(baseLanguage: Boolean): Pair<POInfoDetail, Array<POInfoColumn>> {
        val columnNameMap: MutableMap<String, Int> = mutableMapOf()
        val columnIdMap: MutableMap<Int, Int> = mutableMapOf()
        val toPOInfoColumn: (Row) -> Pair<POInfoDetail, POInfoColumn> = { row ->
            val tableName = row.string(1)
            val ColumnName = row.stringOrNull(2)
            val AD_Reference_ID = row.int(3)
            val IsMandatory = "Y" == row.stringOrNull(4)
            val IsUpdateable = "Y" == row.stringOrNull(5)
            val DefaultLogic = row.stringOrNull(6)
            val Name = row.stringOrNull(7)
            val Description = row.stringOrNull(8)
            val AD_Column_ID = row.int(9)
            val IsKey = "Y" == row.stringOrNull(10)
            val hasKeyColumn = IsKey
            val IsParent = "Y" == row.stringOrNull(11)
            val AD_Reference_Value_ID = row.intOrNull(12) ?: 0
            val ValidationCode = row.stringOrNull(13)
            val FieldLength = row.int(14)
            val ValueMin = row.stringOrNull(15)
            val ValueMax = row.stringOrNull(16)
            val IsTranslated = "Y" == row.stringOrNull(17)
            //
            val accessLevel = row.stringOrNull(18)
            var ColumnSQL: String? = row.stringOrNull(19)
            if (ColumnSQL != null && ColumnSQL.contains("@"))
                ColumnSQL = Env.parseContext(Env.getCtx(), -1, ColumnSQL, false, true)
            val IsEncrypted = "Y" == row.stringOrNull(20)
            val IsAllowLogging = "Y" == row.stringOrNull(21)
            val IsAllowCopy = "Y" == row.stringOrNull(22)
            val isChangeLog = "Y" == row.stringOrNull(23)

            Pair(
                POInfoDetail(
                    tableName, hasKeyColumn, accessLevel ?: I_AD_Table.ACCESSLEVEL_Organization,
                    isChangeLog, mutableMapOf(), mutableMapOf()
                ),
                POInfoColumn(
                    AD_Column_ID,
                    ColumnName,
                    ColumnSQL,
                    AD_Reference_ID,
                    IsMandatory,
                    IsUpdateable,
                    DefaultLogic,
                    Name,
                    Description,
                    IsKey,
                    IsParent,
                    AD_Reference_Value_ID,
                    ValidationCode,
                    FieldLength,
                    ValueMin,
                    ValueMax,
                    IsTranslated,
                    IsEncrypted,
                    IsAllowLogging,
                    IsAllowCopy
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
        return Pair(
            result.first().first.copy(
                columnNameMap = columnNameMap,
                columnIdMap = columnIdMap,
                hasKeyColumn = hasKeyColumn
            ), result.map { it.second }.toTypedArray()
        )
    }
}