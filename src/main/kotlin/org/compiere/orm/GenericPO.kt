// Generic PO.
package org.compiere.orm

// import for GenericPO

import kotliquery.Row

/**
 * Generic PO implementation, this can be use together with ModelValidator as alternative to the
 * classic generated model class and extend ( X_ & M_ ) approach.
 *
 *
 * Originally for used to insert/update data from adempieredata.xml file in 2pack.
 *
 * @author Marco LOMBARDO
 * @contributor Low Heng Sin
 */
class GenericPO : PO {

    override val tableId: Int
        get() = 0

    /**
     * @param ID
     */
    constructor(ID: Int) : super(ID) {}

    /**
     *
     */
    constructor(row: Row) : super(row) {}

    override fun toString(): String {
        return "GenericPO[Table=" +
                "" + tableId + ",ID=" +
                id +
                "]"
    }

    override fun getAccessLevel(): Int = Integer.parseInt(super.p_info.accessLevel)

    companion object {
        private val serialVersionUID = -6558017105997010172L
    }
} // GenericPO
