package org.compiere.orm;

import org.idempiere.orm.PO;

public class MTableIndex extends X_AD_TableIndex {
    /**
     *
     */
    private static final long serialVersionUID = 5312095272014146977L;

    /**
     * Standard constructor
     *
     * @param ctx              context
     * @param AD_TableIndex_ID table index
     * @param trxName          trx name
     */
    public MTableIndex(int AD_TableIndex_ID) {
        super(AD_TableIndex_ID);
        if (AD_TableIndex_ID == 0) {
            setEntityType(PO.ENTITYTYPE_UserMaintained);
            setIsUnique(false);
            setIsCreateConstraint(false);
        }
    }

    /**
     * Get table name
     *
     * @return table name
     */
    public String getDbTableName() {
        int AD_Table_ID = getIndexTableId();
        return MTable.getDbTableName(AD_Table_ID);
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
