package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for EXP_ProcessorParameter
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_EXP_ProcessorParameter {

  /** TableName=EXP_ProcessorParameter */
  public static final String Table_Name = "EXP_ProcessorParameter";

  /** AD_Table_ID=53075 */
  public static final int Table_ID = 53075;

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

  /** Column name EXP_Processor_ID */
  public static final String COLUMNNAME_EXP_Processor_ID = "EXP_Processor_ID";

  /** Set Export Processor */
  public void setEXP_Processor_ID(int EXP_Processor_ID);

  /** Get Export Processor */
  public int getEXP_Processor_ID();

  public I_EXP_Processor getEXP_Processor() throws RuntimeException;

  /** Column name EXP_ProcessorParameter_ID */
  public static final String COLUMNNAME_EXP_ProcessorParameter_ID = "EXP_ProcessorParameter_ID";

  /** Set Processor Parameter */
  public void setEXP_ProcessorParameter_ID(int EXP_ProcessorParameter_ID);

  /** Get Processor Parameter */
  public int getEXP_ProcessorParameter_ID();

  /** Column name EXP_ProcessorParameter_UU */
  public static final String COLUMNNAME_EXP_ProcessorParameter_UU = "EXP_ProcessorParameter_UU";

  /** Set EXP_ProcessorParameter_UU */
  public void setEXP_ProcessorParameter_UU(String EXP_ProcessorParameter_UU);

  /** Get EXP_ProcessorParameter_UU */
  public String getEXP_ProcessorParameter_UU();

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

  /** Column name ParameterValue */
  public static final String COLUMNNAME_ParameterValue = "ParameterValue";

  /** Set Parameter Value */
  public void setParameterValue(String ParameterValue);

  /** Get Parameter Value */
  public String getParameterValue();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name Value */
  public static final String COLUMNNAME_Value = "Value";

  /** Set Search Key. Search key for the record in the format required - must be unique */
  public void setValue(String Value);

  /** Get Search Key. Search key for the record in the format required - must be unique */
  public String getValue();
}
