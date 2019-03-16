package org.compiere.orm

import kotliquery.Row
import org.compiere.model.I_AD_User
import java.util.Properties

/**
 * PO with the userId
 */
abstract class BasePOUser : PO {
    constructor(ctx: Properties, ID: Int) : super(ctx, ID)
    constructor (ctx: Properties, a: String?) : super(ctx, a)
    constructor (ctx: Properties, row: Row) : super(ctx, row)

    /** Set User/Contact.
     * @param AD_User_ID
     * User within the system - Internal or Business Partner Contact
     */
    fun setUserId(AD_User_ID: Int) {
        if (AD_User_ID < 1)
            setValue(I_AD_User.COLUMNNAME_AD_User_ID, null)
        else
            setValue(I_AD_User.COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID))
    }

    /** Get User/Contact.
     * @return User within the system - Internal or Business Partner Contact
     */
    fun getUserId(): Int {
        return getValue(I_AD_User.COLUMNNAME_AD_User_ID) as Int? ?: return 0
    }
}