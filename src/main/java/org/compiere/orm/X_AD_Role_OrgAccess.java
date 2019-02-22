package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Role_OrgAccess;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Role_OrgAccess
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Role_OrgAccess extends PO implements I_AD_Role_OrgAccess, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Role_OrgAccess(Properties ctx, int AD_Role_OrgAccess_ID) {
        super(ctx, AD_Role_OrgAccess_ID);
        /** if (AD_Role_OrgAccess_ID == 0) { setAD_Role_ID (0); setIsReadOnly (false); } */
    }

    /**
     * Load Constructor
     */
    public X_AD_Role_OrgAccess(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_AD_Role_OrgAccess(Properties ctx, Row row) {
        super(ctx, row);
    } //	MRoleOrgAccess

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_AD_Role_OrgAccess[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Role.
     *
     * @return Responsibility Role
     */
    public int getAD_Role_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_Role_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Role.
     *
     * @param AD_Role_ID Responsibility Role
     */
    public void setAD_Role_ID(int AD_Role_ID) {
        if (AD_Role_ID < 0) set_ValueNoCheck(COLUMNNAME_AD_Role_ID, null);
        else set_ValueNoCheck(COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
    }

    /**
     * Set Read Only.
     *
     * @param IsReadOnly Field is read only
     */
    public void setIsReadOnly(boolean IsReadOnly) {
        set_Value(COLUMNNAME_IsReadOnly, Boolean.valueOf(IsReadOnly));
    }

    /**
     * Get Read Only.
     *
     * @return Field is read only
     */
    public boolean isReadOnly() {
        Object oo = get_Value(COLUMNNAME_IsReadOnly);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
