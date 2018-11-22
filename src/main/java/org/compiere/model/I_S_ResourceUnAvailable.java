package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for S_ResourceUnAvailable
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_S_ResourceUnAvailable {

  /** TableName=S_ResourceUnAvailable */
  public static final String Table_Name = "S_ResourceUnAvailable";

  /** AD_Table_ID=482 */
  public static final int Table_ID = 482;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 3 - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(3);

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

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name S_Resource_ID */
  public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";

  /** Set Resource. Resource */
  public void setS_Resource_ID(int S_Resource_ID);

  /** Get Resource. Resource */
  public int getS_Resource_ID();

  public I_S_Resource getS_Resource() throws RuntimeException;

  /** Column name S_ResourceUnAvailable_ID */
  public static final String COLUMNNAME_S_ResourceUnAvailable_ID = "S_ResourceUnAvailable_ID";

  /** Set Resource Unavailability */
  public void setS_ResourceUnAvailable_ID(int S_ResourceUnAvailable_ID);

  /** Get Resource Unavailability */
  public int getS_ResourceUnAvailable_ID();

  /** Column name S_ResourceUnAvailable_UU */
  public static final String COLUMNNAME_S_ResourceUnAvailable_UU = "S_ResourceUnAvailable_UU";

  /** Set S_ResourceUnAvailable_UU */
  public void setS_ResourceUnAvailable_UU(String S_ResourceUnAvailable_UU);

  /** Get S_ResourceUnAvailable_UU */
  public String getS_ResourceUnAvailable_UU();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
