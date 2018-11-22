package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_Project_Acct
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_Project_Acct {

  /** TableName=C_Project_Acct */
  public static final String Table_Name = "C_Project_Acct";

  /** AD_Table_ID=204 */
  public static final int Table_ID = 204;

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

  /** Column name C_AcctSchema_ID */
  public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

  /** Set Accounting Schema. Rules for accounting */
  public void setC_AcctSchema_ID(int C_AcctSchema_ID);

  /** Get Accounting Schema. Rules for accounting */
  public int getC_AcctSchema_ID();

  public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

  /** Column name C_Project_Acct_UU */
  public static final String COLUMNNAME_C_Project_Acct_UU = "C_Project_Acct_UU";

  /** Set C_Project_Acct_UU */
  public void setC_Project_Acct_UU(String C_Project_Acct_UU);

  /** Get C_Project_Acct_UU */
  public String getC_Project_Acct_UU();

  /** Column name C_Project_ID */
  public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

  /** Set Project. Financial Project */
  public void setC_Project_ID(int C_Project_ID);

  /** Get Project. Financial Project */
  public int getC_Project_ID();

  public I_C_Project getC_Project() throws RuntimeException;

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

  /** Column name PJ_Asset_Acct */
  public static final String COLUMNNAME_PJ_Asset_Acct = "PJ_Asset_Acct";

  /** Set Project Asset. Project Asset Account */
  public void setPJ_Asset_Acct(int PJ_Asset_Acct);

  /** Get Project Asset. Project Asset Account */
  public int getPJ_Asset_Acct();

  public I_C_ValidCombination getPJ_Asset_A() throws RuntimeException;

  /** Column name PJ_WIP_Acct */
  public static final String COLUMNNAME_PJ_WIP_Acct = "PJ_WIP_Acct";

  /** Set Work In Progress. Account for Work in Progress */
  public void setPJ_WIP_Acct(int PJ_WIP_Acct);

  /** Get Work In Progress. Account for Work in Progress */
  public int getPJ_WIP_Acct();

  public I_C_ValidCombination getPJ_WIP_A() throws RuntimeException;

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
