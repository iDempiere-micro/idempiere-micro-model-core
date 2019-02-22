package org.idempiere.common.util;

/**
 * Adempiere System Error. Error caused by invalid configurations, etc. (No program error)
 *
 * @author Jorg Janke
 * @version $Id: AdempiereSystemError.java,v 1.2 2006/07/30 00:54:36 jjanke Exp $
 */
public class AdempiereSystemError extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 9111445784263763938L;
    /**
     * Details
     */
    private Object m_detail = null;

    /**
     * Adempiere System Error
     *
     * @param message message
     */
    public AdempiereSystemError(String message) {
        super(message);
    } //	AdempiereSystemError

    /**
     * Adempiere System Error
     *
     * @param message message
     * @param detail  detail
     */
    public AdempiereSystemError(String message, Object detail) {
        super(message);
        setDetail(detail);
    } //	AdempiereSystemError

    /**
     * Adempiere System Error
     *
     * @param message
     * @param cause
     */
    public AdempiereSystemError(String message, Throwable cause) {
        super(message, cause);
    } //	AdempiereSystemError

    /**
     * @param detail The detail to set.
     */
    public void setDetail(Object detail) {
        m_detail = detail;
    }

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        super.toString();
        StringBuilder sb = new StringBuilder("SystemError: ");
        sb.append(getLocalizedMessage());
        if (m_detail != null) sb.append(" (").append(m_detail).append(")");
        return sb.toString();
    } //	toString
} //	AdempiereSystemError
