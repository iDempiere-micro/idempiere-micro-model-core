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

    /** Character Fixed = C */
  public static final String DBDATATYPE_CharacterFixed = "C";
  /** Decimal = D */
  public static final String DBDATATYPE_Decimal = "D";
  /** Integer = I */
  public static final String DBDATATYPE_Integer = "I";
    /** Number = N */
  public static final String DBDATATYPE_Number = "N";
  /** Timestamp = T */
  public static final String DBDATATYPE_Timestamp = "T";
  /** Character Variable = V */
  public static final String DBDATATYPE_CharacterVariable = "V";
    /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_ViewColumn(Properties ctx, int AD_ViewColumn_ID) {
    super(ctx, AD_ViewColumn_ID);
    /**
     * if (AD_ViewColumn_ID == 0) { setAD_ViewColumn_ID (0); setAD_ViewComponent_ID (0);
     * setColumnName (null); setEntityType (null); // @SQL=select
     * get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual }
     */
  }

  /** Load Constructor */
  public X_AD_ViewColumn(Properties ctx, ResultSet rs) {
    super(ctx, rs);
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
   * Set Database View Component.
   *
   * @param AD_ViewComponent_ID Database View Component
   */
  public void setAD_ViewComponent_ID(int AD_ViewComponent_ID) {
    if (AD_ViewComponent_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_ViewComponent_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_ViewComponent_ID, Integer.valueOf(AD_ViewComponent_ID));
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
   * Get Column SQL.
   *
   * @return Virtual Column (r/o)
   */
  public String getColumnSQL() {
    return (String) get_Value(COLUMNNAME_ColumnSQL);
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
   * Set Entity Type.
   *
   * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
   */
  public void setEntityType(String EntityType) {

    set_Value(COLUMNNAME_EntityType, EntityType);
  }

    @Override
  public int getTableId() {
    return Table_ID;
  }
}
