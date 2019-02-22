package org.idempiere.common.util;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Trace Information
 *
 * @author Jorg Janke
 * @version $Id: Trace.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class Trace {
    /**
     * Logger
     */
    private static CLogger log = CLogger.getCLogger(Trace.class);

    /**
     * Get Caller Array
     *
     * @param caller       Optional Throwable/Exception
     * @param maxNestLevel maximum call nesting level - 0 is all
     * @return Array of class.method(file:line)
     */
    public static String[] getCallerClasses(Throwable caller, int maxNestLevel) {
        int nestLevel = maxNestLevel;
        if (nestLevel < 1) nestLevel = 99;
        //
        ArrayList<String> list = new ArrayList<String>();
        Throwable t = caller;
        if (t == null) t = new Throwable();

        StackTraceElement[] elements = t.getStackTrace();
        for (int i = 0; i < elements.length && list.size() <= maxNestLevel; i++) {
            String className = elements[i].getClassName();
            //	System.out.println(list.size() + ": " + className);
            if (!(className.startsWith("org.idempiere.common.util.Trace")
                    || className.startsWith("java.lang.Throwable"))) list.add(className);
        }

        String[] retValue = new String[list.size()];
        list.toArray(retValue);
        return retValue;
    } //  getCallerClasses

    /**
     * Get Caller with nest Level
     *
     * @param nestLevel Nesting Level - 0=calling method, 1=previous, ..
     * @return class name and line info of nesting level or "" if not exist
     */
    public static String getCallerClass(int nestLevel) {
        String[] array = getCallerClasses(null, nestLevel);
        if (array.length < nestLevel) return "";
        return array[nestLevel];
    } //  getCallerClass

    /**
     * Print Stack Trace Info (raw) idempiereOnly - first9only
     */
    public static void printStack() {
        printStack(true, true);
    } //	printStack

    /**
     * Print Stack Trace Info (raw)
     */
    public static void printStack(boolean idempiereOnly, boolean first9only) {
        Throwable t = new Throwable();
        //	t.printStackTrace();
        int counter = 0;
        StackTraceElement[] elements = t.getStackTrace();
        for (int i = 1; i < elements.length; i++) {
            if (elements[i].getClassName().indexOf("util.Trace") != -1) continue;
            if (!idempiereOnly
                    || (idempiereOnly && elements[i].getClassName().startsWith("org.idempiere"))) {
                if (log.isLoggable(Level.FINE)) log.fine(i + ": " + elements[i]);
                if (first9only && ++counter > 8) break;
            }
        }
    } //  printStack
} //  Trace
