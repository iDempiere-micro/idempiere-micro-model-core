package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Val_Rule
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Val_Rule {

  /** TableName=AD_Val_Rule */
  public static final String Table_Name = "AD_Val_Rule";

  /** AD_Table_ID=108 */
  public static final int Table_ID = 108;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Val_Rule_ID */
  public static final String COLUMNNAME_AD_Val_Rule_ID = "AD_Val_Rule_ID";

  /** Set Dynamic Validation. Dynamic Validation Rule */
  public void setAD_Val_Rule_ID(int AD_Val_Rule_ID);

  /** Get Dynamic Validation. Dynamic Validation Rule */
  public int getAD_Val_Rule_ID();

  /** Column name AD_Val_Rule_UU */
  public static final String COLUMNNAME_AD_Val_Rule_UU = "AD_Val_Rule_UU";

  /** Set AD_Val_Rule_UU */
  public void setAD_Val_Rule_UU(String AD_Val_Rule_UU);

  /** Get AD_Val_Rule_UU */
  public String getAD_Val_Rule_UU();

  /** Column name Code */
  public static final String COLUMNNAME_Code = "Code";

  /** Set Validation code. Validation Code */
  public void setCode(String Code);

  /** Get Validation code. Validation Code */
  public String getCode();

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

  /** Column name EntityType */
  public static final String COLUMNNAME_EntityType = "EntityType";

  /** Set Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public void setEntityType(String EntityType);

  /** Get Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public String getEntityType();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name Type */
  public static final String COLUMNNAME_Type = "Type";

  /** Set Type. Type of Validation (SQL, Java Script, Java Language) */
  public void setType(String Type);

  /** Get Type. Type of Validation (SQL, Java Script, Java Language) */
  public String getType();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
