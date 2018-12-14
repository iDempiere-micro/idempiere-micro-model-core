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

open class MBaseSequence : X_AD_Sequence {
    constructor(ctx: Properties, Id: Int, trxName: String) : super(ctx, Id, trxName)
    constructor(ctx: Properties, rs: ResultSet, trxName: String) : super(ctx, rs, trxName)
    constructor(ctx: Properties, row: Row) : super(ctx, row)
}