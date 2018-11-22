package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Document_Action_Access
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Document_Action_Access {

  /** TableName=AD_Document_Action_Access */
  public static final String Table_Name = "AD_Document_Action_Access";

  /** AD_Table_ID=53012 */
  public static final int Table_ID = 53012;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Document_Action_Access_UU */
  public static final String COLUMNNAME_AD_Document_Action_Access_UU =
      "AD_Document_Action_Access_UU";

  /** Set AD_Document_Action_Access_UU */
  public void setAD_Document_Action_Access_UU(String AD_Document_Action_Access_UU);

  /** Get AD_Document_Action_Access_UU */
  public String getAD_Document_Action_Access_UU();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Ref_List_ID */
  public static final String COLUMNNAME_AD_Ref_List_ID = "AD_Ref_List_ID";

  /** Set Reference List. Reference List based on Table */
  public void setAD_Ref_List_ID(int AD_Ref_List_ID);

  /** Get Reference List. Reference List based on Table */
  public int getAD_Ref_List_ID();

  public I_AD_Ref_List getAD_Ref_List() throws RuntimeException;

  /** Column name AD_Role_ID */
  public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

  /** Set Role. Responsibility Role */
  public void setAD_Role_ID(int AD_Role_ID);

  /** Get Role. Responsibility Role */
  public int getAD_Role_ID();

  public I_AD_Role getAD_Role() throws RuntimeException;

  /** Column name C_DocType_ID */
  public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

  /** Set Document Type. Document type or rules */
  public void setC_DocType_ID(int C_DocType_ID);

  /** Get Document Type. Document type or rules */
  public int getC_DocType_ID();

  public I_C_DocType getC_DocType() throws RuntimeException;

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
