package org.idempiere.common.util;

import java.util.Properties;

/**
 * @author Low Heng Sin
 */
public class DefaultContextProvider implements ContextProvider {

    /**
     * Logging
     */
    private static CLogger s_log = CLogger.getCLogger(DefaultContextProvider.class);

    private static Properties s_ctx = new Properties();

    public Properties getContext() {
        return s_ctx;
    }

    public void showURL(String url) {
        try {
            java.net.URI uri = new java.net.URI(url);
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            s_log.warning(e.getLocalizedMessage());
        }
    }
}
