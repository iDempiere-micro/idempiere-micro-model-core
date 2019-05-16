package org.compiere.orm

import kotliquery.Row
import org.compiere.model.Client
import org.compiere.model.ClientInfo
import org.idempiere.common.util.Env
import org.idempiere.common.util.Language
import org.idempiere.common.util.all
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing
import software.hsharp.core.util.Environment

private fun loadAllClients() = Query<Client>(Client.Table_Name, null).list()

private val clientFactory = factory(loadAllClients()) { MClient(it) }

/**
 * Get client by Id
 */
fun getClient(id: Int) = id loadUsing clientFactory

/**
 * Get current client
 */
fun getClient() = getClient(Environment.current.clientId)

/**
 * Get all clients
 */
fun getAllClients() = clientFactory.all()

open class MClient : X_AD_Client {
    /**
     * Client Info
     */
    protected var m_info: MClientInfo? = null

    /**
     * Get Client Info
     *
     * @return Client Info
     */
    open val info: ClientInfo?
        get() {
            if (m_info == null) m_info = getClientInfo(clientId)
            return m_info
        } // 	getMClientInfo

    // default
    val isClientAccountingImmediate: Boolean
        get() {
            val ca = MSysConfig.getConfigValue(
                MSysConfig.CLIENT_ACCOUNTING,
                CLIENT_ACCOUNTING_QUEUE,
                Env.getClientId()
            )
            return ca.equals(CLIENT_ACCOUNTING_IMMEDIATE, ignoreCase = true)
        }

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param AD_Client_ID id
     * @param createNew create new
     */
    @JvmOverloads
    constructor(AD_Client_ID: Int = Env.getClientId(), createNew: Boolean = false) : super(AD_Client_ID) {
        if (AD_Client_ID == 0) {
            if (createNew) {
                setOrgId(0)
                setIsMultiLingualDocument(false)
                setIsSmtpAuthorization(false)
                setIsUseBetaFunctions(true)
                setIsServerEMail(false)
                adLanguage = Language.getBaseLanguageCode()
                setAutoArchive(AUTOARCHIVE_None)
                mmPolicy = MMPOLICY_FiFo // F
                setIsPostImmediate(false)
            } else
                loadFromMap(null)
        }
    } // 	MClient

    /**
     * Load Constructor
     *
     */
    constructor(row: Row) : super(row) {} // 	MClient

    companion object {
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
        const val CLIENT_ACCOUNTING_QUEUE = "Q"
        const val CLIENT_ACCOUNTING_IMMEDIATE = "I"

        /**
         * None = N
         */
        const val AUTOARCHIVE_None = "N"
        /**
         * FiFo = F
         */
        const val MMPOLICY_FiFo = "F"
    }
}
