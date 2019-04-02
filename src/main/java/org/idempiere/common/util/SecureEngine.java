package org.idempiere.common.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

/**
 * Security Engine
 *
 * @author Jorg Janke
 * @version $Id: SecureEngine.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class SecureEngine {
    /**
     * Test String
     */
    private static final String TEST = "This is a 0123456789 .,; -= Test!";
    /**
     * Secure Engine
     */
    private static volatile SecureEngine s_engine = null;
    /**
     * Logger
     */
    private static CLogger log = CLogger.getCLogger(SecureEngine.class.getName());
    /**
     * The real Engine
     */
    private SecureInterface implementation = null;

    /**
     * SecureEngine constructor
     *
     * @param className class name if null defaults to org.idempiere.common.util.Secure
     */
    private SecureEngine(String className) {
        String realClass = className;
        if (realClass == null || realClass.length() == 0)
            realClass = SecureInterface.ADEMPIERE_SECURE_DEFAULT;
        Exception cause = null;
        try {
            Class<?> clazz = Class.forName(realClass);
            implementation = (SecureInterface) clazz.newInstance();
        } catch (Exception e) {
            cause = e;
        }
        if (implementation == null) {
            String msg =
                    "Could not initialize: "
                            + realClass
                            + " - "
                            + cause.toString()
                            + "\nCheck start script parameter ADEMPIERE_SECURE";
            log.severe(msg);
            System.err.println(msg);
        }
        //	See if it works
        String testE = implementation.encrypt(TEST, 0);
        String testC = implementation.decrypt(testE, 0);
        if (!testC.equals(TEST))
            throw new IllegalStateException(realClass + ": " + TEST + "->" + testE + "->" + testC);
        if (log.isLoggable(Level.CONFIG)) log.config(realClass + " initialized - " + implementation);
    } //	SecureEngine

    /**
     * Initialize Security
     */
    public static void init() {
        if (s_engine == null) {
            String className = SecureInterface.ADEMPIERE_SECURE_DEFAULT;
            s_engine = new SecureEngine(className);
        }
    } //	init

    /**
     * Convert String and salt to SHA-512 hash with iterations
     * https://www.owasp.org/index.php/Hashing_Java
     *
     * @param value message
     * @return HexString of message (length = 128 characters)
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String getSHA512Hash(int iterations, String value, byte[] salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (s_engine == null) init();
        return s_engine.implementation.getSHA512Hash(iterations, value, salt);
    } //	getDigest

    /**
     * Encryption. The methods must recognize clear text values
     *
     * @param value        clear value
     * @param AD_Client_ID
     * @return encrypted String
     */
    public static String encrypt(String value, int AD_Client_ID) {
        if (value == null || value.length() == 0) return value;
        if (s_engine == null) init();
        //
        boolean inQuotes = value.startsWith("'") && value.endsWith("'");
        if (inQuotes) value = value.substring(1, value.length() - 1);
        //
        String retValue = s_engine.implementation.encrypt(value, AD_Client_ID);
        if (inQuotes) return "'" + retValue + "'";
        return retValue;
    } //	encrypt

    /**
     * Decryption. The methods must recognize clear text values
     *
     * @param value        encrypted value
     * @param AD_Client_ID
     * @return decrypted String
     */
    public static String decrypt(String value, int AD_Client_ID) {
        if (value == null) return null;
        if (s_engine == null) init();
        boolean inQuotes = value.startsWith("'") && value.endsWith("'");
        if (inQuotes) value = value.substring(1, value.length() - 1);
        String retValue = null;
        if (value.startsWith(SecureInterface.CLEARVALUE_START)
                && value.endsWith(SecureInterface.CLEARVALUE_END))
            retValue =
                    value.substring(
                            SecureInterface.CLEARVALUE_START.length(),
                            value.length() - SecureInterface.CLEARVALUE_END.length());
        else retValue = s_engine.implementation.decrypt(value, AD_Client_ID);
        if (inQuotes) return "'" + retValue + "'";
        return retValue;
    } //	decrypt

    /**
     * Encryption. The methods must recognize clear values
     *
     * @param value        clear value
     * @param AD_Client_ID
     * @return encrypted String
     */
    public static Object encrypt(Object value, int AD_Client_ID) {
        if (value instanceof String) return encrypt((String) value, AD_Client_ID);
        return value;
    } //	encrypt

    /**
     * Decryption. The methods must recognize clear values
     *
     * @param value encrypted value
     * @return decrypted String
     */
    public static Object decrypt(Object value, int AD_Client_ID) {
        if (value instanceof String) return decrypt((String) value, AD_Client_ID);
        return value;
    } //	decrypt

    /**
     * use salt in hex form and text hashed compare with plan text when has exception in hash, log to
     * server
     *
     * @param hashedText
     * @param hexSalt
     * @param planText
     * @return
     */
    public static boolean isMatchHash(String hashedText, String hexSalt, String planText) {
        boolean valid = false;

        // always do calculation to prevent timing based attacks
        if (hashedText == null) hashedText = "0000000000000000";
        if (hexSalt == null) hexSalt = "0000000000000000";

        try {
            valid =
                    SecureEngine.getSHA512Hash(1000, planText, Secure.convertHexString(hexSalt))
                            .equals(hashedText);
        } catch (NoSuchAlgorithmException ignored) {
            log.log(Level.WARNING, "Password hashing not supported by JVM");
        } catch (UnsupportedEncodingException ignored) {
            log.log(Level.WARNING, "Password hashing not supported by JVM");
        }

        return valid;
    }

} //	SecureEngine
