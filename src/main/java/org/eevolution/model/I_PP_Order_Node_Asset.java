package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for PP_Order_Node_Asset
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_PP_Order_Node_Asset {

  /** TableName=PP_Order_Node_Asset */
  public static final String Table_Name = "PP_Order_Node_Asset";

  /** AD_Table_ID=53031 */
  public static final int Table_ID = 53031;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 3 - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(3);

  /** Load Meta Data */

  /** Column name A_Asset_ID */
  public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

  /** Set Asset. Asset used internally or by customers */
  public void setA_Asset_ID(int A_Asset_ID);

  /** Get Asset. Asset used internally or by customers */
  public int getA_Asset_ID();

  public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException;

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

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

  /** Column name PP_Order_ID */
  public static final String COLUMNNAME_PP_Order_ID = "PP_Order_ID";

  /** Set Manufacturing Order. Manufacturing Order */
  public void setPP_Order_ID(int PP_Order_ID);

  /** Get Manufacturing Order. Manufacturing Order */
  public int getPP_Order_ID();

  public org.eevolution.model.I_PP_Order getPP_Order() throws RuntimeException;

  /** Column name PP_Order_Node_Asset_ID */
  public static final String COLUMNNAME_PP_Order_Node_Asset_ID = "PP_Order_Node_Asset_ID";

  /** Set Manufacturing Order Activity Asset */
  public void setPP_Order_Node_Asset_ID(int PP_Order_Node_Asset_ID);

  /** Get Manufacturing Order Activity Asset */
  public int getPP_Order_Node_Asset_ID();

  /** Column name PP_Order_Node_Asset_UU */
  public static final String COLUMNNAME_PP_Order_Node_Asset_UU = "PP_Order_Node_Asset_UU";

  /** Set PP_Order_Node_Asset_UU */
  public void setPP_Order_Node_Asset_UU(String PP_Order_Node_Asset_UU);

  /** Get PP_Order_Node_Asset_UU */
  public String getPP_Order_Node_Asset_UU();

  /** Column name PP_Order_Node_ID */
  public static final String COLUMNNAME_PP_Order_Node_ID = "PP_Order_Node_ID";

  /** Set Manufacturing Order Activity. Workflow Node (activity), step or process */
  public void setPP_Order_Node_ID(int PP_Order_Node_ID);

  /** Get Manufacturing Order Activity. Workflow Node (activity), step or process */
  public int getPP_Order_Node_ID();

  public org.eevolution.model.I_PP_Order_Node getPP_Order_Node() throws RuntimeException;

  /** Column name PP_Order_Workflow_ID */
  public static final String COLUMNNAME_PP_Order_Workflow_ID = "PP_Order_Workflow_ID";

  /** Set Manufacturing Order Workflow */
  public void setPP_Order_Workflow_ID(int PP_Order_Workflow_ID);

  /** Get Manufacturing Order Workflow */
  public int getPP_Order_Workflow_ID();

  public org.eevolution.model.I_PP_Order_Workflow getPP_Order_Workflow() throws RuntimeException;

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
