package org.compiere.orm;

import org.compiere.model.I_AD_Record_Access;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Record_Access
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Record_Access extends PO implements I_AD_Record_Access, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Record_Access(Properties ctx, int AD_Record_Access_ID) {
        super(ctx, AD_Record_Access_ID);
        /**
         * if (AD_Record_Access_ID == 0) { setRoleId (0); setColumnTableId (0); setIsDependentEntities
         * (false); // N setIsExclude (true); // Y setIsReadOnly (false); setRecordId (0); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_Record_Access(Properties ctx, ResultSet rs) {
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
        StringBuffer sb = new StringBuffer("X_AD_Record_Access[").append(getId()).append("]");
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
     * Get Table.
     *
     * @return Database Table information
     */
    public int getRecordTableId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Dependent Entities.
     *
     * @param IsDependentEntities Also check access in dependent entities
     */
    public void setIsDependentEntities(boolean IsDependentEntities) {
        set_Value(COLUMNNAME_IsDependentEntities, Boolean.valueOf(IsDependentEntities));
    }

    /**
     * Get Dependent Entities.
     *
     * @return Also check access in dependent entities
     */
    public boolean isDependentEntities() {
        Object oo = getValue(COLUMNNAME_IsDependentEntities);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Exclude.
     *
     * @param IsExclude Exclude access to the data - if not selected Include access to the data
     */
    public void setIsExclude(boolean IsExclude) {
        set_Value(COLUMNNAME_IsExclude, Boolean.valueOf(IsExclude));
    }

    /**
     * Get Exclude.
     *
     * @return Exclude access to the data - if not selected Include access to the data
     */
    public boolean isExclude() {
        Object oo = getValue(COLUMNNAME_IsExclude);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
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
        Object oo = getValue(COLUMNNAME_IsReadOnly);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Record ID.
     *
     * @return Direct internal record ID
     */
    public int getRecordId() {
        Integer ii = (Integer) getValue(COLUMNNAME_Record_ID);
        if (ii == null) return 0;
        return ii;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
