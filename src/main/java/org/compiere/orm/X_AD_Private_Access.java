package org.compiere.orm;

import org.compiere.model.I_AD_Private_Access;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Private_Access
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Private_Access extends PO implements I_AD_Private_Access, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Private_Access(Properties ctx, int AD_Private_Access_ID) {
        super(ctx, AD_Private_Access_ID);
        /**
         * if (AD_Private_Access_ID == 0) { setAD_Table_ID (0); setAD_User_ID (0); setRecord_ID (0); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_Private_Access(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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
     * Get Table.
     *
     * @return Database Table information
     */
    public int getAD_Table_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Table.
     *
     * @param AD_Table_ID Database Table information
     */
    public void setAD_Table_ID(int AD_Table_ID) {
        if (AD_Table_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Table_ID, null);
        else set_ValueNoCheck(COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
    }

    /**
     * Set User/Contact.
     *
     * @param AD_User_ID User within the system - Internal or Business Partner Contact
     */
    public void setAD_User_ID(int AD_User_ID) {
        if (AD_User_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_User_ID, null);
        else set_ValueNoCheck(COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
    }

    /**
     * Set Record ID.
     *
     * @param Record_ID Direct internal record ID
     */
    public void setRecord_ID(int Record_ID) {
        if (Record_ID < 0) set_ValueNoCheck(COLUMNNAME_Record_ID, null);
        else set_ValueNoCheck(COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
