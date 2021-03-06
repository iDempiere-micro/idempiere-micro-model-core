package software.hsharp.core.orm

import kotliquery.Row
import kotliquery.queryOf
import mu.KotlinLogging
import org.compiere.model.Column
import org.compiere.model.Element
import org.compiere.model.Field
import org.compiere.orm.getColumn
import org.compiere.orm.isZeroIDTable
import org.compiere.util.DisplayType
import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.SecureEngine
import org.idempiere.icommon.model.PersistentObject
import org.idempiere.orm.Null
import org.idempiere.orm.POInfo.getPOInfo
import org.idempiere.orm.POInfoColumn
import software.hsharp.core.util.DB
import software.hsharp.core.util.convertDate
import software.hsharp.core.util.executeUpdate
import software.hsharp.core.util.getSQLValue
import java.math.BigDecimal
import java.sql.Blob
import java.sql.Clob
import java.sql.Timestamp
import kotlin.collections.ArrayList

private val log = KotlinLogging.logger {}

/** Zero Integer  */
const val I_ZERO = 0

internal abstract class PO(row: Row?) : PersistentObject {

    companion object {
        fun copyValues(from: PO, to: PO) {
            log.trace { "From ID=" + from.id + " - To ID=" + to.id }
            // 	Different Classes
            if (from.javaClass != to.javaClass) {
                for (i1 in 0 until from.oldValues.size) {
                    val colName = from.p_info.getColumnName(i1)
                    val column = getColumn(from.p_info.getColumnId(colName))
                    if (column.isVirtualColumn ||
                        column.isKey || // 	KeyColumn

                        column.isUUIDColumn || // IDEMPIERE-67

                        column.isStandardColumn ||
                        !column.isAllowCopy
                    )
                        continue
                    for (i2 in 0 until to.oldValues.size) {
                        if (to.p_info.getColumnName(i2) == colName) {
                            to.newValues[i2] = from.oldValues[i1]
                            break
                        }
                    }
                } // 	from loop
            } else
            // 	same class
            {
                for (i in 0 until from.oldValues.size) {
                    val colName = from.p_info.getColumnName(i)
                    val column = getColumn(from.p_info.getColumnId(colName))
                    if (column.isVirtualColumn ||
                        column.isKey || // 	KeyColumn

                        column.isUUIDColumn ||
                        column.isStandardColumn ||
                        !column.isAllowCopy
                    )
                        continue
                    to.newValues[i] = from.oldValues[i]
                }
            } // 	same class
        } // 	copy
    }

    /**
     * Equals based on ID
     *
     * @param other comparator
     * @return true if ID the same
     */
    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is PO) return false
        return if (other.javaClass == this.javaClass) if (other.id == 0 && id == 0)
            super.equals(other)
        else
            other.id == id else super.equals(other)
    } // 	equals

    override fun hashCode(): Int {
        return if (id == 0) (Int.MIN_VALUE..0).random() else id
    }

    /** Create New for Multi Key  */
    protected var createNew = false
    /** Key Columns  */
    protected var m_keyColumns: Array<String?> = arrayOf()
    /** Record_IDs  */
    protected var ids: Array<Any?> = arrayOf(I_ZERO)

    val p_info: org.idempiere.orm.POInfo = initPO()

    protected fun initPO(): org.idempiere.orm.POInfo {
        return getPOInfo(this.tableId)
    }

    /** Accounting Columns  */
    protected var s_acctColumns: List<String> = listOf()

    /** Original Values  */
    protected val oldValues: Array<Any?> = arrayOfNulls(p_info.columnCount)
    /** New Values  */
    protected val newValues: Array<Any?> = arrayOfNulls(p_info.columnCount)

    protected fun decrypt(index: Int, yy: Any?): Any? {
        if (yy == null) return null
        return if (index != -1 && p_info.isEncrypted(index)) {
            SecureEngine.decrypt(yy, clientId)
        } else yy
    } // 	decrypt

    override val clientId: Int
        get() = getValue("AD_Client_ID") as Int? ?: 0

    /**
     * ************************************************************************
     * LOB
     *
     * @param value LOB
     * @return object
     */
    protected fun getLOB(value: Any?): Any? {
        log.trace { "Value=$value" }
        if (value == null) return null
        //
        var retValue: Any? = null

        var length: Long = -99
        try {
            // [ 1643996 ] Chat not working in postgres port
            if (value is String || value is ByteArray)
                retValue = value
            else if (value is Clob)
            // 	returns String
            {
                val clob = value as Clob?
                length = clob!!.length()
                retValue = clob.getSubString(1, length.toInt())
            } else if (value is Blob)
            // 	returns byte[]
            {
                val blob = value as Blob?
                length = blob!!.length()
                var index = 1 // 	correct
                if (blob.javaClass.name == "oracle.jdbc.rowset.OracleSerialBlob")
                    index = 0 // 	Oracle Bug Invalid Arguments
                // 	at oracle.jdbc.rowset.OracleSerialBlob.getBytes(OracleSerialBlob.java:130)
                retValue = blob.getBytes(index.toLong(), length.toInt())
            } else
                log.error { "Unknown: $value" }
        } catch (e: Exception) {
            log.error(e) { "Length=$length" }
        }

        return retValue
    } // 	getLOB

    fun getWhereClause(withValues: Boolean): String {
        val sb = StringBuilder()
        val keyColumns = m_keyColumns
        for (i in 0 until ids.size) {
            if (i != 0) sb.append(" AND ")
            sb.append(keyColumns[i]).append("=")
            if (withValues) {
                when {
                    keyColumns[i]?.endsWith("_ID") == true -> sb.append(ids[i])
                    ids[i] is Timestamp -> sb.append(convertDate(ids[i] as Timestamp, false))
                    else -> {
                        sb.append("'")
                        if (ids[i] is Boolean) {
                            if (ids[i] as Boolean) {
                                sb.append("Y")
                            } else {
                                sb.append("N")
                            }
                        } else {
                            sb.append(ids[i])
                        }
                        sb.append("'")
                    }
                }
            } else
                sb.append("?")
        }
        return sb.toString()
    } // 	getWhereClause

    /**
     * (re)Load record with m_ID[*]
     *
     * @return true if loaded
     */
    fun load(): Boolean {
        var sql = "SELECT "
        val size = p_info.columnCount
        for (i in 0 until size) {
            if (i != 0) sql += ","
            sql += p_info.getColumnSQL(i) // 	Normal and Virtual Column
        }
        sql += " FROM ${p_info.tableName} WHERE " + getWhereClause(false)

        //
        // 	int index = -1;
        log.trace(getWhereClause(true))

        fun mapParameter(oo: Any?): Any {
            return when (oo) {
                is Int -> oo
                is Boolean -> if (oo) "Y" else "N"
                is Timestamp -> oo
                else -> oo.toString()
            }
        }

        val params = ids.map { mapParameter(it) }.toTypedArray()
        val loadQuery = queryOf(sql, *params).map { load(it) }.asSingle
        val r = DB.current.run(loadQuery)

        val success =
            if (r != null) {
                r
            } else {
                log.error { "NO Data found for " + getWhereClause(true) }
                ids = arrayOf(I_ZERO)
                false
            }
        createNew = false
        // 	reset new values
        clearNewValues()

        loadComplete(success)
        return success
    } //  load

    protected fun clearNewValues() {
        for (i in 0 until newValues.size) newValues[i] = null
    }

    protected fun load(row: Row): Boolean {
        for (index in 0 until p_info.columnCount) {
            val columnName = p_info.getColumnName(index) ?: throw AdempiereSystemError("Column at index $index not found")
            val clazz = p_info.getColumnClass(index)
            val dt = p_info.getColumnDisplayType(index)

            if (clazz == Int::class.java || clazz == POInfoColumn.ColumnClass_Integer)
                oldValues[index] = decrypt(index, row.intOrNull(columnName))
            else if (clazz == BigDecimal::class.java)
                oldValues[index] = decrypt(index, row.bigDecimalOrNull(columnName))
            else if (clazz == Boolean::class.java || clazz == POInfoColumn.ColumnClass_Boolean)
                oldValues[index] = "Y" == decrypt(index, row.stringOrNull(columnName))
            else if (clazz == Timestamp::class.java)
                oldValues[index] = decrypt(index, row.sqlTimestampOrNull(columnName))
            else if (DisplayType.isLOB(dt))
                oldValues[index] = getLOB(row.anyOrNull(columnName))
            else if (clazz == String::class.java || clazz == POInfoColumn.ColumnClass_String) {
                var value: String? = decrypt(index, row.stringOrNull(columnName)) as String?
                if (value != null) {
                    if (tableId == Column.Table_ID ||
                        tableId == Element.Table_ID ||
                        tableId == Field.Table_ID
                    ) {
                        if ("Description" == columnName || "Help" == columnName) {
                            value = value.intern()
                        }
                    }
                }
                oldValues[index] = value
            } else
                oldValues[index] = loadSpecial(row, index)
        }
        createNew = false
        setKeyInfo()
        loadComplete(true)
        return true
    }

    protected open fun loadComplete(success: Boolean) {}

    /** Set Key Info (IDs and KeyColumns).  */
    protected fun setKeyInfo() {
        // 	Search for Primary Key
        for (i in 0 until p_info.columnCount) {
            if (p_info.isKey(i)) {
                val ColumnName = p_info.getColumnName(i)
                m_keyColumns = arrayOf(ColumnName)
                if (p_info.getColumnName(i)!!.endsWith("_ID")) {
                    val ii = getValue(i) as Int?
                    ids = if (ii == null)
                        arrayOf(I_ZERO)
                    else
                        arrayOf(ii)
                    log.trace { "(PK) $ColumnName=$ii" }
                } else {
                    val oo: Any? = getValue(i)
                    ids = if (oo == null)
                        arrayOf(null)
                    else
                        arrayOf(oo)
                    log.trace { "(PK) $ColumnName=$oo" }
                }
                return
            }
        } // 	primary key search

        // 	Search for Parents
        val columnNames = ArrayList<String>()
        for (i in 0 until p_info.columnCount) {
            if (p_info.isColumnParent(i)) columnNames.add(p_info.getColumnName(i) ?: "")
        }
        // 	Set FKs
        val size = columnNames.size
        if (size == 0) throw IllegalStateException("No PK nor FK - " + p_info.tableName)
        ids = arrayOfNulls(size)
        m_keyColumns = arrayOfNulls(size)
        for (i in 0 until size) {
            m_keyColumns[i] = columnNames[i]
            val keyColumn = m_keyColumns[i]
            if (keyColumn != null) {
                if (keyColumn.endsWith("_ID")) {
                    val ii: Int? =
                        try {
                            getValue(keyColumn) as Int?
                        } catch (e: Exception) {
                            log.error(e) { "" }
                            null
                        }

                    if (ii != null) ids[i] = ii
                } else
                    ids[i] = getValue(keyColumn)
            }
            log.trace { "(FK) " + keyColumn + "=" + ids[i] }
        }
    } // 	setKeyInfo

    open fun loadSpecial(rs: Row, index: Int): Any? {
        log.trace { "(NOP) - " + p_info.getColumnName(index)!! }
        return null
    } //  loadSpecial

    protected fun charToBoolean(oo: Any?): Boolean {
        if (oo != null) {
            return if (oo is Boolean) oo else "Y" == oo
        }
        return false
    }

    protected fun setAccountingColumns(acctTable: String): List<String> {
        if (s_acctColumns.isEmpty() || // 	cannot cache C_BP_*_Acct as there are 3
            acctTable.startsWith("C_BP_")
        ) {
            val sql = """
                SELECT c.ColumnName
                FROM AD_Column c INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID)
                WHERE t.TableName=? AND c.IsActive='Y' AND c.AD_Reference_ID=25 ORDER BY c.ColumnName
            """.trimIndent()

            val loadQuery = queryOf(sql, acctTable).map { row -> row.string(1) }.asList
            s_acctColumns = DB.current.run(loadQuery)

            if (s_acctColumns.isEmpty()) {
                log.error { "No Columns for $acctTable" }
            }
        }
        return s_acctColumns
    }

    /**
     * Get UpdatedBy
     *
     * @return AD_User_ID
     */
    fun getUpdatedBy(): Int {
        @Suppress("UNCHECKED_CAST")
        return getValue("UpdatedBy") ?: return 0
    } // 	getUpdatedBy

    /**
     * Insert Accounting Records
     *
     * @param acctTable accounting sub table
     * @param acctBaseTable acct table to get data from
     * @param whereClause optional where clause with alias "p" for acctBaseTable
     * @return true if records inserted
     */
    protected fun insertAccounting(acctTable: String, acctBaseTable: String, whereClause: String?): Boolean {
        val s_acctColumns = setAccountingColumns(acctTable)
        val tableName = p_info.tableName

        // 	Create SQL Statement - INSERT
        val sb = StringBuilder("INSERT INTO ")
            .append(acctTable)
            .append(" (")
            .append(tableName)
            .append(
                "_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy "
            )
        for (i in s_acctColumns.indices) sb.append(",").append(s_acctColumns[i])

        val uuidColumnId = getSQLValue(
            "SELECT col.AD_Column_ID FROM AD_Column col INNER JOIN AD_Table tbl ON col.AD_Table_ID = tbl.AD_Table_ID WHERE tbl.TableName=? AND col.ColumnName=?",
            acctTable,
            org.idempiere.orm.PO.getUUIDColumnName(acctTable)
        )
        if (uuidColumnId > 0)
            sb.append(",").append(org.idempiere.orm.PO.getUUIDColumnName(acctTable))
        // 	..	SELECT
        sb.append(") SELECT ")
            .append(id)
            .append(", p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', SysDate,")
            .append(getUpdatedBy())
            .append(",SysDate,")
            .append(getUpdatedBy())
        for (i in s_acctColumns.indices) sb.append(",p.").append(s_acctColumns[i])
        // uuid column
        if (uuidColumnId > 0) sb.append(",generate_uuid()")
        // 	.. 	FROM
        sb.append(" FROM ")
            .append(acctBaseTable)
            .append(" p WHERE p.AD_Client_ID=")
            .append(clientId)
        if (whereClause != null && whereClause.isNotEmpty()) sb.append(" AND ").append(whereClause)
        sb.append(" AND NOT EXISTS (SELECT * FROM ")
            .append(acctTable)
            .append(" e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.")
            .append(tableName)
            .append("_ID=")
            .append(id)
            .append(")")
        //
        val no = executeUpdate(sb.toString())
        if (no > 0) {
            log.trace { "#$no" }
        } else {
            log.warn { "#$no - Table=$acctTable from $acctBaseTable" }
        }

        return no > 0
    }

    init {
        if (row != null) load(row)
    }

    /**
     * Get Value as int
     *
     * @param index index
     * @return int value or 0
     */
    fun getValueAsInt(index: Int): Int {
        val value: Any = getValue(index) ?: return 0
        if (value is Int) return value
        return try {
            Integer.parseInt(value.toString())
        } catch (ex: NumberFormatException) {
            log.warn(p_info.getColumnName(index) + " - " + ex.message)
            0
        }
    } //  get_ValueAsInt

    /**
     * Get Integer Value
     *
     * @param columnName
     * @return int value
     */
    fun getValueAsInt(columnName: String): Int {
        val idx = getColumnIndex(columnName)
        return if (idx < 0) {
            0
        } else getValueAsInt(idx)
    }

    /**
     * Get Column Index
     *
     * @param columnName column name
     * @return index of column with ColumnName or -1 if not found
     */
    override fun getColumnIndex(columnName: String): Int {
        return p_info.getColumnIndex(columnName)
    } //  getColumnIndex

    /**
     * ************************************************************************ Get Value
     *
     * @param index index
     * @return value
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T> getValue(index: Int): T? {
        val columnCount = p_info.columnCount
        if (index < 0 || index >= columnCount) {
            return null
        }
        val newValues = newValues

        return if (newValues[index] != null) {
            if (newValues[index] == Null.NULL) null else newValues[index] as T
        } else oldValues[index] as T
    } //  get_Value

    /**
     * Get Value
     *
     * @param columnName column name
     * @return value or null
     */
    override fun <T> getValue(columnName: String): T? {
        val index = getColumnIndex(columnName)
        if (index < 0) {
            return null
        }
        return getValue(index)
    } //  get_Value

    /**
     * Get Value of Column
     *
     * @param columnId column
     * @return value or null
     */
    override fun <T> getValueOfColumn(columnId: Int): T? {
        val index = p_info.getColumnIndex(columnId)
        if (index < 0) {
            return null
        }
        return getValue(index)
    } //  get_ValueOfColumn

    /**
     * Is new record
     *
     * @return true if new
     */
    override val isNew: Boolean get() {
        if (createNew) return true

        val id = ids.firstOrNull { it != I_ZERO && it != Null.NULL }
        if (id != null) return false

        return !isZeroIDTable(tableName)
    } // 	isNew
}

fun getAllIDs(tableName: String, whereClause: String?): IntArray {
    val sql = StringBuilder("SELECT ")
    sql.append(tableName).append("_ID FROM ").append(tableName)
    if (whereClause != null && whereClause.isNotEmpty()) sql.append(" WHERE ").append(whereClause)
    val loadQuery = software.hsharp.core.util.queryOf(sql.toString(), listOf()).map { row -> row.int(1) }.asList
    return DB.current.run(loadQuery).toIntArray()
}

fun getIDsEx(sql: String, vararg params: Any): IntArray {
    val loadQuery = software.hsharp.core.util.queryOf(sql, listOf(*params)).map { row -> row.int(1) }.asList
    return DB.current.run(loadQuery).toIntArray()
}