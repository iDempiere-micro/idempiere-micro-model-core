package org.compiere.orm;

import java.sql.ResultSet;
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
    public MViewColumn(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    /**
     * Parent constructor
     *
     * @param parent parent
     */
    public MViewColumn(MViewComponent parent) {
        this(parent.getCtx(), 0);
        setClientOrg(parent);
        setAD_ViewComponent_ID(parent.getAD_ViewComponent_ID());
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
