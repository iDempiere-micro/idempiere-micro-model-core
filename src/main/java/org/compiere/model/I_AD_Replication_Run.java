package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Replication_Run
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Replication_Run {

  /** TableName=AD_Replication_Run */
  public static final String Table_Name = "AD_Replication_Run";

  /** AD_Table_ID=603 */
  public static final int Table_ID = 603;

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

  /** Column name AD_Replication_ID */
  public static final String COLUMNNAME_AD_Replication_ID = "AD_Replication_ID";

  /** Set Replication. Data Replication Target */
  public void setAD_Replication_ID(int AD_Replication_ID);

  /** Get Replication. Data Replication Target */
  public int getAD_Replication_ID();

  public I_AD_Replication getAD_Replication() throws RuntimeException;

  /** Column name AD_Replication_Run_ID */
  public static final String COLUMNNAME_AD_Replication_Run_ID = "AD_Replication_Run_ID";

  /** Set Replication Run. Data Replication Run */
  public void setAD_Replication_Run_ID(int AD_Replication_Run_ID);

  /** Get Replication Run. Data Replication Run */
  public int getAD_Replication_Run_ID();

  /** Column name AD_Replication_Run_UU */
  public static final String COLUMNNAME_AD_Replication_Run_UU = "AD_Replication_Run_UU";

  /** Set AD_Replication_Run_UU */
  public void setAD_Replication_Run_UU(String AD_Replication_Run_UU);

  /** Get AD_Replication_Run_UU */
  public String getAD_Replication_Run_UU();

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

  /** Column name IsReplicated */
  public static final String COLUMNNAME_IsReplicated = "IsReplicated";

  /** Set Replicated. The data is successfully replicated */
  public void setIsReplicated(boolean IsReplicated);

  /** Get Replicated. The data is successfully replicated */
  public boolean isReplicated();

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
