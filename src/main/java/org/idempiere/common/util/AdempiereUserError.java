package org.idempiere.common.util;

/**
 * Adempiere User Error. Cuased by (lack of) user input/selection. (No program error)
 *
 * @author Jorg Janke
 * @version $Id: AdempiereUserError.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 */
public class AdempiereUserError extends RuntimeException {
  /** */
  private static final long serialVersionUID = -5318376918072817705L;
  /** Additional Info how to fix * */
  private String m_fixHint = null;

  /**
   * Adempiere User Error
   *
   * @param message message
   */
  public AdempiereUserError(String message) {
    super(message);
  } //	AdempiereUserError

  /**
   * Adempiere User Error
   *
   * @param message message
   * @param fixHint fix hint
   */
  public AdempiereUserError(String message, String fixHint) {
    super(message);
    setFixHint(fixHint);
  } //	AdempiereUserError

  /**
   * AdempiereUserError
   *
   * @param message
   * @param cause
   */
  public AdempiereUserError(String message, Throwable cause) {
    super(message, cause);
  } //	AdempiereUserError

  /** @return Returns the fixHint. */
  public String getFixHint() {
    return m_fixHint;
  } //	getFixHint

  /**
   * Set Fix Hint
   *
   * @param fixHint fix hint
   */
  public void setFixHint(String fixHint) {
    m_fixHint = fixHint;
  } //	setFixHint

  /**
   * String Representation
   *
   * @return info
   */
  public String toString() {
    super.toString();
    StringBuilder sb = new StringBuilder("UserError: ");
    sb.append(getLocalizedMessage());
    if (m_fixHint != null && m_fixHint.length() > 0) sb.append(" (").append(m_fixHint).append(")");
    return sb.toString();
  } //	toString
} //	AdempiereUserError
