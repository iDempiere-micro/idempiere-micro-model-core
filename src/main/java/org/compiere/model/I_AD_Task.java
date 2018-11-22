package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Task
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Task {

  /** TableName=AD_Task */
  public static final String Table_Name = "AD_Task";

  /** AD_Table_ID=118 */
  public static final int Table_ID = 118;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Column name AccessLevel */
  public static final String COLUMNNAME_AccessLevel = "AccessLevel";

  /** Set Data Access Level. Access Level required */
  public void setAccessLevel(String AccessLevel);

  /** Get Data Access Level. Access Level required */
  public String getAccessLevel();

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_CtxHelp_ID */
  public static final String COLUMNNAME_AD_CtxHelp_ID = "AD_CtxHelp_ID";

  /** Set Context Help */
  public void setAD_CtxHelp_ID(int AD_CtxHelp_ID);

  /** Get Context Help */
  public int getAD_CtxHelp_ID();

  public I_AD_CtxHelp getAD_CtxHelp() throws RuntimeException;

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Task_ID */
  public static final String COLUMNNAME_AD_Task_ID = "AD_Task_ID";

  /** Set OS Task. Operation System Task */
  public void setAD_Task_ID(int AD_Task_ID);

  /** Get OS Task. Operation System Task */
  public int getAD_Task_ID();

  /** Column name AD_Task_UU */
  public static final String COLUMNNAME_AD_Task_UU = "AD_Task_UU";

  /** Set AD_Task_UU */
  public void setAD_Task_UU(String AD_Task_UU);

  /** Get AD_Task_UU */
  public String getAD_Task_UU();

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

  /** Column name EntityType */
  public static final String COLUMNNAME_EntityType = "EntityType";

  /** Set Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public void setEntityType(String EntityType);

  /** Get Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public String getEntityType();

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

  /** Column name IsServerProcess */
  public static final String COLUMNNAME_IsServerProcess = "IsServerProcess";

  /** Set Server Process. Run this Process on Server only */
  public void setIsServerProcess(boolean IsServerProcess);

  /** Get Server Process. Run this Process on Server only */
  public boolean isServerProcess();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name OS_Command */
  public static final String COLUMNNAME_OS_Command = "OS_Command";

  /** Set OS Command. Operating System Command */
  public void setOS_Command(String OS_Command);

  /** Get OS Command. Operating System Command */
  public String getOS_Command();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
