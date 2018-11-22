package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for R_RequestUpdate
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_R_RequestUpdate {

  /** TableName=R_RequestUpdate */
  public static final String Table_Name = "R_RequestUpdate";

  /** AD_Table_ID=802 */
  public static final int Table_ID = 802;

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

  /** Column name ConfidentialTypeEntry */
  public static final String COLUMNNAME_ConfidentialTypeEntry = "ConfidentialTypeEntry";

  /** Set Entry Confidentiality. Confidentiality of the individual entry */
  public void setConfidentialTypeEntry(String ConfidentialTypeEntry);

  /** Get Entry Confidentiality. Confidentiality of the individual entry */
  public String getConfidentialTypeEntry();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name EndTime */
  public static final String COLUMNNAME_EndTime = "EndTime";

  /** Set End Time. End of the time span */
  public void setEndTime(Timestamp EndTime);

  /** Get End Time. End of the time span */
  public Timestamp getEndTime();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name M_ProductSpent_ID */
  public static final String COLUMNNAME_M_ProductSpent_ID = "M_ProductSpent_ID";

  /** Set Product Used. Product/Resource/Service used in Request */
  public void setM_ProductSpent_ID(int M_ProductSpent_ID);

  /** Get Product Used. Product/Resource/Service used in Request */
  public int getM_ProductSpent_ID();

  public I_M_Product getM_ProductSpent() throws RuntimeException;

  /** Column name QtyInvoiced */
  public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";

  /** Set Quantity Invoiced. Invoiced Quantity */
  public void setQtyInvoiced(BigDecimal QtyInvoiced);

  /** Get Quantity Invoiced. Invoiced Quantity */
  public BigDecimal getQtyInvoiced();

  /** Column name QtySpent */
  public static final String COLUMNNAME_QtySpent = "QtySpent";

  /** Set Quantity Used. Quantity used for this event */
  public void setQtySpent(BigDecimal QtySpent);

  /** Get Quantity Used. Quantity used for this event */
  public BigDecimal getQtySpent();

  /** Column name Result */
  public static final String COLUMNNAME_Result = "Result";

  /** Set Result. Result of the action taken */
  public void setResult(String Result);

  /** Get Result. Result of the action taken */
  public String getResult();

  /** Column name R_Request_ID */
  public static final String COLUMNNAME_R_Request_ID = "R_Request_ID";

  /** Set Request. Request from a Business Partner or Prospect */
  public void setR_Request_ID(int R_Request_ID);

  /** Get Request. Request from a Business Partner or Prospect */
  public int getR_Request_ID();

  public I_R_Request getR_Request() throws RuntimeException;

  /** Column name R_RequestUpdate_ID */
  public static final String COLUMNNAME_R_RequestUpdate_ID = "R_RequestUpdate_ID";

  /** Set Request Update. Request Updates */
  public void setR_RequestUpdate_ID(int R_RequestUpdate_ID);

  /** Get Request Update. Request Updates */
  public int getR_RequestUpdate_ID();

  /** Column name R_RequestUpdate_UU */
  public static final String COLUMNNAME_R_RequestUpdate_UU = "R_RequestUpdate_UU";

  /** Set R_RequestUpdate_UU */
  public void setR_RequestUpdate_UU(String R_RequestUpdate_UU);

  /** Get R_RequestUpdate_UU */
  public String getR_RequestUpdate_UU();

  /** Column name StartTime */
  public static final String COLUMNNAME_StartTime = "StartTime";

  /** Set Start Time. Time started */
  public void setStartTime(Timestamp StartTime);

  /** Get Start Time. Time started */
  public Timestamp getStartTime();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
