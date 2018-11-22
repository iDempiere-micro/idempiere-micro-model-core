package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_MigrationScript
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_MigrationScript {

  /** TableName=AD_MigrationScript */
  public static final String Table_Name = "AD_MigrationScript";

  /** AD_Table_ID=53064 */
  public static final int Table_ID = 53064;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_MigrationScript_ID */
  public static final String COLUMNNAME_AD_MigrationScript_ID = "AD_MigrationScript_ID";

  /** Set Migration Script. Table to check whether the migration script has been applied */
  public void setAD_MigrationScript_ID(int AD_MigrationScript_ID);

  /** Get Migration Script. Table to check whether the migration script has been applied */
  public int getAD_MigrationScript_ID();

  /** Column name AD_MigrationScript_UU */
  public static final String COLUMNNAME_AD_MigrationScript_UU = "AD_MigrationScript_UU";

  /** Set AD_MigrationScript_UU */
  public void setAD_MigrationScript_UU(String AD_MigrationScript_UU);

  /** Get AD_MigrationScript_UU */
  public String getAD_MigrationScript_UU();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

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

  /** Column name DeveloperName */
  public static final String COLUMNNAME_DeveloperName = "DeveloperName";

  /** Set Developer Name */
  public void setDeveloperName(String DeveloperName);

  /** Get Developer Name */
  public String getDeveloperName();

  /** Column name FileName */
  public static final String COLUMNNAME_FileName = "FileName";

  /** Set File Name. Name of the local file or URL */
  public void setFileName(String FileName);

  /** Get File Name. Name of the local file or URL */
  public String getFileName();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name isApply */
  public static final String COLUMNNAME_isApply = "isApply";

  /** Set Apply Script */
  public void setisApply(boolean isApply);

  /** Get Apply Script */
  public boolean isApply();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name ProjectName */
  public static final String COLUMNNAME_ProjectName = "ProjectName";

  /** Set Project. Name of the Project */
  public void setProjectName(String ProjectName);

  /** Get Project. Name of the Project */
  public String getProjectName();

  /** Column name Reference */
  public static final String COLUMNNAME_Reference = "Reference";

  /** Set Reference. Reference for this record */
  public void setReference(String Reference);

  /** Get Reference. Reference for this record */
  public String getReference();

  /** Column name ReleaseNo */
  public static final String COLUMNNAME_ReleaseNo = "ReleaseNo";

  /** Set Release No. Internal Release Number */
  public void setReleaseNo(String ReleaseNo);

  /** Get Release No. Internal Release Number */
  public String getReleaseNo();

  /** Column name Script */
  public static final String COLUMNNAME_Script = "Script";

  /** Set Script. Dynamic Java Language Script to calculate result */
  public void setScript(byte[] Script);

  /** Get Script. Dynamic Java Language Script to calculate result */
  public byte[] getScript();

  /** Column name ScriptRoll */
  public static final String COLUMNNAME_ScriptRoll = "ScriptRoll";

  /** Set Roll the Script */
  public void setScriptRoll(String ScriptRoll);

  /** Get Roll the Script */
  public String getScriptRoll();

  /** Column name Status */
  public static final String COLUMNNAME_Status = "Status";

  /** Set Status. Status of the currently running check */
  public void setStatus(String Status);

  /** Get Status. Status of the currently running check */
  public String getStatus();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name URL */
  public static final String COLUMNNAME_URL = "URL";

  /** Set URL. Full URL address - e.g. http://www.idempiere.org */
  public void setURL(String URL);

  /** Get URL. Full URL address - e.g. http://www.idempiere.org */
  public String getURL();
}
