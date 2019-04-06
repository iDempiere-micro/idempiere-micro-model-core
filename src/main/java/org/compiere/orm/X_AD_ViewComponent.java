package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.I_AD_ViewComponent;

/**
 * Generated Model for AD_ViewComponent
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_ViewComponent extends PO implements I_AD_ViewComponent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_ViewComponent(int AD_ViewComponent_ID) {
        super(AD_ViewComponent_ID);
        /**
         * if (AD_ViewComponent_ID == 0) { setColumnTableId (0); setViewComponentId (0); setEntityType
         * (null); // @SQL=select get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual setFromClause
         * (null); setName (null); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_ViewComponent(Row row) {
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
        StringBuffer sb = new StringBuffer("X_AD_ViewComponent[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Set Table.
     *
     * @param AD_Table_ID Database Table information
     */
    public void setViewTableId(int AD_Table_ID) {
        if (AD_Table_ID < 1) setValueNoCheck(COLUMNNAME_AD_Table_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
    }

    /**
     * Get Database View Component.
     *
     * @return Database View Component
     */
    public int getViewComponentId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_ViewComponent_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Entity Type.
     *
     * @return Dictionary Entity Type; Determines ownership and synchronization
     */
    public String getEntityType() {
        return (String) getValue(COLUMNNAME_EntityType);
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
     * Get Name.
     *
     * @return Alphanumeric identifier of the entity
     */
    public String getName() {
        return (String) getValue(HasName.COLUMNNAME_Name);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
