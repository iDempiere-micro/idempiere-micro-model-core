package org.compiere.orm;

import org.compiere.util.Msg;
import org.idempiere.common.exceptions.AdempiereException;
import org.idempiere.orm.PO;

import java.util.List;
import java.util.Properties;

public class MTableIndex extends X_AD_TableIndex {
  /** */
  private static final long serialVersionUID = 5312095272014146977L;
  /** Lines */
  private MIndexColumn[] m_columns = null;
  /** Index Create DDL */
  private String m_ddl = null;

  /**
   * Standard constructor
   *
   * @param ctx context
   * @param AD_TableIndex_ID table index
   * @param trxName trx name
   */
  public MTableIndex(Properties ctx, int AD_TableIndex_ID, String trxName) {
    super(ctx, AD_TableIndex_ID, trxName);
    if (AD_TableIndex_ID == 0) {
      setEntityType(PO.ENTITYTYPE_UserMaintained);
      setIsUnique(false);
      setIsCreateConstraint(false);
    }
  }

    /**
   * Get index columns
   *
   * @param reload reload data
   * @return array of index column
   */
  public MIndexColumn[] getColumns(boolean reload) {
    if (m_columns != null && !reload) return m_columns;

    Query query =
        new Query(
            getCtx(),
            MIndexColumn.Table_Name,
            MIndexColumn.COLUMNNAME_AD_TableIndex_ID + "=?",
            null);
    query.setParameters(getAD_TableIndex_ID());
    query.setOrderBy(MIndexColumn.COLUMNNAME_SeqNo);
    List<MIndexColumn> list = query.list();

    m_columns = new MIndexColumn[list.size()];
    list.toArray(m_columns);
    return m_columns;
  }

  /**
   * Get table name
   *
   * @return table name
   */
  public String getTableName() {
    int AD_Table_ID = getAD_Table_ID();
    return MTable.getTableName(getCtx(), AD_Table_ID);
  }

  /**
   * Get SQL DDL
   *
   * @return DDL
   */
  private String createDDL() {
    StringBuilder sql = null;
    if (!isCreateConstraint()) {
      sql = new StringBuilder("CREATE ");
      if (isUnique()) sql.append("UNIQUE ");
      sql.append("INDEX ").append(getName()).append(" ON ").append(getTableName()).append(" (");
      //
      getColumns(false);
      for (int i = 0; i < m_columns.length; i++) {
        MIndexColumn ic = m_columns[i];
        if (i > 0) sql.append(",");
        sql.append(ic.getColumnName());
      }

      sql.append(")");
    } else if (isUnique()) {
      sql =
          new StringBuilder("ALTER TABLE ")
              .append(getTableName())
              .append(" ADD CONSTRAINT ")
              .append(getName());
      if (isKey()) sql.append(" PRIMARY KEY (");
      else sql.append(" UNIQUE (");
      getColumns(false);
      for (int i = 0; i < m_columns.length; i++) {
        MIndexColumn ic = m_columns[i];
        if (i > 0) sql.append(",");
        sql.append(ic.getColumnName());
      }

      sql.append(")");
    } else {
      String errMsg =
          Msg.getMsg(
              getCtx(), "NeitherTableIndexNorUniqueConstraint", new Object[] {getTableName()});
      log.severe(errMsg);
      throw new AdempiereException(errMsg);
    }

    return sql.toString();
  }

    /**
   * String representation
   *
   * @return info
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("MTableIndex[");
    sb.append(getId())
        .append("-")
        .append(getName())
        .append(",AD_Table_ID=")
        .append(getAD_Table_ID())
        .append("]");
    return sb.toString();
  }
}
