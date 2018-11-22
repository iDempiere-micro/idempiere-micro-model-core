package org.compiere.orm

import java.util.Properties
import java.sql.ResultSet
import org.compiere.model.I_C_Opportunity

abstract class BasePOUser : PO {
    constructor(ctx: Properties, ID: Int, trxName: String?) : super(ctx, ID, trxName)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?) : super(ctx, rs, trxName)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?, a: String?) : super(ctx, rs, trxName, a)

    @Throws(RuntimeException::class)
    fun getAD_User(): org.compiere.model.I_AD_User? {
        val id = getAD_User_ID()
        if (id == 0) return null
        return MTable.get(ctx, org.compiere.model.I_AD_User.Table_Name)
                .getPO(id, _TrxName) as org.compiere.model.I_AD_User?
    }

    /** Set User/Contact.
     * @param AD_User_ID
     * User within the system - Internal or Business Partner Contact
     */
    fun setAD_User_ID(AD_User_ID: Int) {
        if (AD_User_ID < 1)
            set_Value(I_C_Opportunity.COLUMNNAME_AD_User_ID, null)
        else
            set_Value(I_C_Opportunity.COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID))
    }

    /** Get User/Contact.
     * @return User within the system - Internal or Business Partner Contact
     */
    fun getAD_User_ID(): Int {
        return get_Value(I_C_Opportunity.COLUMNNAME_AD_User_ID) as Int? ?: return 0
    }
}