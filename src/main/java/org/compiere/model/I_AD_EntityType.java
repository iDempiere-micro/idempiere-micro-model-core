package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_EntityType
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_EntityType {

  /** TableName=AD_EntityType */
  public static final String Table_Name = "AD_EntityType";

  /** AD_Table_ID=882 */
  public static final int Table_ID = 882;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_EntityType_ID */
  public static final String COLUMNNAME_AD_EntityType_ID = "AD_EntityType_ID";

  /** Set Entity Type. System Entity Type */
  public void setAD_EntityType_ID(int AD_EntityType_ID);

  /** Get Entity Type. System Entity Type */
  public int getAD_EntityType_ID();

  /** Column name AD_EntityType_UU */
  public static final String COLUMNNAME_AD_EntityType_UU = "AD_EntityType_UU";

  /** Set AD_EntityType_UU */
  public void setAD_EntityType_UU(String AD_EntityType_UU);

  /** Get AD_EntityType_UU */
  public String getAD_EntityType_UU();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name Classpath */
  public static final String COLUMNNAME_Classpath = "Classpath";

  /** Set Classpath. Extension Classpath */
  public void setClasspath(String Classpath);

  /** Get Classpath. Extension Classpath */
  public String getClasspath();

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

  /** Column name ModelPackage */
  public static final String COLUMNNAME_ModelPackage = "ModelPackage";

  /** Set ModelPackage. Java Package of the model classes */
  public void setModelPackage(String ModelPackage);

  /** Get ModelPackage. Java Package of the model classes */
  public String getModelPackage();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name Processing */
  public static final String COLUMNNAME_Processing = "Processing";

  /** Set Process Now */
  public void setProcessing(boolean Processing);

  /** Get Process Now */
  public boolean isProcessing();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name Version */
  public static final String COLUMNNAME_Version = "Version";

  /** Set Version. Version of the table definition */
  public void setVersion(String Version);

  /** Get Version. Version of the table definition */
  public String getVersion();
}
