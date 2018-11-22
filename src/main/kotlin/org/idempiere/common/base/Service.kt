package org.idempiere.common.base

/**
 * This is a very simple factory for service locators
 *
 * @author viola
 */
abstract class Service {

    protected abstract val locator: IServiceLocator

    companion object {

        private var theLocator: IServiceLocator? = null
        internal var instance: Service? = null
        private val mutex = Any()

        /**
         *
         * @return service locator instance
         */
        fun locator(): IServiceLocator? {
            synchronized(mutex) {
                if (theLocator != null) {
                    return theLocator
                } else if (instance != null) {
                    theLocator = instance!!.locator
                    return theLocator
                } else
                    throw IllegalStateException("Service Locator")
            }
        }
    }
}