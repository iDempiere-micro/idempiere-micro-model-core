package software.hsharp.api.icommon

import org.idempiere.icommon.db.AdempiereDatabase
import java.sql.Connection

interface ICConnection {
    fun setDataSource()
    fun setDataSource(o: Any)
    fun readInfo(connRW: Connection);
    fun getConnection(autoCommit: Boolean, trxLevel: Int) : Connection
    fun getDatabase() : AdempiereDatabase

    val dbHost: String
    val dbPort: Int
    val dbName: String
    val dbUid: String
    val dbPwd: String
    val ssl: Boolean
    val isDataSource: Boolean
    val oracle: Boolean
    val postgreSQL: Boolean
    val dbInfo: String

}