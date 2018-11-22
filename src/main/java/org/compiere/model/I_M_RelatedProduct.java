package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_RelatedProduct
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_RelatedProduct {

  /** TableName=M_RelatedProduct */
  public static final String Table_Name = "M_RelatedProduct";

  /** AD_Table_ID=662 */
  public static final int Table_ID = 662;

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

  /** Column name M_Product_ID */
  public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

  /** Set Product. Product, Service, Item */
  public void setM_Product_ID(int M_Product_ID);

  /** Get Product. Product, Service, Item */
  public int getM_Product_ID();

  public I_M_Product getM_Product() throws RuntimeException;

  /** Column name M_RelatedProduct_UU */
  public static final String COLUMNNAME_M_RelatedProduct_UU = "M_RelatedProduct_UU";

  /** Set M_RelatedProduct_UU */
  public void setM_RelatedProduct_UU(String M_RelatedProduct_UU);

  /** Get M_RelatedProduct_UU */
  public String getM_RelatedProduct_UU();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name RelatedProduct_ID */
  public static final String COLUMNNAME_RelatedProduct_ID = "RelatedProduct_ID";

  /** Set Related Product. Related Product */
  public void setRelatedProduct_ID(int RelatedProduct_ID);

  /** Get Related Product. Related Product */
  public int getRelatedProduct_ID();

  public I_M_Product getRelatedProduct() throws RuntimeException;

  /** Column name RelatedProductType */
  public static final String COLUMNNAME_RelatedProductType = "RelatedProductType";

  /** Set Related Product Type */
  public void setRelatedProductType(String RelatedProductType);

  /** Get Related Product Type */
  public String getRelatedProductType();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
