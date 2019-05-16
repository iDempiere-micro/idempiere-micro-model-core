package org.idempiere.common.util;

import software.hsharp.core.util.Environment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

/**
 * System Environment and static variables.
 *
 * @author Jorg Janke
 * @author Teo Sarca, www.arhipac.ro
 * <li>BF [ 1619390 ] Use default desktop browser as external browser
 * <li>BF [ 2017987 ] Env.getContext(TAB_INFO) should NOT use global context
 * <li>FR [ 2392044 ] Introduce Env.WINDOW_MAIN
 * @version $Id: Env.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
 */
public final class Env {

    public static final String AD_ROLE_ID = "#AD_Role_ID";

    public static final String AD_USER_ID = "#AD_User_ID";

    public static final String AD_ORG_ID = "#AD_Org_ID";

    public static final String AD_CLIENT_ID = "#AD_Client_ID";

    /**
     * Tab for Info
     */
    public static final int TAB_INFO = 1113;

    public static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * Context Language identifier
     */
    public static final String LANGUAGE = "#AD_Language";
    /**
     * Big Decimal 0
     */
    public static final BigDecimal ZERO = BigDecimal.valueOf(0.0);
    /**
     * Big Decimal 1
     */
    public static final BigDecimal ONE = BigDecimal.valueOf(1.0);

    /**
     * Big Decimal 100
     */
    public static final BigDecimal ONEHUNDRED = BigDecimal.valueOf(100.0);
    /**
     * New Line
     */
    public static final String NL = System.getProperty("line.separator");

    /**
     * Logger
     */
    private static CLogger log = CLogger.getCLogger(Env.class);

    /* Static initializer */
    static {
        try {
            //  Set English as default Language
            getCtx().put(LANGUAGE, Language.getBaseAD_Language());
        } catch (Exception ex) { // nothing too much to do here
        }
    } //  static

    /**
     * Get Context
     *
     * @return Properties
     */
    public static final Properties getCtx() {
        return getContextProvider().getContext();
    } //  getCtx

    public static ContextProvider getContextProvider() {
        return ServerContextProvider.INSTANCE;
    }

    /**
     * JDBC Timestamp Format yyyy-mm-dd hh:mm:ss
     *
     * @return timestamp format
     */
    public static SimpleDateFormat getDefaultTimestampFormat() {
        return new SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT);
    } //  getTimestampFormat_JDBC

    /**
     * Get global Value of Context
     *
     * @param context context key
     * @return value or ""
     */
    public static String getContext(String context) {
        return Environment.Companion.getCurrent().getContext().getProperty(context, "");
    } //	getContext

    /**
     * Get Value of Context for Window. if not found global context if available and enabled
     *
     * @param WindowNo   window
     * @param context    context key
     * @param onlyWindow if true, no defaults are used unless explicitly asked for
     * @return value or ""
     */
    public static String getContext(
            int WindowNo, String context, boolean onlyWindow) {
        if (context == null) throw new IllegalArgumentException("Require Context");
        String s = Environment.Companion.getCurrent().getContext().getProperty(WindowNo + "|" + context);
        if (s == null) {
            //	Explicit Base Values
            if (context.startsWith("#") || context.startsWith("$") || context.startsWith("P|"))
                return getContext(context);
            if (onlyWindow) //	no Default values
                return "";
            return getContext("#" + context);
        }
        return s;
    } //	getContext

    /**
     * Get Context and convert it to an integer (0 if error)
     *
     * @param context context key
     * @return value
     */
    public static int getContextAsInt(String context) {
        String s = getContext(context);
        if (s.length() == 0) s = getContext(0, context, false); // 	search 0 and defaults
        if (s.length() == 0) return 0;
        if (s.equals("null")) return -1;
        //
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            log.log(Level.SEVERE, "(" + context + ") = " + s, e);
        }
        return 0;
    } //	getContextAsInt

    /**
     * Is Sales Order Trx
     *
     * @return true if SO (default)
     */
    public static boolean isSOTrx() {
        /* DAP String s = getContext("IsSOTrx");
        return s == null || !s.equals("N");*/
        return false;
    } //	isSOTrx

    /**
     * Get Context and convert it to a Timestamp if error return today's date
     *
     * @return Timestamp
     */
    public static Timestamp getContextAsDate() {
        return Timestamp.valueOf(LocalDateTime.now());
    } //	getContextAsDate

    /**
     * Get Context and convert it to a Timestamp if error return today's date
     *
     * @param WindowNo window no
     * @param context  context key
     * @return Timestamp
     */
    public static Timestamp getContextAsDate(int WindowNo, String context) {
        String s = getContext(WindowNo, context, false);
        //	JDBC Format YYYY-MM-DD	example 2000-09-11 00:00:00.0
        if (s == null || s.equals("")) {
            if (!"#date".equalsIgnoreCase(context)) {
                log.log(Level.WARNING, "No value for: " + context);
            }
            return new Timestamp(System.currentTimeMillis());
        }

        Date date;
        try {
            date = getDefaultTimestampFormat().parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        return new Timestamp(date.getTime());
        // KTU
    } //	getContextAsDate


    /**
     * Get Login AD_Client_ID
     *
     * @return login AD_Client_ID
     */
    public static int getClientId() {
        return Environment.Companion.getCurrent().getClientId();
    } //	getClientId

    /**
     * Get Login AD_Org_ID
     *
     * @return login AD_Org_ID
     */
    public static int getOrgId() {
        return Environment.Companion.getCurrent().getOrgId();
    } //	getOrgId

    /**
     * Get Login AD_User_ID
     *
     * @return login AD_User_ID
     */
    public static int getUserId() {
        return Environment.Companion.getCurrent().getUserId();
    } //	getUserId

    /**
     * Get Login AD_Role_ID
     *
     * @return login AD_Role_ID
     */
    public static int getRoleId() {
        return Env.getContextAsInt(AD_ROLE_ID);
    } //	getRoleId

    /**
     * Check Base Language
     *
     * @return true if base language and table not translated
     */
    public static boolean isBaseLanguage() {
        return Language.isBaseLanguage(getADLanguage());
    } //	isBaseLanguage

    /**
     * Check Base Language
     *
     * @param AD_Language language
     * @return true if base language and table not translated
     */
    public static boolean isBaseLanguage(String AD_Language) {
        return Language.isBaseLanguage(AD_Language);
    } //	isBaseLanguage

    /**
     * Get System AD_Language
     *
     * @return AD_Language eg. en_US
     */
    public static String getADLanguage() {
        return Language.getBaseAD_Language();
    } //	getADLanguage

    /**
     * Get System Language
     *
     * @return Language
     */
    public static Language getLanguage() {
        return Language.getBaseLanguage();
    } //	getLanguage


    /**
     * Parse Context replaces global or Window context @tag@ with actual value.
     *
     * @param WindowNo         Number of Window
     * @param value            Message to be parsed
     * @param onlyWindow       if true, no defaults are used
     * @param ignoreUnparsable if true, unsuccessful @return parsed String or "" if not successful and
     *                         ignoreUnparsable
     * @return parsed context
     * @tag@ are ignored otherwise "" is returned
     */
    public static String parseContext(
            int WindowNo, String value, boolean onlyWindow, boolean ignoreUnparsable) {
        if (value == null || value.length() == 0) return "";

        String token;
        String inStr = value;
        StringBuilder outStr = new StringBuilder();

        int i = inStr.indexOf('@');
        while (i != -1) {
            outStr.append(inStr, 0, i); // up to @
            inStr = inStr.substring(i + 1); // from first @

            int j = inStr.indexOf('@'); // next @
            if (j < 0) {
                if (log.isLoggable(Level.INFO)) log.log(Level.INFO, "No second tag: " + inStr);
                // not context variable, add back @ and break
                outStr.append("@");
                break;
            }

            token = inStr.substring(0, j);

            // IDEMPIERE-194 Handling null context variable
            String defaultV = null;
            int idx = token.indexOf(":"); // 	or clause
            if (idx >= 0) {
                defaultV = token.substring(idx + 1);
                token = token.substring(0, idx);
            }

            String ctxInfo = getContext(WindowNo, token, onlyWindow); // get context
            if (ctxInfo.length() == 0 && (token.startsWith("#") || token.startsWith("$")))
                ctxInfo = getContext(token); // get global context

            if (ctxInfo.length() == 0 && defaultV != null) ctxInfo = defaultV;

            if (ctxInfo.length() == 0) {
                if (log.isLoggable(Level.CONFIG))
                    log.config("No Context Win=" + WindowNo + " for: " + token);
                if (!ignoreUnparsable) return ""; // 	token not found
            } else outStr.append(ctxInfo); // replace context with Context

            inStr = inStr.substring(j + 1); // from second @
            i = inStr.indexOf('@');
        }
        outStr.append(inStr); // add the rest of the string

        return outStr.toString();
    } //	parseContext

} //  Env
