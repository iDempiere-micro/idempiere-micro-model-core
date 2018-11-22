package org.idempiere.common.base

/**
 * A service locator looks up services.
 * This is the central authority for idempiere service definition,
 * because each service defined has to be looked up via this interface.
 *
 * A service in idempiere is an implementation for the registered interface, expose through osgi service registry
 *
 * @author viola
 */
interface IServiceLocator {
    /**
     *
     * @param type service interface
     * @return holder for dynamic service
     */
    fun <T> locate(type: Class<T>): IServiceHolder<T>

    /**
     *
     * @param type
     * @param query
     * @return
     */
    fun <T> locate(type: Class<T>, query: ServiceQuery): IServiceHolder<T>

    /**
     *
     * @param type
     * @param componentName service component name
     * @param query
     * @return holder for dynamic service
     */
    fun <T> locate(type: Class<T>, componentName: String, query: ServiceQuery): IServiceHolder<T>

    /**
     *
     * @param type
     * @return holder for list of dynamic service
     */
    fun <T> list(type: Class<T>): IServicesHolder<T>

    /**
     *
     * @param type
     * @param query
     * @return holder for list of dynamic service
     */
    fun <T> list(type: Class<T>, query: ServiceQuery): IServicesHolder<T>

    /**
     *
     * @param type
     * @param componentName osgi service component name
     * @param query
     * @return holder for list of dynamic service
     */
    fun <T> list(type: Class<T>, componentName: String, query: ServiceQuery): IServicesHolder<T>
}