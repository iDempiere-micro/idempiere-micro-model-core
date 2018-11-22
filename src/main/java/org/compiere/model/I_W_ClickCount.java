package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for W_ClickCount
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_W_ClickCount {

  /** TableName=W_ClickCount */
  public static final String Table_Name = "W_ClickCount";

  /** AD_Table_ID=553 */
  public static final int Table_ID = 553;

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

  /** Column name C_BPartner_ID */
  public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

  /** Set Business Partner . Identifies a Business Partner */
  public void setC_BPartner_ID(int C_BPartner_ID);

  /** Get Business Partner . Identifies a Business Partner */
  public int getC_BPartner_ID();

  public I_C_BPartner getC_BPartner() throws RuntimeException;

  /** Column name Counter */
  public static final String COLUMNNAME_Counter = "Counter";

  /** Set Counter. Count Value */
  public void setCounter(int Counter);

  /** Get Counter. Count Value */
  public int getCounter();

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

  /** Column name TargetURL */
  public static final String COLUMNNAME_TargetURL = "TargetURL";

  /** Set Target URL. URL for the Target */
  public void setTargetURL(String TargetURL);

  /** Get Target URL. URL for the Target */
  public String getTargetURL();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name W_ClickCount_ID */
  public static final String COLUMNNAME_W_ClickCount_ID = "W_ClickCount_ID";

  /** Set Click Count. Web Click Management */
  public void setW_ClickCount_ID(int W_ClickCount_ID);

  /** Get Click Count. Web Click Management */
  public int getW_ClickCount_ID();

  /** Column name W_ClickCount_UU */
  public static final String COLUMNNAME_W_ClickCount_UU = "W_ClickCount_UU";

  /** Set W_ClickCount_UU */
  public void setW_ClickCount_UU(String W_ClickCount_UU);

  /** Get W_ClickCount_UU */
  public String getW_ClickCount_UU();
}
