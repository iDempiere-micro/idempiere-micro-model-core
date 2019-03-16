package software.hsharp.core.orm

import kotliquery.Row
import org.compiere.orm.MSequence
import org.compiere.orm.MSequence.parseVariable
import org.compiere.orm.X_AD_Sequence
import org.compiere.orm.X_AD_Sequence_No
import org.idempiere.common.util.Env
import org.idempiere.common.util.Util
import software.hsharp.core.util.DB
import software.hsharp.core.util.asResource
import software.hsharp.core.util.queryOf
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Properties

/**
 * Get data to Check/Initialize DocumentNo/Value Sequences for all Clients
 */
fun doCheckClientSequences(ctx: Properties, clientId: Int): Boolean {
    return "/sql/checkClientSequences.sql".asResource { sql ->
        val loadQuery =
            queryOf(sql, listOf(clientId))
                .map { row -> MSequence(ctx, clientId, row.string(1)).save() }
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

internal fun doGetNextIDImpl(tableName: String): Int {
    if (tableName.isEmpty())
        throw IllegalArgumentException("TableName missing")

    return "/sql/getNextIDImpl.sql".asResource { sql ->
        val loadQuery = queryOf(sql, listOf(tableName)).map { GetNextIDImplResult(it) }.asSingle
        val seq = DB.current.run(loadQuery)
        if (seq != null) {
            "/sql/updateNextIDImpl.sql".asResource { updateCmd ->
                val updateQuery = queryOf(updateCmd, listOf(seq.incrementNo, seq.sequenceId)).asUpdate
                DB.current.run(updateQuery)
            }
            seq.retValue
        } else -1
    }
}

private const val NoYearNorMonth = "-"

internal fun doGetDocumentNoFromSeq(seq: MSequence, po: PO?): String? {
    val AD_Sequence_ID = seq.sequenceId
    val isStartNewYear = seq.isStartNewYear
    val isStartNewMonth = seq.isStartNewMonth
    val dateColumn = seq.dateColumn
    val isUseOrgLevel = seq.isOrgLevelSequence
    val orgColumn = seq.orgColumn
    val startNo = seq.startNo
    val incrementNo = seq.incrementNo
    val prefix = seq.prefix
    val suffix = seq.suffix
    val decimalPattern = seq.decimalPattern

    val calendarYearMonth =
        if (isStartNewYear) {
            val sdf: SimpleDateFormat =
                if (isStartNewMonth)
                    SimpleDateFormat("yyyyMM")
                else
                    SimpleDateFormat("yyyy")

            if (po != null && dateColumn != null && dateColumn.length > 0) {
                val docDate = po.getValue(dateColumn) as Date
                sdf.format(docDate)
            } else {
                sdf.format(Date())
            }
        } else {
            NoYearNorMonth
        }
    val docOrg_ID =
        if (isUseOrgLevel && po != null && orgColumn != null && orgColumn.length > 0) {
            po.getValueAsInt(orgColumn)
        } else {
            0
        }

    val next1 = DB.current.run(
        if (isStartNewYear || isUseOrgLevel) {
            "/sql/getDocumentNoFromSeqNY.sql".asResource { sql ->
                queryOf(sql, listOf(AD_Sequence_ID, calendarYearMonth, docOrg_ID)).map { it.int(1) }.asSingle
            }
        } else {
            "/sql/getDocumentNoFromSeq.sql".asResource { sql ->
                queryOf(sql, listOf(AD_Sequence_ID)).map { it.int(1) }.asSingle
            }
        }
    )
    val next =
        (if (next1 != null) {
            if (isStartNewYear || isUseOrgLevel) {
                "/sql/updateDocumentNoFromSeqNY.sql".asResource { sql ->
                    DB.current.run(
                        queryOf(
                            sql,
                            listOf(incrementNo, AD_Sequence_ID, calendarYearMonth, docOrg_ID)
                        ).asUpdate
                    )
                }
            } else {
                "/sql/updateDocumentNoFromSeq.sql".asResource { sql ->
                    DB.current.run(queryOf(sql, listOf(incrementNo, AD_Sequence_ID)).asUpdate)
                }
            }
            next1
        } else {
            if (isUseOrgLevel || isStartNewYear) {
                val seqno = X_AD_Sequence_No(Env.getCtx(), 0)
                seqno.setSequenceId(AD_Sequence_ID)
                seqno.setOrgId(docOrg_ID)
                seqno.setCalendarYearMonth(calendarYearMonth)
                seqno.setCurrentNext(startNo + incrementNo)
                seqno.saveEx()

                startNo
            } else {
                null
            }
        }) ?: return null

    // 	create DocumentNo
    val doc = StringBuilder()
    if (prefix != null && prefix.isNotEmpty()) {
        val prefixValue = parseVariable(prefix, po, null, false)
        if (!Util.isEmpty(prefixValue)) doc.append(prefixValue)
    }

    if (decimalPattern != null && decimalPattern.isNotEmpty())
        doc.append(DecimalFormat(decimalPattern).format(next.toLong()))
    else
        doc.append(next)

    if (suffix != null && suffix.isNotEmpty()) {
        val suffixValue = parseVariable(suffix, po, null, false)
        if (!Util.isEmpty(suffixValue)) doc.append(suffixValue)
    }

    return doc.toString()
}

/**
 * Sequence for Table Document No's
 */
const val PREFIX_DOCSEQ = "DocumentNo_"

/**
 * Get Sequence
 *
 * @param ctx context
 * @param tableName table name
 * @param trxName optional transaction name
 * @param tableID
 * @return Sequence
 */
internal fun get(ctx: Properties, tableName: String, tableID: Boolean): MSequence? {
    val realTableName =
        if (!tableID) {
            PREFIX_DOCSEQ + tableName
        } else tableName

    var sql = "SELECT * FROM AD_Sequence WHERE UPPER(Name)=? AND IsTableID=?"
    if (!tableID) sql = "$sql AND AD_Client_ID=?"

    val parameters =
        listOf(realTableName.toUpperCase(), if (tableID) "Y" else "N") +
        (if (!tableID) listOf(Env.getClientId(ctx)) else emptyList())

    val query = queryOf(sql, parameters).map { row -> MSequence(ctx, row) }.asSingle

    return DB.current.run(query)
} // 	get

abstract class MBaseSequence : X_AD_Sequence {
    constructor(ctx: Properties, Id: Int) : super(ctx, Id)
    constructor(ctx: Properties, row: Row) : super(ctx, row)
}