package org.compiere.orm

import kotliquery.Row
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing

private val organizationInfoFactory = factory { MOrgInfo(it) }

/**
 * Get currency by Id
 */
fun getOrganizationInfo(id: Int) = id loadUsing organizationInfoFactory

/**
 * Organization Info Model
 *
 * @author Jorg Janke
 * @author Teo Sarca, www.arhipac.ro
 *  * BF [ 2107083 ] Caching of MOrgInfo issue
 * @version $Id: MOrgInfo.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
class MOrgInfo : X_AD_OrgInfo {

    /**
     * Organization constructor
     *
     * @param org org
     */
    constructor(org: MOrg) : super(0) {
        setClientOrg(org)
        setDUNS("?")
        setTaxID("?")
    } // 	MOrgInfo

    constructor(row: Row) : super(row) // 	MOrgInfo
    constructor(id: Int) : super(id)

    companion object {
        private val serialVersionUID = 2496591466841600079L
    }
}
