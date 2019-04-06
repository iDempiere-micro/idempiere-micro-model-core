package software.hsharp.core.util

import org.idempiere.common.exceptions.AdempiereException
import software.hsharp.core.modules.BaseModule
import software.hsharp.core.services.EnvironmentService

class Environment {
    companion object {
        private val context = object : InheritableThreadLocal<BaseModule>() {
            override fun initialValue(): BaseModule? {
                return null
            }
        }

        fun <T> run(baseModule: BaseModule, operation: () -> T): T {
            context.set(baseModule)
            return operation()
        }

        val current: EnvironmentService
            get() {
                return Environment.context.get()?.environmentService ?: throw AdempiereException("Setup the environment on the entry point first")
            }
        val module: BaseModule
            get() {
                return Environment.context.get() ?: throw AdempiereException("Setup the environment on the entry point first")
            }

        fun dispose() {
            Environment.context.remove()
        }
    }
}