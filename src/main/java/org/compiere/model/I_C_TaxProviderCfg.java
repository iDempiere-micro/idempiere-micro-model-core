package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_TaxProviderCfg
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_TaxProviderCfg {

  /** TableName=C_TaxProviderCfg */
  public static final String Table_Name = "C_TaxProviderCfg";

  /** AD_Table_ID=200098 */
  public static final int Table_ID = 200098;

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

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name C_TaxProviderCfg_ID */
  public static final String COLUMNNAME_C_TaxProviderCfg_ID = "C_TaxProviderCfg_ID";

  /** Set Tax Provider Configuration */
  public void setC_TaxProviderCfg_ID(int C_TaxProviderCfg_ID);

  /** Get Tax Provider Configuration */
  public int getC_TaxProviderCfg_ID();

  /** Column name C_TaxProviderCfg_UU */
  public static final String COLUMNNAME_C_TaxProviderCfg_UU = "C_TaxProviderCfg_UU";

  /** Set C_TaxProviderCfg_UU */
  public void setC_TaxProviderCfg_UU(String C_TaxProviderCfg_UU);

  /** Get C_TaxProviderCfg_UU */
  public String getC_TaxProviderCfg_UU();

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

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name TaxProviderClass */
  public static final String COLUMNNAME_TaxProviderClass = "TaxProviderClass";

  /** Set Tax Provider Class */
  public void setTaxProviderClass(String TaxProviderClass);

  /** Get Tax Provider Class */
  public String getTaxProviderClass();

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
}
