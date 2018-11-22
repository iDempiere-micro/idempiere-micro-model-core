package org.idempiere.common.exceptions;

import java.util.Properties;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;

/**
 * Any exception that occurs inside the Adempiere core
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class AdempiereException extends RuntimeException {
  /** */
  private static final long serialVersionUID = 2340179640558569534L;

  /** Default Constructor (saved logger error will be used as message) */
  public AdempiereException() {
    this(getMessageFromLogger());
  }

  /** @param message */
  public AdempiereException(String message) {
    super(message);
  }

  /** @param cause */
  public AdempiereException(Throwable cause) {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public AdempiereException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  public String getLocalizedMessage() {
    String msg = super.getLocalizedMessage();
    // msg = Msg.parseTranslation(getCtx(), msg); DAP TODO
    return msg;
  }

  protected Properties getCtx() {
    return Env.getCtx();
  }

  /**
   * @return error message from logger
   * @see org.idempiere.common.util.CLogger#retrieveError()
   */
  private static String getMessageFromLogger() {
    org.idempiere.common.util.ValueNamePair err = CLogger.retrieveError();
    String msg = null;
    if (err != null) msg = err.getName();
    if (msg == null) msg = "UnknownError";
    return msg;
  }
}
