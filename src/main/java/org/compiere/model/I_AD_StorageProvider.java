package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_StorageProvider
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_StorageProvider {

  /** TableName=AD_StorageProvider */
  public static final String Table_Name = "AD_StorageProvider";

  /** AD_Table_ID=200037 */
  public static final int Table_ID = 200037;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_StorageProvider_ID */
  public static final String COLUMNNAME_AD_StorageProvider_ID = "AD_StorageProvider_ID";

  /** Set Storage Provider */
  public void setAD_StorageProvider_ID(int AD_StorageProvider_ID);

  /** Get Storage Provider */
  public int getAD_StorageProvider_ID();

  /** Column name AD_StorageProvider_UU */
  public static final String COLUMNNAME_AD_StorageProvider_UU = "AD_StorageProvider_UU";

  /** Set AD_StorageProvider_UU */
  public void setAD_StorageProvider_UU(String AD_StorageProvider_UU);

  /** Get AD_StorageProvider_UU */
  public String getAD_StorageProvider_UU();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name Folder */
  public static final String COLUMNNAME_Folder = "Folder";

  /** Set Folder. A folder on a local or remote system to store data into */
  public void setFolder(String Folder);

  /** Get Folder. A folder on a local or remote system to store data into */
  public String getFolder();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name Method */
  public static final String COLUMNNAME_Method = "Method";

  /** Set Method */
  public void setMethod(String Method);

  /** Get Method */
  public String getMethod();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name Password */
  public static final String COLUMNNAME_Password = "Password";

  /** Set Password. Password of any length (case sensitive) */
  public void setPassword(String Password);

  /** Get Password. Password of any length (case sensitive) */
  public String getPassword();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name URL */
  public static final String COLUMNNAME_URL = "URL";

  /** Set URL. Full URL address - e.g. http://www.idempiere.org */
  public void setURL(String URL);

  /** Get URL. Full URL address - e.g. http://www.idempiere.org */
  public String getURL();

  /** Column name UserName */
  public static final String COLUMNNAME_UserName = "UserName";

  /** Set User Name */
  public void setUserName(String UserName);

  /** Get User Name */
  public String getUserName();
}
