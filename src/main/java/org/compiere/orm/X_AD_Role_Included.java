package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_Role_Included;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Role_Included
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Role_Included extends PO implements I_AD_Role_Included, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Role_Included(Properties ctx, int AD_Role_Included_ID, String trxName) {
    super(ctx, AD_Role_Included_ID, trxName);
    /**
     * if (AD_Role_Included_ID == 0) { setAD_Role_ID (0); setIncluded_Role_ID (0); setSeqNo (0);
     * // @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_Role_Included WHERE
     * AD_Role_ID=@AD_Role_ID@ }
     */
  }

  /** Load Constructor */
  public X_AD_Role_Included(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * AccessLevel
   *
   * @return 6 - System - Client
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("X_AD_Role_Included[").append(getId()).append("]");
    return sb.toString();
  }

  public org.compiere.model.I_AD_Role getAD_Role() throws RuntimeException {
    return (org.compiere.model.I_AD_Role)
        MTable.get(getCtx(), org.compiere.model.I_AD_Role.Table_Name)
            .getPO(getAD_Role_ID(), get_TrxName());
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
   * Set Role.
   *
   * @param AD_Role_ID Responsibility Role
   */
  public void setAD_Role_ID(int AD_Role_ID) {
    if (AD_Role_ID < 0) set_ValueNoCheck(COLUMNNAME_AD_Role_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
  }

  /**
   * Get AD_Role_Included_UU.
   *
   * @return AD_Role_Included_UU
   */
  public String getAD_Role_Included_UU() {
    return (String) get_Value(COLUMNNAME_AD_Role_Included_UU);
  }

  /**
   * Set AD_Role_Included_UU.
   *
   * @param AD_Role_Included_UU AD_Role_Included_UU
   */
  public void setAD_Role_Included_UU(String AD_Role_Included_UU) {
    set_Value(COLUMNNAME_AD_Role_Included_UU, AD_Role_Included_UU);
  }

  public org.compiere.model.I_AD_Role getIncluded_Role() throws RuntimeException {
    return (org.compiere.model.I_AD_Role)
        MTable.get(getCtx(), org.compiere.model.I_AD_Role.Table_Name)
            .getPO(getIncluded_Role_ID(), get_TrxName());
  }

  /**
   * Get Included Role.
   *
   * @return Included Role
   */
  public int getIncluded_Role_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_Included_Role_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Included Role.
   *
   * @param Included_Role_ID Included Role
   */
  public void setIncluded_Role_ID(int Included_Role_ID) {
    if (Included_Role_ID < 1) set_ValueNoCheck(COLUMNNAME_Included_Role_ID, null);
    else set_ValueNoCheck(COLUMNNAME_Included_Role_ID, Integer.valueOf(Included_Role_ID));
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

  /**
   * Set Sequence.
   *
   * @param SeqNo Method of ordering records; lowest number comes first
   */
  public void setSeqNo(int SeqNo) {
    set_Value(COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
