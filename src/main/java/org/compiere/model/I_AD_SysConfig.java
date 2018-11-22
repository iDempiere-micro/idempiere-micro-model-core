package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_SysConfig
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_SysConfig {

  /** TableName=AD_SysConfig */
  public static final String Table_Name = "AD_SysConfig";

  /** AD_Table_ID=50009 */
  public static final int Table_ID = 50009;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_SysConfig_ID */
  public static final String COLUMNNAME_AD_SysConfig_ID = "AD_SysConfig_ID";

  /** Set System Configurator */
  public void setAD_SysConfig_ID(int AD_SysConfig_ID);

  /** Get System Configurator */
  public int getAD_SysConfig_ID();

  /** Column name AD_SysConfig_UU */
  public static final String COLUMNNAME_AD_SysConfig_UU = "AD_SysConfig_UU";

  /** Set AD_SysConfig_UU */
  public void setAD_SysConfig_UU(String AD_SysConfig_UU);

  /** Get AD_SysConfig_UU */
  public String getAD_SysConfig_UU();

  /** Column name ConfigurationLevel */
  public static final String COLUMNNAME_ConfigurationLevel = "ConfigurationLevel";

  /** Set Configuration Level. Configuration Level for this parameter */
  public void setConfigurationLevel(String ConfigurationLevel);

  /** Get Configuration Level. Configuration Level for this parameter */
  public String getConfigurationLevel();

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
