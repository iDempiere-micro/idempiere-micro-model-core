package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_ProjectType
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_ProjectType {

  /** TableName=C_ProjectType */
  public static final String Table_Name = "C_ProjectType";

  /** AD_Table_ID=575 */
  public static final int Table_ID = 575;

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

  /** Column name C_ProjectType_ID */
  public static final String COLUMNNAME_C_ProjectType_ID = "C_ProjectType_ID";

  /** Set Project Type. Type of the project */
  public void setC_ProjectType_ID(int C_ProjectType_ID);

  /** Get Project Type. Type of the project */
  public int getC_ProjectType_ID();

  /** Column name C_ProjectType_UU */
  public static final String COLUMNNAME_C_ProjectType_UU = "C_ProjectType_UU";

  /** Set C_ProjectType_UU */
  public void setC_ProjectType_UU(String C_ProjectType_UU);

  /** Get C_ProjectType_UU */
  public String getC_ProjectType_UU();

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

  /** Column name ProjectCategory */
  public static final String COLUMNNAME_ProjectCategory = "ProjectCategory";

  /** Set Project Category. Project Category */
  public void setProjectCategory(String ProjectCategory);

  /** Get Project Category. Project Category */
  public String getProjectCategory();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
