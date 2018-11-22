package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_Product_QualityTest
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_Product_QualityTest {

  /** TableName=M_Product_QualityTest */
  public static final String Table_Name = "M_Product_QualityTest";

  /** AD_Table_ID=53333 */
  public static final int Table_ID = 53333;

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

  /** Column name ExpectedResult */
  public static final String COLUMNNAME_ExpectedResult = "ExpectedResult";

  /** Set Expected Result */
  public void setExpectedResult(String ExpectedResult);

  /** Get Expected Result */
  public String getExpectedResult();

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

  /** Column name M_Product_QualityTest_ID */
  public static final String COLUMNNAME_M_Product_QualityTest_ID = "M_Product_QualityTest_ID";

  /** Set Product Quality Test */
  public void setM_Product_QualityTest_ID(int M_Product_QualityTest_ID);

  /** Get Product Quality Test */
  public int getM_Product_QualityTest_ID();

  /** Column name M_Product_QualityTest_UU */
  public static final String COLUMNNAME_M_Product_QualityTest_UU = "M_Product_QualityTest_UU";

  /** Set M_Product_QualityTest_UU */
  public void setM_Product_QualityTest_UU(String M_Product_QualityTest_UU);

  /** Get M_Product_QualityTest_UU */
  public String getM_Product_QualityTest_UU();

  /** Column name M_QualityTest_ID */
  public static final String COLUMNNAME_M_QualityTest_ID = "M_QualityTest_ID";

  /** Set Quality Test */
  public void setM_QualityTest_ID(int M_QualityTest_ID);

  /** Get Quality Test */
  public int getM_QualityTest_ID();

  public I_M_QualityTest getM_QualityTest() throws RuntimeException;

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
