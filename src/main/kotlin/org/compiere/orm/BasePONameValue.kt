package org.compiere.orm

import kotliquery.Row
import org.compiere.model.I_AD_Client.COLUMNNAME_Value
import org.idempiere.common.exceptions.AdempiereException
import java.util.Properties

abstract class BasePONameValue : BasePOName {
    constructor(ctx: Properties, ID: Int) : super(ctx, ID)
    constructor (ctx: Properties, a: String?) : super(ctx, a)
    constructor(ctx: Properties, r: Row) : super(ctx, r)

    protected open fun doGetSearchKey() = getValue(COLUMNNAME_Value) as String
    protected open fun doSetSearchKey(Value: String) {
        setValue(COLUMNNAME_Value, Value)
    }

    fun isSearchKeyNotNull() = getValue(COLUMNNAME_Value) as String? != null

    open var searchKey: String
        get() = doGetSearchKey()
        set(Value) = doSetSearchKey(Value)
}