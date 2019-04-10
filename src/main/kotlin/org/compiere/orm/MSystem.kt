package org.compiere.orm

import org.compiere.model.I_AD_System
import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.memoize

private fun doLoad(): MSystem {
    return Query(I_AD_System.Table_Name, null)
        .setOrderBy(I_AD_System.COLUMNNAME_AD_System_ID)
        .firstOnly() ?: throw AdempiereSystemError("System not present")
}

private val loader = { doLoad() }.memoize()

fun getSystem(): MSystem = loader()

class MSystem : X_AD_System(0) {
    /**
     * ************************************************************************ Default Constructor
     */
    init {
        load() // 	load ID=0
    } // 	MSystem
}
