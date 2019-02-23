package org.idempiere.common.util;

import java.util.Properties;

/**
 * @author Low Heng Sin
 */
public class ServerContextProvider implements ContextProvider {

    public static final ServerContextProvider INSTANCE = new ServerContextProvider();
    private static final Properties context = new ServerContextPropertiesWrapper();

    private ServerContextProvider() {
    }

    /**
     * Get server context proxy
     */
    public Properties getContext() {
        return context;
    }

}
