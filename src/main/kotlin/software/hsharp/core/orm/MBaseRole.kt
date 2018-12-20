package software.hsharp.core.orm

import kotliquery.Row
import org.compiere.orm.*
import org.compiere.util.Msg
import org.idempiere.common.util.Env
import software.hsharp.core.util.DB
import software.hsharp.core.util.queryOf
import java.io.Serializable
import java.sql.ResultSet
import java.util.*
import java.util.logging.Level

fun getOfClient(ctx: Properties): kotlin.Array<MRole> {
    val sql = "SELECT * FROM AD_Role WHERE AD_Client_ID=?"
    val loadQuery = queryOf(sql, listOf(Env.getClientId(ctx))).map { MRole(ctx, it) }.asList
    return DB.current.run(loadQuery).toTypedArray()
} // 	getOfClient

open class MBaseRole : X_AD_Role {
    /** List of Table Access  */
    protected val m_tableAccess: MutableList<MTableAccess> = mutableListOf()

    constructor(ctx: Properties, Id: Int, trxName: String?) : super(ctx, Id, trxName)
    constructor(ctx: Properties, rs: ResultSet, trxName: String) : super(ctx, rs, trxName)
    constructor(ctx: Properties, row: Row) : super(ctx, row)

    /** Org Access Summary  */
    protected inner class OrgAccess
    /**
     * Org Access constructor
     *
     * @param ad_Client_ID client
     * @param ad_Org_ID org
     * @param readonly r/o
     */
        (ad_Client_ID: Int, ad_Org_ID: Int, readonly: Boolean) : Serializable {
        /** Client  */
        var clientId = 0
        /** Organization  */
        var orgId = 0
        /** Read Only  */
        var readOnly = true

        init {
            this.clientId = ad_Client_ID
            this.orgId = ad_Org_ID
            this.readOnly = readonly
        }

        /**
         * Equals
         *
         * @param obj object to compare
         * @return true if equals
         */
        override fun equals(obj: Any?): Boolean {
            if (obj != null && obj is OrgAccess) {
                val comp = obj as OrgAccess?
                return comp!!.clientId == clientId && comp.orgId == orgId
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
            if (clientId != 0) clientName = MClient.get(ctx, clientId).name
            var orgName = "*"
            if (orgId != 0) orgName = MOrg.get(ctx, orgId).name
            val sb = StringBuilder()
            sb.append(Msg.translate(ctx, "AD_Client_ID"))
                .append("=")
                .append(clientName)
                .append(" - ")
                .append(Msg.translate(ctx, "AD_Org_ID"))
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
     * @see Login
     */
    protected fun loadOrgAccessAdd(list: ArrayList<MBaseRole.OrgAccess>, oa: MBaseRole.OrgAccess) {
        if (list.contains(oa)) return
        list.add(oa)
        // 	Do we look for trees?
        if (aD_Tree_Org_ID == 0) return
        val org = MOrg.get(ctx, oa.orgId)
        if (!org.isSummary) return
        // 	Summary Org - Get Dependents
        val tree = MTree_Base.get(ctx, aD_Tree_Org_ID, null)
        val sql = ("SELECT AD_Client_ID, orgId FROM AD_Org " +
                "WHERE IsActive='Y' AND orgId IN (SELECT Node_ID FROM " +
                tree.nodeTableName +
                " WHERE AD_Tree_ID=? AND Parent_ID=? AND IsActive='Y')")
        val loadQuery = queryOf(sql, listOf(tree.aD_Tree_ID, org.orgId)).map {
            val AD_Client_ID = it.int(1)
            val AD_Org_ID = it.int(2)
            loadOrgAccessAdd(list, OrgAccess(AD_Client_ID, AD_Org_ID, oa.readOnly))
            true
        }.asList
        val result = DB.current.run(loadQuery).min() ?: false
    } // 	loadOrgAccessAdd

    /**
     * Load Org Access Role
     *
     * @param list list
     */
    protected fun loadOrgAccessRole(list: ArrayList<OrgAccess>) {
        fun load(row: Row): Boolean {
            val oa = MRoleOrgAccess(ctx, row)
            loadOrgAccessAdd(list, OrgAccess(oa.clientId, oa.orgId, oa.isReadOnly))
            return true
        }

        val sql = "SELECT * FROM AD_Role_OrgAccess " + "WHERE AD_Role_ID=? AND IsActive='Y'"
        val loadQuery = queryOf(sql, listOf(aD_Role_ID)).map { load(it) }.asList
        val result = DB.current.run(loadQuery).min() ?: false
    } // 	loadOrgAccessRole

    /**
     * Load Table Access
     *
     * @param reload reload
     */
    protected fun loadTableAccess(reload: Boolean): Array<MTableAccess> {
        if (m_tableAccess.isNotEmpty() && !reload) return m_tableAccess.toTypedArray()
        val sql = "SELECT * FROM AD_Table_Access " + "WHERE AD_Role_ID=? AND IsActive='Y'"
        val loadQuery = queryOf(sql, listOf(aD_Role_ID)).map { MTableAccess(ctx, it) }.asList
        val result = DB.current.run(loadQuery)
        m_tableAccess.clear()
        m_tableAccess.addAll(result)

        if (log.isLoggable(Level.FINE)) log.fine("#" + m_tableAccess.size)
        return result.toTypedArray()
    } // 	loadTableAccess

    protected fun setTableAccess(tableAccesses: Array<MTableAccess>) {
        m_tableAccess.clear()
        m_tableAccess.addAll(tableAccesses)
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

        if (log.isLoggable(Level.FINE)) log.fine("#" + m_tableAccessLevel.size)
    } // 	loadTableAccessLevel

    /** List of Column Access  MColumnAccess[] m_columnAccess */
    private val m_columnAccess: MutableList<MColumnAccess> = mutableListOf()

    protected fun setColumnAccess(columnAccesses: Array<MColumnAccess>) {
        m_columnAccess.clear()
        m_columnAccess.addAll(columnAccesses)
    }

    /**
     * Load Column Access
     *
     * @param reload reload
     */
    protected fun loadColumnAccess(reload: Boolean): Array<MColumnAccess> {
        if (m_columnAccess.isNotEmpty() && !reload) return m_columnAccess.toTypedArray()
        val sql = "SELECT * FROM AD_Column_Access " + "WHERE AD_Role_ID=? AND IsActive='Y'"
        val loadQuery = queryOf(sql, listOf(aD_Role_ID)).map { MColumnAccess(ctx, it) }.asList
        val result = DB.current.run(loadQuery)
        m_columnAccess.clear()
        m_columnAccess.addAll(result)
        if (log.isLoggable(Level.FINE)) log.fine("#" + m_columnAccess.size)
        return result.toTypedArray()
    } // 	loadColumnAccess
}