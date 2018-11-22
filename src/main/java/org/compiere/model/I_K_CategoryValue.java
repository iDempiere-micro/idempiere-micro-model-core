package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for K_CategoryValue
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_K_CategoryValue {

  /** TableName=K_CategoryValue */
  public static final String Table_Name = "K_CategoryValue";

  /** AD_Table_ID=614 */
  public static final int Table_ID = 614;

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

  /** Column name K_Category_ID */
  public static final String COLUMNNAME_K_Category_ID = "K_Category_ID";

  /** Set Knowledge Category. Knowledge Category */
  public void setK_Category_ID(int K_Category_ID);

  /** Get Knowledge Category. Knowledge Category */
  public int getK_Category_ID();

  public I_K_Category getK_Category() throws RuntimeException;

  /** Column name K_CategoryValue_ID */
  public static final String COLUMNNAME_K_CategoryValue_ID = "K_CategoryValue_ID";

  /** Set Category Value. The value of the category */
  public void setK_CategoryValue_ID(int K_CategoryValue_ID);

  /** Get Category Value. The value of the category */
  public int getK_CategoryValue_ID();

  /** Column name K_CategoryValue_UU */
  public static final String COLUMNNAME_K_CategoryValue_UU = "K_CategoryValue_UU";

  /** Set K_CategoryValue_UU */
  public void setK_CategoryValue_UU(String K_CategoryValue_UU);

  /** Get K_CategoryValue_UU */
  public String getK_CategoryValue_UU();

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
