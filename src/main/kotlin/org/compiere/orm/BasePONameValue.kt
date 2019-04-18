package org.compiere.orm

import kotliquery.Row
import org.compiere.model.Client.COLUMNNAME_Value
import org.idempiere.common.util.AdempiereSystemError

abstract class BasePONameValue : BasePOName {
    constructor(ID: Int) : super(ID)
    constructor(r: Row) : super(r)

    protected open fun doGetSearchKey() : String = getValue(COLUMNNAME_Value) ?: throw AdempiereSystemError("Does not have a search key")
    protected open fun doSetSearchKey(Value: String) {
        setValue(COLUMNNAME_Value, Value)
    }

    fun isSearchKeyNotNull() = getValue(COLUMNNAME_Value) as String? != null

    open var searchKey: String
        get() = doGetSearchKey()
        set(Value) = doSetSearchKey(Value)
}