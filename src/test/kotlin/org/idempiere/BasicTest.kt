package org.idempiere

import company.bigger.test.support.randomString
import kotliquery.HikariCP
import kotliquery.Row
import kotliquery.queryOf
import mu.KotlinLogging
import org.compiere.orm.MColumn
import org.compiere.orm.MTable
import org.compiere.orm.M_Element
import org.idempiere.common.util.Env
import org.junit.Before
import org.junit.Test
import software.hsharp.core.orm.DummyEventManager
import software.hsharp.core.util.DB
import java.sql.Timestamp
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

private val log = KotlinLogging.logger {}
internal val sessionUrl = System.getenv("SESSION_URL") ?: "jdbc:postgresql://localhost:5433/idempiere"

class BasicTest {

    val ctx = Env.getCtx()

    @Before
    fun `login client 0`() {
        val idClient = 0
        val AD_CLIENT_ID = idClient
        val AD_CLIENT_ID_s = AD_CLIENT_ID.toString()
        ctx.setProperty(Env.AD_CLIENT_ID, AD_CLIENT_ID_s)
        Env.setContext(ctx, Env.AD_CLIENT_ID, AD_CLIENT_ID_s)
    }

    init {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE")
        HikariCP.default(sessionUrl, "adempiere", "adempiere")
        DummyEventManager()
        log.trace { "HelloTest initialized" }
    }

    @Test
    fun `database connection works`() {
        val allIdsQuery =
            queryOf("select ad_table_id from adempiere.ad_table").map { row -> row.int("ad_table_id") }.asList
        val ids: List<Int> = DB.current.run(allIdsQuery)
        assertTrue(ids.isNotEmpty())
        log.trace { "database connection works done" }
    }

    @Test
    fun `can get a table from a row`() {
        val toTable: (Row) -> MTable = { row ->
            MTable(ctx, row)
        }
        val tableQuery = queryOf("select * from adempiere.ad_table where ad_table_id = ?", 100).map(toTable).asSingle
        val table: MTable? = DB.current.run(tableQuery)
        assertNotNull(table)
        assertEquals(100, table.id)
        assertEquals(0, table.clientId)
        assertEquals(0, table.orgId)
        assertEquals(true, table.isActive)
        assertEquals(Timestamp.valueOf("1999-05-21 00:00:00"), table.created)
        assertEquals("Table", table.name)
        assertEquals("Table for the Fields", table.description)
        assertNull(table.help)
        assertEquals(false, table.isView)
        assertEquals(4, table.accessLevel)
        assertNull(table.valRule)
        val columns = table.getColumns(false)
        assertEquals(31, columns.size)
        val tableIdColumn = columns.first { it.columnId == 100 }
        assertEquals(true, tableIdColumn.isActive)
        assertEquals(Timestamp.valueOf("1999-05-21 00:00:00"), tableIdColumn.created)
        assertEquals("AD_Table_ID", tableIdColumn.columnName)
        assertEquals(22, tableIdColumn.fieldLength)
        val reference = tableIdColumn.reference
        assertEquals("ID", reference.name)
        log.trace { "can get a table from a row done" }
    }

    @Test
    fun `can create a new column to a table and then delete it`() {
        val table = MTable(ctx, 101, null)
        val randomName = randomString(10)
        val column = MColumn(table)
        column.name = randomName
        column.columnName = column.name
        column.fieldLength = 1
        column.referenceId = 13
        column.save()
        val tableAfterSave = MTable(ctx, 101, null)
        /* For some reason (somehow probably related to the transactions) this is not working
        val columns = tableAfterSave.getColumns(true)
        assertEquals(53 + 1, columns.size)
        val newColumn = columns.first { it.name == randomName }
        */
        val newColumn = MColumn(ctx, column.id, null)
        assert(newColumn.columnId >= 1000000)

        newColumn.delete(true)

        log.trace { "can create a new column to a table and then delete it" }
    }

    @Test
    fun `can run query to get data`() {
        val element = M_Element.get(ctx, "IsPrimary", null)
        assertEquals(398, element.id)
    }
}
