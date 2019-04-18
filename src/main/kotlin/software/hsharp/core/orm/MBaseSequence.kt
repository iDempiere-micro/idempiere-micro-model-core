package software.hsharp.core.orm

import kotliquery.Row
import org.compiere.orm.MSequence
import org.compiere.orm.MSequence.parseVariable
import org.compiere.orm.X_AD_Sequence
import org.compiere.orm.X_AD_Sequence_No
import org.idempiere.common.util.Util
import software.hsharp.core.util.DB
import software.hsharp.core.util.Environment
import software.hsharp.core.util.asResource
import software.hsharp.core.util.queryOf
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Get data to Check/Initialize DocumentNo/Value Sequences for all Clients
 */
fun doCheckClientSequences(clientId: Int): Boolean {
    return "/sql/checkClientSequences.sql".asResource { sql ->
        val loadQuery =
            queryOf(sql, listOf(clientId))
                .map { row -> MSequence(clientId, row.string(1)).save() }
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
    val sequenceId = seq.sequenceId
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

            if (po != null && dateColumn != null && dateColumn.isNotEmpty()) {
                val docDate : Date? = po.getValue(dateColumn)
                sdf.format(docDate)
            } else {
                sdf.format(Date())
            }
        } else {
            NoYearNorMonth
        }
    val docOrgId =
        if (isUseOrgLevel && po != null && orgColumn != null && orgColumn.isNotEmpty()) {
            po.getValueAsInt(orgColumn)
        } else {
            0
        }

    val next1 = DB.current.run(
        if (isStartNewYear || isUseOrgLevel) {
            "/sql/getDocumentNoFromSeqNY.sql".asResource { sql ->
                queryOf(sql, listOf(sequenceId, calendarYearMonth, docOrgId)).map { it.int(1) }.asSingle
            }
        } else {
            "/sql/getDocumentNoFromSeq.sql".asResource { sql ->
                queryOf(sql, listOf(sequenceId)).map { it.int(1) }.asSingle
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
                            listOf(incrementNo, sequenceId, calendarYearMonth, docOrgId)
                        ).asUpdate
                    )
                }
            } else {
                "/sql/updateDocumentNoFromSeq.sql".asResource { sql ->
                    DB.current.run(queryOf(sql, listOf(incrementNo, sequenceId)).asUpdate)
                }
            }
            next1
        } else {
            if (isUseOrgLevel || isStartNewYear) {
                val sequenceNumber = X_AD_Sequence_No(0)
                sequenceNumber.setSequenceId(sequenceId)
                sequenceNumber.setOrgId(docOrgId)
                sequenceNumber.setCalendarYearMonth(calendarYearMonth)
                sequenceNumber.setCurrentNext(startNo + incrementNo)
                sequenceNumber.saveEx()

                startNo
            } else {
                null
            }
        }) ?: return null

    // 	create DocumentNo
    val doc = StringBuilder()
    if (prefix != null && prefix.isNotEmpty()) {
        val prefixValue = parseVariable(prefix, po, false)
        if (!Util.isEmpty(prefixValue)) doc.append(prefixValue)
    }

    if (decimalPattern != null && decimalPattern.isNotEmpty())
        doc.append(DecimalFormat(decimalPattern).format(next.toLong()))
    else
        doc.append(next)

    if (suffix != null && suffix.isNotEmpty()) {
        val suffixValue = parseVariable(suffix, po, false)
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
 * @param tableID
 * @return Sequence
 */
internal fun get(tableName: String, tableID: Boolean): MSequence? {
    val realTableName =
        if (!tableID) {
            PREFIX_DOCSEQ + tableName
        } else tableName

    var sql = "SELECT * FROM AD_Sequence WHERE UPPER(Name)=? AND IsTableID=?"
    if (!tableID) sql = "$sql AND AD_Client_ID=?"

    val parameters =
        listOf(realTableName.toUpperCase(), if (tableID) "Y" else "N") +
        (if (!tableID) listOf(Environment.current.clientId) else emptyList())

    val query = queryOf(sql, parameters).map { row -> MSequence(row) }.asSingle

    return DB.current.run(query)
} // 	get

abstract class MBaseSequence : X_AD_Sequence {
    constructor(Id: Int) : super(Id)
    constructor(row: Row) : super(row)
}