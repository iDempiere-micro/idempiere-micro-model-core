package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_SysConfig;

/**
 * Generated Model for AD_SysConfig
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_SysConfig extends BasePONameValue implements I_AD_SysConfig {

    /**
     * System = S
     */
    public static final String CONFIGURATIONLEVEL_System = "S";
    /**
     * Client = C
     */
    public static final String CONFIGURATIONLEVEL_Client = "C";
    /**
     * Organization = O
     */
    public static final String CONFIGURATIONLEVEL_Organization = "O";
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_SysConfig(int AD_SysConfig_ID) {
        super(AD_SysConfig_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_SysConfig(Row r) {
        super(r);
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
     * Get Configuration Level.
     *
     * @return Configuration Level for this parameter
     */
    public String getConfigurationLevel() {
        return (String) getValue(COLUMNNAME_ConfigurationLevel);
    }

    /**
     * Set Configuration Level.
     *
     * @param ConfigurationLevel Configuration Level for this parameter
     */
    public void setConfigurationLevel(String ConfigurationLevel) {

        setValue(COLUMNNAME_ConfigurationLevel, ConfigurationLevel);
    }

    /**
     * Get Entity Type.
     *
     * @return Dictionary Entity Type; Determines ownership and synchronization
     */
    public String getEntityType() {
        return (String) getValue(COLUMNNAME_EntityType);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
