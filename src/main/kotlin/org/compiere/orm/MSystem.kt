package org.compiere.orm

import org.compiere.model.System
import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.memoize

private fun doLoad(): System {
    return Query<System>(System.Table_Name, null)
        .setOrderBy(System.COLUMNNAME_AD_System_ID)
        .firstOnly() ?: throw AdempiereSystemError("System not present")
}

private val loader = { doLoad() }.memoize()

fun getSystem() = loader()

class MSystem : X_AD_System(0) {
    /**
     * ************************************************************************ Default Constructor
     */
    init {
        load() // 	load ID=0
    } // 	MSystem
}
