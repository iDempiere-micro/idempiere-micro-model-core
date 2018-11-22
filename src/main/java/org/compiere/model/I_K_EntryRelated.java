package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for K_EntryRelated
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_K_EntryRelated {

  /** TableName=K_EntryRelated */
  public static final String Table_Name = "K_EntryRelated";

  /** AD_Table_ID=610 */
  public static final int Table_ID = 610;

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

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name K_Entry_ID */
  public static final String COLUMNNAME_K_Entry_ID = "K_Entry_ID";

  /** Set Entry. Knowledge Entry */
  public void setK_Entry_ID(int K_Entry_ID);

  /** Get Entry. Knowledge Entry */
  public int getK_Entry_ID();

  public I_K_Entry getK_Entry() throws RuntimeException;

  /** Column name K_EntryRelated_ID */
  public static final String COLUMNNAME_K_EntryRelated_ID = "K_EntryRelated_ID";

  /** Set Related Entry. Related Entry for this Entry */
  public void setK_EntryRelated_ID(int K_EntryRelated_ID);

  /** Get Related Entry. Related Entry for this Entry */
  public int getK_EntryRelated_ID();

  /** Column name K_EntryRelated_UU */
  public static final String COLUMNNAME_K_EntryRelated_UU = "K_EntryRelated_UU";

  /** Set K_EntryRelated_UU */
  public void setK_EntryRelated_UU(String K_EntryRelated_UU);

  /** Get K_EntryRelated_UU */
  public String getK_EntryRelated_UU();

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
