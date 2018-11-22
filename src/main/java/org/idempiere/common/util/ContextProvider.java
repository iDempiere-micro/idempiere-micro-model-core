package org.idempiere.common.util;

import java.util.Properties;

/** @author Low Heng Sin */
public interface ContextProvider {

  public Properties getContext();

  public void showURL(String url);
}
