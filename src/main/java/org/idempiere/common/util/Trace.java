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
            if (elements[i].getClassName().contains("util.Trace")) continue;
            if (!idempiereOnly || elements[i].getClassName().startsWith("org.idempiere")) {
                if (log.isLoggable(Level.FINE)) log.fine(i + ": " + elements[i]);
                if (first9only && ++counter > 8) break;
            }
        }
    } //  printStack
} //  Trace
