package org.idempiere.common.util

import org.idempiere.icommon.model.PersistentObject
import java.util.Enumeration
import java.util.concurrent.ConcurrentHashMap

private class Memoize0<R>(val f: () -> R) : () -> R {
    private val values = ConcurrentHashMap<Int, R>()
    override fun invoke(): R {
        return values.getOrPut(0) { f() }
    }
    internal fun clear() = values.clear()
}
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
    internal fun clear() = values.clear()
}
private class Memoize2<P1, P2, R>(val f: (P1, P2) -> R) : (P1, P2) -> R {
    private val values = ConcurrentHashMap<Pair<P1, P2>, R>()
    override fun invoke(p1: P1, p2: P2): R {
        return values.getOrPut(Pair(p1, p2)) { f(p1, p2) }
    }
    internal fun clear() = values.clear()
}
private class Memoize3<P1, P2, P3, R>(val f: (P1, P2, P3) -> R) : (P1, P2, P3) -> R {
    private val values = ConcurrentHashMap<Triple<P1, P2, P3>, R>()
    override fun invoke(p1: P1, p2: P2, p3: P3): R {
        return values.getOrPut(Triple(p1, p2, p3)) { f(p1, p2, p3) }
    }
    internal fun clear() = values.clear()
}

internal fun <R> (() -> R).memoize(): () -> R = Memoize0(this)
internal fun <T, R> ((T) -> R).memoize(): (T) -> R = Memoize1(this)
internal fun <P1, P2, R> ((P1, P2) -> R).memoize(): (P1, P2) -> R = Memoize2(this)
internal fun <P1, P2, P3, R> ((P1, P2, P3) -> R).memoize(): (P1, P2, P3) -> R = Memoize3(this)

internal fun <R> (() -> R).memoClear() = (this as Memoize0).clear()
internal fun <T, R> ((T) -> R).memoClear() = (this as Memoize1).clear()
internal fun <P1, P2, R> ((P1, P2) -> R).memoClear() = (this as Memoize2).clear()
internal fun <P1, P2, P3, R> ((P1, P2, P3) -> R).memoClear() = (this as Memoize3).clear()

fun <T : PersistentObject> factory(initializer: (Int) -> T): (Int) -> T = initializer
fun <T : PersistentObject> factoryString(initializer: (String) -> T): (String) -> T = initializer
fun <T : PersistentObject> factory(allValues: List<T>, initializer: (Int) -> T): (Int) -> T {
    val newInitializer = getOrPutMemoizedInitializer(initializer)
    @Suppress("UNCHECKED_CAST")
    val memoize1: Memoize1<Int, T> = newInitializer as Memoize1<Int, T>
    memoize1.putAll(allValues) { it.id }
    return initializer
}

private val memoizedInitializers = ConcurrentHashMap<(Int) -> PersistentObject, (Int) -> PersistentObject>()
private val memoizedInitializersString = ConcurrentHashMap<(String) -> PersistentObject, (String) -> PersistentObject>()

private fun <T : PersistentObject> getOrPutMemoizedInitializer(initializer: (Int) -> T): (Int) -> PersistentObject =
    memoizedInitializers.getOrPut(initializer) { initializer.memoize() }

infix fun <T : PersistentObject> Int.loadUsing(initializer: (Int) -> T): T {
    val memoizedInitializer = getOrPutMemoizedInitializer(initializer)
    @Suppress("UNCHECKED_CAST")
    return memoizedInitializer(this) as T
}

infix fun <T : PersistentObject> String.loadUsing(initializer: (String) -> T): T {
    val memoizedInitializer = memoizedInitializersString.getOrPut(initializer) { initializer.memoize() }
    @Suppress("UNCHECKED_CAST")
    return memoizedInitializer(this) as T
}

fun <T : PersistentObject> getCachedOrLoadAll(id: Int, allValues: List<T>, initializer: (Int) -> T): T {
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

fun <T : PersistentObject> ((Int) -> T).all(): List<T> {
    @Suppress("UNCHECKED_CAST")
    val memoizedInitializer = getOrPutMemoizedInitializer(this) as Memoize1<Int, T>
    return memoizedInitializer.getAll().toList()
}

fun clearAllCaches() {
    memoizedInitializers.clear()
}