package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for CM_ChatType
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_CM_ChatType {

  /** TableName=CM_ChatType */
  public static final String Table_Name = "CM_ChatType";

  /** AD_Table_ID=874 */
  public static final int Table_ID = 874;

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

  /** Column name AD_Table_ID */
  public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

  /** Set Table. Database Table information */
  public void setAD_Table_ID(int AD_Table_ID);

  /** Get Table. Database Table information */
  public int getAD_Table_ID();

  public I_AD_Table getAD_Table() throws RuntimeException;

  /** Column name CM_ChatType_ID */
  public static final String COLUMNNAME_CM_ChatType_ID = "CM_ChatType_ID";

  /** Set Chat Type. Type of discussion / chat */
  public void setCM_ChatType_ID(int CM_ChatType_ID);

  /** Get Chat Type. Type of discussion / chat */
  public int getCM_ChatType_ID();

  /** Column name CM_ChatType_UU */
  public static final String COLUMNNAME_CM_ChatType_UU = "CM_ChatType_UU";

  /** Set CM_ChatType_UU */
  public void setCM_ChatType_UU(String CM_ChatType_UU);

  /** Get CM_ChatType_UU */
  public String getCM_ChatType_UU();

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

  /** Column name ModerationType */
  public static final String COLUMNNAME_ModerationType = "ModerationType";

  /** Set Moderation Type. Type of moderation */
  public void setModerationType(String ModerationType);

  /** Get Moderation Type. Type of moderation */
  public String getModerationType();

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
