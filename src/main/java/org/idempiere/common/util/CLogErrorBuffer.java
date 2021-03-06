package org.idempiere.common.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import static software.hsharp.core.util.DBKt.isConnected;

/**
 * Client Error Buffer
 *
 * @author Jorg Janke
 * @author Teo Sarca, teo.sarca@gmail.com
 * <li>BF [ 2973298 ] NPE on CLogErrorBuffer
 * @version $Id: CLogErrorBuffer.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
 */
public class CLogErrorBuffer extends Handler {
    private static final String ISSUE_ERROR_KEY = "org.compiere.util.CLogErrorBuffer.issueError";
    private static final String HISTORY_KEY = "org.compiere.util.CLogErrorBuffer.history";
    private static final String ERRORS_KEY = "org.compiere.util.CLogErrorBuffer.errors";
    private static final String LOGS_KEY = "org.compiere.util.CLogErrorBuffer.logs";
    /**
     * Error Buffer Size
     */
    private static final int ERROR_SIZE = 20;
    /**
     * Log Size
     */
    private static final int LOG_SIZE = 100;

    /**
     * ******************************************************************* Constructor
     */
    public CLogErrorBuffer() {
        initialize();
    } //	CLogErrorBuffer

    /**
     * Initialize
     */
    private void initialize() {
        //	System.out.println("CLogConsole.initialize");

        //	Formatting
        setFormatter(CLogFormatter.get());
        //	Default Level
        super.setLevel(Level.INFO);
        //	Filter
        setFilter(CLogFilter.get());
    } //	initialize

    /**
     * Issue Error
     *
     * @return true if issue error
     */
    public boolean isIssueError() {
        Boolean b = (Boolean) Env.getCtx().get(ISSUE_ERROR_KEY);
        if (b == null) {
            b = Boolean.TRUE;
            setIssueError(b);
        }
        return b;
    } //	isIssueError

    /**
     * Set Issue Error
     *
     * @param issueError issue error
     */
    public void setIssueError(boolean issueError) {
        Env.getCtx().put(ISSUE_ERROR_KEY, issueError);
    } //	setIssueError

    /**
     * Set Level. Ignore OFF - and higher then FINE
     *
     * @param newLevel ignored
     * @throws java.lang.SecurityException
     * @see java.util.logging.Handler#setLevel(java.util.logging.Level)
     */
    public synchronized void setLevel(Level newLevel) throws SecurityException {
        if (newLevel == null) return;
        if (newLevel == Level.OFF) super.setLevel(Level.SEVERE);
        else if (newLevel == Level.ALL || newLevel == Level.FINEST || newLevel == Level.FINER)
            super.setLevel(Level.FINE);
        else super.setLevel(newLevel);
    } //	SetLevel

    /**
     * Publish
     *
     * @param record log record
     * @see java.util.logging.Handler#publish(java.util.logging.LogRecord)
     */
    public void publish(LogRecord record) {
        checkContext();

        @SuppressWarnings("unchecked")
        LinkedList<LogRecord> m_logs = (LinkedList<LogRecord>) Env.getCtx().get(LOGS_KEY);
        if (!isLoggable(record) || m_logs == null) return;

        //	Output
        synchronized (m_logs) {
            if (m_logs.size() >= LOG_SIZE) m_logs.removeFirst();
            m_logs.add(record);
        }

        //	We have an error
        if (record.getLevel() == Level.SEVERE) {
            @SuppressWarnings("unchecked")
            LinkedList<LogRecord> m_errors = (LinkedList<LogRecord>) Env.getCtx().get(ERRORS_KEY);
            @SuppressWarnings("unchecked")
            LinkedList<LogRecord[]> m_history = (LinkedList<LogRecord[]>) Env.getCtx().get(HISTORY_KEY);
            if (m_errors.size() >= ERROR_SIZE) {
                m_errors.removeFirst();
                m_history.removeFirst();
            }
            //	Add Error
            m_errors.add(record);
            record.getSourceClassName(); // 	forces Class Name eval

            //	Create History
            ArrayList<LogRecord> history = new ArrayList<LogRecord>();
            for (int i = m_logs.size() - 1; i >= 0; i--) {
                LogRecord rec = m_logs.get(i);
                if (rec.getLevel() == Level.SEVERE) {
                    if (history.size() == 0) history.add(rec);
                    else break; // 	don't include previous error
                } else {
                    history.add(rec);
                    if (history.size() > 10) break; // 	no more then 10 history records
                }
            }
            LogRecord[] historyArray = new LogRecord[history.size()];
            int no = 0;
            for (int i = history.size() - 1; i >= 0; i--) historyArray[no++] = history.get(i);
            m_history.add(historyArray);
            //	Issue Reporting
            if (isIssueError()) {
                String loggerName = record.getLoggerName(); // 	class name
                if (loggerName == null) loggerName = "";
                // String className = record.getSourceClassName();		//	physical class
                String methodName = record.getSourceMethodName(); //
                if (methodName == null) methodName = "";
                if (isConnected()
                        && methodName != null
                        && !methodName.equals("saveError")
                        && !methodName.equals("get_Value")
                        && !methodName.equals("dataSave")
                        && loggerName.indexOf("Issue") == -1
                        && loggerName.indexOf("CConnection") == -1) {
                    setIssueError(false);
                } else {
                    // display to user if database connection not available
                    if (methodName != null
                            && !methodName.equals("saveError")
                            && !methodName.equals("get_Value")
                            && !methodName.equals("dataSave")
                            && loggerName.indexOf("Issue") == -1
                            && loggerName.indexOf("CConnection") == -1) {
                        System.err.println(getFormatter().format(record));
                    }
                }
            }
        }
    } // publish

    /**
     * Flush (NOP)
     *
     * @see java.util.logging.Handler#flush()
     */
    public void flush() {
    } // flush

    /**
     * Close
     *
     * @throws SecurityException
     * @see java.util.logging.Handler#close()
     */
    public void close() throws SecurityException {
        Env.getCtx().remove(LOGS_KEY);
        Env.getCtx().remove(ERRORS_KEY);
        Env.getCtx().remove(HISTORY_KEY);
    } // close

    private void checkContext() {
        if (!Env.getCtx().containsKey(LOGS_KEY)) {
            LinkedList<LogRecord> m_logs = new LinkedList<LogRecord>();
            Env.getCtx().put(LOGS_KEY, m_logs);
        }

        if (!Env.getCtx().containsKey(ERRORS_KEY)) {
            LinkedList<LogRecord> m_errors = new LinkedList<LogRecord>();
            Env.getCtx().put(ERRORS_KEY, m_errors);
        }

        if (!Env.getCtx().containsKey(HISTORY_KEY)) {
            LinkedList<LogRecord[]> m_history = new LinkedList<LogRecord[]>();
            Env.getCtx().put(HISTORY_KEY, m_history);
        }
    }

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        checkContext();

        @SuppressWarnings("unchecked")
        LinkedList<LogRecord> m_logs = (LinkedList<LogRecord>) Env.getCtx().get(LOGS_KEY);
        @SuppressWarnings("unchecked")
        LinkedList<LogRecord> m_errors = (LinkedList<LogRecord>) Env.getCtx().get(ERRORS_KEY);
        @SuppressWarnings("unchecked")
        LinkedList<LogRecord[]> m_history = (LinkedList<LogRecord[]>) Env.getCtx().get(HISTORY_KEY);
        StringBuilder sb = new StringBuilder("CLogErrorBuffer[");
        sb.append("Errors=")
                .append(m_errors.size())
                .append(",History=")
                .append(m_history.size())
                .append(",Logs=")
                .append(m_logs.size())
                .append(",Level=")
                .append(getLevel())
                .append("]");
        return sb.toString();
    } //	toString
} //	CLogErrorBuffer
