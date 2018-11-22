package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for A_Asset_Product
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_A_Asset_Product {

  /** TableName=A_Asset_Product */
  public static final String Table_Name = "A_Asset_Product";

  /** AD_Table_ID=53270 */
  public static final int Table_ID = 53270;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 3 - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(3);

  /** Load Meta Data */

  /** Column name A_Asset_ID */
  public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

  /** Set Asset. Asset used internally or by customers */
  public void setA_Asset_ID(int A_Asset_ID);

  /** Get Asset. Asset used internally or by customers */
  public int getA_Asset_ID();

  public I_A_Asset getA_Asset() throws RuntimeException;

  /** Column name A_Asset_Product_ID */
  public static final String COLUMNNAME_A_Asset_Product_ID = "A_Asset_Product_ID";

  /** Set Asset Product */
  public void setA_Asset_Product_ID(int A_Asset_Product_ID);

  /** Get Asset Product */
  public int getA_Asset_Product_ID();

  /** Column name A_Asset_Product_UU */
  public static final String COLUMNNAME_A_Asset_Product_UU = "A_Asset_Product_UU";

  /** Set A_Asset_Product_UU */
  public void setA_Asset_Product_UU(String A_Asset_Product_UU);

  /** Get A_Asset_Product_UU */
  public String getA_Asset_Product_UU();

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name A_QTY_Current */
  public static final String COLUMNNAME_A_QTY_Current = "A_QTY_Current";

  /** Set Current Qty */
  public void setA_QTY_Current(BigDecimal A_QTY_Current);

  /** Get Current Qty */
  public BigDecimal getA_QTY_Current();

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

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
