package software.hsharp.core.util

import org.idempiere.common.exceptions.AdempiereException
import software.hsharp.core.services.EnvironmentService

class Environment {
    companion object {
        private val context = object : InheritableThreadLocal<EnvironmentService>() {
            override fun initialValue(): EnvironmentService? {
                return null
            }
        }

        fun <T> run(env: EnvironmentService, operation: () -> T): T {
            context.set(env)
            return operation()
        }

        val current: EnvironmentService
            get() {
                return Environment.context.get() ?: throw AdempiereException("Setup the environment on the entry point first")
            }

        fun dispose() {
            Environment.context.remove()
        }
    }
}