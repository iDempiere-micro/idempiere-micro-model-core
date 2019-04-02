package org.compiere.orm;

import kotliquery.Row;

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
     */
    public MViewColumn(int AD_ViewColumn_ID) {
        super(AD_ViewColumn_ID);
    }

    /**
     * Load constructor
     *
     * @param ctx context
     */
    public MViewColumn(Row row) {
        super(row);
    }

    /**
     * Parent constructor
     *
     * @param parent parent
     */
    public MViewColumn(MViewComponent parent) {
        this(0);
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
        return "MViewColumn[" + getId() + "-" + getColumnName() + "]";
    }
}
