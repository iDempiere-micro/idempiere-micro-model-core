package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_Shipper
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_Shipper {

  /** TableName=M_Shipper */
  public static final String Table_Name = "M_Shipper";

  /** AD_Table_ID=253 */
  public static final int Table_ID = 253;

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

  /** Column name C_BPartner_ID */
  public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

  /** Set Business Partner . Identifies a Business Partner */
  public void setC_BPartner_ID(int C_BPartner_ID);

  /** Get Business Partner . Identifies a Business Partner */
  public int getC_BPartner_ID();

  public I_C_BPartner getC_BPartner() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name CreateFrom */
  public static final String COLUMNNAME_CreateFrom = "CreateFrom";

  /**
   * Set Create lines from. Process which will generate a new document lines based on an existing
   * document
   */
  public void setCreateFrom(String CreateFrom);

  /**
   * Get Create lines from. Process which will generate a new document lines based on an existing
   * document
   */
  public String getCreateFrom();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name M_ShipperCfg_ID */
  public static final String COLUMNNAME_M_ShipperCfg_ID = "M_ShipperCfg_ID";

  /** Set Shipper Configuration */
  public void setM_ShipperCfg_ID(int M_ShipperCfg_ID);

  /** Get Shipper Configuration */
  public int getM_ShipperCfg_ID();

  public I_M_ShipperCfg getM_ShipperCfg() throws RuntimeException;

  /** Column name M_Shipper_ID */
  public static final String COLUMNNAME_M_Shipper_ID = "M_Shipper_ID";

  /** Set Shipper. Method or manner of product delivery */
  public void setM_Shipper_ID(int M_Shipper_ID);

  /** Get Shipper. Method or manner of product delivery */
  public int getM_Shipper_ID();

  /** Column name M_Shipper_UU */
  public static final String COLUMNNAME_M_Shipper_UU = "M_Shipper_UU";

  /** Set M_Shipper_UU */
  public void setM_Shipper_UU(String M_Shipper_UU);

  /** Get M_Shipper_UU */
  public String getM_Shipper_UU();

  /** Column name M_ShippingProcessor_ID */
  public static final String COLUMNNAME_M_ShippingProcessor_ID = "M_ShippingProcessor_ID";

  /** Set Shipping Processor */
  public void setM_ShippingProcessor_ID(int M_ShippingProcessor_ID);

  /** Get Shipping Processor */
  public int getM_ShippingProcessor_ID();

  public I_M_ShippingProcessor getM_ShippingProcessor() throws RuntimeException;

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
