package software.hsharp.core.modules

import org.compiere.orm.ModelFactory
import software.hsharp.core.services.EnvironmentService

/**
 * Base implementation of [BaseModule] with just [EnvironmentService] and [ModelFactory]
 */
open class BaseModuleImpl(
    override val environmentService: EnvironmentService,
    override val modelFactory: ModelFactory
) : BaseModule