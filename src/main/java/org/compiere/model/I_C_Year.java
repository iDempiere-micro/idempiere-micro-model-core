package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_Year
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_Year {

  /** TableName=C_Year */
  public static final String Table_Name = "C_Year";

  /** AD_Table_ID=177 */
  public static final int Table_ID = 177;

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

  /** Column name C_Calendar_ID */
  public static final String COLUMNNAME_C_Calendar_ID = "C_Calendar_ID";

  /** Set Calendar. Accounting Calendar Name */
  public void setC_Calendar_ID(int C_Calendar_ID);

  /** Get Calendar. Accounting Calendar Name */
  public int getC_Calendar_ID();

  public I_C_Calendar getC_Calendar() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name C_Year_ID */
  public static final String COLUMNNAME_C_Year_ID = "C_Year_ID";

  /** Set Year. Calendar Year */
  public void setC_Year_ID(int C_Year_ID);

  /** Get Year. Calendar Year */
  public int getC_Year_ID();

  /** Column name C_Year_UU */
  public static final String COLUMNNAME_C_Year_UU = "C_Year_UU";

  /** Set C_Year_UU */
  public void setC_Year_UU(String C_Year_UU);

  /** Get C_Year_UU */
  public String getC_Year_UU();

  /** Column name Description */
  public static final String COLUMNNAME_Description = "Description";

  /** Set Description. Optional short description of the record */
  public void setDescription(String Description);

  /** Get Description. Optional short description of the record */
  public String getDescription();

  /** Column name FiscalYear */
  public static final String COLUMNNAME_FiscalYear = "FiscalYear";

  /** Set Year. The Fiscal Year */
  public void setFiscalYear(String FiscalYear);

  /** Get Year. The Fiscal Year */
  public String getFiscalYear();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name Processing */
  public static final String COLUMNNAME_Processing = "Processing";

  /** Set Process Now */
  public void setProcessing(boolean Processing);

  /** Get Process Now */
  public boolean isProcessing();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
