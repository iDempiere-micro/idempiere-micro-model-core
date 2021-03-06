package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.UserOrganizationalAccess;

/**
 * Generated Model for AD_User_OrgAccess
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_User_OrgAccess extends PO implements UserOrganizationalAccess {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_User_OrgAccess(int AD_User_OrgAccess_ID) {
        super(AD_User_OrgAccess_ID);
        /** if (AD_User_OrgAccess_ID == 0) { setUserId (0); setIsReadOnly (false); // N } */
    }

    /**
     * Load Constructor
     */
    public X_AD_User_OrgAccess(Row row) {
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
        StringBuffer sb = new StringBuffer("X_AD_User_OrgAccess[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get User/Contact.
     *
     * @return User within the system - Internal or Business Partner Contact
     */
    public int getUserId() {
        Integer ii = getValue(COLUMNNAME_AD_User_ID);
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

    /**
     * Set Read Only.
     *
     * @param IsReadOnly Field is read only
     */
    public void setIsReadOnly(boolean IsReadOnly) {
        setValue(COLUMNNAME_IsReadOnly, Boolean.valueOf(IsReadOnly));
    }

    /**
     * Get Read Only.
     *
     * @return Field is read only
     */
    public boolean isReadOnly() {
        Object oo = getValue(COLUMNNAME_IsReadOnly);
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
