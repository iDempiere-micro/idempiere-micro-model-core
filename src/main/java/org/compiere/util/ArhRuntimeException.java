/**
 *
 */
package org.compiere.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Arhipac Runtime exception
 *
 * @author Teo_Sarca, SC ARHIPAC SERVICE SRL
 */
public class ArhRuntimeException extends AdempiereExceptionEx {
    /** */
    private static final long serialVersionUID = -100343773302909791L;
    /** Additional attributes */
    private HashMap<String, Object> m_info = new HashMap<String, Object>();

    /** Default Constructor (logger error will be used as message) */
    public ArhRuntimeException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message error message or "" if you don't need a message
     */
    public ArhRuntimeException(String message) {
        super(message);
    }

    /** @param cause */
    public ArhRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ArhRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#getLocalizedMessage()
     */

    public String getLocalizedMessage() {
        StringBuilder sb = new StringBuilder();

        // Message
        String msg = getMessage();
        if (msg != null && msg.length() > 0) {
            sb.append(MsgKt.parseTranslation(msg));
        } else {
            msg = null;
        }
        // Additional info:
        if (m_info.size() > 0) {
            Iterator<String> it = m_info.keySet().iterator();
            int cnt = 0;
            while (it.hasNext()) {
                String name = it.next();
                Object value = m_info.get(name);
                if (cnt == 0) {
                    if (msg != null) sb.append(" (");
                } else {
                    sb.append(", ");
                }
                sb.append(MsgKt.parseTranslation(name));
                String svalue = getStringValue(value);
                if (value != null && svalue.length() > 0) sb.append(" ").append(svalue);
                cnt++;
            }
            if (cnt > 0 && msg != null) {
                sb.append(")");
            }
        }
        //
        return sb.toString();
    }

    /**
     * Translated string representation of the provided value
     *
     * @param value
     * @return
     */
    private String getStringValue(Object value) {
        String svalue = null;
        if (value == null) {
            svalue = "-";
        } else if (value instanceof Boolean) {
            svalue = MsgKt.getMsg((Boolean) value ? "Yes" : "No");
        } else if (value instanceof Timestamp) {
            SimpleDateFormat df = DisplayType.getDateFormat();
            svalue = df.format((Timestamp) value);
        } else {
            svalue = value.toString();
        }
        return svalue;
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */

    public String toString() {
        return getLocalizedMessage();
    }
}
