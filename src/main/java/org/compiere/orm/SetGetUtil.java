package org.compiere.orm;

import org.compiere.model.SetGetModel;
import org.idempiere.common.exceptions.DBException;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Util;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.prepareStatement;
import static software.hsharp.core.util.DBKt.setParameters;

public class SetGetUtil {
    /**
     * Static logger
     */
    private static CLogger s_log = CLogger.getCLogger(SetGetUtil.class);

    /**
     * Update columns from the result of the given query.
     *
     * @param models
     * @param columnNames column names; if null, all columns from given query are used; if a
     *                    columnName from array is null it will be ignored
     * @param rs
     * @throws SQLException
     */
    public static void updateColumns(SetGetModel[] models, String[] columnNames, ResultSet rs)
            throws SQLException {
        for (SetGetModel model : models) {
            if (s_log.isLoggable(Level.FINEST)) s_log.finest("Model: " + model);
            if (rs.next()) {
                if (columnNames == null) {
                    columnNames = getColumnNames(rs);
                }
                for (String columnName : columnNames) {
                    if (Util.isEmpty(columnName)) continue;
                    //
                    Object obj = null;
                    boolean ok = false;
                    obj = rs.getObject(columnName);
                    //
                    // Date Columns are retuned as Date -> convert to java.sql.Timestamp
                    if (obj instanceof java.sql.Date) {
                        obj = new java.sql.Timestamp(((java.sql.Date) obj).getTime());
                    }
                    //
                    // ID Columns (integer) are returned as BigDecimal -> convert to Integer
                    else if (obj instanceof BigDecimal && columnName.endsWith("_ID")) {
                        obj = ((BigDecimal) obj).intValue();
                    }
                    //
                    ok = model.setAttrValue(columnName, obj);
                    if (s_log.isLoggable(Level.FINEST))
                        s_log.finest(
                                "columnName="
                                        + columnName
                                        + ", value=["
                                        + obj
                                        + "]["
                                        + (obj != null ? obj.getClass().getName() : "null")
                                        + "], ok="
                                        + ok);
                }
            } else {
                if (s_log.isLoggable(Level.FINEST)) s_log.finest("@NoResult@");
                break;
            }
        }
    } //	updateColumns

    /**
     * Update columns from the result of the given query.
     *
     * @param models
     * @param columnNames
     * @param sql
     * @param params
     * @see #updateColumns(SetGetModel[], String[], ResultSet)
     */
    public static void updateColumns(
            SetGetModel[] models, String[] columnNames, String sql, Object[] params) {
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            pstmt = prepareStatement(sql);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();
            updateColumns(models, columnNames, rs);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    } //	updateColumns

    public static void updateColumns(
            SetGetModel model, String[] columnNames, String sql) {
        updateColumns(new SetGetModel[]{model}, columnNames, sql, null);
    }

    /**
     * Get Array of Column Names (String) for given ResultSet
     *
     * @param rs
     * @return column names (upper case)
     * @throws SQLException
     */
    private static final String[] getColumnNames(ResultSet rs) throws SQLException {
        if (rs == null) {
            return new String[0];
        }
        ResultSetMetaData rsmd = rs.getMetaData();
        int no = rsmd.getColumnCount();
        String[] columnNames = new String[no];
        for (int i = 1; i <= no; i++) {
            columnNames[i - 1] = rsmd.getColumnName(i).toUpperCase();
        }
        //
        return columnNames;
    } //	getColumnName

    /**
     * Copy from the fields to. The second object is not required to be in the same table. The
     * following fields are not copied: AD_Client_ID, AD_Org_ID, Created% Updated% IsActive. If
     * excludeFields includeFields and are null, then it will copy all the fields (which can be
     * copied). @ param to destination object @ param object from source @ param includeFields name
     * fields to be excluded; null will be interpreted as String [0]; excludeFields includeFields and
     * mutually exclusive, priority being includeFields; If includeFields excludeFields are null and
     * then copy all fields @ param excludeFields name fields to be excluded, null will be interpreted
     * as String [0]
     *
     * @return false if "to" or "from" is null, true otherwise
     */
    public static boolean copyValues(PO to, PO from, String[] includeFields, String[] excludeFields) {
        int no = copyValues(to, from, includeFields, excludeFields, false);
        return no >= 0;
    }

    /**
     * @param to
     * @param from
     * @param includeFields
     * @param excludeFields
     * @param trackOnlyChanges counts only the fields that were changed from (from.isValueChanged(int
     *                         idx))
     * @return -1 the error or the number of heads that have been copied; if trackOnlyChanges = true
     * then copied and include only the columns that have changed and "from"
     */
    private static int copyValues(
            PO to, PO from, String[] includeFields, String[] excludeFields, boolean trackOnlyChanges) {
        if (s_log.isLoggable(Level.FINEST)) {
            s_log.finest("Entering: From=" + from + " - To=" + to);
            //			s_log.finest("includeFields=" + ARHIPAC.toString(includeFields));
            //			s_log.finest("excludeFields=" + ARHIPAC.toString(excludeFields));
        }
        //
        if (to == null || from == null) {
            if (s_log.isLoggable(Level.FINEST)) {
                s_log.finest("Leaving: to == null || from == null");
                Thread.dumpStack();
            }
            return -1;
        }
        //
        if (includeFields != null) {
            excludeFields = null;
        }
        if (includeFields == null && excludeFields == null) {
            excludeFields = new String[]{"#"}; // dummy value
        }
        //
        int copiedFields = 0;
        for (int idx_from = 0; idx_from < from.getColumnCount(); idx_from++) {
            String colName = from.getP_info().getColumnName(idx_from);
            boolean isExcluded = false;
            //
            //  Ignore Standard Values
            if ("Created".equals(colName)
                    || "CreatedBy".equals(colName)
                    || "Updated".equals(colName)
                    || "UpdatedBy".equals(colName)
                    || "IsActive".equals(colName)
                    || "AD_Client_ID".equals(colName)
                    || "AD_Org_ID".equals(colName)) {
                isExcluded = true;
            }
            //
            // Include Policy
            else if (includeFields != null) {
                isExcluded = true;
                for (String incl : includeFields) {
                    if (incl.equalsIgnoreCase(colName)) {
                        isExcluded = false;
                        break;
                    }
                }
            }
            //
            // Exclude Policy
            else if (excludeFields != null) {
                for (String excl : excludeFields) {
                    if (excl.equalsIgnoreCase(colName)) {
                        isExcluded = true;
                        break;
                    }
                }
            }
            // -
            if (isExcluded) {
                if (s_log.isLoggable(Level.FINEST)) s_log.finest("Field " + colName + " [SKIP:excluded]");
                continue;
            }

            int idx_to = to.getColumnIndex(colName);
            if (idx_to < 0) {
                if (s_log.isLoggable(Level.FINEST)) s_log.finest("Field " + colName + " [SKIP:idx_to < 0]");
                continue;
            }
            if (to.getP_info().isVirtualColumn(idx_to) || to.getP_info().isKey(idx_to)) { // KeyColumn
                if (s_log.isLoggable(Level.FINEST))
                    s_log.finest("Field " + colName + " [SKIP:virtual or key]");
                continue;
            }

            Object value = from.getValue(idx_from);
            to.setValue(idx_to, value);

            if (!trackOnlyChanges || from.isValueChanged(idx_from)) {
                copiedFields++;
            }
            if (s_log.isLoggable(Level.FINEST))
                s_log.finest("Field " + colName + "=[" + value + "], idx=" + idx_from + "->" + idx_to);
        }
        //
        if (s_log.isLoggable(Level.FINEST)) s_log.finest("Leaving: to=" + to);
        return copiedFields;
    } //	copyValues

    /**
     * Copy from the fields to the. The two objects do not need to be in the same table.
     *
     * @param to             destination object
     * @param from_tableName source object table
     * @param from_id        source object ID
     * @param includeFields  name fields to be excluded, null will be interpreted as String[0];
     */
    public static boolean copyValues(
            SetGetModel to, String from_tableName, int from_id, String[] includeFields) {
        if (to == null
                || from_tableName == null
                || from_id <= 0
                || includeFields == null
                || includeFields.length == 0) {
            return false;
        }

        StringBuilder sql = new StringBuilder();
        for (String f : includeFields) {
            if (sql.length() > 0) sql.append(",");
            sql.append(f);
        }
        sql.insert(0, "SELECT ");
        sql.append(" FROM ")
                .append(from_tableName)
                .append(" WHERE ")
                .append(from_tableName)
                .append("_ID=")
                .append(from_id);

        updateColumns(to, includeFields, sql.toString());
        return true;
    }

    /**
     * Get Value as integer
     *
     * @param model
     * @param name
     * @return int value
     */
    public static int getAttrValueAsInt(SetGetModel model, String name) {
        Object o = model.getAttrValue(name);
        if (o instanceof Number) return ((Number) o).intValue();
        return 0;
    } //	getAttrValueAsInt

    /**
     * Get Value as BigDecimal
     *
     * @param model
     * @param name
     * @return BigDecimal or {@link BigDecimal#ZERO}
     */
    public static BigDecimal getAttrValueAsBigDecimal(SetGetModel model, String name) {
        Object o = model.getAttrValue(name);
        if (o instanceof BigDecimal) return (BigDecimal) o;
        return BigDecimal.ZERO;
    } //	getAttrValueAsBigDecimal

    /**
     * Check if given object is persistent object
     *
     * @param o object
     * @return true if is persistent (i.e. instanceof PO)
     */
    public static final boolean isPersistent(Object o) {
        return o instanceof PO;
    }

    /**
     * Wrap given object (if possible) to SetGetModel
     *
     * @param o object
     * @return object wrapped to SetGetModel
     */
    public static SetGetModel wrap(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof SetGetModel && !(o instanceof Proxy)) {
            return (SetGetModel) o;
        } else if (o instanceof PO) {
            final PO po = (PO) o;
            return new SetGetModel() {
                public boolean setAttrValue(String name, Object value) {
                    return po.setValue(name, value);
                }

                public boolean isAttrValueChanged(String ColumnName) {
                    return po.isValueChanged(ColumnName);
                }

                public int getTableId() {
                    return po.getTableId();
                }

                public String getTableName() {
                    return po.getTableName();
                }

                public Object getAttrValue(String name) {
                    return po.getValue(name);
                }
            };
        } else {
            throw new IllegalArgumentException("Can not wrap to SetGetModel - " + o.getClass());
        }
    }

}
