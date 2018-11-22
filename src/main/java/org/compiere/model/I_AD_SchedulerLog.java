package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_SchedulerLog
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_SchedulerLog {

  /** TableName=AD_SchedulerLog */
  public static final String Table_Name = "AD_SchedulerLog";

  /** AD_Table_ID=687 */
  public static final int Table_ID = 687;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Scheduler_ID */
  public static final String COLUMNNAME_AD_Scheduler_ID = "AD_Scheduler_ID";

  /** Set Scheduler. Schedule Processes */
  public void setAD_Scheduler_ID(int AD_Scheduler_ID);

  /** Get Scheduler. Schedule Processes */
  public int getAD_Scheduler_ID();

  public I_AD_Scheduler getAD_Scheduler() throws RuntimeException;

  /** Column name AD_SchedulerLog_ID */
  public static final String COLUMNNAME_AD_SchedulerLog_ID = "AD_SchedulerLog_ID";

  /** Set Scheduler Log. Result of the execution of the Scheduler */
  public void setAD_SchedulerLog_ID(int AD_SchedulerLog_ID);

  /** Get Scheduler Log. Result of the execution of the Scheduler */
  public int getAD_SchedulerLog_ID();

  /** Column name AD_SchedulerLog_UU */
  public static final String COLUMNNAME_AD_SchedulerLog_UU = "AD_SchedulerLog_UU";

  /** Set AD_SchedulerLog_UU */
  public void setAD_SchedulerLog_UU(String AD_SchedulerLog_UU);

  /** Get AD_SchedulerLog_UU */
  public String getAD_SchedulerLog_UU();

  /** Column name BinaryData */
  public static final String COLUMNNAME_BinaryData = "BinaryData";

  /** Set Binary Data. Binary Data */
  public void setBinaryData(byte[] BinaryData);

  /** Get Binary Data. Binary Data */
  public byte[] getBinaryData();

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

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name IsError */
  public static final String COLUMNNAME_IsError = "IsError";

  /** Set Error. An Error occurred in the execution */
  public void setIsError(boolean IsError);

  /** Get Error. An Error occurred in the execution */
  public boolean isError();

  /** Column name Reference */
  public static final String COLUMNNAME_Reference = "Reference";

  /** Set Reference. Reference for this record */
  public void setReference(String Reference);

  /** Get Reference. Reference for this record */
  public String getReference();

  /** Column name Summary */
  public static final String COLUMNNAME_Summary = "Summary";

  /** Set Summary. Textual summary of this request */
  public void setSummary(String Summary);

  /** Get Summary. Textual summary of this request */
  public String getSummary();

  /** Column name TextMsg */
  public static final String COLUMNNAME_TextMsg = "TextMsg";

  /** Set Text Message. Text Message */
  public void setTextMsg(String TextMsg);

  /** Get Text Message. Text Message */
  public String getTextMsg();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
