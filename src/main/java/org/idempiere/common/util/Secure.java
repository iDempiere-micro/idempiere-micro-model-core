package org.idempiere.common.util;

import org.idempiere.icommon.base.IKeyStore;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

/**
 * Security Services.
 *
 * <p>Change log:
 *
 * <ul>
 * <li>2007-01-27 - teo_sarca - [ 1598095 ] class Secure is not working with UTF8
 * </ul>
 *
 * @author Jorg Janke
 * @version $Id: Secure.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class Secure implements SecureInterface {
    /**
     * Logger
     */
    private static CLogger log = CLogger.getCLogger(Secure.class.getName());
    /**
     * Message Digest
     */
    private MessageDigest m_md = null;

    private IKeyStore m_keyStore = null;

    /**
     * ******************************************************************* Adempiere Security
     */
    public Secure() {
        initCipher();
    } //	Secure

    /**
     * ************************************************************************ Convert Byte Array to
     * Hex String
     *
     * @param bytes bytes
     * @return HexString
     */
    public static String convertToHexString(byte[] bytes) {
        //	see also Util.toHex
        int size = bytes.length;
        StringBuilder buffer = new StringBuilder(size * 2);
        for (int i = 0; i < size; i++) {
            // convert byte to an int
            int x = bytes[i];
            // account for int being a signed type and byte being unsigned
            if (x < 0) x += 256;
            String tmp = Integer.toHexString(x);
            // pad out "1" to "01" etc.
            if (tmp.length() == 1) buffer.append("0");
            buffer.append(tmp);
        }
        return buffer.toString();
    } //  convertToHexString

    /**
     * Convert Hex String to Byte Array
     *
     * @param hexString hex string
     * @return byte array
     */
    public static byte[] convertHexString(String hexString) {
        if (hexString == null || hexString.length() == 0) return null;
        int size = hexString.length() / 2;
        byte[] retValue = new byte[size];
        String inString = hexString.toLowerCase();

        try {
            for (int i = 0; i < size; i++) {
                int index = i * 2;
                int ii = Integer.parseInt(inString.substring(index, index + 2), 16);
                retValue[i] = (byte) ii;
            }
            return retValue;
        } catch (Exception e) {
            if (log.isLoggable(Level.FINEST)) log.finest(hexString + " - " + e.getLocalizedMessage());
        }
        return null;
    } //  convertToHexString

    /**
     * @return keystore
     */
    public static IKeyStore getKeyStoreService() {
        return new DefaultKeyStore();
    }

    /**
     * Initialize Cipher & Key
     */
    private synchronized void initCipher() {
        if (m_keyStore == null) {
            m_keyStore = getKeyStore();
        }
    } //	initCipher

    /**
     * Encryption.
     *
     * @param value        clear value
     * @param AD_Client_ID
     * @return encrypted String
     */
    public String encrypt(String value, int AD_Client_ID) {
        String clearText = value;
        if (clearText == null) clearText = "";
        // Init
        if (m_keyStore == null) initCipher();

        // Encrypt
        try {
            Cipher cipher = Cipher.getInstance(m_keyStore.getAlgorithm());

            cipher.init(Cipher.ENCRYPT_MODE, m_keyStore.getKey(AD_Client_ID));
            byte[] encBytes = cipher.doFinal(clearText.getBytes(StandardCharsets.UTF_8));

            String encString = convertToHexString(encBytes);
            // globalqss - [ 1577737 ] Security Breach - show database password
            // log.log (Level.ALL, value + " => " + encString);
            return encString;
        } catch (Exception ex) {
            // log.log(Level.INFO, value, ex);
            if (log.isLoggable(Level.INFO)) log.log(Level.INFO, "Problem encrypting string", ex);
        }

        // Fallback
        return CLEARVALUE_START + value + CLEARVALUE_END;
    } //	encrypt

    /**
     * Decryption. The methods must recognize clear text values
     *
     * @param value        encrypted value
     * @param AD_Client_ID
     * @return decrypted String
     */
    public String decrypt(String value, int AD_Client_ID) {
        if (value == null || value.length() == 0) return value;
        boolean isEncrypted =
                value.startsWith(ENCRYPTEDVALUE_START) && value.endsWith(ENCRYPTEDVALUE_END);
        if (isEncrypted)
            value =
                    value.substring(
                            ENCRYPTEDVALUE_START.length(), value.length() - ENCRYPTEDVALUE_END.length());
        //	Needs to be hex String
        byte[] data = convertHexString(value);
        if (data == null) // 	cannot decrypt
        {
            if (isEncrypted) {
                // log.info("Failed: " + value);
                log.info("Failed");
                return null;
            }
            //	assume not encrypted
            return value;
        }
        //	Init
        if (m_keyStore == null) initCipher();

        //	Encrypt
        if (value != null && value.length() > 0) {
            try {
                Cipher cipher = Cipher.getInstance(m_keyStore.getAlgorithm());
                AlgorithmParameters ap = cipher.getParameters();
                cipher.init(Cipher.DECRYPT_MODE, m_keyStore.getKey(AD_Client_ID), ap);
                byte[] out = cipher.doFinal(data);
                String retValue = new String(out, StandardCharsets.UTF_8);
                // globalqss - [ 1577737 ] Security Breach - show database password
                // log.log (Level.ALL, value + " => " + retValue);
                return retValue;
            } catch (Exception ex) {
                // log.info("Failed: " + value + " - " + ex.toString());
                if (log.isLoggable(Level.INFO)) log.info("Failed decrypting " + ex.toString());
            }
        }
        return null;
    } //	decrypt

    /**
     * Convert String and salt to SHA-512 hash with iterations
     * https://www.owasp.org/index.php/Hashing_Java
     *
     * @param value message
     * @return HexString of message (length = 128 characters)
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String getSHA512Hash(int iterations, String value, byte[] salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.reset();
        digest.update(salt);
        byte[] input = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        for (int i = 0; i < iterations; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        digest.reset();
        //
        return convertToHexString(input);
    } //	getSHA512Hash

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("Secure[");
        sb.append(m_keyStore.getAlgorithm()).append("]");
        return sb.toString();
    } //	toString

    /**
     * @return keystore
     */
    public IKeyStore getKeyStore() {
        IKeyStore keyStore = null;
        try {
            keyStore = getKeyStoreService();
        } catch (Exception ex) {
            log.log(Level.WARNING, "getKeyStore failed", ex);
        }
        if (keyStore == null) keyStore = new DefaultKeyStore();

        return keyStore;
    }
} //  Secure
