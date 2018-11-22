package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_PrintFont
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_PrintFont {

  /** TableName=AD_PrintFont */
  public static final String Table_Name = "AD_PrintFont";

  /** AD_Table_ID=491 */
  public static final int Table_ID = 491;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_PrintFont_ID */
  public static final String COLUMNNAME_AD_PrintFont_ID = "AD_PrintFont_ID";

  /** Set Print Font. Maintain Print Font */
  public void setAD_PrintFont_ID(int AD_PrintFont_ID);

  /** Get Print Font. Maintain Print Font */
  public int getAD_PrintFont_ID();

  /** Column name AD_PrintFont_UU */
  public static final String COLUMNNAME_AD_PrintFont_UU = "AD_PrintFont_UU";

  /** Set AD_PrintFont_UU */
  public void setAD_PrintFont_UU(String AD_PrintFont_UU);

  /** Get AD_PrintFont_UU */
  public String getAD_PrintFont_UU();

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
