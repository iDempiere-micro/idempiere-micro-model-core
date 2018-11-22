package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for CM_AccessListRole
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_CM_AccessListRole {

  /** TableName=CM_AccessListRole */
  public static final String Table_Name = "CM_AccessListRole";

  /** AD_Table_ID=887 */
  public static final int Table_ID = 887;

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

  /** Column name AD_Role_ID */
  public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

  /** Set Role. Responsibility Role */
  public void setAD_Role_ID(int AD_Role_ID);

  /** Get Role. Responsibility Role */
  public int getAD_Role_ID();

  public I_AD_Role getAD_Role() throws RuntimeException;

  /** Column name CM_AccessListRole_UU */
  public static final String COLUMNNAME_CM_AccessListRole_UU = "CM_AccessListRole_UU";

  /** Set CM_AccessListRole_UU */
  public void setCM_AccessListRole_UU(String CM_AccessListRole_UU);

  /** Get CM_AccessListRole_UU */
  public String getCM_AccessListRole_UU();

  /** Column name CM_AccessProfile_ID */
  public static final String COLUMNNAME_CM_AccessProfile_ID = "CM_AccessProfile_ID";

  /** Set Web Access Profile. Web Access Profile */
  public void setCM_AccessProfile_ID(int CM_AccessProfile_ID);

  /** Get Web Access Profile. Web Access Profile */
  public int getCM_AccessProfile_ID();

  public I_CM_AccessProfile getCM_AccessProfile() throws RuntimeException;

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
}
