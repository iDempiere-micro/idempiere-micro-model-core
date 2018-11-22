package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for R_RequestProcessor_Route
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_R_RequestProcessor_Route {

  /** TableName=R_RequestProcessor_Route */
  public static final String Table_Name = "R_RequestProcessor_Route";

  /** AD_Table_ID=474 */
  public static final int Table_ID = 474;

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

  /** Column name Keyword */
  public static final String COLUMNNAME_Keyword = "Keyword";

  /** Set Keyword. Case insensitive keyword */
  public void setKeyword(String Keyword);

  /** Get Keyword. Case insensitive keyword */
  public String getKeyword();

  /** Column name R_RequestProcessor_ID */
  public static final String COLUMNNAME_R_RequestProcessor_ID = "R_RequestProcessor_ID";

  /** Set Request Processor. Processor for Requests */
  public void setR_RequestProcessor_ID(int R_RequestProcessor_ID);

  /** Get Request Processor. Processor for Requests */
  public int getR_RequestProcessor_ID();

  public I_R_RequestProcessor getR_RequestProcessor() throws RuntimeException;

  /** Column name R_RequestProcessor_Route_ID */
  public static final String COLUMNNAME_R_RequestProcessor_Route_ID = "R_RequestProcessor_Route_ID";

  /** Set Request Routing. Automatic routing of requests */
  public void setR_RequestProcessor_Route_ID(int R_RequestProcessor_Route_ID);

  /** Get Request Routing. Automatic routing of requests */
  public int getR_RequestProcessor_Route_ID();

  /** Column name R_RequestProcessor_Route_UU */
  public static final String COLUMNNAME_R_RequestProcessor_Route_UU = "R_RequestProcessor_Route_UU";

  /** Set R_RequestProcessor_Route_UU */
  public void setR_RequestProcessor_Route_UU(String R_RequestProcessor_Route_UU);

  /** Get R_RequestProcessor_Route_UU */
  public String getR_RequestProcessor_Route_UU();

  /** Column name R_RequestType_ID */
  public static final String COLUMNNAME_R_RequestType_ID = "R_RequestType_ID";

  /** Set Request Type. Type of request (e.g. Inquiry, Complaint, ..) */
  public void setR_RequestType_ID(int R_RequestType_ID);

  /** Get Request Type. Type of request (e.g. Inquiry, Complaint, ..) */
  public int getR_RequestType_ID();

  public I_R_RequestType getR_RequestType() throws RuntimeException;

  /** Column name SeqNo */
  public static final String COLUMNNAME_SeqNo = "SeqNo";

  /** Set Sequence. Method of ordering records; lowest number comes first */
  public void setSeqNo(int SeqNo);

  /** Get Sequence. Method of ordering records; lowest number comes first */
  public int getSeqNo();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
