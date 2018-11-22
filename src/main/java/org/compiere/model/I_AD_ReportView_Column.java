package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_ReportView_Column
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_ReportView_Column {

  /** TableName=AD_ReportView_Column */
  public static final String Table_Name = "AD_ReportView_Column";

  /** AD_Table_ID=200109 */
  public static final int Table_ID = 200109;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Column_ID */
  public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

  /** Set Column. Column in the table */
  public void setAD_Column_ID(int AD_Column_ID);

  /** Get Column. Column in the table */
  public int getAD_Column_ID();

  public I_AD_Column getAD_Column() throws RuntimeException;

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_ReportView_Column_UU */
  public static final String COLUMNNAME_AD_ReportView_Column_UU = "AD_ReportView_Column_UU";

  /** Set AD_ReportView_Column_UU */
  public void setAD_ReportView_Column_UU(String AD_ReportView_Column_UU);

  /** Get AD_ReportView_Column_UU */
  public String getAD_ReportView_Column_UU();

  /** Column name AD_ReportView_ID */
  public static final String COLUMNNAME_AD_ReportView_ID = "AD_ReportView_ID";

  /** Set Report View. View used to generate this report */
  public void setAD_ReportView_ID(int AD_ReportView_ID);

  /** Get Report View. View used to generate this report */
  public int getAD_ReportView_ID();

  public I_AD_ReportView getAD_ReportView() throws RuntimeException;

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
