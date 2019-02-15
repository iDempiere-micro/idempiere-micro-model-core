package org.compiere.orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.idempiere.common.exceptions.DBException;

/**
 * Simple wrapper over jdbc resultset
 *
 * @author Low Heng Sin
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *     <li>FR [ 1984834 ] Add POResultSet.hasNext convenient method
 *     <li>FR [ 1985134 ] POResultSet improvements
 */
public class POResultSet<T extends PO> {

  private String trxName;
  private ResultSet resultSet;
  private MTable table;
  private PreparedStatement statement;
  /** Current fetched PO */
  private T currentPO = null;
  /** Should we close the statement and resultSet on any exception that occur ? */
  private boolean closeOnError = true;

  /**
   * Constructs the POResultSet. By default, closeOnError option is false. You need to set it
   * explicitly.
   *
   * @param table
   * @param ps
   * @param rs
   * @param trxName
   */
  public POResultSet(MTable table, PreparedStatement ps, ResultSet rs) {
    this.table = table;
    this.statement = ps;
    this.resultSet = rs;
    this.trxName = trxName;
    this.closeOnError = false;
  }

  /**
   * convenient method to close a {@link POResultSet}
   *
   * @param rs result set
   * @see POResultSet#close()
   */
  public static void close(POResultSet<?> rs) {
    if (rs != null) rs.close();
  }

  /**
   * @return true if it has next, false otherwise
   * @throws DBException
   */
  public boolean hasNext() throws DBException {
    if (currentPO != null) return true;
    currentPO = next();
    return currentPO != null;
  }

  /**
   * @return PO or null if reach the end of resultset
   * @throws DBException
   */
  @SuppressWarnings("unchecked")
  public T next() throws DBException {
    if (currentPO != null) {
      T po = currentPO;
      currentPO = null;
      return po;
    }
    try {
      if (resultSet.next()) {
        return (T) table.getPO(resultSet);
      } else {
        this.close(); // close it if there is no more data to read
        return null;
      }
    } catch (SQLException e) {
      if (this.closeOnError) {
        this.close();
      }
      throw new DBException(e);
    }
    // Catching any RuntimeException, and close the resultset (if closeOnError is set)
    catch (RuntimeException e) {
      if (this.closeOnError) {
        this.close();
      }
      throw e;
    }
  }

    /**
   * Should we automatically close the {@link PreparedStatement} and {@link ResultSet} in case we
   * get an error.
   *
   * @param closeOnError
   */
  public void setCloseOnError(boolean closeOnError) {
    this.closeOnError = closeOnError;
  }

  /** Release database resources. */
  public void close() {
    this.resultSet = null;
    this.statement = null;
    currentPO = null;
  }
}
