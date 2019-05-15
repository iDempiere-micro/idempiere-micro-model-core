package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.ViewComponent;

/**
 * Generated Model for AD_ViewComponent
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public abstract class X_AD_ViewComponent extends PO implements ViewComponent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_ViewComponent(int AD_ViewComponent_ID) {
        super(AD_ViewComponent_ID);
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
        return "X_AD_ViewComponent[" + getId() + "]";
    }

    /**
     * Set Table.
     *
     * @param AD_Table_ID Database Table information
     */
    public void setViewTableId(int AD_Table_ID) {
        if (AD_Table_ID < 1) setValueNoCheck(COLUMNNAME_AD_Table_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Table_ID, AD_Table_ID);
    }

    /**
     * Get Database View Component.
     *
     * @return Database View Component
     */
    public int getViewComponentId() {
        Integer ii = getValue(COLUMNNAME_AD_ViewComponent_ID);
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
