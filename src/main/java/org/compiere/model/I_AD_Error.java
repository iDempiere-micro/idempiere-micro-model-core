package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Error
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Error {

  /** TableName=AD_Error */
  public static final String Table_Name = "AD_Error";

  /** AD_Table_ID=380 */
  public static final int Table_ID = 380;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Error_ID */
  public static final String COLUMNNAME_AD_Error_ID = "AD_Error_ID";

  /** Set Error */
  public void setAD_Error_ID(int AD_Error_ID);

  /** Get Error */
  public int getAD_Error_ID();

  /** Column name AD_Error_UU */
  public static final String COLUMNNAME_AD_Error_UU = "AD_Error_UU";

  /** Set AD_Error_UU */
  public void setAD_Error_UU(String AD_Error_UU);

  /** Get AD_Error_UU */
  public String getAD_Error_UU();

  /** Column name AD_Language */
  public static final String COLUMNNAME_AD_Language = "AD_Language";

  /** Set Language. Language for this entity */
  public void setADLanguage(String AD_Language);

  /** Get Language. Language for this entity */
  public String getADLanguage();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name Code */
  public static final String COLUMNNAME_Code = "Code";

  /** Set Validation code. Validation Code */
  public void setCode(String Code);

  /** Get Validation code. Validation Code */
  public String getCode();

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
