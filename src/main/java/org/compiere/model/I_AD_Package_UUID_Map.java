package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Package_UUID_Map
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Package_UUID_Map {

  /** TableName=AD_Package_UUID_Map */
  public static final String Table_Name = "AD_Package_UUID_Map";

  /** AD_Table_ID=200105 */
  public static final int Table_ID = 200105;

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

  /** Column name AD_Package_UUID_Map_ID */
  public static final String COLUMNNAME_AD_Package_UUID_Map_ID = "AD_Package_UUID_Map_ID";

  /** Set UUID Mapping. UUID Mapping between client */
  public void setAD_Package_UUID_Map_ID(int AD_Package_UUID_Map_ID);

  /** Get UUID Mapping. UUID Mapping between client */
  public int getAD_Package_UUID_Map_ID();

  /** Column name AD_Package_UUID_Map_UU */
  public static final String COLUMNNAME_AD_Package_UUID_Map_UU = "AD_Package_UUID_Map_UU";

  /** Set AD_Package_UUID_Map_UU */
  public void setAD_Package_UUID_Map_UU(String AD_Package_UUID_Map_UU);

  /** Get AD_Package_UUID_Map_UU */
  public String getAD_Package_UUID_Map_UU();

  /** Column name AD_Table_ID */
  public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

  /** Set Table. Database Table information */
  public void setAD_Table_ID(int AD_Table_ID);

  /** Get Table. Database Table information */
  public int getAD_Table_ID();

  public I_AD_Table getAD_Table() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name Source_UUID */
  public static final String COLUMNNAME_Source_UUID = "Source_UUID";

  /** Set Source UUID. UUID from the source client */
  public void setSource_UUID(String Source_UUID);

  /** Get Source UUID. UUID from the source client */
  public String getSource_UUID();

  /** Column name Target_UUID */
  public static final String COLUMNNAME_Target_UUID = "Target_UUID";

  /** Set Target UUID. New UUID created by the pack in client */
  public void setTarget_UUID(String Target_UUID);

  /** Get Target UUID. New UUID created by the pack in client */
  public String getTarget_UUID();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
