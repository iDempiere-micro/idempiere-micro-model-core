package org.compiere.orm

import kotliquery.Row
import kotliquery.queryOf
import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing
import software.hsharp.core.util.DB

private fun doLoadClientInfo(clientId: Int): MClientInfo {
    val sql = "SELECT * FROM AD_ClientInfo WHERE AD_Client_ID=?"
    val loadQuery = queryOf(sql, clientId).map { row -> MClientInfo(row) }.asSingle
    return DB.current.run(loadQuery) ?: throw AdempiereSystemError("Client info not found for client $clientId")
}

private val clientInfoFactory = factory { doLoadClientInfo(it) }

fun getClientInfo(id: Int) = id loadUsing clientInfoFactory

open class MClientInfo : X_AD_ClientInfo {
    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ignored ignored
     */
    constructor(ignored: Int) : super(ignored) {
        if (ignored != 0) throw IllegalArgumentException("Multi-Key")
    } // 	MClientInfo

    /**
     * Load Constructor
     */
    constructor(row: Row) : super(row) {} // 	MClientInfo

    /**
     * Parent Constructor
     *
     * @param client client
     * @param AD_Tree_Org_ID org tree
     * @param AD_Tree_BPartner_ID bp tree
     * @param AD_Tree_Project_ID project tree
     * @param AD_Tree_SalesRegion_ID sr tree
     * @param AD_Tree_Product_ID product tree
     * @param AD_Tree_Campaign_ID campaign tree
     * @param AD_Tree_Activity_ID activity tree
     */
    constructor(
        client: MClient,
        AD_Tree_Org_ID: Int,
        AD_Tree_BPartner_ID: Int,
        AD_Tree_Project_ID: Int,
        AD_Tree_SalesRegion_ID: Int,
        AD_Tree_Product_ID: Int,
        AD_Tree_Campaign_ID: Int,
        AD_Tree_Activity_ID: Int
    ) : super(0) {
        setClientId(client.clientId) // 	to make sure
        setOrgId(0)
        setIsDiscountLineAmt(false)
        //
        setTreeMenuId(10) // 	HARDCODED
        //
        treeOrgId = AD_Tree_Org_ID
        setTreeBPartnerId(AD_Tree_BPartner_ID)
        setTreeProjectId(AD_Tree_Project_ID)
        setTreeSalesRegionId(AD_Tree_SalesRegion_ID)
        setTreeProductId(AD_Tree_Product_ID)
        setTreeCampaignId(AD_Tree_Campaign_ID)
        setTreeActivityId(AD_Tree_Activity_ID)
        //
        createNew = true
    } // 	MClientInfo
}
