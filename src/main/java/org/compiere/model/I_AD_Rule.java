package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Rule
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Rule {

  /** TableName=AD_Rule */
  public static final String Table_Name = "AD_Rule";

  /** AD_Table_ID=53058 */
  public static final int Table_ID = 53058;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Column name AccessLevel */
  public static final String COLUMNNAME_AccessLevel = "AccessLevel";

  /** Set Data Access Level. Access Level required */
  public void setRuleAccessLevel(String AccessLevel);

  /** Get Data Access Level. Access Level required */
  public String getRuleAccessLevel();

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Rule_ID */
  public static final String COLUMNNAME_AD_Rule_ID = "AD_Rule_ID";

  /** Set Rule */
  public void setAD_Rule_ID(int AD_Rule_ID);

  /** Get Rule */
  public int getAD_Rule_ID();

  /** Column name AD_Rule_UU */
  public static final String COLUMNNAME_AD_Rule_UU = "AD_Rule_UU";

  /** Set AD_Rule_UU */
  public void setAD_Rule_UU(String AD_Rule_UU);

  /** Get AD_Rule_UU */
  public String getAD_Rule_UU();

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

  /** Column name EventType */
  public static final String COLUMNNAME_EventType = "EventType";

  /** Set Event Type. Type of Event */
  public void setEventType(String EventType);

  /** Get Event Type. Type of Event */
  public String getEventType();

  /** Column name Help */
  public static final String COLUMNNAME_Help = "Help";

  /** Set Comment/Help. Comment or Hint */
  public void setHelp(String Help);

  /** Get Comment/Help. Comment or Hint */
  public String getHelp();

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

  /** Column name RuleType */
  public static final String COLUMNNAME_RuleType = "RuleType";

  /** Set Rule Type */
  public void setRuleType(String RuleType);

  /** Get Rule Type */
  public String getRuleType();

  /** Column name Script */
  public static final String COLUMNNAME_Script = "Script";

  /** Set Script. Dynamic Java Language Script to calculate result */
  public void setScript(String Script);

  /** Get Script. Dynamic Java Language Script to calculate result */
  public String getScript();

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
