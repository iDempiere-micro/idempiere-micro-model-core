package org.idempiere.common.util;

import java.util.Properties;

/** @author Low Heng Sin */
public class ServerContextProvider implements ContextProvider {

  public static final ServerContextProvider INSTANCE = new ServerContextProvider();
  private static final Properties context = new ServerContextPropertiesWrapper();

  private ServerContextProvider() {}

  /** Get server context proxy */
  public Properties getContext() {
    return context;
  }

  /** Show url at zk desktop */
  public void showURL(String url) {
    ServerContextURLHandler handler =
        (ServerContextURLHandler)
            getContext().get(ServerContextURLHandler.SERVER_CONTEXT_URL_HANDLER);
    if (handler != null) handler.showURL(url);
  }
}
