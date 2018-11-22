package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for U_RoleMenu
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_U_RoleMenu {

  /** TableName=U_RoleMenu */
  public static final String Table_Name = "U_RoleMenu";

  /** AD_Table_ID=52002 */
  public static final int Table_ID = 52002;

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

  /** Column name AD_Role_ID */
  public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

  /** Set Role. Responsibility Role */
  public void setAD_Role_ID(int AD_Role_ID);

  /** Get Role. Responsibility Role */
  public int getAD_Role_ID();

  public I_AD_Role getAD_Role() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name U_RoleMenu_ID */
  public static final String COLUMNNAME_U_RoleMenu_ID = "U_RoleMenu_ID";

  /** Set Role Menu */
  public void setU_RoleMenu_ID(int U_RoleMenu_ID);

  /** Get Role Menu */
  public int getU_RoleMenu_ID();

  /** Column name U_RoleMenu_UU */
  public static final String COLUMNNAME_U_RoleMenu_UU = "U_RoleMenu_UU";

  /** Set U_RoleMenu_UU */
  public void setU_RoleMenu_UU(String U_RoleMenu_UU);

  /** Get U_RoleMenu_UU */
  public String getU_RoleMenu_UU();

  /** Column name U_WebMenu_ID */
  public static final String COLUMNNAME_U_WebMenu_ID = "U_WebMenu_ID";

  /** Set Web Menu */
  public void setU_WebMenu_ID(int U_WebMenu_ID);

  /** Get Web Menu */
  public int getU_WebMenu_ID();

  public I_U_WebMenu getU_WebMenu() throws RuntimeException;
}
