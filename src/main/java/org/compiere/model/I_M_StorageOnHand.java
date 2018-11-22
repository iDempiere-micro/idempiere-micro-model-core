package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_StorageOnHand
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_StorageOnHand {

  /** TableName=M_StorageOnHand */
  public static final String Table_Name = "M_StorageOnHand";

  /** AD_Table_ID=200026 */
  public static final int Table_ID = 200026;

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

  /** Column name DateMaterialPolicy */
  public static final String COLUMNNAME_DateMaterialPolicy = "DateMaterialPolicy";

  /** Set Date Material Policy. Time used for LIFO and FIFO Material Policy */
  public void setDateMaterialPolicy(Timestamp DateMaterialPolicy);

  /** Get Date Material Policy. Time used for LIFO and FIFO Material Policy */
  public Timestamp getDateMaterialPolicy();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name M_AttributeSetInstance_ID */
  public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

  /** Set Attribute Set Instance. Product Attribute Set Instance */
  public void setM_AttributeSetInstance_ID(int M_AttributeSetInstance_ID);

  /** Get Attribute Set Instance. Product Attribute Set Instance */
  public int getMAttributeSetInstance_ID();

  public I_M_AttributeSetInstance getMAttributeSetInstance() throws RuntimeException;

  /** Column name M_Locator_ID */
  public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

  /** Set Locator. Warehouse Locator */
  public void setM_Locator_ID(int M_Locator_ID);

  /** Get Locator. Warehouse Locator */
  public int getM_Locator_ID();

  public I_M_Locator getM_Locator() throws RuntimeException;

  /** Column name M_Product_ID */
  public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

  /** Set Product. Product, Service, Item */
  public void setM_Product_ID(int M_Product_ID);

  /** Get Product. Product, Service, Item */
  public int getM_Product_ID();

  public I_M_Product getM_Product() throws RuntimeException;

  /** Column name M_StorageOnHand_UU */
  public static final String COLUMNNAME_M_StorageOnHand_UU = "M_StorageOnHand_UU";

  /** Set M_StorageOnHand_UU */
  public void setM_StorageOnHand_UU(String M_StorageOnHand_UU);

  /** Get M_StorageOnHand_UU */
  public String getM_StorageOnHand_UU();

  /** Column name QtyOnHand */
  public static final String COLUMNNAME_QtyOnHand = "QtyOnHand";

  /** Set On Hand Quantity. On Hand Quantity */
  public void setQtyOnHand(BigDecimal QtyOnHand);

  /** Get On Hand Quantity. On Hand Quantity */
  public BigDecimal getQtyOnHand();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
