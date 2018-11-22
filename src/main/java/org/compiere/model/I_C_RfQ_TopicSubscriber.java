package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_RfQ_TopicSubscriber
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_RfQ_TopicSubscriber {

  /** TableName=C_RfQ_TopicSubscriber */
  public static final String Table_Name = "C_RfQ_TopicSubscriber";

  /** AD_Table_ID=670 */
  public static final int Table_ID = 670;

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

  /** Column name C_BPartner_ID */
  public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

  /** Set Business Partner . Identifies a Business Partner */
  public void setC_BPartner_ID(int C_BPartner_ID);

  /** Get Business Partner . Identifies a Business Partner */
  public int getC_BPartner_ID();

  public I_C_BPartner getC_BPartner() throws RuntimeException;

  /** Column name C_BPartner_Location_ID */
  public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

  /** Set Partner Location. Identifies the (ship to) address for this Business Partner */
  public void setC_BPartner_Location_ID(int C_BPartner_Location_ID);

  /** Get Partner Location. Identifies the (ship to) address for this Business Partner */
  public int getC_BPartner_Location_ID();

  public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name C_RfQ_Topic_ID */
  public static final String COLUMNNAME_C_RfQ_Topic_ID = "C_RfQ_Topic_ID";

  /** Set RfQ Topic. Topic for Request for Quotations */
  public void setC_RfQ_Topic_ID(int C_RfQ_Topic_ID);

  /** Get RfQ Topic. Topic for Request for Quotations */
  public int getC_RfQ_Topic_ID();

  public I_C_RfQ_Topic getC_RfQ_Topic() throws RuntimeException;

  /** Column name C_RfQ_TopicSubscriber_ID */
  public static final String COLUMNNAME_C_RfQ_TopicSubscriber_ID = "C_RfQ_TopicSubscriber_ID";

  /** Set RfQ Subscriber. Request for Quotation Topic Subscriber */
  public void setC_RfQ_TopicSubscriber_ID(int C_RfQ_TopicSubscriber_ID);

  /** Get RfQ Subscriber. Request for Quotation Topic Subscriber */
  public int getC_RfQ_TopicSubscriber_ID();

  /** Column name C_RfQ_TopicSubscriber_UU */
  public static final String COLUMNNAME_C_RfQ_TopicSubscriber_UU = "C_RfQ_TopicSubscriber_UU";

  /** Set C_RfQ_TopicSubscriber_UU */
  public void setC_RfQ_TopicSubscriber_UU(String C_RfQ_TopicSubscriber_UU);

  /** Get C_RfQ_TopicSubscriber_UU */
  public String getC_RfQ_TopicSubscriber_UU();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name OptOutDate */
  public static final String COLUMNNAME_OptOutDate = "OptOutDate";

  /** Set Opt-out Date. Date the contact opted out */
  public void setOptOutDate(Timestamp OptOutDate);

  /** Get Opt-out Date. Date the contact opted out */
  public Timestamp getOptOutDate();

  /** Column name SubscribeDate */
  public static final String COLUMNNAME_SubscribeDate = "SubscribeDate";

  /** Set Subscribe Date. Date the contact actively subscribed */
  public void setSubscribeDate(Timestamp SubscribeDate);

  /** Get Subscribe Date. Date the contact actively subscribed */
  public Timestamp getSubscribeDate();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
