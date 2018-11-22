package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_WF_Responsible
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_WF_Responsible {

  /** TableName=AD_WF_Responsible */
  public static final String Table_Name = "AD_WF_Responsible";

  /** AD_Table_ID=646 */
  public static final int Table_ID = 646;

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

  /** Column name AD_User_ID */
  public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

  /** Set User/Contact. User within the system - Internal or Business Partner Contact */
  public void setAD_User_ID(int AD_User_ID);

  /** Get User/Contact. User within the system - Internal or Business Partner Contact */
  public int getAD_User_ID();

  public I_AD_User getAD_User() throws RuntimeException;

  /** Column name AD_WF_Responsible_ID */
  public static final String COLUMNNAME_AD_WF_Responsible_ID = "AD_WF_Responsible_ID";

  /** Set Workflow Responsible. Responsible for Workflow Execution */
  public void setAD_WF_Responsible_ID(int AD_WF_Responsible_ID);

  /** Get Workflow Responsible. Responsible for Workflow Execution */
  public int getAD_WF_Responsible_ID();

  /** Column name AD_WF_Responsible_UU */
  public static final String COLUMNNAME_AD_WF_Responsible_UU = "AD_WF_Responsible_UU";

  /** Set AD_WF_Responsible_UU */
  public void setAD_WF_Responsible_UU(String AD_WF_Responsible_UU);

  /** Get AD_WF_Responsible_UU */
  public String getAD_WF_Responsible_UU();

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

  /** Column name EntityType */
  public static final String COLUMNNAME_EntityType = "EntityType";

  /** Set Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public void setEntityType(String EntityType);

  /** Get Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public String getEntityType();

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

  /** Column name ResponsibleType */
  public static final String COLUMNNAME_ResponsibleType = "ResponsibleType";

  /** Set Responsible Type. Type of the Responsibility for a workflow */
  public void setResponsibleType(String ResponsibleType);

  /** Get Responsible Type. Type of the Responsibility for a workflow */
  public String getResponsibleType();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
