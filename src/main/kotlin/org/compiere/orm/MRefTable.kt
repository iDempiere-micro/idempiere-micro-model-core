package org.compiere.orm

import kotliquery.Row
import org.compiere.model.Table
import org.idempiere.orm.PO
import software.hsharp.core.orm.*

/**
 * Reference table
 */
class MRefTable : X_AD_Ref_Table {

    /**
     * Standard Constructor
     *
     * @param AD_Reference_ID id warning if you are referring to reference list or table type should
     * be used AD_Reference_Value_ID
     */
    constructor(AD_Reference_ID: Int) : super(AD_Reference_ID) {
        if (AD_Reference_ID == 0) {
            setEntityType(PO.ENTITYTYPE_UserMaintained) // U
            setIsValueDisplayed(false)
        }
    } //	MRefTable

    /**
     * Load Constructor
     *
     */
    constructor(row: Row) : super(row) //	MRefTable

    @Throws(RuntimeException::class)
    override fun getTable(): Table {
        return getTable(refTableId)
    }

    companion object {
        private const val serialVersionUID = 380648726485603193L
    }
} //	MRefTable
