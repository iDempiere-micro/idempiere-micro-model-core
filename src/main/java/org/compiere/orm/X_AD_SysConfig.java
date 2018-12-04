package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_AD_SysConfig;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_SysConfig
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_SysConfig extends BasePONameValue implements I_AD_SysConfig, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_SysConfig(Properties ctx, int AD_SysConfig_ID, String trxName) {
    super(ctx, AD_SysConfig_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_SysConfig(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  public X_AD_SysConfig(Properties ctx, Row r) {
    super(ctx, r);
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
    StringBuffer sb = new StringBuffer("X_AD_SysConfig[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Set System Configurator.
   *
   * @param AD_SysConfig_ID System Configurator
   */
  public void setAD_SysConfig_ID(int AD_SysConfig_ID) {
    if (AD_SysConfig_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_SysConfig_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_SysConfig_ID, Integer.valueOf(AD_SysConfig_ID));
  }

  /**
   * Get System Configurator.
   *
   * @return System Configurator
   */
  public int getAD_SysConfig_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_SysConfig_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set AD_SysConfig_UU.
   *
   * @param AD_SysConfig_UU AD_SysConfig_UU
   */
  public void setAD_SysConfig_UU(String AD_SysConfig_UU) {
    set_Value(COLUMNNAME_AD_SysConfig_UU, AD_SysConfig_UU);
  }

  /**
   * Get AD_SysConfig_UU.
   *
   * @return AD_SysConfig_UU
   */
  public String getAD_SysConfig_UU() {
    return (String) get_Value(COLUMNNAME_AD_SysConfig_UU);
  }

  /** ConfigurationLevel AD_Reference_ID=53222 */
  public static final int CONFIGURATIONLEVEL_AD_Reference_ID = 53222;
  /** System = S */
  public static final String CONFIGURATIONLEVEL_System = "S";
  /** Client = C */
  public static final String CONFIGURATIONLEVEL_Client = "C";
  /** Organization = O */
  public static final String CONFIGURATIONLEVEL_Organization = "O";
  /**
   * Set Configuration Level.
   *
   * @param ConfigurationLevel Configuration Level for this parameter
   */
  public void setConfigurationLevel(String ConfigurationLevel) {

    set_Value(COLUMNNAME_ConfigurationLevel, ConfigurationLevel);
  }

  /**
   * Get Configuration Level.
   *
   * @return Configuration Level for this parameter
   */
  public String getConfigurationLevel() {
    return (String) get_Value(COLUMNNAME_ConfigurationLevel);
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

  /** EntityType AD_Reference_ID=389 */
  public static final int ENTITYTYPE_AD_Reference_ID = 389;
  /**
   * Set Entity Type.
   *
   * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
   */
  public void setEntityType(String EntityType) {

    set_Value(COLUMNNAME_EntityType, EntityType);
  }

  /**
   * Get Entity Type.
   *
   * @return Dictionary Entity Type; Determines ownership and synchronization
   */
  public String getEntityType() {
    return (String) get_Value(COLUMNNAME_EntityType);
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
