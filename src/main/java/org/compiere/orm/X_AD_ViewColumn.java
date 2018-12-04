package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_ViewColumn;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_ViewColumn
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_ViewColumn extends PO implements I_AD_ViewColumn, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_ViewColumn(Properties ctx, int AD_ViewColumn_ID, String trxName) {
    super(ctx, AD_ViewColumn_ID, trxName);
    /**
     * if (AD_ViewColumn_ID == 0) { setAD_ViewColumn_ID (0); setAD_ViewComponent_ID (0);
     * setColumnName (null); setEntityType (null); // @SQL=select
     * get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual }
     */
  }

  /** Load Constructor */
  public X_AD_ViewColumn(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * AccessLevel
   *
   * @return 4 - System
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("X_AD_ViewColumn[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Set Database View Column.
   *
   * @param AD_ViewColumn_ID Database View Column
   */
  public void setAD_ViewColumn_ID(int AD_ViewColumn_ID) {
    if (AD_ViewColumn_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_ViewColumn_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_ViewColumn_ID, Integer.valueOf(AD_ViewColumn_ID));
  }

  /**
   * Get Database View Column.
   *
   * @return Database View Column
   */
  public int getAD_ViewColumn_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_ViewColumn_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set AD_ViewColumn_UU.
   *
   * @param AD_ViewColumn_UU AD_ViewColumn_UU
   */
  public void setAD_ViewColumn_UU(String AD_ViewColumn_UU) {
    set_Value(COLUMNNAME_AD_ViewColumn_UU, AD_ViewColumn_UU);
  }

  /**
   * Get AD_ViewColumn_UU.
   *
   * @return AD_ViewColumn_UU
   */
  public String getAD_ViewColumn_UU() {
    return (String) get_Value(COLUMNNAME_AD_ViewColumn_UU);
  }

  public org.compiere.model.I_AD_ViewComponent getAD_ViewComponent() throws RuntimeException {
    return (org.compiere.model.I_AD_ViewComponent)
        MTable.get(getCtx(), org.compiere.model.I_AD_ViewComponent.Table_Name)
            .getPO(getAD_ViewComponent_ID(), get_TrxName());
  }

  /**
   * Set Database View Component.
   *
   * @param AD_ViewComponent_ID Database View Component
   */
  public void setAD_ViewComponent_ID(int AD_ViewComponent_ID) {
    if (AD_ViewComponent_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_ViewComponent_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_ViewComponent_ID, Integer.valueOf(AD_ViewComponent_ID));
  }

  /**
   * Get Database View Component.
   *
   * @return Database View Component
   */
  public int getAD_ViewComponent_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_ViewComponent_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set DB Column Name.
   *
   * @param ColumnName Name of the column in the database
   */
  public void setColumnName(String ColumnName) {
    set_Value(COLUMNNAME_ColumnName, ColumnName);
  }

  /**
   * Get DB Column Name.
   *
   * @return Name of the column in the database
   */
  public String getColumnName() {
    return (String) get_Value(COLUMNNAME_ColumnName);
  }

  /**
   * Set Column SQL.
   *
   * @param ColumnSQL Virtual Column (r/o)
   */
  public void setColumnSQL(String ColumnSQL) {
    set_Value(COLUMNNAME_ColumnSQL, ColumnSQL);
  }

  /**
   * Get Column SQL.
   *
   * @return Virtual Column (r/o)
   */
  public String getColumnSQL() {
    return (String) get_Value(COLUMNNAME_ColumnSQL);
  }

  /** DBDataType AD_Reference_ID=200070 */
  public static final int DBDATATYPE_AD_Reference_ID = 200070;
  /** Binary LOB = B */
  public static final String DBDATATYPE_BinaryLOB = "B";
  /** Character Fixed = C */
  public static final String DBDATATYPE_CharacterFixed = "C";
  /** Decimal = D */
  public static final String DBDATATYPE_Decimal = "D";
  /** Integer = I */
  public static final String DBDATATYPE_Integer = "I";
  /** Character LOB = L */
  public static final String DBDATATYPE_CharacterLOB = "L";
  /** Number = N */
  public static final String DBDATATYPE_Number = "N";
  /** Timestamp = T */
  public static final String DBDATATYPE_Timestamp = "T";
  /** Character Variable = V */
  public static final String DBDATATYPE_CharacterVariable = "V";
  /**
   * Set Database Data Type.
   *
   * @param DBDataType Database Data Type
   */
  public void setDBDataType(String DBDataType) {

    set_Value(COLUMNNAME_DBDataType, DBDataType);
  }

  /**
   * Get Database Data Type.
   *
   * @return Database Data Type
   */
  public String getDBDataType() {
    return (String) get_Value(COLUMNNAME_DBDataType);
  }

  /**
   * Set Description.
   *
   * @param Description Optional short description of the record
   */
  public void setDescription(String Description) {
    set_Value(COLUMNNAME_Description, Description);
  }

  /**
   * Get Description.
   *
   * @return Optional short description of the record
   */
  public String getDescription() {
    return (String) get_Value(COLUMNNAME_Description);
  }

  /** EntityType AD_Reference_ID=389 */
  public static final int ENTITYTYPE_AD_Reference_ID = 389;
  /**
   * Set Entity Type.
   *
   * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
   */
  public void setEntityType(String EntityType) {

    set_Value(COLUMNNAME_EntityType, EntityType);
  }

  /**
   * Get Entity Type.
   *
   * @return Dictionary Entity Type; Determines ownership and synchronization
   */
  public String getEntityType() {
    return (String) get_Value(COLUMNNAME_EntityType);
  }

  /**
   * Set Comment/Help.
   *
   * @param Help Comment or Hint
   */
  public void setHelp(String Help) {
    set_Value(COLUMNNAME_Help, Help);
  }

  /**
   * Get Comment/Help.
   *
   * @return Comment or Hint
   */
  public String getHelp() {
    return (String) get_Value(COLUMNNAME_Help);
  }

  /**
   * Set Sequence.
   *
   * @param SeqNo Method of ordering records; lowest number comes first
   */
  public void setSeqNo(int SeqNo) {
    set_Value(COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
  }

  /**
   * Get Sequence.
   *
   * @return Method of ordering records; lowest number comes first
   */
  public int getSeqNo() {
    Integer ii = (Integer) get_Value(COLUMNNAME_SeqNo);
    if (ii == null) return 0;
    return ii;
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
