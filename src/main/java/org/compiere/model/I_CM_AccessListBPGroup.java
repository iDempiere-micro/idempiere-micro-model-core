package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for CM_AccessListBPGroup
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_CM_AccessListBPGroup {

  /** TableName=CM_AccessListBPGroup */
  public static final String Table_Name = "CM_AccessListBPGroup";

  /** AD_Table_ID=886 */
  public static final int Table_ID = 886;

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

  /** Column name C_BP_Group_ID */
  public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

  /** Set Business Partner Group. Business Partner Group */
  public void setC_BP_Group_ID(int C_BP_Group_ID);

  /** Get Business Partner Group. Business Partner Group */
  public int getC_BP_Group_ID();

  public I_C_BP_Group getC_BP_Group() throws RuntimeException;

  /** Column name CM_AccessListBPGroup_UU */
  public static final String COLUMNNAME_CM_AccessListBPGroup_UU = "CM_AccessListBPGroup_UU";

  /** Set CM_AccessListBPGroup_UU */
  public void setCM_AccessListBPGroup_UU(String CM_AccessListBPGroup_UU);

  /** Get CM_AccessListBPGroup_UU */
  public String getCM_AccessListBPGroup_UU();

  /** Column name CM_AccessProfile_ID */
  public static final String COLUMNNAME_CM_AccessProfile_ID = "CM_AccessProfile_ID";

  /** Set Web Access Profile. Web Access Profile */
  public void setCM_AccessProfile_ID(int CM_AccessProfile_ID);

  /** Get Web Access Profile. Web Access Profile */
  public int getCM_AccessProfile_ID();

  public I_CM_AccessProfile getCM_AccessProfile() throws RuntimeException;

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
