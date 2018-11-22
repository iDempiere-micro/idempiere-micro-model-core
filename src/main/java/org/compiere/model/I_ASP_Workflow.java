package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for ASP_Workflow
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_ASP_Workflow {

  /** TableName=ASP_Workflow */
  public static final String Table_Name = "ASP_Workflow";

  /** AD_Table_ID=53053 */
  public static final int Table_ID = 53053;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Workflow_ID */
  public static final String COLUMNNAME_AD_Workflow_ID = "AD_Workflow_ID";

  /** Set Workflow. Workflow or combination of tasks */
  public void setAD_Workflow_ID(int AD_Workflow_ID);

  /** Get Workflow. Workflow or combination of tasks */
  public int getAD_Workflow_ID();

  public I_AD_Workflow getAD_Workflow() throws RuntimeException;

  /** Column name ASP_Level_ID */
  public static final String COLUMNNAME_ASP_Level_ID = "ASP_Level_ID";

  /** Set ASP Level */
  public void setASP_Level_ID(int ASP_Level_ID);

  /** Get ASP Level */
  public int getASP_Level_ID();

  public I_ASP_Level getASP_Level() throws RuntimeException;

  /** Column name ASP_Status */
  public static final String COLUMNNAME_ASP_Status = "ASP_Status";

  /** Set ASP Status */
  public void setASP_Status(String ASP_Status);

  /** Get ASP Status */
  public String getASP_Status();

  /** Column name ASP_Workflow_ID */
  public static final String COLUMNNAME_ASP_Workflow_ID = "ASP_Workflow_ID";

  /** Set ASP Workflow */
  public void setASP_Workflow_ID(int ASP_Workflow_ID);

  /** Get ASP Workflow */
  public int getASP_Workflow_ID();

  /** Column name ASP_Workflow_UU */
  public static final String COLUMNNAME_ASP_Workflow_UU = "ASP_Workflow_UU";

  /** Set ASP_Workflow_UU */
  public void setASP_Workflow_UU(String ASP_Workflow_UU);

  /** Get ASP_Workflow_UU */
  public String getASP_Workflow_UU();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
