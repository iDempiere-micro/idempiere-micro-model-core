package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for CM_NewsChannel
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_CM_NewsChannel {

  /** TableName=CM_NewsChannel */
  public static final String Table_Name = "CM_NewsChannel";

  /** AD_Table_ID=870 */
  public static final int Table_ID = 870;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Language */
  public static final String COLUMNNAME_AD_Language = "AD_Language";

  /** Set Language. Language for this entity */
  public void setADLanguage(String AD_Language);

  /** Get Language. Language for this entity */
  public String getADLanguage();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name CM_NewsChannel_ID */
  public static final String COLUMNNAME_CM_NewsChannel_ID = "CM_NewsChannel_ID";

  /** Set News Channel. News channel for rss feed */
  public void setCM_NewsChannel_ID(int CM_NewsChannel_ID);

  /** Get News Channel. News channel for rss feed */
  public int getCM_NewsChannel_ID();

  /** Column name CM_NewsChannel_UU */
  public static final String COLUMNNAME_CM_NewsChannel_UU = "CM_NewsChannel_UU";

  /** Set CM_NewsChannel_UU */
  public void setCM_NewsChannel_UU(String CM_NewsChannel_UU);

  /** Get CM_NewsChannel_UU */
  public String getCM_NewsChannel_UU();

  /** Column name CM_WebProject_ID */
  public static final String COLUMNNAME_CM_WebProject_ID = "CM_WebProject_ID";

  /**
   * Set Web Project. A web project is the main data container for Containers, URLs, Ads, Media etc.
   */
  public void setCM_WebProject_ID(int CM_WebProject_ID);

  /**
   * Get Web Project. A web project is the main data container for Containers, URLs, Ads, Media etc.
   */
  public int getCM_WebProject_ID();

  public I_CM_WebProject getCM_WebProject() throws RuntimeException;

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

  /** Column name Link */
  public static final String COLUMNNAME_Link = "Link";

  /** Set Link. Contains URL to a target */
  public void setLink(String Link);

  /** Get Link. Contains URL to a target */
  public String getLink();

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
