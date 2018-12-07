package org.compiere.orm;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import org.compiere.model.I_AD_TableIndex;
import org.compiere.util.Msg;
import org.idempiere.common.exceptions.AdempiereException;
import org.idempiere.orm.PO;

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
   * Load constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName trx name
   */
  public MTableIndex(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
    m_ddl = createDDL();
  }

  /**
   * Parent constructor
   *
   * @param parent parent
   * @param name name
   */
  public MTableIndex(MTable parent, String name) {
    this(parent.getCtx(), 0, parent.get_TrxName());
    setClientOrg(parent);
    setAD_Table_ID(parent.getAD_Table_ID());
    setEntityType(parent.getEntityType());
    setName(name);
  }

  /**
   * Get active indexes from table
   *
   * @param table table
   * @return array of table index
   */
  public static MTableIndex[] get(MTable table) {
    Query query =
        new Query(
            table.getCtx(),
            I_AD_TableIndex.Table_Name,
            I_AD_TableIndex.COLUMNNAME_AD_Table_ID + "=?",
            table.get_TrxName());
    query.setParameters(table.getAD_Table_ID());
    query.setOnlyActiveRecords(true);
    List<MTableIndex> list = query.list();

    MTableIndex[] retValue = new MTableIndex[list.size()];
    list.toArray(retValue);
    return retValue;
  }

  /**
   * Get table indexes with where clause
   *
   * @param ctx context
   * @param whereClause where clause
   * @return array of table index
   */
  public static List<MTableIndex> getTableIndexesByQuery(Properties ctx, String whereClause) {
    Query query = new Query(ctx, I_AD_TableIndex.Table_Name, whereClause, null);
    List<MTableIndex> list = query.list();
    return list;
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
            get_TrxName());
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
   * Get SQL index create DDL
   *
   * @return SQL DDL
   */
  public String getDDL() {
    if (m_ddl == null) m_ddl = createDDL();
    return m_ddl;
  }

  /**
   * Get SQL index create DDL
   *
   * @return SQL DDL
   */
  public String getDropDDL() {
    String sql = null;
    if (isCreateConstraint())
      sql =
          "ALTER TABLE "
              + getAD_Table().getTableName()
              + " DROP CONSTRAINT "
              + getName()
              + " CASCADE";
    else sql = "DROP INDEX " + getName();
    return sql;
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
