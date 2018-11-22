package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for ASP_Ref_List
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_ASP_Ref_List {

  /** TableName=ASP_Ref_List */
  public static final String Table_Name = "ASP_Ref_List";

  /** AD_Table_ID=200011 */
  public static final int Table_ID = 200011;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Reference_ID */
  public static final String COLUMNNAME_AD_Reference_ID = "AD_Reference_ID";

  /** Set Reference. System Reference and Validation */
  public void setAD_Reference_ID(int AD_Reference_ID);

  /** Get Reference. System Reference and Validation */
  public int getAD_Reference_ID();

  public I_AD_Reference getAD_Reference() throws RuntimeException;

  /** Column name AD_Ref_List_ID */
  public static final String COLUMNNAME_AD_Ref_List_ID = "AD_Ref_List_ID";

  /** Set Reference List. Reference List based on Table */
  public void setAD_Ref_List_ID(int AD_Ref_List_ID);

  /** Get Reference List. Reference List based on Table */
  public int getAD_Ref_List_ID();

  public I_AD_Ref_List getAD_Ref_List() throws RuntimeException;

  /** Column name ASP_Level_ID */
  public static final String COLUMNNAME_ASP_Level_ID = "ASP_Level_ID";

  /** Set ASP Level */
  public void setASP_Level_ID(int ASP_Level_ID);

  /** Get ASP Level */
  public int getASP_Level_ID();

  public I_ASP_Level getASP_Level() throws RuntimeException;

  /** Column name ASP_Ref_List_ID */
  public static final String COLUMNNAME_ASP_Ref_List_ID = "ASP_Ref_List_ID";

  /** Set ASP_Ref_List */
  public void setASP_Ref_List_ID(int ASP_Ref_List_ID);

  /** Get ASP_Ref_List */
  public int getASP_Ref_List_ID();

  /** Column name ASP_Ref_List_UU */
  public static final String COLUMNNAME_ASP_Ref_List_UU = "ASP_Ref_List_UU";

  /** Set ASP_Ref_List_UU */
  public void setASP_Ref_List_UU(String ASP_Ref_List_UU);

  /** Get ASP_Ref_List_UU */
  public String getASP_Ref_List_UU();

  /** Column name ASP_Status */
  public static final String COLUMNNAME_ASP_Status = "ASP_Status";

  /** Set ASP Status */
  public void setASP_Status(String ASP_Status);

  /** Get ASP Status */
  public String getASP_Status();

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
