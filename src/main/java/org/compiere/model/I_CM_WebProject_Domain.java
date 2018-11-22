package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for CM_WebProject_Domain
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_CM_WebProject_Domain {

  /** TableName=CM_WebProject_Domain */
  public static final String Table_Name = "CM_WebProject_Domain";

  /** AD_Table_ID=873 */
  public static final int Table_ID = 873;

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

  /** Column name CM_Container_ID */
  public static final String COLUMNNAME_CM_Container_ID = "CM_Container_ID";

  /** Set Web Container. Web Container contains content like images, text etc. */
  public void setCM_Container_ID(int CM_Container_ID);

  /** Get Web Container. Web Container contains content like images, text etc. */
  public int getCM_Container_ID();

  public I_CM_Container getCM_Container() throws RuntimeException;

  /** Column name CM_WebProject_Domain_ID */
  public static final String COLUMNNAME_CM_WebProject_Domain_ID = "CM_WebProject_Domain_ID";

  /** Set WebProject Domain. Definition of Domainhandling */
  public void setCM_WebProject_Domain_ID(int CM_WebProject_Domain_ID);

  /** Get WebProject Domain. Definition of Domainhandling */
  public int getCM_WebProject_Domain_ID();

  /** Column name CM_WebProject_Domain_UU */
  public static final String COLUMNNAME_CM_WebProject_Domain_UU = "CM_WebProject_Domain_UU";

  /** Set CM_WebProject_Domain_UU */
  public void setCM_WebProject_Domain_UU(String CM_WebProject_Domain_UU);

  /** Get CM_WebProject_Domain_UU */
  public String getCM_WebProject_Domain_UU();

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

  /** Column name FQDN */
  public static final String COLUMNNAME_FQDN = "FQDN";

  /** Set Fully Qualified Domain Name. Fully Qualified Domain Name i.e. www.comdivision.com */
  public void setFQDN(String FQDN);

  /** Get Fully Qualified Domain Name. Fully Qualified Domain Name i.e. www.comdivision.com */
  public String getFQDN();

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
}
