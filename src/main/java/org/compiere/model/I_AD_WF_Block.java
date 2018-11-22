package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_WF_Block
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_WF_Block {

  /** TableName=AD_WF_Block */
  public static final String Table_Name = "AD_WF_Block";

  /** AD_Table_ID=647 */
  public static final int Table_ID = 647;

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

  /** Column name AD_WF_Block_ID */
  public static final String COLUMNNAME_AD_WF_Block_ID = "AD_WF_Block_ID";

  /** Set Workflow Block. Workflow Transaction Execution Block */
  public void setAD_WF_Block_ID(int AD_WF_Block_ID);

  /** Get Workflow Block. Workflow Transaction Execution Block */
  public int getAD_WF_Block_ID();

  /** Column name AD_WF_Block_UU */
  public static final String COLUMNNAME_AD_WF_Block_UU = "AD_WF_Block_UU";

  /** Set AD_WF_Block_UU */
  public void setAD_WF_Block_UU(String AD_WF_Block_UU);

  /** Get AD_WF_Block_UU */
  public String getAD_WF_Block_UU();

  /** Column name AD_Workflow_ID */
  public static final String COLUMNNAME_AD_Workflow_ID = "AD_Workflow_ID";

  /** Set Workflow. Workflow or combination of tasks */
  public void setAD_Workflow_ID(int AD_Workflow_ID);

  /** Get Workflow. Workflow or combination of tasks */
  public int getAD_Workflow_ID();

  public I_AD_Workflow getAD_Workflow() throws RuntimeException;

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

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
