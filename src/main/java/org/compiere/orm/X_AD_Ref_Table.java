package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_AD_Ref_Table;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Ref_Table
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Ref_Table extends PO implements I_AD_Ref_Table, I_Persistent {

    /**
     * EntityType AD_Reference_ID=389
     */
    public static final int ENTITYTYPE_AD_Reference_ID = 389;
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Ref_Table(Properties ctx, int AD_Ref_Table_ID, String trxName) {
        super(ctx, AD_Ref_Table_ID, trxName);
        /**
         * if (AD_Ref_Table_ID == 0) { setAD_Display (0); setAD_Key (0); setReferenceId (0);
         * setAD_Table_ID (0); setEntityType (null); // @SQL=select
         * get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual setIsValueDisplayed (false); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_Ref_Table(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
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
        StringBuffer sb = new StringBuffer("X_AD_Ref_Table[").append(getId()).append("]");
        return sb.toString();
    }

    public org.compiere.model.I_AD_Column getAD_Disp() throws RuntimeException {
        return (org.compiere.model.I_AD_Column)
                MTable.get(getCtx(), org.compiere.model.I_AD_Column.Table_Name)
                        .getPO(getAD_Display(), get_TrxName());
    }

    /**
     * Get Display column.
     *
     * @return Column that will display
     */
    public int getAD_Display() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_Display);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Display column.
     *
     * @param AD_Display Column that will display
     */
    public void setAD_Display(int AD_Display) {
        set_Value(COLUMNNAME_AD_Display, Integer.valueOf(AD_Display));
    }

    /**
     * Get Info Window.
     *
     * @return Info and search/select Window
     */
    public int getAD_InfoWindow_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_InfoWindow_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Info Window.
     *
     * @param AD_InfoWindow_ID Info and search/select Window
     */
    public void setAD_InfoWindow_ID(int AD_InfoWindow_ID) {
        if (AD_InfoWindow_ID < 1) set_Value(COLUMNNAME_AD_InfoWindow_ID, null);
        else set_Value(COLUMNNAME_AD_InfoWindow_ID, Integer.valueOf(AD_InfoWindow_ID));
    }

    public org.compiere.model.I_AD_Column getAD_() throws RuntimeException {
        return (org.compiere.model.I_AD_Column)
                MTable.get(getCtx(), org.compiere.model.I_AD_Column.Table_Name)
                        .getPO(getAD_Key(), get_TrxName());
    }

    /**
     * Get Key column.
     *
     * @return Unique identifier of a record
     */
    public int getAD_Key() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_Key);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Key column.
     *
     * @param AD_Key Unique identifier of a record
     */
    public void setAD_Key(int AD_Key) {
        set_Value(COLUMNNAME_AD_Key, Integer.valueOf(AD_Key));
    }

    public org.compiere.model.I_AD_Reference getAD_Reference() throws RuntimeException {
        return (org.compiere.model.I_AD_Reference)
                MTable.get(getCtx(), org.compiere.model.I_AD_Reference.Table_Name)
                        .getPO(getAD_Reference_ID(), get_TrxName());
    }

    /**
     * Get Reference.
     *
     * @return System Reference and Validation
     */
    public int getAD_Reference_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_Reference_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Reference.
     *
     * @param AD_Reference_ID System Reference and Validation
     */
    public void setAD_Reference_ID(int AD_Reference_ID) {
        if (AD_Reference_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Reference_ID, null);
        else set_ValueNoCheck(COLUMNNAME_AD_Reference_ID, Integer.valueOf(AD_Reference_ID));
    }

    /**
     * Get Record ID/ColumnName
     *
     * @return ID/ColumnName pair
     */
    public KeyNamePair getKeyNamePair() {
        return new KeyNamePair(getId(), String.valueOf(getAD_Reference_ID()));
    }

    /**
     * Get AD_Ref_Table_UU.
     *
     * @return AD_Ref_Table_UU
     */
    public String getAD_Ref_Table_UU() {
        return (String) get_Value(COLUMNNAME_AD_Ref_Table_UU);
    }

    /**
     * Set AD_Ref_Table_UU.
     *
     * @param AD_Ref_Table_UU AD_Ref_Table_UU
     */
    public void setAD_Ref_Table_UU(String AD_Ref_Table_UU) {
        set_Value(COLUMNNAME_AD_Ref_Table_UU, AD_Ref_Table_UU);
    }

    public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException {
        return (org.compiere.model.I_AD_Table)
                MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
                        .getPO(getAD_Table_ID(), get_TrxName());
    }

    /**
     * Get Table.
     *
     * @return Database Table information
     */
    public int getAD_Table_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Table.
     *
     * @param AD_Table_ID Database Table information
     */
    public void setAD_Table_ID(int AD_Table_ID) {
        if (AD_Table_ID < 1) set_Value(COLUMNNAME_AD_Table_ID, null);
        else set_Value(COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
    }

    /**
     * Get Window.
     *
     * @return Data entry or display window
     */
    public int getAD_Window_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_Window_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Window.
     *
     * @param AD_Window_ID Data entry or display window
     */
    public void setAD_Window_ID(int AD_Window_ID) {
        if (AD_Window_ID < 1) set_Value(COLUMNNAME_AD_Window_ID, null);
        else set_Value(COLUMNNAME_AD_Window_ID, Integer.valueOf(AD_Window_ID));
    }

    /**
     * Get Entity Type.
     *
     * @return Dictionary Entity Type; Determines ownership and synchronization
     */
    public String getEntityType() {
        return (String) get_Value(COLUMNNAME_EntityType);
    }

    /**
     * Set Entity Type.
     *
     * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
     */
    public void setEntityType(String EntityType) {

        set_Value(COLUMNNAME_EntityType, EntityType);
    }

    /**
     * Set Display Value.
     *
     * @param IsValueDisplayed Displays Value column with the Display column
     */
    public void setIsValueDisplayed(boolean IsValueDisplayed) {
        set_Value(COLUMNNAME_IsValueDisplayed, Boolean.valueOf(IsValueDisplayed));
    }

    /**
     * Get Display Value.
     *
     * @return Displays Value column with the Display column
     */
    public boolean isValueDisplayed() {
        Object oo = get_Value(COLUMNNAME_IsValueDisplayed);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Sql ORDER BY.
     *
     * @return Fully qualified ORDER BY clause
     */
    public String getOrderByClause() {
        return (String) get_Value(COLUMNNAME_OrderByClause);
    }

    /**
     * Set Sql ORDER BY.
     *
     * @param OrderByClause Fully qualified ORDER BY clause
     */
    public void setOrderByClause(String OrderByClause) {
        set_Value(COLUMNNAME_OrderByClause, OrderByClause);
    }

    /**
     * Get Sql WHERE.
     *
     * @return Fully qualified SQL WHERE clause
     */
    public String getWhereClause() {
        return (String) get_Value(COLUMNNAME_WhereClause);
    }

    /**
     * Set Sql WHERE.
     *
     * @param WhereClause Fully qualified SQL WHERE clause
     */
    public void setWhereClause(String WhereClause) {
        set_Value(COLUMNNAME_WhereClause, WhereClause);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
