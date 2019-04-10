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
     * @param ignored ignored
     */
    public MColumnAccess(int ignored) {
        super(0);
        if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
    } //	MColumnAccess

    /**
     * Load Constructor
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
        return "MColumnAccess[" + "AD_Role_ID=" +
                getRoleId() +
                ",AD_Table_ID=" +
                getColumnTableId() +
                ",AD_Column_ID=" +
                getColumnId() +
                ",Exclude=" +
                isExclude() +
                "]";
    } //	toString

} //	MColumnAccess
