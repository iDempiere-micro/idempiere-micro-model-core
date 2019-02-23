package org.compiere.orm

import kotliquery.Row
import org.compiere.model.I_AD_Client.COLUMNNAME_Value
import java.sql.ResultSet
import java.util.*

abstract class BasePONameValue : BasePOName {
    constructor(ctx: Properties, ID: Int) : super(ctx, ID)
    constructor (ctx: Properties, rs: ResultSet) : super(ctx, rs)
    constructor (ctx: Properties, rs: ResultSet, a: String?) : super(ctx, rs, a)
    constructor(ctx: Properties, r: Row) : super(ctx, r)

    /** Set Search Key.
     * @param Value
     * Search key for the record in the format required - must be unique
     */
    open fun setSearchKey(Value: String) {
        set_Value(COLUMNNAME_Value, Value)
    }

    /** Get Search Key.
     * @return Search key for the record in the format required - must be unique
     */
    open fun getSearchKey(): String {
        return getValue(COLUMNNAME_Value) as String
    }
}