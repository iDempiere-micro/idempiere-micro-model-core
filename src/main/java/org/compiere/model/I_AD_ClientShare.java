package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_ClientShare
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_ClientShare {

  /** TableName=AD_ClientShare */
  public static final String Table_Name = "AD_ClientShare";

  /** AD_Table_ID=827 */
  public static final int Table_ID = 827;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 2 - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(2);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_ClientShare_ID */
  public static final String COLUMNNAME_AD_ClientShare_ID = "AD_ClientShare_ID";

  /** Set Client Share. Force (not) sharing of client/org entities */
  public void setAD_ClientShare_ID(int AD_ClientShare_ID);

  /** Get Client Share. Force (not) sharing of client/org entities */
  public int getAD_ClientShare_ID();

  /** Column name AD_ClientShare_UU */
  public static final String COLUMNNAME_AD_ClientShare_UU = "AD_ClientShare_UU";

  /** Set AD_ClientShare_UU */
  public void setAD_ClientShare_UU(String AD_ClientShare_UU);

  /** Get AD_ClientShare_UU */
  public String getAD_ClientShare_UU();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Table_ID */
  public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

  /** Set Table. Database Table information */
  public void setAD_Table_ID(int AD_Table_ID);

  /** Get Table. Database Table information */
  public int getAD_Table_ID();

  public I_AD_Table getAD_Table() throws RuntimeException;

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

  /** Column name ShareType */
  public static final String COLUMNNAME_ShareType = "ShareType";

  /** Set Share Type. Type of sharing */
  public void setShareType(String ShareType);

  /** Get Share Type. Type of sharing */
  public String getShareType();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
