package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_TaskInstance
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_TaskInstance {

  /** TableName=AD_TaskInstance */
  public static final String Table_Name = "AD_TaskInstance";

  /** AD_Table_ID=125 */
  public static final int Table_ID = 125;

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

  /** Column name AD_Task_ID */
  public static final String COLUMNNAME_AD_Task_ID = "AD_Task_ID";

  /** Set OS Task. Operation System Task */
  public void setAD_Task_ID(int AD_Task_ID);

  /** Get OS Task. Operation System Task */
  public int getAD_Task_ID();

  /** Column name AD_TaskInstance_ID */
  public static final String COLUMNNAME_AD_TaskInstance_ID = "AD_TaskInstance_ID";

  /** Set Task Instance */
  public void setAD_TaskInstance_ID(int AD_TaskInstance_ID);

  /** Get Task Instance */
  public int getAD_TaskInstance_ID();

  /** Column name AD_TaskInstance_UU */
  public static final String COLUMNNAME_AD_TaskInstance_UU = "AD_TaskInstance_UU";

  /** Set AD_TaskInstance_UU */
  public void setAD_TaskInstance_UU(String AD_TaskInstance_UU);

  /** Get AD_TaskInstance_UU */
  public String getAD_TaskInstance_UU();

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
