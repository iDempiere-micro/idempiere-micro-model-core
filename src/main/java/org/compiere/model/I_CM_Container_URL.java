package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for CM_Container_URL
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_CM_Container_URL {

  /** TableName=CM_Container_URL */
  public static final String Table_Name = "CM_Container_URL";

  /** AD_Table_ID=865 */
  public static final int Table_ID = 865;

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

  /** Column name Checked */
  public static final String COLUMNNAME_Checked = "Checked";

  /** Set Last Checked. Info when we did the last check */
  public void setChecked(Timestamp Checked);

  /** Get Last Checked. Info when we did the last check */
  public Timestamp getChecked();

  /** Column name CM_Container_ID */
  public static final String COLUMNNAME_CM_Container_ID = "CM_Container_ID";

  /** Set Web Container. Web Container contains content like images, text etc. */
  public void setCM_Container_ID(int CM_Container_ID);

  /** Get Web Container. Web Container contains content like images, text etc. */
  public int getCM_Container_ID();

  public I_CM_Container getCM_Container() throws RuntimeException;

  /** Column name CM_Container_URL_ID */
  public static final String COLUMNNAME_CM_Container_URL_ID = "CM_Container_URL_ID";

  /** Set Container URL. Contains info on used URLs */
  public void setCM_Container_URL_ID(int CM_Container_URL_ID);

  /** Get Container URL. Contains info on used URLs */
  public int getCM_Container_URL_ID();

  /** Column name CM_Container_URL_UU */
  public static final String COLUMNNAME_CM_Container_URL_UU = "CM_Container_URL_UU";

  /** Set CM_Container_URL_UU */
  public void setCM_Container_URL_UU(String CM_Container_URL_UU);

  /** Get CM_Container_URL_UU */
  public String getCM_Container_URL_UU();

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

  /** Column name Last_Result */
  public static final String COLUMNNAME_Last_Result = "Last_Result";

  /** Set Last Result. Contains data on the last check result */
  public void setLast_Result(String Last_Result);

  /** Get Last Result. Contains data on the last check result */
  public String getLast_Result();

  /** Column name Status */
  public static final String COLUMNNAME_Status = "Status";

  /** Set Status. Status of the currently running check */
  public void setStatus(String Status);

  /** Get Status. Status of the currently running check */
  public String getStatus();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
