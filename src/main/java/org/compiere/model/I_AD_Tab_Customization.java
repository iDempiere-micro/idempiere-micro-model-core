package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Tab_Customization
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Tab_Customization {

  /** TableName=AD_Tab_Customization */
  public static final String Table_Name = "AD_Tab_Customization";

  /** AD_Table_ID=200008 */
  public static final int Table_ID = 200008;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Tab_Customization_ID */
  public static final String COLUMNNAME_AD_Tab_Customization_ID = "AD_Tab_Customization_ID";

  /** Set Tab Customization */
  public void setAD_Tab_Customization_ID(int AD_Tab_Customization_ID);

  /** Get Tab Customization */
  public int getAD_Tab_Customization_ID();

  /** Column name AD_Tab_Customization_UU */
  public static final String COLUMNNAME_AD_Tab_Customization_UU = "AD_Tab_Customization_UU";

  /** Set AD_Tab_Customization_UU */
  public void setAD_Tab_Customization_UU(String AD_Tab_Customization_UU);

  /** Get AD_Tab_Customization_UU */
  public String getAD_Tab_Customization_UU();

  /** Column name AD_Tab_ID */
  public static final String COLUMNNAME_AD_Tab_ID = "AD_Tab_ID";

  /** Set Tab. Tab within a Window */
  public void setAD_Tab_ID(int AD_Tab_ID);

  /** Get Tab. Tab within a Window */
  public int getAD_Tab_ID();

  public I_AD_Tab getAD_Tab() throws RuntimeException;

  /** Column name AD_User_ID */
  public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

  /** Set User/Contact. User within the system - Internal or Business Partner Contact */
  public void setAD_User_ID(int AD_User_ID);

  /** Get User/Contact. User within the system - Internal or Business Partner Contact */
  public int getAD_User_ID();

  public I_AD_User getAD_User() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name Custom */
  public static final String COLUMNNAME_Custom = "Custom";

  /** Set Custom */
  public void setCustom(String Custom);

  /** Get Custom */
  public String getCustom();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name IsDisplayedGrid */
  public static final String COLUMNNAME_IsDisplayedGrid = "IsDisplayedGrid";

  /** Set Show in Grid */
  public void setIsDisplayedGrid(String IsDisplayedGrid);

  /** Get Show in Grid */
  public String getIsDisplayedGrid();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
