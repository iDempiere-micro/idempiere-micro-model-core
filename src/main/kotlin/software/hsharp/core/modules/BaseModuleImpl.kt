package software.hsharp.core.modules

import org.compiere.orm.IModelFactory
import software.hsharp.core.services.EnvironmentService

class BaseModuleImpl(
    override val environmentService: EnvironmentService,
    override val modelFactory: IModelFactory
) : BaseModule