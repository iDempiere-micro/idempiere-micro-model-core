package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.I_AD_Table;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Table
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Table extends PO implements I_AD_Table, I_Persistent {

  /** AccessLevel AD_Reference_ID=5 */
  public static final int ACCESSLEVEL_AD_Reference_ID = 5;
  /** Client+Organization = 3 */
  public static final String ACCESSLEVEL_ClientPlusOrganization = "3";
  /** System only = 4 */
  public static final String ACCESSLEVEL_SystemOnly = "4";
  /** All = 7 */
  public static final String ACCESSLEVEL_All = "7";
  /** System+Client = 6 */
  public static final String ACCESSLEVEL_SystemPlusClient = "6";
  /** Client only = 2 */
  public static final String ACCESSLEVEL_ClientOnly = "2";
  /** EntityType AD_Reference_ID=389 */
  public static final int ENTITYTYPE_AD_Reference_ID = 389;
  /** ReplicationType AD_Reference_ID=126 */
  public static final int REPLICATIONTYPE_AD_Reference_ID = 126;
  /** Local = L */
  public static final String REPLICATIONTYPE_Local = "L";
  /** Merge = M */
  public static final String REPLICATIONTYPE_Merge = "M";
  /** Reference = R */
  public static final String REPLICATIONTYPE_Reference = "R";
  /** Broadcast = B */
  public static final String REPLICATIONTYPE_Broadcast = "B";
  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Table(Properties ctx, int AD_Table_ID, String trxName) {
    super(ctx, AD_Table_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_Table(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
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
    return (String) get_Value(COLUMNNAME_AccessLevel);
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
  public int getAD_Table_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Table_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Table.
   *
   * @param AD_Table_ID Database Table information
   */
  public void setAD_Table_ID(int AD_Table_ID) {
    if (AD_Table_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Table_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
  }

  /**
   * Get AD_Table_UU.
   *
   * @return AD_Table_UU
   */
  public String getAD_Table_UU() {
    return (String) get_Value(COLUMNNAME_AD_Table_UU);
  }

  /**
   * Set AD_Table_UU.
   *
   * @param AD_Table_UU AD_Table_UU
   */
  public void setAD_Table_UU(String AD_Table_UU) {
    set_Value(COLUMNNAME_AD_Table_UU, AD_Table_UU);
  }

  public org.compiere.model.I_AD_Val_Rule getValRule() throws RuntimeException {
    return (org.compiere.model.I_AD_Val_Rule)
        MTable.get(getCtx(), org.compiere.model.I_AD_Val_Rule.Table_Name)
            .getPO(getValRule_ID(), null);
  }

  /**
   * Set Dynamic Validation.
   *
   * @param AD_Val_Rule_ID Dynamic Validation Rule
   */
  public void setAD_Val_Rule_ID(int AD_Val_Rule_ID) {
    if (AD_Val_Rule_ID < 1) set_Value(COLUMNNAME_AD_Val_Rule_ID, null);
    else set_Value(COLUMNNAME_AD_Val_Rule_ID, Integer.valueOf(AD_Val_Rule_ID));
  }

  /**
   * Get Dynamic Validation.
   *
   * @return Dynamic Validation Rule
   */
  public int getValRule_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Val_Rule_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Get Window.
   *
   * @return Data entry or display window
   */
  public int getAD_Window_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Window_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Window.
   *
   * @param AD_Window_ID Data entry or display window
   */
  public void setAD_Window_ID(int AD_Window_ID) {
    if (AD_Window_ID < 1) set_Value(COLUMNNAME_AD_Window_ID, null);
    else set_Value(COLUMNNAME_AD_Window_ID, Integer.valueOf(AD_Window_ID));
  }

  /**
   * Get Copy Columns From Table.
   *
   * @return Copy Columns From Table
   */
  public String getCopyColumnsFromTable() {
    return (String) get_Value(COLUMNNAME_CopyColumnsFromTable);
  }

  /**
   * Set Copy Columns From Table.
   *
   * @param CopyColumnsFromTable Copy Columns From Table
   */
  public void setCopyColumnsFromTable(String CopyColumnsFromTable) {
    set_Value(COLUMNNAME_CopyColumnsFromTable, CopyColumnsFromTable);
  }

  /**
   * Get Copy Components From View.
   *
   * @return Copy Components From View
   */
  public String getCopyComponentsFromView() {
    return (String) get_Value(COLUMNNAME_CopyComponentsFromView);
  }

  /**
   * Set Copy Components From View.
   *
   * @param CopyComponentsFromView Copy Components From View
   */
  public void setCopyComponentsFromView(String CopyComponentsFromView) {
    set_Value(COLUMNNAME_CopyComponentsFromView, CopyComponentsFromView);
  }

  /**
   * Get Drop view.
   *
   * @return Drop view
   */
  public String getDatabaseViewDrop() {
    return (String) get_Value(COLUMNNAME_DatabaseViewDrop);
  }

  /**
   * Set Drop view.
   *
   * @param DatabaseViewDrop Drop view
   */
  public void setDatabaseViewDrop(String DatabaseViewDrop) {
    set_Value(COLUMNNAME_DatabaseViewDrop, DatabaseViewDrop);
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

    set_Value(COLUMNNAME_EntityType, EntityType);
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
   * Get Import Table.
   *
   * @return Import Table Columns from Database
   */
  public String getImportTable() {
    return (String) get_Value(COLUMNNAME_ImportTable);
  }

  /**
   * Set Import Table.
   *
   * @param ImportTable Import Table Columns from Database
   */
  public void setImportTable(String ImportTable) {
    set_Value(COLUMNNAME_ImportTable, ImportTable);
  }

  /**
   * Set Centrally maintained.
   *
   * @param IsCentrallyMaintained Information maintained in System Element table
   */
  public void setIsCentrallyMaintained(boolean IsCentrallyMaintained) {
    set_Value(COLUMNNAME_IsCentrallyMaintained, Boolean.valueOf(IsCentrallyMaintained));
  }

  /**
   * Get Centrally maintained.
   *
   * @return Information maintained in System Element table
   */
  public boolean isCentrallyMaintained() {
    Object oo = get_Value(COLUMNNAME_IsCentrallyMaintained);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get Maintain Change Log.
   *
   * @return Maintain a log of changes
   */
  public boolean isChangeLog() {
    Object oo = get_Value(COLUMNNAME_IsChangeLog);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
  public boolean isDeleteable() {
    Object oo = get_Value(COLUMNNAME_IsDeleteable);
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
   * Get High Volume.
   *
   * @return Use Search instead of Pick list
   */
  public boolean isHighVolume() {
    Object oo = get_Value(COLUMNNAME_IsHighVolume);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Security enabled.
   *
   * @param IsSecurityEnabled If security is enabled, user access to data can be restricted via
   *     Roles
   */
  public void setIsSecurityEnabled(boolean IsSecurityEnabled) {
    set_Value(COLUMNNAME_IsSecurityEnabled, Boolean.valueOf(IsSecurityEnabled));
  }

  /**
   * Get Security enabled.
   *
   * @return If security is enabled, user access to data can be restricted via Roles
   */
  public boolean isSecurityEnabled() {
    Object oo = get_Value(COLUMNNAME_IsSecurityEnabled);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
    Object oo = get_Value(COLUMNNAME_IsView);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Get Sequence.
   *
   * @return Sequence
   */
  public int getLoadSeq() {
    Integer ii = (Integer) get_Value(COLUMNNAME_LoadSeq);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Sequence.
   *
   * @param LoadSeq Sequence
   */
  public void setLoadSeq(int LoadSeq) {
    set_ValueNoCheck(COLUMNNAME_LoadSeq, Integer.valueOf(LoadSeq));
  }

  /**
   * Get Name.
   *
   * @return Alphanumeric identifier of the entity
   */
  public String getName() {
    return (String) get_Value(HasName.Companion.getCOLUMNNAME_Name());
  }

  /**
   * Set Name.
   *
   * @param Name Alphanumeric identifier of the entity
   */
  public void setName(String Name) {
    set_Value(HasName.Companion.getCOLUMNNAME_Name(), Name);
  }

  /**
   * Get PO Window.
   *
   * @return Purchase Order Window
   */
  public int getPO_Window_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_PO_Window_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set PO Window.
   *
   * @param PO_Window_ID Purchase Order Window
   */
  public void setPO_Window_ID(int PO_Window_ID) {
    if (PO_Window_ID < 1) set_Value(COLUMNNAME_PO_Window_ID, null);
    else set_Value(COLUMNNAME_PO_Window_ID, Integer.valueOf(PO_Window_ID));
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
   * Get Replication Type.
   *
   * @return Type of Data Replication
   */
  public String getReplicationType() {
    return (String) get_Value(COLUMNNAME_ReplicationType);
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
  public String getTableName() {
    return (String) get_Value(COLUMNNAME_TableName);
  }

  /**
   * Set DB Table Name.
   *
   * @param TableName Name of the table in the database
   */
  public void setTableName(String TableName) {
    set_Value(COLUMNNAME_TableName, TableName);
  }

  /**
   * Get Record ID/ColumnName
   *
   * @return ID/ColumnName pair
   */
  public KeyNamePair getKeyNamePair() {
    return new KeyNamePair(getId(), getTableName());
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
