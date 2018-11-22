package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_Cycle
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_Cycle {

  /** TableName=C_Cycle */
  public static final String Table_Name = "C_Cycle";

  /** AD_Table_ID=432 */
  public static final int Table_ID = 432;

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

  /** Column name C_Currency_ID */
  public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

  /** Set Currency. The Currency for this record */
  public void setC_Currency_ID(int C_Currency_ID);

  /** Get Currency. The Currency for this record */
  public int getC_Currency_ID();

  public I_C_Currency getC_Currency() throws RuntimeException;

  /** Column name C_Cycle_ID */
  public static final String COLUMNNAME_C_Cycle_ID = "C_Cycle_ID";

  /** Set Project Cycle. Identifier for this Project Reporting Cycle */
  public void setC_Cycle_ID(int C_Cycle_ID);

  /** Get Project Cycle. Identifier for this Project Reporting Cycle */
  public int getC_Cycle_ID();

  /** Column name C_Cycle_UU */
  public static final String COLUMNNAME_C_Cycle_UU = "C_Cycle_UU";

  /** Set C_Cycle_UU */
  public void setC_Cycle_UU(String C_Cycle_UU);

  /** Get C_Cycle_UU */
  public String getC_Cycle_UU();

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

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
