package org.idempiere.common.util

import software.hsharp.core.models.EnvironmentService
import java.util.*

class EnvironmentServiceImpl : EnvironmentService {
    override val clientId: Int
        get() = Env.getClientId(Env.getCtx())

    override val context: Properties
        get() = Env.getCtx()
}