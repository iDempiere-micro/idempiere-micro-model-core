package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_SubscriptionType
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_SubscriptionType {

  /** TableName=C_SubscriptionType */
  public static final String Table_Name = "C_SubscriptionType";

  /** AD_Table_ID=668 */
  public static final int Table_ID = 668;

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

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name C_SubscriptionType_ID */
  public static final String COLUMNNAME_C_SubscriptionType_ID = "C_SubscriptionType_ID";

  /** Set Subscription Type. Type of subscription */
  public void setC_SubscriptionType_ID(int C_SubscriptionType_ID);

  /** Get Subscription Type. Type of subscription */
  public int getC_SubscriptionType_ID();

  /** Column name C_SubscriptionType_UU */
  public static final String COLUMNNAME_C_SubscriptionType_UU = "C_SubscriptionType_UU";

  /** Set C_SubscriptionType_UU */
  public void setC_SubscriptionType_UU(String C_SubscriptionType_UU);

  /** Get C_SubscriptionType_UU */
  public String getC_SubscriptionType_UU();

  /** Column name Description */
  public static final String COLUMNNAME_Description = "Description";

  /** Set Description. Optional short description of the record */
  public void setDescription(String Description);

  /** Get Description. Optional short description of the record */
  public String getDescription();

  /** Column name Frequency */
  public static final String COLUMNNAME_Frequency = "Frequency";

  /** Set Frequency. Frequency of events */
  public void setFrequency(int Frequency);

  /** Get Frequency. Frequency of events */
  public int getFrequency();

  /** Column name FrequencyType */
  public static final String COLUMNNAME_FrequencyType = "FrequencyType";

  /** Set Frequency Type. Frequency of event */
  public void setFrequencyType(String FrequencyType);

  /** Get Frequency Type. Frequency of event */
  public String getFrequencyType();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
