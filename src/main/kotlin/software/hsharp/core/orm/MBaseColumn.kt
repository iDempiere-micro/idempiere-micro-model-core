package software.hsharp.core.orm

import kotliquery.queryOf
import org.compiere.orm.MTable
import org.compiere.orm.getTableId
import software.hsharp.core.util.DB
import software.hsharp.core.util.asResource

/**
 * Get the column Id from a [tableName] table by [columnName]
 */
fun getColumnId(tableName: String, columnName: String): Int {
    val tableId = getTableId(tableName)
    if (tableId == 0) return 0

    return "/sql/getColumnId.sql".asResource { sql ->
        val loadQuery = queryOf(sql, tableId, columnName).map { row -> row.int(1) }.asSingle

        DB.current.run(loadQuery) ?: -1
    }
}