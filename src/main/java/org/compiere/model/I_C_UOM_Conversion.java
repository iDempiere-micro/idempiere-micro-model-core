package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for C_UOM_Conversion
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_C_UOM_Conversion {

  /** TableName=C_UOM_Conversion */
  public static final String Table_Name = "C_UOM_Conversion";

  /** AD_Table_ID=175 */
  public static final int Table_ID = 175;

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

  /** Column name C_UOM_Conversion_ID */
  public static final String COLUMNNAME_C_UOM_Conversion_ID = "C_UOM_Conversion_ID";

  /** Set UOM Conversion. Unit of Measure Conversion */
  public void setC_UOM_Conversion_ID(int C_UOM_Conversion_ID);

  /** Get UOM Conversion. Unit of Measure Conversion */
  public int getC_UOM_Conversion_ID();

  /** Column name C_UOM_Conversion_UU */
  public static final String COLUMNNAME_C_UOM_Conversion_UU = "C_UOM_Conversion_UU";

  /** Set C_UOM_Conversion_UU */
  public void setC_UOM_Conversion_UU(String C_UOM_Conversion_UU);

  /** Get C_UOM_Conversion_UU */
  public String getC_UOM_Conversion_UU();

  /** Column name C_UOM_ID */
  public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

  /** Set UOM. Unit of Measure */
  public void setC_UOM_ID(int C_UOM_ID);

  /** Get UOM. Unit of Measure */
  public int getC_UOM_ID();

  public I_C_UOM getC_UOM() throws RuntimeException;

  /** Column name C_UOM_To_ID */
  public static final String COLUMNNAME_C_UOM_To_ID = "C_UOM_To_ID";

  /** Set UoM To. Target or destination Unit of Measure */
  public void setC_UOM_To_ID(int C_UOM_To_ID);

  /** Get UoM To. Target or destination Unit of Measure */
  public int getC_UOM_To_ID();

  public I_C_UOM getC_UOM_To() throws RuntimeException;

  /** Column name DivideRate */
  public static final String COLUMNNAME_DivideRate = "DivideRate";

  /** Set Divide Rate. To convert Source number to Target number, the Source is divided */
  public void setDivideRate(BigDecimal DivideRate);

  /** Get Divide Rate. To convert Source number to Target number, the Source is divided */
  public BigDecimal getDivideRate();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name M_Product_ID */
  public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

  /** Set Product. Product, Service, Item */
  public void setM_Product_ID(int M_Product_ID);

  /** Get Product. Product, Service, Item */
  public int getM_Product_ID();

  public I_M_Product getM_Product() throws RuntimeException;

  /** Column name MultiplyRate */
  public static final String COLUMNNAME_MultiplyRate = "MultiplyRate";

  /** Set Multiply Rate. Rate to multiple the source by to calculate the target. */
  public void setMultiplyRate(BigDecimal MultiplyRate);

  /** Get Multiply Rate. Rate to multiple the source by to calculate the target. */
  public BigDecimal getMultiplyRate();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
