package org.compiere.orm;

import org.compiere.model.I_AD_Ref_List;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Ref_List
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Ref_List extends BasePONameValue implements I_AD_Ref_List {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Ref_List(Properties ctx, int AD_Ref_List_ID) {
        super(ctx, AD_Ref_List_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Ref_List(Properties ctx, ResultSet rs) {
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
        StringBuffer sb = new StringBuffer("X_AD_Ref_List[").append(getId()).append("]");
        return sb.toString();
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
