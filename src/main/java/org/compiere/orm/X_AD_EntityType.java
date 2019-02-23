package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_EntityType;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_EntityType
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_EntityType extends BasePOName implements I_AD_EntityType, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_EntityType(Properties ctx, int AD_EntityType_ID) {
        super(ctx, AD_EntityType_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_EntityType(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_AD_EntityType(Properties ctx, Row row) {
        super(ctx, row);
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
        StringBuffer sb = new StringBuffer("X_AD_EntityType[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Entity Type.
     *
     * @return System Entity Type
     */
    public int getEntityTypeId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_EntityType_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get ModelPackage.
     *
     * @return Java Package of the model classes
     */
    public String getModelPackage() {
        return (String) getValue(COLUMNNAME_ModelPackage);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
