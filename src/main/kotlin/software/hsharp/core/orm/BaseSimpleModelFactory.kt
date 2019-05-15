package software.hsharp.core.orm

import kotliquery.Row
import org.compiere.orm.ModelFactory
import org.idempiere.common.exceptions.AdempiereException
import org.idempiere.icommon.model.PersistentObject

open class BaseSimpleModelFactory(
    private val simpleMapperId: Map<String, (Int) -> PersistentObject>,
    private val simpleMapperRow: Map<String, (Row) -> PersistentObject>
) : ModelFactory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : PersistentObject> getPO(tableName: String, recordId: Int): T {
        return if (simpleMapperId.containsKey(tableName)) simpleMapperId[tableName]?.invoke(recordId) as T else throw AdempiereException("Table '$tableName' is unknown for id $recordId")
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : PersistentObject> getPO(tableName: String, row: Row): T {
        return if (simpleMapperRow.containsKey(tableName)) simpleMapperRow[tableName]?.invoke(row) as T else throw AdempiereException("Table '$tableName' is unknown")
    }
}