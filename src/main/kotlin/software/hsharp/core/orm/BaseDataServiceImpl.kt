package software.hsharp.core.orm

import org.compiere.orm.Query
import org.idempiere.icommon.model.IPO
import software.hsharp.core.services.BaseDataService
import software.hsharp.core.services.EnvironmentService

/**
 * Implementation of the [BaseDataService] to serve [T].
 * if [shared] is set then data for client = 0 are returned too in [getAll].
 */
open class BaseDataServiceImpl<T : IPO> (
    private val environmentService: EnvironmentService,
    private val tableName: String,
    private val shared: Boolean
) : BaseDataService<T> {

    protected open fun andWhere(): String = "1=1"

    /**
     * get all [T] either also with for client = 0 or only for the current clitn
     */
    override fun getAll(): List<T> {
        val where =
            (if (shared)
                "AD_Client_ID=0 OR AD_Client_ID=?"
            else "AD_Client_ID=?") + " AND " + andWhere()
        return Query(tableName, where)
            .setParameters(environmentService.clientId)
            .list()
    }
}