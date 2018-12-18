package org.compiere.orm;

import kotliquery.Row;
import org.compiere.util.Msg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.close;
import static software.hsharp.core.util.DBKt.prepareStatement;

/**
 * @author Jorg Janke
 * @version $Id: MTableAccess.java,v 1.3 2006/07/30 00:58:38 jjanke Exp $
 */
public class MTableAccess extends X_AD_Table_Access {
  /** */
  private static final long serialVersionUID = -3747261579266442904L;
  /** TableName */
  private String m_tableName;

  /**
   * Persistency Constructor
   *
   * @param ctx context
   * @param ignored ignored
   * @param trxName transaction
   */
  public MTableAccess(Properties ctx, int ignored, String trxName) {
    super(ctx, 0, trxName);
    if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
  } //	MTableAccess

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MTableAccess(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } //	MTableAccess
  public MTableAccess(Properties ctx, Row row) {
    super(ctx, row);
  } //	MTableAccess

  /**
   * String Representation
   *
   * @return info
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("MTableAccess[");
    sb.append("AD_Role_ID=")
        .append(getAD_Role_ID())
        .append(",AD_Table_ID=")
        .append(getAD_Table_ID())
        .append(",Exclude=")
        .append(isExclude())
        .append(",Type=")
        .append(getAccessTypeRule());
    if (X_AD_Table_Access.ACCESSTYPERULE_Accessing.equals(getAccessTypeRule()))
      sb.append("-ReadOnly=").append(isReadOnly());
    else if (X_AD_Table_Access.ACCESSTYPERULE_Exporting.equals(getAccessTypeRule()))
      sb.append("-CanExport=").append(!isExclude());
    else if (X_AD_Table_Access.ACCESSTYPERULE_Reporting.equals(getAccessTypeRule()))
      sb.append("-CanReport=").append(!isExclude());
    sb.append("]");
    return sb.toString();
  } //	toString

  /**
   * Extended String Representation
   *
   * @param ctx context
   * @return extended info
   */
  public String toStringX(Properties ctx) {
    String in = Msg.getMsg(ctx, "Include");
    String ex = Msg.getMsg(ctx, "Exclude");
    StringBuilder sb = new StringBuilder();
    sb.append(Msg.translate(ctx, "AD_Table_ID")).append("=").append(getTableName(ctx));
    if (X_AD_Table_Access.ACCESSTYPERULE_Accessing.equals(getAccessTypeRule()))
      sb.append(" - ").append(Msg.translate(ctx, "IsReadOnly")).append("=").append(isReadOnly());
    else if (X_AD_Table_Access.ACCESSTYPERULE_Exporting.equals(getAccessTypeRule()))
      sb.append(" - ").append(Msg.translate(ctx, "CanExport")).append("=").append(!isExclude());
    else if (X_AD_Table_Access.ACCESSTYPERULE_Reporting.equals(getAccessTypeRule()))
      sb.append(" - ").append(Msg.translate(ctx, "CanReport")).append("=").append(!isExclude());
    sb.append(" - ").append(isExclude() ? ex : in);
    return sb.toString();
  } //	toStringX

  /**
   * Get Table Name
   *
   * @param ctx context
   * @return table name
   */
  public String getTableName(Properties ctx) {
    if (m_tableName == null) {
      String sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
        pstmt = prepareStatement(sql, null);
        pstmt.setInt(1, getAD_Table_ID());
        rs = pstmt.executeQuery();
        if (rs.next()) m_tableName = rs.getString(1);
      } catch (Exception e) {
        log.log(Level.SEVERE, "getTableName", e);
      } finally {
        close(rs, pstmt);
        rs = null;
        pstmt = null;
      }
      //	Get Clear Text
      String realName = Msg.translate(ctx, m_tableName + "_ID");
      if (!realName.equals(m_tableName + "_ID")) m_tableName = realName;
    }
    return m_tableName;
  } //	getTableName
} //	MTableAccess
