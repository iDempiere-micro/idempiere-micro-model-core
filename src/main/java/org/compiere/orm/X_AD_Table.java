package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.Table;
import org.compiere.model.ValidationRule;
import software.hsharp.core.orm.MBaseTableKt;

/**
 * Generated Model for AD_Table
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public abstract class X_AD_Table extends PO implements Table {

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
    public X_AD_Table(int AD_Table_ID) {
        super(AD_Table_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Table(Row row) {
        super(row);
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
        return "X_AD_Table[" + getId() + "]";
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

        setValue(COLUMNNAME_AccessLevel, AccessLevel);
    }

    /**
     * Get Table.
     *
     * @return Database Table information
     */
    public int getTableTableId() {
        Integer ii = getValue(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    public ValidationRule getValRule() throws RuntimeException {
        return (ValidationRule)
                MBaseTableKt.getTable(ValidationRule.Table_Name)
                        .getPO(getValRuleId());
    }

    /**
     * Get Dynamic Validation.
     *
     * @return Dynamic Validation Rule
     */
    public int getValRuleId() {
        Integer ii = getValue(COLUMNNAME_AD_Val_Rule_ID);
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

        setValue(COLUMNNAME_EntityType, EntityType);
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
        setValue(COLUMNNAME_IsChangeLog, IsChangeLog);
    }

    /**
     * Set Records deletable.
     *
     * @param IsDeleteable Indicates if records can be deleted from the database
     */
    public void setIsDeleteable(boolean IsDeleteable) {
        setValue(COLUMNNAME_IsDeleteable, IsDeleteable);
    }

    /**
     * Get Records deletable.
     *
     * @return Indicates if records can be deleted from the database
     */
    public boolean isDeletable() {
        Object oo = getValue(COLUMNNAME_IsDeleteable);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
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
        setValue(COLUMNNAME_IsHighVolume, IsHighVolume);
    }

    /**
     * Set Security enabled.
     *
     * @param IsSecurityEnabled If security is enabled, user access to data can be restricted via
     *                          Roles
     */
    public void setIsSecurityEnabled(boolean IsSecurityEnabled) {
        setValue(COLUMNNAME_IsSecurityEnabled, IsSecurityEnabled);
    }

    /**
     * Set View.
     *
     * @param IsView This is a view
     */
    public void setIsView(boolean IsView) {
        setValue(COLUMNNAME_IsView, IsView);
    }

    /**
     * Get View.
     *
     * @return This is a view
     */
    public boolean isView() {
        Object oo = getValue(COLUMNNAME_IsView);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Name.
     *
     * @return Alphanumeric identifier of the entity
     * <p>
     * HasName.COLUMNNAME_Name
     */
    public String getName() {
        return (String) getValue(HasName.COLUMNNAME_Name);
    }

    /**
     * Set Replication Type.
     *
     * @param ReplicationType Type of Data Replication
     */
    public void setReplicationType(String ReplicationType) {

        setValue(COLUMNNAME_ReplicationType, ReplicationType);
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
