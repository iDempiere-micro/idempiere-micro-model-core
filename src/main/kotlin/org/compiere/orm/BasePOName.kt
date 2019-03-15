package org.compiere.orm

import kotliquery.Row
import org.compiere.model.HasName
import org.idempiere.common.util.KeyNamePair
import java.util.Properties

abstract class BasePOName : PO {
    constructor(ctx: Properties, ID: Int) : super(ctx, ID)
    constructor (ctx: Properties, a: String?) : super(ctx, a)
    constructor(ctx: Properties, r: Row) : super(ctx, r)

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