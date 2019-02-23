package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.I_AD_Table;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Table
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Table extends PO implements I_AD_Table, I_Persistent {

    /**
     * Client+Organization = 3
     */
    public static final String ACCESSLEVEL_ClientPlusOrganization = "3";
    /**
     * System only = 4
     */
    public static final String ACCESSLEVEL_SystemOnly = "4";
    /**
     * All = 7
     */
    public static final String ACCESSLEVEL_All = "7";
    /**
     * System+Client = 6
     */
    public static final String ACCESSLEVEL_SystemPlusClient = "6";
    /**
     * Client only = 2
     */
    public static final String ACCESSLEVEL_ClientOnly = "2";
    /**
     * Local = L
     */
    public static final String REPLICATIONTYPE_Local = "L";
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Table(Properties ctx, int AD_Table_ID) {
        super(ctx, AD_Table_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Table(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_AD_Table(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * AccessLevel
     *
     * @return 4 - System
     */
    public int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_AD_Table[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Data Access Level.
     *
     * @return Access Level required
     */
    public String getTableAccessLevel() {
        return (String) getValue(COLUMNNAME_AccessLevel);
    }

    /**
     * Set Data Access Level.
     *
     * @param AccessLevel Access Level required
     */
    public void setTableAccessLevel(String AccessLevel) {

        set_Value(COLUMNNAME_AccessLevel, AccessLevel);
    }

    /**
     * Get Table.
     *
     * @return Database Table information
     */
    public int getTableTableId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    public org.compiere.model.I_AD_Val_Rule getValRule() throws RuntimeException {
        return (org.compiere.model.I_AD_Val_Rule)
                MTable.get(getCtx(), org.compiere.model.I_AD_Val_Rule.Table_Name)
                        .getPO(getValRuleId());
    }

    /**
     * Get Dynamic Validation.
     *
     * @return Dynamic Validation Rule
     */
    public int getValRuleId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Val_Rule_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return (String) getValue(COLUMNNAME_Description);
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

        set_Value(COLUMNNAME_EntityType, EntityType);
    }

    /**
     * Get Comment/Help.
     *
     * @return Comment or Hint
     */
    public String getHelp() {
        return (String) getValue(COLUMNNAME_Help);
    }

    /**
     * Set Maintain Change Log.
     *
     * @param IsChangeLog Maintain a log of changes
     */
    public void setIsChangeLog(boolean IsChangeLog) {
        set_Value(COLUMNNAME_IsChangeLog, Boolean.valueOf(IsChangeLog));
    }

    /**
     * Set Records deletable.
     *
     * @param IsDeleteable Indicates if records can be deleted from the database
     */
    public void setIsDeleteable(boolean IsDeleteable) {
        set_Value(COLUMNNAME_IsDeleteable, Boolean.valueOf(IsDeleteable));
    }

    /**
     * Get Records deletable.
     *
     * @return Indicates if records can be deleted from the database
     */
    public boolean isDeletable() {
        Object oo = getValue(COLUMNNAME_IsDeleteable);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set High Volume.
     *
     * @param IsHighVolume Use Search instead of Pick list
     */
    public void setIsHighVolume(boolean IsHighVolume) {
        set_Value(COLUMNNAME_IsHighVolume, Boolean.valueOf(IsHighVolume));
    }

    /**
     * Set Security enabled.
     *
     * @param IsSecurityEnabled If security is enabled, user access to data can be restricted via
     *                          Roles
     */
    public void setIsSecurityEnabled(boolean IsSecurityEnabled) {
        set_Value(COLUMNNAME_IsSecurityEnabled, Boolean.valueOf(IsSecurityEnabled));
    }

    /**
     * Set View.
     *
     * @param IsView This is a view
     */
    public void setIsView(boolean IsView) {
        set_Value(COLUMNNAME_IsView, Boolean.valueOf(IsView));
    }

    /**
     * Get View.
     *
     * @return This is a view
     */
    public boolean isView() {
        Object oo = getValue(COLUMNNAME_IsView);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Name.
     *
     * @return Alphanumeric identifier of the entity
     */
    public String getName() {
        return (String) getValue(HasName.Companion.getCOLUMNNAME_Name());
    }

    /**
     * Set Replication Type.
     *
     * @param ReplicationType Type of Data Replication
     */
    public void setReplicationType(String ReplicationType) {

        set_Value(COLUMNNAME_ReplicationType, ReplicationType);
    }

    /**
     * Get DB Table Name.
     *
     * @return Name of the table in the database
     */
    public String getDbTableName() {
        return (String) getValue(COLUMNNAME_TableName);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
