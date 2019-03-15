package org.compiere.orm;

import kotliquery.Row;

import java.util.Properties;

public class MIndexColumn extends X_AD_IndexColumn {

    /**
     *
     */
    private static final long serialVersionUID = -7588207529142215755L;

    /**
     * Standard constructor
     *
     * @param ctx               context
     * @param AD_IndexColumn_ID index column
     * @param trxName           trx name
     */
    public MIndexColumn(Properties ctx, int AD_IndexColumn_ID) {
        super(ctx, AD_IndexColumn_ID);
    }

    /**
     * Load constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName trx name
     */
    public MIndexColumn(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * Parent constructor
     *
     * @param parent parent
     * @param column column
     * @param seqNo  sequence no
     */
    public MIndexColumn(MTableIndex parent, MColumn column, int seqNo) {
        this(parent.getCtx(), 0);
        setClientOrg(parent);
        setTableIndexId(parent.getTableIndexId());
        setColumnId(column.getColumnId());
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
        int AD_Column_ID = getColumnId();
        return MColumn.getColumnName(getCtx(), AD_Column_ID);
    }

    /**
     * String representation
     *
     * @return info
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MIndexColumn[");
        sb.append(getId()).append("-").append(getColumnId()).append("]");
        return sb.toString();
    }
}
