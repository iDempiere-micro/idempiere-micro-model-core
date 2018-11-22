package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for A_Asset_Retirement
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_A_Asset_Retirement {

  /** TableName=A_Asset_Retirement */
  public static final String Table_Name = "A_Asset_Retirement";

  /** AD_Table_ID=540 */
  public static final int Table_ID = 540;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 3 - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(3);

  /** Load Meta Data */

  /** Column name A_Asset_ID */
  public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

  /** Set Asset. Asset used internally or by customers */
  public void setA_Asset_ID(int A_Asset_ID);

  /** Get Asset. Asset used internally or by customers */
  public int getA_Asset_ID();

  public I_A_Asset getA_Asset() throws RuntimeException;

  /** Column name A_Asset_Retirement_ID */
  public static final String COLUMNNAME_A_Asset_Retirement_ID = "A_Asset_Retirement_ID";

  /** Set Asset Retirement. Internally used asset is not longer used. */
  public void setA_Asset_Retirement_ID(int A_Asset_Retirement_ID);

  /** Get Asset Retirement. Internally used asset is not longer used. */
  public int getA_Asset_Retirement_ID();

  /** Column name A_Asset_Retirement_UU */
  public static final String COLUMNNAME_A_Asset_Retirement_UU = "A_Asset_Retirement_UU";

  /** Set A_Asset_Retirement_UU */
  public void setA_Asset_Retirement_UU(String A_Asset_Retirement_UU);

  /** Get A_Asset_Retirement_UU */
  public String getA_Asset_Retirement_UU();

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AssetMarketValueAmt */
  public static final String COLUMNNAME_AssetMarketValueAmt = "AssetMarketValueAmt";

  /** Set Market value Amount. Market value of the asset */
  public void setAssetMarketValueAmt(BigDecimal AssetMarketValueAmt);

  /** Get Market value Amount. Market value of the asset */
  public BigDecimal getAssetMarketValueAmt();

  /** Column name AssetValueAmt */
  public static final String COLUMNNAME_AssetValueAmt = "AssetValueAmt";

  /** Set Asset value. Book Value of the asset */
  public void setAssetValueAmt(BigDecimal AssetValueAmt);

  /** Get Asset value. Book Value of the asset */
  public BigDecimal getAssetValueAmt();

  /** Column name C_InvoiceLine_ID */
  public static final String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";

  /** Set Invoice Line. Invoice Detail Line */
  public void setC_InvoiceLine_ID(int C_InvoiceLine_ID);

  /** Get Invoice Line. Invoice Detail Line */
  public int getC_InvoiceLine_ID();

  public I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException;

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
