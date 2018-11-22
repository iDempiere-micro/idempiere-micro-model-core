package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_WF_ActivityApprover
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_WF_ActivityApprover {

  /** TableName=AD_WF_ActivityApprover */
  public static final String Table_Name = "AD_WF_ActivityApprover";

  /** AD_Table_ID=200226 */
  public static final int Table_ID = 200226;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 7 - System - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(7);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_User_ID */
  public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

  /** Set User/Contact. User within the system - Internal or Business Partner Contact */
  public void setAD_User_ID(int AD_User_ID);

  /** Get User/Contact. User within the system - Internal or Business Partner Contact */
  public int getAD_User_ID();

  public I_AD_User getAD_User() throws RuntimeException;

  /** Column name AD_WF_ActivityApprover_ID */
  public static final String COLUMNNAME_AD_WF_ActivityApprover_ID = "AD_WF_ActivityApprover_ID";

  /** Set Workflow Activity Approver */
  public void setAD_WF_ActivityApprover_ID(int AD_WF_ActivityApprover_ID);

  /** Get Workflow Activity Approver */
  public int getAD_WF_ActivityApprover_ID();

  /** Column name AD_WF_ActivityApprover_UU */
  public static final String COLUMNNAME_AD_WF_ActivityApprover_UU = "AD_WF_ActivityApprover_UU";

  /** Set AD_WF_ActivityApprover_UU */
  public void setAD_WF_ActivityApprover_UU(String AD_WF_ActivityApprover_UU);

  /** Get AD_WF_ActivityApprover_UU */
  public String getAD_WF_ActivityApprover_UU();

  /** Column name AD_WF_Activity_ID */
  public static final String COLUMNNAME_AD_WF_Activity_ID = "AD_WF_Activity_ID";

  /** Set Workflow Activity. Workflow Activity */
  public void setAD_WF_Activity_ID(int AD_WF_Activity_ID);

  /** Get Workflow Activity. Workflow Activity */
  public int getAD_WF_Activity_ID();

  public I_AD_WF_Activity getAD_WF_Activity() throws RuntimeException;

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
