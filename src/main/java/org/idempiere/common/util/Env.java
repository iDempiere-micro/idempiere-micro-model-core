package org.idempiere.common.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
     * ************************************************************************ Application Context
     */
    /**
     * Big Decimal 100
     */
    public static final BigDecimal ONEHUNDRED = BigDecimal.valueOf(100.0);
    /**
     * New Line
     */
    public static final String NL = System.getProperty("line.separator");

    private static List<IEnvEventListener> eventListeners = new ArrayList<IEnvEventListener>();
    /**
     * Logger
     */
    private static CLogger log = CLogger.getCLogger(Env.class);

    /** Static initializer */
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
     * @param provider
     * @deprecated
     */
    public static void setContextProvider(ContextProvider provider) {
    }

    /**
     * Set Global Context to Value
     *
     * @param ctx     context
     * @param context context key
     * @param value   context value
     */
    public static void setContext(Properties ctx, String context, String value) {
        if (ctx == null || context == null) return;
        if (log.isLoggable(Level.FINER)) log.finer("Context " + context + "==" + value);
        //
        if (value == null || value.length() == 0) ctx.remove(context);
        else ctx.setProperty(context, value);
    } //	setContext

    /**
     * JDBC Timestamp Format yyyy-mm-dd hh:mm:ss
     *
     * @return timestamp format
     */
    public static SimpleDateFormat getTimestampFormat_Default() {
        return new SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT);
    } //  getTimestampFormat_JDBC

    /**
     * Set Global Context to (int) Value
     *
     * @param ctx     context
     * @param context context key
     * @param value   context value
     */
    public static void setContext(Properties ctx, String context, int value) {
        if (ctx == null || context == null) return;
        if (log.isLoggable(Level.FINER)) log.finer("Context " + context + "==" + value);
        //
        ctx.setProperty(context, String.valueOf(value));
    } //	setContext

    /**
     * Set Context for Window to Value
     *
     * @param ctx      context
     * @param WindowNo window no
     * @param context  context key
     * @param value    context value
     */
    public static void setContext(Properties ctx, int WindowNo, String context, String value) {
        if (ctx == null || context == null) return;
        if (log.isLoggable(Level.FINER))
            log.finer("Context(" + WindowNo + ") " + context + "==" + value);
        //
        if (value == null || value.equals("")) ctx.remove(WindowNo + "|" + context);
        else ctx.setProperty(WindowNo + "|" + context, value);
    } //	setContext

    /**
     * Set Context for Window to int Value
     *
     * @param ctx      context
     * @param WindowNo window no
     * @param context  context key
     * @param value    context value
     */
    public static void setContext(Properties ctx, int WindowNo, String context, int value) {
        if (ctx == null || context == null) return;
        if (log.isLoggable(Level.FINER))
            log.finer("Context(" + WindowNo + ") " + context + "==" + value);
        //
        ctx.setProperty(WindowNo + "|" + context, String.valueOf(value));
    } //	setContext

    private static String convert(boolean value) {
        return value ? "Y" : "N";
    }

    /**
     * Set Context for Window & Tab to Value
     *
     * @param ctx      context
     * @param WindowNo window no
     * @param TabNo    tab no
     * @param context  context key
     * @param value    context value
     */
    public static void setContext(
            Properties ctx, int WindowNo, int TabNo, String context, String value) {
        if (ctx == null || context == null) return;
        if (log.isLoggable(Level.FINEST))
            log.finest("Context(" + WindowNo + "," + TabNo + ") " + context + "==" + value);
        //
        if (value == null)
            if (context.endsWith("_ID"))
                // TODO: Research potential problems with tables with Record_ID=0
                value = "0";
            else value = "";
        ctx.setProperty(WindowNo + "|" + TabNo + "|" + context, value);
    } //	setContext

    /**
     * Set Auto Commit
     *
     * @param ctx        context
     * @param autoCommit auto commit (save) @Deprecated user setProperty instead
     */
    @Deprecated
    public static void setAutoCommit(Properties ctx, boolean autoCommit) {
        if (ctx == null) return;
        ctx.setProperty("AutoCommit", convert(autoCommit));
    } //	setAutoCommit

    /**
     * Set Auto New Record
     *
     * @param ctx     context
     * @param autoNew auto new record @Deprecated user setProperty instead
     */
    @Deprecated
    public static void setAutoNew(Properties ctx, boolean autoNew) {
        if (ctx == null) return;
        ctx.setProperty("AutoNew", convert(autoNew));
    } //	setAutoNew

    /**
     * Set SO Trx
     *
     * @param ctx     context
     * @param isSOTrx SO Context
     */
    public static void setSOTrx(Properties ctx, boolean isSOTrx) {
        if (ctx == null) return;
        ctx.setProperty("IsSOTrx", convert(isSOTrx));
    } //	setSOTrx

    /**
     * Get global Value of Context
     *
     * @param ctx     context
     * @param context context key
     * @return value or ""
     */
    public static String getContext(Properties ctx, String context) {
        if (ctx == null || context == null) throw new IllegalArgumentException("Require Context");
        return ctx.getProperty(context, "");
    } //	getContext

    /**
     * Get Value of Context for Window. if not found global context if available and enabled
     *
     * @param ctx        context
     * @param WindowNo   window
     * @param context    context key
     * @param onlyWindow if true, no defaults are used unless explicitly asked for
     * @return value or ""
     */
    public static String getContext(
            Properties ctx, int WindowNo, String context, boolean onlyWindow) {
        if (ctx == null) throw new IllegalArgumentException("No Ctx");
        if (context == null) throw new IllegalArgumentException("Require Context");
        String s = ctx.getProperty(WindowNo + "|" + context);
        if (s == null) {
            //	Explicit Base Values
            if (context.startsWith("#") || context.startsWith("$") || context.startsWith("P|"))
                return getContext(ctx, context);
            if (onlyWindow) //	no Default values
                return "";
            return getContext(ctx, "#" + context);
        }
        return s;
    } //	getContext

    /**
     * Get Value of Context for Window & Tab, if not found global context if available. If TabNo is
     * TAB_INFO only tab's context will be checked.
     *
     * @param ctx      context
     * @param WindowNo window no
     * @param TabNo    tab no
     * @param context  context key
     * @return value or ""
     */
    public static String getContext(Properties ctx, int WindowNo, int TabNo, String context) {
        if (ctx == null || context == null) throw new IllegalArgumentException("Require Context");
        String s = ctx.getProperty(WindowNo + "|" + TabNo + "|" + context);
        // If TAB_INFO, don't check Window and Global context - teo_sarca BF [ 2017987 ]
        if (TAB_INFO == TabNo) return s != null ? s : "";
        //
        if (Util.isEmpty(s)) return getContext(ctx, WindowNo, context, false);
        return s;
    } //	getContext

    /**
     * Get Value of Context for Window & Tab, if not found global context if available. If TabNo is
     * TAB_INFO only tab's context will be checked.
     *
     * @param ctx      context
     * @param WindowNo window no
     * @param TabNo    tab no
     * @param context  context key
     * @param onlyTab  if true, no window value is searched
     * @return value or ""
     */
    public static String getContext(
            Properties ctx, int WindowNo, int TabNo, String context, boolean onlyTab) {
        return getContext(ctx, WindowNo, TabNo, context, onlyTab, onlyTab);
    }

    /**
     * Get Value of Context for Window & Tab, if not found global context if available. If TabNo is
     * TAB_INFO only tab's context will be checked.
     *
     * @param ctx        context
     * @param WindowNo   window no
     * @param TabNo      tab no
     * @param context    context key
     * @param onlyTab    if true, no window value is searched
     * @param onlyWindow if true, no global context will be searched
     * @return value or ""
     */
    public static String getContext(
            Properties ctx,
            int WindowNo,
            int TabNo,
            String context,
            boolean onlyTab,
            boolean onlyWindow) {
        if (ctx == null || context == null) throw new IllegalArgumentException("Require Context");
        String s = ctx.getProperty(WindowNo + "|" + TabNo + "|" + context);
        // If TAB_INFO, don't check Window and Global context - teo_sarca BF [ 2017987 ]
        if (TAB_INFO == TabNo) return s != null ? s : "";
        //
        if (Util.isEmpty(s) && !onlyTab) return getContext(ctx, WindowNo, context, onlyWindow);
        return s;
    } //	getContext

    /**
     * Get Context and convert it to an integer (0 if error)
     *
     * @param ctx     context
     * @param context context key
     * @return value
     */
    public static int getContextAsInt(Properties ctx, String context) {
        if (ctx == null || context == null) throw new IllegalArgumentException("Require Context");
        String s = getContext(ctx, context);
        if (s.length() == 0) s = getContext(ctx, 0, context, false); // 	search 0 and defaults
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
     * Get Context and convert it to an integer (0 if error)
     *
     * @param ctx      context
     * @param WindowNo window no
     * @param context  context key
     * @return value or 0
     */
    public static int getContextAsInt(Properties ctx, int WindowNo, String context) {
        String s = getContext(ctx, WindowNo, context, false);
        if (s.length() == 0) return 0;
        //
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            log.log(Level.SEVERE, "(" + context + ") = " + s, e);
        }
        return 0;
    } //	getContextAsInt

    /**
     * Is AutoCommit
     *
     * @param ctx context
     * @return true if auto commit
     */
    public static boolean isAutoCommit(Properties ctx) {
        if (ctx == null) throw new IllegalArgumentException("Require Context");
        String s = getContext(ctx, "AutoCommit");
        return s != null && s.equals("Y");
    } //	isAutoCommit

    /**
     * Is Auto New Record
     *
     * @param ctx context
     * @return true if auto new
     */
    public static boolean isAutoNew(Properties ctx) {
        if (ctx == null) throw new IllegalArgumentException("Require Context");
        String s = getContext(ctx, "AutoNew");
        return s != null && s.equals("Y");
    } //	isAutoNew

    /**
     * Is Sales Order Trx
     *
     * @param ctx context
     * @return true if SO (default)
     */
    public static boolean isSOTrx(Properties ctx) {
        String s = getContext(ctx, "IsSOTrx");
        return s == null || !s.equals("N");
    } //	isSOTrx

    /**
     * Get Context and convert it to a Timestamp if error return today's date
     *
     * @param ctx     context
     * @param context context key
     * @return Timestamp
     */
    public static Timestamp getContextAsDate(Properties ctx, String context) {
        return getContextAsDate(ctx, 0, context);
    } //	getContextAsDate

    /**
     * Get Context and convert it to a Timestamp if error return today's date
     *
     * @param ctx      context
     * @param WindowNo window no
     * @param context  context key
     * @return Timestamp
     */
    public static Timestamp getContextAsDate(Properties ctx, int WindowNo, String context) {
        if (ctx == null || context == null) throw new IllegalArgumentException("Require Context");
        String s = getContext(ctx, WindowNo, context, false);
        //	JDBC Format YYYY-MM-DD	example 2000-09-11 00:00:00.0
        if (s == null || s.equals("")) {
            if (!"#date".equalsIgnoreCase(context)) {
                log.log(Level.WARNING, "No value for: " + context);
            }
            return new Timestamp(System.currentTimeMillis());
        }

        // BUG:3075946 KTU - Fix Thai Date
    /*
    //  timestamp requires time
    if (s.trim().length() == 10)
    	s = s.trim() + " 00:00:00.0";
    else if (s.indexOf('.') == -1)
    	s = s.trim() + ".0";

    return Timestamp.valueOf(s);*/

        Date date = null;
        try {
            date = getTimestampFormat_Default().parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        Timestamp timeStampDate = new Timestamp(date.getTime());

        return timeStampDate;
        // KTU
    } //	getContextAsDate

    /** ************************************************************************ Language issues */

    /**
     * Get Login AD_Client_ID
     *
     * @param ctx context
     * @return login AD_Client_ID
     */
    public static int getClientId(Properties ctx) {
        return Env.getContextAsInt(ctx, AD_CLIENT_ID);
    } //	getClientId

    /**
     * Get Login AD_Org_ID
     *
     * @param ctx context
     * @return login AD_Org_ID
     */
    public static int getOrgId(Properties ctx) {
        return Env.getContextAsInt(ctx, AD_ORG_ID);
    } //	getOrgId

    /**
     * Get Login AD_User_ID
     *
     * @param ctx context
     * @return login AD_User_ID
     */
    public static int getAD_User_ID(Properties ctx) {
        return Env.getContextAsInt(ctx, AD_USER_ID);
    } //	getAD_User_ID

    /**
     * Get Login AD_Role_ID
     *
     * @param ctx context
     * @return login AD_Role_ID
     */
    public static int getAD_Role_ID(Properties ctx) {
        return Env.getContextAsInt(ctx, AD_ROLE_ID);
    } //	getAD_Role_ID

    /**
     * Check Base Language
     *
     * @param ctx       context
     * @param tableName table to be translated
     * @return true if base language and table not translated
     */
    public static boolean isBaseLanguage(Properties ctx, String tableName) {
        /**
         * if (isBaseTranslation(tableName)) return Language.isBaseLanguage (getADLanguage(ctx)); else
         * // No AD Table if (!isMultiLingualDocument(ctx)) return true; // access base table
         */
        return Language.isBaseLanguage(getADLanguage(ctx));
    } //	isBaseLanguage

    /**
     * Check Base Language
     *
     * @param AD_Language language
     * @param tableName   table to be translated
     * @return true if base language and table not translated
     */
    public static boolean isBaseLanguage(String AD_Language, String tableName) {
        /**
         * if (isBaseTranslation(tableName)) return Language.isBaseLanguage (AD_Language); else // No AD
         * Table if (!isMultiLingualDocument(s_ctx)) // Base Context return true; // access base table
         */
        return Language.isBaseLanguage(AD_Language);
    } //	isBaseLanguage

    /**
     * Get System AD_Language
     *
     * @param ctx context
     * @return AD_Language eg. en_US
     */
    public static String getADLanguage(Properties ctx) {
        if (ctx != null) {
            String lang = getContext(ctx, LANGUAGE);
            if (!Util.isEmpty(lang)) return lang;
        }
        return Language.getBaseAD_Language();
    } //	getADLanguage

    /**
     * Get System Language
     *
     * @param ctx context
     * @return Language
     */
    public static Language getLanguage(Properties ctx) {
        if (ctx != null) {
            String lang = getContext(ctx, LANGUAGE);
            if (!Util.isEmpty(lang)) return Language.getLanguage(lang);
        }
        return Language.getBaseLanguage();
    } //	getLanguage

    /**
     * ************************************************************************ Get Context as String
     * array with format: key == value
     *
     * @param ctx context
     * @return context string
     */
    public static String[] getEntireContext(Properties ctx) {
        if (ctx == null) throw new IllegalArgumentException("Require Context");
        Iterator<?> keyIterator = ctx.keySet().iterator();
        String[] sList = new String[ctx.size()];
        int i = 0;
        while (keyIterator.hasNext()) {
            Object key = keyIterator.next();
            sList[i++] = key.toString() + " == " + ctx.get(key).toString();
        }

        return sList;
    } //	getEntireContext

    /**
     * Parse Context replaces global or Window context @tag@ with actual value.
     *
     * @param ctx              context
     * @param WindowNo         Number of Window
     * @param value            Message to be parsed
     * @param onlyWindow       if true, no defaults are used
     * @param ignoreUnparsable if true, unsuccessful @return parsed String or "" if not successful and
     *                         ignoreUnparsable
     * @return parsed context
     * @tag@ are ignored otherwise "" is returned
     */
    public static String parseContext(
            Properties ctx, int WindowNo, String value, boolean onlyWindow, boolean ignoreUnparsable) {
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

            String ctxInfo = getContext(ctx, WindowNo, token, onlyWindow); // get context
            if (ctxInfo.length() == 0 && (token.startsWith("#") || token.startsWith("$")))
                ctxInfo = getContext(ctx, token); // get global context

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

    /**
     * Parse Context replaces global or Window context @tag@ with actual value.
     *
     * @param ctx              context
     * @param WindowNo         Number of Window
     * @param tabNo            Number of Tab
     * @param value            Message to be parsed
     * @param onlyTab          if true, no defaults are used
     * @param ignoreUnparsable if true, unsuccessful @return parsed String or "" if not successful and
     *                         ignoreUnparsable
     * @return parsed context
     * @tag@ are ignored otherwise "" is returned
     */
    public static String parseContext(
            Properties ctx,
            int WindowNo,
            int tabNo,
            String value,
            boolean onlyTab,
            boolean ignoreUnparsable) {
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

            String ctxInfo = getContext(ctx, WindowNo, tabNo, token, onlyTab); // get context
            if (ctxInfo.length() == 0 && (token.startsWith("#") || token.startsWith("$")))
                ctxInfo = getContext(ctx, token); // get global context

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

    /** ************************************************************************ Static Variables */

    /**
     * Prepare the context for calling remote server (for e.g, ejb), only default and global variables
     * are pass over. It is too expensive and also can have serialization issue if every remote call
     * to server is passing the whole client context.
     *
     * @param ctx
     * @return Properties
     */
    public static Properties getRemoteCallCtx(Properties ctx) {
        Properties p = new Properties();
        Set<Object> keys = ctx.keySet();
        for (Object key : keys) {
            if (!(key instanceof String)) continue;

            Object value = ctx.get(key);
            if (!(value instanceof String)) continue;

            p.put(key, value);
        }

        return p;
    }
} //  Env
