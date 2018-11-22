package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Workbench
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Workbench {

  /** TableName=AD_Workbench */
  public static final String Table_Name = "AD_Workbench";

  /** AD_Table_ID=468 */
  public static final int Table_ID = 468;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Color_ID */
  public static final String COLUMNNAME_AD_Color_ID = "AD_Color_ID";

  /** Set System Color. Color for backgrounds or indicators */
  public void setAD_Color_ID(String AD_Color_ID);

  /** Get System Color. Color for backgrounds or indicators */
  public String getAD_Color_ID();

  /** Column name AD_Column_ID */
  public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

  /** Set Column. Column in the table */
  public void setAD_Column_ID(int AD_Column_ID);

  /** Get Column. Column in the table */
  public int getAD_Column_ID();

  public I_AD_Column getAD_Column() throws RuntimeException;

  /** Column name AD_Image_ID */
  public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";

  /** Set Image. Image or Icon */
  public void setAD_Image_ID(int AD_Image_ID);

  /** Get Image. Image or Icon */
  public int getAD_Image_ID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Workbench_ID */
  public static final String COLUMNNAME_AD_Workbench_ID = "AD_Workbench_ID";

  /** Set Workbench. Collection of windows, reports */
  public void setAD_Workbench_ID(int AD_Workbench_ID);

  /** Get Workbench. Collection of windows, reports */
  public int getAD_Workbench_ID();

  /** Column name AD_Workbench_UU */
  public static final String COLUMNNAME_AD_Workbench_UU = "AD_Workbench_UU";

  /** Set AD_Workbench_UU */
  public void setAD_Workbench_UU(String AD_Workbench_UU);

  /** Get AD_Workbench_UU */
  public String getAD_Workbench_UU();

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
