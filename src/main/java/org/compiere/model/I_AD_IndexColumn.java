package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_IndexColumn
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_IndexColumn {

  /** TableName=AD_IndexColumn */
  public static final String Table_Name = "AD_IndexColumn";

  /** AD_Table_ID=200086 */
  public static final int Table_ID = 200086;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Column_ID */
  public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

  /** Set Column. Column in the table */
  public void setAD_Column_ID(int AD_Column_ID);

  /** Get Column. Column in the table */
  public int getAD_Column_ID();

  public I_AD_Column getAD_Column() throws RuntimeException;

  /** Column name AD_IndexColumn_ID */
  public static final String COLUMNNAME_AD_IndexColumn_ID = "AD_IndexColumn_ID";

  /** Set Table Index Column */
  public void setAD_IndexColumn_ID(int AD_IndexColumn_ID);

  /** Get Table Index Column */
  public int getAD_IndexColumn_ID();

  /** Column name AD_IndexColumn_UU */
  public static final String COLUMNNAME_AD_IndexColumn_UU = "AD_IndexColumn_UU";

  /** Set AD_IndexColumn_UU */
  public void setAD_IndexColumn_UU(String AD_IndexColumn_UU);

  /** Get AD_IndexColumn_UU */
  public String getAD_IndexColumn_UU();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_TableIndex_ID */
  public static final String COLUMNNAME_AD_TableIndex_ID = "AD_TableIndex_ID";

  /** Set Table Index */
  public void setAD_TableIndex_ID(int AD_TableIndex_ID);

  /** Get Table Index */
  public int getAD_TableIndex_ID();

  public I_AD_TableIndex getAD_TableIndex() throws RuntimeException;

  /** Column name ColumnSQL */
  public static final String COLUMNNAME_ColumnSQL = "ColumnSQL";

  /** Set Column SQL. Virtual Column (r/o) */
  public void setColumnSQL(String ColumnSQL);

  /** Get Column SQL. Virtual Column (r/o) */
  public String getColumnSQL();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name EntityType */
  public static final String COLUMNNAME_EntityType = "EntityType";

  /** Set Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public void setEntityType(String EntityType);

  /** Get Entity Type. Dictionary Entity Type; Determines ownership and synchronization */
  public String getEntityType();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

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
