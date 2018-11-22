package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for R_RequestUpdates
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_R_RequestUpdates {

  /** TableName=R_RequestUpdates */
  public static final String Table_Name = "R_RequestUpdates";

  /** AD_Table_ID=783 */
  public static final int Table_ID = 783;

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

  /** Column name IsSelfService */
  public static final String COLUMNNAME_IsSelfService = "IsSelfService";

  /**
   * Set Self-Service. This is a Self-Service entry or this entry can be changed via Self-Service
   */
  public void setIsSelfService(boolean IsSelfService);

  /**
   * Get Self-Service. This is a Self-Service entry or this entry can be changed via Self-Service
   */
  public boolean isSelfService();

  /** Column name R_Request_ID */
  public static final String COLUMNNAME_R_Request_ID = "R_Request_ID";

  /** Set Request. Request from a Business Partner or Prospect */
  public void setR_Request_ID(int R_Request_ID);

  /** Get Request. Request from a Business Partner or Prospect */
  public int getR_Request_ID();

  public I_R_Request getR_Request() throws RuntimeException;

  /** Column name R_RequestUpdates_UU */
  public static final String COLUMNNAME_R_RequestUpdates_UU = "R_RequestUpdates_UU";

  /** Set R_RequestUpdates_UU */
  public void setR_RequestUpdates_UU(String R_RequestUpdates_UU);

  /** Get R_RequestUpdates_UU */
  public String getR_RequestUpdates_UU();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
