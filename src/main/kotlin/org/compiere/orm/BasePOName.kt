package org.compiere.orm

import kotliquery.Row
import org.compiere.model.HasName
import org.idempiere.common.util.KeyNamePair
import java.sql.ResultSet
import java.util.*

abstract class BasePOName : PO {
    constructor(ctx: Properties, ID: Int) : super(ctx, ID)
    constructor (ctx: Properties, rs: ResultSet) : super(ctx, rs)
    constructor (ctx: Properties, rs: ResultSet, a: String?) : super(ctx, rs, a)
    constructor(ctx: Properties, r: Row) : super(ctx, r)

    open var name: String
        get() = get_Value(HasName.COLUMNNAME_Name) as String
        set(Name) { set_Value(HasName.COLUMNNAME_Name, Name) }

    /** Get Record ID/ColumnName
     * @return ID/ColumnName pair
     */
    fun getKeyNamePair(): KeyNamePair {
        return KeyNamePair(id, name)
    }
}