package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_Lot
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_Lot {

  /** TableName=M_Lot */
  public static final String Table_Name = "M_Lot";

  /** AD_Table_ID=557 */
  public static final int Table_ID = 557;

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

  /** Column name DateFrom */
  public static final String COLUMNNAME_DateFrom = "DateFrom";

  /** Set Date From. Starting date for a range */
  public void setDateFrom(Timestamp DateFrom);

  /** Get Date From. Starting date for a range */
  public Timestamp getDateFrom();

  /** Column name DateTo */
  public static final String COLUMNNAME_DateTo = "DateTo";

  /** Set Date To. End date of a date range */
  public void setDateTo(Timestamp DateTo);

  /** Get Date To. End date of a date range */
  public Timestamp getDateTo();

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

  /** Column name M_LotCtl_ID */
  public static final String COLUMNNAME_M_LotCtl_ID = "M_LotCtl_ID";

  /** Set Lot Control. Product Lot Control */
  public void setM_LotCtl_ID(int M_LotCtl_ID);

  /** Get Lot Control. Product Lot Control */
  public int getM_LotCtl_ID();

  public I_M_LotCtl getM_LotCtl() throws RuntimeException;

  /** Column name M_Lot_ID */
  public static final String COLUMNNAME_M_Lot_ID = "M_Lot_ID";

  /** Set Lot. Product Lot Definition */
  public void setM_Lot_ID(int M_Lot_ID);

  /** Get Lot. Product Lot Definition */
  public int getM_Lot_ID();

  /** Column name M_Lot_UU */
  public static final String COLUMNNAME_M_Lot_UU = "M_Lot_UU";

  /** Set M_Lot_UU */
  public void setM_Lot_UU(String M_Lot_UU);

  /** Get M_Lot_UU */
  public String getM_Lot_UU();

  /** Column name M_Product_ID */
  public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

  /** Set Product. Product, Service, Item */
  public void setM_Product_ID(int M_Product_ID);

  /** Get Product. Product, Service, Item */
  public int getM_Product_ID();

  public I_M_Product getM_Product() throws RuntimeException;

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
