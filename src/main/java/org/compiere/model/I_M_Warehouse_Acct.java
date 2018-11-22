package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_Warehouse_Acct
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_Warehouse_Acct {

  /** TableName=M_Warehouse_Acct */
  public static final String Table_Name = "M_Warehouse_Acct";

  /** AD_Table_ID=191 */
  public static final int Table_ID = 191;

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

  /** Column name M_Warehouse_Acct_UU */
  public static final String COLUMNNAME_M_Warehouse_Acct_UU = "M_Warehouse_Acct_UU";

  /** Set M_Warehouse_Acct_UU */
  public void setM_Warehouse_Acct_UU(String M_Warehouse_Acct_UU);

  /** Get M_Warehouse_Acct_UU */
  public String getM_Warehouse_Acct_UU();

  /** Column name M_Warehouse_ID */
  public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

  /** Set Warehouse. Storage Warehouse and Service Point */
  public void setM_Warehouse_ID(int M_Warehouse_ID);

  /** Get Warehouse. Storage Warehouse and Service Point */
  public int getM_Warehouse_ID();

  public I_M_Warehouse getM_Warehouse() throws RuntimeException;

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name W_Differences_Acct */
  public static final String COLUMNNAME_W_Differences_Acct = "W_Differences_Acct";

  /** Set Warehouse Differences. Warehouse Differences Account */
  public void setW_Differences_Acct(int W_Differences_Acct);

  /** Get Warehouse Differences. Warehouse Differences Account */
  public int getW_Differences_Acct();

  public I_C_ValidCombination getW_Differences_A() throws RuntimeException;
}
