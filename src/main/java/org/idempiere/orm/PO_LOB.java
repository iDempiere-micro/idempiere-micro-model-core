package org.idempiere.orm;

import static software.hsharp.core.util.DBKt.*;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import org.compiere.util.DisplayType;
import org.idempiere.common.util.CLogger;

/**
 * Persistent Object LOB. Allows to store LOB remotely Currently Oracle specific!
 *
 * @author Jorg Janke
 * @version $Id: PO_LOB.java,v 1.2 2006/07/30 00:58:04 jjanke Exp $
 */
public class PO_LOB implements Serializable {
  /** */
  private static final long serialVersionUID = -325477490976139224L;
  /** Logger */
  protected CLogger log = CLogger.getCLogger(getClass());
  /** Table Name */
  private String m_tableName;
  /** Column Name */
  private String m_columnName;
  /** Where Clause */
  private String m_whereClause;
  /** Display Type */
  private int m_displayType;
  /** Data */
  private Object m_value;

  /**
   * Constructor
   *
   * @param tableName table name
   * @param columnName column name
   * @param whereClause where
   * @param displayType display type
   * @param value value
   */
  public PO_LOB(
      String tableName, String columnName, String whereClause, int displayType, Object value) {
    m_tableName = tableName;
    m_columnName = columnName;
    m_whereClause = whereClause;
    m_displayType = displayType;
    m_value = value;
  } //	PO_LOB

  /**
   * Save LOB
   *
   * @param whereClause clause
   * @param trxName trx name
   * @return true if saved
   */
  public boolean save(String whereClause, String trxName) {
    m_whereClause = whereClause;
    return save(trxName);
  } //	save

  /**
   * Save LOB. see also org.compiere.session.ServerBean#updateLOB
   *
   * @param trxName trx name
   * @return true if saved
   */
  public boolean save(String trxName) {
    if (m_value == null
        || (!(m_value instanceof String || m_value instanceof byte[]))
        || (m_value instanceof String && m_value.toString().length() == 0)
        || (m_value instanceof byte[] && ((byte[]) m_value).length == 0)) {
      StringBuffer sql =
          new StringBuffer("UPDATE ")
              .append(m_tableName)
              .append(" SET ")
              .append(m_columnName)
              .append("=null WHERE ")
              .append(m_whereClause);
      int no = executeUpdate(sql.toString(), trxName);
      if (log.isLoggable(Level.FINE))
        log.fine("save [" + trxName + "] #" + no + " - no data - set to null - " + m_value);
      if (no == 0) log.warning("[" + trxName + "] - not updated - " + sql);
      return true;
    }

    StringBuffer sql =
        new StringBuffer("UPDATE ")
            .append(m_tableName)
            .append(" SET ")
            .append(m_columnName)
            .append("=? WHERE ")
            .append(m_whereClause);
    //

    if (log.isLoggable(Level.FINE)) log.fine("[" + trxName + "] - Local - " + m_value);
    //	Connection
    Connection con = null;
    //	Create Connection
    if (con == null) con = createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
    if (con == null) {
      log.log(Level.SEVERE, "Could not get Connection");
      return false;
    }

    PreparedStatement pstmt = null;
    boolean success = true;
    try {
      pstmt = con.prepareStatement(sql.toString());
      if (m_displayType == DisplayType.TextLong) pstmt.setString(1, (String) m_value);
      else pstmt.setBytes(1, (byte[]) m_value);
      int no = pstmt.executeUpdate();
      if (no != 1) {
        log.warning("[" + trxName + "] - Not updated #" + no + " - " + sql);
        success = false;
      }
    } catch (Throwable e) {
      log.log(Level.SEVERE, "[" + trxName + "] - " + sql, e);
      success = false;
    } finally {
      close(pstmt);
      pstmt = null;
    }

    //	Error - roll back
    if (!success) {
      log.severe("[" + trxName + "] - rollback");
      throw new Error("Rollback");
    }

    return success;
  } //	save

  /**
   * String Representation
   *
   * @return info
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("PO_LOB[");
    sb.append(m_tableName)
        .append(".")
        .append(m_columnName)
        .append(",DisplayType=")
        .append(m_displayType)
        .append("]");
    return sb.toString();
  } //	toString
} //	PO_LOB
