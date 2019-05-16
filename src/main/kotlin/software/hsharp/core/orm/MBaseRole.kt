package software.hsharp.core.orm

import kotliquery.Row
import org.compiere.model.ColumnAccess
import org.compiere.model.RecordAccess
import org.compiere.model.TableAccess
import org.compiere.model.OrganizationAccessSummary
import org.compiere.orm.MColumnAccess
import org.compiere.orm.MTableAccess
import org.compiere.orm.X_AD_Role
import org.compiere.orm.MRecordAccess
import org.compiere.orm.MRoleOrgAccess
import org.compiere.orm.MUserOrgAccess
import org.compiere.orm.getClient
import org.compiere.orm.getOrg
import org.compiere.orm.getTree
import org.compiere.util.translate
import org.idempiere.common.util.CLogger
import software.hsharp.core.util.DB
import software.hsharp.core.util.queryOf
import java.io.Serializable
import java.util.logging.Level

abstract class MBaseRole : X_AD_Role {
    private val tableAccesses: MutableList<TableAccess> = mutableListOf()

    constructor(Id: Int) : super(Id)
    constructor(row: Row) : super(row)

    protected val localLog: CLogger get() = super.getMyLog()

    /**
     * User
     */
    var userId: Int = -1

    /** Org Access Summary  */
    protected inner class OrgAccess
    /**
     * Org Access constructor
     *
     * @param ad_Client_ID client
     * @param ad_Org_ID org
     * @param readonly r/o
     */
        (ad_Client_ID: Int, ad_Org_ID: Int, readonly: Boolean) : Serializable, OrganizationAccessSummary {
        /** Client  */
        override var clientId = 0
        /** Organization  */
        override var orgId = 0
        /** Read Only  */
        override var readOnly = true

        init {
            this.clientId = ad_Client_ID
            this.orgId = ad_Org_ID
            this.readOnly = readonly
        }

        /**
         * Equals
         *
         * @param other object to compare
         * @return true if equals
         */
        override fun equals(other: Any?): Boolean {
            if (other != null && other is OrgAccess) {
                return other.clientId == clientId && other.orgId == orgId
            }
            return false
        } // 	equals

        /**
         * Hash Code
         *
         * @return hash Code
         */
        override fun hashCode(): Int {
            return clientId * 7 + orgId
        } // 	hashCode

        /**
         * Extended String Representation
         *
         * @return extended info
         */
        override fun toString(): String {
            var clientName = "System"
            if (clientId != 0) clientName = getClient(clientId).name
            var orgName = "*"
            if (orgId != 0) orgName = getOrg(orgId).name
            val sb = StringBuilder()
            sb.append(translate("AD_Client_ID"))
                .append("=")
                .append(clientName)
                .append(" - ")
                .append(translate("AD_Org_ID"))
                .append("=")
                .append(orgName)
            if (readOnly) sb.append(" r/o")
            return sb.toString()
        } // 	toString
    } // 	OrgAccess

    /**
     * Load Org Access Add Tree to List
     *
     * @param list list
     * @param oa org access
     */
    protected fun loadOrgAccessAdd(list: MutableList<OrganizationAccessSummary>, oa: MBaseRole.OrgAccess) {
        if (list.contains(oa)) return
        list.add(oa)
        // 	Do we look for trees?
        if (treeOrgId == 0) return
        val org = getOrg(oa.orgId)
        if (!org.isSummary) return
        // 	Summary Org - Get Dependents
        val tree = getTree(treeOrgId)
        val sql = ("SELECT AD_Client_ID, orgId FROM AD_Org " +
                "WHERE IsActive='Y' AND orgId IN (SELECT Node_ID FROM " +
                tree.nodeTableName +
                " WHERE AD_Tree_ID=? AND Parent_ID=? AND IsActive='Y')")
        val loadQuery = queryOf(sql, listOf(tree.treeId, org.orgId)).map {
            val AD_Client_ID = it.int(1)
            val AD_Org_ID = it.int(2)
            loadOrgAccessAdd(list, OrgAccess(AD_Client_ID, AD_Org_ID, oa.readOnly))
            true
        }.asList
        DB.current.run(loadQuery).min()
    } // 	loadOrgAccessAdd

    /**
     * Load Org Access Role
     *
     * @param list list
     */
    protected fun loadOrgAccessRole(list: MutableList<OrganizationAccessSummary>) {
        fun load(row: Row): Boolean {
            val oa = MRoleOrgAccess(row)
            loadOrgAccessAdd(list, OrgAccess(oa.clientId, oa.orgId, oa.isReadOnly))
            return true
        }

        val sql = "SELECT * FROM AD_Role_OrgAccess " + "WHERE AD_Role_ID=? AND IsActive='Y'"
        val loadQuery = queryOf(sql, listOf(roleId)).map { load(it) }.asList
        DB.current.run(loadQuery).min()
    } // 	loadOrgAccessRole

    /**
     * Load Table Access
     *
     * @param reload reload
     */
    override fun loadTableAccess(reload: Boolean): List<TableAccess> {
        if (tableAccesses.isNotEmpty() && !reload) return tableAccesses
        val sql = "SELECT * FROM AD_Table_Access " + "WHERE AD_Role_ID=? AND IsActive='Y'"
        val loadQuery = queryOf(sql, listOf(roleId)).map { MTableAccess(it) }.asList
        val result = DB.current.run(loadQuery)
        tableAccesses.clear()
        tableAccesses.addAll(result)

        if (localLog.isLoggable(Level.FINE)) localLog.fine("#" + tableAccesses.size)
        return result
    } // 	loadTableAccess

    protected fun setTableAccess(tableAccesses: List<TableAccess>) {
        this.tableAccesses.clear()
        this.tableAccesses.addAll(tableAccesses)
    }

    /** Table Data Access Level  */
    protected val m_tableAccessLevel = mutableMapOf<Int, String>()
    /** Table Name  */
    protected val m_tableName = mutableMapOf<String, Int>()
    /** View Name  */
    protected val m_viewName: MutableSet<String> = mutableSetOf()
    /** ID Column Name *  */
    protected var m_tableIdName = mutableMapOf<String, String>()

    /**
     * Load Table Access and Name
     *
     * @param reload reload
     */
    protected fun loadTableInfo(reload: Boolean) {
        if (m_tableAccessLevel.isNotEmpty() && m_tableName.isNotEmpty() && !reload) return
        m_tableAccessLevel.clear()
        m_tableName.clear()
        m_viewName.clear()
        m_tableIdName.clear()

        fun load(row: Row): Boolean {
            val ii = row.int(1)
            m_tableAccessLevel.put(ii, row.string(2))
            val tableName = row.string(3)
            m_tableName.put(tableName, ii)
            val isView = row.string(4)
            if ("Y" == isView) {
                m_viewName.add(tableName.toUpperCase())
            }
            val idColumn = row.stringOrNull(5)
            if (idColumn != null && idColumn.trim { it <= ' ' }.isNotEmpty()) {
                m_tableIdName.put(tableName.toUpperCase(), idColumn)
            }
            return true
        }

        val sql = ("SELECT AD_Table_ID, AccessLevel, TableName, IsView, " +
                "(SELECT ColumnName FROM AD_COLUMN WHERE AD_COLUMN.AD_TABLE_ID = AD_TABLE.AD_TABLE_ID AND AD_COLUMN.COLUMNNAME = AD_TABLE.TABLENAME || '_ID') " +
                "FROM AD_Table WHERE IsActive='Y'")
        val loadQuery = queryOf(sql, listOf()).map { load(it) }.asList
        DB.current.run(loadQuery)

        if (localLog.isLoggable(Level.FINE)) localLog.fine("#" + m_tableAccessLevel.size)
    } // 	loadTableAccessLevel

    /** List of Column Access  MColumnAccess[] m_columnAccess */
    private val m_columnAccess: MutableList<ColumnAccess> = mutableListOf()

    protected fun setColumnAccess(columnAccesses: List<ColumnAccess>) {
        m_columnAccess.clear()
        m_columnAccess.addAll(columnAccesses)
    }

    /**
     * Load Column Access
     *
     * @param reload reload
     */
    override fun loadColumnAccess(reload: Boolean): List<ColumnAccess> {
        if (m_columnAccess.isNotEmpty() && !reload) return m_columnAccess
        val sql = "SELECT * FROM AD_Column_Access " + "WHERE AD_Role_ID=? AND IsActive='Y'"
        val loadQuery = queryOf(sql, listOf(roleId)).map { MColumnAccess(it) }.asList
        val result = DB.current.run(loadQuery)
        m_columnAccess.clear()
        m_columnAccess.addAll(result)
        if (localLog.isLoggable(Level.FINE)) localLog.fine("#" + m_columnAccess.size)
        return result
    } // 	loadColumnAccess

    /**
     * Load Org Access User
     *
     * @param list list
     */
    protected fun loadOrgAccessUser(list: MutableList<OrganizationAccessSummary>) {
        val sql = "SELECT * FROM AD_User_OrgAccess " + "WHERE AD_User_ID=? AND IsActive='Y'"

        fun load(row: Row): Int {
            val oa = MUserOrgAccess(row)
            loadOrgAccessAdd(list, OrgAccess(oa.clientId, oa.orgId, oa.isReadOnly()))
            return 0
        }

        val query = queryOf(sql, listOf(userId)).map { row -> load(row) }.asList

        DB.current.run(query).min()
    } // 	loadOrgAccessRole

    /**
     * List of Record Access
     */
    protected var recordAccess = mutableListOf<RecordAccess>()

    override fun getRecordAccessArray(): List<RecordAccess> = recordAccess
    protected fun setRecordAccessArray(value: List<RecordAccess>) {
        recordAccess.clear()
        recordAccess.addAll(value)
    }

    /**
     * List of Dependent Record Access
     */
    protected var recordDependentAccess = mutableListOf<RecordAccess>()

    override fun getRecordDependentAccessArray(): List<RecordAccess> = recordDependentAccess
    protected fun setRecordDependentAccessArray(value: List<RecordAccess>) {
        recordDependentAccess.clear()
        recordDependentAccess.addAll(value)
    }

    /**
     * Load Record Access
     *
     * @param reload reload
     */
    protected fun loadRecordAccess(reload: Boolean) {
        if (!(reload || recordAccess.isEmpty() || recordDependentAccess.isEmpty())) return

        fun load(row: Row): Int {
            val ra = MRecordAccess(row)
            recordAccess.add(ra)
            if (ra.isDependentEntities) recordDependentAccess.add(ra)
            return 0
        }

        val sql = "SELECT * FROM AD_Record_Access " + "WHERE AD_Role_ID=? AND IsActive='Y' ORDER BY AD_Table_ID"
        val query = queryOf(sql, listOf(roleId)).map { row -> load(row) }.asList

        DB.current.run(query).max()
    } // 	loadRecordAccess
}