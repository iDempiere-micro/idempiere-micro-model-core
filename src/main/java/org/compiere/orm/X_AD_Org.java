package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Org;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

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
  public X_AD_Org(Properties ctx, Row row) {
    super(ctx, row);
  } //	MOrg

  /**
   * AccessLevel
   *
   * @return 7 - System - Client - Org
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    return "X_AD_Org[" + getId() + "]";
  }

  /**
   * Get AD_Org_UU.
   *
   * @return AD_Org_UU
   */
  public String getAD_Org_UU() {
    return (String) get_Value(COLUMNNAME_AD_Org_UU);
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
   * Set Replication Strategy.
   *
   * @param AD_ReplicationStrategy_ID Data Replication Strategy
   */
  public void setADReplicationStrategyID(int AD_ReplicationStrategy_ID) {
    if (AD_ReplicationStrategy_ID < 1) set_Value(COLUMNNAME_AD_ReplicationStrategy_ID, null);
    else set_Value(COLUMNNAME_AD_ReplicationStrategy_ID, AD_ReplicationStrategy_ID);
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
   * Set Description.
   *
   * @param Description Optional short description of the record
   */
  public void setDescription(String Description) {
    set_Value(COLUMNNAME_Description, Description);
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

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
