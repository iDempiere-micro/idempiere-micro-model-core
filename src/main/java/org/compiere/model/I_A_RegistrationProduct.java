package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for A_RegistrationProduct
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_A_RegistrationProduct {

  /** TableName=A_RegistrationProduct */
  public static final String Table_Name = "A_RegistrationProduct";

  /** AD_Table_ID=715 */
  public static final int Table_ID = 715;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 2 - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(2);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name A_RegistrationAttribute_ID */
  public static final String COLUMNNAME_A_RegistrationAttribute_ID = "A_RegistrationAttribute_ID";

  /** Set Registration Attribute. Asset Registration Attribute */
  public void setA_RegistrationAttribute_ID(int A_RegistrationAttribute_ID);

  /** Get Registration Attribute. Asset Registration Attribute */
  public int getA_RegistrationAttribute_ID();

  public I_A_RegistrationAttribute getA_RegistrationAttribute() throws RuntimeException;

  /** Column name A_RegistrationProduct_UU */
  public static final String COLUMNNAME_A_RegistrationProduct_UU = "A_RegistrationProduct_UU";

  /** Set A_RegistrationProduct_UU */
  public void setA_RegistrationProduct_UU(String A_RegistrationProduct_UU);

  /** Get A_RegistrationProduct_UU */
  public String getA_RegistrationProduct_UU();

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

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
