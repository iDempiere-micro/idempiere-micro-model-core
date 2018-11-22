package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_WF_ActivityResult
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_WF_ActivityResult {

  /** TableName=AD_WF_ActivityResult */
  public static final String Table_Name = "AD_WF_ActivityResult";

  /** AD_Table_ID=650 */
  public static final int Table_ID = 650;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 7 - System - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(7);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_WF_Activity_ID */
  public static final String COLUMNNAME_AD_WF_Activity_ID = "AD_WF_Activity_ID";

  /** Set Workflow Activity. Workflow Activity */
  public void setAD_WF_Activity_ID(int AD_WF_Activity_ID);

  /** Get Workflow Activity. Workflow Activity */
  public int getAD_WF_Activity_ID();

  public I_AD_WF_Activity getAD_WF_Activity() throws RuntimeException;

  /** Column name AD_WF_ActivityResult_ID */
  public static final String COLUMNNAME_AD_WF_ActivityResult_ID = "AD_WF_ActivityResult_ID";

  /** Set Workflow Activity Result. Result of the Workflow Process Activity */
  public void setAD_WF_ActivityResult_ID(int AD_WF_ActivityResult_ID);

  /** Get Workflow Activity Result. Result of the Workflow Process Activity */
  public int getAD_WF_ActivityResult_ID();

  /** Column name AD_WF_ActivityResult_UU */
  public static final String COLUMNNAME_AD_WF_ActivityResult_UU = "AD_WF_ActivityResult_UU";

  /** Set AD_WF_ActivityResult_UU */
  public void setAD_WF_ActivityResult_UU(String AD_WF_ActivityResult_UU);

  /** Get AD_WF_ActivityResult_UU */
  public String getAD_WF_ActivityResult_UU();

  /** Column name AttributeName */
  public static final String COLUMNNAME_AttributeName = "AttributeName";

  /** Set Attribute Name. Name of the Attribute */
  public void setAttributeName(String AttributeName);

  /** Get Attribute Name. Name of the Attribute */
  public String getAttributeName();

  /** Column name AttributeValue */
  public static final String COLUMNNAME_AttributeValue = "AttributeValue";

  /** Set Attribute Value. Value of the Attribute */
  public void setAttributeValue(String AttributeValue);

  /** Get Attribute Value. Value of the Attribute */
  public String getAttributeValue();

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

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
