package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.ViewComponent;

public class MViewColumn extends X_AD_ViewColumn {

    /**
     *
     */
    private static final long serialVersionUID = 1497519704377959238L;

    /**
     * Standard constructor
     *
     * @param AD_ViewColumn_ID view column
     */
    public MViewColumn(int AD_ViewColumn_ID) {
        super(AD_ViewColumn_ID);
    }

    /**
     * Load constructor
     *
     */
    public MViewColumn(Row row) {
        super(row);
    }

    /**
     * Parent constructor
     *
     * @param parent parent
     */
    public MViewColumn(ViewComponent parent) {
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
