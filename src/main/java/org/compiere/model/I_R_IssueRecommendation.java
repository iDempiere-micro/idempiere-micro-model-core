package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for R_IssueRecommendation
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_R_IssueRecommendation {

  /** TableName=R_IssueRecommendation */
  public static final String Table_Name = "R_IssueRecommendation";

  /** AD_Table_ID=837 */
  public static final int Table_ID = 837;

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

  /** Column name R_IssueRecommendation_ID */
  public static final String COLUMNNAME_R_IssueRecommendation_ID = "R_IssueRecommendation_ID";

  /** Set Issue Recommendation. Recommendations how to fix an Issue */
  public void setR_IssueRecommendation_ID(int R_IssueRecommendation_ID);

  /** Get Issue Recommendation. Recommendations how to fix an Issue */
  public int getR_IssueRecommendation_ID();

  /** Column name R_IssueRecommendation_UU */
  public static final String COLUMNNAME_R_IssueRecommendation_UU = "R_IssueRecommendation_UU";

  /** Set R_IssueRecommendation_UU */
  public void setR_IssueRecommendation_UU(String R_IssueRecommendation_UU);

  /** Get R_IssueRecommendation_UU */
  public String getR_IssueRecommendation_UU();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
