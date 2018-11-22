package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Window_Access
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Window_Access {

  /** TableName=AD_Window_Access */
  public static final String Table_Name = "AD_Window_Access";

  /** AD_Table_ID=201 */
  public static final int Table_ID = 201;

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

  /** Column name AD_Window_Access_UU */
  public static final String COLUMNNAME_AD_Window_Access_UU = "AD_Window_Access_UU";

  /** Set AD_Window_Access_UU */
  public void setAD_Window_Access_UU(String AD_Window_Access_UU);

  /** Get AD_Window_Access_UU */
  public String getAD_Window_Access_UU();

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

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name IsReadWrite */
  public static final String COLUMNNAME_IsReadWrite = "IsReadWrite";

  /** Set Read Write. Field is read / write */
  public void setIsReadWrite(boolean IsReadWrite);

  /** Get Read Write. Field is read / write */
  public boolean isReadWrite();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
