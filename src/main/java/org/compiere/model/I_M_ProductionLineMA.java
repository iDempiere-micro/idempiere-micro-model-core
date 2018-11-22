package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_ProductionLineMA
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_ProductionLineMA {

  /** TableName=M_ProductionLineMA */
  public static final String Table_Name = "M_ProductionLineMA";

  /** AD_Table_ID=765 */
  public static final int Table_ID = 765;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 1 - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(1);

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

  /** Column name DateMaterialPolicy */
  public static final String COLUMNNAME_DateMaterialPolicy = "DateMaterialPolicy";

  /** Set Date Material Policy. Time used for LIFO and FIFO Material Policy */
  public void setDateMaterialPolicy(Timestamp DateMaterialPolicy);

  /** Get Date Material Policy. Time used for LIFO and FIFO Material Policy */
  public Timestamp getDateMaterialPolicy();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name M_AttributeSetInstance_ID */
  public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

  /** Set Attribute Set Instance. Product Attribute Set Instance */
  public void setM_AttributeSetInstance_ID(int M_AttributeSetInstance_ID);

  /** Get Attribute Set Instance. Product Attribute Set Instance */
  public int getMAttributeSetInstance_ID();

  public I_M_AttributeSetInstance getMAttributeSetInstance() throws RuntimeException;

  /** Column name MovementQty */
  public static final String COLUMNNAME_MovementQty = "MovementQty";

  /** Set Movement Quantity. Quantity of a product moved. */
  public void setMovementQty(BigDecimal MovementQty);

  /** Get Movement Quantity. Quantity of a product moved. */
  public BigDecimal getMovementQty();

  /** Column name M_ProductionLine_ID */
  public static final String COLUMNNAME_M_ProductionLine_ID = "M_ProductionLine_ID";

  /** Set Production Line. Document Line representing a production */
  public void setM_ProductionLine_ID(int M_ProductionLine_ID);

  /** Get Production Line. Document Line representing a production */
  public int getM_ProductionLine_ID();

  public I_M_ProductionLine getM_ProductionLine() throws RuntimeException;

  /** Column name M_ProductionLineMA_UU */
  public static final String COLUMNNAME_M_ProductionLineMA_UU = "M_ProductionLineMA_UU";

  /** Set M_ProductionLineMA_UU */
  public void setM_ProductionLineMA_UU(String M_ProductionLineMA_UU);

  /** Get M_ProductionLineMA_UU */
  public String getM_ProductionLineMA_UU();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
