package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Image
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Image {

  /** TableName=AD_Image */
  public static final String Table_Name = "AD_Image";

  /** AD_Table_ID=461 */
  public static final int Table_ID = 461;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 6 - System - Client */
  BigDecimal accessLevel = BigDecimal.valueOf(6);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Image_ID */
  public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";

  /** Set Image. Image or Icon */
  public void setAD_Image_ID(int AD_Image_ID);

  /** Get Image. Image or Icon */
  public int getAD_Image_ID();

  /** Column name AD_Image_UU */
  public static final String COLUMNNAME_AD_Image_UU = "AD_Image_UU";

  /** Set AD_Image_UU */
  public void setAD_Image_UU(String AD_Image_UU);

  /** Get AD_Image_UU */
  public String getAD_Image_UU();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name BinaryData */
  public static final String COLUMNNAME_BinaryData = "BinaryData";

  /** Set Binary Data. Binary Data */
  public void setBinaryData(byte[] BinaryData);

  /** Get Binary Data. Binary Data */
  public byte[] getBinaryData();

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

  /** Column name EntityType */
  public static final String COLUMNNAME_EntityType = "EntityType";

  /** Set Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public void setEntityType(String EntityType);

  /** Get Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public String getEntityType();

  /** Column name ImageURL */
  public static final String COLUMNNAME_ImageURL = "ImageURL";

  /** Set Image URL. URL of image */
  public void setImageURL(String ImageURL);

  /** Get Image URL. URL of image */
  public String getImageURL();

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
