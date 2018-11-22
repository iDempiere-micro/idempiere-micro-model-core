package org.idempiere.common.util;

import java.util.Properties;

/** @author hengsin */
public abstract class ContextRunnable implements Runnable {

  protected Properties context = null;

  public ContextRunnable() {
    this.context = ServerContext.getCurrentInstance();
  }

  /* (non-Javadoc)
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    try {
      ServerContext.setCurrentInstance(context);
      doRun();
    } finally {
      ServerContext.dispose();
    }
  }

  protected abstract void doRun();
}
