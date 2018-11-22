package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_DistributionRunLine
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_DistributionRunLine {

  /** TableName=M_DistributionRunLine */
  public static final String Table_Name = "M_DistributionRunLine";

  /** AD_Table_ID=713 */
  public static final int Table_ID = 713;

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

  /** Column name Line */
  public static final String COLUMNNAME_Line = "Line";

  /** Set Line No. Unique line for this document */
  public void setLine(int Line);

  /** Get Line No. Unique line for this document */
  public int getLine();

  /** Column name M_DistributionList_ID */
  public static final String COLUMNNAME_M_DistributionList_ID = "M_DistributionList_ID";

  /**
   * Set Distribution List. Distribution Lists allow to distribute products to a selected list of
   * partners
   */
  public void setM_DistributionList_ID(int M_DistributionList_ID);

  /**
   * Get Distribution List. Distribution Lists allow to distribute products to a selected list of
   * partners
   */
  public int getM_DistributionList_ID();

  public I_M_DistributionList getM_DistributionList() throws RuntimeException;

  /** Column name M_DistributionRun_ID */
  public static final String COLUMNNAME_M_DistributionRun_ID = "M_DistributionRun_ID";

  /**
   * Set Distribution Run. Distribution Run create Orders to distribute products to a selected list
   * of partners
   */
  public void setM_DistributionRun_ID(int M_DistributionRun_ID);

  /**
   * Get Distribution Run. Distribution Run create Orders to distribute products to a selected list
   * of partners
   */
  public int getM_DistributionRun_ID();

  public I_M_DistributionRun getM_DistributionRun() throws RuntimeException;

  /** Column name M_DistributionRunLine_ID */
  public static final String COLUMNNAME_M_DistributionRunLine_ID = "M_DistributionRunLine_ID";

  /**
   * Set Distribution Run Line. Distribution Run Lines define Distribution List, the Product and
   * Quantities
   */
  public void setM_DistributionRunLine_ID(int M_DistributionRunLine_ID);

  /**
   * Get Distribution Run Line. Distribution Run Lines define Distribution List, the Product and
   * Quantities
   */
  public int getM_DistributionRunLine_ID();

  /** Column name M_DistributionRunLine_UU */
  public static final String COLUMNNAME_M_DistributionRunLine_UU = "M_DistributionRunLine_UU";

  /** Set M_DistributionRunLine_UU */
  public void setM_DistributionRunLine_UU(String M_DistributionRunLine_UU);

  /** Get M_DistributionRunLine_UU */
  public String getM_DistributionRunLine_UU();

  /** Column name MinQty */
  public static final String COLUMNNAME_MinQty = "MinQty";

  /** Set Minimum Quantity. Minimum quantity for the business partner */
  public void setMinQty(BigDecimal MinQty);

  /** Get Minimum Quantity. Minimum quantity for the business partner */
  public BigDecimal getMinQty();

  /** Column name M_Product_ID */
  public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

  /** Set Product. Product, Service, Item */
  public void setM_Product_ID(int M_Product_ID);

  /** Get Product. Product, Service, Item */
  public int getM_Product_ID();

  public I_M_Product getM_Product() throws RuntimeException;

  /** Column name TotalQty */
  public static final String COLUMNNAME_TotalQty = "TotalQty";

  /** Set Total Quantity. Total Quantity */
  public void setTotalQty(BigDecimal TotalQty);

  /** Get Total Quantity. Total Quantity */
  public BigDecimal getTotalQty();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
