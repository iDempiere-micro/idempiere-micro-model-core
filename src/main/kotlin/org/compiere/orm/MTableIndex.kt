package org.compiere.orm

import kotliquery.Row
import org.idempiere.orm.PO

class MTableIndex : X_AD_TableIndex {

    /**
     * Get table name
     *
     * @return table name
     */
    val dbTableName: String
        get() {
            val AD_Table_ID = indexTableId
            return getDbTableName(AD_Table_ID)
        }

    /**
     * Standard constructor
     *
     * @param AD_TableIndex_ID table index
     */
    constructor(AD_TableIndex_ID: Int) : super(AD_TableIndex_ID) {
        if (AD_TableIndex_ID == 0) {
            setEntityType(PO.ENTITYTYPE_UserMaintained)
            setIsUnique(false)
            setIsCreateConstraint(false)
        }
    }

    constructor(row: Row) : super(row) {}

    /**
     * String representation
     *
     * @return info
     */
    override fun toString(): String {
        return "MTableIndex[" + id +
                "-" +
                name +
                ",AD_Table_ID=" +
                indexTableId +
                "]"
    }

    companion object {
        private val serialVersionUID = 5312095272014146977L
    }
}
