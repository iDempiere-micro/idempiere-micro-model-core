package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_StorageReservation
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_StorageReservation {

  /** TableName=M_StorageReservation */
  public static final String Table_Name = "M_StorageReservation";

  /** AD_Table_ID=200027 */
  public static final int Table_ID = 200027;

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

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name DateLastInventory */
  public static final String COLUMNNAME_DateLastInventory = "DateLastInventory";

  /** Set Date last inventory count. Date of Last Inventory Count */
  public void setDateLastInventory(Timestamp DateLastInventory);

  /** Get Date last inventory count. Date of Last Inventory Count */
  public Timestamp getDateLastInventory();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name IsSOTrx */
  public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

  /** Set Sales Transaction. This is a Sales Transaction */
  public void setIsSOTrx(boolean IsSOTrx);

  /** Get Sales Transaction. This is a Sales Transaction */
  public boolean isSOTrx();

  /** Column name M_AttributeSetInstance_ID */
  public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

  /** Set Attribute Set Instance. Product Attribute Set Instance */
  public void setM_AttributeSetInstance_ID(int M_AttributeSetInstance_ID);

  /** Get Attribute Set Instance. Product Attribute Set Instance */
  public int getMAttributeSetInstance_ID();

  public I_M_AttributeSetInstance getMAttributeSetInstance() throws RuntimeException;

  /** Column name M_Product_ID */
  public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

  /** Set Product. Product, Service, Item */
  public void setM_Product_ID(int M_Product_ID);

  /** Get Product. Product, Service, Item */
  public int getM_Product_ID();

  public I_M_Product getM_Product() throws RuntimeException;

  /** Column name M_StorageReservation_UU */
  public static final String COLUMNNAME_M_StorageReservation_UU = "M_StorageReservation_UU";

  /** Set M_StorageReservation_UU */
  public void setM_StorageReservation_UU(String M_StorageReservation_UU);

  /** Get M_StorageReservation_UU */
  public String getM_StorageReservation_UU();

  /** Column name M_Warehouse_ID */
  public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

  /** Set Warehouse. Storage Warehouse and Service Point */
  public void setM_Warehouse_ID(int M_Warehouse_ID);

  /** Get Warehouse. Storage Warehouse and Service Point */
  public int getM_Warehouse_ID();

  public I_M_Warehouse getM_Warehouse() throws RuntimeException;

  /** Column name Qty */
  public static final String COLUMNNAME_Qty = "Qty";

  /** Set Quantity. Quantity */
  public void setQty(BigDecimal Qty);

  /** Get Quantity. Quantity */
  public BigDecimal getQty();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
