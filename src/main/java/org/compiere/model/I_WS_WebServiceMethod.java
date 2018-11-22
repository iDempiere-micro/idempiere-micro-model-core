package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for WS_WebServiceMethod
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_WS_WebServiceMethod {

  /** TableName=WS_WebServiceMethod */
  public static final String Table_Name = "WS_WebServiceMethod";

  /** AD_Table_ID=53163 */
  public static final int Table_ID = 53163;

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

  /** Column name WS_WebService_ID */
  public static final String COLUMNNAME_WS_WebService_ID = "WS_WebService_ID";

  /** Set Web Service */
  public void setWS_WebService_ID(int WS_WebService_ID);

  /** Get Web Service */
  public int getWS_WebService_ID();

  public I_WS_WebService getWS_WebService() throws RuntimeException;

  /** Column name WS_WebServiceMethod_ID */
  public static final String COLUMNNAME_WS_WebServiceMethod_ID = "WS_WebServiceMethod_ID";

  /** Set Web Service Method */
  public void setWS_WebServiceMethod_ID(int WS_WebServiceMethod_ID);

  /** Get Web Service Method */
  public int getWS_WebServiceMethod_ID();

  /** Column name WS_WebServiceMethod_UU */
  public static final String COLUMNNAME_WS_WebServiceMethod_UU = "WS_WebServiceMethod_UU";

  /** Set WS_WebServiceMethod_UU */
  public void setWS_WebServiceMethod_UU(String WS_WebServiceMethod_UU);

  /** Get WS_WebServiceMethod_UU */
  public String getWS_WebServiceMethod_UU();
}
