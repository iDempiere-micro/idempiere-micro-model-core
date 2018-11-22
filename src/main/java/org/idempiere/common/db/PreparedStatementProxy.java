package org.idempiere.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import org.idempiere.common.db.util.AutoCommitConnectionBroker;
import org.idempiere.common.exceptions.DBException;
import org.idempiere.common.util.CStatementVO;
import org.idempiere.common.util.DB;
import org.idempiere.common.util.Trx;

/**
 * Dynamic proxy for the CPreparedStatement interface
 *
 * @author Low Heng Sin
 */
public class PreparedStatementProxy extends StatementProxy {

  public PreparedStatementProxy(
      int resultSetType, int resultSetConcurrency, String sql0, String trxName) {
    if (sql0 == null || sql0.length() == 0) throw new IllegalArgumentException("sql required");

    p_vo =
        new CStatementVO(
            resultSetType, resultSetConcurrency, DB.getDatabase().convertStatement(sql0));

    p_vo.setTrxName(trxName);

    init();
  } // PreparedStatementProxy

  public PreparedStatementProxy(CStatementVO vo) {
    super(vo);
  } //	PreparedStatementProxy

  /** Initialise the prepared statement wrapper object */
  protected void init() {
    try {
      Connection conn = null;
      Trx trx = p_vo.getTrxName() == null ? null : Trx.get(p_vo.getTrxName(), false);
      if (trx != null) {
        conn = trx.getConnection();
      } else {
        m_conn = AutoCommitConnectionBroker.getConnection();
        conn = m_conn;
      }
      if (conn == null) throw new DBException("No Connection");
      p_stmt =
          conn.prepareStatement(
              p_vo.getSql(), p_vo.getResultSetType(), p_vo.getResultSetConcurrency());
    } catch (Exception e) {
      log.log(Level.SEVERE, p_vo.getSql(), e);
      throw new DBException(e);
    }
  }

  @Override
  protected RowSet getRowSet() {
    log.finest("local_getRowSet");

    RowSet rowSet = null;
    ResultSet rs = null;
    PreparedStatement pstmt = (PreparedStatement) p_stmt;
    try {
      rs = pstmt.executeQuery();
      CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
      crs.populate(rs);
      rowSet = crs;
    } catch (Exception ex) {
      log.log(Level.SEVERE, p_vo.toString(), ex);
      throw new RuntimeException(ex);
    } finally {
      DB.close(rs);
    }
    return rowSet;
  } //	local_getRowSet
}
