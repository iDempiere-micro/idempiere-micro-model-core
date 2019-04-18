package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.ColumnAccess;

/**
 * Generated Model for AD_Column_Access
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Column_Access extends PO implements ColumnAccess {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Column_Access(int AD_Column_Access_ID) {
        super(AD_Column_Access_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Column_Access(Row row) {
        super(row);
    } //	MColumnAccess

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_AD_Column_Access[" + getId() + "]";
    }

    /**
     * Get Column.
     *
     * @return Column in the table
     */
    public int getColumnId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Column_ID);
        if (ii == null) return 0;
        return ii;
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
    public int getColumnTableId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Exclude.
     *
     * @param IsExclude Exclude access to the data - if not selected Include access to the data
     */
    public void setIsExclude(boolean IsExclude) {
        setValue(COLUMNNAME_IsExclude, IsExclude);
    }

    /**
     * Get Exclude.
     *
     * @return Exclude access to the data - if not selected Include access to the data
     */
    public boolean isExclude() {
        Object oo = getValue(COLUMNNAME_IsExclude);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
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
        setValue(COLUMNNAME_IsReadOnly, IsReadOnly);
    }

    /**
     * Get Read Only.
     *
     * @return Field is read only
     */
    public boolean isReadOnly() {
        Object oo = getValue(COLUMNNAME_IsReadOnly);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
