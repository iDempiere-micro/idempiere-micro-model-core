package org.compiere.orm

import kotliquery.Row
import org.compiere.model.ReferenceTable
import org.compiere.model.Table
import org.idempiere.common.util.AdempiereSystemError
import software.hsharp.core.orm.*

/**
 * Generated Model for AD_Ref_Table
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
open class X_AD_Ref_Table : PO, ReferenceTable {

    override val tableId: Int
        get() = ReferenceTable.Table_ID

    /**
     * Standard Constructor
     */
    constructor(AD_Ref_Table_ID: Int) : super(AD_Ref_Table_ID)

    /**
     * Load Constructor
     */
    constructor(row: Row) : super(row)

    /**
     * AccessLevel
     *
     * @return 4 - System
     */
    override fun getAccessLevel(): Int {
        return ReferenceTable.accessLevel.toInt()
    }

    override fun toString(): String {
        return "X_AD_Ref_Table[$id]"
    }

    override fun getTable(): Table {
        return getTable(Table.Table_Name)
            .getPO(refTableId) ?: throw AdempiereSystemError("Table not found")
    }

    /**
     * Get Table.
     *
     * @return Database Table information
     */
    override fun getRefTableId(): Int {
        return getValue(ReferenceTable.COLUMNNAME_AD_Table_ID) as Int? ?: return 0
    }

    /**
     * Set Entity Type.
     *
     * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
     */
    override fun setEntityType(EntityType: String) {

        setValue(ReferenceTable.COLUMNNAME_EntityType, EntityType)
    }

    /**
     * Set Display Value.
     *
     * @param IsValueDisplayed Displays Value column with the Display column
     */
    override fun setIsValueDisplayed(IsValueDisplayed: Boolean) {
        setValue(ReferenceTable.COLUMNNAME_IsValueDisplayed, IsValueDisplayed)
    }

    companion object {
        private const val serialVersionUID = 20171031L
    }
}
