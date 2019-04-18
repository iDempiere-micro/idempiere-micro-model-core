package org.compiere.orm

import kotliquery.Row
import org.compiere.model.Column
import org.compiere.model.Table
import org.compiere.model.ViewComponent
import org.compiere.model.TypedQuery
import org.idempiere.common.util.memoize
import org.idempiere.icommon.model.PersistentObject
import software.hsharp.core.orm.MBaseTable
import software.hsharp.core.orm.getFactoryList
import software.hsharp.core.orm.getTable
import software.hsharp.core.util.asResource
import software.hsharp.core.util.getSQLValueEx

import java.util.ArrayList

/**
 * Get Table Name
 *
 * @param tableId table
 * @return tavle name
 */
fun getDbTableName(tableId: Int): String {
    return getTable(tableId).dbTableName
} //	getTableName

/**
 * Grant independence to GenerateModel from AD_Table_ID
 *
 * @return int retValue
 */
fun getTableId(tableName: String): Int {
    return "/sql/getTableId.sql".asResource {
        sql -> getSQLValueEx( sql, listOf(tableName) )
    }
}

/**
 * Verify if the table contains ID=0
 *
 * @return true if table has zero ID
 */
fun isZeroIDTable(tableName: String): Boolean {
    return (tableName == "AD_Org"
            || tableName == "AD_OrgInfo"
            || tableName == "AD_Client"
            || // IDEMPIERE-668

            tableName == "AD_ReportView"
            || tableName == "AD_Role"
            || tableName == "AD_System"
            || tableName == "AD_User"
            || tableName == "C_DocType"
            || tableName == "GL_Category"
            || tableName == "M_AttributeSet"
            || tableName == "M_AttributeSetInstance")
}



/**
 * Persistent Table Model
 *
 *
 * Change log:
 *
 *
 *  * 2007-02-01 - teo_sarca - [ 1648850 ] MTable.getClass works incorrect for table "Fact_Acct"
 *
 *
 *
 *  * 2007-08-30 - vpj-cd - [ 1784588 ] Use ModelPackage of EntityType to Find Model Class
 *
 *
 * @author Jorg Janke
 * @author Teo Sarca, teo.sarca@gmail.com
 *  * BF [ 3017117 ] MTable.getClass returns bad class
 * https://sourceforge.net/tracker/?func=detail&aid=3017117&group_id=176962&atid=879332
 * @version $Id: MTable.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
class MTable : MBaseTable {
    /**
     * Get view components
     *
     * @param reload reload data
     * @return array of view component
     */
    private fun doLoadViewComponents(): List<ViewComponent>? {
        if (!isView || !isActive()) return null

        val query = Query<ViewComponent>(
            ViewComponent.Table_Name,
            ViewComponent.COLUMNNAME_AD_Table_ID + "=?"
        )
        query.setParameters(tableTableId)
        query.setOrderBy(MViewComponent.COLUMNNAME_SeqNo)
        query.setOnlyActiveRecords(true)
        return query.list()
    }
    private val loadViewComponents = { doLoadViewComponents() }.memoize()

    /**
     * Table has a single Key
     *
     * @return true if table has single key column
     */
    val isSingleKey: Boolean
        get() {
            val keys = tableKeyColumns
            return keys.size == 1
        } //	isSingleKey

    /**
     * Get Key Columns of Table
     *
     * @return key columns
     */
    //
    override fun getTableKeyColumns(): Array<String> {
        val m_columns = getColumns(false)
        val list = ArrayList<String>()
        for (column in m_columns) {
            if (column.isKey) return arrayOf(column.columnName)
            if (column.isParent) list.add(column.columnName)
        }
        return list.toTypedArray()
    } //	getKeyColumns

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param AD_Table_ID id
     */
    constructor(AD_Table_ID: Int) : super(AD_Table_ID) {
        if (AD_Table_ID == 0) {
            tableAccessLevel = ACCESSLEVEL_SystemOnly // 4
            entityType = org.idempiere.orm.PO.ENTITYTYPE_UserMaintained // U
            setIsChangeLog(false)
            setIsDeleteable(false)
            setIsHighVolume(false)
            setIsSecurityEnabled(false)
            setIsView(false) // N
            setReplicationType(REPLICATIONTYPE_Local)
        }
    } //	MTable

    /**
     * Load Constructor
     */
    constructor(row: Row) : super(row)

    /**
     * Get Column
     *
     * @param columnName (case insensitive)
     * @return column if found
     */
    override fun getColumn(columnName: String): Column? {
        return getColumns(false)
            .firstOrNull { columnName.equals(it.columnName, ignoreCase = true) }
    } //	getColumn

    /**
     * Get Column Index
     *
     * @param ColumnName column name
     * @return index of column with ColumnName or -1 if not found
     */
    @Synchronized
    override fun getDbColumnIndex(ColumnName: String): Int {
        val i = columnNameMap[ColumnName.toUpperCase()]
        return i ?: -1

    } //  getColumnIndex

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    override fun beforeSave(newRecord: Boolean): Boolean {
        if (isView && isDeletable) setIsDeleteable(false)
        //
        return true
    } //	beforeSave

    // globalqss

    /**
     * After Save
     *
     * @param newRecord new
     * @param success   success
     * @return success
     */
    override fun afterSave(newRecord: Boolean, success: Boolean): Boolean {
        if (!success) return success
        //	Sync Table ID
        val seq = MSequence.get(dbTableName)
        if (seq == null || seq.id == 0)
            MSequence.createTableSequence(dbTableName)
        else if (seq.name != dbTableName) {
            seq.name = dbTableName
            seq.saveEx()
        }

        return success
    } //	afterSave

    /**
     * Create query to retrieve one or more PO.
     *
     * @param whereClause
     * @return Query
     */
    override fun <T:PersistentObject> createQuery(whereClause: String): TypedQuery<T> {
        return Query(this, whereClause)
    }

    /**
     * Get view components
     *
     * @param reload reload data
     * @return array of view component
     */
    fun getViewComponent(reload: Boolean): Array<ViewComponent>? {
        return (if (reload) { doLoadViewComponents() } else { loadViewComponents() })?.toTypedArray()
    }

    /**
     * String Representation
     *
     * @return info
     */
    override fun toString(): String {
        return "MTable[$id-$dbTableName]"
    } //	toString

    override fun <T : PersistentObject> getPO(id: Int): T? {
        val tableName = dbTableName

        val factoryList = getFactoryList()
        return factoryList?.map { it.getPO<T>(tableName, id) }?.first()
    } // 	getPO

    /**
     * Get Data Access Level.
     *
     * @return Access Level required
     */
    /**
     * Set Data Access Level.
     *
     * @param AccessLevel Access Level required
     */
    override fun getTableAccessLevel(): String = getValue(Table.COLUMNNAME_AccessLevel) as String
    override fun setTableAccessLevel(AccessLevel: String) {
        setValue(Table.COLUMNNAME_AccessLevel, AccessLevel)
    }

    companion object {
        private val serialVersionUID = -8757836873040013402L
    }
} //	MTable
