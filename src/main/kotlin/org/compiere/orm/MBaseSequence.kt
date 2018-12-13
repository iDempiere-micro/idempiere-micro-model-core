package org.compiere.orm

import kotliquery.Row
import software.hsharp.core.util.DB
import software.hsharp.core.util.queryOf
import java.util.*

fun checkClientSequences(ctx: Properties, clientId: Int): Boolean {
    val sql = """
        SELECT TableName
        FROM AD_Table t
        WHERE IsActive='Y' AND IsView='N'
        AND AD_Table_ID IN
        (SELECT AD_Table_ID FROM AD_Column
        WHERE ColumnName = 'DocumentNo' OR ColumnName = 'Value')
        AND 'DocumentNo_' || TableName NOT IN
        (SELECT Name FROM AD_Sequence s
        WHERE s.AD_Client_ID=?)
    """.trimIndent()
    val processTable: (row: Row) -> Boolean = {
        val tableName = it.string(1)
        val seq = MSequence(ctx, clientId, tableName, null)
        seq.save()
    }
    val loadQuery = queryOf(sql, listOf()).map(processTable).asList
    return DB.current.run(loadQuery).min() ?: false
}