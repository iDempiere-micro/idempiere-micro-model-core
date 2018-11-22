package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for B_BidComment
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_B_BidComment {

  /** TableName=B_BidComment */
  public static final String Table_Name = "B_BidComment";

  /** AD_Table_ID=685 */
  public static final int Table_ID = 685;

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

  /** Column name AD_User_ID */
  public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

  /** Set User/Contact. User within the system - Internal or Business Partner Contact */
  public void setAD_User_ID(int AD_User_ID);

  /** Get User/Contact. User within the system - Internal or Business Partner Contact */
  public int getAD_User_ID();

  public I_AD_User getAD_User() throws RuntimeException;

  /** Column name B_BidComment_ID */
  public static final String COLUMNNAME_B_BidComment_ID = "B_BidComment_ID";

  /** Set Bid Comment. Make a comment to a Bid Topic */
  public void setB_BidComment_ID(int B_BidComment_ID);

  /** Get Bid Comment. Make a comment to a Bid Topic */
  public int getB_BidComment_ID();

  /** Column name B_BidComment_UU */
  public static final String COLUMNNAME_B_BidComment_UU = "B_BidComment_UU";

  /** Set B_BidComment_UU */
  public void setB_BidComment_UU(String B_BidComment_UU);

  /** Get B_BidComment_UU */
  public String getB_BidComment_UU();

  /** Column name B_Topic_ID */
  public static final String COLUMNNAME_B_Topic_ID = "B_Topic_ID";

  /** Set Topic. Auction Topic */
  public void setB_Topic_ID(int B_Topic_ID);

  /** Get Topic. Auction Topic */
  public int getB_Topic_ID();

  public I_B_Topic getB_Topic() throws RuntimeException;

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
