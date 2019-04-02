package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_OrgInfo;
import org.idempiere.common.util.CCache;

/**
 * Organization Info Model
 *
 * @author Jorg Janke
 * @author Teo Sarca, www.arhipac.ro
 * <li>BF [ 2107083 ] Caching of MOrgInfo issue
 * @version $Id: MOrgInfo.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MOrgInfo extends X_AD_OrgInfo {
    /**
     *
     */
    private static final long serialVersionUID = 2496591466841600079L;
    /**
     * Cache
     */
    private static CCache<Integer, MOrgInfo> s_cache =
            new CCache<>(I_AD_OrgInfo.Table_Name, 50);

    /**
     * Organization constructor
     *
     * @param org org
     */
    public MOrgInfo(MOrg org) {
        super(0);
        setClientOrg(org);
        setDUNS("?");
        setTaxID("?");
    } //	MOrgInfo

    public MOrgInfo(Row row) {
        super(row);
    } //	MOrgInfo

    /**
     * Load Constructor
     *
     * @param AD_Org_ID id
     * @return Org Info
     */
    public static MOrgInfo get(int AD_Org_ID) {
        MOrgInfo retValue = s_cache.get(AD_Org_ID);
        if (retValue != null) {
            return retValue;
        }
        retValue =
                new Query(I_AD_OrgInfo.Table_Name, "AD_Org_ID=?")
                        .setParameters(AD_Org_ID)
                        .firstOnly();
        if (retValue != null) {
            s_cache.put(AD_Org_ID, retValue);
        }
        return retValue;
    } //	get
}
