package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_RfQ_Topic
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_RfQ_Topic {

  /** TableName=C_RfQ_Topic */
  public static final String Table_Name = "C_RfQ_Topic";

  /** AD_Table_ID=671 */
  public static final int Table_ID = 671;

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

  /** Column name AD_PrintFormat_ID */
  public static final String COLUMNNAME_AD_PrintFormat_ID = "AD_PrintFormat_ID";

  /** Set Print Format. Data Print Format */
  public void setAD_PrintFormat_ID(int AD_PrintFormat_ID);

  /** Get Print Format. Data Print Format */
  public int getAD_PrintFormat_ID();

  public I_AD_PrintFormat getAD_PrintFormat() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name C_RfQ_Topic_ID */
  public static final String COLUMNNAME_C_RfQ_Topic_ID = "C_RfQ_Topic_ID";

  /** Set RfQ Topic. Topic for Request for Quotations */
  public void setC_RfQ_Topic_ID(int C_RfQ_Topic_ID);

  /** Get RfQ Topic. Topic for Request for Quotations */
  public int getC_RfQ_Topic_ID();

  /** Column name C_RfQ_Topic_UU */
  public static final String COLUMNNAME_C_RfQ_Topic_UU = "C_RfQ_Topic_UU";

  /** Set C_RfQ_Topic_UU */
  public void setC_RfQ_Topic_UU(String C_RfQ_Topic_UU);

  /** Get C_RfQ_Topic_UU */
  public String getC_RfQ_Topic_UU();

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

  /** Column name IsSelfService */
  public static final String COLUMNNAME_IsSelfService = "IsSelfService";

  /**
   * Set Self-Service. This is a Self-Service entry or this entry can be changed via Self-Service
   */
  public void setIsSelfService(boolean IsSelfService);

  /**
   * Get Self-Service. This is a Self-Service entry or this entry can be changed via Self-Service
   */
  public boolean isSelfService();

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
