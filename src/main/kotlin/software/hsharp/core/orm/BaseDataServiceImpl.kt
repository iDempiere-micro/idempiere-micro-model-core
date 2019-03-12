package software.hsharp.core.orm

import org.compiere.orm.Query
import org.idempiere.icommon.model.IPO
import software.hsharp.core.models.BaseDataService
import software.hsharp.core.models.EnvironmentService

open class BaseDataServiceImpl<T : IPO> (
    private val environmentService: EnvironmentService,
    private val tableName: String,
    private val shared: Boolean
) : BaseDataService<T> {
    override fun getAll(): List<T> {
        return Query(environmentService.context, tableName, if (shared) "AD_Client_ID=0 OR AD_Client_ID=?" else "AD_Client_ID=?")
            .setParameters(environmentService.clientId)
            .list()
    }
}