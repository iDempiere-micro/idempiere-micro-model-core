package org.compiere.orm

import kotliquery.Row
import org.compiere.model.ColumnAccess
import org.compiere.model.RecordAccess
import org.compiere.model.Role
import org.compiere.model.RoleIncluded
import org.compiere.model.Table.ACCESSLEVEL_Organization
import org.compiere.model.TableAccess
import org.compiere.model.OrganizationAccessSummary
import org.compiere.util.SystemIDs.USER_SUPERUSER
import org.compiere.util.getElementTranslation
import org.idempiere.common.exceptions.AdempiereException
import org.idempiere.common.util.Env
import org.idempiere.common.util.Trace
import org.idempiere.common.util.memoClear
import org.idempiere.common.util.memoize
import software.hsharp.core.orm.MBaseRole
import software.hsharp.core.orm.getTable
import software.hsharp.core.util.DB
import software.hsharp.core.util.Environment
import software.hsharp.core.util.executeUpdateEx
import software.hsharp.core.util.prepareStatement
import software.hsharp.core.util.queryOf
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.logging.Level

/**
 * Get Role for User
 *
 * @param roleId role
 * @param userId user
 * @param reload if true forces load
 * @return role
 */
private fun loadRoleForUser(
    roleId: Int,
    userId: Int
): MRole {
    val role = MRole(roleId)
    if (roleId == 0) {
        role.load() // 	special Handling
    }
    role.userId = userId
    role.loadAccess(true)
    return role
} // 	get

private val doGetRoleForUser = { roleId: Int, userId: Int -> loadRoleForUser(roleId, userId) }.memoize()

fun getRoleForUser(
    roleId: Int,
    userId: Int,
    reload: Boolean
): MRole = if (reload) loadRoleForUser(roleId, userId) else doGetRoleForUser(roleId, userId)

private fun loadDefaultRole(): MRole {
    val roleId = Env.getContextAsInt("#AD_Role_ID")
    val userId = Environment.current.userId
    return getRoleForUser(roleId, userId, true)
}

private val doGetDefaultRole = { loadDefaultRole() }.memoize()

/**
 * Get Role (cached). Did not set user - so no access loaded
 *
 * @param AD_Role_ID role
 * @return role
 */
fun getRole(AD_Role_ID: Int): MRole {
    return getRoleForUser(
        AD_Role_ID,
        Env.getUserId(),
        false
    ) // metas-2009_0021_AP1_G94 - we need to use this method because we need to
} // 	get

/**
 * Get/Set Default Role.
 *
 * @param reload if true forces load
 * @return role
 * @see Login.loadPreferences
 */
fun getDefaultRole(reload: Boolean): MRole = if (reload) loadDefaultRole() else doGetDefaultRole()
fun getDefaultRole(): MRole = doGetDefaultRole()

fun getClientRoles(): Array<MRole> {
    val sql = "SELECT * FROM AD_Role WHERE AD_Client_ID=?"
    val loadQuery = queryOf(sql, listOf(Env.getClientId())).map { MRole(it) }.asList
    return DB.current.run(loadQuery).toTypedArray()
} // 	getOfClient

/**
 * Get Roles With where clause
 *
 * @param ctx context
 * @param whereClause where clause
 * @return roles of client
 */
fun getFilteredRoles(whereClause: String?): List<MRole> {
    var sql = "SELECT * FROM AD_Role"
    if (whereClause != null && whereClause.isNotEmpty()) sql += " WHERE $whereClause"

    val query = queryOf(sql, listOf()).map { row -> MRole(row) }.asList
    return DB.current.run(query)
} // 	getOf

/**
 * Role Model. Includes AD_User runtime info for Personal Access The class is final, so that you
 * cannot overwrite the security rules.
 *
 * @author Jorg Janke
 * @author Karsten Thiemann FR [ 1782412 ]
 * @author Carlos Ruiz - globalqss - FR [ 1846929 ] - implement ASP
 * @version $Id: MRole.java,v 1.5 2006/08/09 16:38:47 jjanke Exp $
 * @contributor KittiU - FR [ 3062553 ] - Duplicated action in DocAction list for Multiple Role
 * Users
 */
class MRole : MBaseRole {
    override fun getOrgAccess(): List<OrganizationAccessSummary> {
        return m_orgAccess
    }

    override fun getIncludedSeqNo(): Int {
        return m_includedSeqNo
    }

    override fun setIncludedSeqNo(seqNo: Int) {
        m_includedSeqNo = seqNo
    }

    /**
     * Positive List of Organizational Access
     */
    private var m_orgAccess =  mutableListOf<OrganizationAccessSummary>()
    /**
     * Window Access
     */
    private var m_windowAccess: HashMap<Int, Boolean>? = null
    /**
     * Process Access
     */
    private var m_processAccess: HashMap<Int, Boolean>? = null
    /**
     * Task Access
     */
    private var m_taskAccess: HashMap<Int, Boolean>? = null
    /**
     * Workflow Access
     */
    private var m_workflowAccess: HashMap<Int, Boolean>? = null
    /**
     * Form Access
     */
    private var m_formAccess: HashMap<Int, Boolean>? = null
    /**
     * Info Windows
     */
    private var m_infoAccess: HashMap<Int, Boolean>? = null
    /**
     * List of included roles. Do not access directly
     */
    private val m_includedRoles: MutableList<Role> = mutableListOf()
    /**
     * Parent Role
     */
    private var m_parent: Role? = null

    private var m_includedSeqNo = -1

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param AD_Role_ID id
     */
    constructor(AD_Role_ID: Int) : super(AD_Role_ID) {
        // 	ID=0 == System Administrator
        if (AD_Role_ID == 0) {
            setIsCanExport(true)
            setIsCanReport(true)
            setIsManual(false)
            setIsPersonalAccess(false)
            setIsPersonalLock(false)
            setIsShowAcct(false)
            setIsAccessAllOrgs(false)
            userLevel = USERLEVEL_Organization
            setPreferenceType(PREFERENCETYPE_Organization)
            setIsChangeLog(false)
            isOverwritePriceLimit = false
            setIsUseUserOrgAccess(false)
            setMaxQueryRecords(0)
            confirmQueryRecords = 0
        }
    } // 	MRole

    /**
     * Load Constructor
     */
    constructor(row: Row) : super(row) {} // 	MRole

    /**
     * Get Confirm Query Records
     *
     * @return entered records or 500 (default)
     */
    override fun getConfirmQueryRecords(): Int {
        val no = super.getConfirmQueryRecords()
        return if (no == 0) 500 else no
    } // 	getConfirmQueryRecords

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true if it can be saved
     */
    override fun beforeSave(newRecord: Boolean): Boolean {
        // 	if (newRecord || isValueChanged("UserLevel"))
        // 	{
        if (clientId == 0)
            userLevel = USERLEVEL_System
        else if (userLevel == USERLEVEL_System) {
            log.saveError("AccessTableNoUpdate", getElementTranslation("UserLevel"))
            return false
        }
        // 	}
        return true
    } // 	beforeSave

    /**
     * After Save
     *
     * @param newRecord new
     * @param success success
     * @return success
     */
    override fun afterSave(newRecord: Boolean, success: Boolean): Boolean {
        if (!success) return success
        if (newRecord) {
            // 	Add Role to SuperUser
            val su = MUserRoles(SUPERUSER_USER_ID, roleId)
            su.saveEx()
            // 	Add Role to User
            if (createdBy != SUPERUSER_USER_ID) {
                val ur = MUserRoles(createdBy, roleId)
                ur.saveEx()
            }
            updateAccessRecords()
        } else if (isValueChanged("UserLevel")) updateAccessRecords() //

        // 	Default Role changed
        doGetDefaultRole.memoClear()

        return success
    } // 	afterSave

    /**
     * Executed after Delete operation.
     *
     * @param success true if record deleted
     * @return true if delete is a success
     */
    override fun afterDelete(success: Boolean): Boolean {
        if (success) {
            deleteAccessRecords()
        }
        return success
    } // 	afterDelete

    /**
     * Create Access Records
     *
     * @param reset true will reset existing access
     * @return info
     */
    @JvmOverloads
    fun updateAccessRecords(reset: Boolean = true): String {
        if (isManual) return "-"

        val roleClientOrgUser = (roleId.toString() +
                "," +
                clientId +
                "," +
                orgId +
                ",'Y', SysDate," +
                getUpdatedBy() +
                ", SysDate," +
                getUpdatedBy() +
                ",'Y' ") // 	IsReadWrite

        val sqlWindow = ("INSERT INTO AD_Window_Access " +
                "(AD_Window_ID, AD_Role_ID," +
                " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) " +
                "SELECT DISTINCT w.AD_Window_ID, " +
                roleClientOrgUser +
                "FROM AD_Window w" +
                " INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID)" +
                " INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) " +
                " LEFT JOIN AD_Window_Access wa ON " +
                "(wa.AD_Role_ID=" +
                roleId +
                " AND w.AD_Window_ID = wa.AD_Window_ID) " +
                "WHERE wa.AD_Window_ID IS NULL AND t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt " + // only

                // check first tab
                "WHERE xt.AD_Window_ID=w.AD_Window_ID)" +
                "AND tt.AccessLevel IN ")

        val sqlProcess = ("INSERT INTO AD_Process_Access " +
                "(AD_Process_ID, AD_Role_ID," +
                " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, IsReadWrite) " +
                "SELECT DISTINCT p.AD_Process_ID, " +
                roleClientOrgUser +
                "FROM AD_Process p LEFT JOIN AD_Process_Access pa ON " +
                "(pa.AD_Role_ID=" +
                roleId +
                " AND p.AD_Process_ID = pa.AD_Process_ID) " +
                "WHERE pa.AD_Process_ID IS NULL AND AccessLevel IN ")

        val sqlForm = ("INSERT INTO AD_Form_Access " +
                "(AD_Form_ID, AD_Role_ID," +
                " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) " +
                "SELECT f.AD_Form_ID, " +
                roleClientOrgUser +
                "FROM AD_Form f LEFT JOIN AD_Form_Access fa ON " +
                "(fa.AD_Role_ID=" +
                roleId +
                " AND f.AD_Form_ID = fa.AD_Form_ID) " +
                "WHERE fa.AD_Form_ID IS NULL AND AccessLevel IN ")

        val sqlWorkflow = ("INSERT INTO AD_WorkFlow_Access " +
                "(AD_WorkFlow_ID, AD_Role_ID," +
                " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) " +
                "SELECT w.AD_WorkFlow_ID, " +
                roleClientOrgUser +
                "FROM AD_WorkFlow w LEFT JOIN AD_WorkFlow_Access wa ON " +
                "(wa.AD_Role_ID=" +
                roleId +
                " AND w.AD_WorkFlow_ID = wa.AD_WorkFlow_ID) " +
                "WHERE w.AD_Client_ID IN (0," +
                clientId +
                ") AND wa.AD_WorkFlow_ID IS NULL AND AccessLevel IN ")

        val sqlDocAction = ("INSERT INTO AD_Document_Action_Access " +
                "(AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
                "C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) " +
                "(SELECT " +
                clientId +
                ",0,'Y', SysDate," +
                getUpdatedBy() +
                ", SysDate," +
                getUpdatedBy() +
                ", doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID " +
                "FROM AD_Client client " +
                "INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) " +
                "INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) " +
                "INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID " +
                "AND rol.AD_Role_ID=" +
                roleId +
                ") LEFT JOIN AD_Document_Action_Access da ON " +
                "(da.AD_Role_ID=" +
                roleId +
                " AND da.C_DocType_ID=doctype.C_DocType_ID AND da.AD_Ref_List_ID=action.AD_Ref_List_ID) " +
                "WHERE (da.C_DocType_ID IS NULL AND da.AD_Ref_List_ID IS NULL)) ")

        val sqlInfo = ("INSERT INTO AD_InfoWindow_Access " +
                "(AD_InfoWindow_ID, AD_Role_ID," +
                " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy) " +
                "SELECT i.AD_InfoWindow_ID," +
                roleId +
                "," +
                clientId +
                "," +
                id +
                ",'Y',SysDate," +
                getUpdatedBy() +
                ", SysDate," +
                getUpdatedBy() +
                " FROM AD_InfoWindow i LEFT JOIN AD_InfoWindow_Access ia ON " +
                "(ia.AD_Role_ID=" +
                roleId +
                " AND i.AD_InfoWindow_ID = ia.AD_InfoWindow_ID) " +
                "WHERE i.AD_Client_ID IN (0," +
                clientId +
                ") AND ia.AD_InfoWindow_ID IS NULL")

        val roleAccessLevel: String?
        var roleAccessLevelWin: String? = null
        if (USERLEVEL_System == userLevel)
            roleAccessLevel = "('4','7','6')"
        else if (USERLEVEL_Client == userLevel)
            roleAccessLevel = "('7','6','3','2')"
        else if (USERLEVEL_ClientPlusOrganization == userLevel)
            roleAccessLevel = "('7','6','3','2','1')"
        else
        // 	if (USERLEVEL_Organization.equals(getUserLevel()))
        {
            roleAccessLevel = "('3','1','7')"
            roleAccessLevelWin = "$roleAccessLevel AND w.Name NOT LIKE '%(all)%'"
        }
        if (roleAccessLevelWin == null) roleAccessLevelWin = roleAccessLevel

        if (reset) deleteAccessRecords()

        val win = executeUpdateEx(sqlWindow + roleAccessLevelWin)
        val proc = executeUpdateEx(sqlProcess + roleAccessLevel)
        val form = executeUpdateEx(sqlForm + roleAccessLevel)
        val wf = executeUpdateEx(sqlWorkflow + roleAccessLevel)
        val docact = executeUpdateEx(sqlDocAction)
        val info = executeUpdateEx(sqlInfo)

        loadAccess(true)
        return ("@AD_Window_ID@ #" +
                win +
                " -  @AD_Process_ID@ #" +
                proc +
                " -  @AD_Form_ID@ #" +
                form +
                " -  @AD_Workflow_ID@ #" +
                wf +
                " -  @DocAction@ #" +
                docact +
                " -  @AD_InfoWindow_ID@ #" +
                info)
    } // 	createAccessRecords

    /**
     * Delete Access Records of the role after the role was (successfully) deleted.
     */
    private fun deleteAccessRecords() {
        val whereDel = " WHERE AD_Role_ID=$roleId"
        //
        val winDel = executeUpdateEx("DELETE FROM AD_Window_Access$whereDel")
        val procDel = executeUpdateEx("DELETE FROM AD_Process_Access$whereDel")
        val formDel = executeUpdateEx("DELETE FROM AD_Form_Access$whereDel")
        val wfDel = executeUpdateEx("DELETE FROM AD_WorkFlow_Access$whereDel")
        val docactDel = executeUpdateEx("DELETE FROM AD_Document_Action_Access$whereDel")
        val infoDel = executeUpdateEx("DELETE FROM AD_InfoWindow_Access$whereDel")

        if (log.isLoggable(Level.FINE))
            log.fine(
                "AD_Window_Access=" +
                        winDel +
                        ", AD_Process_Access=" +
                        procDel +
                        ", AD_Form_Access=" +
                        formDel +
                        ", AD_Workflow_Access=" +
                        wfDel +
                        ", AD_Document_Action_Access=" +
                        docactDel +
                        ", AD_InfoWindow_Access=" +
                        infoDel
            )
    }

    /**
     * String Representation
     *
     * @return info
     */
    override fun toString(): String {
        return "MRole[" + roleId +
                "," +
                name +
                ",UserLevel=" +
                userLevel +
                "," +
                getClientWhere(false) +
                "," +
                getOrgWhere(false) +
                "]"
    } // 	toString

    /**
     * ************************************************************************ Load Access Info
     *
     * @param reload re-load from disk
     */
    override fun loadAccess(reload: Boolean) {
        loadOrgAccess(reload)
        loadTableAccess(reload)
        loadTableInfo(reload)
        loadColumnAccess(reload)
        loadRecordAccess(reload)
        if (reload) {
            m_windowAccess = null
            m_processAccess = null
            m_taskAccess = null
            m_workflowAccess = null
            m_formAccess = null
        }
        loadIncludedRoles(reload) // Load/Reload included roles - metas-2009_0021_AP1_G94
    } // 	loadAccess

    /**
     * Load Org Access
     *
     * @param reload reload
     */
    private fun loadOrgAccess(reload: Boolean) {
        if (!(reload)) return
        //
        val list = ArrayList<OrganizationAccessSummary>()

        if (isUseUserOrgAccess)
            loadOrgAccessUser(list)
        else
            loadOrgAccessRole(list)

        m_orgAccess = list
        if (log.isLoggable(Level.FINE))
            log.fine("#" + m_orgAccess.size + if (reload) " - reload" else "")
    } // 	loadOrgAccess

    /**
     * Check if tableName is a view
     *
     * @param tableName
     * @return boolean
     */
    private fun isView(tableName: String): Boolean {
        val m_viewName = m_viewName
        return m_viewName.contains(tableName.toUpperCase())
    }

    private fun getIdColumnName(tableName: String): String? {
        return m_tableIdName[tableName.toUpperCase()]
    }

    /**
     * ************************************************************************ Get Client Where
     * Clause Value
     *
     * @param rw read write
     * @return "AD_Client_ID=0" or "AD_Client_ID IN(0,1)"
     */
    fun getClientWhere(rw: Boolean): String {
        // 	All Orgs - use Client of Role
        if (isAccessAllOrgs) {
            return if (rw || clientId == 0) "AD_Client_ID=$clientId" else "AD_Client_ID IN (0,$clientId)"
        }

        // 	Get Client from Org List
        loadOrgAccess(false)
        // 	Unique Strings
        val set = HashSet<String>()
        if (!rw) set.add("0")
        // 	Positive List
        for (mOrgAccess in m_orgAccess) set.add(mOrgAccess.clientId.toString())
        //
        val sb = StringBuilder()
        val it = set.iterator()
        var oneOnly = true
        while (it.hasNext()) {
            if (sb.length > 0) {
                sb.append(",")
                oneOnly = false
            }
            sb.append(it.next())
        }
        if (oneOnly) {
            if (sb.length > 0)
                return "AD_Client_ID=$sb"
            else {
                log.log(Level.SEVERE, "No Access Org records")
                return "AD_Client_ID=-1" // 	No Access Record
            }
        }
        return "AD_Client_ID IN($sb)"
    } // 	getClientWhereValue

    /**
     * Access to Client
     *
     * @param AD_Client_ID client
     * @param rw read write access
     * @return true if access
     */
    fun isClientAccess(AD_Client_ID: Int, rw: Boolean): Boolean {
        if (AD_Client_ID == 0 && !rw)
        // 	can always read System
            return true
        //
        // Check Access All Orgs:
        if (isAccessAllOrgs) {
            // User has access to given clientId if the role is defined on that clientId
            return clientId == AD_Client_ID
        }
        //
        loadOrgAccess(false)
        // 	Positive List
        for (mOrgAccess in m_orgAccess) {
            if (mOrgAccess.clientId == AD_Client_ID) {
                if (!rw) return true
                if (!mOrgAccess.readOnly)
                // 	rw
                    return true
            }
        }
        return false
    } // 	isClientAccess

    /**
     * Get Org Where Clause Value
     *
     * @param rw read write
     * @return "AD_Org_ID=0" or "AD_Org_ID IN(0,1)" or null (if access all org)
     */
    fun getOrgWhere(rw: Boolean): String? {
        if (isAccessAllOrgs) return null
        loadOrgAccess(false)
        // 	Unique Strings
        val set = HashSet<String>()
        if (!rw) set.add("0")
        // 	Positive List
        for (mOrgAccess in m_orgAccess) {
            if (!rw)
                set.add(mOrgAccess.orgId.toString())
            else if (!mOrgAccess.readOnly)
            // 	rw
                set.add(mOrgAccess.orgId.toString())
        }
        //
        val sb = StringBuilder()
        val it = set.iterator()
        var oneOnly = true
        while (it.hasNext()) {
            if (sb.length > 0) {
                sb.append(",")
                oneOnly = false
            }
            sb.append(it.next())
        }
        if (oneOnly) {
            if (sb.length > 0)
                return "AD_Org_ID=$sb"
            else {
                log.log(Level.SEVERE, "No Access Org records")
                return "AD_Org_ID=-1" // 	No Access Record
            }
        }
        return "AD_Org_ID IN($sb)"
    } // 	getOrgWhereValue

    /**
     * Access to Table
     *
     * @param AD_Table_ID table
     * @param ro check read only access otherwise read write access level
     * @return has RO/RW access to table
     */
    fun isTableAccess(AD_Table_ID: Int, ro: Boolean): Boolean {
        if (!isTableAccessLevel(AD_Table_ID, ro))
        // 	Role Based Access
            return false

        // default to negative list, can access on all tables
        var hasAccess = true // 	assuming exclusive rule
        val m_tableAccess = loadTableAccess(false)
        for (mTableAccess in m_tableAccess) {
            if (X_AD_Table_Access.ACCESSTYPERULE_Accessing != mTableAccess.accessTypeRule)
                continue
            if (mTableAccess.isExclude)
            // 	Exclude
            // 	If you Exclude Access to a table and select Read Only,
            // 	you can only read data (otherwise no access).
            {
                if (mTableAccess.accessTableId == AD_Table_ID) {
                    if (ro)
                        hasAccess = mTableAccess.isReadOnly
                    else
                        hasAccess = false
                    if (log.isLoggable(Level.FINE))
                        log.fine(
                            "Exclude AD_Table_ID=" +
                                    AD_Table_ID +
                                    " (ro=" +
                                    ro +
                                    ",TableAccessRO=" +
                                    mTableAccess.isReadOnly +
                                    ") = " +
                                    hasAccess
                        )
                    return hasAccess
                }
            } else
            // 	Include
            // 	If you Include Access to a table and select Read Only,
            // 	you can only read data (otherwise full access).
            {
                // positive list, can access ONLY on included tables
                hasAccess = false
                if (mTableAccess.accessTableId == AD_Table_ID) {
                    if (!ro)
                    // 	rw only if not r/o
                        hasAccess = !mTableAccess.isReadOnly
                    else
                        hasAccess = true
                    if (log.isLoggable(Level.FINE))
                        log.fine(
                            "Include AD_Table_ID=" +
                                    AD_Table_ID +
                                    " (ro=" +
                                    ro +
                                    ",TableAccessRO=" +
                                    mTableAccess.isReadOnly +
                                    ") = " +
                                    hasAccess
                        )
                    return hasAccess
                }
            }
        } // 	for all Table Access
        if (!hasAccess)
            if (log.isLoggable(Level.FINE))
                log.fine("AD_Table_ID=$AD_Table_ID(ro=$ro) = $hasAccess")
        return hasAccess
    } // 	isTableAccess

    /**
     * Access to Table based on Role User Level Table Access Level
     *
     * @param AD_Table_ID table
     * @param ro check read only access otherwise read write access level
     * @return has RO/RW access to table
     */
    fun isTableAccessLevel(AD_Table_ID: Int, ro: Boolean): Boolean {
        if (ro)
        // 	role can always read
            return true
        //
        loadTableInfo(false)
        // 	AccessLevel
        // 		1 = Org - 2 = Client - 4 = System
        // 		3 = Org+Client - 6 = Client+System - 7 = All
        val roleAccessLevel = m_tableAccessLevel[AD_Table_ID]
        if (roleAccessLevel == null) {
            if (log.isLoggable(Level.FINE)) log.fine("NO - No AccessLevel - AD_Table_ID=$AD_Table_ID")
            return false
        }
        // 	Access to all User Levels
        if (roleAccessLevel == X_AD_Table.ACCESSLEVEL_All) return true
        // 	User Level = SCO
        val userLevel = userLevel
        //
        if (userLevel[0] == 'S' && (roleAccessLevel == X_AD_Table.ACCESSLEVEL_SystemOnly || roleAccessLevel == X_AD_Table.ACCESSLEVEL_SystemPlusClient))
            return true
        if (userLevel[1] == 'C' && (roleAccessLevel == X_AD_Table.ACCESSLEVEL_ClientOnly || roleAccessLevel == X_AD_Table.ACCESSLEVEL_SystemPlusClient))
            return true
        if (userLevel[2] == 'O' && (roleAccessLevel == ACCESSLEVEL_Organization || roleAccessLevel == X_AD_Table.ACCESSLEVEL_ClientPlusOrganization))
            return true
        if (log.isLoggable(Level.FINE))
            log.fine(
                "NO - AD_Table_ID=" +
                        AD_Table_ID +
                        ", UserLevel=" +
                        userLevel +
                        ", AccessLevel=" +
                        roleAccessLevel
            )
        return false
    } // 	isTableAccessLevel

    /**
     * Get Window Access
     *
     * @param AD_Window_ID window
     * @return null in no access, TRUE if r/w and FALSE if r/o
     */
    @Synchronized
    fun getWindowAccess(AD_Window_ID: Int): Boolean? {
        if (m_windowAccess == null) {
            m_windowAccess = HashMap(100)
            // first get the window access from the included and substitute roles
            mergeIncludedAccess("m_windowAccess") // Load included accesses - metas-2009_0021_AP1_G94
            // and now get the window access directly from this role
            val client = getClient()
            var ASPFilter = ""
            if (client.isUseASP)
                ASPFilter = ("   AND (   AD_Window_ID IN ( " +
                        // Just ASP subscribed windows for client "
                        "              SELECT w.AD_Window_ID " +
                        "                FROM ASP_Window w, ASP_Level l, ASP_ClientLevel cl " +
                        "               WHERE w.ASP_Level_ID = l.ASP_Level_ID " +
                        "                 AND cl.clientId = " +
                        client.clientId +
                        "                 AND cl.ASP_Level_ID = l.ASP_Level_ID " +
                        "                 AND w.IsActive = 'Y' " +
                        "                 AND l.IsActive = 'Y' " +
                        "                 AND cl.IsActive = 'Y' " +
                        "                 AND w.ASP_Status = 'S') " + // Show

                        "        OR AD_Window_ID IN ( " +
                        // + show ASP exceptions for client
                        "              SELECT AD_Window_ID " +
                        "                FROM ASP_ClientException ce " +
                        "               WHERE ce.clientId = " +
                        client.clientId +
                        "                 AND ce.IsActive = 'Y' " +
                        "                 AND ce.AD_Window_ID IS NOT NULL " +
                        "                 AND ce.AD_Tab_ID IS NULL " +
                        "                 AND ce.AD_Field_ID IS NULL " +
                        "                 AND ce.ASP_Status = 'S') " + // Show

                        "       ) " +
                        "   AND AD_Window_ID NOT IN ( " +
                        // minus hide ASP exceptions for client
                        "          SELECT AD_Window_ID " +
                        "            FROM ASP_ClientException ce " +
                        "           WHERE ce.clientId = " +
                        client.clientId +
                        "             AND ce.IsActive = 'Y' " +
                        "             AND ce.AD_Window_ID IS NOT NULL " +
                        "             AND ce.AD_Tab_ID IS NULL " +
                        "             AND ce.AD_Field_ID IS NULL " +
                        "             AND ce.ASP_Status = 'H')") // Hide
            val sql = "SELECT AD_Window_ID, IsReadWrite, IsActive FROM AD_Window_Access WHERE AD_Role_ID=?$ASPFilter"
            val directAccess = HashMap<Int, Boolean>(100)
            try {
                calculateAccess(sql, directAccess, m_windowAccess!!)
            } catch (e: Exception) {
                log.log(Level.SEVERE, sql, e)
            }

            //
            setAccessMap(
                "m_windowAccess", mergeAccess(getAccessMap("m_windowAccess"), directAccess, true)
            )
            if (log.isLoggable(Level.FINE)) log.fine("#" + m_windowAccess!!.size)
        } // 	reload
        val retValue = m_windowAccess!![AD_Window_ID]
        if (log.isLoggable(Level.FINE))
            log.fine("getWindowAccess - AD_Window_ID=$AD_Window_ID - $retValue")
        return retValue
    } // 	getWindowAccess

    @Throws(SQLException::class)
    private fun calculateAccess(
        sql: String,
        directAccess: HashMap<Int, Boolean>,
        m_windowAccess: HashMap<Int, Boolean>
    ) {
        val pstmt: PreparedStatement?
        val rs: ResultSet
        pstmt = prepareStatement(sql)
        pstmt!!.setInt(1, roleId)
        rs = pstmt.executeQuery()
        while (rs.next()) {
            val winId = rs.getInt(1)
            if ("N" == rs.getString(3)) {
                // inactive window on direct access
                m_windowAccess.remove(winId)
            } else {
                directAccess[winId] = "Y" == rs.getString(2)
            }
        }
    }

    /**
     * Get Process Access
     *
     * @param AD_Process_ID process
     * @return null in no access, TRUE if r/w and FALSE if r/o
     */
    @Synchronized
    fun getProcessAccess(AD_Process_ID: Int): Boolean? {
        if (m_processAccess == null) {
            m_processAccess = HashMap(50)
            // first get the process access from the included and substitute roles
            mergeIncludedAccess("m_processAccess") // Load included accesses - metas-2009_0021_AP1_G94
            // and now get the process access directly from this role
            val client = getClient()
            var ASPFilter = ""
            if (client.isUseASP)
                ASPFilter = ("   AND (   AD_Process_ID IN ( " +
                        // Just ASP subscribed processes for client "
                        "              SELECT p.AD_Process_ID " +
                        "                FROM ASP_Process p, ASP_Level l, ASP_ClientLevel cl " +
                        "               WHERE p.ASP_Level_ID = l.ASP_Level_ID " +
                        "                 AND cl.clientId = " +
                        client.clientId +
                        "                 AND cl.ASP_Level_ID = l.ASP_Level_ID " +
                        "                 AND p.IsActive = 'Y' " +
                        "                 AND l.IsActive = 'Y' " +
                        "                 AND cl.IsActive = 'Y' " +
                        "                 AND p.ASP_Status = 'S') " + // Show

                        "        OR AD_Process_ID IN ( " +
                        // + show ASP exceptions for client
                        "              SELECT AD_Process_ID " +
                        "                FROM ASP_ClientException ce " +
                        "               WHERE ce.clientId = " +
                        client.clientId +
                        "                 AND ce.IsActive = 'Y' " +
                        "                 AND ce.AD_Process_ID IS NOT NULL " +
                        "                 AND ce.AD_Process_Para_ID IS NULL " +
                        "                 AND ce.ASP_Status = 'S') " + // Show

                        "       ) " +
                        "   AND AD_Process_ID NOT IN ( " +
                        // minus hide ASP exceptions for client
                        "          SELECT AD_Process_ID " +
                        "            FROM ASP_ClientException ce " +
                        "           WHERE ce.clientId = " +
                        client.clientId +
                        "             AND ce.IsActive = 'Y' " +
                        "             AND ce.AD_Process_ID IS NOT NULL " +
                        "             AND ce.AD_Process_Para_ID IS NULL " +
                        "             AND ce.ASP_Status = 'H')") // Hide
            val sql = "SELECT AD_Process_ID, IsReadWrite, IsActive FROM AD_Process_Access WHERE AD_Role_ID=?$ASPFilter"
            val directAccess = HashMap<Int, Boolean>(100)
            try {
                calculateAccess(sql, directAccess, m_processAccess!!)
            } catch (e: Exception) {
                log.log(Level.SEVERE, sql, e)
            }

            setAccessMap(
                "m_processAccess", mergeAccess(getAccessMap("m_processAccess"), directAccess, true)
            )
        } // 	reload
        return m_processAccess!![AD_Process_ID]
    } // 	getProcessAccess

    /**
     * Get Task Access
     *
     * @param AD_Task_ID task
     * @return null in no access, TRUE if r/w and FALSE if r/o
     */
    @Synchronized
    fun getTaskAccess(AD_Task_ID: Int): Boolean? {
        if (m_taskAccess == null) {
            m_taskAccess = HashMap(10)
            // first get the task access from the included and substitute roles
            mergeIncludedAccess("m_taskAccess") // Load included accesses - metas-2009_0021_AP1_G94
            // and now get the task access directly from this role
            val client = getClient()
            var ASPFilter = ""
            if (client.isUseASP)
                ASPFilter = ("   AND (   AD_Task_ID IN ( " +
                        // Just ASP subscribed tasks for client "
                        "              SELECT t.AD_Task_ID " +
                        "                FROM ASP_Task t, ASP_Level l, ASP_ClientLevel cl " +
                        "               WHERE t.ASP_Level_ID = l.ASP_Level_ID " +
                        "                 AND cl.clientId = " +
                        client.clientId +
                        "                 AND cl.ASP_Level_ID = l.ASP_Level_ID " +
                        "                 AND t.IsActive = 'Y' " +
                        "                 AND l.IsActive = 'Y' " +
                        "                 AND cl.IsActive = 'Y' " +
                        "                 AND t.ASP_Status = 'S') " + // Show

                        "        OR AD_Task_ID IN ( " +
                        // + show ASP exceptions for client
                        "              SELECT AD_Task_ID " +
                        "                FROM ASP_ClientException ce " +
                        "               WHERE ce.clientId = " +
                        client.clientId +
                        "                 AND ce.IsActive = 'Y' " +
                        "                 AND ce.AD_Task_ID IS NOT NULL " +
                        "                 AND ce.ASP_Status = 'S') " + // Show

                        "       ) " +
                        "   AND AD_Task_ID NOT IN ( " +
                        // minus hide ASP exceptions for client
                        "          SELECT AD_Task_ID " +
                        "            FROM ASP_ClientException ce " +
                        "           WHERE ce.clientId = " +
                        client.clientId +
                        "             AND ce.IsActive = 'Y' " +
                        "             AND ce.AD_Task_ID IS NOT NULL " +
                        "             AND ce.ASP_Status = 'H')") // Hide
            val sql = "SELECT AD_Task_ID, IsReadWrite, IsActive FROM AD_Task_Access WHERE AD_Role_ID=?$ASPFilter"
            val directAccess = HashMap<Int, Boolean>(100)
            try {
                calculateAccess(sql, directAccess, m_taskAccess!!)
            } catch (e: Exception) {
                log.log(Level.SEVERE, sql, e)
            }

            setAccessMap("m_taskAccess", mergeAccess(getAccessMap("m_taskAccess"), directAccess, true))
        } // 	reload
        return m_taskAccess!![AD_Task_ID]
    } // 	getTaskAccess

    /**
     * Get Form Access
     *
     * @param AD_Form_ID form
     * @return null in no access, TRUE if r/w and FALSE if r/o
     */
    @Synchronized
    fun getFormAccess(AD_Form_ID: Int): Boolean? {
        if (m_formAccess == null) {
            m_formAccess = HashMap(20)
            // first get the form access from the included and substitute roles
            mergeIncludedAccess("m_formAccess") // Load included accesses - metas-2009_0021_AP1_G94
            // and now get the form access directly from this role
            val client = getClient()
            var ASPFilter = ""
            if (client.isUseASP)
                ASPFilter = ("   AND (   AD_Form_ID IN ( " +
                        // Just ASP subscribed forms for client "
                        "              SELECT f.AD_Form_ID " +
                        "                FROM ASP_Form f, ASP_Level l, ASP_ClientLevel cl " +
                        "               WHERE f.ASP_Level_ID = l.ASP_Level_ID " +
                        "                 AND cl.clientId = " +
                        client.clientId +
                        "                 AND cl.ASP_Level_ID = l.ASP_Level_ID " +
                        "                 AND f.IsActive = 'Y' " +
                        "                 AND l.IsActive = 'Y' " +
                        "                 AND cl.IsActive = 'Y' " +
                        "                 AND f.ASP_Status = 'S') " + // Show

                        "        OR AD_Form_ID IN ( " +
                        // + show ASP exceptions for client
                        "              SELECT AD_Form_ID " +
                        "                FROM ASP_ClientException ce " +
                        "               WHERE ce.clientId = " +
                        client.clientId +
                        "                 AND ce.IsActive = 'Y' " +
                        "                 AND ce.AD_Form_ID IS NOT NULL " +
                        "                 AND ce.ASP_Status = 'S') " + // Show

                        "       ) " +
                        "   AND AD_Form_ID NOT IN ( " +
                        // minus hide ASP exceptions for client
                        "          SELECT AD_Form_ID " +
                        "            FROM ASP_ClientException ce " +
                        "           WHERE ce.clientId = " +
                        client.clientId +
                        "             AND ce.IsActive = 'Y' " +
                        "             AND ce.AD_Form_ID IS NOT NULL " +
                        "             AND ce.ASP_Status = 'H')") // Hide
            val sql = "SELECT AD_Form_ID, IsReadWrite, IsActive FROM AD_Form_Access WHERE AD_Role_ID=?$ASPFilter"
            val directAccess = HashMap<Int, Boolean>(100)
            try {
                calculateAccess(sql, directAccess, m_formAccess!!)
            } catch (e: Exception) {
                log.log(Level.SEVERE, sql, e)
            }

            setAccessMap("m_formAccess", mergeAccess(getAccessMap("m_formAccess"), directAccess, true))
        } // 	reload
        return m_formAccess!![AD_Form_ID]
    } // 	getFormAccess

    /**
     * Get Workflow Access
     *
     * @param AD_Workflow_ID workflow
     * @return null in no access, TRUE if r/w and FALSE if r/o
     */
    @Synchronized
    fun getWorkflowAccess(AD_Workflow_ID: Int): Boolean? {
        if (m_workflowAccess == null) {
            m_workflowAccess = HashMap(20)
            // first get the workflow access from the included and substitute roles
            mergeIncludedAccess("m_workflowAccess") // Load included accesses - metas-2009_0021_AP1_G94
            // and now get the workflow access directly from this role
            val client = getClient()
            var ASPFilter = ""
            if (client.isUseASP)
                ASPFilter = ("   AND (   AD_Workflow_ID IN ( " +
                        // Just ASP subscribed workflows for client "
                        "              SELECT w.AD_Workflow_ID " +
                        "                FROM ASP_Workflow w, ASP_Level l, ASP_ClientLevel cl " +
                        "               WHERE w.ASP_Level_ID = l.ASP_Level_ID " +
                        "                 AND cl.clientId = " +
                        client.clientId +
                        "                 AND cl.ASP_Level_ID = l.ASP_Level_ID " +
                        "                 AND w.IsActive = 'Y' " +
                        "                 AND l.IsActive = 'Y' " +
                        "                 AND cl.IsActive = 'Y' " +
                        "                 AND w.ASP_Status = 'S') " + // Show

                        "        OR AD_Workflow_ID IN ( " +
                        // + show ASP exceptions for client
                        "              SELECT AD_Workflow_ID " +
                        "                FROM ASP_ClientException ce " +
                        "               WHERE ce.clientId = " +
                        client.clientId +
                        "                 AND ce.IsActive = 'Y' " +
                        "                 AND ce.AD_Workflow_ID IS NOT NULL " +
                        "                 AND ce.ASP_Status = 'S') " + // Show

                        "       ) " +
                        "   AND AD_Workflow_ID NOT IN ( " +
                        // minus hide ASP exceptions for client
                        "          SELECT AD_Workflow_ID " +
                        "            FROM ASP_ClientException ce " +
                        "           WHERE ce.clientId = " +
                        client.clientId +
                        "             AND ce.IsActive = 'Y' " +
                        "             AND ce.AD_Workflow_ID IS NOT NULL " +
                        "             AND ce.ASP_Status = 'H')") // Hide
            val sql =
                "SELECT AD_Workflow_ID, IsReadWrite, IsActive FROM AD_Workflow_Access WHERE AD_Role_ID=?$ASPFilter"
            val directAccess = HashMap<Int, Boolean>(100)
            try {
                calculateAccess(sql, directAccess, m_workflowAccess!!)
            } catch (e: Exception) {
                log.log(Level.SEVERE, sql, e)
            } finally {
            }
            setAccessMap(
                "m_workflowAccess", mergeAccess(getAccessMap("m_workflowAccess"), directAccess, true)
            )
        } // 	reload
        return m_workflowAccess!![AD_Workflow_ID]
    } // 	getTaskAccess

    /**
     * *********************************************************************** Appends where clause to
     * SQL statement for Table
     *
     * @param SQL existing SQL statement
     * @param TableNameIn Table Name or list of table names AAA, BBB or AAA a, BBB b
     * @param fullyQualified fullyQualified names
     * @param rw if false, includes System Data
     * @return updated SQL statement
     */
    fun addAccessSQL(SQL: String, TableNameIn: String?, fullyQualified: Boolean, rw: Boolean): String {
        val retSQL = StringBuilder()

        // 	Cut off last ORDER BY clause
        var orderBy = ""
        val posOrder = SQL.lastIndexOf(" ORDER BY ")
        if (posOrder != -1) {
            orderBy = SQL.substring(posOrder)
            retSQL.append(SQL, 0, posOrder)
        } else
            retSQL.append(SQL)

        // 	Parse SQL
        val asp = AccessSqlParser(retSQL.toString())
        val ti = asp.getTableInfo(asp.mainSqlIndex)

        //  Do we have to add WHERE or AND
        if (!asp.mainSql!!.contains(" WHERE "))
            retSQL.append(" WHERE ")
        else
            retSQL.append(" AND ")

        // 	Use First Table
        var tableName = ""
        if (ti!!.size > 0) {
            tableName = ti[0].synonym
            if (tableName.length == 0) tableName = ti[0].tableName
        }
        if (TableNameIn != null && tableName != TableNameIn) {
            var msg = "TableName not correctly parsed - TableNameIn=$TableNameIn - $asp"
            if (ti.size > 0) msg += " - #1 " + ti[0]
            msg += "\n = $SQL"
            log.log(Level.SEVERE, msg)
            Trace.printStack()
            tableName = TableNameIn
        }

        if (tableName != X_AD_PInstance_Log.Table_Name) { // globalqss, bug 1662433
            // 	Client Access
            if (fullyQualified) retSQL.append(tableName).append(".")
            retSQL.append(getClientWhere(rw))

            // 	Org Access
            if (!isAccessAllOrgs) {
                retSQL.append(" AND ")
                if (fullyQualified) retSQL.append(tableName).append(".")
                retSQL.append(getOrgWhere(rw))
            }
        } else {
            retSQL.append("1=1")
        }

        // 	** Data Access	**
        for (i in ti.indices) {
            val TableName = ti[i].tableName

            // [ 1644310 ] Rev. 1292 hangs on start
            if (TableName.toUpperCase().endsWith("_TRL")) continue
            if (isView(TableName)) continue

            val AD_Table_ID = getRowTableId(TableName)
            // 	Data Table Access
            if (AD_Table_ID != 0 && !isTableAccess(AD_Table_ID, !rw)) {
                retSQL.append(" AND 1=3") // 	prevent access at all
                if (log.isLoggable(Level.FINE))
                    log.fine("No access to AD_Table_ID=$AD_Table_ID - $TableName - $retSQL")
                break // 	no need to check further
            }

            // 	Data Column Access

            // 	Data Record Access
            var keyColumnName = ""
            if (fullyQualified) {
                keyColumnName = ti[i].synonym // 	table synonym
                if (keyColumnName.length == 0) keyColumnName = TableName
                keyColumnName += "."
            }
            // keyColumnName += TableName + "_ID";	//	derived from table
            if (getIdColumnName(TableName) == null) continue
            keyColumnName += getIdColumnName(TableName)

            // log.fine("addAccessSQL - " + TableName + "(" + AD_Table_ID + ") " + keyColumnName);
            val recordWhere = getRecordWhere(AD_Table_ID, keyColumnName, rw)
            if (recordWhere.length > 0) {
                retSQL.append(" AND ").append(recordWhere)
                if (log.isLoggable(Level.FINEST)) log.finest("Record access - $recordWhere")
            }
        } // 	for all table info

        // 	Dependent Records (only for main SQL)
        val mainSql = asp.mainSql
        loadRecordAccess(false)
        var AD_Table_ID = 0
        var whereColumnName: String? = null
        val includes = ArrayList<Int>()
        val excludes = ArrayList<Int>()
        val m_recordDependentAccess = recordDependentAccess
        for (i in m_recordDependentAccess.indices) {
            val columnName = m_recordDependentAccess[i].getKeyColumnName(asp.getTableInfo(asp.mainSqlIndex) ?: continue ) ?: continue
// 	no key column

            if (mainSql!!.toUpperCase().startsWith("SELECT COUNT(*) FROM ")) {
                // globalqss - Carlos Ruiz - [ 1965744 ] Dependent entities access problem
                // this is the count select, it doesn't have the column but needs to be filtered
                val table = getTable(tableName)
                val column = table.getColumn(columnName)
                if (column == null || column.isVirtualColumn || !column.isActive()) continue
            } else {
                val posColumn = mainSql.indexOf(columnName)
                if (posColumn == -1) continue
                // 	we found the column name - make sure it's a column name
                var charCheck = mainSql[posColumn - 1] // 	before
                if (!(charCheck == ',' || charCheck == '.' || charCheck == ' ' || charCheck == '('))
                    continue
                charCheck = mainSql[posColumn + columnName.length] // 	after
                if (!(charCheck == ',' || charCheck == ' ' || charCheck == ')')) continue
            }

            if (AD_Table_ID != 0 && AD_Table_ID != m_recordDependentAccess[i].recordTableId)
                retSQL.append(getDependentAccess(whereColumnName, includes, excludes))

            AD_Table_ID = m_recordDependentAccess[i].recordTableId
            // 	*** we found the column in the main query
            if (m_recordDependentAccess[i].isExclude) {
                excludes.add(m_recordDependentAccess[i].recordId)
                if (log.isLoggable(Level.FINE))
                    log.fine("Exclude " + columnName + " - " + m_recordDependentAccess[i])
            } else if (!rw || !m_recordDependentAccess[i].isReadOnly) {
                includes.add(m_recordDependentAccess[i].recordId)
                if (log.isLoggable(Level.FINE))
                    log.fine("Include " + columnName + " - " + m_recordDependentAccess[i])
            }
            whereColumnName = getDependentRecordWhereColumn(mainSql, columnName)
        } // 	for all dependent records
        retSQL.append(getDependentAccess(whereColumnName, includes, excludes))
        //
        retSQL.append(orderBy)
        if (log.isLoggable(Level.FINEST)) log.finest(retSQL.toString())
        return retSQL.toString()
    } // 	addAccessSQL

    /**
     * Get Dependent Access
     *
     * @param whereColumnName column
     * @param includes ids to include
     * @param excludes ids to exclude
     * @return where clause starting with AND or ""
     */
    private fun getDependentAccess(
        whereColumnName: String?,
        includes: ArrayList<Int>,
        excludes: ArrayList<Int>
    ): String {
        if (includes.size == 0 && excludes.size == 0) return ""
        if (includes.size != 0 && excludes.size != 0)
            log.warning("Mixing Include and Excluse rules - Will not return values")

        val where = StringBuilder(" AND ")
        if (includes.size == 1)
            where.append(whereColumnName).append("=").append(includes[0])
        else if (includes.size > 1) {
            where.append(whereColumnName).append(" IN (")
            for (ii in includes.indices) {
                if (ii > 0) where.append(",")
                where.append(includes[ii])
            }
            where.append(")")
        } else if (excludes.size == 1) {
            where.append("($whereColumnName IS NULL OR ")
            where.append(whereColumnName).append("<>").append(excludes[0]).append(")")
        } else if (excludes.size > 1) {
            where.append("($whereColumnName IS NULL OR ")
            where.append(whereColumnName).append(" NOT IN (")
            for (ii in excludes.indices) {
                if (ii > 0) where.append(",")
                where.append(excludes[ii])
            }
            where.append("))")
        }
        if (log.isLoggable(Level.FINEST)) log.finest(where.toString())
        return where.toString()
    } // 	getDependentAccess

    /**
     * Get Dependent Record Where clause
     *
     * @param mainSql sql to examine
     * @param columnName columnName
     * @return where clause column "x.columnName"
     */
    private fun getDependentRecordWhereColumn(mainSql: String, columnName: String): String {
        val index = mainSql.indexOf(columnName)
        if (index == -1) return columnName
        // 	see if there are table synonym
        var offset = index - 1
        var c = mainSql[offset]
        if (c == '.') {
            val sb = StringBuilder()
            while (c != ' ' && c != ',' && c != '(')
            // 	delimeter
            {
                sb.insert(0, c)
                c = mainSql[--offset]
            }
            sb.append(columnName)
            return sb.toString()
        }
        return columnName
    } // 	getDependentRecordWhereColumn

    /**
     * Get Table ID from name
     *
     * @param tableName table name
     * @return AD_Table_ID or 0
     */
    private fun getRowTableId(tableName: String): Int {
        loadTableInfo(false)
        val ii = m_tableName[tableName]
        return ii ?: 0
        // 	log.log(Level.WARNING,"getColumnTableId - not found (" + tableName + ")");
    } // 	getColumnTableId

    /**
     * Return Where clause for Record Access
     *
     * @param AD_Table_ID table
     * @param keyColumnName (fully qualified) key column name
     * @param rw true if read write
     * @return where clause or ""
     */
    private fun getRecordWhere(AD_Table_ID: Int, keyColumnName: String, rw: Boolean): String {
        loadRecordAccess(false)
        //
        val sbInclude = StringBuffer()
        val sbExclude = StringBuffer()
        val m_recordAccess = recordAccess
        // 	Role Access
        for (mRecordAccess in m_recordAccess) {
            if (mRecordAccess.recordTableId == AD_Table_ID) {
                // 	NOT IN (x)
                if (mRecordAccess.isExclude) {
                    if (sbExclude.length == 0) {
                        sbExclude.append("(").append(keyColumnName).append(" IS NULL OR ")
                        sbExclude.append(keyColumnName).append(" NOT IN (")
                    } else
                        sbExclude.append(",")
                    sbExclude.append(mRecordAccess.recordId)
                } else if (!rw || !mRecordAccess.isReadOnly)
                // 	include
                {
                    if (sbInclude.length == 0)
                        sbInclude.append(keyColumnName).append(" IN (")
                    else
                        sbInclude.append(",")
                    sbInclude.append(mRecordAccess.recordId)
                } // 	IN (x)
            }
        } // 	for all Table Access

        val sb = StringBuilder()
        if (sbExclude.length > 0) sb.append(sbExclude).append("))")
        if (sbInclude.length > 0) {
            if (sb.length > 0) sb.append(" AND ")
            sb.append(sbInclude).append(")")
        }

        // 	Don't ignore Privacy Access
        if (!isPersonalAccess) {
            val lockedIDs = MPrivateAccess.getLockedRecordWhere(AD_Table_ID, userId)
            if (lockedIDs != null) {
                if (sb.length > 0) sb.append(" AND ")
                sb.append(" ($keyColumnName IS NULL OR ")
                sb.append(keyColumnName).append(lockedIDs).append(") ")
            }
        }
        //
        return sb.toString()
    } // 	getRecordWhere

    /**
     * Include role permissions
     *
     * @param role
     * @param seqNo
     * @see metas-2009_0021_AP1_G94
     */
    private fun includeRole(role: Role, seqNo: Int) {
        if (this.roleId == role.roleId) {
            return
        }
        for (r in this.m_includedRoles) {
            if (r.roleId == role.roleId) {
                return
            }
        }

        if (s_log.isLoggable(Level.INFO)) s_log.info("Include $role")

        if (role.isActive()) {
            this.m_includedRoles.add(role)
            role.setParentRole(this)
            role.setIncludedSeqNo(seqNo)
        }
    }

    /**
     * @return unmodifiable list of included roles
     * @see metas-2009_0021_AP1_G94
     */
    override fun getIncludedRoles(recursive: Boolean): List<Role> {
        if (!recursive) {
            return this.m_includedRoles.toList()
        } else {
            val list = ArrayList<Role>()
            for (role in m_includedRoles) {
                list.add(role)
                list.addAll(role.getIncludedRoles(true))
            }
            return list
        }
    }

    /**
     * Load all included roles (direct inclusion or from user substitution)
     *
     * @param reload
     * @see metas-2009_0021_AP1_G94
     */
    private fun loadIncludedRoles(reload: Boolean) {
        loadChildRoles()
        loadSubstitutedRoles()
        //
        if (this.m_parent == null) {
            mergeAccesses(reload)
        }
    }

    override fun mergeAccesses(reload: Boolean) {
        var orgAccess = listOf<OrganizationAccessSummary>()
        var tableAccess = listOf<TableAccess>()
        var columnAccess = listOf<ColumnAccess>()
        var recordAccess = listOf<RecordAccess>()
        var recordDependentAccess = listOf<RecordAccess>()
        //
        var last_role: Role? = null
        for (role in getIncludedRoles(false)) {
            var override = false
            //
            // If roles have same SeqNo, then, the second role will override permissions from first role
            if (last_role != null &&
                last_role.includedSeqNo >= 0 &&
                role.includedSeqNo >= 0 &&
                last_role.includedSeqNo == role.includedSeqNo
            ) {
                override = true
            }
            //
            role.loadAccess(reload)
            role.mergeAccesses(reload)
            orgAccess = mergeAccess<OrganizationAccessSummary>(orgAccess, role.orgAccess, override)
            tableAccess = mergeAccess<TableAccess>(tableAccess, role.loadTableAccess(false), override)
            columnAccess = mergeAccess<ColumnAccess>(columnAccess, role.loadColumnAccess(false), override)
            recordAccess = mergeAccess<RecordAccess>(recordAccess, role.getRecordAccessArray(), override)
            recordDependentAccess =
                mergeAccess<RecordAccess>(recordDependentAccess, role.getRecordDependentAccessArray(), override)
            //
            last_role = role
        }
        //
        // Merge permissions inside this role
        this.m_orgAccess.clear()
        this.m_orgAccess.addAll( mergeAccess<OrganizationAccessSummary>(this.m_orgAccess, orgAccess, false) )
        this.setTableAccess(mergeAccess<TableAccess>(this.loadTableAccess(false), tableAccess, false))
        this.setColumnAccess(mergeAccess<ColumnAccess>(this.loadColumnAccess(false), columnAccess, false))
        this.recordAccessArray = mergeAccess<RecordAccess>(this.recordAccessArray, recordAccess, false)
        this.setRecordDependentAccessArray(
            mergeAccess<RecordAccess>(this.getRecordDependentAccessArray(), recordDependentAccess, false)
        )
    }

    /**
     * Load Child Roles
     *
     * @see metas-2009_0021_AP1_G94
     */
    private fun loadChildRoles() {
        m_includedRoles.clear() // reset included roles
        val AD_User_ID = userId
        if (AD_User_ID < 0) {
            // throw new IllegalStateException("AD_User_ID is not set");
            return
        }
        //
        val whereClause = X_AD_Role_Included.COLUMNNAME_AD_Role_ID + "=?"
        val list = Query<RoleIncluded>(X_AD_Role_Included.Table_Name, whereClause)
            .setParameters(roleId)
            .setOnlyActiveRecords(true)
            .setOrderBy(
                X_AD_Role_Included.COLUMNNAME_SeqNo +
                        "," +
                        X_AD_Role_Included.COLUMNNAME_Included_Role_ID
            )
            .list()
        for (includedRole in list) {
            val role = getRole(includedRole.includedRoleId)
            includeRole(role, includedRole.seqNo)
        }
    }

    /**
     * Load substituted roles
     *
     * @see metas-2009_0021_AP1_G94
     */
    private fun loadSubstitutedRoles() {
        if (this.m_parent != null) {
            // load only if this is logged role (no parent roles)
            return
        }
        //
        val AD_User_ID = userId
        if (AD_User_ID < 0) {
            // throw new IllegalStateException("AD_User_ID is not set");
            return
        }
        //
        val whereClause = ("EXISTS (" +
                " SELECT 1 FROM AD_User_Roles ur" +
                " INNER JOIN AD_User_Substitute us ON (us.AD_User_ID=ur.AD_User_ID)" +
                " WHERE ur.AD_Role_ID=AD_Role.AD_Role_ID AND ur.IsActive='Y' AND us.IsActive='Y'" +
                " AND (us.ValidFrom IS NULL OR us.ValidFrom <= SYSDATE)" +
                " AND (us.ValidTo IS NULL OR us.ValidTo >= SYSDATE)" +
                " AND us.Substitute_ID=?)")

        val list = Query<Role>(Role.Table_Name, whereClause)
            .setParameters(AD_User_ID)
            .setClientId()
            .setOrderBy(Role.COLUMNNAME_AD_Role_ID)
            .list()
        for (role in list) {
            includeRole(role, -1)
        }
    }

    /**
     * Set parent role. This method is called when this role is included in a parent role.
     *
     * @param parent
     * @see metas-2009_0021_AP1_G94
     */
    override fun setParentRole(parent: Role) {
        if (parent is MRole) this.userId = parent.userId
        this.m_parent = parent
    }

    private fun mergeIncludedAccess(varname: String) {
        var includedAccess = HashMap<Int, Boolean>()
        var last_role: Role? = null
        for (role in getIncludedRoles(false)) {
            var override = false
            //
            // If roles have same SeqNo, then, the second role will override permissions from first role
            if (last_role != null &&
                last_role.includedSeqNo >= 0 &&
                role.includedSeqNo >= 0 &&
                last_role.includedSeqNo == role.includedSeqNo
            ) {
                override = true
            }
            includedAccess = mergeAccess(includedAccess, role.getAccessMap(varname), override)
            last_role = role
        }
        setAccessMap(varname, mergeAccess(getAccessMap(varname), includedAccess, false))
    }

    override fun getAccessMap(varname: String): HashMap<Int, Boolean>? {
        if ("m_windowAccess" == varname) {
            getWindowAccess(-1)
            return m_windowAccess
        } else if ("m_processAccess" == varname) {
            getProcessAccess(-1)
            return m_processAccess
        } else if ("m_taskAccess" == varname) {
            getTaskAccess(-1)
            return m_taskAccess
        } else if ("m_workflowAccess" == varname) {
            getWorkflowAccess(-1)
            return m_workflowAccess
        } else if ("m_formAccess" == varname) {
            getFormAccess(-1)
            return m_formAccess
        } else if ("m_infoAccess" == varname) {
            getInfoAccess(-1)
            return m_infoAccess
        } else {
            throw IllegalArgumentException("varname not supported - $varname")
        }
    }

    private fun setAccessMap(varname: String, map: HashMap<Int, Boolean>) {
        if ("m_windowAccess" == varname) {
            m_windowAccess = map
        } else if ("m_processAccess" == varname) {
            m_processAccess = map
        } else if ("m_taskAccess" == varname) {
            m_taskAccess = map
        } else if ("m_workflowAccess" == varname) {
            m_workflowAccess = map
        } else if ("m_formAccess" == varname) {
            m_formAccess = map
        } else if ("m_infoAccess" == varname) {
            m_infoAccess = map
        } else {
            throw IllegalArgumentException("varname not supported - $varname")
        }
    }

    @Synchronized
    fun getInfoAccess(AD_InfoWindow_ID: Int): Boolean? {
        if (m_infoAccess == null) {
            m_infoAccess = HashMap(20)
            // first get the info access from the included and substitute roles
            mergeIncludedAccess("m_infoAccess")
            // and now get the info access directly from this role
            val ASPFilter = ""

            val sql = "SELECT AD_InfoWindow_ID, IsActive FROM AD_InfoWindow_Access WHERE AD_Role_ID=?$ASPFilter"
            val pstmt: PreparedStatement?
            val rs: ResultSet?
            val directAccess = HashMap<Int, Boolean>(100)
            try {
                pstmt = prepareStatement(sql)
                pstmt!!.setInt(1, roleId)
                rs = pstmt.executeQuery()
                while (rs!!.next()) {
                    val infoId = rs.getInt(1)
                    if ("N" == rs.getString(2)) {
                        // inactive info on direct access
                        m_infoAccess!!.remove(infoId)
                    } else {
                        directAccess[infoId] = java.lang.Boolean.TRUE
                    }
                }
            } catch (e: Exception) {
                log.log(Level.SEVERE, sql, e)
            }

            setAccessMap("m_infoAccess", mergeAccess(getAccessMap("m_infoAccess"), directAccess, true))
        } // 	reload
        return m_infoAccess!![AD_InfoWindow_ID]
    }

    companion object {
        /**
         * Access SQL Read Only
         */
        const val SQL_RO = false
        /**
         * Access SQL Fully Qualified
         */
        const val SQL_FULLYQUALIFIED = true
        /**
         * Access SQL Not Fully Qualified
         */
        const val SQL_NOTQUALIFIED = false
        /**
         * The AD_User_ID of the SuperUser
         */
        const val SUPERUSER_USER_ID = USER_SUPERUSER
        private val serialVersionUID = 8952907008982481439L

        /**
         * System = S
         */
        const val USERLEVEL_System = "S  "
        /**
         * Client = C
         */
        const val USERLEVEL_Client = " C "
        /**
         * Organization = O
         */
        const val USERLEVEL_Organization = "  O"
        /**
         * Client+Organization = CO
         */
        const val USERLEVEL_ClientPlusOrganization = " CO"

        /**
         * Client = C
         */
        const val PREFERENCETYPE_Client = "C"
        /**
         * Organization = O
         */
        const val PREFERENCETYPE_Organization = "O"

        /**
         * Merge permissions access
         *
         * @param <T>
         * @param array1
         * @param array2
         * @return array of merged values
         * @see metas-2009_0021_AP1_G94
        </T> */
        private fun <T> mergeAccess(array1: List<T>?, array2: List<T>?, override: Boolean): List<T> {
            if (array1 == null) {
                s_log.info("array1 null !!!")
            }
            val list = ArrayList<T>(array1)
            for (o2 in array2!!) {
                var found = false
                for (o1 in array1!!) {
                    if (o1 is OrgAccess) {
                        val oa1 = o1 as OrgAccess
                        val oa2 = o2 as OrgAccess
                        found = oa1 == oa2
                        if (found && override) {
                            // stronger permissions first
                            if (!oa2.readOnly) oa1.readOnly = false
                        }
                    } else if (o1 is MTableAccess) {
                        val ta1 = o1 as MTableAccess
                        val ta2 = o2 as MTableAccess
                        found = ta1.accessTableId == ta2.accessTableId
                        if (found && override) {
                            // stronger permissions first
                            if (!ta2.isExclude) ta1.setIsExclude(false)
                        }
                    } else if (o1 is MColumnAccess) {
                        val ca1 = o1 as MColumnAccess
                        val ca2 = o2 as MColumnAccess
                        found = ca1.columnId == ca2.columnId
                        if (found && override) {
                            // stronger permissions first
                            if (!ca2.isReadOnly) ca1.setIsReadOnly(false)
                            if (!ca2.isExclude) ca1.setIsExclude(false)
                        }
                    } else if (o1 is MRecordAccess) {
                        val ra1 = o1 as MRecordAccess
                        val ra2 = o2 as MRecordAccess
                        found = ra1.recordTableId == ra2.recordTableId && ra1.recordId == ra2.recordId
                        if (found && override) {
                            // stronger permissions first
                            if (!ra2.isReadOnly) ra1.setIsReadOnly(false)
                            if (!ra2.isDependentEntities) ra1.setIsDependentEntities(false)
                            if (!ra2.isExclude) ra1.setIsExclude(false)
                        }
                    } else {
                        throw AdempiereException("Not supported objects - $o1, $o2")
                    }
                    //
                    if (found) {
                        break
                    }
                } // end for array1
                if (!found) {
                    // s_log.info("add "+o2);
                    list.add(o2)
                }
            }
            return list
        }

        private fun mergeAccess(
            map1: HashMap<Int, Boolean>?,
            map2: HashMap<Int, Boolean>?,
            override: Boolean
        ): HashMap<Int, Boolean> {
            val map = HashMap<Int, Boolean>()
            if (map1 != null) {
                map.putAll(map1)
            }
            //
            for ((key, value) in map2!!) {
                val b2 = value
                val b1 = map[key]
                if (b1 == null) {
                    map[key] = b2
                } else {
                    if (override && b2 && !b1) {
                        map[key] = b2
                    }
                }
            }
            //
            return map
        }
    }
}
