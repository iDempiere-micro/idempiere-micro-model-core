package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_TableIndex;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_TableIndex
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_TableIndex extends BasePOName implements I_AD_TableIndex, I_Persistent {

    /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_TableIndex(Properties ctx, int AD_TableIndex_ID, String trxName) {
    super(ctx, AD_TableIndex_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_TableIndex(Properties ctx, ResultSet rs, String trxName) {
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
    StringBuffer sb = new StringBuffer("X_AD_TableIndex[").append(getId()).append("]");
    return sb.toString();
  }

    public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException {
    return (org.compiere.model.I_AD_Table)
        MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
            .getPO(getAD_Table_ID(), null);
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
   * Get Table Index.
   *
   * @return Table Index
   */
  public int getAD_TableIndex_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_TableIndex_ID);
    if (ii == null) return 0;
    return ii;
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
   * Set Create Constraint.
   *
   * @param IsCreateConstraint Create Constraint
   */
  public void setIsCreateConstraint(boolean IsCreateConstraint) {
    set_Value(COLUMNNAME_IsCreateConstraint, Boolean.valueOf(IsCreateConstraint));
  }

  /**
   * Get Create Constraint.
   *
   * @return Create Constraint
   */
  public boolean isCreateConstraint() {
    Object oo = get_Value(COLUMNNAME_IsCreateConstraint);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

    /**
   * Get Key column.
   *
   * @return This column is the key in this table
   */
  public boolean isKey() {
    Object oo = get_Value(COLUMNNAME_IsKey);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Unique.
   *
   * @param IsUnique Unique
   */
  public void setIsUnique(boolean IsUnique) {
    set_Value(COLUMNNAME_IsUnique, Boolean.valueOf(IsUnique));
  }

  /**
   * Get Unique.
   *
   * @return Unique
   */
  public boolean isUnique() {
    Object oo = get_Value(COLUMNNAME_IsUnique);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

    @Override
  public int getTableId() {
    return Table_ID;
  }
}
