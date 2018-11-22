package org.idempiere.common.util;

import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.RowSet;

/**
 * Interface to wrap and extend Statement
 *
 * @author Low Heng Sin
 */
public interface CStatement extends Statement {
  /**
   * Get Sql
   *
   * @return sql
   */
  public String getSql();

  /**
   * Execute Query
   *
   * @return ResultSet or RowSet
   * @throws SQLException
   * @see java.sql.PreparedStatement#executeQuery()
   */
  public RowSet getRowSet();

  /**
   * @return boolean
   * @throws SQLException
   */
  public boolean isClosed() throws SQLException;

  /** @throws SQLException */
  public void commit() throws SQLException;

  /**
   * don't call this directly, invoke by finalizer thread
   *
   * @throws Throwable
   */
  public void finalize() throws Throwable;
} //	CStatement
