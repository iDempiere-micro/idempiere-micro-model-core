package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.RecordAccess;

/**
 * Generated Model for AD_Record_Access
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public abstract class X_AD_Record_Access extends PO implements RecordAccess {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Record_Access(int AD_Record_Access_ID) {
        super(AD_Record_Access_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Record_Access(Row row) {
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
        return "X_AD_Record_Access[" + getId() + "]";
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
        setValue(COLUMNNAME_IsDependentEntities, Boolean.valueOf(IsDependentEntities));
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
        setValue(COLUMNNAME_IsExclude, Boolean.valueOf(IsExclude));
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
