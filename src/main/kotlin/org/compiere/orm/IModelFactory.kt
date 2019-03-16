package org.compiere.orm

import kotliquery.Row
import org.idempiere.orm.PO

/**
 * Model factory interface, extracted from legacy code in MTable.
 *
 * @author hengsin
 */
interface IModelFactory {
    /**
     * Get Persistence Class for Table
     *
     * @param tableName table name
     * @return class or null
     */
    fun getClass(tableName: String): Class<*>

    /**
     * ************************************************************************ Get PO Class Instance
     *
     * @param tableName
     * @param Record_ID record
     * @param trxName
     * @return PO for Record or null
     */
    fun getPO(tableName: String, Record_ID: Int): PO

    /**
     * Get PO Class Instance
     *
     * @param tableName
     * @param rs result set
     * @param trxName transaction
     * @return PO for Record or null
     */
    fun <T> getPO(tableName: String, row: Row): T
}
