package software.hsharp.core.orm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.I_AD_SysConfig
import org.compiere.orm.X_AD_SysConfig
import org.idempiere.common.util.CCache
import software.hsharp.core.util.DB
import software.hsharp.core.util.asResource
import java.util.Properties

/** Cache  */
internal val sysConfigCache = CCache<String, String>(I_AD_SysConfig.Table_Name, 40, 0, true)

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
    constructor(ctx: Properties, r: Row) : super(ctx, r)

    constructor(ctx: Properties, AD_SysConfig_ID: Int) : super(ctx, AD_SysConfig_ID)
}