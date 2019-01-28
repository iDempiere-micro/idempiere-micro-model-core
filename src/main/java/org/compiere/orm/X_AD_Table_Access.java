package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_AD_Table_Access;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Table_Access
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Table_Access extends PO implements I_AD_Table_Access, I_Persistent {

    /** Accessing = A */
  public static final String ACCESSTYPERULE_Accessing = "A";
  /** Reporting = R */
  public static final String ACCESSTYPERULE_Reporting = "R";
  /** Exporting = E */
  public static final String ACCESSTYPERULE_Exporting = "E";
  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Table_Access(Properties ctx, int AD_Table_Access_ID, String trxName) {
    super(ctx, AD_Table_Access_ID, trxName);
    /**
     * if (AD_Table_Access_ID == 0) { setAccessTypeRule (null); // A setAD_Role_ID (0);
     * setAD_Table_ID (0); setIsExclude (true); // Y setIsReadOnly (false); }
     */
  }

  /** Load Constructor */
  public X_AD_Table_Access(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  public X_AD_Table_Access(Properties ctx, Row row) {
    super(ctx, row);
  } //	MTableAccess

  /**
   * AccessLevel
   *
   * @return 6 - System - Client
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("X_AD_Table_Access[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Get Access Type.
   *
   * @return The type of access for this rule
   */
  public String getAccessTypeRule() {
    return (String) get_Value(COLUMNNAME_AccessTypeRule);
  }

    /**
   * Get Role.
   *
   * @return Responsibility Role
   */
  public int getAD_Role_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Role_ID);
    if (ii == null) return 0;
    return ii;
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
   * Set Exclude.
   *
   * @param IsExclude Exclude access to the data - if not selected Include access to the data
   */
  public void setIsExclude(boolean IsExclude) {
    set_Value(COLUMNNAME_IsExclude, Boolean.valueOf(IsExclude));
  }

  /**
   * Get Exclude.
   *
   * @return Exclude access to the data - if not selected Include access to the data
   */
  public boolean isExclude() {
    Object oo = get_Value(COLUMNNAME_IsExclude);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

    /**
   * Get Read Only.
   *
   * @return Field is read only
   */
  public boolean isReadOnly() {
    Object oo = get_Value(COLUMNNAME_IsReadOnly);
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
