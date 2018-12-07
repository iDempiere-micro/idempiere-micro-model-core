package org.compiere.orm;

import static software.hsharp.core.util.DBKt.*;

import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.I_AD_Element;
import org.compiere.util.Msg;
import org.idempiere.common.exceptions.DBException;
import org.idempiere.common.util.Env;

/**
 * System Element Model
 *
 * @author Jorg Janke
 * @version $Id: M_Element.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $ FR: [ 2214883 ] Remove SQL
 * code and Replace for Query - red1, teo_sarca
 */
public class M_Element extends X_AD_Element {

    /**
     *
     */
    private static final long serialVersionUID = -6644398794862560030L;

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ctx           context
     * @param AD_Element_ID element
     * @param trxName       transaction
     */
    public M_Element(Properties ctx, int AD_Element_ID, String trxName) {
        super(ctx, AD_Element_ID, trxName);
        if (AD_Element_ID == 0) {
            //	setColumnName (null);
            //	setEntityType (null);	// U
            //	setName (null);
            //	setPrintName (null);
        }
    } //	M_Element

    public M_Element(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public M_Element(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    } //	M_Element

    /**
     * Minimum Constructor
     *
     * @param ctx        context
     * @param columnName column
     * @param EntityType entity type
     * @param trxName    trx
     */
    public M_Element(Properties ctx, String columnName, String EntityType, String trxName) {
        super(ctx, 0, trxName);
        setColumnName(columnName);
        setName(columnName);
        setPrintName(columnName);
        //
        setEntityType(EntityType); // U
    } //	M_Element

    /**
     * Get case sensitive Column Name
     *
     * @param columnName case insensitive column name
     * @return case sensitive column name
     */
    public static String getColumnName(String columnName) {
        return getColumnName(columnName, null);
    }

    /**
     * Get case sensitive Column Name
     *
     * @param columnName case insensitive column name
     * @param trxName    optional transaction name
     * @return case sensitive column name
     */
    public static String getColumnName(String columnName, String trxName) {
        if (columnName == null || columnName.length() == 0) return columnName;
        M_Element element = get(Env.getCtx(), columnName, trxName);
        if (element == null) return columnName;
        return element.getColumnName();
    } //	getColumnName

    /**
     * Get Element
     *
     * @param ctx        context
     * @param columnName case insensitive column name
     * @return case sensitive column name
     */
    public static M_Element get(Properties ctx, String columnName) {
        return get(ctx, columnName, null);
    }

    /**
     * Get Element
     *
     * @param ctx        context
     * @param columnName case insensitive column name
     * @param trxName    optional transaction name
     * @return case sensitive column name
     */
    public static M_Element get(Properties ctx, String columnName, String trxName) {
        if (columnName == null || columnName.length() == 0) return null;
        //
        // TODO: caching if trxName == null
        final String whereClause = "UPPER(ColumnName)=?";
        M_Element retValue =
                new Query(ctx, I_AD_Element.Table_Name, whereClause, trxName)
                        .setParameters(columnName.toUpperCase())
                        .firstOnly();
        return retValue;
    } //	get

    /**
     * Get Element
     *
     * @param ctx        context
     * @param columnName case insensitive column name
     * @param trxName    trx
     * @return case sensitive column name
     */
    public static M_Element getOfColumn(Properties ctx, int AD_Column_ID, String trxName) {
        if (AD_Column_ID == 0) return null;
        final String whereClause =
                "EXISTS (SELECT 1 FROM AD_Column c "
                        + "WHERE c.AD_Element_ID=AD_Element.AD_Element_ID AND c.AD_Column_ID=?)";
        M_Element retValue =
                new Query(ctx, I_AD_Element.Table_Name, whereClause, trxName)
                        .setParameters(AD_Column_ID)
                        .firstOnly();
        return retValue;
    } //	get

    /**
     * Get Element
     *
     * @param ctx        context
     * @param columnName case insentitive column name
     * @return case sensitive column name
     */
    public static M_Element getOfColumn(Properties ctx, int AD_Column_ID) {
        return getOfColumn(ctx, AD_Column_ID, null);
    } //	get

    /* (non-Javadoc)
     * @see PO#beforeSave(boolean)
     */
    @Override
    protected boolean beforeSave(boolean newRecord) {
        // Column AD_Element.ColumnName should be unique - teo_sarca [ 1613107 ]
        if (newRecord || is_ValueChanged(I_AD_Element.COLUMNNAME_ColumnName)) {
            String columnName = getColumnName().trim();
            if (getColumnName().length() != columnName.length()) setColumnName(columnName);

            StringBuilder sql =
                    new StringBuilder("select count(*) from AD_Element where UPPER(ColumnName)=?");
            if (!newRecord) sql.append(" AND AD_Element_ID<>").append(getId());
            int no = getSQLValue(null, sql.toString(), columnName.toUpperCase());
            if (no > 0) {
                log.saveError(
                        DBException.SAVE_ERROR_NOT_UNIQUE_MSG,
                        Msg.getElement(getCtx(), I_AD_Element.COLUMNNAME_ColumnName));
                return false;
            }
        }

        return true;
    }

    /**
     * After Save
     *
     * @param newRecord new
     * @param success   success
     * @return success
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        if (!success) return success;
        //	Update Columns, Fields, Parameters, Print Info
        if (!newRecord) {
            StringBuilder sql = new StringBuilder();
            int no = 0;

            if (is_ValueChanged(HasName.Companion.getCOLUMNNAME_Name())
                    || is_ValueChanged(M_Element.COLUMNNAME_Description)
                    || is_ValueChanged(M_Element.COLUMNNAME_Help)
                    || is_ValueChanged(M_Element.COLUMNNAME_ColumnName)) {
                //	Column
                sql =
                        new StringBuilder("UPDATE AD_Column SET ColumnName=")
                                .append(TO_STRING(getColumnName()))
                                .append(", Name=")
                                .append(TO_STRING(getName()))
                                .append(", Description=")
                                .append(TO_STRING(getDescription()))
                                .append(", Help=")
                                .append(TO_STRING(getHelp()))
                                .append(" WHERE AD_Element_ID=")
                                .append(getId());
                no = executeUpdate(sql.toString(), get_TrxName());
                if (log.isLoggable(Level.FINE)) log.fine("afterSave - Columns updated #" + no);

                //	Parameter
                sql =
                        new StringBuilder("UPDATE AD_Process_Para SET ColumnName=")
                                .append(TO_STRING(getColumnName()))
                                .append(", Name=")
                                .append(TO_STRING(getName()))
                                .append(", Description=")
                                .append(TO_STRING(getDescription()))
                                .append(", Help=")
                                .append(TO_STRING(getHelp()))
                                .append(", AD_Element_ID=")
                                .append(getId())
                                .append(" WHERE UPPER(ColumnName)=")
                                .append(TO_STRING(getColumnName().toUpperCase()))
                                .append(" AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL");
                no = executeUpdate(sql.toString(), get_TrxName());

                sql =
                        new StringBuilder("UPDATE AD_Process_Para SET ColumnName=")
                                .append(TO_STRING(getColumnName()))
                                .append(", Name=")
                                .append(TO_STRING(getName()))
                                .append(", Description=")
                                .append(TO_STRING(getDescription()))
                                .append(", Help=")
                                .append(TO_STRING(getHelp()))
                                .append(" WHERE AD_Element_ID=")
                                .append(getId())
                                .append(" AND IsCentrallyMaintained='Y'");
                no += executeUpdate(sql.toString(), get_TrxName());
                if (log.isLoggable(Level.FINE)) log.fine("Parameters updated #" + no);

                // Info Column
                sql =
                        new StringBuilder("UPDATE AD_InfoColumn SET ColumnName=")
                                .append(TO_STRING(getColumnName()))
                                .append(", Name=")
                                .append(TO_STRING(getName()))
                                .append(", Description=")
                                .append(TO_STRING(getDescription()))
                                .append(", Help=")
                                .append(TO_STRING(getHelp()))
                                .append(" WHERE AD_Element_ID=")
                                .append(getId())
                                .append(" AND IsCentrallyMaintained='Y'");
                no += executeUpdate(sql.toString(), get_TrxName());
                if (log.isLoggable(Level.FINE)) log.fine("Info Column updated #" + no);
            }

            if (is_ValueChanged(HasName.Companion.getCOLUMNNAME_Name())
                    || is_ValueChanged(M_Element.COLUMNNAME_Description)
                    || is_ValueChanged(M_Element.COLUMNNAME_Help)) {
                //	Field
                sql =
                        new StringBuilder("UPDATE AD_Field SET Name=")
                                .append(TO_STRING(getName()))
                                .append(", Description=")
                                .append(TO_STRING(getDescription()))
                                .append(", Help=")
                                .append(TO_STRING(getHelp()))
                                .append(
                                        " WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=")
                                .append(getId())
                                .append(") AND IsCentrallyMaintained='Y'");
                no = executeUpdate(sql.toString(), get_TrxName());
                if (log.isLoggable(Level.FINE)) log.fine("Fields updated #" + no);

                // Info Column - update Name, Description, Help - doesn't have IsCentrallyMaintained
                // currently
                // no =executeUpdate(sql.toString(), get_TrxName());
                // log.fine("InfoColumn updated #" + no);
            }

            if (is_ValueChanged(M_Element.COLUMNNAME_PrintName)
                    || is_ValueChanged(HasName.Companion.getCOLUMNNAME_Name())) {
                //	Print Info
                sql =
                        new StringBuilder("UPDATE AD_PrintFormatItem SET PrintName=")
                                .append(TO_STRING(getPrintName()))
                                .append(", Name=")
                                .append(TO_STRING(getName()))
                                .append(" WHERE IsCentrallyMaintained='Y'")
                                .append(" AND EXISTS (SELECT * FROM AD_Column c ")
                                .append("WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=")
                                .append(getId())
                                .append(")");
                no = executeUpdate(sql.toString(), get_TrxName());
                if (log.isLoggable(Level.FINE)) log.fine("PrintFormatItem updated #" + no);
            }
        }
        return success;
    } //	afterSave

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("M_Element[");
        sb.append(getId()).append("-").append(getColumnName()).append("]");
        return sb.toString();
    } //	toString
} //	M_Element
