package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_ShipperPackagingCfg
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_ShipperPackagingCfg {

  /** TableName=M_ShipperPackagingCfg */
  public static final String Table_Name = "M_ShipperPackagingCfg";

  /** AD_Table_ID=200049 */
  public static final int Table_ID = 200049;

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

  /** Column name M_ShipperCfg_ID */
  public static final String COLUMNNAME_M_ShipperCfg_ID = "M_ShipperCfg_ID";

  /** Set Shipper Configuration */
  public void setM_ShipperCfg_ID(int M_ShipperCfg_ID);

  /** Get Shipper Configuration */
  public int getM_ShipperCfg_ID();

  public I_M_ShipperCfg getM_ShipperCfg() throws RuntimeException;

  /** Column name M_ShipperPackagingCfg_ID */
  public static final String COLUMNNAME_M_ShipperPackagingCfg_ID = "M_ShipperPackagingCfg_ID";

  /** Set Shipper Packaging Configuration */
  public void setM_ShipperPackagingCfg_ID(int M_ShipperPackagingCfg_ID);

  /** Get Shipper Packaging Configuration */
  public int getM_ShipperPackagingCfg_ID();

  /** Column name M_ShipperPackagingCfg_UU */
  public static final String COLUMNNAME_M_ShipperPackagingCfg_UU = "M_ShipperPackagingCfg_UU";

  /** Set M_ShipperPackagingCfg_UU */
  public void setM_ShipperPackagingCfg_UU(String M_ShipperPackagingCfg_UU);

  /** Get M_ShipperPackagingCfg_UU */
  public String getM_ShipperPackagingCfg_UU();

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

  /** Column name Weight */
  public static final String COLUMNNAME_Weight = "Weight";

  /** Set Weight. Weight of a product */
  public void setWeight(BigDecimal Weight);

  /** Get Weight. Weight of a product */
  public BigDecimal getWeight();
}
