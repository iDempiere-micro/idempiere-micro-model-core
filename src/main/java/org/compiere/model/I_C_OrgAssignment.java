package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_OrgAssignment
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_OrgAssignment {

  /** TableName=C_OrgAssignment */
  public static final String Table_Name = "C_OrgAssignment";

  /** AD_Table_ID=585 */
  public static final int Table_ID = 585;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 2 - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(2);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_User_ID */
  public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

  /** Set User/Contact. User within the system - Internal or Business Partner Contact */
  public void setAD_User_ID(int AD_User_ID);

  /** Get User/Contact. User within the system - Internal or Business Partner Contact */
  public int getAD_User_ID();

  public I_AD_User getAD_User() throws RuntimeException;

  /** Column name C_OrgAssignment_ID */
  public static final String COLUMNNAME_C_OrgAssignment_ID = "C_OrgAssignment_ID";

  /** Set Org Assignment. Assignment to (transaction) Organization */
  public void setC_OrgAssignment_ID(int C_OrgAssignment_ID);

  /** Get Org Assignment. Assignment to (transaction) Organization */
  public int getC_OrgAssignment_ID();

  /** Column name C_OrgAssignment_UU */
  public static final String COLUMNNAME_C_OrgAssignment_UU = "C_OrgAssignment_UU";

  /** Set C_OrgAssignment_UU */
  public void setC_OrgAssignment_UU(String C_OrgAssignment_UU);

  /** Get C_OrgAssignment_UU */
  public String getC_OrgAssignment_UU();

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

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name ValidFrom */
  public static final String COLUMNNAME_ValidFrom = "ValidFrom";

  /** Set Valid from. Valid from including this date (first day) */
  public void setValidFrom(Timestamp ValidFrom);

  /** Get Valid from. Valid from including this date (first day) */
  public Timestamp getValidFrom();

  /** Column name ValidTo */
  public static final String COLUMNNAME_ValidTo = "ValidTo";

  /** Set Valid to. Valid to including this date (last day) */
  public void setValidTo(Timestamp ValidTo);

  /** Get Valid to. Valid to including this date (last day) */
  public Timestamp getValidTo();
}
