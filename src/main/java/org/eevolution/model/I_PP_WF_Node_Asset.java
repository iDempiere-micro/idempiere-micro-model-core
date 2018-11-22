package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for PP_WF_Node_Asset
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_PP_WF_Node_Asset {

  /** TableName=PP_WF_Node_Asset */
  public static final String Table_Name = "PP_WF_Node_Asset";

  /** AD_Table_ID=53017 */
  public static final int Table_ID = 53017;

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

  /** Column name AD_WF_Node_ID */
  public static final String COLUMNNAME_AD_WF_Node_ID = "AD_WF_Node_ID";

  /** Set Node. Workflow Node (activity), step or process */
  public void setAD_WF_Node_ID(int AD_WF_Node_ID);

  /** Get Node. Workflow Node (activity), step or process */
  public int getAD_WF_Node_ID();

  public org.compiere.model.I_AD_WF_Node getAD_WF_Node() throws RuntimeException;

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

  /** Column name PP_WF_Node_Asset_ID */
  public static final String COLUMNNAME_PP_WF_Node_Asset_ID = "PP_WF_Node_Asset_ID";

  /** Set Workflow Node Asset */
  public void setPP_WF_Node_Asset_ID(int PP_WF_Node_Asset_ID);

  /** Get Workflow Node Asset */
  public int getPP_WF_Node_Asset_ID();

  /** Column name PP_WF_Node_Asset_UU */
  public static final String COLUMNNAME_PP_WF_Node_Asset_UU = "PP_WF_Node_Asset_UU";

  /** Set PP_WF_Node_Asset_UU */
  public void setPP_WF_Node_Asset_UU(String PP_WF_Node_Asset_UU);

  /** Get PP_WF_Node_Asset_UU */
  public String getPP_WF_Node_Asset_UU();

  /** Column name SeqNo */
  public static final String COLUMNNAME_SeqNo = "SeqNo";

  /** Set Sequence. Method of ordering records; lowest number comes first */
  public void setSeqNo(int SeqNo);

  /** Get Sequence. Method of ordering records; lowest number comes first */
  public int getSeqNo();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
