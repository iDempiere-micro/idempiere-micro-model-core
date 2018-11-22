package org.compiere.orm

import org.compiere.model.HasName
import org.idempiere.common.util.KeyNamePair
import java.sql.ResultSet
import java.util.Properties

abstract class BasePOName : PO {
    constructor(ctx: Properties, ID: Int, trxName: String?) : super(ctx, ID, trxName)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?) : super(ctx, rs, trxName)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?, a: String?) : super(ctx, rs, trxName, a)

    /** Set Name.
     * @param Name
     * Alphanumeric identifier of the entity
     */
    fun setName(Name: String) {
        set_Value(HasName.COLUMNNAME_Name, Name)
    }

    /** Get Name.
     * @return Alphanumeric identifier of the entity
     */
    open fun getName(): String {
        return get_Value(HasName.COLUMNNAME_Name) as String
    }

    /** Get Record ID/ColumnName
     * @return ID/ColumnName pair
     */
    fun getKeyNamePair(): KeyNamePair {
        return KeyNamePair(id, getName())
    }
}