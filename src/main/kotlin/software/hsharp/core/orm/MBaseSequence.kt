package software.hsharp.core.orm

import kotliquery.Row
import org.compiere.orm.MSequence
import org.compiere.orm.X_AD_Sequence
import software.hsharp.core.util.DB
import software.hsharp.core.util.asResource
import software.hsharp.core.util.queryOf
import java.sql.ResultSet
import java.util.*

fun doCheckClientSequences(ctx: Properties, clientId: Int): Boolean {
    return "/sql/checkClientSequences.sql".asResource { sql ->
        val loadQuery =
            queryOf(sql, listOf(clientId))
            .map { row -> MSequence(ctx, clientId, row.string(1), null).save() }
            .asList
        DB.current.run(loadQuery).min() ?: false
    }
}

private data class GetNextIDImplResult(
    val sequenceId: Int, // 4
    val incrementNo: Int, // 3
    val retValue: Int // 1
) {
    constructor(row: Row) : this(
        row.int(4), row.int(3), row.int(1)
    )
}

fun doGetNextIDImpl(clientId: Int, tableName: String) : Int {
    if (tableName.isEmpty())
        throw IllegalArgumentException("TableName missing")

    return "/sql/getNextIDImpl.sql".asResource {
        sql ->
            val loadQuery = queryOf(sql, listOf(tableName)).map { GetNextIDImplResult(it) }.asSingle
            val seq = DB.current.run(loadQuery)
            if (seq != null ) {
                "/sql/updateNextIDImpl.sql".asResource { updateCmd ->
                    val updateQuery = queryOf(updateCmd, listOf(seq.incrementNo, seq.sequenceId)).asUpdate
                    DB.current.run(updateQuery)
                }
                seq.retValue
            } else -1
    }
}

open class MBaseSequence : X_AD_Sequence {
    constructor(ctx: Properties, Id: Int, trxName: String?) : super(ctx, Id, trxName)
    constructor(ctx: Properties, rs: ResultSet, trxName: String) : super(ctx, rs, trxName)
    constructor(ctx: Properties, row: Row) : super(ctx, row)
}