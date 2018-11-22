package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for PA_DashboardContent_Access
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_PA_DashboardContent_Access {

  /** TableName=PA_DashboardContent_Access */
  public static final String Table_Name = "PA_DashboardContent_Access";

  /** AD_Table_ID=200106 */
  public static final int Table_ID = 200106;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 3 - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(3);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Role_ID */
  public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

  /** Set Role. Responsibility Role */
  public void setAD_Role_ID(int AD_Role_ID);

  /** Get Role. Responsibility Role */
  public int getAD_Role_ID();

  public I_AD_Role getAD_Role() throws RuntimeException;

  /** Column name AD_User_ID */
  public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

  /** Set User/Contact. User within the system - Internal or Business Partner Contact */
  public void setAD_User_ID(int AD_User_ID);

  /** Get User/Contact. User within the system - Internal or Business Partner Contact */
  public int getAD_User_ID();

  public I_AD_User getAD_User() throws RuntimeException;

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

  /** Column name PA_DashboardContent_Access_ID */
  public static final String COLUMNNAME_PA_DashboardContent_Access_ID =
      "PA_DashboardContent_Access_ID";

  /** Set PA_DashboardContent_Access_ID */
  public void setPA_DashboardContent_Access_ID(int PA_DashboardContent_Access_ID);

  /** Get PA_DashboardContent_Access_ID */
  public int getPA_DashboardContent_Access_ID();

  /** Column name PA_DashboardContent_Access_UU */
  public static final String COLUMNNAME_PA_DashboardContent_Access_UU =
      "PA_DashboardContent_Access_UU";

  /** Set PA_DashboardContent_Access_UU */
  public void setPA_DashboardContent_Access_UU(String PA_DashboardContent_Access_UU);

  /** Get PA_DashboardContent_Access_UU */
  public String getPA_DashboardContent_Access_UU();

  /** Column name PA_DashboardContent_ID */
  public static final String COLUMNNAME_PA_DashboardContent_ID = "PA_DashboardContent_ID";

  /** Set Dashboard Content */
  public void setPA_DashboardContent_ID(int PA_DashboardContent_ID);

  /** Get Dashboard Content */
  public int getPA_DashboardContent_ID();

  public I_PA_DashboardContent getPA_DashboardContent() throws RuntimeException;

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
