package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_AttachmentNote
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_AttachmentNote {

  /** TableName=AD_AttachmentNote */
  public static final String Table_Name = "AD_AttachmentNote";

  /** AD_Table_ID=705 */
  public static final int Table_ID = 705;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Column name AD_Attachment_ID */
  public static final String COLUMNNAME_AD_Attachment_ID = "AD_Attachment_ID";

  /** Set Attachment. Attachment for the document */
  public void setAD_Attachment_ID(int AD_Attachment_ID);

  /** Get Attachment. Attachment for the document */
  public int getAD_Attachment_ID();

  public I_AD_Attachment getAD_Attachment() throws RuntimeException;

  /** Column name AD_AttachmentNote_ID */
  public static final String COLUMNNAME_AD_AttachmentNote_ID = "AD_AttachmentNote_ID";

  /** Set Attachment Note. Personal Attachment Note */
  public void setAD_AttachmentNote_ID(int AD_AttachmentNote_ID);

  /** Get Attachment Note. Personal Attachment Note */
  public int getAD_AttachmentNote_ID();

  /** Column name AD_AttachmentNote_UU */
  public static final String COLUMNNAME_AD_AttachmentNote_UU = "AD_AttachmentNote_UU";

  /** Set AD_AttachmentNote_UU */
  public void setAD_AttachmentNote_UU(String AD_AttachmentNote_UU);

  /** Get AD_AttachmentNote_UU */
  public String getAD_AttachmentNote_UU();

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

  /** Column name TextMsg */
  public static final String COLUMNNAME_TextMsg = "TextMsg";

  /** Set Text Message. Text Message */
  public void setTextMsg(String TextMsg);

  /** Get Text Message. Text Message */
  public String getTextMsg();

  /** Column name Title */
  public static final String COLUMNNAME_Title = "Title";

  /** Set Title. Name this entity is referred to as */
  public void setTitle(String Title);

  /** Get Title. Name this entity is referred to as */
  public String getTitle();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
