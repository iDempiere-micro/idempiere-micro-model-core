package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.HasName;
import org.compiere.model.I_AD_ViewComponent;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_ViewComponent
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_ViewComponent extends PO implements I_AD_ViewComponent, I_Persistent {

    /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_ViewComponent(Properties ctx, int AD_ViewComponent_ID) {
    super(ctx, AD_ViewComponent_ID);
    /**
     * if (AD_ViewComponent_ID == 0) { setAD_Table_ID (0); setAD_ViewComponent_ID (0); setEntityType
     * (null); // @SQL=select get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual setFromClause
     * (null); setName (null); }
     */
  }

  /** Load Constructor */
  public X_AD_ViewComponent(Properties ctx, ResultSet rs) {
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
    StringBuffer sb = new StringBuffer("X_AD_ViewComponent[").append(getId()).append("]");
    return sb.toString();
  }

    /**
   * Get Table.
   *
   * @return Database Table information
   */
  public int getAD_Table_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Table_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Table.
   *
   * @param AD_Table_ID Database Table information
   */
  public void setAD_Table_ID(int AD_Table_ID) {
    if (AD_Table_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Table_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
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
   * Get Entity Type.
   *
   * @return Dictionary Entity Type; Determines ownership and synchronization
   */
  public String getEntityType() {
    return (String) get_Value(COLUMNNAME_EntityType);
  }

  /**
   * Set Entity Type.
   *
   * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
   */
  public void setEntityType(String EntityType) {

    set_Value(COLUMNNAME_EntityType, EntityType);
  }

  /**
   * Get Sql FROM.
   *
   * @return SQL FROM clause
   */
  public String getFromClause() {
    return (String) get_Value(COLUMNNAME_FromClause);
  }

    /**
   * Get Name.
   *
   * @return Alphanumeric identifier of the entity
   */
  public String getName() {
    return (String) get_Value(HasName.Companion.getCOLUMNNAME_Name());
  }

    /**
   * Get Other SQL Clause.
   *
   * @return Other SQL Clause
   */
  public String getOtherClause() {
    return (String) get_Value(COLUMNNAME_OtherClause);
  }

    /**
   * Get Referenced Table.
   *
   * @return Referenced Table
   */
  public int getReferenced_Table_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_Referenced_Table_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Sql WHERE.
   *
   * @return Fully qualified SQL WHERE clause
   */
  public String getWhereClause() {
    return (String) get_Value(COLUMNNAME_WhereClause);
  }

    @Override
  public int getTableId() {
    return Table_ID;
  }
}
