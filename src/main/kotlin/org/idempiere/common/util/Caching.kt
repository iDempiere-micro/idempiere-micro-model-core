package org.idempiere.common.util

import org.idempiere.icommon.model.IPO
import java.util.Enumeration
import java.util.concurrent.ConcurrentHashMap

private class Memoize1<T, R>(val f: (T) -> R) : (T) -> R {
    private val values = ConcurrentHashMap<T, R>()
    override fun invoke(x: T): R {
        return values.getOrPut(x) { f(x) }
    }
    internal fun putAll(allValues: List<R>, deconstructor: (R) -> T) {
        values.clear()
        values.putAll(allValues.map { Pair(deconstructor(it), it) })
    }

    fun getAll(): Enumeration<R> = values.elements()
}

internal fun <T, R> ((T) -> R).memoize(): (T) -> R = Memoize1(this)

fun <T : IPO> factory(initializer: (Int) -> T): (Int) -> T = initializer
fun <T : IPO> factory(allValues: List<T>, initializer: (Int) -> T): (Int) -> T {
    val newInitializer = getOrPutMemoizedInitializer(initializer)
    @Suppress("UNCHECKED_CAST")
    val memoize1: Memoize1<Int, T> = newInitializer as Memoize1<Int, T>
    memoize1.putAll(allValues) { it.id }
    return initializer
}

private val memoizedInitializers = ConcurrentHashMap<(Int) -> IPO, (Int) -> IPO>()

private fun <T : IPO> getOrPutMemoizedInitializer(initializer: (Int) -> T): (Int) -> IPO =
    memoizedInitializers.getOrPut(initializer) { initializer.memoize() }

infix fun <T : IPO> Int.loadUsing(initializer: (Int) -> T): T {
    val memoizedInitializer = getOrPutMemoizedInitializer(initializer)
    @Suppress("UNCHECKED_CAST")
    return memoizedInitializer(this) as T
}

fun <T : IPO> getCachedOrLoadAll(id: Int, allValues: List<T>, initializer: (Int) -> T): T {
    val potentialMemoizedInitializer = memoizedInitializers[initializer]
    @Suppress("UNCHECKED_CAST")
    return if (potentialMemoizedInitializer != null)
        potentialMemoizedInitializer(id) as T
    else {
        val newInitializer = getOrPutMemoizedInitializer(initializer)
        val memoize1: Memoize1<Int, T> = newInitializer as Memoize1<Int, T>
        memoize1.putAll(allValues) { it.id }
        return newInitializer(id)
    }
}

fun <T : IPO> ((Int) -> T).all(): List<T> {
    @Suppress("UNCHECKED_CAST")
    val memoizedInitializer = getOrPutMemoizedInitializer(this) as Memoize1<Int, T>
    return memoizedInitializer.getAll().toList()
}

fun clearAllCaches() {
    memoizedInitializers.clear()
}