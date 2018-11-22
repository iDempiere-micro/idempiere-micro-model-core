package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for U_WebMenu
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_U_WebMenu {

  /** TableName=U_WebMenu */
  public static final String Table_Name = "U_WebMenu";

  /** AD_Table_ID=52003 */
  public static final int Table_ID = 52003;

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

  /** Column name Category */
  public static final String COLUMNNAME_Category = "Category";

  /** Set Category */
  public void setCategory(String Category);

  /** Get Category */
  public String getCategory();

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

  /** Column name HasSubMenu */
  public static final String COLUMNNAME_HasSubMenu = "HasSubMenu";

  /** Set Has SubMenu */
  public void setHasSubMenu(boolean HasSubMenu);

  /** Get Has SubMenu */
  public boolean isHasSubMenu();

  /** Column name Help */
  public static final String COLUMNNAME_Help = "Help";

  /** Set Comment/Help. Comment or Hint */
  public void setHelp(String Help);

  /** Get Comment/Help. Comment or Hint */
  public String getHelp();

  /** Column name ImageLink */
  public static final String COLUMNNAME_ImageLink = "ImageLink";

  /** Set Image Link */
  public void setImageLink(String ImageLink);

  /** Get Image Link */
  public String getImageLink();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name MenuLink */
  public static final String COLUMNNAME_MenuLink = "MenuLink";

  /** Set Menu Link */
  public void setMenuLink(String MenuLink);

  /** Get Menu Link */
  public String getMenuLink();

  /** Column name Module */
  public static final String COLUMNNAME_Module = "Module";

  /** Set Module */
  public void setModule(String Module);

  /** Get Module */
  public String getModule();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name ParentMenu_ID */
  public static final String COLUMNNAME_ParentMenu_ID = "ParentMenu_ID";

  /** Set Parent Menu */
  public void setParentMenu_ID(int ParentMenu_ID);

  /** Get Parent Menu */
  public int getParentMenu_ID();

  public I_U_WebMenu getParentMenu() throws RuntimeException;

  /** Column name Position */
  public static final String COLUMNNAME_Position = "Position";

  /** Set Position */
  public void setPosition(String Position);

  /** Get Position */
  public String getPosition();

  /** Column name Sequence */
  public static final String COLUMNNAME_Sequence = "Sequence";

  /** Set Sequence */
  public void setSequence(BigDecimal Sequence);

  /** Get Sequence */
  public BigDecimal getSequence();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name U_WebMenu_ID */
  public static final String COLUMNNAME_U_WebMenu_ID = "U_WebMenu_ID";

  /** Set Web Menu */
  public void setU_WebMenu_ID(int U_WebMenu_ID);

  /** Get Web Menu */
  public int getU_WebMenu_ID();

  /** Column name U_WebMenu_UU */
  public static final String COLUMNNAME_U_WebMenu_UU = "U_WebMenu_UU";

  /** Set U_WebMenu_UU */
  public void setU_WebMenu_UU(String U_WebMenu_UU);

  /** Get U_WebMenu_UU */
  public String getU_WebMenu_UU();
}
