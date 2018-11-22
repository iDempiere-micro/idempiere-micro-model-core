package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_Bank
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_Bank {

  /** TableName=C_Bank */
  public static final String Table_Name = "C_Bank";

  /** AD_Table_ID=296 */
  public static final int Table_ID = 296;

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

  /** Column name C_Bank_ID */
  public static final String COLUMNNAME_C_Bank_ID = "C_Bank_ID";

  /** Set Bank. Bank */
  public void setC_Bank_ID(int C_Bank_ID);

  /** Get Bank. Bank */
  public int getC_Bank_ID();

  /** Column name C_Bank_UU */
  public static final String COLUMNNAME_C_Bank_UU = "C_Bank_UU";

  /** Set C_Bank_UU */
  public void setC_Bank_UU(String C_Bank_UU);

  /** Get C_Bank_UU */
  public String getC_Bank_UU();

  /** Column name C_Location_ID */
  public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

  /** Set Address. Location or Address */
  public void setC_Location_ID(int C_Location_ID);

  /** Get Address. Location or Address */
  public int getC_Location_ID();

  public I_C_Location getC_Location() throws RuntimeException;

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

  /** Column name IsOwnBank */
  public static final String COLUMNNAME_IsOwnBank = "IsOwnBank";

  /** Set Own Bank. Bank for this Organization */
  public void setIsOwnBank(boolean IsOwnBank);

  /** Get Own Bank. Bank for this Organization */
  public boolean isOwnBank();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name RoutingNo */
  public static final String COLUMNNAME_RoutingNo = "RoutingNo";

  /** Set Routing No. Bank Routing Number */
  public void setRoutingNo(String RoutingNo);

  /** Get Routing No. Bank Routing Number */
  public String getRoutingNo();

  /** Column name SwiftCode */
  public static final String COLUMNNAME_SwiftCode = "SwiftCode";

  /** Set Swift code. Swift Code or BIC */
  public void setSwiftCode(String SwiftCode);

  /** Get Swift code. Swift Code or BIC */
  public String getSwiftCode();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
