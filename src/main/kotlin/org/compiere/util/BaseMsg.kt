package org.compiere.util

import kotliquery.Row
import org.idempiere.common.util.CCache
import org.idempiere.common.util.Env
import org.idempiere.common.util.Language
import software.hsharp.core.util.DB
import software.hsharp.core.util.asResource
import software.hsharp.core.util.queryOf

/** Initial size of HashMap  */
private const val MAP_SIZE = 1500

internal fun getElement(ad_language: String?, ColumnName: String?, isSOTrx: Boolean): String {
    if (ColumnName == null || ColumnName == "") return ""
    var AD_Language: String? = ad_language
    if (AD_Language == null || AD_Language.length == 0)
        AD_Language = Language.getBaseAD_Language()

    var retStr: String? = null

    fun map(r: Row): Pair<String?, String?> {
        return Pair(r.stringOrNull(1), r.stringOrNull(2))
    }

    val loadQuery =
        if (AD_Language == null || AD_Language.length == 0 || Env.isBaseLanguage(AD_Language, "AD_Element")) {
                "/sql/getElementBase.sql".asResource { sql ->
                    queryOf(sql, listOf(ColumnName.toUpperCase())).map { map(it) }.asSingle
                }
            } else {
                "/sql/getElement.sql".asResource { sql ->
                    queryOf(sql, listOf(ColumnName.toUpperCase(), AD_Language)).map { map(it) }.asSingle
                }
        }

    val res = DB.current.run(loadQuery)
    retStr = res?.first
    if (!isSOTrx) {
        val temp = res?.second
        if (temp != null && temp.length > 0) retStr = temp
    }

    retStr = if (retStr == null) "" else retStr.trim { it <= ' ' }
    return retStr
}

open class BaseMsg {
    companion object {
        /** Separator between Msg and optional Tip  */
        protected val SEPARATOR = Env.NL + Env.NL
    }

    protected fun initMsg(language: String?): CCache<String, String> {
        val msg = CCache<String, String>("AD_Message", MAP_SIZE, 0, false, 0)

        fun processRow(row: Row): Boolean {
            val message = row.stringOrNull(1)
            val builder = StringBuilder()
            builder.append(row.stringOrNull(2))
            val tip = row.stringOrNull(3)
            //
            if (tip != null)
            // 	messageTip on next line, if exists
                builder.append(" ").append(SEPARATOR).append(tip)
            msg[message] = builder.toString()

            return true
        }

        val loadQuery =
            if (language == null ||
                language.isEmpty() ||
                Env.isBaseLanguage(language, "AD_Language")
            ) {
                "/sql/initMsgBase.sql".asResource { sql ->
                    queryOf(sql, listOf()).map { processRow(it) }.asList
                }
            } else {
                "/sql/initMsg.sql".asResource { sql ->
                    queryOf(sql, listOf(language)).map { processRow(it) }.asList
                }
            }
        DB.current.run(loadQuery)
        return msg
    }
}