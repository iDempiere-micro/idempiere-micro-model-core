package org.compiere.orm

import kotliquery.Row
import org.compiere.model.Client.COLUMNNAME_Value

abstract class BasePONameValue : BasePOName {
    constructor(ID: Int) : super(ID)
    constructor(r: Row) : super(r)

    protected open fun doGetSearchKey() = getValue(COLUMNNAME_Value) as String
    protected open fun doSetSearchKey(Value: String) {
        setValue(COLUMNNAME_Value, Value)
    }

    fun isSearchKeyNotNull() = getValue(COLUMNNAME_Value) as String? != null

    open var searchKey: String
        get() = doGetSearchKey()
        set(Value) = doSetSearchKey(Value)
}