package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for K_Source
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_K_Source {

  /** TableName=K_Source */
  public static final String Table_Name = "K_Source";

  /** AD_Table_ID=609 */
  public static final int Table_ID = 609;

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

  /** Column name DescriptionURL */
  public static final String COLUMNNAME_DescriptionURL = "DescriptionURL";

  /** Set Description URL. URL for the description */
  public void setDescriptionURL(String DescriptionURL);

  /** Get Description URL. URL for the description */
  public String getDescriptionURL();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name K_Source_ID */
  public static final String COLUMNNAME_K_Source_ID = "K_Source_ID";

  /** Set Knowledge Source. Source of a Knowledge Entry */
  public void setK_Source_ID(int K_Source_ID);

  /** Get Knowledge Source. Source of a Knowledge Entry */
  public int getK_Source_ID();

  /** Column name K_Source_UU */
  public static final String COLUMNNAME_K_Source_UU = "K_Source_UU";

  /** Set K_Source_UU */
  public void setK_Source_UU(String K_Source_UU);

  /** Get K_Source_UU */
  public String getK_Source_UU();

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
