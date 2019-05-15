package org.compiere.orm

import kotliquery.Row
import org.compiere.model.HasName
import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.KeyNamePair

abstract class BasePOName : PO {
    constructor(ID: Int) : super(ID)
    constructor(r: Row) : super(r)
    constructor(r: Row?, ID: Int) : super(r, ID)

    open var name: String
        get() = getValue(HasName.COLUMNNAME_Name) ?: throw AdempiereSystemError("Does not have a name")
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