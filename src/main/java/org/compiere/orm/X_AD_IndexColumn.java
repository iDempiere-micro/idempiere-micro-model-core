package org.compiere.orm;

import org.compiere.model.I_AD_IndexColumn;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_IndexColumn
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_IndexColumn extends PO implements I_AD_IndexColumn {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_IndexColumn(Properties ctx, int AD_IndexColumn_ID) {
        super(ctx, AD_IndexColumn_ID);
        /**
         * if (AD_IndexColumn_ID == 0) { setAD_IndexColumn_ID (0); setTableIndexId (0);
         * setEntityType (null); // @SQL=select get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_IndexColumn(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Column_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Column.
     *
     * @param AD_Column_ID Column in the table
     */
    public void setColumnId(int AD_Column_ID) {
        if (AD_Column_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Column_ID, null);
        else set_ValueNoCheck(COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
    }

    /**
     * Set Table Index.
     *
     * @param AD_TableIndex_ID Table Index
     */
    public void setTableIndexId(int AD_TableIndex_ID) {
        if (AD_TableIndex_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_TableIndex_ID, null);
        else set_ValueNoCheck(COLUMNNAME_AD_TableIndex_ID, Integer.valueOf(AD_TableIndex_ID));
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
        set_Value(COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
