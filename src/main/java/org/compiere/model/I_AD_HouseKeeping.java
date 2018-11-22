package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_HouseKeeping
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_HouseKeeping {

  /** TableName=AD_HouseKeeping */
  public static final String Table_Name = "AD_HouseKeeping";

  /** AD_Table_ID=53147 */
  public static final int Table_ID = 53147;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_HouseKeeping_ID */
  public static final String COLUMNNAME_AD_HouseKeeping_ID = "AD_HouseKeeping_ID";

  /** Set House Keeping Configuration */
  public void setAD_HouseKeeping_ID(int AD_HouseKeeping_ID);

  /** Get House Keeping Configuration */
  public int getAD_HouseKeeping_ID();

  /** Column name AD_HouseKeeping_UU */
  public static final String COLUMNNAME_AD_HouseKeeping_UU = "AD_HouseKeeping_UU";

  /** Set AD_HouseKeeping_UU */
  public void setAD_HouseKeeping_UU(String AD_HouseKeeping_UU);

  /** Get AD_HouseKeeping_UU */
  public String getAD_HouseKeeping_UU();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Table_ID */
  public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

  /** Set Table. Database Table information */
  public void setAD_Table_ID(int AD_Table_ID);

  /** Get Table. Database Table information */
  public int getAD_Table_ID();

  public I_AD_Table getAD_Table() throws RuntimeException;

  /** Column name BackupFolder */
  public static final String COLUMNNAME_BackupFolder = "BackupFolder";

  /** Set Backup Folder. Backup Folder */
  public void setBackupFolder(String BackupFolder);

  /** Get Backup Folder. Backup Folder */
  public String getBackupFolder();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name Description */
  public static final String COLUMNNAME_Description = "Description";

  /** Set Description. Optional short description of the record */
  public void setDescription(String Description);

  /** Get Description. Optional short description of the record */
  public String getDescription();

  /** Column name Help */
  public static final String COLUMNNAME_Help = "Help";

  /** Set Comment/Help. Comment or Hint */
  public void setHelp(String Help);

  /** Get Comment/Help. Comment or Hint */
  public String getHelp();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name IsExportXMLBackup */
  public static final String COLUMNNAME_IsExportXMLBackup = "IsExportXMLBackup";

  /** Set Export XML Backup */
  public void setIsExportXMLBackup(boolean IsExportXMLBackup);

  /** Get Export XML Backup */
  public boolean isExportXMLBackup();

  /** Column name IsSaveInHistoric */
  public static final String COLUMNNAME_IsSaveInHistoric = "IsSaveInHistoric";

  /** Set Save In Historic */
  public void setIsSaveInHistoric(boolean IsSaveInHistoric);

  /** Get Save In Historic */
  public boolean isSaveInHistoric();

  /** Column name LastDeleted */
  public static final String COLUMNNAME_LastDeleted = "LastDeleted";

  /** Set Last Deleted */
  public void setLastDeleted(int LastDeleted);

  /** Get Last Deleted */
  public int getLastDeleted();

  /** Column name LastRun */
  public static final String COLUMNNAME_LastRun = "LastRun";

  /** Set Last Run */
  public void setLastRun(Timestamp LastRun);

  /** Get Last Run */
  public Timestamp getLastRun();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name Processing */
  public static final String COLUMNNAME_Processing = "Processing";

  /** Set Process Now */
  public void setProcessing(boolean Processing);

  /** Get Process Now */
  public boolean isProcessing();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name Value */
  public static final String COLUMNNAME_Value = "Value";

  /** Set Search Key. Search key for the record in the format required - must be unique */
  public void setValue(String Value);

  /** Get Search Key. Search key for the record in the format required - must be unique */
  public String getValue();

  /** Column name WhereClause */
  public static final String COLUMNNAME_WhereClause = "WhereClause";

  /** Set Sql WHERE. Fully qualified SQL WHERE clause */
  public void setWhereClause(String WhereClause);

  /** Get Sql WHERE. Fully qualified SQL WHERE clause */
  public String getWhereClause();
}
