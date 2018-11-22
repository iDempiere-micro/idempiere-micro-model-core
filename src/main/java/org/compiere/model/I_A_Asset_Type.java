package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for A_Asset_Type
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_A_Asset_Type {

  /** TableName=A_Asset_Type */
  public static final String Table_Name = "A_Asset_Type";

  /** AD_Table_ID=53276 */
  public static final int Table_ID = 53276;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 3 - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(3);

  /** Load Meta Data */

  /** Column name A_Asset_Type_ID */
  public static final String COLUMNNAME_A_Asset_Type_ID = "A_Asset_Type_ID";

  /** Set Asset Type */
  public void setA_Asset_Type_ID(int A_Asset_Type_ID);

  /** Get Asset Type */
  public int getA_Asset_Type_ID();

  /** Column name A_Asset_Type_UU */
  public static final String COLUMNNAME_A_Asset_Type_UU = "A_Asset_Type_UU";

  /** Set A_Asset_Type_UU */
  public void setA_Asset_Type_UU(String A_Asset_Type_UU);

  /** Get A_Asset_Type_UU */
  public String getA_Asset_Type_UU();

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

  /** Column name IsDepreciable */
  public static final String COLUMNNAME_IsDepreciable = "IsDepreciable";

  /** Set Is Depreciable. This asset CAN be depreciated */
  public void setIsDepreciable(String IsDepreciable);

  /** Get Is Depreciable. This asset CAN be depreciated */
  public String getIsDepreciable();

  /** Column name IsInPosession */
  public static final String COLUMNNAME_IsInPosession = "IsInPosession";

  /** Set In Possession. The asset is in the possession of the organization */
  public void setIsInPosession(String IsInPosession);

  /** Get In Possession. The asset is in the possession of the organization */
  public String getIsInPosession();

  /** Column name IsOwned */
  public static final String COLUMNNAME_IsOwned = "IsOwned";

  /** Set Owned. The asset is owned by the organization */
  public void setIsOwned(String IsOwned);

  /** Get Owned. The asset is owned by the organization */
  public String getIsOwned();

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

  /** Column name Value */
  public static final String COLUMNNAME_Value = "Value";

  /** Set Search Key. Search key for the record in the format required - must be unique */
  public void setValue(String Value);

  /** Get Search Key. Search key for the record in the format required - must be unique */
  public String getValue();
}
