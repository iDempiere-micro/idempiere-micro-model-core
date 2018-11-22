package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_InfoRelated
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_InfoRelated {

  /** TableName=AD_InfoRelated */
  public static final String Table_Name = "AD_InfoRelated";

  /** AD_Table_ID=200089 */
  public static final int Table_ID = 200089;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_InfoRelated_ID */
  public static final String COLUMNNAME_AD_InfoRelated_ID = "AD_InfoRelated_ID";

  /** Set InfoRelated */
  public void setAD_InfoRelated_ID(int AD_InfoRelated_ID);

  /** Get InfoRelated */
  public int getAD_InfoRelated_ID();

  /** Column name AD_InfoRelated_UU */
  public static final String COLUMNNAME_AD_InfoRelated_UU = "AD_InfoRelated_UU";

  /** Set AD_InfoRelated_UU */
  public void setAD_InfoRelated_UU(String AD_InfoRelated_UU);

  /** Get AD_InfoRelated_UU */
  public String getAD_InfoRelated_UU();

  /** Column name AD_InfoWindow_ID */
  public static final String COLUMNNAME_AD_InfoWindow_ID = "AD_InfoWindow_ID";

  /** Set Info Window. Info and search/select Window */
  public void setAD_InfoWindow_ID(int AD_InfoWindow_ID);

  /** Get Info Window. Info and search/select Window */
  public int getAD_InfoWindow_ID();

  public I_AD_InfoWindow getAD_InfoWindow() throws RuntimeException;

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

  /** Column name ParentRelatedColumn_ID */
  public static final String COLUMNNAME_ParentRelatedColumn_ID = "ParentRelatedColumn_ID";

  /**
   * Set Parent Related Column. column in parent info window, link with column in this relate info
   */
  public void setParentRelatedColumn_ID(int ParentRelatedColumn_ID);

  /**
   * Get Parent Related Column. column in parent info window, link with column in this relate info
   */
  public int getParentRelatedColumn_ID();

  public I_AD_InfoColumn getParentRelatedColumn() throws RuntimeException;

  /** Column name RelatedColumn_ID */
  public static final String COLUMNNAME_RelatedColumn_ID = "RelatedColumn_ID";

  /** Set Related Info Column */
  public void setRelatedColumn_ID(int RelatedColumn_ID);

  /** Get Related Info Column */
  public int getRelatedColumn_ID();

  public I_AD_InfoColumn getRelatedColumn() throws RuntimeException;

  /** Column name RelatedInfo_ID */
  public static final String COLUMNNAME_RelatedInfo_ID = "RelatedInfo_ID";

  /** Set Related Info Window */
  public void setRelatedInfo_ID(int RelatedInfo_ID);

  /** Get Related Info Window */
  public int getRelatedInfo_ID();

  public I_AD_InfoWindow getRelatedInfo() throws RuntimeException;

  /** Column name SeqNo */
  public static final String COLUMNNAME_SeqNo = "SeqNo";

  /** Set Sequence. Method of ordering records; lowest number comes first */
  public void setSeqNo(int SeqNo);

  /** Get Sequence. Method of ordering records; lowest number comes first */
  public int getSeqNo();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
