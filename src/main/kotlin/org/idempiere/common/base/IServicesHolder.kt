package org.idempiere.common.base

/**
 *
 * @author hengsin
 *
 * @param <T>
</T> */
interface IServicesHolder<T> {

    /**
     *
     * @return list of service instance. null if not available or no matching service found
     */
    val services: List<T>
}