package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_UserDef_Win
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_UserDef_Win {

  /** TableName=AD_UserDef_Win */
  public static final String Table_Name = "AD_UserDef_Win";

  /** AD_Table_ID=467 */
  public static final int Table_ID = 467;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

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

  /** Column name AD_Role_ID */
  public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

  /** Set Role. Responsibility Role */
  public void setAD_Role_ID(int AD_Role_ID);

  /** Get Role. Responsibility Role */
  public int getAD_Role_ID();

  public I_AD_Role getAD_Role() throws RuntimeException;

  /** Column name AD_UserDef_Win_ID */
  public static final String COLUMNNAME_AD_UserDef_Win_ID = "AD_UserDef_Win_ID";

  /** Set User defined Window */
  public void setAD_UserDef_Win_ID(int AD_UserDef_Win_ID);

  /** Get User defined Window */
  public int getAD_UserDef_Win_ID();

  /** Column name AD_UserDef_Win_UU */
  public static final String COLUMNNAME_AD_UserDef_Win_UU = "AD_UserDef_Win_UU";

  /** Set AD_UserDef_Win_UU */
  public void setAD_UserDef_Win_UU(String AD_UserDef_Win_UU);

  /** Get AD_UserDef_Win_UU */
  public String getAD_UserDef_Win_UU();

  /** Column name AD_User_ID */
  public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

  /** Set User/Contact. User within the system - Internal or Business Partner Contact */
  public void setAD_User_ID(int AD_User_ID);

  /** Get User/Contact. User within the system - Internal or Business Partner Contact */
  public int getAD_User_ID();

  public I_AD_User getAD_User() throws RuntimeException;

  /** Column name AD_Window_ID */
  public static final String COLUMNNAME_AD_Window_ID = "AD_Window_ID";

  /** Set Window. Data entry or display window */
  public void setAD_Window_ID(int AD_Window_ID);

  /** Get Window. Data entry or display window */
  public int getAD_Window_ID();

  public I_AD_Window getAD_Window() throws RuntimeException;

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

  /** Column name IsDefault */
  public static final String COLUMNNAME_IsDefault = "IsDefault";

  /** Set Default. Default value */
  public void setIsDefault(boolean IsDefault);

  /** Get Default. Default value */
  public boolean isDefault();

  /** Column name IsReadOnly */
  public static final String COLUMNNAME_IsReadOnly = "IsReadOnly";

  /** Set Read Only. Field is read only */
  public void setIsReadOnly(boolean IsReadOnly);

  /** Get Read Only. Field is read only */
  public boolean isReadOnly();

  /** Column name IsUserUpdateable */
  public static final String COLUMNNAME_IsUserUpdateable = "IsUserUpdateable";

  /** Set User updatable. The field can be updated by the user */
  public void setIsUserUpdateable(boolean IsUserUpdateable);

  /** Get User updatable. The field can be updated by the user */
  public boolean isUserUpdateable();

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
