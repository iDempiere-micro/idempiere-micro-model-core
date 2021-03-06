package org.compiere.orm

import kotliquery.Row
import software.hsharp.core.util.DB
import software.hsharp.core.util.queryOf

fun checkClientSequences(clientId: Int): Boolean {
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
        val seq = MSequence(clientId, tableName)
        seq.save()
    }
    val loadQuery = queryOf(sql, listOf(clientId)).map(processTable).asList
    val result = DB.current.run(loadQuery)
    return result.min() ?: false
}