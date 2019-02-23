package org.compiere.orm;

import org.idempiere.orm.PO;

import java.util.List;
import java.util.Properties;

public class MTableIndex extends X_AD_TableIndex {
    /**
     *
     */
    private static final long serialVersionUID = 5312095272014146977L;
    /**
     * Lines
     */
    private MIndexColumn[] m_columns = null;

    /**
     * Standard constructor
     *
     * @param ctx              context
     * @param AD_TableIndex_ID table index
     * @param trxName          trx name
     */
    public MTableIndex(Properties ctx, int AD_TableIndex_ID) {
        super(ctx, AD_TableIndex_ID);
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
                        MIndexColumn.COLUMNNAME_AD_TableIndex_ID + "=?"
                );
        query.setParameters(getTableIndexId());
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
    public String getDbTableName() {
        int AD_Table_ID = getIndexTableId();
        return MTable.getDbTableName(getCtx(), AD_Table_ID);
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
                .append(getIndexTableId())
                .append("]");
        return sb.toString();
    }
}
