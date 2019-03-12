package software.hsharp.core.orm

import org.compiere.orm.Query
import org.idempiere.icommon.model.IPO
import software.hsharp.core.models.BaseDataService
import software.hsharp.core.models.EnvironmentService

/**
 * Implementation of the [BaseDataService] to serve [T].
 * if [shared] is set then data for client = 0 are returned too in [getAll].
 */
open class BaseDataServiceImpl<T : IPO> (
    private val environmentService: EnvironmentService,
    private val tableName: String,
    private val shared: Boolean
) : BaseDataService<T> {

    /**
     * get all [T] either also with for client = 0 or only for the current clitn
     */
    override fun getAll(): List<T> {
        return Query(environmentService.context, tableName, if (shared) "AD_Client_ID=0 OR AD_Client_ID=?" else "AD_Client_ID=?")
            .setParameters(environmentService.clientId)
            .list()
    }
}