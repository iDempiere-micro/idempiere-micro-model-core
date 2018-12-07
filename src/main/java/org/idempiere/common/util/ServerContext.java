package org.idempiere.common.util;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @version $Revision: 0.10 $
 * @date Feb 25, 2007
 */
public final class ServerContext implements Serializable {
  /** generated serial version Id */
  private static final long serialVersionUID = -8274580404204046413L;

  private static InheritableThreadLocal<Properties> context =
      new InheritableThreadLocal<Properties>() {
        protected Properties initialValue() {
          Properties ctx = new Properties();
          return ctx;
        }
      };

  private ServerContext() {}

  /**
   * Get server context for current thread
   *
   * @return Properties
   */
  public static Properties getCurrentInstance() {
    return context.get();
  }

  /**
   * Set server context for current thread
   *
   * @param ctx
   */
  public static void setCurrentInstance(Properties ctx) {
    context.set(ctx);
  }

  /** dispose server context for current thread */
  public static void dispose() {
    context.remove();
  }
}
