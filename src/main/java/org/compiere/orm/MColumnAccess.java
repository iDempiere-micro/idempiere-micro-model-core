package org.compiere.orm;

import kotliquery.Row;

/**
 * Column Access Model
 *
 * @author Jorg Janke
 * @version $Id: MColumnAccess.java,v 1.3 2006/07/30 00:54:54 jjanke Exp $
 */
public class MColumnAccess extends X_AD_Column_Access {
    /**
     *
     */
    private static final long serialVersionUID = -2362624234744824977L;
    /**
     * TableName
     */
    private String m_tableName;
    /**
     * ColumnName
     */
    private String m_columnName;

    /**
     * Persistency Constructor
     *
     * @param ctx     context
     * @param ignored ignored
     * @param trxName transaction
     */
    public MColumnAccess(int ignored) {
        super(0);
        if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
    } //	MColumnAccess

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MColumnAccess(Row row) {
        super(row);
    } //	MColumnAccess

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("MColumnAccess[");
        sb.append("AD_Role_ID=")
                .append(getRoleId())
                .append(",AD_Table_ID=")
                .append(getColumnTableId())
                .append(",AD_Column_ID=")
                .append(getColumnId())
                .append(",Exclude=")
                .append(isExclude());
        sb.append("]");
        return sb.toString();
    } //	toString

} //	MColumnAccess
