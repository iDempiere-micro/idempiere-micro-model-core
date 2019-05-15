package software.hsharp.core.modules

import org.compiere.orm.ModelFactory
import software.hsharp.core.services.EnvironmentService

open class BaseModuleImpl(
    override val environmentService: EnvironmentService,
    override val modelFactory: ModelFactory
) : BaseModule