package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.ReferenceList;

/**
 * Generated Model for AD_Ref_List
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Ref_List extends BasePONameValue implements ReferenceList {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Ref_List(int AD_Ref_List_ID) {
        super(AD_Ref_List_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Ref_List(Row row) {
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
        return "X_AD_Ref_List[" + getId() + "]";
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
