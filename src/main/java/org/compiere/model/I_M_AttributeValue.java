package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_AttributeValue
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_AttributeValue {

  /** TableName=M_AttributeValue */
  public static final String Table_Name = "M_AttributeValue";

  /** AD_Table_ID=558 */
  public static final int Table_ID = 558;

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

  /** Column name M_Attribute_ID */
  public static final String COLUMNNAME_M_Attribute_ID = "M_Attribute_ID";

  /** Set Attribute. Product Attribute */
  public void setMAttributeID(int M_Attribute_ID);

  /** Get Attribute. Product Attribute */
  public int getMAttribute_ID();

  public I_M_Attribute getMAttribute() throws RuntimeException;

  /** Column name M_AttributeValue_ID */
  public static final String COLUMNNAME_M_AttributeValue_ID = "M_AttributeValue_ID";

  /** Set Attribute Value. Product Attribute Value */
  public void setM_AttributeValue_ID(int M_AttributeValue_ID);

  /** Get Attribute Value. Product Attribute Value */
  public int getMAttributeValue_ID();

  /** Column name M_AttributeValue_UU */
  public static final String COLUMNNAME_M_AttributeValue_UU = "M_AttributeValue_UU";

  /** Set M_AttributeValue_UU */
  public void setM_AttributeValue_UU(String M_AttributeValue_UU);

  /** Get M_AttributeValue_UU */
  public String getMAttributeValue_UU();

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
