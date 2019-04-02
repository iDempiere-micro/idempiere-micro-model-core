package org.compiere.orm

import arrow.syntax.function.memoize
import kotliquery.Row
import org.compiere.model.I_AD_Org
import org.compiere.orm.MOrg.Companion.doGetOrg
import software.hsharp.core.util.asResource

import software.hsharp.core.util.getSQLValue

/**
 * Get organization by Id
 */
fun getOrg(orgId: Int) = doGetOrg(orgId)

/**
 * Get Active Organizations Of Client
 *
 * @param po persistent object
 * @return array of orgs
 */
fun getClientOrganizations(po: PO): Array<MOrg> {
    val list = Query(I_AD_Org.Table_Name, "AD_Client_ID=?")
        .setOrderBy(I_AD_Org.COLUMNNAME_Value)
        .setOnlyActiveRecords(true)
        .setParameters(po.clientId)
        .list<MOrg>()
    return list.map { getOrg(it.id) }.toTypedArray()
} // 	getOfClient

/**
 * Organization Model
 *
 * @author Jorg Janke
 * @version $Id: MOrg.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
class MOrg : X_AD_Org {
    private val doLinkedBusinessPartnerId = {
        val result = "/sql/getLinkedBusinessPartnerId.sql".asResource { sql ->
            getSQLValue(sql, orgId)
        }
        if (result < 0) 0 else result
    }.memoize()

    /**
     * Get Org Info
     *
     * @return Org Info
     */
    val info: MOrgInfo?
        get() = MOrgInfo.get(orgId) // 	getMOrgInfo

    /**
     * Get Linked BPartner
     *
     * @return C_BPartner_ID
     */
    // 	not found = -1
    val linkedBusinessPartnerId: Int
        get() = doLinkedBusinessPartnerId()

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param AD_Org_ID id
     */
    private constructor(AD_Org_ID: Int) : super(AD_Org_ID) {
        if (AD_Org_ID == 0) {
            setIsSummary(false)
        }
    } // 	MOrg

    /**
     * Load Constructor
     *
     * @param ctx context
     */
    constructor(row: Row) : super(row) // 	MOrg

    /**
     * Parent Constructor
     *
     * @param client client
     * @param orgName name
     */
    constructor(client: MClient, value: String, orgName: String) : this(0) {
        setADClientID(client.clientId)
        searchKey = value
        name = orgName
    } // 	MOrg

    /**
     * After Save
     *
     * @param newRecord new Record
     * @param success save success
     * @return success
     */
    override fun afterSave(newRecord: Boolean, success: Boolean): Boolean {
        if (!success) return success
        if (newRecord) {
            // 	Info
            val info = MOrgInfo(this)
            info.saveEx()
            // 	Access
            MRoleOrgAccess.createForOrg(this)
            val role = MRole.getDefault(true) // 	reload
            role.loadAccess(true) // reload org access within transaction
            // 	TreeNode
            insert_Tree(MTree_Base.TREETYPE_Organization)
        }
        if (newRecord || isValueChanged(I_AD_Org.COLUMNNAME_Value))
            update_Tree(MTree_Base.TREETYPE_Organization)

        return true
    } // 	afterSave

    /**
     * After Delete
     *
     * @param success
     * @return deleted
     */
    override fun afterDelete(success: Boolean): Boolean {
        if (success) delete_Tree(MTree_Base.TREETYPE_Organization)
        return success
    } // 	afterDelete

    companion object {
        private const val serialVersionUID = -5604686137606338725L
        internal val doGetOrg = { AD_Org_ID: Int -> MOrg(AD_Org_ID) }.memoize()
    }
} // 	MOrg
