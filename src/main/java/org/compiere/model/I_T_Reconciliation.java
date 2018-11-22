package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for T_Reconciliation
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_T_Reconciliation {

  /** TableName=T_Reconciliation */
  public static final String Table_Name = "T_Reconciliation";

  /** AD_Table_ID=53287 */
  public static final int Table_ID = 53287;

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

  /** Column name AD_PInstance_ID */
  public static final String COLUMNNAME_AD_PInstance_ID = "AD_PInstance_ID";

  /** Set Process Instance. Instance of the process */
  public void setAD_PInstance_ID(int AD_PInstance_ID);

  /** Get Process Instance. Instance of the process */
  public int getAD_PInstance_ID();

  public I_AD_PInstance getAD_PInstance() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name Fact_Acct_ID */
  public static final String COLUMNNAME_Fact_Acct_ID = "Fact_Acct_ID";

  /** Set Accounting Fact */
  public void setFact_Acct_ID(int Fact_Acct_ID);

  /** Get Accounting Fact */
  public int getFact_Acct_ID();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name MatchCode */
  public static final String COLUMNNAME_MatchCode = "MatchCode";

  /** Set Match Code. String identifying related accounting facts */
  public void setMatchCode(String MatchCode);

  /** Get Match Code. String identifying related accounting facts */
  public String getMatchCode();

  /** Column name T_Reconciliation_UU */
  public static final String COLUMNNAME_T_Reconciliation_UU = "T_Reconciliation_UU";

  /** Set T_Reconciliation_UU */
  public void setT_Reconciliation_UU(String T_Reconciliation_UU);

  /** Get T_Reconciliation_UU */
  public String getT_Reconciliation_UU();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
