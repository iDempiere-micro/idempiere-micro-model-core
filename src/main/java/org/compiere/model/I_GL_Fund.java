package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for GL_Fund
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_GL_Fund {

  /** TableName=GL_Fund */
  public static final String Table_Name = "GL_Fund";

  /** AD_Table_ID=823 */
  public static final int Table_ID = 823;

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

  /** Column name Amt */
  public static final String COLUMNNAME_Amt = "Amt";

  /** Set Amount. Amount */
  public void setAmt(BigDecimal Amt);

  /** Get Amount. Amount */
  public BigDecimal getAmt();

  /** Column name C_AcctSchema_ID */
  public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

  /** Set Accounting Schema. Rules for accounting */
  public void setC_AcctSchema_ID(int C_AcctSchema_ID);

  /** Get Accounting Schema. Rules for accounting */
  public int getC_AcctSchema_ID();

  public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name DateFrom */
  public static final String COLUMNNAME_DateFrom = "DateFrom";

  /** Set Date From. Starting date for a range */
  public void setDateFrom(Timestamp DateFrom);

  /** Get Date From. Starting date for a range */
  public Timestamp getDateFrom();

  /** Column name DateTo */
  public static final String COLUMNNAME_DateTo = "DateTo";

  /** Set Date To. End date of a date range */
  public void setDateTo(Timestamp DateTo);

  /** Get Date To. End date of a date range */
  public Timestamp getDateTo();

  /** Column name Description */
  public static final String COLUMNNAME_Description = "Description";

  /** Set Description. Optional short description of the record */
  public void setDescription(String Description);

  /** Get Description. Optional short description of the record */
  public String getDescription();

  /** Column name GL_Fund_ID */
  public static final String COLUMNNAME_GL_Fund_ID = "GL_Fund_ID";

  /** Set GL Fund. General Ledger Funds Control */
  public void setGL_Fund_ID(int GL_Fund_ID);

  /** Get GL Fund. General Ledger Funds Control */
  public int getGL_Fund_ID();

  /** Column name GL_Fund_UU */
  public static final String COLUMNNAME_GL_Fund_UU = "GL_Fund_UU";

  /** Set GL_Fund_UU */
  public void setGL_Fund_UU(String GL_Fund_UU);

  /** Get GL_Fund_UU */
  public String getGL_Fund_UU();

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
