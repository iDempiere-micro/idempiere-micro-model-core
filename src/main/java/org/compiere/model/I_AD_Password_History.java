package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Password_History
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Password_History {

  /** TableName=AD_Password_History */
  public static final String Table_Name = "AD_Password_History";

  /** AD_Table_ID=200171 */
  public static final int Table_ID = 200171;

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

  /** Column name AD_Password_History_ID */
  public static final String COLUMNNAME_AD_Password_History_ID = "AD_Password_History_ID";

  /** Set Password History ID */
  public void setAD_Password_History_ID(int AD_Password_History_ID);

  /** Get Password History ID */
  public int getAD_Password_History_ID();

  /** Column name AD_Password_History_UU */
  public static final String COLUMNNAME_AD_Password_History_UU = "AD_Password_History_UU";

  /** Set AD_Password_History_UU */
  public void setAD_Password_History_UU(String AD_Password_History_UU);

  /** Get AD_Password_History_UU */
  public String getAD_Password_History_UU();

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

  /** Column name DatePasswordChanged */
  public static final String COLUMNNAME_DatePasswordChanged = "DatePasswordChanged";

  /** Set Date Password Changed */
  public void setDatePasswordChanged(Timestamp DatePasswordChanged);

  /** Get Date Password Changed */
  public Timestamp getDatePasswordChanged();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name Password */
  public static final String COLUMNNAME_Password = "Password";

  /** Set Password. Password of any length (case sensitive) */
  public void setPassword(String Password);

  /** Get Password. Password of any length (case sensitive) */
  public String getPassword();

  /** Column name Salt */
  public static final String COLUMNNAME_Salt = "Salt";

  /** Set Salt. Random data added to improve password hash effectiveness */
  public void setSalt(String Salt);

  /** Get Salt. Random data added to improve password hash effectiveness */
  public String getSalt();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
