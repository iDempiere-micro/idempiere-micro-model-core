package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_Org;
import org.idempiere.orm.I_Persistent;
import org.idempiere.orm.POInfo;

/**
 * Generated Model for AD_Org
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Org extends BasePONameValue implements I_AD_Org, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Org(Properties ctx, int AD_Org_ID, String trxName) {
    super(ctx, AD_Org_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_Org(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * AccessLevel
   *
   * @return 7 - System - Client - Org
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  /** Load Meta Data */
  protected POInfo initPO(Properties ctx) {
    return POInfo.getPOInfo(ctx, Table_ID, get_TrxName());
  }

  public String toString() {
    return "X_AD_Org[" + getId() + "]";
  }

  /**
   * Set AD_Org_UU.
   *
   * @param AD_Org_UU AD_Org_UU
   */
  public void setAD_Org_UU(String AD_Org_UU) {
    set_Value(COLUMNNAME_AD_Org_UU, AD_Org_UU);
  }

  /**
   * Get AD_Org_UU.
   *
   * @return AD_Org_UU
   */
  public String getAD_Org_UU() {
    return (String) get_Value(COLUMNNAME_AD_Org_UU);
  }

  public org.compiere.model.I_AD_ReplicationStrategy getADReplicationStrategy()
      throws RuntimeException {
    return (org.compiere.model.I_AD_ReplicationStrategy)
        MTable.get(getCtx(), org.compiere.model.I_AD_ReplicationStrategy.Table_Name)
            .getPO(getADReplicationStrategyID(), get_TrxName());
  }

  /**
   * Set Replication Strategy.
   *
   * @param AD_ReplicationStrategy_ID Data Replication Strategy
   */
  public void setADReplicationStrategyID(int AD_ReplicationStrategy_ID) {
    if (AD_ReplicationStrategy_ID < 1) set_Value(COLUMNNAME_AD_ReplicationStrategy_ID, null);
    else set_Value(COLUMNNAME_AD_ReplicationStrategy_ID, AD_ReplicationStrategy_ID);
  }

  /**
   * Get Replication Strategy.
   *
   * @return Data Replication Strategy
   */
  public int getADReplicationStrategyID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_ReplicationStrategy_ID);
    if (ii == null) return 0;
    return ii;
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

  /**
   * Set Summary Level.
   *
   * @param IsSummary This is a summary entity
   */
  public void setIsSummary(boolean IsSummary) {
    set_Value(COLUMNNAME_IsSummary, IsSummary);
  }

  /**
   * Get Summary Level.
   *
   * @return This is a summary entity
   */
  public boolean isSummary() {
    Object oo = get_Value(COLUMNNAME_IsSummary);
    if (oo != null) {
      if (oo instanceof Boolean) return (Boolean) oo;
      return "Y".equals(oo);
    }
    return false;
  }
}
