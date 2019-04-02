package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Client;
import org.idempiere.common.util.CCache;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Language;
import software.hsharp.core.util.Environment;

import java.util.HashMap;
import java.util.List;

public class MClient extends X_AD_Client {
    /*
     * Is Client Accounting enabled?
     * CLIENT_ACCOUNTING parameter allow the next values
     *   D - Disabled (default)
     *   Q - Queue (enabled to post by hand - queue documents for posterior processing)
     *   I - Immediate (immediate post - allow complete on errors)
     *
     *	@return boolean representing if client accounting is enabled and it's on a client
     */
    // private static final String CLIENT_ACCOUNTING_DISABLED = "D";
    protected static final String CLIENT_ACCOUNTING_QUEUE = "Q";
    protected static final String CLIENT_ACCOUNTING_IMMEDIATE = "I";
    /**
     * Cache
     */
    protected static CCache<Integer, MClient> s_cache =
            new CCache<>(Table_Name, 3, 120, true);
    /**
     * Client Info
     */
    protected MClientInfo m_info = null;

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ctx          context
     * @param AD_Client_ID id
     * @param createNew    create new
     */
    public MClient(int AD_Client_ID, boolean createNew) {
        super(AD_Client_ID);
        createNew = createNew;
        if (AD_Client_ID == 0) {
            if (createNew) {
                //	setValue (null);
                //	setName (null);
                setOrgId(0);
                setIsMultiLingualDocument(false);
                setIsSmtpAuthorization(false);
                setIsUseBetaFunctions(true);
                setIsServerEMail(false);
                setADLanguage(Language.getBaseAD_Language());
                setAutoArchive(AUTOARCHIVE_None);
                setMMPolicy(MMPOLICY_FiFo); // F
                setIsPostImmediate(false);
            } else load((HashMap) null);
        }
    } //	MClient

    /**
     * Standard Constructor
     *
     * @param ctx          context
     * @param AD_Client_ID id
     */
    public MClient(int AD_Client_ID) {
        this(AD_Client_ID, false);
    } //	MClient

    /**
     * Load Constructor
     *
     * @param ctx context
     */
    public MClient(Row row) {
        super(row);
    } //	MClient

    /**
     * Simplified Constructor
     *
     * @param ctx context
     */
    public MClient() {
        this(Env.getClientId());
    } //	MClient

    /**
     * Get optionally cached client
     *
     * @return client
     */
    public static MClient get() {
        return get(Environment.Companion.getCurrent().getClientId());
    } //	get

    /**
     * Get client
     *
     * @param AD_Client_ID id
     * @return client
     */
    public static MClient get(int AD_Client_ID) {
        Integer key = AD_Client_ID;
        MClient client = s_cache.get(key);
        if (client != null) return client;
        client = new MClient(AD_Client_ID);
        s_cache.put(key, client);
        return client;
    } //	get

    /**
     * Get all clients
     *
     * @return clients
     */
    public static MClient[] getAll() {
        return getAll("");
    } //	getAll

    /**
     * Get all clients
     *
     * @return clients
     */
    public static MClient[] getAll(String orderBy) {
        List<MClient> list =
                new Query(I_AD_Client.Table_Name, null).setOrderBy(orderBy).list();
        for (MClient client : list) {
            s_cache.put(client.getClientId(), client);
        }
        MClient[] retValue = new MClient[list.size()];
        list.toArray(retValue);
        return retValue;
    } //	getAll

    /**
     * Get Client Info
     *
     * @return Client Info
     */
    public MClientInfo getInfo() {
        if (m_info == null) m_info = MClientInfo.get(getClientId());
        return m_info;
    } //	getMClientInfo

    public boolean isClientAccountingImmediate() {
        String ca =
                MSysConfig.getConfigValue(
                        MSysConfig.CLIENT_ACCOUNTING,
                        CLIENT_ACCOUNTING_QUEUE, // default
                        Env.getClientId());
        return ca.equalsIgnoreCase(CLIENT_ACCOUNTING_IMMEDIATE);
    }

}
