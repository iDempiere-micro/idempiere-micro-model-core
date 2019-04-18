package org.idempiere

import kotliquery.Row
import kotliquery.queryOf
import mu.KotlinLogging
import org.compiere.orm.MColumn
import org.compiere.orm.MTable
import org.compiere.orm.M_Element
import org.compiere.orm.getClientOrganizations
import org.compiere.orm.getOrg
import org.idempiere.common.util.EnvironmentServiceImpl
import org.junit.Test
import software.hsharp.core.modules.BaseModuleImpl
import software.hsharp.core.orm.BaseSimpleModelFactory
import software.hsharp.core.util.DB
import software.hsharp.core.util.Environment
import software.hsharp.core.util.HikariCPI
import java.sql.Timestamp
import java.util.Random
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Generate a random string (small letters)
 */
fun randomString(length: Int): String {
    fun ClosedRange<Char>.randomString(length: Int) =
        (1..length)
            .map { (Random().nextInt(endInclusive.toInt() - start.toInt()) + start.toInt()).toChar() }
            .joinToString("")
    return ('a'..'z').randomString(length)
}

private val log = KotlinLogging.logger {}
internal val sessionUrl = System.getenv("SESSION_URL") ?: "jdbc:postgresql://localhost:5433/idempiere"

private val environment = EnvironmentServiceImpl(0, 0, 0)
private val baseModule = BaseModuleImpl(environmentService = environment, modelFactory = BaseSimpleModelFactory())

class BasicTest {
    init {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE")
        HikariCPI.default(sessionUrl, "adempiere", "adempiere")
        log.trace { "HelloTest initialized" }
    }

    @Test
    fun `database connection works`() {
        Environment.run(baseModule) {
            DB.run {
                val allIdsQuery =
                    queryOf("select ad_table_id from adempiere.ad_table").map { row -> row.int("ad_table_id") }.asList
                val ids: List<Int> = DB.current.run(allIdsQuery)
                assertTrue(ids.isNotEmpty())
                log.trace { "database connection works done" }
            }
        }
    }

    @Test
    fun `can get a table from a row`() {
        Environment.run(baseModule) {
            DB.run {
                val toTable: (Row) -> MTable = { row ->
                    MTable(row)
                }
                val tableQuery =
                    queryOf("select * from adempiere.ad_table where ad_table_id = ?", 100).map(toTable).asSingle
                val table: MTable? = DB.current.run(tableQuery)
                assertNotNull(table)
                assertEquals(100, table.id)
                assertEquals(0, table.clientId)
                assertEquals(0, table.orgId)
                assertEquals(true, table.isActive())
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
                assertEquals(true, tableIdColumn.isActive())
                assertEquals(Timestamp.valueOf("1999-05-21 00:00:00"), tableIdColumn.created)
                assertEquals("AD_Table_ID", tableIdColumn.columnName)
                assertEquals(22, tableIdColumn.fieldLength)
                val reference = tableIdColumn.reference
                assertEquals("ID", reference.name)
                log.trace { "can get a table from a row done" }
            }
        }
    }

    @Test
    fun `can create a new column to a table and then delete it`() {
        Environment.run(baseModule) {
            DB.run {
                val table = MTable(101)
                val randomName = randomString(10)
                val column = MColumn(table)
                column.name = randomName
                column.columnName = column.name
                column.fieldLength = 1
                column.referenceId = 13
                column.save()
                val tableAfterSave = MTable(101)
                val columns = tableAfterSave.getColumns(true)
                val newColumn = columns.first { it.name == randomName }
                assert(newColumn.columnId >= 1000000)

                newColumn.delete(true)

                log.trace { "can create a new column to a table and then delete it" }
            }
        }
    }

    @Test
    fun `can run query to get data`() {
        Environment.run(baseModule) {
            DB.run {
                val element = M_Element.get("IsPrimary")
                assertEquals(398, element.id)
            }
        }
    }

    @Test
    fun `can get MOrg`() {
        Environment.run(baseModule) {
            DB.run {
                val org = getOrg(11)
                assertEquals("HQ", org.name)
                val org2 = getOrg(11)
                assertEquals(org.name, org2.name)
            }
        }
    }

    @Test
    fun `can get MOrg of PO`() {
        Environment.run(baseModule) {
            DB.run {
                val org = getOrg(11)
                val orgs = getClientOrganizations(org)
                assertEquals(9, orgs.size)
                println(orgs)
            }
        }
    }

    @Test
    fun `can get MOrg linked business partner`() {
        Environment.run(baseModule) {
            DB.run {
                val org = getOrg(50005)
                val partnerId1 = org.linkedBusinessPartnerId
                assertEquals(50005, partnerId1)
                val partnerId2 = org.linkedBusinessPartnerId
                assertEquals(50005, partnerId2)
                val org2 = getOrg(50004)
                val partnerId3 = org2.linkedBusinessPartnerId
                assertEquals(50008, partnerId3)
            }
        }
    }
}
