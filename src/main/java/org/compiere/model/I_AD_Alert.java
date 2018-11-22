package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Alert
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Alert {

  /** TableName=AD_Alert */
  public static final String Table_Name = "AD_Alert";

  /** AD_Table_ID=594 */
  public static final int Table_ID = 594;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Column name AD_Alert_ID */
  public static final String COLUMNNAME_AD_Alert_ID = "AD_Alert_ID";

  /** Set Alert. iDempiere Alert */
  public void setAD_Alert_ID(int AD_Alert_ID);

  /** Get Alert. iDempiere Alert */
  public int getAD_Alert_ID();

  /** Column name AD_AlertProcessor_ID */
  public static final String COLUMNNAME_AD_AlertProcessor_ID = "AD_AlertProcessor_ID";

  /** Set Alert Processor. Alert Processor/Server Parameter */
  public void setAD_AlertProcessor_ID(int AD_AlertProcessor_ID);

  /** Get Alert Processor. Alert Processor/Server Parameter */
  public int getAD_AlertProcessor_ID();

  public I_AD_AlertProcessor getAD_AlertProcessor() throws RuntimeException;

  /** Column name AD_Alert_UU */
  public static final String COLUMNNAME_AD_Alert_UU = "AD_Alert_UU";

  /** Set AD_Alert_UU */
  public void setAD_Alert_UU(String AD_Alert_UU);

  /** Get AD_Alert_UU */
  public String getAD_Alert_UU();

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AlertMessage */
  public static final String COLUMNNAME_AlertMessage = "AlertMessage";

  /** Set Alert Message. Message of the Alert */
  public void setAlertMessage(String AlertMessage);

  /** Get Alert Message. Message of the Alert */
  public String getAlertMessage();

  /** Column name AlertSubject */
  public static final String COLUMNNAME_AlertSubject = "AlertSubject";

  /** Set Alert Subject. Subject of the Alert */
  public void setAlertSubject(String AlertSubject);

  /** Get Alert Subject. Subject of the Alert */
  public String getAlertSubject();

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

  /** Column name EnforceClientSecurity */
  public static final String COLUMNNAME_EnforceClientSecurity = "EnforceClientSecurity";

  /**
   * Set Enforce Client Security. Send alerts to recipient only if the client security rules of the
   * role allows
   */
  public void setEnforceClientSecurity(boolean EnforceClientSecurity);

  /**
   * Get Enforce Client Security. Send alerts to recipient only if the client security rules of the
   * role allows
   */
  public boolean isEnforceClientSecurity();

  /** Column name EnforceRoleSecurity */
  public static final String COLUMNNAME_EnforceRoleSecurity = "EnforceRoleSecurity";

  /**
   * Set Enforce Role Security. Send alerts to recipient only if the data security rules of the role
   * allows
   */
  public void setEnforceRoleSecurity(boolean EnforceRoleSecurity);

  /**
   * Get Enforce Role Security. Send alerts to recipient only if the data security rules of the role
   * allows
   */
  public boolean isEnforceRoleSecurity();

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

  /** Column name IsValid */
  public static final String COLUMNNAME_IsValid = "IsValid";

  /** Set Valid. Element is valid */
  public void setIsValid(boolean IsValid);

  /** Get Valid. Element is valid */
  public boolean isValid();

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
