package org.compiere.orm;

import kotliquery.Row;
import org.compiere.util.DisplayType;
import org.idempiere.common.util.CLogger;
import software.hsharp.core.orm.MBaseSysConfig;
import software.hsharp.core.orm.MBaseSysConfigKt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.prepareStatement;

/**
 * System Configuration
 *
 * @author Armen Rizal
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * <li>BF [ 1885496 ] Performance NEEDS
 * @version $Id: MSysConfig.java,v 1.5 2005/11/28 11:56:45 armen Exp $ Contributor: Carlos Ruiz -
 * globalqss - [ 1800371 ] System Configurator Enhancements
 */
public class MSysConfig extends MBaseSysConfig {
    public static final String ALLOW_OVER_APPLIED_PAYMENT = "ALLOW_OVER_APPLIED_PAYMENT";
    public static final String CASH_AS_PAYMENT = "CASH_AS_PAYMENT";
    public static final String CHECK_CREDIT_ON_CASH_POS_ORDER = "CHECK_CREDIT_ON_CASH_POS_ORDER";
    public static final String CHECK_CREDIT_ON_PREPAY_ORDER = "CHECK_CREDIT_ON_PREPAY_ORDER";
    public static final String CLIENT_ACCOUNTING = "CLIENT_ACCOUNTING";
    public static final String DEFAULT_COA_PATH = "DEFAULT_COA_PATH";
    public static final String DICTIONARY_ID_COMMENTS = "DICTIONARY_ID_COMMENTS";
    public static final String DICTIONARY_ID_USE_CENTRALIZED_ID = "DICTIONARY_ID_USE_CENTRALIZED_ID";
    public static final String IBAN_VALIDATION = "IBAN_VALIDATION";
    public static final String IDENTIFIER_SEPARATOR = "IDENTIFIER_SEPARATOR";
    public static final String Invoice_ReverseUseNewNumber = "Invoice_ReverseUseNewNumber";
    public static final String LOCATION_MAPS_DESTINATION_ADDRESS =
            "LOCATION_MAPS_DESTINATION_ADDRESS";
    public static final String LOCATION_MAPS_ROUTE_PREFIX = "LOCATION_MAPS_ROUTE_PREFIX";
    public static final String LOCATION_MAPS_SOURCE_ADDRESS = "LOCATION_MAPS_SOURCE_ADDRESS";
    public static final String LOCATION_MAPS_URL_PREFIX = "LOCATION_MAPS_URL_PREFIX";
    public static final String MAIL_SEND_CREDENTIALS = "MAIL_SEND_CREDENTIALS";
    public static final String MFG_ValidateCostsDifferenceOnCreate =
            "MFG_ValidateCostsDifferenceOnCreate";
    public static final String MFG_ValidateCostsOnCreate = "MFG_ValidateCostsOnCreate";
    public static final String PAYMENT_OVERWRITE_DOCUMENTNO_WITH_CHECK_ON_PAYMENT =
            "PAYMENT_OVERWRITE_DOCUMENTNO_WITH_CHECK_ON_PAYMENT";
    public static final String PAYMENT_OVERWRITE_DOCUMENTNO_WITH_CHECK_ON_RECEIPT =
            "PAYMENT_OVERWRITE_DOCUMENTNO_WITH_CHECK_ON_RECEIPT";
    public static final String PAYMENT_OVERWRITE_DOCUMENTNO_WITH_CREDIT_CARD =
            "PAYMENT_OVERWRITE_DOCUMENTNO_WITH_CREDIT_CARD";
    public static final String ProductUOMConversionRateValidate = "ProductUOMConversionRateValidate";
    public static final String ProductUOMConversionUOMValidate = "ProductUOMConversionUOMValidate";
    public static final String PROJECT_ID_COMMENTS = "PROJECT_ID_COMMENTS";
    public static final String PROJECT_ID_USE_CENTRALIZED_ID = "PROJECT_ID_USE_CENTRALIZED_ID";
    public static final String REAL_TIME_POS = "REAL_TIME_POS";
    public static final String SHIPPING_DEFAULT_WEIGHT_PER_PACKAGE =
            "SHIPPING_DEFAULT_WEIGHT_PER_PACKAGE";
    public static final String START_VALUE_BPLOCATION_NAME = "START_VALUE_BPLOCATION_NAME";
    public static final String SYSTEM_NATIVE_SEQUENCE = "SYSTEM_NATIVE_SEQUENCE";
    public static final String USE_EMAIL_FOR_LOGIN = "USE_EMAIL_FOR_LOGIN";
    public static final String USER_PASSWORD_HASH = "USER_PASSWORD_HASH";
    public static final String VALIDATE_MATCHING_TO_ORDERED_QTY = "VALIDATE_MATCHING_TO_ORDERED_QTY";
    public static final String ZK_DASHBOARD_PERFORMANCE_REFRESH_INTERVAL =
            "ZK_DASHBOARD_PERFORMANCE_REFRESH_INTERVAL";
    /**
     *
     */
    private static final long serialVersionUID = 2617379167881737860L;
    /**
     * Static Logger
     */
    private static CLogger s_log = CLogger.getCLogger(MSysConfig.class);

    private static int lendate = DisplayType.DEFAULT_DATE_FORMAT.length();
    private static int lentime = DisplayType.DEFAULT_TIME_FORMAT.length();
    private static int lentimestamp = DisplayType.DEFAULT_TIMESTAMP_FORMAT.length();

    /**
     * Standard Constructor
     *
     * @param AD_SysConfig_ID id
     */
    public MSysConfig(int AD_SysConfig_ID) {
        super(AD_SysConfig_ID);
    } //	MSysConfig

    /**
     * Load Constructor
     */
    public MSysConfig(Row row) {
        super(row);
    } //	MSysConfig

    /**
     * Get system configuration property of type string
     *
     * @param Name
     * @param defaultValue
     * @return String
     */
    public static String getConfigValue(String Name, String defaultValue) {
        return getConfigValue(Name, defaultValue, 0, 0);
    }

    /**
     * Get system configuration property of type string
     *
     * @param Name
     * @return String
     */
    public static String getConfigValue(String Name) {
        return getConfigValue(Name, null);
    }

    /**
     * Get system configuration property of type double
     *
     * @param Name
     * @param defaultValue
     * @return double
     */
    public static double getDoubleValue(String Name, double defaultValue) {
        String s = getConfigValue(Name);
        if (s == null || s.length() == 0) return defaultValue;
        //
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            s_log.log(Level.SEVERE, "getDoubleValue (" + Name + ") = " + s, e);
        }
        return defaultValue;
    }

    /**
     * Get system configuration property of type boolean
     *
     * @param Name
     * @param defaultValue
     * @return boolean
     */
    public static boolean getBooleanValue(String Name, boolean defaultValue) {
        String s = getConfigValue(Name);
        if (s == null || s.length() == 0) return defaultValue;

        if ("Y".equalsIgnoreCase(s)) return true;
        else if ("N".equalsIgnoreCase(s)) return false;
        else return Boolean.valueOf(s).booleanValue();
    }

    /**
     * Get client configuration property of type string
     *
     * @param Name
     * @param defaultValue
     * @return String
     */
    public static String getConfigValue(String Name, String defaultValue, int AD_Client_ID) {
        return getConfigValue(Name, defaultValue, AD_Client_ID, 0);
    }

    /**
     * Get system configuration property of type string
     *
     * @param Name
     * @return String
     */
    public static String getConfigValue(String Name, int AD_Client_ID) {
        return (getConfigValue(Name, null, AD_Client_ID));
    }

    /**
     * Get system configuration property of type int
     *
     * @param Name
     * @param defaultValue
     * @return int
     */
    public static int getIntValue(String Name, int defaultValue, int AD_Client_ID) {
        String s = getConfigValue(Name, AD_Client_ID);
        if (s == null) return defaultValue;

        if (s.length() == 0) return defaultValue;
        //
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            s_log.log(Level.SEVERE, "getIntValue (" + Name + ") = " + s, e);
        }
        return defaultValue;
    }

    /**
     * Get system configuration property of type boolean
     *
     * @param Name
     * @param defaultValue
     * @return boolean
     */
    public static boolean getBooleanValue(String Name, boolean defaultValue, int AD_Client_ID) {
        String s = getConfigValue(Name, AD_Client_ID);
        if (s == null || s.length() == 0) return defaultValue;

        if ("Y".equalsIgnoreCase(s)) return true;
        else if ("N".equalsIgnoreCase(s)) return false;
        else return Boolean.valueOf(s);
    }

    /**
     * Get client configuration property of type string
     *
     * @param Name
     * @param defaultValue
     * @return String
     */
    public static String getConfigValue(String Name, String defaultValue, int AD_Client_ID, int AD_Org_ID) {
        return MBaseSysConfigKt.getValue(Name, defaultValue, AD_Client_ID, AD_Org_ID);
    }

    /**
     * Get system configuration property of type string
     *
     * @param Name
     * @return String
     */
    public static String getConfigValue(String Name, int AD_Client_ID, int AD_Org_ID) {
        return getConfigValue(Name, null, AD_Client_ID, AD_Org_ID);
    }

    /**
     * Get system configuration property of type int
     *
     * @param Name
     * @param defaultValue
     * @return int
     */
    public static int getIntValue(String Name, int defaultValue, int AD_Client_ID, int AD_Org_ID) {
        String s = getConfigValue(Name, AD_Client_ID, AD_Org_ID);
        if (s == null) return defaultValue;

        if (s.length() == 0) return defaultValue;
        //
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            s_log.log(Level.SEVERE, "getIntValue (" + Name + ") = " + s, e);
        }
        return defaultValue;
    }

    /**
     * Get system configuration property of type boolean
     *
     * @param Name
     * @param defaultValue
     * @return boolean
     */
    public static boolean getBooleanValue(
            String Name, boolean defaultValue, int AD_Client_ID, int AD_Org_ID) {
        String s = getConfigValue(Name, AD_Client_ID, AD_Org_ID);
        if (s == null || s.length() == 0) return defaultValue;

        if ("Y".equalsIgnoreCase(s)) return true;
        else if ("N".equalsIgnoreCase(s)) return false;
        else return Boolean.valueOf(s);
    }

    /**
     * Get system configuration property of type Timestamp
     *
     * @param Name
     * @param defaultValue
     * @return Timestamp
     */
    public static Timestamp getTimestampValue(String Name, Timestamp defaultValue, int AD_Client_ID) {
        return getTimestampValue(Name, defaultValue, AD_Client_ID, 0);
    }

    /**
     * Get system configuration property of type Timestamp
     *
     * @param Name
     * @param defaultValue
     * @return Timestamp
     */
    public static Timestamp getTimestampValue(
            String Name, Timestamp defaultValue, int AD_Client_ID, int AD_Org_ID) {
        String text = getConfigValue(Name, null, AD_Client_ID, AD_Org_ID);
        if (text != null) return convertStringToTimestamp(text);

        return defaultValue;
    }

    /**
     * convert a string to a timestamp
     */
    private static Timestamp convertStringToTimestamp(String text) {
        SimpleDateFormat sdf = null;
        int lentext = text.length();
        if (lentext == lendate) {
            sdf = DisplayType.getDateFormatInJDBC();
        } else if (lentext == lentime) {
            sdf = DisplayType.getDefaultTimeFormat();
        } else if (lentext == lentimestamp) {
            sdf = DisplayType.getDefaultTimestampFormat();
        } else {
            s_log.warning("Cannot convert to a valid timestamp (invalid length): " + text);
        }

        Timestamp dt = null;
        if (sdf != null) {
            try {
                Date date = sdf.parse(text);
                dt = new Timestamp(date.getTime());
            } catch (ParseException e) {
                s_log.warning("Cannot convert to a valid timestamp: " + text);
            }
        }
        return dt;
    }

    /**
     * ************************************************************************ Before Save
     *
     * @param newRecord
     * @return true if save
     */
    protected boolean beforeSave(boolean newRecord) {
        if (log.isLoggable(Level.FINE)) log.fine("New=" + newRecord);

        if (getClientId() != 0 || getOrgId() != 0) {

            // Get the configuration level from the System Record
            String configLevel = null;
            String sql =
                    "SELECT ConfigurationLevel FROM AD_SysConfig WHERE Name=? AND AD_Client_ID = 0 AND AD_Org_ID = 0";
            PreparedStatement pstmt;
            ResultSet rs;
            try {
                pstmt = prepareStatement(sql);
                pstmt.setString(1, getName());
                rs = pstmt.executeQuery();
                if (rs.next()) configLevel = rs.getString(1);
            } catch (SQLException e) {
                s_log.log(Level.SEVERE, "getValue", e);
            }

            // not found for system
            // if saving an org parameter - look config in client
            if (configLevel == null && getOrgId() != 0) {
                // Get the configuration level from the System Record
                sql =
                        "SELECT ConfigurationLevel FROM AD_SysConfig WHERE Name=? AND AD_Client_ID = ? AND AD_Org_ID = 0";
                try {
                    pstmt = prepareStatement(sql);
                    pstmt.setString(1, getName());
                    pstmt.setInt(2, getClientId());
                    rs = pstmt.executeQuery();
                    if (rs.next()) configLevel = rs.getString(1);
                } catch (SQLException e) {
                    s_log.log(Level.SEVERE, "getValue", e);
                }
            }

            if (configLevel != null) {

                setConfigurationLevel(configLevel);

                // Disallow saving org parameter if the system parameter is marked as 'S' or 'C'
                if (getOrgId() != 0
                        && (configLevel.equals(MSysConfig.CONFIGURATIONLEVEL_System)
                        || configLevel.equals(MSysConfig.CONFIGURATIONLEVEL_Client))) {
                    log.saveError(
                            "Can't Save Org Level",
                            "This is a system or client parameter, you can't save it as organization parameter");
                    return false;
                }

                // Disallow saving client parameter if the system parameter is marked as 'S'
                if (getClientId() != 0 && configLevel.equals(MSysConfig.CONFIGURATIONLEVEL_System)) {
                    log.saveError(
                            "Can't Save Client Level",
                            "This is a system parameter, you can't save it as client parameter");
                    return false;
                }

            } else {

                // fix possible wrong config level
                if (getOrgId() != 0) setConfigurationLevel(X_AD_SysConfig.CONFIGURATIONLEVEL_Organization);
                else if (getClientId() != 0
                        && getConfigurationLevel().equals(MSysConfig.CONFIGURATIONLEVEL_System))
                    setConfigurationLevel(X_AD_SysConfig.CONFIGURATIONLEVEL_Client);
            }
        }

        return true;
    } //	beforeSave

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "["
                + getId()
                + ", "
                + getName()
                + "="
                + getSearchKey()
                + ", ConfigurationLevel="
                + getConfigurationLevel()
                + ", Client|Org="
                + getClientId()
                + "|"
                + getOrgId()
                + ", EntityType="
                + getEntityType()
                + "]";
    }
} //	MSysConfig;
