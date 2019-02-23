package org.compiere.util;

import org.idempiere.common.util.CCache;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Language;

import java.io.File;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Reads all Messages and stores them in a HashMap
 *
 * @author Jorg Janke
 * @version $Id: Msg.java,v 1.2 2006/07/30 00:54:36 jjanke Exp $
 */
public final class Msg extends BaseMsg {

    /**
     * Singleton
     */
    private static Msg s_msg = null;

    /**
     * Logger
     */
    private static CLogger s_log = CLogger.getCLogger(Msg.class);

    public final String COPYRIGHT = "\u00A9 1999-2016 iDempiere\u00AE";
    /**
     * The Map
     */
    private CCache<String, CCache<String, String>> m_languages =
            new CCache<String, CCache<String, String>>(null, "msg_lang", 2, 0, false);

    private CCache<String, CCache<String, String>> m_elementCache =
            new CCache<String, CCache<String, String>>(null, "msg_element", 2, 0, false);

    /**
     * ****************************************************************** Constructor
     */
    private Msg() {
    } // 	Mag

    /**
     * Get Message Object
     *
     * @return Msg
     */
    private static synchronized Msg get() {
        if (s_msg == null) s_msg = new Msg();
        return s_msg;
    } //	get

    /**
     * ************************************************************************ Get translated text
     * for AD_Message
     *
     * @param ad_language - Language
     * @param AD_Message  - Message Key
     * @return translated text
     */
    public static String getMsg(String ad_language, String AD_Message) {
        if (AD_Message == null || AD_Message.length() == 0) return "";
        //
        String AD_Language = ad_language;
        if (AD_Language == null || AD_Language.length() == 0)
            AD_Language = Language.getBaseAD_Language();
        //
        String retStr = get().lookup(AD_Language, AD_Message);
        //
        if (retStr == null || retStr.length() == 0) {
            s_log.warning("NOT found: " + AD_Message);
            return AD_Message;
        }

        return retStr;
    } //	getMsg

    /**
     * Get translated text message for AD_Message
     *
     * @param ctx        Context to retrieve language
     * @param AD_Message - Message Key
     * @return translated text
     */
    public static String getMsg(Properties ctx, String AD_Message) {
        return getMsg(Env.getADLanguage(ctx), AD_Message);
    } //  getMeg

    /**
     * Get translated text message for AD_Message
     *
     * @param ad_language - Language
     * @param AD_Message  - Message Key
     * @param getText     if true only return Text, if false only return Tip
     * @return translated text
     */
    public static String getMsg(String ad_language, String AD_Message, boolean getText) {
        String retStr = getMsg(ad_language, AD_Message);
        int pos = retStr.indexOf(Companion.getSEPARATOR());
        //  No Tip
        if (pos == -1) {
            if (getText) return retStr;
            else return "";
        } else //  with Tip
        {
            if (getText) retStr = retStr.substring(0, pos);
            else {
                int start = pos + Companion.getSEPARATOR().length();
                //	int end = retStr.length();
                retStr = retStr.substring(start);
            }
        }
        return retStr;
    } //	getMsg

    /**
     * Get translated text message for AD_Message
     *
     * @param ctx        Context to retrieve language
     * @param AD_Message Message Key
     * @param getText    if true only return Text, if false only return Tip
     * @return translated text
     */
    public static String getMsg(Properties ctx, String AD_Message, boolean getText) {
        return getMsg(Env.getADLanguage(ctx), AD_Message, getText);
    } //  getMsg

    /**
     * Get clear text for AD_Message with parameters
     *
     * @param ctx        Context to retrieve language
     * @param AD_Message Message key
     * @param args       MessageFormat arguments
     * @return translated text
     * @see java.text.MessageFormat for formatting options
     */
    public static String getMsg(Properties ctx, String AD_Message, Object[] args) {
        return getMsg(Env.getADLanguage(ctx), AD_Message, args);
    } //	getMsg

    /**
     * Get clear text for AD_Message with parameters
     *
     * @param ad_language Language
     * @param AD_Message  Message key
     * @param args        MessageFormat arguments
     * @return translated text
     * @see java.text.MessageFormat for formatting options
     */
    public static String getMsg(String ad_language, String AD_Message, Object[] args) {
        String msg = getMsg(ad_language, AD_Message);
        String retStr = msg;
        try {
            retStr = MessageFormat.format(msg, args); // 	format string
        } catch (Exception e) {
            s_log.log(Level.SEVERE, msg, e);
        }
        return retStr;
    } //	getMsg

    /**
     * ************************************************************************ Get Translation for
     * Element
     *
     * @param ad_language language
     * @param ColumnName  column name
     * @param isSOTrx     if false PO terminology is used (if exists)
     * @return Name of the Column or "" if not found
     */
    public static String getElement(String ad_language, String ColumnName, boolean isSOTrx) {
        if (ColumnName == null || ColumnName.equals("")) return "";
        String AD_Language = ad_language;
        if (AD_Language == null || AD_Language.length() == 0)
            AD_Language = Language.getBaseAD_Language();

        Msg msg = get();
        CCache<String, String> cache = msg.getElementMap(AD_Language);
        String key = ColumnName + "|" + isSOTrx;
        String retStr = cache.get(key);
        if (retStr != null) return retStr;

        retStr = BaseMsgKt.getElement(ad_language, ColumnName, isSOTrx);

        cache.put(key, retStr);
        return retStr;
    } //  getElement

    /**
     * Get Translation for Element using Sales terminology
     *
     * @param ctx        context
     * @param ColumnName column name
     * @return Name of the Column or "" if not found
     */
    public static String getElement(Properties ctx, String ColumnName) {
        return getElement(Env.getADLanguage(ctx), ColumnName, true);
    } //  getElement

    /**
     * Get Translation for Element
     *
     * @param ctx        context
     * @param ColumnName column name
     * @param isSOTrx    sales transaction
     * @return Name of the Column or "" if not found
     */
    public static String getElement(Properties ctx, String ColumnName, boolean isSOTrx) {
        return getElement(Env.getADLanguage(ctx), ColumnName, isSOTrx);
    } //  getElement

    /**
     * ************************************************************************ "Translate" text.
     *
     * <pre>
     * 	- Check AD_Message.AD_Message 	->	MsgText
     * 	- Check AD_Element.ColumnName	->	Name
     *  </pre>
     *
     * <p>If checking AD_Element, the SO terminology is used.
     *
     * @param ad_language Language
     * @param isSOTrx     sales order context
     * @param text        Text - MsgText or Element Name
     * @return translated text or original text if not found
     */
    public static String translate(String ad_language, boolean isSOTrx, String text) {
        if (text == null || text.equals("")) return "";
        String AD_Language = ad_language;
        if (AD_Language == null || AD_Language.length() == 0)
            AD_Language = Language.getBaseAD_Language();

        //	Check AD_Message
        String retStr = get().lookup(AD_Language, text);
        if (retStr != null) return retStr;

        //	Check AD_Element
        retStr = getElement(AD_Language, text, isSOTrx);
        if (!retStr.equals("")) return retStr.trim();

        //	Nothing found
        if (!text.startsWith("*")) s_log.warning("NOT found: " + text);
        return text;
    } //	translate

    /**
     * * "Translate" text (SO Context).
     *
     * <pre>
     * 	- Check AD_Message.AD_Message 	->	MsgText
     * 	- Check AD_Element.ColumnName	->	Name
     *  </pre>
     *
     * <p>If checking AD_Element, the SO terminology is used.
     *
     * @param ad_language Language
     * @param text        Text - MsgText or Element Name
     * @return translated text or original text if not found
     */
    public static String translate(String ad_language, String text) {
        return translate(ad_language, true, text);
    } //	translate

    /**
     * "Translate" text.
     *
     * <pre>
     * 	- Check AD_Message.AD_Message 	->	MsgText
     * 	- Check AD_Element.ColumnName	->	Name
     *  </pre>
     *
     * @param ctx  Context
     * @param text Text - MsgText or Element Name
     * @return translated text or original text if not found
     */
    public static String translate(Properties ctx, String text) {
        if (text == null || text.length() == 0) return text;
        String s = ctx.getProperty(text);
        if (s != null && s.length() > 0) return s;
        return translate(Env.getADLanguage(ctx), Env.isSOTrx(ctx), text);
    } //  translate

    /**
     * Translate elements enclosed in "@" (at sign)
     *
     * @param ctx  Context
     * @param text Text
     * @return translated text or original text if not found
     */
    public static String parseTranslation(Properties ctx, String text) {
        if (text == null || text.length() == 0) return text;

        String inStr = text;
        String token;
        StringBuilder outStr = new StringBuilder();

        int i = inStr.indexOf('@');
        while (i != -1) {
            outStr.append(inStr, 0, i); // up to @
            inStr = inStr.substring(i + 1); // from first @

            int j = inStr.indexOf('@'); // next @
            if (j < 0) // no second tag
            {
                inStr = "@" + inStr;
                break;
            }

            token = inStr.substring(0, j);
            outStr.append(translate(ctx, token)); // replace context

            inStr = inStr.substring(j + 1); // from second @
            i = inStr.indexOf('@');
        }

        outStr.append(inStr); // 	add remainder
        return outStr.toString();
    } //  parseTranslation

    /**
     * Get Language specific Message Map
     *
     * @param ad_language Language Key
     * @return HashMap of Language
     */
    private CCache<String, String> getMsgMap(String ad_language) {
        String AD_Language = ad_language;
        if (AD_Language == null || AD_Language.length() == 0)
            AD_Language = Language.getBaseAD_Language();
        //  Do we have the language ?
        CCache<String, String> retValue = m_languages.get(AD_Language);
        if (retValue != null && retValue.size() > 0) return retValue;

        //  Load Language
        retValue = initMsg(AD_Language);
        if (retValue != null) {
            m_languages.put(AD_Language, retValue);
            return retValue;
        }
        return retValue;
    } //  getMsgMap

    private CCache<String, String> getElementMap(String ad_language) {
        String AD_Language = ad_language;
        if (AD_Language == null || AD_Language.length() == 0)
            AD_Language = Language.getBaseAD_Language();
        //  Do we have the language ?
        CCache<String, String> retValue = m_elementCache.get(AD_Language);
        if (retValue != null && retValue.size() > 0) return retValue;

        retValue = new CCache<String, String>("element", 100, 0, false, 0);
        m_elementCache.put(AD_Language, retValue);
        return retValue;
    }

    /**
     * Lookup term
     *
     * @param AD_Language language
     * @param text        text
     * @return translated term or null
     */
    private String lookup(String AD_Language, String text) {
        if (text == null) return null;
        if (AD_Language == null || AD_Language.length() == 0) return text;
        //  hardcoded trl
        if (text.equals("/") || text.equals("\\")) return File.separator;
        if (text.equals(";") || text.equals(":")) return File.pathSeparator;
        if (text.equals("bat") || text.equals("sh")) {
            if (System.getProperty("os.name").startsWith("Win")) return "bat";
            return "sh";
        }
        if (text.equals("CopyRight")) return COPYRIGHT;
        //
        CCache<String, String> langMap = getMsgMap(AD_Language);
        if (langMap == null) return null;
        return langMap.get(text);
    } //  lookup
} //	Msg
