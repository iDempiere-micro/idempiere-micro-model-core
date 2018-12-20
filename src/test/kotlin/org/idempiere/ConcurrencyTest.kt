package org.idempiere

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner
import kotliquery.Row
import kotliquery.queryOf
import mu.KotlinLogging
import org.compiere.orm.MTable
import org.idempiere.common.util.Env
import org.junit.Test
import org.junit.runner.RunWith
import software.hsharp.core.util.DB
import software.hsharp.core.util.HikariCPI
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

private val log = KotlinLogging.logger {}

@RunWith(ConcurrentTestRunner::class)
class ConcurrencyTest {
    init {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE")
        HikariCPI.default(sessionUrl, "adempiere", "adempiere")
        log.trace { "ConcurrencyTest initialized" }
    }

    @Test
    fun runTest() {
        DB.run {
            val toTable: (Row) -> MTable = { row ->
                MTable(Env.getCtx(), row)
            }
            val tableQuery =
                queryOf("select * from adempiere.ad_table where ad_table_id = ?", 100).map(toTable).asSingle
            val table: MTable? = DB.current.run(tableQuery)
            assertNotNull(table)
            assertEquals(100, table.id)
        }
    }
}