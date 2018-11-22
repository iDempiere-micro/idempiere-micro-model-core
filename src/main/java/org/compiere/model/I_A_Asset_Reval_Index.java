package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for A_Asset_Reval_Index
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_A_Asset_Reval_Index {

  /** TableName=A_Asset_Reval_Index */
  public static final String Table_Name = "A_Asset_Reval_Index";

  /** AD_Table_ID=53120 */
  public static final int Table_ID = 53120;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 7 - System - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(7);

  /** Load Meta Data */

  /** Column name A_Asset_Reval_Index_ID */
  public static final String COLUMNNAME_A_Asset_Reval_Index_ID = "A_Asset_Reval_Index_ID";

  /** Set Asset Reval Index */
  public void setA_Asset_Reval_Index_ID(int A_Asset_Reval_Index_ID);

  /** Get Asset Reval Index */
  public int getA_Asset_Reval_Index_ID();

  /** Column name A_Asset_Reval_Index_UU */
  public static final String COLUMNNAME_A_Asset_Reval_Index_UU = "A_Asset_Reval_Index_UU";

  /** Set A_Asset_Reval_Index_UU */
  public void setA_Asset_Reval_Index_UU(String A_Asset_Reval_Index_UU);

  /** Get A_Asset_Reval_Index_UU */
  public String getA_Asset_Reval_Index_UU();

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name A_Effective_Date */
  public static final String COLUMNNAME_A_Effective_Date = "A_Effective_Date";

  /** Set Effective Date */
  public void setA_Effective_Date(Timestamp A_Effective_Date);

  /** Get Effective Date */
  public Timestamp getA_Effective_Date();

  /** Column name A_Reval_Code */
  public static final String COLUMNNAME_A_Reval_Code = "A_Reval_Code";

  /** Set Reval. Code */
  public void setA_Reval_Code(String A_Reval_Code);

  /** Get Reval. Code */
  public String getA_Reval_Code();

  /** Column name A_Reval_Multiplier */
  public static final String COLUMNNAME_A_Reval_Multiplier = "A_Reval_Multiplier";

  /** Set Reval. Multiplier */
  public void setA_Reval_Multiplier(String A_Reval_Multiplier);

  /** Get Reval. Multiplier */
  public String getA_Reval_Multiplier();

  /** Column name A_Reval_Rate */
  public static final String COLUMNNAME_A_Reval_Rate = "A_Reval_Rate";

  /** Set Reval. Rate */
  public void setA_Reval_Rate(BigDecimal A_Reval_Rate);

  /** Get Reval. Rate */
  public BigDecimal getA_Reval_Rate();

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
