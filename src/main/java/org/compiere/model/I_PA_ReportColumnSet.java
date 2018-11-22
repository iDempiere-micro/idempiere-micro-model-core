package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for PA_ReportColumnSet
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_PA_ReportColumnSet {

  /** TableName=PA_ReportColumnSet */
  public static final String Table_Name = "PA_ReportColumnSet";

  /** AD_Table_ID=447 */
  public static final int Table_ID = 447;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 7 - System - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(7);

  /** Load Meta Data */

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

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name PA_ReportColumnSet_ID */
  public static final String COLUMNNAME_PA_ReportColumnSet_ID = "PA_ReportColumnSet_ID";

  /** Set Report Column Set. Collection of Columns for Report */
  public void setPA_ReportColumnSet_ID(int PA_ReportColumnSet_ID);

  /** Get Report Column Set. Collection of Columns for Report */
  public int getPA_ReportColumnSet_ID();

  /** Column name PA_ReportColumnSet_UU */
  public static final String COLUMNNAME_PA_ReportColumnSet_UU = "PA_ReportColumnSet_UU";

  /** Set PA_ReportColumnSet_UU */
  public void setPA_ReportColumnSet_UU(String PA_ReportColumnSet_UU);

  /** Get PA_ReportColumnSet_UU */
  public String getPA_ReportColumnSet_UU();

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
