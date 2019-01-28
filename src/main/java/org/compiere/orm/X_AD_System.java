package org.compiere.orm;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.I_AD_System;
import org.idempiere.common.util.Env;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_System
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_System extends BasePOName implements I_AD_System, I_Persistent {

    /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_System(Properties ctx, int AD_System_ID, String trxName) {
    super(ctx, AD_System_ID, trxName);
    /**
     * if (AD_System_ID == 0) { setAD_System_ID (0); // 0 setInfo (null); setIsAllowStatistics
     * (false); setIsAutoErrorReport (true); // Y setIsFailOnBuildDiffer (false); // N
     * setIsFailOnMissingModelValidator (true); // Y setName (null); setPassword (null);
     * setReplicationType (null); // L setSystemStatus (null); // E setUserName (null); setVersion
     * (null); }
     */
  }

  /** Load Constructor */
  public X_AD_System(Properties ctx, ResultSet rs, String trxName) {
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
    StringBuffer sb = new StringBuffer("X_AD_System[").append(getId()).append("]");
    return sb.toString();
  }

    /**
   * Get ID Range End.
   *
   * @return End if the ID Range used
   */
  public BigDecimal getIDRangeEnd() {
    BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_IDRangeEnd);
    if (bd == null) return Env.ZERO;
    return bd;
  }

    /**
   * Get Fail on Missing Model Validator.
   *
   * @return Fail on Missing Model Validator
   */
  public boolean isFailOnMissingModelValidator() {
    Object oo = get_Value(COLUMNNAME_IsFailOnMissingModelValidator);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

    /**
   * Set Internal Users.
   *
   * @param SupportUnits Number of Internal Users for iDempiere Support
   */
  public void setSupportUnits(int SupportUnits) {
    set_ValueNoCheck(COLUMNNAME_SupportUnits, Integer.valueOf(SupportUnits));
  }

    @Override
  public int getTableId() {
    return Table_ID;
  }
}
