package software.hsharp.core.orm

import kotliquery.queryOf
import org.compiere.orm.MTable
import software.hsharp.core.util.DB

fun getColumnId(tableName: String, columnName: String): Int {
    val tableId = MTable.getTable_ID(tableName)
    if (tableId == 0) return 0

    val sql = "SELECT AD_Column_ID FROM AD_Column WHERE AD_Table_ID = ?  AND columnname = ?"
    val loadQuery = queryOf(sql, tableId, columnName).map { row -> row.int(1) }.asSingle

    return DB.current.run(loadQuery) ?: -1
}