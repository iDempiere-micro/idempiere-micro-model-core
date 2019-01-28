package org.compiere.orm;

import java.util.List;
import java.util.Properties;

public class MViewComponent extends X_AD_ViewComponent {

  /** */
  private static final long serialVersionUID = -8915166706061086737L;
  /** Columns */
  private MViewColumn[] m_columns = null;

  /**
   * Standard constructor
   *
   * @param ctx context
   * @param AD_ViewComponent_ID view component
   * @param trxName trx name
   */
  public MViewComponent(Properties ctx, int AD_ViewComponent_ID, String trxName) {
    super(ctx, AD_ViewComponent_ID, trxName);
  }

  /**
   * Parent constructor
   *
   * @param parent parent
   */
  public MViewComponent(MTable parent) {
    this(parent.getCtx(), 0, null);
    setClientOrg(parent);
    setAD_Table_ID(parent.getAD_Table_ID());
  }

    /**
   * Get columns
   *
   * @param reload reload data
   * @return array of view column
   */
  public MViewColumn[] getColumns(boolean reload) {
    if (m_columns != null && !reload) return m_columns;

    Query query =
        new Query(
            getCtx(),
            MViewColumn.Table_Name,
            MViewColumn.COLUMNNAME_AD_ViewComponent_ID + "=?",
            null);
    query.setParameters(getAD_ViewComponent_ID());
    query.setOrderBy("SeqNo, AD_ViewColumn_ID");
    List<MViewColumn> list = query.list();

    m_columns = new MViewColumn[list.size()];
    list.toArray(m_columns);
    return m_columns;
  }

    /**
   * String representation
   *
   * @return info
   */
  @Override
  public String toString() {
    StringBuffer sb =
        new StringBuffer("MViewComponent[").append(getId()).append("-").append(getName());
    sb.append("]");
    return sb.toString();
  }
}
