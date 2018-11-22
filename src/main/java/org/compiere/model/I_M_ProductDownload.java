package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_ProductDownload
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_ProductDownload {

  /** TableName=M_ProductDownload */
  public static final String Table_Name = "M_ProductDownload";

  /** AD_Table_ID=777 */
  public static final int Table_ID = 777;

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

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name DownloadURL */
  public static final String COLUMNNAME_DownloadURL = "DownloadURL";

  /** Set Download URL. URL of the Download files */
  public void setDownloadURL(String DownloadURL);

  /** Get Download URL. URL of the Download files */
  public String getDownloadURL();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name M_ProductDownload_ID */
  public static final String COLUMNNAME_M_ProductDownload_ID = "M_ProductDownload_ID";

  /** Set Product Download. Product downloads */
  public void setM_ProductDownload_ID(int M_ProductDownload_ID);

  /** Get Product Download. Product downloads */
  public int getM_ProductDownload_ID();

  /** Column name M_ProductDownload_UU */
  public static final String COLUMNNAME_M_ProductDownload_UU = "M_ProductDownload_UU";

  /** Set M_ProductDownload_UU */
  public void setM_ProductDownload_UU(String M_ProductDownload_UU);

  /** Get M_ProductDownload_UU */
  public String getM_ProductDownload_UU();

  /** Column name M_Product_ID */
  public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

  /** Set Product. Product, Service, Item */
  public void setM_Product_ID(int M_Product_ID);

  /** Get Product. Product, Service, Item */
  public int getM_Product_ID();

  public I_M_Product getM_Product() throws RuntimeException;

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
