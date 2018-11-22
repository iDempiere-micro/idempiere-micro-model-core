package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Scheduler_Para
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Scheduler_Para {

  /** TableName=AD_Scheduler_Para */
  public static final String Table_Name = "AD_Scheduler_Para";

  /** AD_Table_ID=698 */
  public static final int Table_ID = 698;

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

  /** Column name AD_Process_Para_ID */
  public static final String COLUMNNAME_AD_Process_Para_ID = "AD_Process_Para_ID";

  /** Set Process Parameter */
  public void setAD_Process_Para_ID(int AD_Process_Para_ID);

  /** Get Process Parameter */
  public int getAD_Process_Para_ID();

  public I_AD_Process_Para getAD_Process_Para() throws RuntimeException;

  /** Column name AD_Scheduler_ID */
  public static final String COLUMNNAME_AD_Scheduler_ID = "AD_Scheduler_ID";

  /** Set Scheduler. Schedule Processes */
  public void setAD_Scheduler_ID(int AD_Scheduler_ID);

  /** Get Scheduler. Schedule Processes */
  public int getAD_Scheduler_ID();

  public I_AD_Scheduler getAD_Scheduler() throws RuntimeException;

  /** Column name AD_Scheduler_Para_UU */
  public static final String COLUMNNAME_AD_Scheduler_Para_UU = "AD_Scheduler_Para_UU";

  /** Set AD_Scheduler_Para_UU */
  public void setAD_Scheduler_Para_UU(String AD_Scheduler_Para_UU);

  /** Get AD_Scheduler_Para_UU */
  public String getAD_Scheduler_Para_UU();

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

  /** Column name ParameterDefault */
  public static final String COLUMNNAME_ParameterDefault = "ParameterDefault";

  /** Set Default Parameter. Default value of the parameter */
  public void setParameterDefault(String ParameterDefault);

  /** Get Default Parameter. Default value of the parameter */
  public String getParameterDefault();

  /** Column name ParameterToDefault */
  public static final String COLUMNNAME_ParameterToDefault = "ParameterToDefault";

  /** Set Default To Parameter. Default value of the to parameter */
  public void setParameterToDefault(String ParameterToDefault);

  /** Get Default To Parameter. Default value of the to parameter */
  public String getParameterToDefault();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
