package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_OrgType
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_OrgType {

  /** TableName=AD_OrgType */
  public static final String Table_Name = "AD_OrgType";

  /** AD_Table_ID=689 */
  public static final int Table_ID = 689;

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

  /** Column name AD_OrgType_ID */
  public static final String COLUMNNAME_AD_OrgType_ID = "AD_OrgType_ID";

  /** Set Organization Type. Organization Type */
  public void setAD_OrgType_ID(int AD_OrgType_ID);

  /** Get Organization Type. Organization Type */
  public int getAD_OrgType_ID();

  /** Column name AD_OrgType_UU */
  public static final String COLUMNNAME_AD_OrgType_UU = "AD_OrgType_UU";

  /** Set AD_OrgType_UU */
  public void setAD_OrgType_UU(String AD_OrgType_UU);

  /** Get AD_OrgType_UU */
  public String getAD_OrgType_UU();

  /** Column name AD_PrintColor_ID */
  public static final String COLUMNNAME_AD_PrintColor_ID = "AD_PrintColor_ID";

  /** Set Print Color. Color used for printing and display */
  public void setAD_PrintColor_ID(int AD_PrintColor_ID);

  /** Get Print Color. Color used for printing and display */
  public int getAD_PrintColor_ID();

  public I_AD_PrintColor getAD_PrintColor() throws RuntimeException;

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
