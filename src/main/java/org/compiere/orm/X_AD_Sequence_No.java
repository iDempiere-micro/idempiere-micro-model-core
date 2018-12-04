package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_Sequence_No;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Sequence_No
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Sequence_No extends PO implements I_AD_Sequence_No, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Sequence_No(Properties ctx, int AD_Sequence_No_ID, String trxName) {
    super(ctx, AD_Sequence_No_ID, trxName);
    /**
     * if (AD_Sequence_No_ID == 0) { setAD_Sequence_ID (0); setCalendarYearMonth (null);
     * setCurrentNext (0); }
     */
  }

  /** Load Constructor */
  public X_AD_Sequence_No(Properties ctx, ResultSet rs, String trxName) {
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
    StringBuffer sb = new StringBuffer("X_AD_Sequence_No[").append(getId()).append("]");
    return sb.toString();
  }

  public org.compiere.model.I_AD_Sequence getAD_Sequence() throws RuntimeException {
    return (org.compiere.model.I_AD_Sequence)
        MTable.get(getCtx(), org.compiere.model.I_AD_Sequence.Table_Name)
            .getPO(getAD_Sequence_ID(), get_TrxName());
  }

  /**
   * Set Sequence.
   *
   * @param AD_Sequence_ID Document Sequence
   */
  public void setAD_Sequence_ID(int AD_Sequence_ID) {
    if (AD_Sequence_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Sequence_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Sequence_ID, Integer.valueOf(AD_Sequence_ID));
  }

  /**
   * Get Sequence.
   *
   * @return Document Sequence
   */
  public int getAD_Sequence_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Sequence_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set AD_Sequence_No_UU.
   *
   * @param AD_Sequence_No_UU AD_Sequence_No_UU
   */
  public void setAD_Sequence_No_UU(String AD_Sequence_No_UU) {
    set_Value(COLUMNNAME_AD_Sequence_No_UU, AD_Sequence_No_UU);
  }

  /**
   * Get AD_Sequence_No_UU.
   *
   * @return AD_Sequence_No_UU
   */
  public String getAD_Sequence_No_UU() {
    return (String) get_Value(COLUMNNAME_AD_Sequence_No_UU);
  }

  /**
   * Set YearMonth.
   *
   * @param CalendarYearMonth YYYYMM
   */
  public void setCalendarYearMonth(String CalendarYearMonth) {
    set_ValueNoCheck(COLUMNNAME_CalendarYearMonth, CalendarYearMonth);
  }

  /**
   * Get YearMonth.
   *
   * @return YYYYMM
   */
  public String getCalendarYearMonth() {
    return (String) get_Value(COLUMNNAME_CalendarYearMonth);
  }

  /**
   * Set Current Next.
   *
   * @param CurrentNext The next number to be used
   */
  public void setCurrentNext(int CurrentNext) {
    set_Value(COLUMNNAME_CurrentNext, Integer.valueOf(CurrentNext));
  }

  /**
   * Get Current Next.
   *
   * @return The next number to be used
   */
  public int getCurrentNext() {
    Integer ii = (Integer) get_Value(COLUMNNAME_CurrentNext);
    if (ii == null) return 0;
    return ii;
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
