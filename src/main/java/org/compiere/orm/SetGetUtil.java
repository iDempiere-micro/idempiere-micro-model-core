package org.compiere.orm;

import org.compiere.model.SetGetModel;
import org.idempiere.common.exceptions.AdempiereException;
import org.idempiere.common.exceptions.DBException;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Util;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.*;

public class SetGetUtil {
  /** Static logger */
  private static CLogger s_log = CLogger.getCLogger(SetGetUtil.class);

  /**
   * Update columns from the result of the given query.
   *
   * @param models
   * @param columnNames column names; if null, all columns from given query are used; if a
   *     columnName from array is null it will be ignored
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
          ok = model.set_AttrValue(columnName, obj);
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
   * @param trxName
   * @see #updateColumns(SetGetModel[], String[], ResultSet)
   */
  public static void updateColumns(
      SetGetModel[] models, String[] columnNames, String sql, Object[] params, String trxName) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = prepareStatement(sql);
      setParameters(pstmt, params);
      rs = pstmt.executeQuery();
      updateColumns(models, columnNames, rs);
    } catch (SQLException e) {
      throw new DBException(e, sql);
    } finally {
      rs = null;
      pstmt = null;
    }
  } //	updateColumns

    public static void updateColumns(
      SetGetModel model, String[] columnNames, String sql, String trxName) {
    updateColumns(new SetGetModel[] {model}, columnNames, sql, null, trxName);
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
   * @param trackOnlyChanges counts only the fields that were changed from (from.is_ValueChanged(int
   *     idx))
   * @return -1 the error or the number of heads that have been copied; if trackOnlyChanges = true
   *     then copied and include only the columns that have changed and "from"
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
      excludeFields = new String[] {"#"}; // dummy value
    }
    //
    int copiedFields = 0;
    for (int idx_from = 0; idx_from < from.get_ColumnCount(); idx_from++) {
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

      int idx_to = to.get_ColumnIndex(colName);
      if (idx_to < 0) {
        if (s_log.isLoggable(Level.FINEST)) s_log.finest("Field " + colName + " [SKIP:idx_to < 0]");
        continue;
      }
      if (to.getP_info().isVirtualColumn(idx_to) || to.getP_info().isKey(idx_to)) { // KeyColumn
        if (s_log.isLoggable(Level.FINEST))
          s_log.finest("Field " + colName + " [SKIP:virtual or key]");
        continue;
      }

      Object value = from.get_Value(idx_from);
      to.set_Value(idx_to, value);

      if (!trackOnlyChanges || from.is_ValueChanged(idx_from)) {
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
   * @param to destination object
   * @param from_tableName source object table
   * @param from_id source object ID
   * @param includeFields name fields to be excluded, null will be interpreted as String[0];
   * @see #updateColumns(SetGetModel, String[], String, String)
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

    updateColumns(to, includeFields, sql.toString(), null);
    return true;
  }

  /**
   * Get Value as integer
   *
   * @param model
   * @param name
   * @return int value
   */
  public static int get_AttrValueAsInt(SetGetModel model, String name) {
    Object o = model.get_AttrValue(name);
    if (o instanceof Number) return ((Number) o).intValue();
    return 0;
  } //	get_AttrValueAsInt

  /**
   * Get Value as Timestamp
   *
   * @param model
   * @param name
   * @return Timestamp value
   */
  public static Timestamp get_AttrValueAsDate(SetGetModel model, String name) {
    Object o = model.get_AttrValue(name);
    if (o instanceof Timestamp) return (Timestamp) o;
    return null;
  } //	get_AttrValueAsDate

  /**
   * Get Value as BigDecimal
   *
   * @param model
   * @param name
   * @return BigDecimal or {@link BigDecimal#ZERO}
   */
  public static BigDecimal get_AttrValueAsBigDecimal(SetGetModel model, String name) {
    Object o = model.get_AttrValue(name);
    if (o instanceof BigDecimal) return (BigDecimal) o;
    return BigDecimal.ZERO;
  } //	get_AttrValueAsBigDecimal

    /**
   * Get Value as String
   *
   * @param model
   * @param name
   * @param valueIfNull value that will be returned if the value is null
   * @return String value
   */
  public static String get_AttrValueAsString(SetGetModel model, String name, String valueIfNull) {
    Object o = model.get_AttrValue(name);
    if (o == null) return valueIfNull;
    return o.toString();
  }

    /**
   * Check if given object is persistent object
   *
   * @param o object
   * @return true if is persistent (i.e. instanceof PO)
   */
  public static final boolean isPersistent(Object o) {
    return o != null && o instanceof PO;
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
        public boolean set_AttrValue(String name, Object value) {
          return po.set_Value(name, value);
        }

        public boolean is_AttrValueChanged(String ColumnName) {
          return po.is_ValueChanged(ColumnName);
        }

        public int getTableId() {
          return po.getTableId();
        }

        public String get_TableName() {
          return po.get_TableName();
        }

        public Object get_AttrValue(String name) {
          return po.get_Value(name);
        }

        public Properties getCtx() {
          return po.getCtx();
        }
      };
    } else {
      throw new IllegalArgumentException("Can not wrap to SetGetModel - " + o.getClass());
    }
  }

}
