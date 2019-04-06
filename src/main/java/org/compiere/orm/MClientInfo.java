package org.compiere.orm;

import kotliquery.Row;

public class MClientInfo extends X_AD_ClientInfo {
    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ignored ignored
     */
    public MClientInfo(int ignored) {
        super(ignored);
        if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
    } //	MClientInfo

    /**
     * Load Constructor
     */
    public MClientInfo(Row row) {
        super(row);
    } //	MClientInfo

    /**
     * Parent Constructor
     *
     * @param client                 client
     * @param AD_Tree_Org_ID         org tree
     * @param AD_Tree_BPartner_ID    bp tree
     * @param AD_Tree_Project_ID     project tree
     * @param AD_Tree_SalesRegion_ID sr tree
     * @param AD_Tree_Product_ID     product tree
     * @param AD_Tree_Campaign_ID    campaign tree
     * @param AD_Tree_Activity_ID    activity tree
     */
    public MClientInfo(
            MClient client,
            int AD_Tree_Org_ID,
            int AD_Tree_BPartner_ID,
            int AD_Tree_Project_ID,
            int AD_Tree_SalesRegion_ID,
            int AD_Tree_Product_ID,
            int AD_Tree_Campaign_ID,
            int AD_Tree_Activity_ID) {
        super(0);
        setADClientID(client.getClientId()); // 	to make sure
        setOrgId(0);
        setIsDiscountLineAmt(false);
        //
        setTreeMenuId(10); // 	HARDCODED
        //
        setTreeOrgId(AD_Tree_Org_ID);
        setTreeBPartnerId(AD_Tree_BPartner_ID);
        setTreeProjectId(AD_Tree_Project_ID);
        setTreeSalesRegionId(AD_Tree_SalesRegion_ID);
        setTreeProductId(AD_Tree_Product_ID);
        setTreeCampaignId(AD_Tree_Campaign_ID);
        setTreeActivityId(AD_Tree_Activity_ID);
        //
        setCreateNew(true);
    } //	MClientInfo


    /**
     * Get Client Info
     *
     * @param AD_Client_ID id
     * @return Client Info
     */
    public static MClientInfo get(int AD_Client_ID) {
        return MBaseClientInfoKt.get(AD_Client_ID);
    } //	get
}
