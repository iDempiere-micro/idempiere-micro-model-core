package org.compiere.orm;

import kotliquery.Row;

import java.util.List;
import java.util.Properties;

public class MViewComponent extends X_AD_ViewComponent {

    /**
     *
     */
    private static final long serialVersionUID = -8915166706061086737L;
    /**
     * Columns
     */
    private MViewColumn[] m_columns = null;

    /**
     * Standard constructor
     *
     * @param ctx                 context
     * @param AD_ViewComponent_ID view component
     */
    public MViewComponent(Properties ctx, int AD_ViewComponent_ID) {
        super(ctx, AD_ViewComponent_ID);
    }

    public MViewComponent(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * Parent constructor
     *
     * @param parent parent
     */
    public MViewComponent(MTable parent) {
        this(parent.getCtx(), 0);
        setClientOrg(parent);
        setViewTableId(parent.getTableTableId());
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
                        MViewColumn.COLUMNNAME_AD_ViewComponent_ID + "=?"
                );
        query.setParameters(getViewComponentId());
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
        StringBuilder sb =
                new StringBuilder("MViewComponent[").append(getId()).append("-").append(getName());
        sb.append("]");
        return sb.toString();
    }
}
