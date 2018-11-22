package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for U_BlackListCheque
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_U_BlackListCheque {

  /** TableName=U_BlackListCheque */
  public static final String Table_Name = "U_BlackListCheque";

  /** AD_Table_ID=52000 */
  public static final int Table_ID = 52000;

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

  /** Column name BankName */
  public static final String COLUMNNAME_BankName = "BankName";

  /** Set Bank Name */
  public void setBankName(String BankName);

  /** Get Bank Name */
  public String getBankName();

  /** Column name ChequeNo */
  public static final String COLUMNNAME_ChequeNo = "ChequeNo";

  /** Set Cheque No */
  public void setChequeNo(String ChequeNo);

  /** Get Cheque No */
  public String getChequeNo();

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

  /** Column name U_BlackListCheque_ID */
  public static final String COLUMNNAME_U_BlackListCheque_ID = "U_BlackListCheque_ID";

  /** Set Black List Cheque */
  public void setU_BlackListCheque_ID(int U_BlackListCheque_ID);

  /** Get Black List Cheque */
  public int getU_BlackListCheque_ID();

  /** Column name U_BlackListCheque_UU */
  public static final String COLUMNNAME_U_BlackListCheque_UU = "U_BlackListCheque_UU";

  /** Set U_BlackListCheque_UU */
  public void setU_BlackListCheque_UU(String U_BlackListCheque_UU);

  /** Get U_BlackListCheque_UU */
  public String getU_BlackListCheque_UU();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
