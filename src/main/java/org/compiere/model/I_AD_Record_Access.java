package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Record_Access
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Record_Access {

  /** TableName=AD_Record_Access */
  public static final String Table_Name = "AD_Record_Access";

  /** AD_Table_ID=567 */
  public static final int Table_ID = 567;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Record_Access_UU */
  public static final String COLUMNNAME_AD_Record_Access_UU = "AD_Record_Access_UU";

  /** Set AD_Record_Access_UU */
  public void setAD_Record_Access_UU(String AD_Record_Access_UU);

  /** Get AD_Record_Access_UU */
  public String getAD_Record_Access_UU();

  /** Column name AD_Role_ID */
  public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

  /** Set Role. Responsibility Role */
  public void setAD_Role_ID(int AD_Role_ID);

  /** Get Role. Responsibility Role */
  public int getAD_Role_ID();

  public I_AD_Role getAD_Role() throws RuntimeException;

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

  /** Column name IsDependentEntities */
  public static final String COLUMNNAME_IsDependentEntities = "IsDependentEntities";

  /** Set Dependent Entities. Also check access in dependent entities */
  public void setIsDependentEntities(boolean IsDependentEntities);

  /** Get Dependent Entities. Also check access in dependent entities */
  public boolean isDependentEntities();

  /** Column name IsExclude */
  public static final String COLUMNNAME_IsExclude = "IsExclude";

  /** Set Exclude. Exclude access to the data - if not selected Include access to the data */
  public void setIsExclude(boolean IsExclude);

  /** Get Exclude. Exclude access to the data - if not selected Include access to the data */
  public boolean isExclude();

  /** Column name IsReadOnly */
  public static final String COLUMNNAME_IsReadOnly = "IsReadOnly";

  /** Set Read Only. Field is read only */
  public void setIsReadOnly(boolean IsReadOnly);

  /** Get Read Only. Field is read only */
  public boolean isReadOnly();

  /** Column name Record_ID */
  public static final String COLUMNNAME_Record_ID = "Record_ID";

  /** Set Record ID. Direct internal record ID */
  public void setRecord_ID(int Record_ID);

  /** Get Record ID. Direct internal record ID */
  public int getRecord_ID();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
