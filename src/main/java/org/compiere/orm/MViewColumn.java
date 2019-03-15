package org.compiere.orm;

import kotliquery.Row;

import java.util.Properties;

public class MViewColumn extends X_AD_ViewColumn {

    /**
     *
     */
    private static final long serialVersionUID = 1497519704377959238L;

    /**
     * Standard constructor
     *
     * @param ctx              context
     * @param AD_ViewColumn_ID view column
     * @param trxName          trx name
     */
    public MViewColumn(Properties ctx, int AD_ViewColumn_ID) {
        super(ctx, AD_ViewColumn_ID);
    }

    /**
     * Load constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName trx name
     */
    public MViewColumn(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * Parent constructor
     *
     * @param parent parent
     */
    public MViewColumn(MViewComponent parent) {
        this(parent.getCtx(), 0);
        setClientOrg(parent);
        setViewComponentId(parent.getViewComponentId());
    }

    /**
     * String representation
     *
     * @return info
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MViewColumn[");
        sb.append(getId()).append("-").append(getColumnName()).append("]");
        return sb.toString();
    }
}
