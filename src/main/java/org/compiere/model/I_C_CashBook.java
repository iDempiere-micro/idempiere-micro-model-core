package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_CashBook
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_CashBook {

  /** TableName=C_CashBook */
  public static final String Table_Name = "C_CashBook";

  /** AD_Table_ID=408 */
  public static final int Table_ID = 408;

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

  /** Column name C_CashBook_ID */
  public static final String COLUMNNAME_C_CashBook_ID = "C_CashBook_ID";

  /** Set Cash Book. Cash Book for recording petty cash transactions */
  public void setC_CashBook_ID(int C_CashBook_ID);

  /** Get Cash Book. Cash Book for recording petty cash transactions */
  public int getC_CashBook_ID();

  /** Column name C_CashBook_UU */
  public static final String COLUMNNAME_C_CashBook_UU = "C_CashBook_UU";

  /** Set C_CashBook_UU */
  public void setC_CashBook_UU(String C_CashBook_UU);

  /** Get C_CashBook_UU */
  public String getC_CashBook_UU();

  /** Column name C_Currency_ID */
  public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

  /** Set Currency. The Currency for this record */
  public void setC_Currency_ID(int C_Currency_ID);

  /** Get Currency. The Currency for this record */
  public int getC_Currency_ID();

  public I_C_Currency getC_Currency() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

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
