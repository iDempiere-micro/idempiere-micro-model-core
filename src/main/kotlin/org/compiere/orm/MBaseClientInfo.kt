package org.compiere.orm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.I_AD_ClientInfo
import org.idempiere.common.util.CCache
import software.hsharp.core.util.DB

/** Cache  */
private val clientInfoCache = CCache<Int, MClientInfo>(I_AD_ClientInfo.Table_Name, 2)

fun get(clientId: Int): MClientInfo? {
    return get(clientId, { row -> MClientInfo(row) })
}

fun get(
    clientId: Int,
    factory: (Row) -> MClientInfo,
    retype: (MClientInfo?) -> MClientInfo? = { it }
): MClientInfo? {
    val cached = clientInfoCache[clientId]
    if (retype(cached) != null) return cached
    //
    val sql = "SELECT * FROM AD_ClientInfo WHERE AD_Client_ID=?"
    val loadQuery = queryOf(sql, clientId).map { row -> factory(row) }.asSingle
    val loaded = DB.current.run(loadQuery)
    clientInfoCache[clientId] = loaded
    return loaded
} // 	get