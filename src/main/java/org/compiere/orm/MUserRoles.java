package org.compiere.orm;

import org.compiere.model.I_AD_User_Roles;
import org.compiere.util.Msg;
import org.idempiere.common.util.CLogger;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * User Roles Model
 *
 * @author Jorg Janke
 * @version $Id: MUserRoles.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MUserRoles extends X_AD_User_Roles {
    /**
     *
     */
    private static final long serialVersionUID = 5850010835736994376L;
    /**
     * Static Logger
     */
    @SuppressWarnings("unused")
    private static CLogger s_log = CLogger.getCLogger(MUserRoles.class);

    /**
     * ************************************************************************ Persistence
     * Constructor
     *
     * @param ctx     context
     * @param ignored invalid
     * @param trxName transaction
     */
    public MUserRoles(Properties ctx, int ignored) {
        super(ctx, ignored);
        if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
    } //	MUserRoles

    /**
     * Load constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MUserRoles(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    } //	MUserRoles

    /**
     * Full Constructor
     *
     * @param ctx        context
     * @param AD_User_ID user
     * @param AD_Role_ID role
     * @param trxName    transaction
     */
    public MUserRoles(Properties ctx, int AD_User_ID, int AD_Role_ID) {
        this(ctx, 0);
        setAD_User_ID(AD_User_ID);
        setAD_Role_ID(AD_Role_ID);
    } //	MUserRoles

    /**
     * Get User Roles Of Role
     *
     * @param ctx        context
     * @param AD_Role_ID role
     * @return array of user roles
     */
    public static MUserRoles[] getOfRole(Properties ctx, int AD_Role_ID) {
        final String whereClause = I_AD_User_Roles.COLUMNNAME_AD_Role_ID + "=?";
        List<MUserRoles> list =
                new Query(ctx, I_AD_User_Roles.Table_Name, whereClause)
                        .setParameters(AD_Role_ID)
                        .list();
        MUserRoles[] retValue = new MUserRoles[list.size()];
        list.toArray(retValue);
        return retValue;
    } //	getOfRole

    /**
     * Set User/Contact. User within the system - Internal or Business Partner Contact
     *
     * @param AD_User_ID user
     */
    public void setAD_User_ID(int AD_User_ID) {
        set_ValueNoCheck("AD_User_ID", new Integer(AD_User_ID));
    } //	setAD_User_ID

    /**
     * Set Role. Responsibility Role
     *
     * @param AD_Role_ID role
     */
    public void setAD_Role_ID(int AD_Role_ID) {
        set_ValueNoCheck("AD_Role_ID", new Integer(AD_Role_ID));
    } //	setAD_Role_ID

    @Override
    protected boolean beforeSave(boolean newRecord) {
        // IDEMPIERE-1410
        if (!MRole.getDefault().isAccessAdvanced()) {
            MRole role = new MRole(getCtx(), getAD_Role_ID());
            if (role.isAccessAdvanced()) {
                log.saveError("Error", Msg.getMsg(getCtx(), "ActionNotAllowedHere"));
                return false;
            }
            if (!newRecord && is_ValueChanged(I_AD_User_Roles.COLUMNNAME_AD_Role_ID)) {
                MRole oldrole =
                        new MRole(getCtx(), get_ValueOldAsInt(I_AD_User_Roles.COLUMNNAME_AD_Role_ID));
                if (oldrole.isAccessAdvanced()) {
                    log.saveError("Error", Msg.getMsg(getCtx(), "ActionNotAllowedHere"));
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected boolean beforeDelete() {
        // IDEMPIERE-1410
        if (!MRole.getDefault().isAccessAdvanced()) {
            MRole role = new MRole(getCtx(), getAD_Role_ID());
            if (role.isAccessAdvanced()) {
                log.saveError("Error", Msg.getMsg(getCtx(), "ActionNotAllowedHere"));
                return false;
            }
        }
        return true;
    }
} //	MUserRoles
