package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_AlertRecipient
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_AlertRecipient {

  /** TableName=AD_AlertRecipient */
  public static final String Table_Name = "AD_AlertRecipient";

  /** AD_Table_ID=592 */
  public static final int Table_ID = 592;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Column name AD_Alert_ID */
  public static final String COLUMNNAME_AD_Alert_ID = "AD_Alert_ID";

  /** Set Alert. iDempiere Alert */
  public void setAD_Alert_ID(int AD_Alert_ID);

  /** Get Alert. iDempiere Alert */
  public int getAD_Alert_ID();

  public I_AD_Alert getAD_Alert() throws RuntimeException;

  /** Column name AD_AlertRecipient_ID */
  public static final String COLUMNNAME_AD_AlertRecipient_ID = "AD_AlertRecipient_ID";

  /** Set Alert Recipient. Recipient of the Alert Notification */
  public void setAD_AlertRecipient_ID(int AD_AlertRecipient_ID);

  /** Get Alert Recipient. Recipient of the Alert Notification */
  public int getAD_AlertRecipient_ID();

  /** Column name AD_AlertRecipient_UU */
  public static final String COLUMNNAME_AD_AlertRecipient_UU = "AD_AlertRecipient_UU";

  /** Set AD_AlertRecipient_UU */
  public void setAD_AlertRecipient_UU(String AD_AlertRecipient_UU);

  /** Get AD_AlertRecipient_UU */
  public String getAD_AlertRecipient_UU();

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

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
