package org.idempiere.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Properties;
import org.compiere.model.I_AD_Table;
import org.compiere.util.DisplayType;
import org.idempiere.common.util.CCache;

/**
 * Persistent Object Info. Provides structural information
 *
 * @author Jorg Janke
 * @author Victor Perez, e-Evolution SC
 *     <li>[ 2195894 ] Improve performance in PO engine
 *     <li>http://sourceforge.net/tracker/index.php?func=detail&aid=2195894&group_id=176962&atid=879335
 * @version $Id: POInfo.java,v 1.2 2006/07/30 00:58:37 jjanke Exp $
 */
public class POInfo extends software.hsharp.core.orm.POInfo implements Serializable {
  /** */
  private static final long serialVersionUID = 3496403499343293597L;

  /** Used by Remote FinReport */
  /** Cache of POInfo */
  private static CCache<Integer, POInfo> s_cache =
      new CCache<Integer, POInfo>(I_AD_Table.Table_Name, "POInfo", 200);

  private Boolean m_IsTranslated = null;

  /**
   * ************************************************************************ Create Persistent Info
   *
   * @param ctx context
   * @param AD_Table_ID AD_ Table_ID
   * @param baseLanguageOnly get in base language
   */
  protected POInfo(Properties ctx, int AD_Table_ID, boolean baseLanguageOnly) {
    this(ctx, AD_Table_ID, baseLanguageOnly, null);
  }

  /**
   * ************************************************************************ Create Persistent Info
   *
   * @param ctx context
   * @param AD_Table_ID AD_ Table_ID
   * @param baseLanguageOnly get in base language
   * @param trxName transaction name
   */
  protected POInfo(Properties ctx, int AD_Table_ID, boolean baseLanguageOnly, String trxName) {
    super(ctx, AD_Table_ID, baseLanguageOnly);
  } //  PInfo

    /**
   * POInfo Factory
   *
   * @param ctx context
   * @param AD_Table_ID AD_Table_ID
   * @param trxName Transaction name
   * @return POInfo
   */
  public static synchronized POInfo getPOInfo(Properties ctx, int AD_Table_ID, String trxName) {
    Integer key = AD_Table_ID;
    POInfo retValue = s_cache.get(key);
    if (retValue == null) {
      retValue = new POInfo(ctx, AD_Table_ID, false, trxName);
      if (retValue.getColumnCount() == 0)
        //	May be run before Language verification
        retValue = new POInfo(ctx, AD_Table_ID, true, trxName);
      else s_cache.put(key, retValue);
    }
    return retValue;
  } //  getPOInfo

  /**
   * String representation
   *
   * @return String Representation
   */
  public String toString() {
    return "POInfo[" + getTableName() + ",AD_Table_ID=" + getAD_Table_ID() + "]";
  } //  toString

  /**
   * String representation for index
   *
   * @param index column index
   * @return String Representation
   */
  public String toString(int index) {
    if (index < 0 || index >= getColumns().length)
      return "POInfo[" + getTableName() + "-(InvalidColumnIndex=" + index + ")]";
    return "POInfo[" + getTableName() + "-" + getColumns()[index].toString() + "]";
  } //  toString

  /**
   * Get AD_Table_ID
   *
   * @return AD_Table_ID
   */
  public int getAD_Table_ID() {
    return getTableId();
  } //  getAD_Table_ID

  /**
   * ************************************************************************ Get ColumnCount
   *
   * @return column count
   */
  public int getColumnCount() {
    return getColumns().length;
  } //  getColumnCount

  /**
   * Get Column Index
   *
   * @param ColumnName column name
   * @return index of column with ColumnName or -1 if not found
   */
  public int getColumnIndex(String ColumnName) {
    Integer i = getColumnNameMap().get(ColumnName.toUpperCase());
    if (i != null) return i.intValue();

    return -1;
  } //  getColumnIndex

  /**
   * Get Column Index
   *
   * @param AD_Column_ID column
   * @return index of column with ColumnName or -1 if not found
   */
  public int getColumnIndex(int AD_Column_ID) {
    Integer i = getColumnIdMap().get(AD_Column_ID);
    if (i != null) return i.intValue();

    return -1;
  } //  getColumnIndex

  /**
   * @param columnName
   * @return AD_Column_ID if found, -1 if not found
   */
  public int getAD_Column_ID(String columnName) {
    for (int i = 0; i < getColumns().length; i++) {
      if (columnName.equalsIgnoreCase(
          getColumns()[i].ColumnName)) // globalqss : modified to compare ignoring case [ 1619179 ]
      return getColumns()[i].AD_Column_ID;
    }
    return -1;
  }

  /**
   * Get Column
   *
   * @param index index
   * @return column
   */
  public POInfoColumn getColumn(int index) {
    if (index < 0 || index >= getColumns().length) return null;
    return getColumns()[index];
  } //  getColumn

  /**
   * Get Column Name
   *
   * @param index index
   * @return ColumnName column name
   */
  public String getColumnName(int index) {
    if (index < 0 || index >= getColumns().length) return null;
    return getColumns()[index].ColumnName;
  } //  getColumnName

  /**
   * Get Column SQL or Column Name
   *
   * @param index index
   * @return ColumnSQL column sql or name
   */
  public String getColumnSQL(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return null;
    if (columns[index].ColumnSQL != null && columns[index].ColumnSQL.length() > 0)
      return columns[index].ColumnSQL + " AS " + columns[index].ColumnName;
    return columns[index].ColumnName;
  } //  getColumnSQL

  /**
   * Is Column Virtual?
   *
   * @param index index
   * @return true if column is virtual
   */
  public boolean isVirtualColumn(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return true;
    return columns[index].ColumnSQL != null && columns[index].ColumnSQL.length() > 0;
  } //  isVirtualColumn

  /**
   * Get Column Label
   *
   * @param index index
   * @return column label
   */
  public String getColumnLabel(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return null;
    return columns[index].ColumnLabel;
  } //  getColumnLabel

  /**
   * Get Column Description
   *
   * @param index index
   * @return column description
   */
  public String getColumnDescription(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return null;
    return columns[index].ColumnDescription;
  } //  getColumnDescription

  /**
   * Get Column Class
   *
   * @param index index
   * @return Class
   */
  public Class<?> getColumnClass(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return null;
    return columns[index].ColumnClass;
  } //  getColumnClass

  /**
   * Get Column Display Type
   *
   * @param index index
   * @return DisplayType
   */
  public int getColumnDisplayType(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return DisplayType.String;
    return columns[index].DisplayType;
  } //  getColumnDisplayType

    /**
   * Is Column Mandatory
   *
   * @param index index
   * @return true if column mandatory
   */
  public boolean isColumnMandatory(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return false;
    return columns[index].IsMandatory;
  } //  isMandatory

  /**
   * Is Column Updateable
   *
   * @param index index
   * @return true if column updateable
   */
  public boolean isColumnUpdateable(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return false;
    return columns[index].IsUpdateable;
  } //  isUpdateable

  /**
   * Set Column Updateable
   *
   * @param index index
   * @param updateable column updateable
   */
  public void setColumnUpdateable(int index, boolean updateable) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return;
    columns[index].IsUpdateable = updateable;
  } //	setColumnUpdateable

  /**
   * Set all columns updateable
   *
   * @param updateable updateable
   */
  public void setUpdateable(boolean updateable) {
    POInfoColumn[] columns = getColumns();
    for (int i = 0; i < columns.length; i++) columns[i].IsUpdateable = updateable;
  } //	setUpdateable

    /**
   * Is Column Key
   *
   * @param index index
   * @return true if column is the key
   */
  public boolean isKey(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return false;
    return columns[index].IsKey;
  } //  isKey

  /**
   * Is Column Parent
   *
   * @param index index
   * @return true if column is a Parent
   */
  public boolean isColumnParent(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return false;
    return columns[index].IsParent;
  } //  isColumnParent

  /**
   * Is Column Translated
   *
   * @param index index
   * @return true if column is translated
   */
  public boolean isColumnTranslated(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return false;
    return columns[index].IsTranslated;
  } //  isColumnTranslated

  /**
   * Is Table Translated
   *
   * @return true if table is translated
   */
  public synchronized boolean isTranslated() {
    POInfoColumn[] columns = getColumns();
    if (m_IsTranslated == null) {
      m_IsTranslated = Boolean.FALSE;
      for (int i = 0; i < columns.length; i++) {
        if (columns[i].IsTranslated) {
          m_IsTranslated = Boolean.TRUE;
          break;
        }
      }
    }
    return m_IsTranslated.booleanValue();
  } //  isTranslated

  /**
   * Is Column (data) Encrypted
   *
   * @param index index
   * @return true if column is encrypted
   */
  public boolean isEncrypted(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return false;
    return columns[index].IsEncrypted;
  } //  isEncrypted

    /**
   * Get Column FieldLength
   *
   * @param index index
   * @return field length
   */
  public int getFieldLength(int index) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return 0;
    return columns[index].FieldLength;
  } //  getFieldLength

    /**
   * Validate Content
   *
   * @param index index
   * @param value new Value
   * @return null if all valid otherwise error message
   */
  public String validate(int index, Object value) {
    POInfoColumn[] columns = getColumns();
    if (index < 0 || index >= columns.length) return "RangeError";
    //	Mandatory (i.e. not null
    if (columns[index].IsMandatory && value == null) {
      return "FillMandatory";
    }
    if (value == null) return null;

    //	Length ignored

    //
    if (columns[index].ValueMin != null) {
      BigDecimal value_BD = null;
      try {
        if (columns[index].ValueMin_BD != null) value_BD = new BigDecimal(value.toString());
      } catch (Exception ex) {
      }
      //	Both are Numeric
      if (columns[index].ValueMin_BD != null
          && value_BD != null) { // 	error: 1 - 0 => 1  -  OK: 1 - 1 => 0 & 1 - 10 => -1
        int comp = columns[index].ValueMin_BD.compareTo(value_BD);
        if (comp > 0) {
          return "LessThanMinValue" + ";" + columns[index].ValueMin_BD.toPlainString();
        }
      } else //	String
      {
        int comp = columns[index].ValueMin.compareTo(value.toString());
        if (comp > 0) {
          return "LessThanMinValue" + ";" + columns[index].ValueMin;
        }
      }
    }
    if (columns[index].ValueMax != null) {
      BigDecimal value_BD = null;
      try {
        if (columns[index].ValueMax_BD != null) value_BD = new BigDecimal(value.toString());
      } catch (Exception ex) {
      }
      //	Both are Numeric
      if (columns[index].ValueMax_BD != null
          && value_BD != null) { // 	error 12 - 20 => -1  -  OK: 12 - 12 => 0 & 12 - 10 => 1
        int comp = columns[index].ValueMax_BD.compareTo(value_BD);
        if (comp < 0) {
          return "MoreThanMaxValue" + ";" + columns[index].ValueMax_BD.toPlainString();
        }
      } else //	String
      {
        int comp = columns[index].ValueMax.compareTo(value.toString());
        if (comp < 0) {
          return "MoreThanMaxValue" + ";" + columns[index].ValueMax;
        }
      }
    }
    return null;
  } //  validate

  /**
   * Build select clause
   *
   * @return stringbuilder
   */
  public StringBuilder buildSelect() {
    return buildSelect(false, false);
  }

  /**
   * Build select clause
   *
   * @param fullyQualified
   * @param noVirtualColumn
   * @return stringbuilder
   */
  public StringBuilder buildSelect(boolean fullyQualified, boolean noVirtualColumn) {
    POInfoColumn[] columns = getColumns();
    StringBuilder sql = new StringBuilder("SELECT ");
    int size = getColumnCount();
    int count = 0;
    for (int i = 0; i < size; i++) {
      boolean virtual = isVirtualColumn(i);
      if (virtual && noVirtualColumn) continue;

      count++;
      if (count > 1) sql.append(",");
      String columnSQL = getColumnSQL(i);
      if (fullyQualified && !virtual) sql.append(getTableName()).append(".");
      sql.append(columnSQL); // 	Normal and Virtual Column
      if (fullyQualified && !virtual) sql.append(" AS ").append(columns[i].ColumnName);
    }
    sql.append(" FROM ").append(getTableName());
    return sql;
  }
} //  POInfo
