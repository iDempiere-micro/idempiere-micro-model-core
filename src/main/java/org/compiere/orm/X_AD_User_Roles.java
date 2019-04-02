package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_User_Roles;

/**
 * Generated Model for AD_User_Roles
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_User_Roles extends PO implements I_AD_User_Roles {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_User_Roles(int AD_User_Roles_ID) {
        super(AD_User_Roles_ID);
        /** if (AD_User_Roles_ID == 0) { setRoleId (0); setUserId (0); } */
    }

    /**
     * Load Constructor
     */
    public X_AD_User_Roles(Row row) {
        super(row);
    }

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_AD_User_Roles[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Role.
     *
     * @return Responsibility Role
     */
    public int getRoleId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Role_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Role.
     *
     * @param AD_Role_ID Responsibility Role
     */
    public void setRoleId(int AD_Role_ID) {
        if (AD_Role_ID < 0) setValueNoCheck(COLUMNNAME_AD_Role_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
    }

    /**
     * Get User/Contact.
     *
     * @return User within the system - Internal or Business Partner Contact
     */
    public int getUserId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_User_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set User/Contact.
     *
     * @param AD_User_ID User within the system - Internal or Business Partner Contact
     */
    public void setUserId(int AD_User_ID) {
        if (AD_User_ID < 1) setValueNoCheck(COLUMNNAME_AD_User_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
