package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Org;
import org.idempiere.common.util.CCache;

import java.util.List;
import java.util.Properties;

import static software.hsharp.core.util.DBKt.getSQLValue;

/**
 * Organization Model
 *
 * @author Jorg Janke
 * @version $Id: MOrg.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public class MOrg extends X_AD_Org {
    /**
     *
     */
    private static final long serialVersionUID = -5604686137606338725L;
    /**
     * Cache
     */
    private static CCache<Integer, MOrg> s_cache = new CCache<Integer, MOrg>(I_AD_Org.Table_Name, 50);
    /**
     * Linked Business Partner
     */
    private Integer m_linkedBPartner = null;

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ctx       context
     * @param AD_Org_ID id
     * @param trxName   transaction
     */
    public MOrg(Properties ctx, int AD_Org_ID) {
        super(ctx, AD_Org_ID);
        if (AD_Org_ID == 0) {
            //	setValue (null);
            //	setName (null);
            setIsSummary(false);
        }
    } //	MOrg

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MOrg(Properties ctx, Row row) {
        super(ctx, row);
    } //	MOrg

    /**
     * Parent Constructor
     *
     * @param client client
     * @param name   name
     */
    public MOrg(MClient client, String value, String name) {
        this(client.getCtx(), 0);
        setADClientID(client.getClientId());
        setSearchKey(value);
        setName(name);
    } //	MOrg

    /**
     * Get Active Organizations Of Client
     *
     * @param po persistent object
     * @return array of orgs
     */
    public static MOrg[] getOfClient(PO po) {
        List<MOrg> list =
                new Query(po.getCtx(), I_AD_Org.Table_Name, "AD_Client_ID=?")
                        .setOrderBy(I_AD_Org.COLUMNNAME_Value)
                        .setOnlyActiveRecords(true)
                        .setParameters(po.getClientId())
                        .list();
        for (MOrg org : list) {
            s_cache.put(org.getId(), org);
        }
        return list.toArray(new MOrg[list.size()]);
    } //	getOfClient

    /**
     * Get Org from Cache
     *
     * @param ctx       context
     * @param AD_Org_ID id
     * @return MOrg
     */
    public static MOrg get(Properties ctx, int AD_Org_ID) {
        MOrg retValue = s_cache.get(AD_Org_ID);
        if (retValue != null) return retValue;
        retValue = new MOrg(ctx, AD_Org_ID);
        if (retValue.getId() != 0) s_cache.put(AD_Org_ID, retValue);
        return retValue;
    } //	get

    /**
     * Get Org Info
     *
     * @return Org Info
     */
    public MOrgInfo getInfo() {
        return MOrgInfo.get(getCtx(), getOrgId());
    } //	getMOrgInfo

    /**
     * After Save
     *
     * @param newRecord new Record
     * @param success   save success
     * @return success
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        if (!success) return success;
        if (newRecord) {
            //	Info
            MOrgInfo info = new MOrgInfo(this);
            info.saveEx();
            //	Access
            MRoleOrgAccess.createForOrg(this);
            MRole role = MRole.getDefault(getCtx(), true); // 	reload
            role.loadAccess(true); // reload org access within transaction
            //	TreeNode
            insert_Tree(MTree_Base.TREETYPE_Organization);
        }
        if (newRecord || is_ValueChanged(I_AD_Org.COLUMNNAME_Value))
            update_Tree(MTree_Base.TREETYPE_Organization);

        return true;
    } //	afterSave

    /**
     * After Delete
     *
     * @param success
     * @return deleted
     */
    protected boolean afterDelete(boolean success) {
        if (success) delete_Tree(MTree_Base.TREETYPE_Organization);
        return success;
    } //	afterDelete

    /**
     * Get Linked BPartner
     *
     * @return C_BPartner_ID
     */
    public int getLinkedC_BPartner_ID(String trxName) {
        if (m_linkedBPartner == null) {
            int C_BPartner_ID =
                    getSQLValue(
                            "SELECT C_BPartner_ID FROM C_BPartner WHERE AD_OrgBP_ID=?", getOrgId());
            if (C_BPartner_ID < 0) // 	not found = -1
                C_BPartner_ID = 0;
            m_linkedBPartner = new Integer(C_BPartner_ID);
        }
        return m_linkedBPartner.intValue();
    } //	getLinkedC_BPartner_ID
} //	MOrg
