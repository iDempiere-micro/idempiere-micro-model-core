package org.idempiere.common.util;

import org.idempiere.icommon.base.IKeyStore;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.util.Properties;
import java.util.logging.Level;

/**
 * @author deepak
 * @author hengsin
 */
public class DefaultKeyStore implements IKeyStore {

    private static final String LEGACY_ALGORITHM = "DES";

    private static final String IDEMPIERE_KEYSTORE_PROPERTIES = "idempiere-ks.properties";

    private static final String IDEMPIERE_KEYSTORE = "idempiere.ks";

    /**
     * Logger
     */
    private static CLogger log = CLogger.getCLogger(DefaultKeyStore.class.getName());

    /**
     * Adempiere Key
     */
    private SecretKey m_key = null;

    private KeyStore keyStore;

    private char[] password = null;

    private String algorithm;

    public DefaultKeyStore() {
        File file = new File("", IDEMPIERE_KEYSTORE_PROPERTIES);
        if (file.exists()) {
            FileInputStream is = null;
            try {
                is = new FileInputStream(file);
                Properties p = new Properties();
                p.load(is);
                String s = p.getProperty("password");
                String a = p.getProperty("algorithm");
                if (!Util.isEmpty(s) && !Util.isEmpty(a)) {
                    password = s.toCharArray();
                    algorithm = a;
                    keyStore = KeyStore.getInstance("JCEKS");
                    file = new File("", IDEMPIERE_KEYSTORE);
                    if (file.exists()) {
                        FileInputStream stream = new FileInputStream(file);
                        keyStore.load(stream, password);
                    } else {
                        keyStore.load(null, password);
                    }
                } else {
                    createLegacyKey();
                }
            } catch (Exception ex) {
                log.log(Level.SEVERE, "", ex);
                password = null;
                createLegacyKey();
            } finally {
                try {
                    if (is != null) is.close();
                } catch (Exception e) {
                }
            }
        } else {
            createLegacyKey();
        }
    }

    private void createLegacyKey() {
        m_key =
                new javax.crypto.spec.SecretKeySpec(
                        new byte[]{100, 25, 28, -122, -26, 94, -3, -26}, LEGACY_ALGORITHM);
    }

    @Override
    public synchronized SecretKey getKey(int AD_Client_ID) {
        if (password != null) {
            try {
                PasswordProtection protParam = new PasswordProtection(password);
                String alias = "ad_client_" + AD_Client_ID;
                SecretKeyEntry entry = (SecretKeyEntry) keyStore.getEntry(alias, protParam);
                if (entry == null) {
                    KeyGenerator generator = KeyGenerator.getInstance(algorithm);
                    SecretKey key = generator.generateKey();
                    entry = new SecretKeyEntry(key);

                    keyStore.setEntry(alias, entry, protParam);
                    File file = new File(IDEMPIERE_KEYSTORE);
                    FileOutputStream stream = null;
                    try {
                        stream = new FileOutputStream(file);
                        keyStore.store(stream, password);
                        stream.flush();
                    } finally {
                        if (stream != null) {
                            try {
                                stream.close();
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                return entry.getSecretKey();
            } catch (Exception ex) {
                log.log(Level.SEVERE, "", ex);
            }
        }
        return m_key;
    }

    @Override
    public String getAlgorithm() {
        if (algorithm == null) return LEGACY_ALGORITHM;
        else return algorithm;
    }
}
