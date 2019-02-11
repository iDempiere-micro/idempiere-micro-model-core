package org.idempiere.common.util

import software.hsharp.core.models.EnvironmentService

class EnvironmentServiceImpl : EnvironmentService {
    override val clientId: Int
        get() = Env.getClientId(Env.getCtx())
}