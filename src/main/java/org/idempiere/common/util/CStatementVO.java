package org.idempiere.common.util;

import java.io.Serializable;

/**
 * Adempiere Statement Value Object
 *
 * @author Jorg Janke
 * @version $Id: CStatementVO.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 */
public class CStatementVO implements Serializable {
  /**
   * VO Constructor
   *
   * @param resultSetType - ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE,
   *     ResultSet.TYPE_SCROLL_SENSITIVE
   * @param resultSetConcurrency - ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE
   */
  public CStatementVO(int resultSetType, int resultSetConcurrency) {
    setResultSetType(resultSetType);
    setResultSetConcurrency(resultSetConcurrency);
  } //	CStatementVO

  /**
   * VO Constructor
   *
   * @param resultSetType - ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE,
   *     ResultSet.TYPE_SCROLL_SENSITIVE
   * @param resultSetConcurrency - ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE
   * @param sql sql
   */
  public CStatementVO(int resultSetType, int resultSetConcurrency, String sql) {
    this(resultSetType, resultSetConcurrency);
    setSql(sql);
  } //	CStatementVO

  /** Serialization Info * */
  static final long serialVersionUID = -3393389471515956399L;

  /** Type */
  private int m_resultSetType;
  /** Concurrency */
  private int m_resultSetConcurrency;
  /** SQL Statement */
  private String m_sql;
  /** Transaction Name * */
  private String m_trxName = null;
  /**
   * String representation
   *
   * @return info
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("CStatementVO[");
    sb.append("SQL=" + getSql());
    if (m_trxName != null) sb.append(" TrxName=" + m_trxName);
    sb.append("]");
    return sb.toString();
  } //	toString

  /**
   * Get SQL
   *
   * @return sql
   */
  public String getSql() {
    return m_sql;
  } //	getSql

  /**
   * Set SQL. Replace ROWID with TRIM(ROWID) for remote SQL to convert into String as ROWID is not
   * serialized
   *
   * @param sql sql
   */
  public void setSql(String sql) {
    m_sql = sql;
  } //	setSql

  /**
   * Get ResultSet Concurrency
   *
   * @return rs concurrency
   */
  public int getResultSetConcurrency() {
    return m_resultSetConcurrency;
  }
  /**
   * Get ResultSet Type
   *
   * @return rs type
   */
  public int getResultSetType() {
    return m_resultSetType;
  }
  /**
   * Set ResultSet Type
   *
   * @param resultSetType type
   */
  public void setResultSetType(int resultSetType) {
    m_resultSetType = resultSetType;
  }
  /**
   * Set ResultSet Concurrency
   *
   * @param resultSetConcurrency concurrency
   */
  public void setResultSetConcurrency(int resultSetConcurrency) {
    m_resultSetConcurrency = resultSetConcurrency;
  }

  /** @return transaction name */
  public String getTrxName() {
    return m_trxName;
  }

  /**
   * Set transaction name
   *
   * @param trxName
   */
  public void setTrxName(String trxName) {
    m_trxName = trxName;
  }
} //	CStatementVO
