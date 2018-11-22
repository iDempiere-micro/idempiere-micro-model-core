package org.idempiere.common.db.util;

import java.sql.Connection;
import java.sql.SQLException;
import org.idempiere.common.util.DB;

/**
 * This class managed the sharing of non-transactional connection per thread.
 *
 * @author hengsin
 */
public class AutoCommitConnectionBroker {
  private static ThreadLocal<ConnectionReference> threadLocalConnection =
      new ThreadLocal<ConnectionReference>() {
        protected ConnectionReference initialValue() {
          return null;
        }
      };

  /**
   * Retrieve non-transactional connection for current thread. If none have been allocated yet, a
   * new one will be created from the connection pool.
   *
   * @return Connection
   */
  public static Connection getConnection() {
    ConnectionReference connReference = threadLocalConnection.get();
    try {
      if (connReference != null && !connReference.connection.isClosed()) {
        connReference.referenceCount++;
        return connReference.connection;
      }
    } catch (SQLException e) {
    }

    Connection connection = DB.createConnection(true, false, Connection.TRANSACTION_READ_COMMITTED);
    connReference = new ConnectionReference(connection);
    threadLocalConnection.set(connReference);
    return connection;
  }

  /**
   * Release connection. The connection goes back to pool if reference count is zero.
   *
   * @param conn
   */
  public static void releaseConnection(Connection conn) {
    ConnectionReference connReference = threadLocalConnection.get();
    if (connReference != null && connReference.connection == conn) {
      connReference.referenceCount--;
      if (connReference.referenceCount <= 0) {
        threadLocalConnection.set(null);
        try {
          connReference.connection.close();
        } catch (SQLException e) {
        }
      }
    } else {
      try {
        conn.close();
      } catch (SQLException e) {
      }
    }
  }

  private static class ConnectionReference {
    protected Connection connection;
    protected int referenceCount;

    protected ConnectionReference(Connection conn) {
      connection = conn;
      referenceCount = 1;
    }
  }
}
