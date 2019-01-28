package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_Ref_Table;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Ref_Table
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Ref_Table extends PO implements I_AD_Ref_Table, I_Persistent {

    /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Ref_Table(Properties ctx, int AD_Ref_Table_ID, String trxName) {
    super(ctx, AD_Ref_Table_ID, trxName);
    /**
     * if (AD_Ref_Table_ID == 0) { setAD_Display (0); setAD_Key (0); setReferenceId (0);
     * setAD_Table_ID (0); setEntityType (null); // @SQL=select
     * get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual setIsValueDisplayed (false); }
     */
  }

  /** Load Constructor */
  public X_AD_Ref_Table(Properties ctx, ResultSet rs, String trxName) {
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
    StringBuffer sb = new StringBuffer("X_AD_Ref_Table[").append(getId()).append("]");
    return sb.toString();
  }

    /**
   * Get Display column.
   *
   * @return Column that will display
   */
  public int getAD_Display() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Display);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Key column.
   *
   * @return Unique identifier of a record
   */
  public int getAD_Key() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Key);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Reference.
   *
   * @return System Reference and Validation
   */
  public int getReferenceId() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Reference_ID);
    if (ii == null) return 0;
    return ii;
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
   * Set Entity Type.
   *
   * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
   */
  public void setEntityType(String EntityType) {

    set_Value(COLUMNNAME_EntityType, EntityType);
  }

  /**
   * Set Display Value.
   *
   * @param IsValueDisplayed Displays Value column with the Display column
   */
  public void setIsValueDisplayed(boolean IsValueDisplayed) {
    set_Value(COLUMNNAME_IsValueDisplayed, Boolean.valueOf(IsValueDisplayed));
  }

    @Override
  public int getTableId() {
    return Table_ID;
  }
}
