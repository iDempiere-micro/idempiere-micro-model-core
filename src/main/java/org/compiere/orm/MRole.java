package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Role;
import org.compiere.util.Msg;
import org.idempiere.common.exceptions.AdempiereException;
import org.idempiere.common.util.CCache;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.common.util.Trace;
import org.idempiere.icommon.model.IPO;
import software.hsharp.core.orm.MBaseRole;
import software.hsharp.core.orm.MBaseRoleKt;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import static org.compiere.util.SystemIDs.USER_SUPERUSER;
import static software.hsharp.core.util.DBKt.executeUpdateEx;
import static software.hsharp.core.util.DBKt.prepareStatement;

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
public class MRole extends MBaseRole {
    /**
     * Access SQL Read Only
     */
    public static final boolean SQL_RO = false;
    /**
     * Access SQL Fully Qualified
     */
    public static final boolean SQL_FULLYQUALIFIED = true;
    /**
     * Access SQL Not Fully Qualified
     */
    public static final boolean SQL_NOTQUALIFIED = false;
    /**
     * The AD_User_ID of the SuperUser
     */
    public static final int SUPERUSER_USER_ID = USER_SUPERUSER;
    /**
     *
     */
    private static final long serialVersionUID = 8952907008982481439L;

    private static final String ROLE_KEY = "org.compiere.impl.DefaultRole";
    /**
     * Role/User Cache
     */
    private static CCache<String, MRole> s_roles = new CCache<String, MRole>(I_AD_Role.Table_Name, 5);
    /**
     * Log
     */
    private static CLogger s_log = CLogger.getCLogger(MRole.class);
    /**
     * User
     */
    private int m_AD_User_ID = -1;
    /**
     * Positive List of Organizational Access
     */
    private OrgAccess[] m_orgAccess = null;
    /**
     * List of Record Access
     */
    private MRecordAccess[] m_recordAccess = null;
    /**
     * List of Dependent Record Access
     */
    private MRecordAccess[] m_recordDependentAccess = null;
    /**
     * Window Access
     */
    private HashMap<Integer, Boolean> m_windowAccess = null;
    /**
     * Process Access
     */
    private HashMap<Integer, Boolean> m_processAccess = null;
    /**
     * Task Access
     */
    private HashMap<Integer, Boolean> m_taskAccess = null;
    /**
     * Workflow Access
     */
    private HashMap<Integer, Boolean> m_workflowAccess = null;
    /**
     * Form Access
     */
    private HashMap<Integer, Boolean> m_formAccess = null;
    /**
     * Info Windows
     */
    private HashMap<Integer, Boolean> m_infoAccess;
    /**
     * List of included roles. Do not access directly
     */
    private List<MRole> m_includedRoles = null;
    /**
     * Parent Role
     */
    private MRole m_parent = null;

    private int m_includedSeqNo = -1;

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ctx        context
     * @param AD_Role_ID id
     * @param trxName    transaction
     */
    public MRole(Properties ctx, int AD_Role_ID) {
        super(ctx, AD_Role_ID);
        //	ID=0 == System Administrator
        if (AD_Role_ID == 0) {
            //	setName (null);
            setIsCanExport(true);
            setIsCanReport(true);
            setIsManual(false);
            setIsPersonalAccess(false);
            setIsPersonalLock(false);
            setIsShowAcct(false);
            setIsAccessAllOrgs(false);
            setUserLevel(X_AD_Role.USERLEVEL_Organization);
            setPreferenceType(X_AD_Role.PREFERENCETYPE_Organization);
            setIsChangeLog(false);
            setOverwritePriceLimit(false);
            setIsUseUserOrgAccess(false);
            setMaxQueryRecords(0);
            setConfirmQueryRecords(0);
        }
    } //	MRole

    /**
     * *********************************************************************** Access Management
     * **********************************************************************
     */

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MRole(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    } //	MRole

    public MRole(Properties ctx, Row row) {
        super(ctx, row);
    } //	MRole

    /**
     * Get Default (Client) Role
     *
     * @return role
     */
    public static MRole getDefault() {
        return getDefault(Env.getCtx(), false);
    } //	getDefault

    /**
     * Get/Set Default Role.
     *
     * @param ctx    context
     * @param reload if true forces load
     * @return role
     * @see Login#loadPreferences(KeyNamePair, KeyNamePair, java.sql.Timestamp, String)
     */
    public static MRole getDefault(Properties ctx, boolean reload) {
        int AD_Role_ID = Env.getContextAsInt(ctx, "#AD_Role_ID");
        int AD_User_ID = Env.getContextAsInt(ctx, "#AD_User_ID");
        //		if (!Ini.isClient())	//	none for Server
        //			AD_User_ID = 0;
        MRole defaultRole = getDefaultRole();
        if (reload || defaultRole == null) {
            defaultRole = get(ctx, AD_Role_ID, AD_User_ID, reload);
            setDefaultRole(defaultRole);
        } else if (defaultRole.getRoleId() != AD_Role_ID
                || defaultRole.getUserId() != AD_User_ID) {
            defaultRole = get(ctx, AD_Role_ID, AD_User_ID, reload);
            setDefaultRole(defaultRole);
        }
        return defaultRole;
    } //	getDefault

    private static MRole getDefaultRole() {
        return (MRole) Env.getCtx().get(ROLE_KEY);
    }

    private static void setDefaultRole(MRole defaultRole) {
        Env.getCtx().remove(ROLE_KEY);
        Env.getCtx().put(ROLE_KEY, defaultRole);
    }

    /**
     * Get Role for User
     *
     * @param ctx        context
     * @param AD_Role_ID role
     * @param AD_User_ID user
     * @param reload     if true forces load
     * @return role
     */
    public static synchronized MRole get(
            Properties ctx, int AD_Role_ID, int AD_User_ID, boolean reload) {
        if (s_log.isLoggable(Level.INFO))
            s_log.info("AD_Role_ID=" + AD_Role_ID + ", AD_User_ID=" + AD_User_ID + ", reload=" + reload);
        String key = AD_Role_ID + "_" + AD_User_ID;
        MRole role = s_roles.get(key);
        if (role == null || reload) {
            role = new MRole(ctx, AD_Role_ID);
            s_roles.put(key, role);
            if (AD_Role_ID == 0) {
                String trxName = null;
                role.load(); // 	special Handling
            }
            role.setUserId(AD_User_ID);
            role.loadAccess(reload);
            if (s_log.isLoggable(Level.INFO)) s_log.info(role.toString());
        }
        return role;
    } //	get

    /**
     * Get Role (cached). Did not set user - so no access loaded
     *
     * @param ctx        context
     * @param AD_Role_ID role
     * @return role
     */
    public static MRole get(Properties ctx, int AD_Role_ID) {
        return get(
                ctx,
                AD_Role_ID,
                Env.getUserId(ctx),
                false); // metas-2009_0021_AP1_G94 - we need to use this method because we need to
        // load/reload all accesses
    /* metas-2009_0021_AP1_G94
    String key = String.valueOf(AD_Role_ID);
    MRole role = (MRole)s_roles.get (key);
    String trxName = null;
    if (role == null)
    {
    	role = new MRole (ctx, AD_Role_ID);
    	s_roles.put (key, role);
    	if (AD_Role_ID == 0)	//	System Role
    	{
    		role.load(trxName);	//	special Handling
    	}
    }
    return role;
    /**/
        // metas-2009_0021_AP1_G94
    } //	get

    /**
     * Get Roles Of Client
     *
     * @param ctx context
     * @return roles of client
     */
    public static MRole[] getOfClient(Properties ctx) {
        return MBaseRoleKt.getOfClient(ctx);
    } //	getOfClient

    /**
     * Get Roles With where clause
     *
     * @param ctx         context
     * @param whereClause where clause
     * @return roles of client
     */
    public static MRole[] getOf(Properties ctx, String whereClause) {
        String sql = "SELECT * FROM AD_Role";
        if (whereClause != null && whereClause.length() > 0) sql += " WHERE " + whereClause;
        ArrayList<MRole> list = new ArrayList<MRole>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) list.add(new MRole(ctx, rs));
        } catch (Exception e) {
            s_log.log(Level.SEVERE, sql, e);
        } finally {
            rs = null;
            pstmt = null;
        }
        MRole[] retValue = new MRole[list.size()];
        list.toArray(retValue);
        return retValue;
    } //	getOf

    /**
     * Merge permissions access
     *
     * @param <T>
     * @param array1
     * @param array2
     * @return array of merged values
     * @see metas-2009_0021_AP1_G94
     */
    @SuppressWarnings("unchecked")
    private static final <T> T[] mergeAccess(T[] array1, T[] array2, boolean override) {
        if (array1 == null) {
            s_log.info("array1 null !!!");
        }
        List<T> list = new ArrayList<T>();
        for (T po : array1) {
            list.add(po);
        }
        for (T o2 : array2) {
            boolean found = false;
            for (int i = 0; i < array1.length; i++) {
                final T o1 = array1[i];
                if (o1 instanceof OrgAccess) {
                    final OrgAccess oa1 = (OrgAccess) o1;
                    final OrgAccess oa2 = (OrgAccess) o2;
                    found = oa1.equals(oa2);
                    if (found && override) {
                        // stronger permissions first
                        if (!oa2.getReadOnly()) oa1.setReadOnly(false);
                    }
                } else if (o1 instanceof MTableAccess) {
                    final MTableAccess ta1 = (MTableAccess) o1;
                    final MTableAccess ta2 = (MTableAccess) o2;
                    found = ta1.getAccessTableId() == ta2.getAccessTableId();
                    if (found && override) {
                        // stronger permissions first
                        if (!ta2.isExclude()) ta1.setIsExclude(false);
                    }
                } else if (o1 instanceof MColumnAccess) {
                    final MColumnAccess ca1 = (MColumnAccess) o1;
                    final MColumnAccess ca2 = (MColumnAccess) o2;
                    found = ca1.getColumnId() == ca2.getColumnId();
                    if (found && override) {
                        // stronger permissions first
                        if (!ca2.isReadOnly()) ca1.setIsReadOnly(false);
                        if (!ca2.isExclude()) ca1.setIsExclude(false);
                    }
                } else if (o1 instanceof MRecordAccess) {
                    final MRecordAccess ra1 = (MRecordAccess) o1;
                    final MRecordAccess ra2 = (MRecordAccess) o2;
                    found =
                            ra1.getRecordTableId() == ra2.getRecordTableId()
                                    && ra1.getRecordId() == ra2.getRecordId();
                    if (found && override) {
                        // stronger permissions first
                        if (!ra2.isReadOnly()) ra1.setIsReadOnly(false);
                        if (!ra2.isDependentEntities()) ra1.setIsDependentEntities(false);
                        if (!ra2.isExclude()) ra1.setIsExclude(false);
                    }
                } else {
                    throw new AdempiereException("Not supported objects - " + o1 + ", " + o2);
                }
                //
                if (found) {
                    break;
                }
            } // end for array1
            if (!found) {
                // s_log.info("add "+o2);
                list.add(o2);
            }
        }
        T[] arr = (T[]) Array.newInstance(array1.getClass().getComponentType(), list.size());
        return list.toArray(arr);
    }

    private static final HashMap<Integer, Boolean> mergeAccess(
            HashMap<Integer, Boolean> map1, HashMap<Integer, Boolean> map2, boolean override) {
        final HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        if (map1 != null) {
            map.putAll(map1);
        }
        //
        for (final Entry<Integer, Boolean> e : map2.entrySet()) {
            final Integer key = e.getKey();
            final Boolean b2 = e.getValue();
            if (b2 == null) {
                continue;
            }
            final Boolean b1 = map.get(key);
            if (b1 == null) {
                map.put(key, b2);
            } else {
                if (override && b2 == true && b1 == false) {
                    map.put(key, b2);
                }
            }
        }
        //
        return map;
    }

    /**
     * Get Confirm Query Records
     *
     * @return entered records or 500 (default)
     */
    public int getConfirmQueryRecords() {
        int no = super.getConfirmQueryRecords();
        if (no == 0) return 500;
        return no;
    } //	getConfirmQueryRecords

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true if it can be saved
     */
    protected boolean beforeSave(boolean newRecord) {
        //	if (newRecord || is_ValueChanged("UserLevel"))
        //	{
        if (getClientId() == 0) setUserLevel(X_AD_Role.USERLEVEL_System);
        else if (getUserLevel().equals(X_AD_Role.USERLEVEL_System)) {
            log.saveError("AccessTableNoUpdate", Msg.getElement(getCtx(), "UserLevel"));
            return false;
        }
        //	}
        return true;
    } //	beforeSave

    /**
     * After Save
     *
     * @param newRecord new
     * @param success   success
     * @return success
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        if (!success) return success;
        if (newRecord && success) {
            //	Add Role to SuperUser
            MUserRoles su = new MUserRoles(getCtx(), SUPERUSER_USER_ID, getRoleId());
            su.saveEx();
            //	Add Role to User
            if (getCreatedBy() != SUPERUSER_USER_ID) {
                MUserRoles ur = new MUserRoles(getCtx(), getCreatedBy(), getRoleId());
                ur.saveEx();
            }
            updateAccessRecords();
        }
        //
        else if (is_ValueChanged("UserLevel")) updateAccessRecords();

        //	Default Role changed
        if (getDefaultRole() != null && getDefaultRole().getId() == getId()) setDefaultRole(this);
        return success;
    } //	afterSave

    /**
     * Executed after Delete operation.
     *
     * @param success true if record deleted
     * @return true if delete is a success
     */
    protected boolean afterDelete(boolean success) {
        if (success) {
            deleteAccessRecords();
        }
        return success;
    } //	afterDelete

    /**
     * Create Access Records
     *
     * @return info
     */
    public String updateAccessRecords() {
        return updateAccessRecords(true);
    }

    /**
     * Create Access Records
     *
     * @param reset true will reset existing access
     * @return info
     */
    public String updateAccessRecords(boolean reset) {
        if (isManual()) return "-";

        String roleClientOrgUser =
                getRoleId()
                        + ","
                        + getClientId()
                        + ","
                        + getOrgId()
                        + ",'Y', SysDate,"
                        + getUpdatedBy()
                        + ", SysDate,"
                        + getUpdatedBy()
                        + ",'Y' "; //	IsReadWrite

        String sqlWindow =
                "INSERT INTO AD_Window_Access "
                        + "(AD_Window_ID, AD_Role_ID,"
                        + " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
                        + "SELECT DISTINCT w.AD_Window_ID, "
                        + roleClientOrgUser
                        + "FROM AD_Window w"
                        + " INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID)"
                        + " INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) "
                        + " LEFT JOIN AD_Window_Access wa ON "
                        + "(wa.AD_Role_ID="
                        + getRoleId()
                        + " AND w.AD_Window_ID = wa.AD_Window_ID) "
                        + "WHERE wa.AD_Window_ID IS NULL AND t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt " // only
                        // check first tab
                        + "WHERE xt.AD_Window_ID=w.AD_Window_ID)"
                        + "AND tt.AccessLevel IN ";

        String sqlProcess =
                "INSERT INTO AD_Process_Access "
                        + "(AD_Process_ID, AD_Role_ID,"
                        + " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, IsReadWrite) "
                        + "SELECT DISTINCT p.AD_Process_ID, "
                        + roleClientOrgUser
                        + "FROM AD_Process p LEFT JOIN AD_Process_Access pa ON "
                        + "(pa.AD_Role_ID="
                        + getRoleId()
                        + " AND p.AD_Process_ID = pa.AD_Process_ID) "
                        + "WHERE pa.AD_Process_ID IS NULL AND AccessLevel IN ";

        String sqlForm =
                "INSERT INTO AD_Form_Access "
                        + "(AD_Form_ID, AD_Role_ID,"
                        + " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
                        + "SELECT f.AD_Form_ID, "
                        + roleClientOrgUser
                        + "FROM AD_Form f LEFT JOIN AD_Form_Access fa ON "
                        + "(fa.AD_Role_ID="
                        + getRoleId()
                        + " AND f.AD_Form_ID = fa.AD_Form_ID) "
                        + "WHERE fa.AD_Form_ID IS NULL AND AccessLevel IN ";

        String sqlWorkflow =
                "INSERT INTO AD_WorkFlow_Access "
                        + "(AD_WorkFlow_ID, AD_Role_ID,"
                        + " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
                        + "SELECT w.AD_WorkFlow_ID, "
                        + roleClientOrgUser
                        + "FROM AD_WorkFlow w LEFT JOIN AD_WorkFlow_Access wa ON "
                        + "(wa.AD_Role_ID="
                        + getRoleId()
                        + " AND w.AD_WorkFlow_ID = wa.AD_WorkFlow_ID) "
                        + "WHERE w.AD_Client_ID IN (0,"
                        + getClientId()
                        + ") AND wa.AD_WorkFlow_ID IS NULL AND AccessLevel IN ";

        String sqlDocAction =
                "INSERT INTO AD_Document_Action_Access "
                        + "(AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
                        + "C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) "
                        + "(SELECT "
                        + getClientId()
                        + ",0,'Y', SysDate,"
                        + getUpdatedBy()
                        + ", SysDate,"
                        + getUpdatedBy()
                        + ", doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID "
                        + "FROM AD_Client client "
                        + "INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) "
                        + "INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) "
                        + "INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID "
                        + "AND rol.AD_Role_ID="
                        + getRoleId()
                        + ") LEFT JOIN AD_Document_Action_Access da ON "
                        + "(da.AD_Role_ID="
                        + getRoleId()
                        + " AND da.C_DocType_ID=doctype.C_DocType_ID AND da.AD_Ref_List_ID=action.AD_Ref_List_ID) "
                        + "WHERE (da.C_DocType_ID IS NULL AND da.AD_Ref_List_ID IS NULL)) ";

        String sqlInfo =
                "INSERT INTO AD_InfoWindow_Access "
                        + "(AD_InfoWindow_ID, AD_Role_ID,"
                        + " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy) "
                        + "SELECT i.AD_InfoWindow_ID,"
                        + getRoleId()
                        + ","
                        + getClientId()
                        + ","
                        + getOrgId()
                        + ",'Y',SysDate,"
                        + getUpdatedBy()
                        + ", SysDate,"
                        + getUpdatedBy()
                        + " FROM AD_InfoWindow i LEFT JOIN AD_InfoWindow_Access ia ON "
                        + "(ia.AD_Role_ID="
                        + getRoleId()
                        + " AND i.AD_InfoWindow_ID = ia.AD_InfoWindow_ID) "
                        + "WHERE i.AD_Client_ID IN (0,"
                        + getClientId()
                        + ") AND ia.AD_InfoWindow_ID IS NULL";

        /**
         * Fill AD_xx_Access ---------------------------------------------------------------------------
         * SCO# Levels S__ 100 4 System info SCO 111 7 System shared info SC_ 110 6 System/Client info
         * _CO 011 3 Client shared info _C_ 011 2 Client __O 001 1 Organization info Roles: S 4,7,6 _CO
         * 7,6,3,2,1 __O 3,1,7
         */
        String roleAccessLevel = null;
        String roleAccessLevelWin = null;
        if (X_AD_Role.USERLEVEL_System.equals(getUserLevel())) roleAccessLevel = "('4','7','6')";
        else if (X_AD_Role.USERLEVEL_Client.equals(getUserLevel()))
            roleAccessLevel = "('7','6','3','2')";
        else if (X_AD_Role.USERLEVEL_ClientPlusOrganization.equals(getUserLevel()))
            roleAccessLevel = "('7','6','3','2','1')";
        else //	if (USERLEVEL_Organization.equals(getUserLevel()))
        {
            roleAccessLevel = "('3','1','7')";
            roleAccessLevelWin = roleAccessLevel + " AND w.Name NOT LIKE '%(all)%'";
        }
        if (roleAccessLevelWin == null) roleAccessLevelWin = roleAccessLevel;

        if (reset) deleteAccessRecords();

        int win = executeUpdateEx(sqlWindow + roleAccessLevelWin);
        int proc = executeUpdateEx(sqlProcess + roleAccessLevel);
        int form = executeUpdateEx(sqlForm + roleAccessLevel);
        int wf = executeUpdateEx(sqlWorkflow + roleAccessLevel);
        int docact = executeUpdateEx(sqlDocAction);
        int info = executeUpdateEx(sqlInfo);

        loadAccess(true);
        return "@AD_Window_ID@ #"
                + win
                + " -  @AD_Process_ID@ #"
                + proc
                + " -  @AD_Form_ID@ #"
                + form
                + " -  @AD_Workflow_ID@ #"
                + wf
                + " -  @DocAction@ #"
                + docact
                + " -  @AD_InfoWindow_ID@ #"
                + info;
    } //	createAccessRecords

    /**
     * Delete Access Records of the role after the role was (successfully) deleted.
     */
    private void deleteAccessRecords() {
        String whereDel = " WHERE AD_Role_ID=" + getRoleId();
        //
        int winDel = executeUpdateEx("DELETE FROM AD_Window_Access" + whereDel);
        int procDel = executeUpdateEx("DELETE FROM AD_Process_Access" + whereDel);
        int formDel = executeUpdateEx("DELETE FROM AD_Form_Access" + whereDel);
        int wfDel = executeUpdateEx("DELETE FROM AD_WorkFlow_Access" + whereDel);
        int docactDel = executeUpdateEx("DELETE FROM AD_Document_Action_Access" + whereDel);
        int infoDel = executeUpdateEx("DELETE FROM AD_InfoWindow_Access" + whereDel);

        if (log.isLoggable(Level.FINE))
            log.fine(
                    "AD_Window_Access="
                            + winDel
                            + ", AD_Process_Access="
                            + procDel
                            + ", AD_Form_Access="
                            + formDel
                            + ", AD_Workflow_Access="
                            + wfDel
                            + ", AD_Document_Action_Access="
                            + docactDel
                            + ", AD_InfoWindow_Access="
                            + infoDel);
    }

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("MRole[");
        sb.append(getRoleId())
                .append(",")
                .append(getName())
                .append(",UserLevel=")
                .append(getUserLevel())
                .append(",")
                .append(getClientWhere(false))
                .append(",")
                .append(getOrgWhere(false))
                .append("]");
        return sb.toString();
    } //	toString

    /**
     * Get Logged in user
     *
     * @return AD_User_ID user requesting info
     */
    public int getUserId() {
        return m_AD_User_ID;
    } //	getUserId

    /**
     * Set Logged in user
     *
     * @param AD_User_ID user requesting info
     */
    public void setUserId(int AD_User_ID) {
        m_AD_User_ID = AD_User_ID;
    } //	setUserId

    /**
     * ************************************************************************ Load Access Info
     *
     * @param reload re-load from disk
     */
    public void loadAccess(boolean reload) {
        loadOrgAccess(reload);
        loadTableAccess(reload);
        loadTableInfo(reload);
        loadColumnAccess(reload);
        loadRecordAccess(reload);
        if (reload) {
            m_windowAccess = null;
            m_processAccess = null;
            m_taskAccess = null;
            m_workflowAccess = null;
            m_formAccess = null;
        }
        loadIncludedRoles(reload); // Load/Reload included roles - metas-2009_0021_AP1_G94
    } //	loadAccess

    /**
     * Load Org Access
     *
     * @param reload reload
     */
    private void loadOrgAccess(boolean reload) {
        if (!(reload || m_orgAccess == null)) return;
        //
        ArrayList<OrgAccess> list = new ArrayList<OrgAccess>();

        if (isUseUserOrgAccess()) loadOrgAccessUser(list);
        else loadOrgAccessRole(list);

        m_orgAccess = new OrgAccess[list.size()];
        list.toArray(m_orgAccess);
        if (log.isLoggable(Level.FINE))
            log.fine("#" + m_orgAccess.length + (reload ? " - reload" : ""));
    } //	loadOrgAccess

    /**
     * Load Org Access User
     *
     * @param list list
     */
    private void loadOrgAccessUser(ArrayList<OrgAccess> list) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM AD_User_OrgAccess " + "WHERE AD_User_ID=? AND IsActive='Y'";
        try {
            pstmt = prepareStatement(sql);
            pstmt.setInt(1, getUserId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MUserOrgAccess oa = new MUserOrgAccess(getCtx(), rs);
                loadOrgAccessAdd(list, new OrgAccess(oa.getClientId(), oa.getOrgId(), oa.isReadOnly()));
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, sql, e);
        } finally {
        }
    } //	loadOrgAccessRole

    /**
     * Check if tableName is a view
     *
     * @param tableName
     * @return boolean
     */
    private boolean isView(String tableName) {
        Set<String> m_viewName = getM_viewName();
        if (m_viewName == null) loadAccess(true);
        return m_viewName.contains(tableName.toUpperCase());
    }

    private String getIdColumnName(String tableName) {
        return getM_tableIdName().get(tableName.toUpperCase());
    }

    /**
     * Load Record Access
     *
     * @param reload reload
     */
    private void loadRecordAccess(boolean reload) {
        if (!(reload || m_recordAccess == null || m_recordDependentAccess == null)) return;
        ArrayList<MRecordAccess> list = new ArrayList<MRecordAccess>();
        ArrayList<MRecordAccess> dependent = new ArrayList<MRecordAccess>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql =
                "SELECT * FROM AD_Record_Access "
                        + "WHERE AD_Role_ID=? AND IsActive='Y' ORDER BY AD_Table_ID";
        try {
            pstmt = prepareStatement(sql);
            pstmt.setInt(1, getRoleId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MRecordAccess ra = new MRecordAccess(getCtx(), rs);
                list.add(ra);
                if (ra.isDependentEntities()) dependent.add(ra);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, sql, e);
        } finally {
        }
        m_recordAccess = new MRecordAccess[list.size()];
        list.toArray(m_recordAccess);
        m_recordDependentAccess = new MRecordAccess[dependent.size()];
        dependent.toArray(m_recordDependentAccess);
        if (log.isLoggable(Level.FINE))
            log.fine("#" + m_recordAccess.length + " - Dependent #" + m_recordDependentAccess.length);
    } //	loadRecordAccess

    /**
     * ************************************************************************ Get Client Where
     * Clause Value
     *
     * @param rw read write
     * @return "AD_Client_ID=0" or "AD_Client_ID IN(0,1)"
     */
    public String getClientWhere(boolean rw) {
        //	All Orgs - use Client of Role
        if (isAccessAllOrgs()) {
            if (rw || getClientId() == 0) return "AD_Client_ID=" + getClientId();
            return "AD_Client_ID IN (0," + getClientId() + ")";
        }

        //	Get Client from Org List
        loadOrgAccess(false);
        //	Unique Strings
        HashSet<String> set = new HashSet<String>();
        if (!rw) set.add("0");
        //	Positive List
        for (int i = 0; i < m_orgAccess.length; i++)
            set.add(String.valueOf(m_orgAccess[i].getClientId()));
        //
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = set.iterator();
        boolean oneOnly = true;
        while (it.hasNext()) {
            if (sb.length() > 0) {
                sb.append(",");
                oneOnly = false;
            }
            sb.append(it.next());
        }
        if (oneOnly) {
            if (sb.length() > 0) return "AD_Client_ID=" + sb.toString();
            else {
                log.log(Level.SEVERE, "No Access Org records");
                return "AD_Client_ID=-1"; //	No Access Record
            }
        }
        return "AD_Client_ID IN(" + sb.toString() + ")";
    } //	getClientWhereValue

    /**
     * Access to Client
     *
     * @param AD_Client_ID client
     * @param rw           read write access
     * @return true if access
     */
    public boolean isClientAccess(int AD_Client_ID, boolean rw) {
        if (AD_Client_ID == 0 && !rw) // 	can always read System
            return true;
        //
        // Check Access All Orgs:
        if (isAccessAllOrgs()) {
            // User has access to given clientId if the role is defined on that clientId
            return getClientId() == AD_Client_ID;
        }
        //
        loadOrgAccess(false);
        //	Positive List
        for (int i = 0; i < m_orgAccess.length; i++) {
            if (m_orgAccess[i].getClientId() == AD_Client_ID) {
                if (!rw) return true;
                if (!m_orgAccess[i].getReadOnly()) // 	rw
                    return true;
            }
        }
        return false;
    } //	isClientAccess

    /**
     * Get Org Where Clause Value
     *
     * @param rw read write
     * @return "AD_Org_ID=0" or "AD_Org_ID IN(0,1)" or null (if access all org)
     */
    public String getOrgWhere(boolean rw) {
        if (isAccessAllOrgs()) return null;
        loadOrgAccess(false);
        //	Unique Strings
        HashSet<String> set = new HashSet<String>();
        if (!rw) set.add("0");
        //	Positive List
        for (int i = 0; i < m_orgAccess.length; i++) {
            if (!rw) set.add(String.valueOf(m_orgAccess[i].getOrgId()));
            else if (!m_orgAccess[i].getReadOnly()) // 	rw
                set.add(String.valueOf(m_orgAccess[i].getOrgId()));
        }
        //
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = set.iterator();
        boolean oneOnly = true;
        while (it.hasNext()) {
            if (sb.length() > 0) {
                sb.append(",");
                oneOnly = false;
            }
            sb.append(it.next());
        }
        if (oneOnly) {
            if (sb.length() > 0) return "AD_Org_ID=" + sb.toString();
            else {
                log.log(Level.SEVERE, "No Access Org records");
                return "AD_Org_ID=-1"; //	No Access Record
            }
        }
        return "AD_Org_ID IN(" + sb.toString() + ")";
    } //	getOrgWhereValue

    /**
     * Access to Table
     *
     * @param AD_Table_ID table
     * @param ro          check read only access otherwise read write access level
     * @return has RO/RW access to table
     */
    public boolean isTableAccess(int AD_Table_ID, boolean ro) {
        if (!isTableAccessLevel(AD_Table_ID, ro)) // 	Role Based Access
            return false;

        // default to negative list, can access on all tables
        boolean hasAccess = true; // 	assuming exclusive rule
        MTableAccess[] m_tableAccess = loadTableAccess(false);
        for (int i = 0; i < m_tableAccess.length; i++) {
            if (!X_AD_Table_Access.ACCESSTYPERULE_Accessing.equals(m_tableAccess[i].getAccessTypeRule()))
                continue;
            if (m_tableAccess[i].isExclude()) // 	Exclude
            //	If you Exclude Access to a table and select Read Only,
            //	you can only read data (otherwise no access).
            {
                if (m_tableAccess[i].getAccessTableId() == AD_Table_ID) {
                    if (ro) hasAccess = m_tableAccess[i].isReadOnly();
                    else hasAccess = false;
                    if (log.isLoggable(Level.FINE))
                        log.fine(
                                "Exclude AD_Table_ID="
                                        + AD_Table_ID
                                        + " (ro="
                                        + ro
                                        + ",TableAccessRO="
                                        + m_tableAccess[i].isReadOnly()
                                        + ") = "
                                        + hasAccess);
                    return hasAccess;
                }
            } else //	Include
            //	If you Include Access to a table and select Read Only,
            //	you can only read data (otherwise full access).
            {
                // positive list, can access ONLY on included tables
                hasAccess = false;
                if (m_tableAccess[i].getAccessTableId() == AD_Table_ID) {
                    if (!ro) //	rw only if not r/o
                        hasAccess = !m_tableAccess[i].isReadOnly();
                    else hasAccess = true;
                    if (log.isLoggable(Level.FINE))
                        log.fine(
                                "Include AD_Table_ID="
                                        + AD_Table_ID
                                        + " (ro="
                                        + ro
                                        + ",TableAccessRO="
                                        + m_tableAccess[i].isReadOnly()
                                        + ") = "
                                        + hasAccess);
                    return hasAccess;
                }
            }
        } //	for all Table Access
        if (!hasAccess)
            if (log.isLoggable(Level.FINE))
                log.fine("AD_Table_ID=" + AD_Table_ID + "(ro=" + ro + ") = " + hasAccess);
        return hasAccess;
    } //	isTableAccess

    /**
     * Access to Table based on Role User Level Table Access Level
     *
     * @param AD_Table_ID table
     * @param ro          check read only access otherwise read write access level
     * @return has RO/RW access to table
     */
    public boolean isTableAccessLevel(int AD_Table_ID, boolean ro) {
        if (ro) //	role can always read
            return true;
        //
        loadTableInfo(false);
        //	AccessLevel
        //		1 = Org - 2 = Client - 4 = System
        //		3 = Org+Client - 6 = Client+System - 7 = All
        String roleAccessLevel = getM_tableAccessLevel().get(new Integer(AD_Table_ID));
        if (roleAccessLevel == null) {
            if (log.isLoggable(Level.FINE)) log.fine("NO - No AccessLevel - AD_Table_ID=" + AD_Table_ID);
            return false;
        }
        //	Access to all User Levels
        if (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_All)) return true;
        //	User Level = SCO
        String userLevel = getUserLevel();
        //
        if (userLevel.charAt(0) == 'S'
                && (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemOnly)
                || roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemPlusClient))) return true;
        if (userLevel.charAt(1) == 'C'
                && (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_ClientOnly)
                || roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemPlusClient))) return true;
        if (userLevel.charAt(2) == 'O'
                && (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_Organization)
                || roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_ClientPlusOrganization))) return true;
        if (log.isLoggable(Level.FINE))
            log.fine(
                    "NO - AD_Table_ID="
                            + AD_Table_ID
                            + ", UserLevel="
                            + userLevel
                            + ", AccessLevel="
                            + roleAccessLevel);
        return false;
    } //	isTableAccessLevel

    /**
     * Get Window Access
     *
     * @param AD_Window_ID window
     * @return null in no access, TRUE if r/w and FALSE if r/o
     */
    public synchronized Boolean getWindowAccess(int AD_Window_ID) {
        if (m_windowAccess == null) {
            m_windowAccess = new HashMap<Integer, Boolean>(100);
            // first get the window access from the included and substitute roles
            mergeIncludedAccess("m_windowAccess"); // Load included accesses - metas-2009_0021_AP1_G94
            // and now get the window access directly from this role
            MClient client = MClient.get(Env.getCtx());
            String ASPFilter = "";
            if (client.isUseASP())
                ASPFilter =
                        "   AND (   AD_Window_ID IN ( "
                                // Just ASP subscribed windows for client "
                                + "              SELECT w.AD_Window_ID "
                                + "                FROM ASP_Window w, ASP_Level l, ASP_ClientLevel cl "
                                + "               WHERE w.ASP_Level_ID = l.ASP_Level_ID "
                                + "                 AND cl.clientId = "
                                + client.getClientId()
                                + "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
                                + "                 AND w.IsActive = 'Y' "
                                + "                 AND l.IsActive = 'Y' "
                                + "                 AND cl.IsActive = 'Y' "
                                + "                 AND w.ASP_Status = 'S') " // Show
                                + "        OR AD_Window_ID IN ( "
                                // + show ASP exceptions for client
                                + "              SELECT AD_Window_ID "
                                + "                FROM ASP_ClientException ce "
                                + "               WHERE ce.clientId = "
                                + client.getClientId()
                                + "                 AND ce.IsActive = 'Y' "
                                + "                 AND ce.AD_Window_ID IS NOT NULL "
                                + "                 AND ce.AD_Tab_ID IS NULL "
                                + "                 AND ce.AD_Field_ID IS NULL "
                                + "                 AND ce.ASP_Status = 'S') " // Show
                                + "       ) "
                                + "   AND AD_Window_ID NOT IN ( "
                                // minus hide ASP exceptions for client
                                + "          SELECT AD_Window_ID "
                                + "            FROM ASP_ClientException ce "
                                + "           WHERE ce.clientId = "
                                + client.getClientId()
                                + "             AND ce.IsActive = 'Y' "
                                + "             AND ce.AD_Window_ID IS NOT NULL "
                                + "             AND ce.AD_Tab_ID IS NULL "
                                + "             AND ce.AD_Field_ID IS NULL "
                                + "             AND ce.ASP_Status = 'H')"; // Hide
            String sql =
                    "SELECT AD_Window_ID, IsReadWrite, IsActive FROM AD_Window_Access WHERE AD_Role_ID=?"
                            + ASPFilter;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            HashMap<Integer, Boolean> directAccess = new HashMap<Integer, Boolean>(100);
            try {
                pstmt = prepareStatement(sql);
                pstmt.setInt(1, getRoleId());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Integer winId = new Integer(rs.getInt(1));
                    if ("N".equals(rs.getString(3))) {
                        // inactive window on direct access
                        m_windowAccess.remove(winId);
                    } else {
                        directAccess.put(winId, new Boolean("Y".equals(rs.getString(2))));
                    }
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, sql, e);
            } finally {
            }
            //
            setAccessMap(
                    "m_windowAccess", mergeAccess(getAccessMap("m_windowAccess"), directAccess, true));
            if (log.isLoggable(Level.FINE)) log.fine("#" + m_windowAccess.size());
        } //	reload
        Boolean retValue = m_windowAccess.get(AD_Window_ID);
        if (log.isLoggable(Level.FINE))
            log.fine("getWindowAccess - AD_Window_ID=" + AD_Window_ID + " - " + retValue);
        return retValue;
    } //	getWindowAccess

    /**
     * Get Process Access
     *
     * @param AD_Process_ID process
     * @return null in no access, TRUE if r/w and FALSE if r/o
     */
    public synchronized Boolean getProcessAccess(int AD_Process_ID) {
        if (m_processAccess == null) {
            m_processAccess = new HashMap<Integer, Boolean>(50);
            // first get the process access from the included and substitute roles
            mergeIncludedAccess("m_processAccess"); // Load included accesses - metas-2009_0021_AP1_G94
            // and now get the process access directly from this role
            MClient client = MClient.get(Env.getCtx());
            String ASPFilter = "";
            if (client.isUseASP())
                ASPFilter =
                        "   AND (   AD_Process_ID IN ( "
                                // Just ASP subscribed processes for client "
                                + "              SELECT p.AD_Process_ID "
                                + "                FROM ASP_Process p, ASP_Level l, ASP_ClientLevel cl "
                                + "               WHERE p.ASP_Level_ID = l.ASP_Level_ID "
                                + "                 AND cl.clientId = "
                                + client.getClientId()
                                + "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
                                + "                 AND p.IsActive = 'Y' "
                                + "                 AND l.IsActive = 'Y' "
                                + "                 AND cl.IsActive = 'Y' "
                                + "                 AND p.ASP_Status = 'S') " // Show
                                + "        OR AD_Process_ID IN ( "
                                // + show ASP exceptions for client
                                + "              SELECT AD_Process_ID "
                                + "                FROM ASP_ClientException ce "
                                + "               WHERE ce.clientId = "
                                + client.getClientId()
                                + "                 AND ce.IsActive = 'Y' "
                                + "                 AND ce.AD_Process_ID IS NOT NULL "
                                + "                 AND ce.AD_Process_Para_ID IS NULL "
                                + "                 AND ce.ASP_Status = 'S') " // Show
                                + "       ) "
                                + "   AND AD_Process_ID NOT IN ( "
                                // minus hide ASP exceptions for client
                                + "          SELECT AD_Process_ID "
                                + "            FROM ASP_ClientException ce "
                                + "           WHERE ce.clientId = "
                                + client.getClientId()
                                + "             AND ce.IsActive = 'Y' "
                                + "             AND ce.AD_Process_ID IS NOT NULL "
                                + "             AND ce.AD_Process_Para_ID IS NULL "
                                + "             AND ce.ASP_Status = 'H')"; // Hide
            String sql =
                    "SELECT AD_Process_ID, IsReadWrite, IsActive FROM AD_Process_Access WHERE AD_Role_ID=?"
                            + ASPFilter;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            HashMap<Integer, Boolean> directAccess = new HashMap<Integer, Boolean>(100);
            try {
                pstmt = prepareStatement(sql);
                pstmt.setInt(1, getRoleId());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Integer procId = new Integer(rs.getInt(1));
                    if ("N".equals(rs.getString(3))) {
                        // inactive process on direct access
                        m_processAccess.remove(procId);
                    } else {
                        directAccess.put(procId, new Boolean("Y".equals(rs.getString(2))));
                    }
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, sql, e);
            } finally {
            }
            setAccessMap(
                    "m_processAccess", mergeAccess(getAccessMap("m_processAccess"), directAccess, true));
        } //	reload
        Boolean retValue = m_processAccess.get(AD_Process_ID);
        return retValue;
    } //	getProcessAccess

    /**
     * Get Task Access
     *
     * @param AD_Task_ID task
     * @return null in no access, TRUE if r/w and FALSE if r/o
     */
    public synchronized Boolean getTaskAccess(int AD_Task_ID) {
        if (m_taskAccess == null) {
            m_taskAccess = new HashMap<Integer, Boolean>(10);
            // first get the task access from the included and substitute roles
            mergeIncludedAccess("m_taskAccess"); // Load included accesses - metas-2009_0021_AP1_G94
            // and now get the task access directly from this role
            MClient client = MClient.get(Env.getCtx());
            String ASPFilter = "";
            if (client.isUseASP())
                ASPFilter =
                        "   AND (   AD_Task_ID IN ( "
                                // Just ASP subscribed tasks for client "
                                + "              SELECT t.AD_Task_ID "
                                + "                FROM ASP_Task t, ASP_Level l, ASP_ClientLevel cl "
                                + "               WHERE t.ASP_Level_ID = l.ASP_Level_ID "
                                + "                 AND cl.clientId = "
                                + client.getClientId()
                                + "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
                                + "                 AND t.IsActive = 'Y' "
                                + "                 AND l.IsActive = 'Y' "
                                + "                 AND cl.IsActive = 'Y' "
                                + "                 AND t.ASP_Status = 'S') " // Show
                                + "        OR AD_Task_ID IN ( "
                                // + show ASP exceptions for client
                                + "              SELECT AD_Task_ID "
                                + "                FROM ASP_ClientException ce "
                                + "               WHERE ce.clientId = "
                                + client.getClientId()
                                + "                 AND ce.IsActive = 'Y' "
                                + "                 AND ce.AD_Task_ID IS NOT NULL "
                                + "                 AND ce.ASP_Status = 'S') " // Show
                                + "       ) "
                                + "   AND AD_Task_ID NOT IN ( "
                                // minus hide ASP exceptions for client
                                + "          SELECT AD_Task_ID "
                                + "            FROM ASP_ClientException ce "
                                + "           WHERE ce.clientId = "
                                + client.getClientId()
                                + "             AND ce.IsActive = 'Y' "
                                + "             AND ce.AD_Task_ID IS NOT NULL "
                                + "             AND ce.ASP_Status = 'H')"; // Hide
            String sql =
                    "SELECT AD_Task_ID, IsReadWrite, IsActive FROM AD_Task_Access WHERE AD_Role_ID=?"
                            + ASPFilter;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            HashMap<Integer, Boolean> directAccess = new HashMap<Integer, Boolean>(100);
            try {
                pstmt = prepareStatement(sql);
                pstmt.setInt(1, getRoleId());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Integer taskId = new Integer(rs.getInt(1));
                    if ("N".equals(rs.getString(3))) {
                        // inactive task on direct access
                        m_taskAccess.remove(taskId);
                    } else {
                        directAccess.put(taskId, new Boolean("Y".equals(rs.getString(2))));
                    }
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, sql, e);
            } finally {
            }
            setAccessMap("m_taskAccess", mergeAccess(getAccessMap("m_taskAccess"), directAccess, true));
        } //	reload
        Boolean retValue = m_taskAccess.get(AD_Task_ID);
        return retValue;
    } //	getTaskAccess

    /**
     * Get Form Access
     *
     * @param AD_Form_ID form
     * @return null in no access, TRUE if r/w and FALSE if r/o
     */
    public synchronized Boolean getFormAccess(int AD_Form_ID) {
        if (m_formAccess == null) {
            m_formAccess = new HashMap<Integer, Boolean>(20);
            // first get the form access from the included and substitute roles
            mergeIncludedAccess("m_formAccess"); // Load included accesses - metas-2009_0021_AP1_G94
            // and now get the form access directly from this role
            MClient client = MClient.get(Env.getCtx());
            String ASPFilter = "";
            if (client.isUseASP())
                ASPFilter =
                        "   AND (   AD_Form_ID IN ( "
                                // Just ASP subscribed forms for client "
                                + "              SELECT f.AD_Form_ID "
                                + "                FROM ASP_Form f, ASP_Level l, ASP_ClientLevel cl "
                                + "               WHERE f.ASP_Level_ID = l.ASP_Level_ID "
                                + "                 AND cl.clientId = "
                                + client.getClientId()
                                + "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
                                + "                 AND f.IsActive = 'Y' "
                                + "                 AND l.IsActive = 'Y' "
                                + "                 AND cl.IsActive = 'Y' "
                                + "                 AND f.ASP_Status = 'S') " // Show
                                + "        OR AD_Form_ID IN ( "
                                // + show ASP exceptions for client
                                + "              SELECT AD_Form_ID "
                                + "                FROM ASP_ClientException ce "
                                + "               WHERE ce.clientId = "
                                + client.getClientId()
                                + "                 AND ce.IsActive = 'Y' "
                                + "                 AND ce.AD_Form_ID IS NOT NULL "
                                + "                 AND ce.ASP_Status = 'S') " // Show
                                + "       ) "
                                + "   AND AD_Form_ID NOT IN ( "
                                // minus hide ASP exceptions for client
                                + "          SELECT AD_Form_ID "
                                + "            FROM ASP_ClientException ce "
                                + "           WHERE ce.clientId = "
                                + client.getClientId()
                                + "             AND ce.IsActive = 'Y' "
                                + "             AND ce.AD_Form_ID IS NOT NULL "
                                + "             AND ce.ASP_Status = 'H')"; // Hide
            String sql =
                    "SELECT AD_Form_ID, IsReadWrite, IsActive FROM AD_Form_Access WHERE AD_Role_ID=?"
                            + ASPFilter;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            HashMap<Integer, Boolean> directAccess = new HashMap<Integer, Boolean>(100);
            try {
                pstmt = prepareStatement(sql);
                pstmt.setInt(1, getRoleId());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Integer formId = new Integer(rs.getInt(1));
                    if ("N".equals(rs.getString(3))) {
                        // inactive form on direct access
                        m_formAccess.remove(formId);
                    } else {
                        directAccess.put(formId, new Boolean("Y".equals(rs.getString(2))));
                    }
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, sql, e);
            } finally {
            }
            setAccessMap("m_formAccess", mergeAccess(getAccessMap("m_formAccess"), directAccess, true));
        } //	reload
        Boolean retValue = m_formAccess.get(AD_Form_ID);
        return retValue;
    } //	getFormAccess

    /**
     * Get Workflow Access
     *
     * @param AD_Workflow_ID workflow
     * @return null in no access, TRUE if r/w and FALSE if r/o
     */
    public synchronized Boolean getWorkflowAccess(int AD_Workflow_ID) {
        if (m_workflowAccess == null) {
            m_workflowAccess = new HashMap<Integer, Boolean>(20);
            // first get the workflow access from the included and substitute roles
            mergeIncludedAccess("m_workflowAccess"); // Load included accesses - metas-2009_0021_AP1_G94
            // and now get the workflow access directly from this role
            MClient client = MClient.get(Env.getCtx());
            String ASPFilter = "";
            if (client.isUseASP())
                ASPFilter =
                        "   AND (   AD_Workflow_ID IN ( "
                                // Just ASP subscribed workflows for client "
                                + "              SELECT w.AD_Workflow_ID "
                                + "                FROM ASP_Workflow w, ASP_Level l, ASP_ClientLevel cl "
                                + "               WHERE w.ASP_Level_ID = l.ASP_Level_ID "
                                + "                 AND cl.clientId = "
                                + client.getClientId()
                                + "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
                                + "                 AND w.IsActive = 'Y' "
                                + "                 AND l.IsActive = 'Y' "
                                + "                 AND cl.IsActive = 'Y' "
                                + "                 AND w.ASP_Status = 'S') " // Show
                                + "        OR AD_Workflow_ID IN ( "
                                // + show ASP exceptions for client
                                + "              SELECT AD_Workflow_ID "
                                + "                FROM ASP_ClientException ce "
                                + "               WHERE ce.clientId = "
                                + client.getClientId()
                                + "                 AND ce.IsActive = 'Y' "
                                + "                 AND ce.AD_Workflow_ID IS NOT NULL "
                                + "                 AND ce.ASP_Status = 'S') " // Show
                                + "       ) "
                                + "   AND AD_Workflow_ID NOT IN ( "
                                // minus hide ASP exceptions for client
                                + "          SELECT AD_Workflow_ID "
                                + "            FROM ASP_ClientException ce "
                                + "           WHERE ce.clientId = "
                                + client.getClientId()
                                + "             AND ce.IsActive = 'Y' "
                                + "             AND ce.AD_Workflow_ID IS NOT NULL "
                                + "             AND ce.ASP_Status = 'H')"; // Hide
            String sql =
                    "SELECT AD_Workflow_ID, IsReadWrite, IsActive FROM AD_Workflow_Access WHERE AD_Role_ID=?"
                            + ASPFilter;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            HashMap<Integer, Boolean> directAccess = new HashMap<Integer, Boolean>(100);
            try {
                pstmt = prepareStatement(sql);
                pstmt.setInt(1, getRoleId());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Integer formId = new Integer(rs.getInt(1));
                    if ("N".equals(rs.getString(3))) {
                        // inactive workflow on direct access
                        m_workflowAccess.remove(formId);
                    } else {
                        directAccess.put(formId, new Boolean("Y".equals(rs.getString(2))));
                    }
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, sql, e);
            } finally {
            }
            setAccessMap(
                    "m_workflowAccess", mergeAccess(getAccessMap("m_workflowAccess"), directAccess, true));
        } //	reload
        Boolean retValue = m_workflowAccess.get(AD_Workflow_ID);
        return retValue;
    } //	getTaskAccess

    /**
     * *********************************************************************** Appends where clause to
     * SQL statement for Table
     *
     * @param SQL            existing SQL statement
     * @param TableNameIn    Table Name or list of table names AAA, BBB or AAA a, BBB b
     * @param fullyQualified fullyQualified names
     * @param rw             if false, includes System Data
     * @return updated SQL statement
     */
    public String addAccessSQL(String SQL, String TableNameIn, boolean fullyQualified, boolean rw) {
        StringBuilder retSQL = new StringBuilder();

        //	Cut off last ORDER BY clause
        String orderBy = "";
        int posOrder = SQL.lastIndexOf(" ORDER BY ");
        if (posOrder != -1) {
            orderBy = SQL.substring(posOrder);
            retSQL.append(SQL, 0, posOrder);
        } else retSQL.append(SQL);

        //	Parse SQL
        AccessSqlParser asp = new AccessSqlParser(retSQL.toString());
        AccessSqlParser.TableInfo[] ti = asp.getTableInfo(asp.getMainSqlIndex());

        //  Do we have to add WHERE or AND
        if (asp.getMainSql().indexOf(" WHERE ") == -1) retSQL.append(" WHERE ");
        else retSQL.append(" AND ");

        //	Use First Table
        String tableName = "";
        if (ti.length > 0) {
            tableName = ti[0].getSynonym();
            if (tableName.length() == 0) tableName = ti[0].getTableName();
        }
        if (TableNameIn != null && !tableName.equals(TableNameIn)) {
            String msg = "TableName not correctly parsed - TableNameIn=" + TableNameIn + " - " + asp;
            if (ti.length > 0) msg += " - #1 " + ti[0];
            msg += "\n = " + SQL;
            log.log(Level.SEVERE, msg);
            Trace.printStack();
            tableName = TableNameIn;
        }

        if (!tableName.equals(X_AD_PInstance_Log.Table_Name)) { // globalqss, bug 1662433
            //	Client Access
            if (fullyQualified) retSQL.append(tableName).append(".");
            retSQL.append(getClientWhere(rw));

            //	Org Access
            if (!isAccessAllOrgs()) {
                retSQL.append(" AND ");
                if (fullyQualified) retSQL.append(tableName).append(".");
                retSQL.append(getOrgWhere(rw));
            }
        } else {
            retSQL.append("1=1");
        }

        //	** Data Access	**
        for (int i = 0; i < ti.length; i++) {
            String TableName = ti[i].getTableName();

            // [ 1644310 ] Rev. 1292 hangs on start
            if (TableName.toUpperCase().endsWith("_TRL")) continue;
            if (isView(TableName)) continue;

            int AD_Table_ID = getAD_Table_ID(TableName);
            //	Data Table Access
            if (AD_Table_ID != 0 && !isTableAccess(AD_Table_ID, !rw)) {
                retSQL.append(" AND 1=3"); // 	prevent access at all
                if (log.isLoggable(Level.FINE))
                    log.fine("No access to AD_Table_ID=" + AD_Table_ID + " - " + TableName + " - " + retSQL);
                break; //	no need to check further
            }

            //	Data Column Access

            //	Data Record Access
            String keyColumnName = "";
            if (fullyQualified) {
                keyColumnName = ti[i].getSynonym(); // 	table synonym
                if (keyColumnName.length() == 0) keyColumnName = TableName;
                keyColumnName += ".";
            }
            // keyColumnName += TableName + "_ID";	//	derived from table
            if (getIdColumnName(TableName) == null) continue;
            keyColumnName += getIdColumnName(TableName);

            // log.fine("addAccessSQL - " + TableName + "(" + AD_Table_ID + ") " + keyColumnName);
            String recordWhere = getRecordWhere(AD_Table_ID, keyColumnName, rw);
            if (recordWhere.length() > 0) {
                retSQL.append(" AND ").append(recordWhere);
                if (log.isLoggable(Level.FINEST)) log.finest("Record access - " + recordWhere);
            }
        } //	for all table info

        //	Dependent Records (only for main SQL)
        String mainSql = asp.getMainSql();
        loadRecordAccess(false);
        int AD_Table_ID = 0;
        String whereColumnName = null;
        ArrayList<Integer> includes = new ArrayList<Integer>();
        ArrayList<Integer> excludes = new ArrayList<Integer>();
        for (int i = 0; i < m_recordDependentAccess.length; i++) {
            String columnName =
                    m_recordDependentAccess[i].getKeyColumnName(asp.getTableInfo(asp.getMainSqlIndex()));
            if (columnName == null) continue; // 	no key column

            if (mainSql.toUpperCase().startsWith("SELECT COUNT(*) FROM ")) {
                // globalqss - Carlos Ruiz - [ 1965744 ] Dependent entities access problem
                // this is the count select, it doesn't have the column but needs to be filtered
                MTable table = MTable.get(getCtx(), tableName);
                if (table == null) continue;
                MColumn column = table.getColumn(columnName);
                if (column == null || column.isVirtualColumn() || !column.isActive()) continue;
            } else {
                int posColumn = mainSql.indexOf(columnName);
                if (posColumn == -1) continue;
                //	we found the column name - make sure it's a column name
                char charCheck = mainSql.charAt(posColumn - 1); // 	before
                if (!(charCheck == ',' || charCheck == '.' || charCheck == ' ' || charCheck == '('))
                    continue;
                charCheck = mainSql.charAt(posColumn + columnName.length()); // 	after
                if (!(charCheck == ',' || charCheck == ' ' || charCheck == ')')) continue;
            }

            if (AD_Table_ID != 0 && AD_Table_ID != m_recordDependentAccess[i].getRecordTableId())
                retSQL.append(getDependentAccess(whereColumnName, includes, excludes));

            AD_Table_ID = m_recordDependentAccess[i].getRecordTableId();
            //	*** we found the column in the main query
            if (m_recordDependentAccess[i].isExclude()) {
                excludes.add(m_recordDependentAccess[i].getRecordId());
                if (log.isLoggable(Level.FINE))
                    log.fine("Exclude " + columnName + " - " + m_recordDependentAccess[i]);
            } else if (!rw || !m_recordDependentAccess[i].isReadOnly()) {
                includes.add(m_recordDependentAccess[i].getRecordId());
                if (log.isLoggable(Level.FINE))
                    log.fine("Include " + columnName + " - " + m_recordDependentAccess[i]);
            }
            whereColumnName = getDependentRecordWhereColumn(mainSql, columnName);
        } //	for all dependent records
        retSQL.append(getDependentAccess(whereColumnName, includes, excludes));
        //
        retSQL.append(orderBy);
        if (log.isLoggable(Level.FINEST)) log.finest(retSQL.toString());
        return retSQL.toString();
    } //	addAccessSQL

    /**
     * Get Dependent Access
     *
     * @param whereColumnName column
     * @param includes        ids to include
     * @param excludes        ids to exclude
     * @return where clause starting with AND or ""
     */
    private String getDependentAccess(
            String whereColumnName, ArrayList<Integer> includes, ArrayList<Integer> excludes) {
        if (includes.size() == 0 && excludes.size() == 0) return "";
        if (includes.size() != 0 && excludes.size() != 0)
            log.warning("Mixing Include and Excluse rules - Will not return values");

        StringBuilder where = new StringBuilder(" AND ");
        if (includes.size() == 1) where.append(whereColumnName).append("=").append(includes.get(0));
        else if (includes.size() > 1) {
            where.append(whereColumnName).append(" IN (");
            for (int ii = 0; ii < includes.size(); ii++) {
                if (ii > 0) where.append(",");
                where.append(includes.get(ii));
            }
            where.append(")");
        } else if (excludes.size() == 1) {
            where.append("(" + whereColumnName + " IS NULL OR ");
            where.append(whereColumnName).append("<>").append(excludes.get(0)).append(")");
        } else if (excludes.size() > 1) {
            where.append("(" + whereColumnName + " IS NULL OR ");
            where.append(whereColumnName).append(" NOT IN (");
            for (int ii = 0; ii < excludes.size(); ii++) {
                if (ii > 0) where.append(",");
                where.append(excludes.get(ii));
            }
            where.append("))");
        }
        if (log.isLoggable(Level.FINEST)) log.finest(where.toString());
        return where.toString();
    } //	getDependentAccess

    /**
     * Get Dependent Record Where clause
     *
     * @param mainSql    sql to examine
     * @param columnName columnName
     * @return where clause column "x.columnName"
     */
    private String getDependentRecordWhereColumn(String mainSql, String columnName) {
        String retValue = columnName; // 	if nothing else found
        int index = mainSql.indexOf(columnName);
        if (index == -1) return retValue;
        //	see if there are table synonym
        int offset = index - 1;
        char c = mainSql.charAt(offset);
        if (c == '.') {
            StringBuilder sb = new StringBuilder();
            while (c != ' ' && c != ',' && c != '(') // 	delimeter
            {
                sb.insert(0, c);
                c = mainSql.charAt(--offset);
            }
            sb.append(columnName);
            return sb.toString();
        }
        return retValue;
    } //	getDependentRecordWhereColumn

    /**
     * Get Table ID from name
     *
     * @param tableName table name
     * @return AD_Table_ID or 0
     */
    private int getAD_Table_ID(String tableName) {
        loadTableInfo(false);
        Integer ii = getM_tableName().get(tableName);
        if (ii != null) return ii;
        //	log.log(Level.WARNING,"getColumnTableId - not found (" + tableName + ")");
        return 0;
    } //	getColumnTableId

    /**
     * Return Where clause for Record Access
     *
     * @param AD_Table_ID   table
     * @param keyColumnName (fully qualified) key column name
     * @param rw            true if read write
     * @return where clause or ""
     */
    private String getRecordWhere(int AD_Table_ID, String keyColumnName, boolean rw) {
        loadRecordAccess(false);
        //
        StringBuffer sbInclude = new StringBuffer();
        StringBuffer sbExclude = new StringBuffer();
        //	Role Access
        for (int i = 0; i < m_recordAccess.length; i++) {
            if (m_recordAccess[i].getRecordTableId() == AD_Table_ID) {
                //	NOT IN (x)
                if (m_recordAccess[i].isExclude()) {
                    if (sbExclude.length() == 0) {
                        sbExclude.append("(").append(keyColumnName).append(" IS NULL OR ");
                        sbExclude.append(keyColumnName).append(" NOT IN (");
                    } else sbExclude.append(",");
                    sbExclude.append(m_recordAccess[i].getRecordId());
                }
                //	IN (x)
                else if (!rw || !m_recordAccess[i].isReadOnly()) // 	include
                {
                    if (sbInclude.length() == 0) sbInclude.append(keyColumnName).append(" IN (");
                    else sbInclude.append(",");
                    sbInclude.append(m_recordAccess[i].getRecordId());
                }
            }
        } //	for all Table Access

        StringBuilder sb = new StringBuilder();
        if (sbExclude.length() > 0) sb.append(sbExclude).append("))");
        if (sbInclude.length() > 0) {
            if (sb.length() > 0) sb.append(" AND ");
            sb.append(sbInclude).append(")");
        }

        //	Don't ignore Privacy Access
        if (!isPersonalAccess()) {
            String lockedIDs = MPrivateAccess.getLockedRecordWhere(AD_Table_ID, m_AD_User_ID);
            if (lockedIDs != null) {
                if (sb.length() > 0) sb.append(" AND ");
                sb.append(" (" + keyColumnName + " IS NULL OR ");
                sb.append(keyColumnName).append(lockedIDs).append(") ");
            }
        }
        //
        return sb.toString();
    } //	getRecordWhere

    /**
     * Include role permissions
     *
     * @param role
     * @param seqNo
     * @see metas-2009_0021_AP1_G94
     */
    private void includeRole(MRole role, int seqNo) {
        if (this.getRoleId() == role.getRoleId()) {
            return;
        }
        if (this.m_includedRoles == null) {
            m_includedRoles = new ArrayList<MRole>();
        }
        for (MRole r : this.m_includedRoles) {
            if (r.getRoleId() == role.getRoleId()) {
                return;
            }
        }

        if (s_log.isLoggable(Level.INFO)) s_log.info("Include " + role);

        if (role.isActive()) {
            this.m_includedRoles.add(role);
            role.setParentRole(this);
            role.m_includedSeqNo = seqNo;
        }
    }

    /**
     * @return unmodifiable list of included roles
     * @see metas-2009_0021_AP1_G94
     */
    public List<MRole> getIncludedRoles(boolean recursive) {
        if (!recursive) {
            List<MRole> list = this.m_includedRoles;
            if (list == null) list = new ArrayList<MRole>();
            return Collections.unmodifiableList(list);
        } else {
            List<MRole> list = new ArrayList<MRole>();
            if (m_includedRoles != null) {
                for (MRole role : m_includedRoles) {
                    list.add(role);
                    list.addAll(role.getIncludedRoles(true));
                }
            }
            return list;
        }
    }

    /**
     * Load all included roles (direct inclusion or from user substitution)
     *
     * @param reload
     * @see metas-2009_0021_AP1_G94
     */
    private void loadIncludedRoles(boolean reload) {
        loadChildRoles(reload);
        loadSubstitutedRoles(reload);
        //
        if (this.m_parent == null) {
            mergeAccesses(reload);
        }
    }

    private void mergeAccesses(boolean reload) {
        OrgAccess[] orgAccess = new OrgAccess[]{};
        MTableAccess[] tableAccess = new MTableAccess[]{};
        MColumnAccess[] columnAccess = new MColumnAccess[]{};
        MRecordAccess[] recordAccess = new MRecordAccess[]{};
        MRecordAccess[] recordDependentAccess = new MRecordAccess[]{};
        //
        MRole last_role = null;
        for (MRole role : getIncludedRoles(false)) {
            boolean override = false;
            //
            // If roles have same SeqNo, then, the second role will override permissions from first role
            if (last_role != null
                    && last_role.m_includedSeqNo >= 0
                    && role.m_includedSeqNo >= 0
                    && last_role.m_includedSeqNo == role.m_includedSeqNo) {
                override = true;
            }
            //
            role.loadAccess(reload);
            role.mergeAccesses(reload);
            orgAccess = mergeAccess(orgAccess, role.m_orgAccess, override);
            tableAccess = mergeAccess(tableAccess, role.loadTableAccess(false), override);
            columnAccess = mergeAccess(columnAccess, role.loadColumnAccess(false), override);
            recordAccess = mergeAccess(recordAccess, role.m_recordAccess, override);
            recordDependentAccess =
                    mergeAccess(recordDependentAccess, role.m_recordDependentAccess, override);
            //
            last_role = role;
        }
        //
        // Merge permissions inside this role
        this.m_orgAccess = mergeAccess(this.m_orgAccess, orgAccess, false);
        this.setTableAccess(mergeAccess(this.loadTableAccess(false), tableAccess, false));
        this.setColumnAccess(mergeAccess(this.loadColumnAccess(false), columnAccess, false));
        this.m_recordAccess = mergeAccess(this.m_recordAccess, recordAccess, false);
        this.m_recordDependentAccess =
                mergeAccess(this.m_recordDependentAccess, recordDependentAccess, false);
    }

    /**
     * Load Child Roles
     *
     * @param reload
     * @see metas-2009_0021_AP1_G94
     */
    private void loadChildRoles(boolean reload) {
        m_includedRoles = null; // reset included roles
        final int AD_User_ID = getUserId();
        if (AD_User_ID < 0) {
            // throw new IllegalStateException("AD_User_ID is not set");
            return;
        }
        //
        final String whereClause = X_AD_Role_Included.COLUMNNAME_AD_Role_ID + "=?";
        List<X_AD_Role_Included> list =
                new Query(getCtx(), X_AD_Role_Included.Table_Name, whereClause)
                        .setParameters(getRoleId())
                        .setOnlyActiveRecords(true)
                        .setOrderBy(
                                X_AD_Role_Included.COLUMNNAME_SeqNo
                                        + ","
                                        + X_AD_Role_Included.COLUMNNAME_Included_Role_ID)
                        .list();
        for (X_AD_Role_Included includedRole : list) {
            MRole role = MRole.get(getCtx(), includedRole.getIncludedRoleId());
            includeRole(role, includedRole.getSeqNo());
        }
    }

    /**
     * Load substituted roles
     *
     * @param reload
     * @see metas-2009_0021_AP1_G94
     */
    private void loadSubstitutedRoles(boolean reload) {
        if (this.m_parent != null) {
            // load only if this is logged role (no parent roles)
            return;
        }
        //
        final int AD_User_ID = getUserId();
        if (AD_User_ID < 0) {
            // throw new IllegalStateException("AD_User_ID is not set");
            return;
        }
        //
        final String whereClause =
                "EXISTS ("
                        + " SELECT 1 FROM AD_User_Roles ur"
                        + " INNER JOIN AD_User_Substitute us ON (us.AD_User_ID=ur.AD_User_ID)"
                        + " WHERE ur.AD_Role_ID=AD_Role.AD_Role_ID AND ur.IsActive='Y' AND us.IsActive='Y'"
                        + " AND (us.ValidFrom IS NULL OR us.ValidFrom <= SYSDATE)"
                        + " AND (us.ValidTo IS NULL OR us.ValidTo >= SYSDATE)"
                        + " AND us.Substitute_ID=?)";

        List<MRole> list =
                new Query(getCtx(), I_AD_Role.Table_Name, whereClause)
                        .setParameters(AD_User_ID)
                        .setClient_ID()
                        .setOrderBy(I_AD_Role.COLUMNNAME_AD_Role_ID)
                        .list();
        for (MRole role : list) {
            includeRole(role, -1);
        }
    }

    /**
     * Set parent role. This method is called when this role is included in a parent role.
     *
     * @param parent
     * @see metas-2009_0021_AP1_G94
     */
    private void setParentRole(MRole parent) {
        this.setUserId(parent.getUserId());
        this.m_parent = parent;
    }

    private void mergeIncludedAccess(String varname) {
        HashMap<Integer, Boolean> includedAccess = new HashMap<Integer, Boolean>();
        MRole last_role = null;
        for (MRole role : getIncludedRoles(false)) {
            boolean override = false;
            //
            // If roles have same SeqNo, then, the second role will override permissions from first role
            if (last_role != null
                    && last_role.m_includedSeqNo >= 0
                    && role.m_includedSeqNo >= 0
                    && last_role.m_includedSeqNo == role.m_includedSeqNo) {
                override = true;
            }
            includedAccess = mergeAccess(includedAccess, role.getAccessMap(varname), override);
            last_role = role;
        }
        setAccessMap(varname, mergeAccess(getAccessMap(varname), includedAccess, false));
    }

    private HashMap<Integer, Boolean> getAccessMap(String varname) {
        if ("m_windowAccess".equals(varname)) {
            getWindowAccess(-1);
            return m_windowAccess;
        } else if ("m_processAccess".equals(varname)) {
            getProcessAccess(-1);
            return m_processAccess;
        } else if ("m_taskAccess".equals(varname)) {
            getTaskAccess(-1);
            return m_taskAccess;
        } else if ("m_workflowAccess".equals(varname)) {
            getWorkflowAccess(-1);
            return m_workflowAccess;
        } else if ("m_formAccess".equals(varname)) {
            getFormAccess(-1);
            return m_formAccess;
        } else if ("m_infoAccess".equals(varname)) {
            getInfoAccess(-1);
            return m_infoAccess;
        } else {
            throw new IllegalArgumentException("varname not supported - " + varname);
        }
    }

    private void setAccessMap(String varname, HashMap<Integer, Boolean> map) {
        if ("m_windowAccess".equals(varname)) {
            m_windowAccess = map;
        } else if ("m_processAccess".equals(varname)) {
            m_processAccess = map;
        } else if ("m_taskAccess".equals(varname)) {
            m_taskAccess = map;
        } else if ("m_workflowAccess".equals(varname)) {
            m_workflowAccess = map;
        } else if ("m_formAccess".equals(varname)) {
            m_formAccess = map;
        } else if ("m_infoAccess".equals(varname)) {
            m_infoAccess = map;
        } else {
            throw new IllegalArgumentException("varname not supported - " + varname);
        }
    }

    public synchronized Boolean getInfoAccess(int AD_InfoWindow_ID) {
        if (m_infoAccess == null) {
            m_infoAccess = new HashMap<Integer, Boolean>(20);
            // first get the info access from the included and substitute roles
            mergeIncludedAccess("m_infoAccess");
            // and now get the info access directly from this role
            String ASPFilter = "";
      /*
      MClient client = MClient.get(getCtx(), getClientId());
      if (client.isUseASP())
      	ASPFilter =
      		  "   AND (   AD_InfoWindow_ID IN ( "
      		// Just ASP subscribed forms for client "
      		+ "              SELECT f.AD_InfoWindow_ID "
      		+ "                FROM ASP_InfoWindow f, ASP_Level l, ASP_ClientLevel cl "
      		+ "               WHERE f.ASP_Level_ID = l.ASP_Level_ID "
      		+ "                 AND cl.clientId = " + client.getClientId()
      		+ "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
      		+ "                 AND f.IsActive = 'Y' "
      		+ "                 AND l.IsActive = 'Y' "
      		+ "                 AND cl.IsActive = 'Y' "
      		+ "                 AND f.ASP_Status = 'S') " // Show
      		+ "        OR AD_InfoWindow_ID IN ( "
      		// + show ASP exceptions for client
      		+ "              SELECT AD_InfoWindow_ID "
      		+ "                FROM ASP_ClientException ce "
      		+ "               WHERE ce.clientId = " + client.getClientId()
      		+ "                 AND ce.IsActive = 'Y' "
      		+ "                 AND ce.AD_InfoWindow_ID IS NOT NULL "
      		+ "                 AND ce.ASP_Status = 'S') " // Show
      		+ "       ) "
      		+ "   AND AD_InfoWindow_ID NOT IN ( "
      		// minus hide ASP exceptions for client
      		+ "          SELECT AD_InfoWindow_ID "
      		+ "            FROM ASP_ClientException ce "
      		+ "           WHERE ce.clientId = " + client.getClientId()
      		+ "             AND ce.IsActive = 'Y' "
      		+ "             AND ce.AD_InfoWindow_ID IS NOT NULL "
      		+ "             AND ce.ASP_Status = 'H')"; // Hide
      */
            String sql =
                    "SELECT AD_InfoWindow_ID, IsActive FROM AD_InfoWindow_Access WHERE AD_Role_ID=?"
                            + ASPFilter;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            HashMap<Integer, Boolean> directAccess = new HashMap<Integer, Boolean>(100);
            try {
                pstmt = prepareStatement(sql);
                pstmt.setInt(1, getRoleId());
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Integer infoId = new Integer(rs.getInt(1));
                    if ("N".equals(rs.getString(2))) {
                        // inactive info on direct access
                        m_infoAccess.remove(infoId);
                    } else {
                        directAccess.put(infoId, Boolean.TRUE);
                    }
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, sql, e);
            } finally {
            }
            setAccessMap("m_infoAccess", mergeAccess(getAccessMap("m_infoAccess"), directAccess, true));
        } //	reload
        Boolean retValue = m_infoAccess.get(AD_InfoWindow_ID);
        return retValue;
    }

    public void setClientOrg(IPO a) {
        super.setClientOrg(a);
    }
} //	MRole
