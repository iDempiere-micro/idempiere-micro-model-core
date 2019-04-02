package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Ref_Table;

/**
 * Generated Model for AD_Ref_Table
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Ref_Table extends PO implements I_AD_Ref_Table {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Ref_Table(int AD_Ref_Table_ID) {
        super(AD_Ref_Table_ID);
        /**
         * if (AD_Ref_Table_ID == 0) { setAD_Display (0); setAD_Key (0); setReferenceId (0);
         * setColumnTableId (0); setEntityType (null); // @SQL=select
         * get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual setIsValueDisplayed (false); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_Ref_Table(Row row) {
        super(row);
    }

    /**
     * AccessLevel
     *
     * @return 4 - System
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_AD_Ref_Table[").append(getId()).append("]");
        return sb.toString();
    }

    public org.compiere.model.I_AD_Table getTable() throws RuntimeException {
        return (org.compiere.model.I_AD_Table)
                MTable.get(org.compiere.model.I_AD_Table.Table_Name)
                        .getPO(getRefTableId());
    }

    /**
     * Get Table.
     *
     * @return Database Table information
     */
    public int getRefTableId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Entity Type.
     *
     * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
     */
    public void setEntityType(String EntityType) {

        setValue(COLUMNNAME_EntityType, EntityType);
    }

    /**
     * Set Display Value.
     *
     * @param IsValueDisplayed Displays Value column with the Display column
     */
    public void setIsValueDisplayed(boolean IsValueDisplayed) {
        setValue(COLUMNNAME_IsValueDisplayed, Boolean.valueOf(IsValueDisplayed));
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
