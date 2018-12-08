package org.compiere.orm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.I_AD_ClientInfo
import org.idempiere.common.util.CCache
import software.hsharp.core.util.DB
import java.util.Properties

/** Cache  */
private val clientInfoCache = CCache<Int, MClientInfo>(I_AD_ClientInfo.Table_Name, 2)

fun get(ctx: Properties, AD_Client_ID: Int, trxName: String?): MClientInfo? {
    return get(ctx, AD_Client_ID, trxName, { row -> MClientInfo(ctx, row) })
}

fun get(ctx: Properties, AD_Client_ID: Int, trxName: String?, factory: (Row) -> MClientInfo, retype: (MClientInfo?) -> MClientInfo? = { it }): MClientInfo? {
    val cached = clientInfoCache[AD_Client_ID]
    if (retype(cached) != null) return cached
    //
    val sql = "SELECT * FROM AD_ClientInfo WHERE AD_Client_ID=?"
    val loadQuery = queryOf(sql, AD_Client_ID).map { row -> factory(row) }.asSingle
    val loaded = DB.current.run(loadQuery)
    if (trxName == null) clientInfoCache[AD_Client_ID] = loaded
    return loaded
} // 	get