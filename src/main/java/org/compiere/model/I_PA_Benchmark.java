package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for PA_Benchmark
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_PA_Benchmark {

  /** TableName=PA_Benchmark */
  public static final String Table_Name = "PA_Benchmark";

  /** AD_Table_ID=833 */
  public static final int Table_ID = 833;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Column name AccumulationType */
  public static final String COLUMNNAME_AccumulationType = "AccumulationType";

  /** Set Accumulation Type. How to accumulate data on time axis */
  public void setAccumulationType(String AccumulationType);

  /** Get Accumulation Type. How to accumulate data on time axis */
  public String getAccumulationType();

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

  /** Column name PA_Benchmark_ID */
  public static final String COLUMNNAME_PA_Benchmark_ID = "PA_Benchmark_ID";

  /** Set Benchmark. Performance Benchmark */
  public void setPA_Benchmark_ID(int PA_Benchmark_ID);

  /** Get Benchmark. Performance Benchmark */
  public int getPA_Benchmark_ID();

  /** Column name PA_Benchmark_UU */
  public static final String COLUMNNAME_PA_Benchmark_UU = "PA_Benchmark_UU";

  /** Set PA_Benchmark_UU */
  public void setPA_Benchmark_UU(String PA_Benchmark_UU);

  /** Get PA_Benchmark_UU */
  public String getPA_Benchmark_UU();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
