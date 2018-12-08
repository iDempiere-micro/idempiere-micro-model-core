package org.idempiere.common.util;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * idempiere Log Filter
 *
 * @author Jorg Janke
 * @version $Id: CLogFilter.java,v 1.2 2006/07/30 00:54:36 jjanke Exp $
 */
public class CLogFilter implements Filter {
    /**
     * Singleton
     */
    private static CLogFilter s_filter = null;

    /**
     * ********************************************************************* Constructor
     */
    public CLogFilter() {
    } // 	CLogFilter

    /**
     * Get Filter
     *
     * @return singleton
     */
    public static synchronized CLogFilter get() {
        if (s_filter == null) s_filter = new CLogFilter();
        return s_filter;
    }

    /**
     * Loggable - Don't log core java classes
     *
     * @param record log record
     * @return true
     */
    public boolean isLoggable(LogRecord record) {
        if (record.getLevel() == Level.SEVERE || record.getLevel() == Level.WARNING) return true;
        //
        String loggerName = record.getLoggerName();
        if (loggerName != null) {
            //	if (loggerName.toLowerCase().indexOf("focus") != -1)
            //		return true;
            if (loggerName.startsWith("sun.")
                    || loggerName.startsWith("java.awt.")
                    || loggerName.startsWith("javax.")) return false;
        }
        String className = record.getSourceClassName();
        if (className != null) {
            return !className.startsWith("sun.")
                    && !className.startsWith("java.awt.")
                    && !className.startsWith("javax.");
        }
        return true;
    } //	isLoggable
} //	CLogFilter
