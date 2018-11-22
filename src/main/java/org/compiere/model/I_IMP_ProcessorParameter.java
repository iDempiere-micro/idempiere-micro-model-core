package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for IMP_ProcessorParameter
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_IMP_ProcessorParameter {

  /** TableName=IMP_ProcessorParameter */
  public static final String Table_Name = "IMP_ProcessorParameter";

  /** AD_Table_ID=53078 */
  public static final int Table_ID = 53078;

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

  /** Column name Help */
  public static final String COLUMNNAME_Help = "Help";

  /** Set Comment/Help. Comment or Hint */
  public void setHelp(String Help);

  /** Get Comment/Help. Comment or Hint */
  public String getHelp();

  /** Column name IMP_Processor_ID */
  public static final String COLUMNNAME_IMP_Processor_ID = "IMP_Processor_ID";

  /** Set Import Processor */
  public void setIMP_Processor_ID(int IMP_Processor_ID);

  /** Get Import Processor */
  public int getIMP_Processor_ID();

  public I_IMP_Processor getIMP_Processor() throws RuntimeException;

  /** Column name IMP_ProcessorParameter_ID */
  public static final String COLUMNNAME_IMP_ProcessorParameter_ID = "IMP_ProcessorParameter_ID";

  /** Set Import Processor Parameter */
  public void setIMP_ProcessorParameter_ID(int IMP_ProcessorParameter_ID);

  /** Get Import Processor Parameter */
  public int getIMP_ProcessorParameter_ID();

  /** Column name IMP_ProcessorParameter_UU */
  public static final String COLUMNNAME_IMP_ProcessorParameter_UU = "IMP_ProcessorParameter_UU";

  /** Set IMP_ProcessorParameter_UU */
  public void setIMP_ProcessorParameter_UU(String IMP_ProcessorParameter_UU);

  /** Get IMP_ProcessorParameter_UU */
  public String getIMP_ProcessorParameter_UU();

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
