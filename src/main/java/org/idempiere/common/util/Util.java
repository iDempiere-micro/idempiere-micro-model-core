package org.idempiere.common.util;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * General Utilities
 *
 * @author Jorg Janke
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL - BF [ 1748346 ]
 * @version $Id: Util.java,v 1.3 2006/07/30 00:52:23 jjanke Exp $
 */
public class Util {

    /**
     * Replace String values.
     *
     * @param value   string to be processed
     * @param oldPart old part
     * @param newPart replacement - can be null or ""
     * @return String with replaced values
     */
    public static String replace(String value, String oldPart, String newPart) {
        if (value == null || value.length() == 0 || oldPart == null || oldPart.length() == 0)
            return value;
        //
        int oldPartLength = oldPart.length();
        String oldValue = value;
        StringBuilder retValue = new StringBuilder();
        int pos = oldValue.indexOf(oldPart);
        while (pos != -1) {
            retValue.append(oldValue, 0, pos);
            if (newPart != null && newPart.length() > 0) retValue.append(newPart);
            oldValue = oldValue.substring(pos + oldPartLength);
            pos = oldValue.indexOf(oldPart);
        }
        retValue.append(oldValue);
        //	log.fine( "Env.replace - " + value + " - Old=" + oldPart + ", New=" + newPart + ", Result=" +
        // retValue.toString());
        return retValue.toString();
    } //	replace

    /**
     * Is String Empty
     *
     * @param str string
     * @return true if >= 1 char
     */
    public static boolean isEmpty(String str) {
        return isEmpty(str, false);
    } //	isEmpty

    /**
     * Is String Empty
     *
     * @param str             string
     * @param trimWhitespaces trim whitespaces
     * @return true if >= 1 char
     */
    public static boolean isEmpty(String str, boolean trimWhitespaces) {
        if (str == null) return true;
        if (trimWhitespaces) return str.trim().length() == 0;
        else return str.length() == 0;
    } //	isEmpty

    /**
     * ************************************************************************ Find index of search
     * character in str. This ignores content in () and 'texts'
     *
     * @param str    string
     * @param search search character
     * @return index or -1 if not found
     */
    public static int findIndexOf(String str, char search) {
        return findIndexOf(str, search, search);
    } //  findIndexOf

    /**
     * Find index of search characters in str. This ignores content in () and 'texts'
     *
     * @param str     string
     * @param search1 first search character
     * @param search2 second search character (or)
     * @return index or -1 if not found
     */
    public static int findIndexOf(String str, char search1, char search2) {
        if (str == null) return -1;
        //
        int endIndex = -1;
        int parCount = 0;
        boolean ignoringText = false;
        int size = str.length();
        while (++endIndex < size) {
            char c = str.charAt(endIndex);
            if (c == '\'') ignoringText = !ignoringText;
            else if (!ignoringText) {
                if (parCount == 0 && (c == search1 || c == search2)) return endIndex;
                else if (c == ')') parCount--;
                else if (c == '(') parCount++;
            }
        }
        return -1;
    } //  findIndexOf

    /**
     * Find index of search character in str. This ignores content in () and 'texts'
     *
     * @param str    string
     * @param search search character
     * @return index or -1 if not found
     */
    public static int findIndexOf(String str, String search) {
        if (str == null || search == null || search.length() == 0) return -1;
        //
        int endIndex = -1;
        int parCount = 0;
        boolean ignoringText = false;
        int size = str.length();
        while (++endIndex < size) {
            char c = str.charAt(endIndex);
            if (c == '\'') ignoringText = !ignoringText;
            else if (!ignoringText) {
                if (parCount == 0 && c == search.charAt(0)) {
                    if (str.substring(endIndex).startsWith(search)) return endIndex;
                } else if (c == ')') parCount--;
                else if (c == '(') parCount++;
            }
        }
        return -1;
    } //  findIndexOf

    /**
     * Clean Ampersand (used to indicate shortcut)
     *
     * @param in input
     * @return cleaned string
     */
    public static String cleanAmp(String in) {
        if (in == null || in.length() == 0) return in;
        int pos = in.indexOf('&');
        if (pos == -1) return in;
        //
        if (pos + 1 < in.length() && in.charAt(pos + 1) != ' ')
            in = in.substring(0, pos) + in.substring(pos + 1);
        return in;
    } //	cleanAmp

    public static Timestamp removeTime(Timestamp ts) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }
} //  Util
