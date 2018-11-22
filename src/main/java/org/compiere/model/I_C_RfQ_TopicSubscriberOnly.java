package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_RfQ_TopicSubscriberOnly
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_RfQ_TopicSubscriberOnly {

  /** TableName=C_RfQ_TopicSubscriberOnly */
  public static final String Table_Name = "C_RfQ_TopicSubscriberOnly";

  /** AD_Table_ID=747 */
  public static final int Table_ID = 747;

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

  /** Column name C_RfQ_TopicSubscriber_ID */
  public static final String COLUMNNAME_C_RfQ_TopicSubscriber_ID = "C_RfQ_TopicSubscriber_ID";

  /** Set RfQ Subscriber. Request for Quotation Topic Subscriber */
  public void setC_RfQ_TopicSubscriber_ID(int C_RfQ_TopicSubscriber_ID);

  /** Get RfQ Subscriber. Request for Quotation Topic Subscriber */
  public int getC_RfQ_TopicSubscriber_ID();

  public I_C_RfQ_TopicSubscriber getC_RfQ_TopicSubscriber() throws RuntimeException;

  /** Column name C_RfQ_TopicSubscriberOnly_ID */
  public static final String COLUMNNAME_C_RfQ_TopicSubscriberOnly_ID =
      "C_RfQ_TopicSubscriberOnly_ID";

  /**
   * Set RfQ Topic Subscriber Restriction. Include Subscriber only for certain products or product
   * categories
   */
  public void setC_RfQ_TopicSubscriberOnly_ID(int C_RfQ_TopicSubscriberOnly_ID);

  /**
   * Get RfQ Topic Subscriber Restriction. Include Subscriber only for certain products or product
   * categories
   */
  public int getC_RfQ_TopicSubscriberOnly_ID();

  /** Column name C_RfQ_TopicSubscriberOnly_UU */
  public static final String COLUMNNAME_C_RfQ_TopicSubscriberOnly_UU =
      "C_RfQ_TopicSubscriberOnly_UU";

  /** Set C_RfQ_TopicSubscriberOnly_UU */
  public void setC_RfQ_TopicSubscriberOnly_UU(String C_RfQ_TopicSubscriberOnly_UU);

  /** Get C_RfQ_TopicSubscriberOnly_UU */
  public String getC_RfQ_TopicSubscriberOnly_UU();

  /** Column name Description */
  public static final String COLUMNNAME_Description = "Description";

  /** Set Description. Optional short description of the record */
  public void setDescription(String Description);

  /** Get Description. Optional short description of the record */
  public String getDescription();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name M_Product_Category_ID */
  public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

  /** Set Product Category. Category of a Product */
  public void setM_Product_Category_ID(int M_Product_Category_ID);

  /** Get Product Category. Category of a Product */
  public int getM_Product_Category_ID();

  public I_M_Product_Category getM_Product_Category() throws RuntimeException;

  /** Column name M_Product_ID */
  public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

  /** Set Product. Product, Service, Item */
  public void setM_Product_ID(int M_Product_ID);

  /** Get Product. Product, Service, Item */
  public int getM_Product_ID();

  public I_M_Product getM_Product() throws RuntimeException;

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
