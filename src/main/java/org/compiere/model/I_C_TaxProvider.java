package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_TaxProvider
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_TaxProvider {

  /** TableName=C_TaxProvider */
  public static final String Table_Name = "C_TaxProvider";

  /** AD_Table_ID=200099 */
  public static final int Table_ID = 200099;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 3 - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(3);

  /** Load Meta Data */

  /** Column name Account */
  public static final String COLUMNNAME_Account = "Account";

  /** Set Account */
  public void setAccount(String Account);

  /** Get Account */
  public String getAccount();

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name CompanyCode */
  public static final String COLUMNNAME_CompanyCode = "CompanyCode";

  /** Set Company Code */
  public void setCompanyCode(String CompanyCode);

  /** Get Company Code */
  public String getCompanyCode();

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

  public I_C_TaxProviderCfg getC_TaxProviderCfg() throws RuntimeException;

  /** Column name C_TaxProvider_ID */
  public static final String COLUMNNAME_C_TaxProvider_ID = "C_TaxProvider_ID";

  /** Set Tax Provider */
  public void setC_TaxProvider_ID(int C_TaxProvider_ID);

  /** Get Tax Provider */
  public int getC_TaxProvider_ID();

  /** Column name C_TaxProvider_UU */
  public static final String COLUMNNAME_C_TaxProvider_UU = "C_TaxProvider_UU";

  /** Set C_TaxProvider_UU */
  public void setC_TaxProvider_UU(String C_TaxProvider_UU);

  /** Get C_TaxProvider_UU */
  public String getC_TaxProvider_UU();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name License */
  public static final String COLUMNNAME_License = "License";

  /** Set License */
  public void setLicense(String License);

  /** Get License */
  public String getLicense();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name SeqNo */
  public static final String COLUMNNAME_SeqNo = "SeqNo";

  /** Set Sequence. Method of ordering records; lowest number comes first */
  public void setSeqNo(int SeqNo);

  /** Get Sequence. Method of ordering records; lowest number comes first */
  public int getSeqNo();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name ValidateConnection */
  public static final String COLUMNNAME_ValidateConnection = "ValidateConnection";

  /** Set Validate Connection */
  public void setValidateConnection(String ValidateConnection);

  /** Get Validate Connection */
  public String getValidateConnection();

  Properties getCtx();
}
