package org.idempiere.common.util;

import software.hsharp.core.util.Environment;

import java.util.Properties;

/**
 * @author Low Heng Sin
 */
public class ServerContextProvider implements ContextProvider {

    public static final ServerContextProvider INSTANCE = new ServerContextProvider();

    private ServerContextProvider() {
    }

    /**
     * Get server context proxy
     */
    public Properties getContext() {
        return Environment.Companion.getCurrent().getContext();
    }

}
