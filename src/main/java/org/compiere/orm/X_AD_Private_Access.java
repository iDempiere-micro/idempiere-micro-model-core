package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.PrivateAccess;

/**
 * Generated Model for AD_Private_Access
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Private_Access extends PO implements PrivateAccess {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Private_Access(int AD_Private_Access_ID) {
        super(AD_Private_Access_ID);
        /**
         * if (AD_Private_Access_ID == 0) { setColumnTableId (0); setUserId (0); setRecordId (0); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_Private_Access(Row row) {
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
        StringBuffer sb = new StringBuffer("X_AD_Private_Access[").append(getId()).append("]");
        return sb.toString();
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
     * Set Record ID.
     *
     * @param Record_ID Direct internal record ID
     */
    public void setRecordId(int Record_ID) {
        if (Record_ID < 0) setValueNoCheck(COLUMNNAME_Record_ID, null);
        else setValueNoCheck(COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }

    /**
     * Set Table.
     *
     * @param AD_Table_ID Database Table information
     */
    public void setTableId(int AD_Table_ID) {
        if (AD_Table_ID < 1) setValueNoCheck(COLUMNNAME_AD_Table_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
    }
}
