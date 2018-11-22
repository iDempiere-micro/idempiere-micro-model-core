package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_AllClients_V
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_AllClients_V {

  /** TableName=AD_AllClients_V */
  public static final String Table_Name = "AD_AllClients_V";

  /** AD_Table_ID=200223 */
  public static final int Table_ID = 200223;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Column name AD_AllClients_V_ID */
  public static final String COLUMNNAME_AD_AllClients_V_ID = "AD_AllClients_V_ID";

  /** Set All Clients */
  public void setAD_AllClients_V_ID(int AD_AllClients_V_ID);

  /** Get All Clients */
  public int getAD_AllClients_V_ID();

  /** Column name AD_AllClients_V_UU */
  public static final String COLUMNNAME_AD_AllClients_V_UU = "AD_AllClients_V_UU";

  /** Set AD_AllClients_V_UU */
  public void setAD_AllClients_V_UU(String AD_AllClients_V_UU);

  /** Get AD_AllClients_V_UU */
  public String getAD_AllClients_V_UU();

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Language */
  public static final String COLUMNNAME_AD_Language = "AD_Language";

  /** Set Language. Language for this entity */
  public void setADLanguage(String AD_Language);

  /** Get Language. Language for this entity */
  public String getADLanguage();

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
