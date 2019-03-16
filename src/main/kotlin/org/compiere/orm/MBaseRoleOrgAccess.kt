package org.compiere.orm

import software.hsharp.core.util.DB
import software.hsharp.core.util.queryOf
import java.util.Properties

/**
 * Get Organizational Info
 *
 * @param ctx context
 * @param sql sql command
 * @param id id
 * @return array of Role Org Access
 */
fun get(ctx: Properties, sql: String, id: Int): Array<MRoleOrgAccess> {
    val query = queryOf(sql, listOf(id)).map { row -> MRoleOrgAccess(ctx, row) }.asList
    return DB.current.run(query).toTypedArray()
} // 	get