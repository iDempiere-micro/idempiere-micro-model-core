package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for A_FundingMode_Acct
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_A_FundingMode_Acct {

  /** TableName=A_FundingMode_Acct */
  public static final String Table_Name = "A_FundingMode_Acct";

  /** AD_Table_ID=53274 */
  public static final int Table_ID = 53274;

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

  /** Column name A_FundingMode_Acct */
  public static final String COLUMNNAME_A_FundingMode_Acct = "A_FundingMode_Acct";

  /** Set Funding Mode Account */
  public void setA_FundingMode_Acct(int A_FundingMode_Acct);

  /** Get Funding Mode Account */
  public int getA_FundingMode_Acct();

  public I_C_ValidCombination getA_FundingMode_A() throws RuntimeException;

  /** Column name A_FundingMode_Acct_UU */
  public static final String COLUMNNAME_A_FundingMode_Acct_UU = "A_FundingMode_Acct_UU";

  /** Set A_FundingMode_Acct_UU */
  public void setA_FundingMode_Acct_UU(String A_FundingMode_Acct_UU);

  /** Get A_FundingMode_Acct_UU */
  public String getA_FundingMode_Acct_UU();

  /** Column name A_FundingMode_ID */
  public static final String COLUMNNAME_A_FundingMode_ID = "A_FundingMode_ID";

  /** Set Asset Funding Mode */
  public void setA_FundingMode_ID(int A_FundingMode_ID);

  /** Get Asset Funding Mode */
  public int getA_FundingMode_ID();

  public I_A_FundingMode getA_FundingMode() throws RuntimeException;

  /** Column name C_AcctSchema_ID */
  public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

  /** Set Accounting Schema. Rules for accounting */
  public void setC_AcctSchema_ID(int C_AcctSchema_ID);

  /** Get Accounting Schema. Rules for accounting */
  public int getC_AcctSchema_ID();

  public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

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
