package org.compiere.orm;

import org.compiere.model.I_AD_ViewColumn;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_ViewColumn
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_ViewColumn extends PO implements I_AD_ViewColumn, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_ViewColumn(Properties ctx, int AD_ViewColumn_ID) {
        super(ctx, AD_ViewColumn_ID);
        /**
         * if (AD_ViewColumn_ID == 0) { setAD_ViewColumn_ID (0); setViewComponentId (0);
         * setColumnName (null); setEntityType (null); // @SQL=select
         * get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_ViewColumn(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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
        if (AD_ViewComponent_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_ViewComponent_ID, null);
        else set_ValueNoCheck(COLUMNNAME_AD_ViewComponent_ID, Integer.valueOf(AD_ViewComponent_ID));
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

        set_Value(COLUMNNAME_EntityType, EntityType);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
