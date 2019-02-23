package org.compiere.orm;

import org.compiere.model.I_AD_TableIndex;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_TableIndex
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_TableIndex extends BasePOName implements I_AD_TableIndex, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_TableIndex(Properties ctx, int AD_TableIndex_ID) {
        super(ctx, AD_TableIndex_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_TableIndex(Properties ctx, ResultSet rs) {
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
        StringBuffer sb = new StringBuffer("X_AD_TableIndex[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Table.
     *
     * @return Database Table information
     */
    public int getIndexTableId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Table Index.
     *
     * @return Table Index
     */
    public int getTableIndexId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_TableIndex_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Entity Type.
     *
     * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
     */
    public void setEntityType(String EntityType) {

        set_Value(COLUMNNAME_EntityType, EntityType);
    }

    /**
     * Set Create Constraint.
     *
     * @param IsCreateConstraint Create Constraint
     */
    public void setIsCreateConstraint(boolean IsCreateConstraint) {
        set_Value(COLUMNNAME_IsCreateConstraint, Boolean.valueOf(IsCreateConstraint));
    }

    /**
     * Get Create Constraint.
     *
     * @return Create Constraint
     */
    public boolean isCreateConstraint() {
        Object oo = getValue(COLUMNNAME_IsCreateConstraint);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Key column.
     *
     * @return This column is the key in this table
     */
    public boolean isKey() {
        Object oo = getValue(COLUMNNAME_IsKey);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Unique.
     *
     * @param IsUnique Unique
     */
    public void setIsUnique(boolean IsUnique) {
        set_Value(COLUMNNAME_IsUnique, Boolean.valueOf(IsUnique));
    }

    /**
     * Get Unique.
     *
     * @return Unique
     */
    public boolean isUnique() {
        Object oo = getValue(COLUMNNAME_IsUnique);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
