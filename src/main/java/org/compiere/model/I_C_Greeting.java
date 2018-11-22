package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_Greeting
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_Greeting {

  /** TableName=C_Greeting */
  public static final String Table_Name = "C_Greeting";

  /** AD_Table_ID=346 */
  public static final int Table_ID = 346;

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

  /** Column name C_Greeting_ID */
  public static final String COLUMNNAME_C_Greeting_ID = "C_Greeting_ID";

  /** Set Greeting. Greeting to print on correspondence */
  public void setC_Greeting_ID(int C_Greeting_ID);

  /** Get Greeting. Greeting to print on correspondence */
  public int getC_Greeting_ID();

  /** Column name C_Greeting_UU */
  public static final String COLUMNNAME_C_Greeting_UU = "C_Greeting_UU";

  /** Set C_Greeting_UU */
  public void setC_Greeting_UU(String C_Greeting_UU);

  /** Get C_Greeting_UU */
  public String getC_Greeting_UU();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name Greeting */
  public static final String COLUMNNAME_Greeting = "Greeting";

  /**
   * Set Greeting. For letters, e.g. "Dear { 0} " or "Dear Mr. { 0} " - At runtime, " { 0} " is
   * replaced by the name
   */
  public void setGreeting(String Greeting);

  /**
   * Get Greeting. For letters, e.g. "Dear { 0} " or "Dear Mr. { 0} " - At runtime, " { 0} " is
   * replaced by the name
   */
  public String getGreeting();

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

  /** Column name IsFirstNameOnly */
  public static final String COLUMNNAME_IsFirstNameOnly = "IsFirstNameOnly";

  /** Set First name only. Print only the first name in greetings */
  public void setIsFirstNameOnly(boolean IsFirstNameOnly);

  /** Get First name only. Print only the first name in greetings */
  public boolean isFirstNameOnly();

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
