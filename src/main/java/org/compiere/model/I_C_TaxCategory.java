package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.icommon.model.IPO;

/**
 * Generated Interface for C_TaxCategory
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_TaxCategory extends IPO {

  /** TableName=C_TaxCategory */
  public static final String Table_Name = "C_TaxCategory";

  /** AD_Table_ID=252 */
  public static final int Table_ID = 252;

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

  /** Column name CommodityCode */
  public static final String COLUMNNAME_CommodityCode = "CommodityCode";

  /** Set Commodity Code. Commodity code used for tax calculation */
  public void setCommodityCode(String CommodityCode);

  /** Get Commodity Code. Commodity code used for tax calculation */
  public String getCommodityCode();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name C_TaxCategory_ID */
  public static final String COLUMNNAME_C_TaxCategory_ID = "C_TaxCategory_ID";

  /** Set Tax Category. Tax Category */
  public void setC_TaxCategory_ID(int C_TaxCategory_ID);

  /** Get Tax Category. Tax Category */
  public int getC_TaxCategory_ID();

  /** Column name C_TaxCategory_UU */
  public static final String COLUMNNAME_C_TaxCategory_UU = "C_TaxCategory_UU";

  /** Set C_TaxCategory_UU */
  public void setC_TaxCategory_UU(String C_TaxCategory_UU);

  /** Get C_TaxCategory_UU */
  public String getC_TaxCategory_UU();

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

  /** Column name IsDefault */
  public static final String COLUMNNAME_IsDefault = "IsDefault";

  /** Set Default. Default value */
  public void setIsDefault(boolean IsDefault);

  /** Get Default. Default value */
  public boolean isDefault();

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
