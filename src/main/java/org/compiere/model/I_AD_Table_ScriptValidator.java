package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Table_ScriptValidator
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Table_ScriptValidator {

  /** TableName=AD_Table_ScriptValidator */
  public static final String Table_Name = "AD_Table_ScriptValidator";

  /** AD_Table_ID=53059 */
  public static final int Table_ID = 53059;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Rule_ID */
  public static final String COLUMNNAME_AD_Rule_ID = "AD_Rule_ID";

  /** Set Rule */
  public void setAD_Rule_ID(int AD_Rule_ID);

  /** Get Rule */
  public int getAD_Rule_ID();

  public I_AD_Rule getAD_Rule() throws RuntimeException;

  /** Column name AD_Table_ID */
  public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

  /** Set Table. Database Table information */
  public void setAD_Table_ID(int AD_Table_ID);

  /** Get Table. Database Table information */
  public int getAD_Table_ID();

  public I_AD_Table getAD_Table() throws RuntimeException;

  /** Column name AD_Table_ScriptValidator_ID */
  public static final String COLUMNNAME_AD_Table_ScriptValidator_ID = "AD_Table_ScriptValidator_ID";

  /** Set Table Script Validator */
  public void setAD_Table_ScriptValidator_ID(int AD_Table_ScriptValidator_ID);

  /** Get Table Script Validator */
  public int getAD_Table_ScriptValidator_ID();

  /** Column name AD_Table_ScriptValidator_UU */
  public static final String COLUMNNAME_AD_Table_ScriptValidator_UU = "AD_Table_ScriptValidator_UU";

  /** Set AD_Table_ScriptValidator_UU */
  public void setAD_Table_ScriptValidator_UU(String AD_Table_ScriptValidator_UU);

  /** Get AD_Table_ScriptValidator_UU */
  public String getAD_Table_ScriptValidator_UU();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name EventModelValidator */
  public static final String COLUMNNAME_EventModelValidator = "EventModelValidator";

  /** Set Event Model Validator */
  public void setEventModelValidator(String EventModelValidator);

  /** Get Event Model Validator */
  public String getEventModelValidator();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name SeqNo */
  public static final String COLUMNNAME_SeqNo = "SeqNo";

  /** Set Sequence. Method of ordering records; lowest number comes first */
  public void setSeqNo(int SeqNo);

  /** Get Sequence. Method of ordering records; lowest number comes first */
  public int getSeqNo();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
