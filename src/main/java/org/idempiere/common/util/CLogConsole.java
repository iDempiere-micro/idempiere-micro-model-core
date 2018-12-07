package org.idempiere.common.util;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.util.logging.ErrorManager;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Adempiere Console Logger
 *
 * @author Jorg Janke
 * @version $Id: CLogConsole.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 */
public class CLogConsole extends Handler {
    /**
     * Printed header
     */
    private boolean m_doneHeader = false;
    /**
     * Normal Writer
     */
    private PrintWriter m_writerOut = null;
    /**
     * Error Writer
     */
    private PrintWriter m_writerErr = null;
    /**
     * Constructor
     */
    public CLogConsole() {
        initialize();
    } // CLogConsole

    /**
     * Initialize
     */
    private void initialize() {
        //	System.out.println("CLogConsole.initialize");
        //	Set Writers
        String encoding = getEncoding();
        if (encoding != null) {
            try {
                m_writerOut = new PrintWriter(new OutputStreamWriter(System.out, encoding));
                m_writerErr = new PrintWriter(new OutputStreamWriter(System.err, encoding));
            } catch (UnsupportedEncodingException ex) {
                reportError("Opening encoded Writers", ex, ErrorManager.OPEN_FAILURE);
            }
        }
        if (m_writerOut == null) m_writerOut = new PrintWriter(System.out);
        if (m_writerErr == null) m_writerErr = new PrintWriter(System.err);

        //	Formatting
        setFormatter(CLogFormatter.get());
        //	Default Level
        setLevel(Level.INFO);
        //	Filter
        setFilter(CLogFilter.get());
        //
    } //	initialize

    /**
     * Set Encoding
     *
     * @param encoding encoding
     * @throws SecurityException
     * @throws java.io.UnsupportedEncodingException
     */
    public void setEncoding(String encoding)
            throws SecurityException, java.io.UnsupportedEncodingException {
        super.setEncoding(encoding);
        // Replace the current writer with a writer for the new encoding.
        flush();
        initialize();
    } //	setEncoding

    /**
     * Set Level
     *
     * @param newLevel new Level
     * @throws java.lang.SecurityException
     * @see java.util.logging.Handler#setLevel(java.util.logging.Level)
     */
    public synchronized void setLevel(Level newLevel) throws SecurityException {
        if (newLevel == null) return;
        super.setLevel(newLevel);
        boolean enableJDBC = newLevel == Level.FINEST;
        if (enableJDBC) DriverManager.setLogWriter(m_writerOut); // 	lists Statements
        else DriverManager.setLogWriter(null);
    } //	setLevel

    /**
     * Publish
     *
     * @param record log record
     * @see java.util.logging.Handler#publish(java.util.logging.LogRecord)
     */
    public void publish(LogRecord record) {
        if (!isLoggable(record) || m_writerOut == null) return;

        //	Format
        String msg = null;
        try {
            msg = getFormatter().format(record);
        } catch (Exception ex) {
            reportError("formatting", ex, ErrorManager.FORMAT_FAILURE);
            return;
        }
        //	Output
        try {
            if (!m_doneHeader) {
                m_writerOut.write(getFormatter().getHead(this));
                m_doneHeader = true;
            }
            if (record.getLevel() == Level.SEVERE || record.getLevel() == Level.WARNING) {
                flush();
                m_writerErr.write(msg);
                flush();
            } else {
                m_writerOut.write(msg);
                m_writerOut.flush();
            }
        } catch (Exception ex) {
            reportError("writing", ex, ErrorManager.WRITE_FAILURE);
        }
    } // publish

    /**
     * Flush
     *
     * @see java.util.logging.Handler#flush()
     */
    public void flush() {
        try {
            if (m_writerOut != null) m_writerOut.flush();
        } catch (Exception ex) {
            reportError("flush out", ex, ErrorManager.FLUSH_FAILURE);
        }
        try {
            if (m_writerErr != null) m_writerErr.flush();
        } catch (Exception ex) {
            reportError("flush err", ex, ErrorManager.FLUSH_FAILURE);
        }
    } // flush

    /**
     * Close
     *
     * @throws SecurityException
     * @see java.util.logging.Handler#close()
     */
    public void close() throws SecurityException {
        if (m_writerOut == null) return;

        //	Write Tail
        try {
            if (!m_doneHeader) m_writerOut.write(getFormatter().getHead(this));
            //
            m_writerOut.write(getFormatter().getTail(this));
        } catch (Exception ex) {
            reportError("tail", ex, ErrorManager.WRITE_FAILURE);
        }
        //
        flush();
        //	Close
        try {
            m_writerOut.close();
        } catch (Exception ex) {
            reportError("close out", ex, ErrorManager.CLOSE_FAILURE);
        }
        m_writerOut = null;
        try {
            m_writerErr.close();
        } catch (Exception ex) {
            reportError("close err", ex, ErrorManager.CLOSE_FAILURE);
        }
        m_writerErr = null;
    } // close

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("CLogConsole[");
        sb.append("Level=").append(getLevel()).append("]");
        return sb.toString();
    } //	toString
} // CLogConsole
