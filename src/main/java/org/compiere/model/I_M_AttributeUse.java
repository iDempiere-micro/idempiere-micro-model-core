package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for M_AttributeUse
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_M_AttributeUse {

  /** TableName=M_AttributeUse */
  public static final String Table_Name = "M_AttributeUse";

  /** AD_Table_ID=563 */
  public static final int Table_ID = 563;

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

  public I_M_Attribute getMAttribute() throws RuntimeException;

  /** Column name M_AttributeSet_ID */
  public static final String COLUMNNAME_M_AttributeSet_ID = "M_AttributeSet_ID";

  /** Set Attribute Set. Product Attribute Set */
  public void setM_AttributeSet_ID(int M_AttributeSet_ID);

  /** Get Attribute Set. Product Attribute Set */
  public int getMAttributeSet_ID();

  public I_M_AttributeSet getMAttributeSet() throws RuntimeException;

  /** Column name M_AttributeUse_UU */
  public static final String COLUMNNAME_M_AttributeUse_UU = "M_AttributeUse_UU";

  /** Set M_AttributeUse_UU */
  public void setM_AttributeUse_UU(String M_AttributeUse_UU);

  /** Get M_AttributeUse_UU */
  public String getMAttributeUse_UU();

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
}
