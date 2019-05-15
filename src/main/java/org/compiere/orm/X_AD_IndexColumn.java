package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.IndexColumn;

/**
 * Generated Model for AD_IndexColumn
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_IndexColumn extends PO implements IndexColumn {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_IndexColumn(int AD_IndexColumn_ID) {
        super(AD_IndexColumn_ID);
        /**
         * if (AD_IndexColumn_ID == 0) { setAD_IndexColumn_ID (0); setTableIndexId (0);
         * setEntityType (null); // @SQL=select get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_IndexColumn(Row row) {
        super(row);
    }

    /**
     * AccessLevel
     *
     * @return 4 - System
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_AD_IndexColumn[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Column.
     *
     * @return Column in the table
     */
    public int getColumnId() {
        Integer ii = getValue(COLUMNNAME_AD_Column_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Column.
     *
     * @param AD_Column_ID Column in the table
     */
    public void setColumnId(int AD_Column_ID) {
        if (AD_Column_ID < 1) setValueNoCheck(COLUMNNAME_AD_Column_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
    }

    /**
     * Set Table Index.
     *
     * @param AD_TableIndex_ID Table Index
     */
    public void setTableIndexId(int AD_TableIndex_ID) {
        if (AD_TableIndex_ID < 1) setValueNoCheck(COLUMNNAME_AD_TableIndex_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_TableIndex_ID, Integer.valueOf(AD_TableIndex_ID));
    }

    /**
     * Get Column SQL.
     *
     * @return Virtual Column (r/o)
     */
    public String getColumnSQL() {
        return (String) getValue(COLUMNNAME_ColumnSQL);
    }

    /**
     * Set Sequence.
     *
     * @param SeqNo Method of ordering records; lowest number comes first
     */
    public void setSeqNo(int SeqNo) {
        setValue(COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
