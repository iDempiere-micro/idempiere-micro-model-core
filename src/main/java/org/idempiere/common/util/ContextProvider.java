package org.idempiere.common.util;

import java.util.Properties;

/**
 * @author Low Heng Sin
 */
public interface ContextProvider {

    Properties getContext();

    void showURL(String url);
}
