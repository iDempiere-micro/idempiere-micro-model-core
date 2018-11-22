package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for A_Asset_Use
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_A_Asset_Use {

  /** TableName=A_Asset_Use */
  public static final String Table_Name = "A_Asset_Use";

  /** AD_Table_ID=53138 */
  public static final int Table_ID = 53138;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 7 - System - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(7);

  /** Load Meta Data */

  /** Column name A_Asset_ID */
  public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

  /** Set Asset. Asset used internally or by customers */
  public void setA_Asset_ID(int A_Asset_ID);

  /** Get Asset. Asset used internally or by customers */
  public int getA_Asset_ID();

  public I_A_Asset getA_Asset() throws RuntimeException;

  /** Column name A_Asset_Use_ID */
  public static final String COLUMNNAME_A_Asset_Use_ID = "A_Asset_Use_ID";

  /** Set A_Asset_Use_ID */
  public void setA_Asset_Use_ID(int A_Asset_Use_ID);

  /** Get A_Asset_Use_ID */
  public int getA_Asset_Use_ID();

  /** Column name A_Asset_Use_UU */
  public static final String COLUMNNAME_A_Asset_Use_UU = "A_Asset_Use_UU";

  /** Set A_Asset_Use_UU */
  public void setA_Asset_Use_UU(String A_Asset_Use_UU);

  /** Get A_Asset_Use_UU */
  public String getA_Asset_Use_UU();

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

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

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name UseDate */
  public static final String COLUMNNAME_UseDate = "UseDate";

  /** Set UseDate */
  public void setUseDate(Timestamp UseDate);

  /** Get UseDate */
  public Timestamp getUseDate();

  /** Column name UseUnits */
  public static final String COLUMNNAME_UseUnits = "UseUnits";

  /** Set Use units. Currently used units of the assets */
  public void setUseUnits(int UseUnits);

  /** Get Use units. Currently used units of the assets */
  public int getUseUnits();
}
