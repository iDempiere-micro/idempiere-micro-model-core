package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_Demand
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_Demand {

  /** TableName=M_Demand */
  public static final String Table_Name = "M_Demand";

  /** AD_Table_ID=723 */
  public static final int Table_ID = 723;

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

  public I_C_Year getC_Year() throws RuntimeException;

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

  /** Column name IsDefault */
  public static final String COLUMNNAME_IsDefault = "IsDefault";

  /** Set Default. Default value */
  public void setIsDefault(boolean IsDefault);

  /** Get Default. Default value */
  public boolean isDefault();

  /** Column name M_Demand_ID */
  public static final String COLUMNNAME_M_Demand_ID = "M_Demand_ID";

  /** Set Demand. Material Demand */
  public void setM_Demand_ID(int M_Demand_ID);

  /** Get Demand. Material Demand */
  public int getM_Demand_ID();

  /** Column name M_Demand_UU */
  public static final String COLUMNNAME_M_Demand_UU = "M_Demand_UU";

  /** Set M_Demand_UU */
  public void setM_Demand_UU(String M_Demand_UU);

  /** Get M_Demand_UU */
  public String getM_Demand_UU();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

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
