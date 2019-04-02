package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_ViewColumn;

/**
 * Generated Model for AD_ViewColumn
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_ViewColumn extends PO implements I_AD_ViewColumn {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_ViewColumn(int AD_ViewColumn_ID) {
        super(AD_ViewColumn_ID);
        /**
         * if (AD_ViewColumn_ID == 0) { setAD_ViewColumn_ID (0); setViewComponentId (0);
         * setColumnName (null); setEntityType (null); // @SQL=select
         * get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_ViewColumn(Row row) {
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
        StringBuffer sb = new StringBuffer("X_AD_ViewColumn[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Set Database View Component.
     *
     * @param AD_ViewComponent_ID Database View Component
     */
    public void setViewComponentId(int AD_ViewComponent_ID) {
        if (AD_ViewComponent_ID < 1) setValueNoCheck(COLUMNNAME_AD_ViewComponent_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_ViewComponent_ID, Integer.valueOf(AD_ViewComponent_ID));
    }

    /**
     * Get DB Column Name.
     *
     * @return Name of the column in the database
     */
    public String getColumnName() {
        return (String) getValue(COLUMNNAME_ColumnName);
    }

    /**
     * Set Entity Type.
     *
     * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
     */
    public void setEntityType(String EntityType) {

        setValue(COLUMNNAME_EntityType, EntityType);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
