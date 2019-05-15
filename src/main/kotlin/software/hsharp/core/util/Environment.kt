package software.hsharp.core.util

import org.idempiere.common.exceptions.AdempiereException
import software.hsharp.core.modules.BaseModule
import software.hsharp.core.services.EnvironmentService

class Environment<U : BaseModule> {
    constructor(baseModule: U) {
        context.set(baseModule)
    }
    constructor()

    val module: U
        get() {
            @Suppress("UNCHECKED_CAST")
            return context.get() as U? ?: throw AdempiereException("Setup the environment on the entry point first")
        }

    companion object {
        private val context = object : InheritableThreadLocal<BaseModule>() {
            override fun initialValue(): BaseModule? {
                return null
            }
        }

        fun <T> run(operation: () -> T): T {
            return operation()
        }

        val current: EnvironmentService
            get() {
                return Environment.context.get()?.environmentService ?: throw AdempiereException("Setup the environment on the entry point first")
            }

        fun dispose() {
            Environment.context.remove()
        }
    }
}