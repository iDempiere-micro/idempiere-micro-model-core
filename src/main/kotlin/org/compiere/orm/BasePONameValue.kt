package org.compiere.orm

import org.compiere.model.I_AD_User.COLUMNNAME_Value
import java.sql.ResultSet
import java.util.Properties

abstract class BasePONameValue : BasePOName {
    constructor(ctx: Properties, ID: Int, trxName: String?) : super(ctx, ID, trxName)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?) : super(ctx, rs, trxName)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?, a: String?) : super(ctx, rs, trxName, a)

    /** Set Search Key.
     * @param Value
     * Search key for the record in the format required - must be unique
     */
    open fun setValue(Value: String) {
        set_Value(COLUMNNAME_Value, Value)
    }

    /** Get Search Key.
     * @return Search key for the record in the format required - must be unique
     */
    open fun getValue(): String {
        return get_Value(COLUMNNAME_Value) as String
    }
}