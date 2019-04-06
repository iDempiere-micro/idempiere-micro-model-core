package org.idempiere.common.util

import software.hsharp.core.services.EnvironmentService
import java.util.Properties

/**
 * implementation of the [EnvironmentService].
 */
class EnvironmentServiceImpl(
    initialClientId: Int,
    initialOrgId: Int,
    initialUserId: Int
) : EnvironmentService {
    private var actualClientId = initialClientId
    private var actualOrgId = initialOrgId
    private var actualUserId = initialUserId

    override val clientId: Int
        get() = actualClientId
    override val orgId: Int
        get() = actualOrgId
    override val userId: Int
        get() = actualUserId
    override val context: Properties
        get() = mapOf(
            Env.AD_CLIENT_ID to clientId.toString(),
            Env.AD_USER_ID to userId.toString(),
            Env.AD_ORG_ID to orgId.toString()
        ).toProperties()

    override fun login(clientId: Int, orgId: Int, userId: Int) {
        actualClientId = clientId
        actualOrgId = orgId
        actualUserId = userId
    }
}