package software.hsharp.core.orm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.SystemConfig
import org.compiere.orm.X_AD_SysConfig
import org.idempiere.common.util.CCache
import software.hsharp.core.util.DB
import software.hsharp.core.util.asResource

/** Cache  */
internal val sysConfigCache = CCache<String, String>(SystemConfig.Table_Name, 40, 0, true)

internal fun getValue(name: String, defaultValue: String?, clientId: Int, orgId: Int): String? {
    val key = "" + clientId + "_" + orgId + "_" + name
    val cached = sysConfigCache[key]
    if (cached != null) return cached
    if (sysConfigCache.containsKey(key))
        return defaultValue

    return "/sql/getBaseSysConfigValue.sql".asResource { sql ->
        val loadQuery = queryOf(sql, name, clientId, orgId).map { it.stringOrNull(1) }.asSingle
        val r = DB.current.run(loadQuery)
        sysConfigCache[name] = r
        r ?: defaultValue
    }
}

abstract class MBaseSysConfig : X_AD_SysConfig {
    constructor(r: Row) : super(r)

    constructor(AD_SysConfig_ID: Int) : super(AD_SysConfig_ID)
}