package org.idempiere

import org.idempiere.common.util.all
import org.idempiere.common.util.clearAllCaches
import org.idempiere.common.util.factory
import org.idempiere.common.util.getCachedOrLoadAll
import org.idempiere.common.util.loadUsing
import org.idempiere.common.util.memoize
import org.idempiere.icommon.model.IPO
import org.junit.Test
import kotlin.test.assertEquals

private var counter: Int = 0

private class Test(
    override val id: Int,
    override val keyColumns: Array<String> = emptyArray(),
    override val tableName: String = "",
    override val isNew: Boolean = false,
    override val orgId: Int = 0,
    override val tableId: Int = 0,
    override val clientId: Int = 0
) : IPO {
    override fun isActive(): Boolean {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getValueOfColumn(columnId: Int): Any {
        TODO("not implemented")
    }

    override fun getColumnIndex(columnName: String): Int {
        TODO("not implemented")
    }

    override fun getValue(columnName: String): Any? {
        TODO("not implemented")
    }

    override fun getValue(index: Int): Any? {
        TODO("not implemented")
    }

    override fun save(): Boolean {
        TODO("not implemented")
    }

    init {
        counter++
    }
}

class CachingTest {
    @Test
    fun `memoize works`() {
        counter = 0

        fun isFactor(number: Int, potential: Int) = number % potential == 0
        fun factorsOf(number: Int) = (1 to number).toList().filter { isFactor(number, it) }
        fun sumOfFactors(number: Int) {
            counter++
            factorsOf(number).sum()
        }
        val memoizedSumFactors = { x: Int -> sumOfFactors(x) }.memoize()
        memoizedSumFactors(3)
        memoizedSumFactors(3)

        assertEquals(1, counter)
    }

    @Test
    fun `Caching works`() {
        counter = 0
        val initializer = factory { org.idempiere.Test(it) }
        1 loadUsing initializer
        1 loadUsing initializer
        assertEquals(1, counter)
    }

    @Test
    fun `Cache global reset works`() {
        counter = 0
        val initializer = factory { org.idempiere.Test(it) }
        1 loadUsing initializer
        clearAllCaches()
        1 loadUsing initializer
        assertEquals(2, counter)
    }

    @Test
    fun `cache with all the values works`() {
        val id2 = (0..Int.MAX_VALUE).random()
        val values = listOf(Test(-1), Test(id2))
        val initializer = factory { org.idempiere.Test(it) }
        assertEquals(2, counter)

        counter = 0
        val result = getCachedOrLoadAll(id2, values, initializer)
        assertEquals(0, counter)
        assertEquals(id2, result.id)
        val result2 = id2 loadUsing initializer
        assertEquals(0, counter)
        assertEquals(id2, result2.id)
        val data = initializer.all().toList()
        assertEquals(2, data.size)
        val result3 = data.get(1)
        assertEquals(id2, result3.id)
    }

    @Test
    fun `cache can return all the values even without asking for a value first`() {
        counter = 0
        val id2 = (0..Int.MAX_VALUE).random()
        val values = listOf(Test(-1), Test(id2))
        val initializer = factory(values) { org.idempiere.Test(it) }
        assertEquals(2, counter)

        counter = 0

        val data = initializer.all().toList()
        assertEquals(0, counter)
        assertEquals(2, data.size)
        val result3 = data.get(1)
        assertEquals(id2, result3.id)
        assertEquals(0, counter)

        val result4 = id2 loadUsing initializer
        assertEquals(id2, result4.id)
        assertEquals(0, counter)
    }
}