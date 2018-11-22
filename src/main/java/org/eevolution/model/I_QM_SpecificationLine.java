package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for QM_SpecificationLine
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_QM_SpecificationLine {

  /** TableName=QM_SpecificationLine */
  public static final String Table_Name = "QM_SpecificationLine";

  /** AD_Table_ID=53041 */
  public static final int Table_ID = 53041;

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

  /** Column name AndOr */
  public static final String COLUMNNAME_AndOr = "AndOr";

  /** Set And/Or. Logical operation: AND or OR */
  public void setAndOr(String AndOr);

  /** Get And/Or. Logical operation: AND or OR */
  public String getAndOr();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name M_Attribute_ID */
  public static final String COLUMNNAME_M_Attribute_ID = "M_Attribute_ID";

  /** Set Attribute. Product Attribute */
  public void setMAttributeID(int M_Attribute_ID);

  /** Get Attribute. Product Attribute */
  public int getMAttribute_ID();

  public org.compiere.model.I_M_Attribute getMAttribute() throws RuntimeException;

  /** Column name Operation */
  public static final String COLUMNNAME_Operation = "Operation";

  /** Set Operation. Compare Operation */
  public void setOperation(String Operation);

  /** Get Operation. Compare Operation */
  public String getOperation();

  /** Column name QM_Specification_ID */
  public static final String COLUMNNAME_QM_Specification_ID = "QM_Specification_ID";

  /** Set Quality Specification */
  public void setQM_Specification_ID(int QM_Specification_ID);

  /** Get Quality Specification */
  public int getQM_Specification_ID();

  public org.eevolution.model.I_QM_Specification getQM_Specification() throws RuntimeException;

  /** Column name QM_SpecificationLine_ID */
  public static final String COLUMNNAME_QM_SpecificationLine_ID = "QM_SpecificationLine_ID";

  /** Set QM Specification Line */
  public void setQM_SpecificationLine_ID(int QM_SpecificationLine_ID);

  /** Get QM Specification Line */
  public int getQM_SpecificationLine_ID();

  /** Column name QM_SpecificationLine_UU */
  public static final String COLUMNNAME_QM_SpecificationLine_UU = "QM_SpecificationLine_UU";

  /** Set QM_SpecificationLine_UU */
  public void setQM_SpecificationLine_UU(String QM_SpecificationLine_UU);

  /** Get QM_SpecificationLine_UU */
  public String getQM_SpecificationLine_UU();

  /** Column name SeqNo */
  public static final String COLUMNNAME_SeqNo = "SeqNo";

  /** Set Sequence. Method of ordering records; lowest number comes first */
  public void setSeqNo(int SeqNo);

  /** Get Sequence. Method of ordering records; lowest number comes first */
  public int getSeqNo();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name ValidFrom */
  public static final String COLUMNNAME_ValidFrom = "ValidFrom";

  /** Set Valid from. Valid from including this date (first day) */
  public void setValidFrom(String ValidFrom);

  /** Get Valid from. Valid from including this date (first day) */
  public String getValidFrom();

  /** Column name ValidTo */
  public static final String COLUMNNAME_ValidTo = "ValidTo";

  /** Set Valid to. Valid to including this date (last day) */
  public void setValidTo(Timestamp ValidTo);

  /** Get Valid to. Valid to including this date (last day) */
  public Timestamp getValidTo();

  /** Column name Value */
  public static final String COLUMNNAME_Value = "Value";

  /** Set Search Key. Search key for the record in the format required - must be unique */
  public void setValue(String Value);

  /** Get Search Key. Search key for the record in the format required - must be unique */
  public String getValue();
}
