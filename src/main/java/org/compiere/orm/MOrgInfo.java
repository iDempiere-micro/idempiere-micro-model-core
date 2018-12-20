package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_AD_OrgInfo;
import org.idempiere.common.util.CCache;

/**
 * Organization Info Model
 *
 * @author Jorg Janke
 * @author Teo Sarca, www.arhipac.ro
 *     <li>BF [ 2107083 ] Caching of MOrgInfo issue
 * @version $Id: MOrgInfo.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MOrgInfo extends X_AD_OrgInfo {
  /** */
  private static final long serialVersionUID = 2496591466841600079L;
  /** Cache */
  private static CCache<Integer, MOrgInfo> s_cache =
      new CCache<Integer, MOrgInfo>(I_AD_OrgInfo.Table_Name, 50);

  /**
   * ************************************************************************ Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MOrgInfo(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } //	MOrgInfo

  public MOrgInfo(Properties ctx, Row row) {
    super(ctx, row);
  } //	MOrgInfo

  /**
   * Organization constructor
   *
   * @param org org
   */
  public MOrgInfo(MOrg org) {
    super(org.getCtx(), 0, null);
    setClientOrg(org);
    setDUNS("?");
    setTaxID("?");
  } //	MOrgInfo

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param AD_Org_ID id
   * @return Org Info
   * @deprecated
   */
  public static MOrgInfo get(Properties ctx, int AD_Org_ID) {
    return get(ctx, AD_Org_ID, null);
  } //	get

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param AD_Org_ID id
   * @param trxName
   * @return Org Info
   */
  public static MOrgInfo get(Properties ctx, int AD_Org_ID, String trxName) {
    MOrgInfo retValue = s_cache.get(AD_Org_ID);
    if (retValue != null) {
      return retValue;
    }
    retValue =
        new Query(ctx, I_AD_OrgInfo.Table_Name, "AD_Org_ID=?", trxName)
            .setParameters(AD_Org_ID)
            .firstOnly();
    if (retValue != null) {
      s_cache.put(AD_Org_ID, retValue);
    }
    return retValue;
  } //	get
}
