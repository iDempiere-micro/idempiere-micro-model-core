package org.idempiere.common.base

/**
 *
 * @author hengsin
 *
 * @param <T>
</T> */
interface IServiceHolder<T> {

    /**
     *
     * @return service instance. null if not available or no matching service found
     */
    val service: T?
}