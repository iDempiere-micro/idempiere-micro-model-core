package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_UserBPAccess
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_UserBPAccess {

  /** TableName=AD_UserBPAccess */
  public static final String Table_Name = "AD_UserBPAccess";

  /** AD_Table_ID=813 */
  public static final int Table_ID = 813;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 2 - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(2);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_UserBPAccess_ID */
  public static final String COLUMNNAME_AD_UserBPAccess_ID = "AD_UserBPAccess_ID";

  /** Set User BP Access. User/contact access to Business Partner information and resources */
  public void setAD_UserBPAccess_ID(int AD_UserBPAccess_ID);

  /** Get User BP Access. User/contact access to Business Partner information and resources */
  public int getAD_UserBPAccess_ID();

  /** Column name AD_UserBPAccess_UU */
  public static final String COLUMNNAME_AD_UserBPAccess_UU = "AD_UserBPAccess_UU";

  /** Set AD_UserBPAccess_UU */
  public void setAD_UserBPAccess_UU(String AD_UserBPAccess_UU);

  /** Get AD_UserBPAccess_UU */
  public String getAD_UserBPAccess_UU();

  /** Column name AD_User_ID */
  public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

  /** Set User/Contact. User within the system - Internal or Business Partner Contact */
  public void setAD_User_ID(int AD_User_ID);

  /** Get User/Contact. User within the system - Internal or Business Partner Contact */
  public int getAD_User_ID();

  public I_AD_User getAD_User() throws RuntimeException;

  /** Column name BPAccessType */
  public static final String COLUMNNAME_BPAccessType = "BPAccessType";

  /**
   * Set Access Type. Type of Access of the user/contact to Business Partner information and
   * resources
   */
  public void setBPAccessType(String BPAccessType);

  /**
   * Get Access Type. Type of Access of the user/contact to Business Partner information and
   * resources
   */
  public String getBPAccessType();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name DocBaseType */
  public static final String COLUMNNAME_DocBaseType = "DocBaseType";

  /** Set Document BaseType. Logical type of document */
  public void setDocBaseType(String DocBaseType);

  /** Get Document BaseType. Logical type of document */
  public String getDocBaseType();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name R_RequestType_ID */
  public static final String COLUMNNAME_R_RequestType_ID = "R_RequestType_ID";

  /** Set Request Type. Type of request (e.g. Inquiry, Complaint, ..) */
  public void setR_RequestType_ID(int R_RequestType_ID);

  /** Get Request Type. Type of request (e.g. Inquiry, Complaint, ..) */
  public int getR_RequestType_ID();

  public I_R_RequestType getR_RequestType() throws RuntimeException;

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
