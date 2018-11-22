package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_AttributeSearch
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_AttributeSearch {

  /** TableName=M_AttributeSearch */
  public static final String Table_Name = "M_AttributeSearch";

  /** AD_Table_ID=564 */
  public static final int Table_ID = 564;

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

  /** Column name M_AttributeSearch_ID */
  public static final String COLUMNNAME_M_AttributeSearch_ID = "M_AttributeSearch_ID";

  /** Set Attribute Search. Common Search Attribute */
  public void setM_AttributeSearch_ID(int M_AttributeSearch_ID);

  /** Get Attribute Search. Common Search Attribute */
  public int getMAttributeSearch_ID();

  /** Column name M_AttributeSearch_UU */
  public static final String COLUMNNAME_M_AttributeSearch_UU = "M_AttributeSearch_UU";

  /** Set M_AttributeSearch_UU */
  public void setM_AttributeSearch_UU(String M_AttributeSearch_UU);

  /** Get M_AttributeSearch_UU */
  public String getMAttributeSearch_UU();

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
