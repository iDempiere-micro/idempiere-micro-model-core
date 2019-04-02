package org.compiere.orm

import kotliquery.Row
import org.compiere.model.HasName
import org.idempiere.common.util.KeyNamePair

abstract class BasePOName : PO {
    constructor(ID: Int) : super(ID)
    constructor(r: Row) : super(r)

    open var name: String
        get() = getValue(HasName.COLUMNNAME_Name) as String
        set(Name) {
            setValue(HasName.COLUMNNAME_Name, Name)
        }

    /** Get Record ID/ColumnName
     * @return ID/ColumnName pair
     */
    fun getKeyNamePair(): KeyNamePair {
        return KeyNamePair(id, name)
    }
}