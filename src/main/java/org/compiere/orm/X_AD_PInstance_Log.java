package org.compiere.orm;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.I_AD_PInstance_Log;
import org.idempiere.common.util.Env;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_PInstance_Log
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_PInstance_Log extends PO implements I_AD_PInstance_Log, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_PInstance_Log(Properties ctx, int AD_PInstance_Log_ID, String trxName) {
    super(ctx, AD_PInstance_Log_ID, trxName);
    /** if (AD_PInstance_Log_ID == 0) { setAD_PInstance_ID (0); setLog_ID (0); } */
  }

  /** Load Constructor */
  public X_AD_PInstance_Log(Properties ctx, ResultSet rs, String trxName) {
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
    StringBuffer sb = new StringBuffer("X_AD_PInstance_Log[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Get Process Instance.
   *
   * @return Instance of the process
   */
  public int getAD_PInstance_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_PInstance_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Process Instance.
   *
   * @param AD_PInstance_ID Instance of the process
   */
  public void setAD_PInstance_ID(int AD_PInstance_ID) {
    if (AD_PInstance_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_PInstance_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_PInstance_ID, Integer.valueOf(AD_PInstance_ID));
  }

  /**
   * Get AD_PInstance_Log_UU.
   *
   * @return AD_PInstance_Log_UU
   */
  public String getAD_PInstance_Log_UU() {
    return (String) get_Value(COLUMNNAME_AD_PInstance_Log_UU);
  }

  /**
   * Set AD_PInstance_Log_UU.
   *
   * @param AD_PInstance_Log_UU AD_PInstance_Log_UU
   */
  public void setAD_PInstance_Log_UU(String AD_PInstance_Log_UU) {
    set_Value(COLUMNNAME_AD_PInstance_Log_UU, AD_PInstance_Log_UU);
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
    if (AD_Table_ID < 1) set_Value(COLUMNNAME_AD_Table_ID, null);
    else set_Value(COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
  }

  /**
   * Get Log.
   *
   * @return Log
   */
  public int getLog_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_Log_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Log.
   *
   * @param Log_ID Log
   */
  public void setLog_ID(int Log_ID) {
    if (Log_ID < 1) set_ValueNoCheck(COLUMNNAME_Log_ID, null);
    else set_ValueNoCheck(COLUMNNAME_Log_ID, Integer.valueOf(Log_ID));
  }

  /**
   * Get Process Date.
   *
   * @return Process Parameter
   */
  public Timestamp getP_Date() {
    return (Timestamp) get_Value(COLUMNNAME_P_Date);
  }

  /**
   * Set Process Date.
   *
   * @param P_Date Process Parameter
   */
  public void setP_Date(Timestamp P_Date) {
    set_ValueNoCheck(COLUMNNAME_P_Date, P_Date);
  }

  /**
   * Get Process ID.
   *
   * @return Process ID
   */
  public int getP_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_P_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Process ID.
   *
   * @param P_ID Process ID
   */
  public void setP_ID(int P_ID) {
    if (P_ID < 1) set_ValueNoCheck(COLUMNNAME_P_ID, null);
    else set_ValueNoCheck(COLUMNNAME_P_ID, Integer.valueOf(P_ID));
  }

  /**
   * Get Process Message.
   *
   * @return Process Message
   */
  public String getP_Msg() {
    return (String) get_Value(COLUMNNAME_P_Msg);
  }

  /**
   * Set Process Message.
   *
   * @param P_Msg Process Message
   */
  public void setP_Msg(String P_Msg) {
    set_ValueNoCheck(COLUMNNAME_P_Msg, P_Msg);
  }

  /**
   * Get Process Number.
   *
   * @return Process Parameter
   */
  public BigDecimal getP_Number() {
    BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_P_Number);
    if (bd == null) return Env.ZERO;
    return bd;
  }

  /**
   * Set Process Number.
   *
   * @param P_Number Process Parameter
   */
  public void setP_Number(BigDecimal P_Number) {
    set_ValueNoCheck(COLUMNNAME_P_Number, P_Number);
  }

  /**
   * Get Record ID.
   *
   * @return Direct internal record ID
   */
  public int getRecord_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_Record_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Record ID.
   *
   * @param Record_ID Direct internal record ID
   */
  public void setRecord_ID(int Record_ID) {
    if (Record_ID < 0) set_Value(COLUMNNAME_Record_ID, null);
    else set_Value(COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
