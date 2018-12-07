package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;

import kotliquery.Row;
import org.compiere.model.I_AD_EntityType;
import org.idempiere.orm.I_Persistent;

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
    public X_AD_EntityType(Properties ctx, int AD_EntityType_ID, String trxName) {
        super(ctx, AD_EntityType_ID, trxName);
    }

    /**
     * Load Constructor
     */
    public X_AD_EntityType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
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
    public int getAD_EntityType_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_EntityType_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Entity Type.
     *
     * @param AD_EntityType_ID System Entity Type
     */
    public void setAD_EntityType_ID(int AD_EntityType_ID) {
        if (AD_EntityType_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_EntityType_ID, null);
        else set_ValueNoCheck(COLUMNNAME_AD_EntityType_ID, Integer.valueOf(AD_EntityType_ID));
    }

    /**
     * Get AD_EntityType_UU.
     *
     * @return AD_EntityType_UU
     */
    public String getAD_EntityType_UU() {
        return (String) get_Value(COLUMNNAME_AD_EntityType_UU);
    }

    /**
     * Set AD_EntityType_UU.
     *
     * @param AD_EntityType_UU AD_EntityType_UU
     */
    public void setAD_EntityType_UU(String AD_EntityType_UU) {
        set_Value(COLUMNNAME_AD_EntityType_UU, AD_EntityType_UU);
    }

    /**
     * Get Classpath.
     *
     * @return Extension Classpath
     */
    public String getClasspath() {
        return (String) get_Value(COLUMNNAME_Classpath);
    }

    /**
     * Set Classpath.
     *
     * @param Classpath Extension Classpath
     */
    public void setClasspath(String Classpath) {
        set_Value(COLUMNNAME_Classpath, Classpath);
    }

    /**
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return (String) get_Value(COLUMNNAME_Description);
    }

    /**
     * Set Description.
     *
     * @param Description Optional short description of the record
     */
    public void setDescription(String Description) {
        set_Value(COLUMNNAME_Description, Description);
    }

    /**
     * Get Entity Type.
     *
     * @return Dictionary Entity Type; Determines ownership and synchronization
     */
    public String getEntityType() {
        return (String) get_Value(COLUMNNAME_EntityType);
    }

    /**
     * Set Entity Type.
     *
     * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
     */
    public void setEntityType(String EntityType) {
        set_ValueNoCheck(COLUMNNAME_EntityType, EntityType);
    }

    /**
     * Get Comment/Help.
     *
     * @return Comment or Hint
     */
    public String getHelp() {
        return (String) get_Value(COLUMNNAME_Help);
    }

    /**
     * Set Comment/Help.
     *
     * @param Help Comment or Hint
     */
    public void setHelp(String Help) {
        set_Value(COLUMNNAME_Help, Help);
    }

    /**
     * Get ModelPackage.
     *
     * @return Java Package of the model classes
     */
    public String getModelPackage() {
        return (String) get_Value(COLUMNNAME_ModelPackage);
    }

    /**
     * Set ModelPackage.
     *
     * @param ModelPackage Java Package of the model classes
     */
    public void setModelPackage(String ModelPackage) {
        set_Value(COLUMNNAME_ModelPackage, ModelPackage);
    }

    /**
     * Get Process Now.
     *
     * @return Process Now
     */
    public boolean isProcessing() {
        Object oo = get_Value(COLUMNNAME_Processing);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Process Now.
     *
     * @param Processing Process Now
     */
    public void setProcessing(boolean Processing) {
        set_Value(COLUMNNAME_Processing, Boolean.valueOf(Processing));
    }

    /**
     * Get Version.
     *
     * @return Version of the table definition
     */
    public String getVersion() {
        return (String) get_Value(COLUMNNAME_Version);
    }

    /**
     * Set Version.
     *
     * @param Version Version of the table definition
     */
    public void setVersion(String Version) {
        set_Value(COLUMNNAME_Version, Version);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
