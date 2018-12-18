package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;

public class MIndexColumn extends X_AD_IndexColumn {

  /** */
  private static final long serialVersionUID = -7588207529142215755L;

  /**
   * Standard constructor
   *
   * @param ctx context
   * @param AD_IndexColumn_ID index column
   * @param trxName trx name
   */
  public MIndexColumn(Properties ctx, int AD_IndexColumn_ID, String trxName) {
    super(ctx, AD_IndexColumn_ID, trxName);
  }

  /**
   * Load constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName trx name
   */
  public MIndexColumn(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * Parent constructor
   *
   * @param parent parent
   * @param column column
   * @param seqNo sequence no
   */
  public MIndexColumn(MTableIndex parent, MColumn column, int seqNo) {
    this(parent.getCtx(), 0, null);
    setClientOrg(parent);
    setAD_TableIndex_ID(parent.getAD_TableIndex_ID());
    setAD_Column_ID(column.getColumnId());
    setSeqNo(seqNo);
  }

  /**
   * Get column name
   *
   * @return column name
   */
  public String getColumnName() {
    String sql = getColumnSQL(); // Function Index
    if (sql != null && sql.length() > 0) return sql;
    int AD_Column_ID = getAD_Column_ID();
    return MColumn.getColumnName(getCtx(), AD_Column_ID, null);
  }

  /**
   * String representation
   *
   * @return info
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("MIndexColumn[");
    sb.append(getId()).append("-").append(getAD_Column_ID()).append("]");
    return sb.toString();
  }
}
