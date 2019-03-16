package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.I_AD_Element;
import org.compiere.util.Msg;
import org.idempiere.common.exceptions.DBException;

import java.util.Properties;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.convertString;
import static software.hsharp.core.util.DBKt.executeUpdate;
import static software.hsharp.core.util.DBKt.getSQLValue;

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
    public M_Element(Properties ctx, int AD_Element_ID) {
        super(ctx, AD_Element_ID);
        if (AD_Element_ID == 0) {
            //	setColumnName (null);
            //	setEntityType (null);	// U
            //	setName (null);
            //	setPrintName (null);
        }
    } //	M_Element


    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public M_Element(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * Minimum Constructor
     *
     * @param ctx        context
     * @param columnName column
     * @param EntityType entity type
     * @param trxName    trx
     */
    public M_Element(Properties ctx, String columnName, String EntityType) {
        super(ctx, 0);
        setColumnName(columnName);
        setName(columnName);
        setPrintName(columnName);
        //
        setEntityType(EntityType); // U
    } //	M_Element

    /**
     * Get Element
     *
     * @param ctx        context
     * @param columnName case insensitive column name
     * @param trxName    optional transaction name
     * @return case sensitive column name
     */
    public static M_Element get(Properties ctx, String columnName) {
        if (columnName == null || columnName.length() == 0) return null;
        //
        // TODO: caching if trxName == null
        final String whereClause = "UPPER(ColumnName)=?";
        M_Element retValue =
                new Query(ctx, I_AD_Element.Table_Name, whereClause)
                        .setParameters(columnName.toUpperCase())
                        .firstOnly();
        return retValue;
    } //	get

    /* (non-Javadoc)
     * @see PO#beforeSave(boolean)
     */
    @Override
    protected boolean beforeSave(boolean newRecord) {
        // Column AD_Element.ColumnName should be unique - teo_sarca [ 1613107 ]
        if (newRecord || isValueChanged(I_AD_Element.COLUMNNAME_ColumnName)) {
            String columnName = getColumnName().trim();
            if (getColumnName().length() != columnName.length()) setColumnName(columnName);

            StringBuilder sql =
                    new StringBuilder("select count(*) from AD_Element where UPPER(ColumnName)=?");
            if (!newRecord) sql.append(" AND AD_Element_ID<>").append(getId());
            int no = getSQLValue(sql.toString(), columnName.toUpperCase());
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

            if (isValueChanged(HasName.Companion.getCOLUMNNAME_Name())
                    || isValueChanged(M_Element.COLUMNNAME_Description)
                    || isValueChanged(M_Element.COLUMNNAME_Help)
                    || isValueChanged(M_Element.COLUMNNAME_ColumnName)) {
                //	Column
                sql =
                        new StringBuilder("UPDATE AD_Column SET ColumnName=")
                                .append(convertString(getColumnName()))
                                .append(", Name=")
                                .append(convertString(getName()))
                                .append(", Description=")
                                .append(convertString(getDescription()))
                                .append(", Help=")
                                .append(convertString(getHelp()))
                                .append(" WHERE AD_Element_ID=")
                                .append(getId());
                no = executeUpdate(sql.toString());
                if (log.isLoggable(Level.FINE)) log.fine("afterSave - Columns updated #" + no);

                //	Parameter
                sql =
                        new StringBuilder("UPDATE AD_Process_Para SET ColumnName=")
                                .append(convertString(getColumnName()))
                                .append(", Name=")
                                .append(convertString(getName()))
                                .append(", Description=")
                                .append(convertString(getDescription()))
                                .append(", Help=")
                                .append(convertString(getHelp()))
                                .append(", AD_Element_ID=")
                                .append(getId())
                                .append(" WHERE UPPER(ColumnName)=")
                                .append(convertString(getColumnName().toUpperCase()))
                                .append(" AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL");
                no = executeUpdate(sql.toString());

                sql =
                        new StringBuilder("UPDATE AD_Process_Para SET ColumnName=")
                                .append(convertString(getColumnName()))
                                .append(", Name=")
                                .append(convertString(getName()))
                                .append(", Description=")
                                .append(convertString(getDescription()))
                                .append(", Help=")
                                .append(convertString(getHelp()))
                                .append(" WHERE AD_Element_ID=")
                                .append(getId())
                                .append(" AND IsCentrallyMaintained='Y'");
                no += executeUpdate(sql.toString());
                if (log.isLoggable(Level.FINE)) log.fine("Parameters updated #" + no);

                // Info Column
                sql =
                        new StringBuilder("UPDATE AD_InfoColumn SET ColumnName=")
                                .append(convertString(getColumnName()))
                                .append(", Name=")
                                .append(convertString(getName()))
                                .append(", Description=")
                                .append(convertString(getDescription()))
                                .append(", Help=")
                                .append(convertString(getHelp()))
                                .append(" WHERE AD_Element_ID=")
                                .append(getId())
                                .append(" AND IsCentrallyMaintained='Y'");
                no += executeUpdate(sql.toString());
                if (log.isLoggable(Level.FINE)) log.fine("Info Column updated #" + no);
            }

            if (isValueChanged(HasName.Companion.getCOLUMNNAME_Name())
                    || isValueChanged(M_Element.COLUMNNAME_Description)
                    || isValueChanged(M_Element.COLUMNNAME_Help)) {
                //	Field
                sql =
                        new StringBuilder("UPDATE AD_Field SET Name=")
                                .append(convertString(getName()))
                                .append(", Description=")
                                .append(convertString(getDescription()))
                                .append(", Help=")
                                .append(convertString(getHelp()))
                                .append(
                                        " WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=")
                                .append(getId())
                                .append(") AND IsCentrallyMaintained='Y'");
                no = executeUpdate(sql.toString());
                if (log.isLoggable(Level.FINE)) log.fine("Fields updated #" + no);

                // Info Column - update Name, Description, Help - doesn't have IsCentrallyMaintained
                // currently
                // no =executeUpdate(sql.toString(), null);
                // log.fine("InfoColumn updated #" + no);
            }

            if (isValueChanged(M_Element.COLUMNNAME_PrintName)
                    || isValueChanged(HasName.Companion.getCOLUMNNAME_Name())) {
                //	Print Info
                sql =
                        new StringBuilder("UPDATE AD_PrintFormatItem SET PrintName=")
                                .append(convertString(getPrintName()))
                                .append(", Name=")
                                .append(convertString(getName()))
                                .append(" WHERE IsCentrallyMaintained='Y'")
                                .append(" AND EXISTS (SELECT * FROM AD_Column c ")
                                .append("WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=")
                                .append(getId())
                                .append(")");
                no = executeUpdate(sql.toString());
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
