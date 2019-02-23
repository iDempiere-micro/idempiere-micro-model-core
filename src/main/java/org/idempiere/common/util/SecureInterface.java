package org.idempiere.common.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Adempiere Security Interface. To enable your own class, you need to set the property
 * ADEMPIERE_SECURE when starting the client or server. The setting for the default class would be:
 * -DADEMPIERE_SECURE=org.idempiere.common.util.Secure
 *
 * @author Jorg Janke
 * @version $Id: SecureInterface.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public interface SecureInterface {
    /**
     * Class Name implementing SecureInterface
     */
    String ADEMPIERE_SECURE = "ADEMPIERE_SECURE";
    /**
     * Default Class Name implementing SecureInterface
     */
    String ADEMPIERE_SECURE_DEFAULT = "org.idempiere.common.util.Secure";

    /**
     * Clear Text Indicator xyz
     */
    String CLEARVALUE_START = "xyz";
    /**
     * Clear Text Indicator
     */
    String CLEARVALUE_END = "";
    /**
     * Encrypted Text Indiactor ~
     */
    String ENCRYPTEDVALUE_START = "~";
    /**
     * Encrypted Text Indiactor ~
     */
    String ENCRYPTEDVALUE_END = "~";

    /**
     * Encryption.
     *
     * @param value        clear value
     * @param AD_Client_ID
     * @return encrypted String
     */
    String encrypt(String value, int AD_Client_ID);

    /**
     * Decryption.
     *
     * @param value encrypted value
     * @return decrypted String
     */
    String decrypt(String value, int AD_Client_ID);

    /**
     * Convert String and salt to SHA-512 hash with iterations
     * https://www.owasp.org/index.php/Hashing_Java
     *
     * @param value message
     * @return HexString of message (length = 128 characters)
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    String getSHA512Hash(int iterations, String value, byte[] salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException;
} //	SecureInterface
