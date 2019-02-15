package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;

public class MClientInfo extends X_AD_ClientInfo {
  /**
   * ************************************************************************ Standard Constructor
   *
   * @param ctx context
   * @param ignored ignored
   * @param trxName transaction
   */
  public MClientInfo(Properties ctx, int ignored) {
    super(ctx, ignored);
    if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
  } //	MClientInfo

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MClientInfo(Properties ctx, ResultSet rs) {
    super(ctx, rs);
  } //	MClientInfo

  public MClientInfo(Properties ctx, Row row) {
    super(ctx, row);
  } //	MClientInfo

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
   * @param trxName transaction
   */
  public MClientInfo(
      MClient client,
      int AD_Tree_Org_ID,
      int AD_Tree_BPartner_ID,
      int AD_Tree_Project_ID,
      int AD_Tree_SalesRegion_ID,
      int AD_Tree_Product_ID,
      int AD_Tree_Campaign_ID,
      int AD_Tree_Activity_ID,
      String trxName) {
    super(client.getCtx(), 0);
    setADClientID(client.getClientId()); // 	to make sure
    setAD_Org_ID(0);
    setIsDiscountLineAmt(false);
    //
    setAD_Tree_Menu_ID(10); // 	HARDCODED
    //
    setAD_Tree_Org_ID(AD_Tree_Org_ID);
    setAD_Tree_BPartner_ID(AD_Tree_BPartner_ID);
    setAD_Tree_Project_ID(AD_Tree_Project_ID);
    setAD_Tree_SalesRegion_ID(AD_Tree_SalesRegion_ID);
    setAD_Tree_Product_ID(AD_Tree_Product_ID);
    setAD_Tree_Campaign_ID(AD_Tree_Campaign_ID);
    setAD_Tree_Activity_ID(AD_Tree_Activity_ID);
    //
    setCreateNew(true);
  } //	MClientInfo


  /**
   * Get Client Info
   *
   * @param ctx context
   * @param AD_Client_ID id
   * @param trxName optional trx
   * @return Client Info
   */
  public static MClientInfo get(Properties ctx, int AD_Client_ID) {
    return MBaseClientInfoKt.get(ctx, AD_Client_ID);
  } //	get
}
