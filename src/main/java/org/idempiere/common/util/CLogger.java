package org.idempiere.common.util;

import java.io.Serializable;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * idempiere Logger
 *
 * @author Jorg Janke
 * @version $Id: CLogger.java,v 1.3 2006/08/09 16:38:47 jjanke Exp $
 */
public class CLogger extends Logger implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6492376264463028357L;

    private static final String LAST_INFO = "org.idempiere.common.util.CLogger.lastInfo";
    private static final String LAST_WARNING = "org.idempiere.common.util.CLogger.lastWarning";
    private static final String LAST_ERROR = "org.idempiere.common.util.CLogger.lastError";
    private static final String LAST_EXCEPTION = "org.idempiere.common.util.CLogger.lastException";

    public static Boolean throwOnError = true;
    /**
     * Default Logger
     */
    private static volatile CLogger s_logger = null;

    /**
     * ************************************************************************ Standard constructor
     *
     * @param name               logger name
     * @param resourceBundleName optional resource bundle (ignored)
     */
    private CLogger(String name, String resourceBundleName) {
        super(name, resourceBundleName);
        //	setLevel(Level.ALL);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        addHandler(handler);
        handler.setLevel(Level.ALL);
        setLevel(Level.ALL);
    } //	CLogger

    /**
     * Get Logger
     *
     * @param className class name
     * @return Logger
     */
    public static synchronized CLogger getCLogger(String className) {
        return getCLogger(className, true);
    }

    /**
     * Get Logger
     *
     * @param className       class name
     * @param usePackageLevel
     * @return Logger
     */
    public static synchronized CLogger getCLogger(String className, boolean usePackageLevel) {
        //	CLogMgt.initialize();
        LogManager manager = LogManager.getLogManager();
        if (className == null || className.trim().length() == 0) className = "";

        Logger result = manager.getLogger(className);
        if (result != null && result instanceof CLogger) return (CLogger) result;

        Logger packageLogger = null;
        if (className.indexOf(".") > 0 && usePackageLevel) {
            String s = className.substring(0, className.lastIndexOf("."));
            while (s.indexOf(".") > 0) {
                result = manager.getLogger(s);
                if (result != null && result instanceof CLogger) {
                    packageLogger = result;
                    break;
                }
                s = s.substring(0, s.lastIndexOf("."));
            }
        }
        //
        CLogger newLogger = new CLogger(className, null);
        if (packageLogger != null && packageLogger.getLevel() != null)
            newLogger.setLevel(packageLogger.getLevel());
        else newLogger.setLevel(CLogMgt.getLevel());
        manager.addLogger(newLogger);
        return newLogger;
    } //	getLogger

    /**
     * Get Logger
     *
     * @param clazz class name
     * @return Logger
     */
    public static CLogger getCLogger(Class<?> clazz) {
        if (clazz == null) return get();
        return getCLogger(clazz.getName());
    } //	getLogger

    /**
     * Get default idempiere Logger. Need to be used in serialized objects
     *
     * @return logger
     */
    public static CLogger get() {
        if (s_logger == null) s_logger = getCLogger("org.idempiere.default");
        return s_logger;
    } //	get

    /** ********************************************************************** */

    /**
     * Get Error from Stack
     *
     * @return AD_Message as Value and Message as String
     */
    public static ValueNamePair retrieveError() {
        ValueNamePair vp = (ValueNamePair) Env.getCtx().remove(LAST_ERROR);
        return vp;
    } //  retrieveError

    /**
     * Get Error message from stack
     *
     * @param defaultMsg default message (used when there are no errors on stack)
     * @return error message, or defaultMsg if there is not error message saved
     * @author Teo Sarca, SC ARHIPAC SERVICE SRL
     * @see #retrieveError()
     */
    public static String retrieveErrorString(String defaultMsg) {
        ValueNamePair vp = retrieveError();
        if (vp == null) return defaultMsg;
        return vp.getName();
    }

    /**
     * Get Error from Stack
     *
     * @return last exception
     */
    public static Exception retrieveException() {
        Exception ex = (Exception) Env.getCtx().remove(LAST_EXCEPTION);
        return ex;
    } //  retrieveError

    /**
     * Reset Saved Messages/Errors/Info
     */
    public static void resetLast() {
        Env.getCtx().remove(LAST_ERROR);
        Env.getCtx().remove(LAST_EXCEPTION);
        Env.getCtx().remove(LAST_WARNING);
        Env.getCtx().remove(LAST_INFO);
    } //	resetLast

    /**
     * Set and issue Error and save as ValueNamePair
     *
     * @param AD_Message message key
     * @param message    clear text message
     * @return true (to avoid removal of method)
     */
    public boolean saveError(String AD_Message, String message) {
        return saveError(AD_Message, message, true);
    } //  saveError

    /**
     * Set and issue Error and save as ValueNamePair
     *
     * @param AD_Message message key
     * @param ex         exception
     * @return true (to avoid removal of method)
     */
    public boolean saveError(String AD_Message, Exception ex) {
        Env.getCtx().put(LAST_EXCEPTION, ex);
        return saveError(AD_Message, ex.getLocalizedMessage(), true);
    } //  saveError

    /**
     * Set and issue (if specified) Error and save as ValueNamePair
     *
     * @param AD_Message message key
     * @param ex         exception
     * @param issueError if true will issue an error
     * @return true (to avoid removal of method)
     */
    public boolean saveError(String AD_Message, Exception ex, boolean issueError) {
        Env.getCtx().put(LAST_EXCEPTION, ex);
        return saveError(AD_Message, ex.getLocalizedMessage(), issueError);
    } //  saveError

    /**
     * Set Error and save as ValueNamePair
     *
     * @param AD_Message message key
     * @param message    clear text message
     * @param issueError print error message (default true)
     * @return true
     */
    public boolean saveError(String AD_Message, String message, boolean issueError) {
        ValueNamePair lastError = new ValueNamePair(AD_Message, message);
        Env.getCtx().put(LAST_ERROR, lastError);
        //  print it
        if (issueError) severe(AD_Message + " - " + message);
        if (throwOnError) throw new Error(AD_Message + "-" + message);
        return true;
    } //  saveError

    /**
     * Save Warning as ValueNamePair.
     *
     * @param AD_Message message key
     * @param message    clear text message
     * @return true
     */
    public boolean saveWarning(String AD_Message, String message) {
        ValueNamePair lastWarning = new ValueNamePair(AD_Message, message);
        Env.getCtx().put(LAST_WARNING, lastWarning);
        //  print it
        warning(AD_Message + " - " + message);
        return true;
    } //  saveWarning

    public void severe(String msg) {
        if (throwOnError) throw new Error(msg);
        super.severe(msg);
    }

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        return "CLogger[" + getName() + ",Level=" + getLevel() + "]";
    } //	toString
} //	CLogger
