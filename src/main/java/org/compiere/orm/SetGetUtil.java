package org.compiere.orm;

import static software.hsharp.core.util.DBKt.*;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.SetGetModel;
import org.idempiere.common.exceptions.AdempiereException;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Util;

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

  public static void updateColumns(SetGetModel model, String[] columnNames, ResultSet rs)
      throws SQLException {
    updateColumns(new SetGetModel[] {model}, columnNames, rs);
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
   * Copy all values from "from" to "to"
   *
   * @return number of columns that were copied and were were also changed in object "from"
   * @see #copyValues(PO, PO, String[], String[])
   */
  public static int copyChangedValues(PO to, PO from) {
    return copyValues(to, from, null, null, true);
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
   * Get Value as Boolean
   *
   * @param model
   * @param name
   * @return boolean value
   */
  public static boolean get_AttrValueAsBoolean(SetGetModel model, String name) {
    Object o = model.get_AttrValue(name);
    if (o != null) {
      if (o instanceof Boolean) return ((Boolean) o).booleanValue();
      else return "Y".equals(o);
    }
    return false;
  }

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
   * Set Attribute Value
   *
   * @param model
   * @param name
   * @param value
   * @throws AdempiereException if it can not be set (error setting, attribute/column name not
   *     found).
   */
  public static void set_AttrValueEx(SetGetModel model, String name, Object value) {
    if (!model.set_AttrValue(name, value))
      throw new AdempiereException("Value not set " + name + "=" + value);
  }

  /**
   * @param model
   * @param propertyNames
   * @return true if ANY of given properties had changed
   */
  public static boolean is_ValueChanged(SetGetModel model, String... propertyNames) {
    for (String name : propertyNames) {
      if (model.is_AttrValueChanged(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get TrxName for given object.
   *
   * @param o
   * @return trxName or null
   */
  public static String getTrxName(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof SetGetModel) {
      return ((SetGetModel) o).get_TrxName();
    } else if (o instanceof PO) {
      return ((PO) o).get_TrxName();
    } else {
      return null;
    }
  }

  /**
   * Check if given object was produced by used entry (i.e. created from a window)
   *
   * @param o object
   * @return If object is instanceof PO then {@link PO#is_UserEntry()} will be checked. If object is
   *     null then false will be returned. Else true will be returned.
   */
  public static boolean isUserEntry(Object o) {
    if (o == null) {
      return false;
    } else if (o instanceof PO) {
      //			return ((PO)o).is_UserEntry();
      return false; // TODO
    } else {
      return true;
    }
  }

  /**
   * Set model's Line#
   *
   * @param model
   * @param parentColumnName parent column name; if null then it won't be used
   * @param lineColumnName line column name; if null "Line" will be used
   */
  public static void setLineNo(SetGetModel model, String parentColumnName, String lineColumnName) {
    if (lineColumnName == null) {
      lineColumnName = "Line";
    }
    int lineNo = get_AttrValueAsInt(model, lineColumnName);
    if (lineNo != 0) {
      return;
    }
    //
    String tableName = model.get_TableName();
    String idColumnName = tableName + "_ID";
    //
    Collection<Object> params = new ArrayList<Object>();
    StringBuilder sql = new StringBuilder("SELECT COALESCE(MAX(" + lineColumnName + "),0)+10");
    sql.append(" FROM ").append(tableName);
    // Only active records
    sql.append(" WHERE IsActive=?");
    params.add(true);
    // Client Security Check
    sql.append(" AND AD_Client_ID IN (0,?)");
    params.add(SetGetUtil.get_AttrValueAsInt(model, "AD_Client_ID"));
    // Ignore this record
    sql.append(" AND ").append(idColumnName).append("<>?");
    params.add(get_AttrValueAsInt(model, idColumnName));
    // With same parent (if defined)
    if (parentColumnName != null) {
      sql.append(" AND ").append(parentColumnName).append("=?");
      params.add(get_AttrValueAsInt(model, parentColumnName));
    }
    //
    // Set LineNo
    lineNo = getSQLValueEx(model.get_TrxName(), sql.toString(), params);
    model.set_AttrValue(lineColumnName, lineNo);
  }

  /**
   * Get Table_Name for given PO class
   *
   * @param clazz
   * @return tableName
   * @throws AdempiereException if no table name found or any other exception occurs
   */
  public static String getTableName(Class<? extends PO> clazz) {
    try {
      return (String) clazz.getField("Table_Name").get(null);
    } catch (Exception e) {
      throw new AdempiereException(e);
    }
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
   * Get Context from given object. If object is null, null will be returned. If object does not
   * have getCtx() support then Env.getCtx() will be returned.
   *
   * @param o object
   * @return context or null(if object is null)
   */
  public static Properties getCtx(Object o) {
    if (o == null) {
      return null;
    } else if (o instanceof SetGetModel) {
      return ((SetGetModel) o).getCtx();
    }
    if (o instanceof PO) {
      return ((PO) o).getCtx();
    }
    return Env.getCtx();
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

        public String get_TrxName() {
          return po.get_TrxName();
        }

        public int get_Table_ID() {
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

  public static <T> T newInstance(Properties ctx, Class<T> clazz, String trxName) {
    try {
      return clazz
          .getConstructor(Properties.class, int.class, String.class)
          .newInstance(ctx, 0, trxName);
    } catch (Exception e) {
      throw new AdempiereException(e);
    }
  }

  public static void appendValue(SetGetModel model, String columnName, String value) {
    if (Util.isEmpty(value, true)) {
      return;
    }
    //
    final String valueToAppend = value.trim();
    final String valueOld = get_AttrValueAsString(model, columnName, null);
    final String valueNew;
    if (Util.isEmpty(valueOld, true)) {
      valueNew = value;
    } else {
      valueNew = valueOld + " | " + valueToAppend;
    }
    //
    model.set_AttrValue(columnName, valueNew);
  }
}
