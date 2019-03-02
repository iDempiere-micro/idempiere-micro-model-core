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

    open protected fun doGetSearchKey() = getValue(COLUMNNAME_Value) as String
    open protected fun doSetSearchKey(Value: String) { setValue(COLUMNNAME_Value, Value) }

    open var searchKey: String
        get() = doGetSearchKey()
        set(Value) = doSetSearchKey(Value)
}