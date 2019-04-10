package org.compiere.util

import org.idempiere.common.util.CCache
import org.idempiere.common.util.CLogger
import org.idempiere.common.util.Env
import org.idempiere.common.util.Language

import java.io.File
import java.text.MessageFormat

/**
 * Get Translation for Element using Sales terminology
 *
 * @param ColumnName column name
 * @return Name of the Column or "" if not found
 */
fun getElementTranslation(ColumnName: String): String {
    return getElementTranslation(Env.getADLanguage(), ColumnName, true)
} //  getElement

/**
 * Get Translation for Element
 *
 * @param ColumnName column name
 * @param isSOTrx sales transaction
 * @return Name of the Column or "" if not found
 */
fun getElementTranslation(ColumnName: String, isSOTrx: Boolean): String {
    return getElementTranslation(Env.getADLanguage(), ColumnName, isSOTrx)
} //  getElement

/**
 * Get Translation for Element
 *
 * @param ad_language language
 * @param ColumnName column name
 * @param isSOTrx if false PO terminology is used (if exists)
 * @return Name of the Column or "" if not found
 */
fun getElementTranslation(ad_language: String, ColumnName: String?, isSOTrx: Boolean): String {
    if (ColumnName == null || ColumnName == "") return ""
    val language = if (ad_language.isEmpty()) Language.getBaseAD_Language() else ad_language

    val msg = Msg.get()
    val cache = msg.getElementMap(language)
    val key = "$ColumnName|$isSOTrx"
    var retStr: String? = cache[key]
    if (retStr != null) return retStr

    retStr = getElement(ad_language, ColumnName, isSOTrx)

    cache[key] = retStr
    return retStr
} //  getElement

/**
 * "Translate" text.
 *
 * <pre>
 * - Check AD_Message.AD_Message 	->	MsgText
 * - Check AD_Element.ColumnName	->	Name
</pre> *
 *
 * @param text Text - MsgText or Element Name
 * @return translated text or original text if not found
 */
fun translate(text: String?): String? {
    return if (text == null || text.isEmpty()) text else Msg.translate(
        Env.getADLanguage(),
        Env.isSOTrx(),
        text
    )
} //  translate

/**
 * Get clear text for AD_Message with parameters
 *
 * @param AD_Message Message key
 * @param args MessageFormat arguments
 * @return translated text
 * @see java.text.MessageFormat for formatting options
 */
fun getMsg(AD_Message: String, args: Array<Any>): String? {
    return getMsg(Env.getADLanguage(), AD_Message, args)
} // 	getMsg

/**
 * Get translated text message for AD_Message
 *
 * @param AD_Message - Message Key
 * @return translated text
 */
fun getMsg(AD_Message: String): String {
    return Msg.getMsg(Env.getADLanguage(), AD_Message)
} //  getMeg

/**
 * Get clear text for AD_Message with parameters
 *
 * @param ad_language Language
 * @param AD_Message Message key
 * @param args MessageFormat arguments
 * @return translated text
 * @see java.text.MessageFormat for formatting options
 */
fun getMsg(ad_language: String, AD_Message: String, args: Array<Any>): String? {
    val msg = Msg.getMsg(ad_language, AD_Message)
    var retStr = msg
    try {
        retStr = MessageFormat.format(msg, *args) // 	format string
    } catch (e: Exception) {
    }

    return retStr
} // 	getMsg

/**
 * Translate elements enclosed in "@" (at sign)
 *
 * @param text Text
 * @return translated text or original text if not found
 */
fun parseTranslation(text: String?): String? {
    if (text == null || text.isEmpty()) return text

    var inStr: String = text
    var token: String
    val outStr = StringBuilder()

    var i = inStr.indexOf('@')
    while (i != -1) {
        outStr.append(inStr, 0, i) // up to @
        inStr = inStr.substring(i + 1) // from first @

        val j = inStr.indexOf('@') // next @
        if (j < 0)
        // no second tag
        {
            inStr = "@$inStr"
            break
        }

        token = inStr.substring(0, j)
        outStr.append(translate(token)) // replace context

        inStr = inStr.substring(j + 1) // from second @
        i = inStr.indexOf('@')
    }

    outStr.append(inStr) // 	add remainder
    return outStr.toString()
} //  parseTranslation

/**
 * Get translated text message for AD_Message
 *
 * @param AD_Message Message Key
 * @param getText if true only return Text, if false only return Tip
 * @return translated text
 */
fun getMsg(AD_Message: String, getText: Boolean): String {
    return Msg.getMsg(Env.getADLanguage(), AD_Message, getText)
} //  getMsg

/**
 * * "Translate" text (SO Context).
 *
 * <pre>
 * - Check AD_Message.AD_Message 	->	MsgText
 * - Check AD_Element.ColumnName	->	Name
</pre> *
 *
 *
 * If checking AD_Element, the SO terminology is used.
 *
 * @param ad_language Language
 * @param text Text - MsgText or Element Name
 * @return translated text or original text if not found
 */
fun translate(ad_language: String, text: String): String {
    return Msg.translate(ad_language, true, text)
} // 	translate

/**
 * Reads all Messages and stores them in a HashMap
 *
 * @author Jorg Janke
 * @version $Id: Msg.java,v 1.2 2006/07/30 00:54:36 jjanke Exp $
 */
class Msg
/**
 * ****************************************************************** Constructor
 */
private constructor() // 	Mag
    : BaseMsg() {

    val COPYRIGHT = "\u00A9 1999-2016 iDempiere\u00AE"
    /**
     * The Map
     */
    private val m_languages = CCache<String, CCache<String, String>>(null, "msg_lang", 2, 0, false)

    private val m_elementCache = CCache<String, CCache<String, String>>(null, "msg_element", 2, 0, false)

    /**
     * Get Language specific Message Map
     *
     * @param ad_language Language Key
     * @return HashMap of Language
     */
    private fun getMsgMap(ad_language: String): CCache<String, String> {
        var AD_Language: String? = ad_language
        if (AD_Language == null || AD_Language.length == 0)
            AD_Language = Language.getBaseAD_Language()
        //  Do we have the language ?
        var retValue: CCache<String, String>? = m_languages[AD_Language]
        if (retValue != null && retValue.size > 0) return retValue

        //  Load Language
        retValue = initMsg(AD_Language)
        m_languages[AD_Language] = retValue
        return retValue
    } //  getMsgMap

    internal fun getElementMap(ad_language: String): CCache<String, String> {
        var AD_Language: String? = ad_language
        if (AD_Language == null || AD_Language.length == 0)
            AD_Language = Language.getBaseAD_Language()
        //  Do we have the language ?
        var retValue: CCache<String, String>? = m_elementCache[AD_Language]
        if (retValue != null && retValue.size > 0) return retValue

        retValue = CCache("element", 100, 0, false, 0)
        m_elementCache[AD_Language] = retValue
        return retValue
    }

    /**
     * Lookup term
     *
     * @param AD_Language language
     * @param text text
     * @return translated term or null
     */
    private fun lookup(AD_Language: String?, text: String?): String? {
        if (text == null) return null
        if (AD_Language == null || AD_Language.length == 0) return text
        //  hardcoded trl
        if (text == "/" || text == "\\") return File.separator
        if (text == ";" || text == ":") return File.pathSeparator
        if (text == "bat" || text == "sh") {
            return if (System.getProperty("os.name").startsWith("Win")) "bat" else "sh"
        }
        if (text == "CopyRight") return COPYRIGHT
        //
        val langMap = getMsgMap(AD_Language)
        return langMap[text]
    } //  lookup

    companion object {

        /**
         * Singleton
         */
        private var s_msg: Msg? = null

        /**
         * Logger
         */
        private val s_log = CLogger.getCLogger(Msg::class.java)

        /**
         * Get Message Object
         *
         * @return Msg
         */
        @Synchronized
        internal fun get(): Msg {
            if (s_msg == null) s_msg = Msg()
            return s_msg!!
        } // 	get

        /**
         * ************************************************************************ Get translated text
         * for AD_Message
         *
         * @param ad_language - Language
         * @param AD_Message - Message Key
         * @return translated text
         */
        fun getMsg(ad_language: String, AD_Message: String?): String {
            if (AD_Message == null || AD_Message.length == 0) return ""
            //
            var AD_Language: String? = ad_language
            if (AD_Language == null || AD_Language.length == 0)
                AD_Language = Language.getBaseAD_Language()
            //
            val retStr = get().lookup(AD_Language, AD_Message)
            //
            if (retStr == null || retStr.length == 0) {
                s_log.warning("NOT found: $AD_Message")
                return AD_Message
            }

            return retStr
        } // 	getMsg

        /**
         * Get translated text message for AD_Message
         *
         * @param ad_language - Language
         * @param AD_Message - Message Key
         * @param getText if true only return Text, if false only return Tip
         * @return translated text
         */
        fun getMsg(ad_language: String, AD_Message: String, getText: Boolean): String {
            var retStr = getMsg(ad_language, AD_Message)
            val pos = retStr.indexOf(BaseMsg.SEPARATOR)
            //  No Tip
            if (pos == -1) {
                return if (getText)
                    retStr
                else
                    ""
            } else
            //  with Tip
            {
                if (getText)
                    retStr = retStr.substring(0, pos)
                else {
                    val start = pos + BaseMsg.SEPARATOR.length
                    // 	int end = retStr.length();
                    retStr = retStr.substring(start)
                }
            }
            return retStr
        } // 	getMsg

        /**
         * ************************************************************************ "Translate" text.
         *
         * <pre>
         * - Check AD_Message.AD_Message 	->	MsgText
         * - Check AD_Element.ColumnName	->	Name
        </pre> *
         *
         *
         * If checking AD_Element, the SO terminology is used.
         *
         * @param ad_language Language
         * @param isSOTrx sales order context
         * @param text Text - MsgText or Element Name
         * @return translated text or original text if not found
         */
        fun translate(ad_language: String, isSOTrx: Boolean, text: String?): String {
            if (text == null || text == "") return ""
            var AD_Language: String? = ad_language
            if (AD_Language == null || AD_Language.length == 0)
                AD_Language = Language.getBaseAD_Language()

            // 	Check AD_Message
            var retStr = get().lookup(AD_Language, text)
            if (retStr != null) return retStr

            // 	Check AD_Element
            retStr = getElement(AD_Language, text, isSOTrx)
            if (retStr != "") return retStr.trim { it <= ' ' }

            // 	Nothing found
            if (!text.startsWith("*")) s_log.warning("NOT found: $text")
            return text
        } // 	translate
    }
} // 	Msg
