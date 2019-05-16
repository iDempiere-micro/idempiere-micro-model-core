package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.EntityType;

/**
 * Generated Model for AD_EntityType
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_EntityType extends BasePOName implements EntityType {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_EntityType(int AD_EntityType_ID) {
        super(AD_EntityType_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_EntityType(Row row) {
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
        return "X_AD_EntityType[" + getId() + "]";
    }

    /**
     * Get Entity Type.
     *
     * @return System Entity Type
     */
    public int getEntityTypeId() {
        Integer ii = getValue(COLUMNNAME_AD_EntityType_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get ModelPackage.
     *
     * @return Java Package of the model classes
     */
    public String getModelPackage() {
        return getValue(COLUMNNAME_ModelPackage);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
