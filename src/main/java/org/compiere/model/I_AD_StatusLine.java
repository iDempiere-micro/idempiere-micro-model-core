package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_StatusLine
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_StatusLine {

  /** TableName=AD_StatusLine */
  public static final String Table_Name = "AD_StatusLine";

  /** AD_Table_ID=200107 */
  public static final int Table_ID = 200107;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Message_ID */
  public static final String COLUMNNAME_AD_Message_ID = "AD_Message_ID";

  /** Set Message. System Message */
  public void setAD_Message_ID(int AD_Message_ID);

  /** Get Message. System Message */
  public int getAD_Message_ID();

  public I_AD_Message getAD_Message() throws RuntimeException;

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_StatusLine_ID */
  public static final String COLUMNNAME_AD_StatusLine_ID = "AD_StatusLine_ID";

  /** Set Status Line */
  public void setAD_StatusLine_ID(int AD_StatusLine_ID);

  /** Get Status Line */
  public int getAD_StatusLine_ID();

  /** Column name AD_StatusLine_UU */
  public static final String COLUMNNAME_AD_StatusLine_UU = "AD_StatusLine_UU";

  /** Set AD_StatusLine_UU */
  public void setAD_StatusLine_UU(String AD_StatusLine_UU);

  /** Get AD_StatusLine_UU */
  public String getAD_StatusLine_UU();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name EntityType */
  public static final String COLUMNNAME_EntityType = "EntityType";

  /** Set Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public void setEntityType(String EntityType);

  /** Get Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public String getEntityType();

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

  /** Column name SQLStatement */
  public static final String COLUMNNAME_SQLStatement = "SQLStatement";

  /** Set SQLStatement */
  public void setSQLStatement(String SQLStatement);

  /** Get SQLStatement */
  public String getSQLStatement();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
