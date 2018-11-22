package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Tree
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Tree {

  /** TableName=AD_Tree */
  public static final String Table_Name = "AD_Tree";

  /** AD_Table_ID=288 */
  public static final int Table_ID = 288;

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

  /** Column name AD_Table_ID */
  public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

  /** Set Table. Database Table information */
  public void setAD_Table_ID(int AD_Table_ID);

  /** Get Table. Database Table information */
  public int getAD_Table_ID();

  public I_AD_Table getAD_Table() throws RuntimeException;

  /** Column name AD_Tree_ID */
  public static final String COLUMNNAME_AD_Tree_ID = "AD_Tree_ID";

  /** Set Tree. Identifies a Tree */
  public void setAD_Tree_ID(int AD_Tree_ID);

  /** Get Tree. Identifies a Tree */
  public int getAD_Tree_ID();

  /** Column name AD_Tree_UU */
  public static final String COLUMNNAME_AD_Tree_UU = "AD_Tree_UU";

  /** Set AD_Tree_UU */
  public void setAD_Tree_UU(String AD_Tree_UU);

  /** Get AD_Tree_UU */
  public String getAD_Tree_UU();

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

  /** Column name IsAllNodes */
  public static final String COLUMNNAME_IsAllNodes = "IsAllNodes";

  /** Set All Nodes. All Nodes are included (Complete Tree) */
  public void setIsAllNodes(boolean IsAllNodes);

  /** Get All Nodes. All Nodes are included (Complete Tree) */
  public boolean isAllNodes();

  /** Column name IsDefault */
  public static final String COLUMNNAME_IsDefault = "IsDefault";

  /** Set Default. Default value */
  public void setIsDefault(boolean IsDefault);

  /** Get Default. Default value */
  public boolean isDefault();

  /** Column name IsLoadAllNodesImmediately */
  public static final String COLUMNNAME_IsLoadAllNodesImmediately = "IsLoadAllNodesImmediately";

  /** Set Loads directly all nodes. If checked, all nodes are loaded before tree is displayed */
  public void setIsLoadAllNodesImmediately(boolean IsLoadAllNodesImmediately);

  /** Get Loads directly all nodes. If checked, all nodes are loaded before tree is displayed */
  public boolean isLoadAllNodesImmediately();

  /** Column name IsTreeDrivenByValue */
  public static final String COLUMNNAME_IsTreeDrivenByValue = "IsTreeDrivenByValue";

  /** Set Driven by Search Key */
  public void setIsTreeDrivenByValue(boolean IsTreeDrivenByValue);

  /** Get Driven by Search Key */
  public boolean isTreeDrivenByValue();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name Processing */
  public static final String COLUMNNAME_Processing = "Processing";

  /** Set Process Now */
  public void setProcessing(boolean Processing);

  /** Get Process Now */
  public boolean isProcessing();

  /** Column name TreeType */
  public static final String COLUMNNAME_TreeType = "TreeType";

  /** Set Type | Area. Element this tree is built on (i.e Product, Business Partner) */
  public void setTreeType(String TreeType);

  /** Get Type | Area. Element this tree is built on (i.e Product, Business Partner) */
  public String getTreeType();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
