package org.idempiere.orm;

import static kotliquery.PackageKt.queryOf;
import static software.hsharp.core.orm.POKt.I_ZERO;
import static software.hsharp.core.util.DBKt.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.text.Collator;
import java.util.*;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import kotliquery.Row;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Element;
import org.compiere.model.I_AD_Field;
import org.compiere.util.DisplayType;
import org.compiere.util.Msg;
import org.idempiere.common.exceptions.AdempiereException;
import org.idempiere.common.exceptions.DBException;
import org.idempiere.common.util.*;
import org.idempiere.icommon.model.IPO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import software.hsharp.core.util.DB;

/**
 * Persistent Object. Superclass for actual implementations
 *
 * @author Jorg Janke
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *     <li>FR [ 1675490 ] ModelValidator on modelChange after events
 *     <li>BF [ 1704828 ] PO.is_Changed() and PO.is_ValueChanged are not consistent
 *     <li>FR [ 1720995 ] Add PO.saveEx() and PO.deleteEx() methods
 *     <li>BF [ 1990856 ] PO.set_Value* : truncate string more than needed
 *     <li>FR [ 2042844 ] PO.get_Translation improvements
 *     <li>FR [ 2818369 ] Implement PO.get_ValueAs*(columnName)
 *         https://sourceforge.net/tracker/?func=detail&aid=2818369&group_id=176962&atid=879335
 *     <li>BF [ 2849122 ] PO.AfterSave is not rollback on error
 *         https://sourceforge.net/tracker/?func=detail&aid=2849122&group_id=176962&atid=879332
 *     <li>BF [ 2859125 ] Can't set AD_OrgBP_ID
 *         https://sourceforge.net/tracker/index.php?func=detail&aid=2859125&group_id=176962&atid=879332
 *     <li>BF [ 2866493 ] VTreePanel is not saving who did the node move
 *         https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2866493&group_id=176962
 * @author Teo Sarca, teo.sarca@gmail.com
 *     <li>BF [ 2876259 ] PO.insertTranslation query is not correct
 *         https://sourceforge.net/tracker/?func=detail&aid=2876259&group_id=176962&atid=879332
 * @author Victor Perez, e-Evolution SC
 *     <li>[ 2195894 ] Improve performance in PO engine
 *     <li>http://sourceforge.net/tracker/index.php?func=detail&aid=2195894&group_id=176962&atid=879335
 *     <li>BF [2947622] The replication ID (Primary Key) is not working
 *     <li>https://sourceforge.net/tracker/?func=detail&aid=2947622&group_id=176962&atid=879332
 * @version $Id: PO.java,v 1.12 2006/08/09 16:38:47 jjanke Exp $
 */
public abstract class PO extends software.hsharp.core.orm.PO
    implements Serializable, Comparator<Object>, Evaluatee, Cloneable, IPO, I_Persistent {
  public static final String LOCAL_TRX_PREFIX = "POSave"; // TODO DAP use one from Trx.java
  /** User Maintained Entity Type */
  public static final String ENTITYTYPE_UserMaintained = "U";
  /** Dictionary Maintained Entity Type */
  public static final String ENTITYTYPE_Dictionary = "D";
  /** Access Level S__ 100 4 System info */
  public static final int ACCESSLEVEL_SYSTEM = 4;
  /** Access Level _C_ 010 2 Client info */
  public static final int ACCESSLEVEL_CLIENT = 2;
  /** Access Level __O 001 1 Organization info */
  public static final int ACCESSLEVEL_ORG = 1;
  /** Access Level SCO 111 7 System shared info */
  public static final int ACCESSLEVEL_ALL = 7;
  /** Access Level SC_ 110 6 System/Client info */
  public static final int ACCESSLEVEL_SYSTEMCLIENT = 6;
  /** Access Level _CO 011 3 Client shared info */
  public static final int ACCESSLEVEL_CLIENTORG = 3;
  /** default timeout, 300 seconds * */
  protected static final int QUERY_TIME_OUT = 300;
  /** Table ID Attribute */
  protected static final String XML_ATTRIBUTE_AD_Table_ID = "AD_Table_ID";
  /** Record ID Attribute */
  protected static final String XML_ATTRIBUTE_Record_ID = "Record_ID";
  /** */
  private static final long serialVersionUID = -6777678451696979575L;

  private static final String USE_TIMEOUT_FOR_UPDATE = "org.adempiere.po.useTimeoutForUpdate";
  /** Document Value Workflow Manager */
  protected static DocWorkflowMgr s_docWFMgr = null;
  /** Logger */
  protected static transient CLogger logS = CLogger.getCLogger(PO.class);
  /** Static Logger */
  protected static CLogger s_log = CLogger.getCLogger(PO.class);
  /** Cache */
  protected static CCache<String, String> trl_cache = new CCache<String, String>("po_trl", 5);

  protected String m_columnNamePrefix = null;
  protected transient CLogger log = CLogger.getCLogger(getClass());
  /** Errors when setting */
  protected ValueNamePair[] m_setErrors = null;
  /** Deleted ID */
  protected int m_idOld = 0;
  /** Custom Columns */
  protected HashMap<String, String> m_custom = null;
  /** Accounting Columns */
  protected ArrayList<String> s_acctColumns = null;
  /** Optional Transaction */
  protected String m_trxName = null;
  /** Attributes */
  private HashMap<String, Object> m_attributes = null;
  /** Trifon - Indicates that this record is created by replication functionality. */
  private boolean m_isReplication = false;
  /** LOB Info */
  private ArrayList<PO_LOB> m_lobInfo = null;

  public PO(Properties ctx, Row row) {
    super(ctx, row, null);
    int size = getP_info().getColumnCount();
    clearNewValues();
    m_setErrors = new ValueNamePair[size];
  }

  /**
   * ************************************************************************ Create New Persistent
   * Object
   *
   * @param ctx context
   */
  public PO(Properties ctx) {
    this(ctx, 0, null, null, null);
  } //  PO

  /**
   * Create & Load existing Persistent Object
   *
   * @param ID The unique ID of the object
   * @param ctx context
   * @param trxName transaction name
   */
  public PO(Properties ctx, int ID, String trxName) {
    this(ctx, ID, trxName, null, null);
  } //  PO

  /**
   * Create & Load existing Persistent Object.
   *
   * @param ctx context
   * @param rs optional - load from current result set position (no navigation, not closed) if null,
   *     a new record is created.
   * @param trxName transaction name
   */
  public PO(Properties ctx, ResultSet rs, String trxName, String columnNamePrefix) {
    this(ctx, 0, trxName, rs, columnNamePrefix);
  } //	PO

  /**
   * Create & Load existing Persistent Object.
   *
   * <pre>
   *  You load
   * 		- an existing single key record with 	new PO (ctx, Record_ID)
   * 			or									new PO (ctx, Record_ID, trxName)
   * 			or									new PO (ctx, rs, get_TrxName())
   * 		- a new single key record with			new PO (ctx, 0)
   * 		- an existing multi key record with		new PO (ctx, rs, get_TrxName())
   * 		- a new multi key record with			new PO (ctx, null)
   *  The ID for new single key records is created automatically,
   *  you need to set the IDs for multi-key records explicitly.
   * </pre>
   *
   * @param ctx context
   * @param ID the ID if 0, the record defaults are applied - ignored if re exists
   * @param trxName transaction name
   * @param rs optional - load from current result set position (no navigation, not closed)
   */
  public PO(Properties _ctx, int ID, String trxName, ResultSet rs, String _columnNamePrefix) {
    super(_ctx, null, _columnNamePrefix);
    m_trxName = trxName;

    POInfo p_info = super.getP_info();
    if (p_info == null || p_info.getTableName() == null)
      throw new IllegalArgumentException("Invalid PO Info - " + p_info);
    //
    int size = p_info.getColumnCount();
    clearNewValues();
    m_setErrors = new ValueNamePair[size];

    if (rs != null) load(rs); // 	will not have virtual columns
    else load(ID, trxName);
  } //  PO

  /**
   * Set Document Value Workflow Manager
   *
   * @param docWFMgr mgr
   */
  public static void setDocWorkflowMgr(DocWorkflowMgr docWFMgr) {
    s_docWFMgr = docWFMgr;
    s_log.config(s_docWFMgr.toString());
  } //	setDocWorkflowMgr

  /** Returns the summary node with the corresponding value */
  public static int retrieveIdOfParentValue(
      String value, String tableName, int clientID, String trxName) {
    String sql =
        "SELECT "
            + tableName
            + "_ID FROM "
            + tableName
            + " WHERE IsSummary='Y' AND AD_Client_ID=? AND Value=?";
    int pos = value.length() - 1;
    while (pos > 0) {
      String testParentValue = value.substring(0, pos);
      int parentID = getSQLValueEx(trxName, sql, clientID, testParentValue);
      if (parentID > 0) return parentID;
      pos--;
    }
    return 0; // rootID
  }

  /**
   * Get Find parameter. Convert to upper case and add % at the end
   *
   * @param query in string
   * @return out string
   */
  protected static String getFindParameter(String query) {
    if (query == null) return null;
    if (query.length() == 0 || query.equals("%")) return null;
    if (!query.endsWith("%")) query += "%";
    return query.toUpperCase();
  } //	getFindParameter

  /** PO.setTrxName - set given trxName to an array of POs As suggested by teo in [ 1854603 ] */
  public static void set_TrxName(PO[] lines, String trxName) {
    for (PO line : lines) line.set_TrxName(trxName);
  }

  /**
   * @param tableName
   * @return uuid column name
   */
  public static String getUUIDColumnName(String tableName) {
    String columnName = tableName + "_UU";
    if (columnName.length() > 30) {
      int i = columnName.length() - 30;
      columnName = tableName.substring(0, tableName.length() - i) + "_UU";
    }
    return columnName;
  }

  /**
   * Get Table Access Level
   *
   * @return Access Level
   */
  protected abstract int getAccessLevel();

  /**
   * String representation
   *
   * @return String representation
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("PO[").append(get_WhereClause(true)).append("]");
    return sb.toString();
  } //  toString

  /**
   * Equals based on ID
   *
   * @param cmp comparator
   * @return true if ID the same
   */
  public boolean equals(Object cmp) {
    if (cmp == null) return false;
    if (!(cmp instanceof PO)) return false;
    if (cmp.getClass().equals(this.getClass()))
      // if both ID's are zero they can't be compared by ID
      if (((PO) cmp).getId() == 0 && getId() == 0) return super.equals(cmp);
      else return ((PO) cmp).getId() == getId();
    return super.equals(cmp);
  } //	equals

  public int hashCode() {
    assert false : "hashCode not designed";
    return 42; // any arbitrary constant will do
  }

  /**
   * Compare based on DocumentNo, Value, Name, Description
   *
   * @param o1 Object 1
   * @param o2 Object 2
   * @return -1 if o1 < o2
   */
  public int compare(Object o1, Object o2) {
    if (o1 == null) return -1;
    else if (o2 == null) return 1;
    if (!(o1 instanceof PO)) throw new ClassCastException("Not PO -1- " + o1);
    if (!(o2 instanceof PO)) throw new ClassCastException("Not PO -2- " + o2);
    //	same class
    Collator collator = Collator.getInstance();
    if (o1.getClass().equals(o2.getClass())) {
      int index = get_ColumnIndex("DocumentNo");
      if (index == -1) index = get_ColumnIndex("Value");
      if (index == -1) index = get_ColumnIndex("Name");
      if (index == -1) index = get_ColumnIndex("Description");
      if (index != -1) {
        PO po1 = (PO) o1;
        Object comp1 = po1.get_Value(index);
        PO po2 = (PO) o2;
        Object comp2 = po2.get_Value(index);
        if (comp1 == null) return -1;
        else if (comp2 == null) return 1;
        return collator.compare(comp1.toString(), comp2.toString());
      }
    }
    return collator.compare(o1.toString(), o2.toString());
  } //	compare

  /**
   * Get TableName.
   *
   * @return table name
   */
  public String get_TableName() {
    POInfo p_info = super.getP_info();
    return p_info.getTableName();
  } //  get_TableName

  /**
   * Get Key Columns.
   *
   * @return table name
   */
  public String[] get_KeyColumns() {
    return super.getM_keyColumns();
  } //  get_KeyColumns

  /**
   * Return Single Key Record ID
   *
   * @return ID or 0
   */
  public int getId() {
    Object oo = super.getIds()[0];
    if (oo != null && oo instanceof Integer) return (Integer) oo;
    return 0;
  } //  getID

  /**
   * Return Deleted Single Key Record ID
   *
   * @return ID or 0
   */
  public int get_IDOld() {
    return m_idOld;
  } //  getID

  /**
   * Get Logger
   *
   * @return logger
   */
  public CLogger get_Logger() {
    return log;
  } //	getLogger

  /**
   * ************************************************************************ Get Value
   *
   * @param index index
   * @return value
   */
  public final Object get_Value(int index) {
    if (index < 0 || index >= get_ColumnCount()) {
      log.log(Level.WARNING, "Index invalid - " + index);
      return null;
    }
    Object[] newValues = getNewValues();

    if (newValues[index] != null) {
      if (newValues[index].equals(Null.NULL)) return null;
      return newValues[index];
    }
    return getOldValues()[index];
  } //  get_Value

  /**
   * Get Value as int
   *
   * @param index index
   * @return int value or 0
   */
  public int get_ValueAsInt(int index) {
    Object value = get_Value(index);
    if (value == null) return 0;
    if (value instanceof Integer) return (Integer) value;
    try {
      return Integer.parseInt(value.toString());
    } catch (NumberFormatException ex) {
      POInfo p_info = super.getP_info();
      log.warning(p_info.getColumnName(index) + " - " + ex.getMessage());
      return 0;
    }
  } //  get_ValueAsInt

  /**
   * Get Value
   *
   * @param columnName column name
   * @return value or null
   */
  public final Object get_Value(String columnName) {
    int index = get_ColumnIndex(columnName);
    if (index < 0) {
      log.log(Level.WARNING, "Column not found - " + columnName);
      Trace.printStack();
      return null;
    }
    return get_Value(index);
  } //  get_Value

  /**
   * Get Encrypted Value
   *
   * @param columnName column name
   * @return value or null
   */
  protected final Object get_ValueE(String columnName) {
    return get_Value(columnName);
  } //  get_ValueE

  /**
   * Get Column Value
   *
   * @param variableName name
   * @return value or ""
   */
  public String get_ValueAsString(String variableName) {
    Object value = get_Value(variableName);
    if (value == null) return "";
    return value.toString();
  } //	get_ValueAsString

  /**
   * Get Value of Column
   *
   * @param AD_Column_ID column
   * @return value or null
   */
  public final Object get_ValueOfColumn(int AD_Column_ID) {
    POInfo p_info = super.getP_info();
    int index = p_info.getColumnIndex(AD_Column_ID);
    if (index < 0) {
      log.log(Level.WARNING, "Not found - AD_Column_ID=" + AD_Column_ID);
      return null;
    }
    return get_Value(index);
  } //  get_ValueOfColumn

  /**
   * Get Old Value
   *
   * @param index index
   * @return value
   */
  public final Object get_ValueOld(int index) {
    if (index < 0 || index >= get_ColumnCount()) {
      log.log(Level.WARNING, "Index invalid - " + index);
      return null;
    }
    return getOldValues()[index];
  } //  get_ValueOld

  /**
   * Get Old Value
   *
   * @param columnName column name
   * @return value or null
   */
  public final Object get_ValueOld(String columnName) {
    int index = get_ColumnIndex(columnName);
    if (index < 0) {
      log.log(Level.WARNING, "Column not found - " + columnName);
      return null;
    }
    return get_ValueOld(index);
  } //  get_ValueOld

  /**
   * Get Old Value as int
   *
   * @param columnName column name
   * @return int value or 0
   */
  public int get_ValueOldAsInt(String columnName) {
    Object value = get_ValueOld(columnName);
    if (value == null) return 0;
    if (value instanceof Integer) return (Integer) value;
    try {
      return Integer.parseInt(value.toString());
    } catch (NumberFormatException ex) {
      log.warning(columnName + " - " + ex.getMessage());
      return 0;
    }
  } //  get_ValueOldAsInt

  /**
   * Is Value Changed
   *
   * @param index index
   * @return true if changed
   */
  public final boolean is_ValueChanged(int index) {
    if (index < 0 || index >= get_ColumnCount()) {
      log.log(Level.WARNING, "Index invalid - " + index);
      return false;
    }
    Object[] newValues = getNewValues();
    if (newValues[index] == null) return false;
    if (newValues[index] == Null.NULL && getOldValues()[index] == null) return false;
    return !newValues[index].equals(getOldValues()[index]);
  } //  is_ValueChanged

  /**
   * Is Value Changed
   *
   * @param columnName column name
   * @return true if changed
   */
  public final boolean is_ValueChanged(String columnName) {
    int index = get_ColumnIndex(columnName);
    if (index < 0) {
      log.log(Level.WARNING, "Column not found - " + columnName);
      return false;
    }
    return is_ValueChanged(index);
  } //  is_ValueChanged

  /**
   * Return new - old. - New Value if Old Value is null - New Value - Old Value if Number -
   * otherwise null
   *
   * @param index index
   * @return new - old or null if not appropriate or not changed
   */
  public final Object get_ValueDifference(int index) {
    if (index < 0 || index >= get_ColumnCount()) {
      log.log(Level.WARNING, "Index invalid - " + index);
      return null;
    }
    Object[] newValues = getNewValues();
    Object nValue = newValues[index];
    //	No new Value or NULL
    if (nValue == null || nValue == Null.NULL) return null;
    //
    Object oValue = getOldValues()[index];
    if (oValue == null || oValue == Null.NULL) return nValue;
    if (nValue instanceof BigDecimal) {
      BigDecimal obd = (BigDecimal) oValue;
      return ((BigDecimal) nValue).subtract(obd);
    } else if (nValue instanceof Integer) {
      int result = (Integer) nValue;
      result -= (Integer) oValue;
      return result;
    }
    //
    log.warning("Invalid type - New=" + nValue);
    return null;
  } //  get_ValueDifference

  /**
   * Return new - old. - New Value if Old Value is null - New Value - Old Value if Number -
   * otherwise null
   *
   * @param columnName column name
   * @return new - old or null if not appropriate or not changed
   */
  public final Object get_ValueDifference(String columnName) {
    int index = get_ColumnIndex(columnName);
    if (index < 0) {
      log.log(Level.WARNING, "Column not found - " + columnName);
      return null;
    }
    return get_ValueDifference(index);
  } //  get_ValueDifference

  /**
   * Set (numeric) Key Value
   *
   * @param ColumnName column name
   * @param value value
   */
  protected void set_Keys(String ColumnName, Object value) {
    //	Update if KeyColumn
    for (int i = 0; i < getIds().length; i++) {
      if (ColumnName.equals(getM_keyColumns()[i])) {
        getIds()[i] = value;
      }
    } //	for all key columns
  } //	setKeys

  /**
   * ************************************************************************ Get Column Count
   *
   * @return column count
   */
  public int get_ColumnCount() {
    POInfo p_info = super.getP_info();
    return p_info.getColumnCount();
  } //  getColumnCount

  /**
   * Get Column Name
   *
   * @param index index
   * @return ColumnName
   */
  public String get_ColumnName(int index) {
    POInfo p_info = super.getP_info();
    return p_info.getColumnName(index);
  } //  getColumnName

  /**
   * Get Column Label
   *
   * @param index index
   * @return Column Label
   */
  protected String get_ColumnLabel(int index) {
    POInfo p_info = super.getP_info();
    return p_info.getColumnLabel(index);
  } //  getColumnLabel

  /**
   * Get Column Description
   *
   * @param index index
   * @return column description
   */
  protected String get_ColumnDescription(int index) {
    POInfo p_info = super.getP_info();
    return p_info.getColumnDescription(index);
  } //  getColumnDescription

  /**
   * Is Column Mandatory
   *
   * @param index index
   * @return true if column mandatory
   */
  protected boolean isColumnMandatory(int index) {

    POInfo p_info = super.getP_info();
    return p_info.isColumnMandatory(index);
  } //  isColumnNandatory

  /**
   * Is Column Updateable
   *
   * @param index index
   * @return true if column updateable
   */
  protected boolean isColumnUpdateable(int index) {

    POInfo p_info = super.getP_info();
    return p_info.isColumnUpdateable(index);
  } //	isColumnUpdateable

  /**
   * Set Column Updateable
   *
   * @param index index
   * @param updateable column updateable
   */
  protected void set_ColumnUpdateable(int index, boolean updateable) {
    POInfo p_info = super.getP_info();
    p_info.setColumnUpdateable(index, updateable);
  } //	setColumnUpdateable

  /**
   * Set all columns updateable
   *
   * @param updateable updateable
   */
  protected void setUpdateable(boolean updateable) {
    POInfo p_info = super.getP_info();
    p_info.setUpdateable(updateable);
  } //	setUpdateable

  /**
   * Get Column DisplayType
   *
   * @param index index
   * @return display type
   */
  protected int get_ColumnDisplayType(int index) {
    POInfo p_info = super.getP_info();
    return p_info.getColumnDisplayType(index);
  } //	getColumnDisplayType

  /**
   * Get Column Index
   *
   * @param columnName column name
   * @return index of column with ColumnName or -1 if not found
   */
  public final int get_ColumnIndex(String columnName) {

    POInfo p_info = super.getP_info();
    return p_info.getColumnIndex(columnName);
  } //  getColumnIndex

  /**
   * Get Display Value of value
   *
   * @param columnName columnName
   * @param currentValue current value
   * @return String value with "./." as null
   */
  public String get_DisplayValue(String columnName, boolean currentValue) {
    Object value = currentValue ? get_Value(columnName) : get_ValueOld(columnName);
    if (value == null) return "./.";
    String retValue = value.toString();
    int index = get_ColumnIndex(columnName);
    if (index < 0) return retValue;
    int dt = get_ColumnDisplayType(index);
    if (DisplayType.isText(dt) || DisplayType.YesNo == dt) return retValue;
    //	Lookup
    //	Other
    return retValue;
  } //	get_DisplayValue

  /**
   * ************************************************************************ Load record with ID
   *
   * @param ID ID
   * @param trxName transaction name
   */
  protected void load(int ID, String trxName) {
    if (log.isLoggable(Level.FINEST)) log.finest("ID=" + ID);
    if (ID > 0) {
      setKeyInfo();
      setIds(new Object[] {ID});
      // keyColumns = new String[] {p_info.getTableName() + "_ID"};
      load(trxName);
    } else //	new
    {
      loadDefaults();
      setCreateNew(true);
      setKeyInfo(); //	sets ids
      loadComplete(true);
    }
  } //	load

  /**
   * Load from the current position of a ResultSet
   *
   * @param rs result set
   * @return true if loaded
   */
  protected boolean load(ResultSet rs) {
    int size = get_ColumnCount();
    boolean success = true;
    int index = 0;
    POInfo p_info = super.getP_info();
    log.finest("(rs)");
    //  load column values
    for (index = 0; index < size; index++) {
      String columnName =
          (m_columnNamePrefix == null ? "" : m_columnNamePrefix) + p_info.getColumnName(index);
      Class<?> clazz = p_info.getColumnClass(index);
      int dt = p_info.getColumnDisplayType(index);
      try {
        if (clazz == Integer.class) getOldValues()[index] = decrypt(index, rs.getInt(columnName));
        else if (clazz == BigDecimal.class)
          getOldValues()[index] = decrypt(index, rs.getBigDecimal(columnName));
        else if (clazz == Boolean.class)
          getOldValues()[index] = "Y".equals(decrypt(index, rs.getString(columnName)));
        else if (clazz == Timestamp.class)
          getOldValues()[index] = decrypt(index, rs.getTimestamp(columnName));
        else if (DisplayType.isLOB(dt)) getOldValues()[index] = get_LOB(rs.getObject(columnName));
        else if (clazz == String.class) {
          String value = (String) decrypt(index, rs.getString(columnName));
          if (value != null) {
            if (getTableId() == I_AD_Column.Table_ID
                || getTableId() == I_AD_Element.Table_ID
                || getTableId() == I_AD_Field.Table_ID) {
              if ("Description".equals(columnName) || "Help".equals(columnName)) {
                value = value.intern();
              }
            }
          }
          getOldValues()[index] = value;
        } else getOldValues()[index] = loadSpecial(rs, index);
        //	NULL
        if (rs.wasNull() && getOldValues()[index] != null) getOldValues()[index] = null;
        //
        if (CLogMgt.isLevelAll())
          log.finest(
              index
                  + ": "
                  + p_info.getColumnName(index)
                  + "("
                  + p_info.getColumnClass(index)
                  + ") = "
                  + getOldValues()[index]);
      } catch (SQLException e) {
        if (p_info.isVirtualColumn(index)) { // 	if rs constructor used
          if (log.isLoggable(Level.FINER))
            log.log(Level.FINER, "Virtual Column not loaded: " + columnName);
        } else {
          log.log(
              Level.SEVERE,
              "(rs) - "
                  + index
                  + ": "
                  + p_info.getTableName()
                  + "."
                  + p_info.getColumnName(index)
                  + " ("
                  + p_info.getColumnClass(index)
                  + ") - "
                  + e);
          success = false;
        }
      }
    }
    setCreateNew(false);
    setKeyInfo();
    loadComplete(success);
    return success;
  } //	load

  /**
   * Load from HashMap
   *
   * @param hmIn hash map
   * @return true if loaded
   */
  protected boolean load(HashMap<String, String> hmIn) {
    int size = get_ColumnCount();
    boolean success = true;
    int index = 0;
    POInfo p_info = super.getP_info();
    log.finest("(hm)");
    //  load column values
    for (index = 0; index < size; index++) {
      String columnName = p_info.getColumnName(index);
      String value = hmIn.get(columnName);
      if (value == null) continue;
      Class<?> clazz = p_info.getColumnClass(index);
      int dt = p_info.getColumnDisplayType(index);
      try {
        if (clazz == Integer.class) getOldValues()[index] = Integer.valueOf(value);
        else if (clazz == BigDecimal.class) getOldValues()[index] = new BigDecimal(value);
        else if (clazz == Boolean.class) getOldValues()[index] = "Y".equals(value);
        else if (clazz == Timestamp.class) getOldValues()[index] = Timestamp.valueOf(value);
        else if (DisplayType.isLOB(dt))
          getOldValues()[index] = null; // 	get_LOB (rs.getObject(columnName));
        else if (clazz == String.class) getOldValues()[index] = value;
        else getOldValues()[index] = null; // loadSpecial(rs, index);
        //
        if (CLogMgt.isLevelAll())
          log.finest(
              index
                  + ": "
                  + p_info.getColumnName(index)
                  + "("
                  + p_info.getColumnClass(index)
                  + ") = "
                  + getOldValues()[index]);
      } catch (Exception e) {
        if (p_info.isVirtualColumn(index)) { // 	if rs constructor used
          if (log.isLoggable(Level.FINER))
            log.log(Level.FINER, "Virtual Column not loaded: " + columnName);
        } else {
          log.log(
              Level.SEVERE,
              "(ht) - "
                  + index
                  + ": "
                  + p_info.getTableName()
                  + "."
                  + p_info.getColumnName(index)
                  + " ("
                  + p_info.getColumnClass(index)
                  + ") - "
                  + e);
          success = false;
        }
      }
    }
    setCreateNew(false);
    //	Overwrite
    setStandardDefaults();
    setKeyInfo();
    loadComplete(success);
    return success;
  } //	load

  /**
   * Create Hashmap with data as Strings
   *
   * @return HashMap
   */
  protected HashMap<String, String> get_HashMap() {
    HashMap<String, String> hmOut = new HashMap<String, String>();
    int size = get_ColumnCount();
    POInfo p_info = super.getP_info();
    for (int i = 0; i < size; i++) {
      Object value = get_Value(i);
      //	Don't insert NULL values (allows Database defaults)
      if (value == null || p_info.isVirtualColumn(i)) continue;
      //	Display Type
      int dt = p_info.getColumnDisplayType(i);
      //  Based on class of definition, not class of value
      Class<?> c = p_info.getColumnClass(i);
      String stringValue = null;
      if (c == Object.class) ; // 	saveNewSpecial (value, i));
      else if (value == null || value.equals(Null.NULL)) ;
      else if (value instanceof Integer || value instanceof BigDecimal)
        stringValue = value.toString();
      else if (c == Boolean.class) {
        boolean bValue = false;
        if (value instanceof Boolean) bValue = (Boolean) value;
        else bValue = "Y".equals(value);
        stringValue = bValue ? "Y" : "N";
      } else if (value instanceof Timestamp) stringValue = value.toString();
      else if (c == String.class) stringValue = (String) value;
      else if (DisplayType.isLOB(dt)) ;
      else ; // 	saveNewSpecial (value, i));
      //
      if (stringValue != null) hmOut.put(p_info.getColumnName(i), stringValue);
    }
    //	Custom Columns
    if (m_custom != null) {
      for (String column : m_custom.keySet()) {
        //				int index = p_info.getColumnIndex(column);
        String value = m_custom.get(column);
        if (value != null) hmOut.put(column, value);
      }
      m_custom = null;
    }
    return hmOut;
  } //  get_HashMap

  /**
   * Load Special data (images, ..). To be extended by sub-classes
   *
   * @param rs result set
   * @param index zero based index
   * @return value value
   * @throws SQLException
   */
  protected Object loadSpecial(ResultSet rs, int index) throws SQLException {
    POInfo p_info = super.getP_info();
    if (log.isLoggable(Level.FINEST)) log.finest("(NOP) - " + p_info.getColumnName(index));
    return null;
  } //  loadSpecial

  /** Load Defaults */
  protected void loadDefaults() {
    setStandardDefaults();
    //
    /** @todo defaults from Field */
    //	MField.getDefault(p_info.getDefaultLogic(i));
  } //	loadDefaults

  /** Set Default values. Client, Org, Created/Updated, *By, IsActive */
  protected void setStandardDefaults() {
    int size = get_ColumnCount();
    POInfo p_info = super.getP_info();
    Object[] newValues = getNewValues();
    for (int i = 0; i < size; i++) {
      if (p_info.isVirtualColumn(i)) continue;
      String colName = p_info.getColumnName(i);
      //  Set Standard Values
      if (colName.endsWith("tedBy")) newValues[i] = Env.getContextAsInt(getCtx(), "#AD_User_ID");
      else if (colName.equals("Created") || colName.equals("Updated"))
        newValues[i] = new Timestamp(System.currentTimeMillis());
      else if (colName.equals(p_info.getTableName() + "_ID")) //  KeyColumn
      newValues[i] = I_ZERO;
      else if (colName.equals("IsActive")) newValues[i] = Boolean.TRUE;
      else if (colName.equals("AD_Client_ID")) newValues[i] = Env.getADClientID(getCtx());
      else if (colName.equals("AD_Org_ID")) newValues[i] = Env.getOrgId(getCtx());
      else if (colName.equals("Processed")) newValues[i] = Boolean.FALSE;
      else if (colName.equals("Processing")) newValues[i] = Boolean.FALSE;
      else if (colName.equals("Posted")) newValues[i] = Boolean.FALSE;
    }
  } //  setDefaults

  /**
   * ************************************************************************ Are all mandatory
   * Fields filled (i.e. can we save)?. Stops at first null mandatory field
   *
   * @return true if all mandatory fields are ok
   */
  protected boolean isMandatoryOK() {
    int size = get_ColumnCount();
    POInfo p_info = super.getP_info();
    for (int i = 0; i < size; i++) {
      if (p_info.isColumnMandatory(i)) {
        if (p_info.isVirtualColumn(i)) continue;
        if (get_Value(i) == null || get_Value(i).equals(Null.NULL)) {
          if (log.isLoggable(Level.INFO)) log.info(p_info.getColumnName(i));
          return false;
        }
      }
    }
    return true;
  } //  isMandatoryOK

  /**
   * Get AD_Org
   *
   * @return AD_Org_ID
   */
  public int getOrgId() {
    Integer ii = (Integer) get_Value("AD_Org_ID");
    if (ii == null) return 0;
    return ii;
  } //	getOrgId

  /**
   * Is Active
   *
   * @return is active
   */
  public final boolean isActive() {
    Boolean bb = (Boolean) get_Value("IsActive");
    if (bb != null) return bb;
    return false;
  } //	isActive

  /**
   * Get Created
   *
   * @return created
   */
  public final Timestamp getCreated() {
    return (Timestamp) get_Value("Created");
  } //	getCreated

  /**
   * Get Updated
   *
   * @return updated
   */
  public final Timestamp getUpdated() {
    return (Timestamp) get_Value("Updated");
  } //	getUpdated

  /**
   * Get CreatedBy
   *
   * @return AD_User_ID
   */
  public final int getCreatedBy() {
    Integer ii = (Integer) get_Value("CreatedBy");
    if (ii == null) return 0;
    return ii;
  } //	getCreateddBy

  /**
   * Get UpdatedBy
   *
   * @return AD_User_ID
   */
  public final int getUpdatedBy() {
    Integer ii = (Integer) get_Value("UpdatedBy");
    if (ii == null) return 0;
    return ii;
  } //	getUpdatedBy

  public String get_Translation(String columnName, String AD_Language) {
    return get_Translation(columnName, AD_Language, false, true);
  }

  /**
   * Get Translation of column (if needed). It checks if the base language is used or the column is
   * not translated. If there is no translation then it fallback to original value.
   *
   * @param columnName
   * @param AD_Language
   * @param reload don't use cache, reload from DB
   * @param fallback fallback to base if no translation found
   * @return translated string
   * @throws IllegalArgumentException if columnName or AD_Language is null or model has multiple PK
   */
  public String get_Translation(
      String columnName, String AD_Language, boolean reload, boolean fallback) {
    //
    // Check if columnName, AD_Language is valid or table support translation (has 1 PK) => error
    if (columnName == null
        || AD_Language == null
        || getIds().length > 1
        || getIds()[0].equals(I_ZERO)
        || !(getIds()[0] instanceof Integer)) {
      throw new IllegalArgumentException(
          "ColumnName="
              + columnName
              + ", AD_Language="
              + AD_Language
              + ", ID.length="
              + getIds().length
              + ", ID="
              + getIds()[0]);
    }

    String key = getTrlCacheKey(columnName, AD_Language);
    String retValue = null;
    if (!reload && trl_cache.containsKey(key)) {
      retValue = trl_cache.get(key);
      return retValue;

    } else {
      //
      // Check if NOT base language and column is translated => load trl from db
      POInfo p_info = super.getP_info();
      if (!Env.isBaseLanguage(AD_Language, get_TableName())
          && p_info.isColumnTranslated(p_info.getColumnIndex(columnName))) {
        // Load translation from database
        int ID = (Integer) getIds()[0];
        StringBuilder sql =
            new StringBuilder("SELECT ")
                .append(columnName)
                .append(" FROM ")
                .append(p_info.getTableName())
                .append("_Trl WHERE ")
                .append(getM_keyColumns()[0])
                .append("=?")
                .append(" AND AD_Language=?");
        retValue = getSQLValueString(get_TrxName(), sql.toString(), ID, AD_Language);
      }
    }
    //
    // If no translation found or not translated, fallback to original:
    if (retValue == null && fallback) {
      Object val = get_Value(columnName);
      retValue = (val != null ? val.toString() : null);
    }
    trl_cache.put(key, retValue);
    //
    return retValue;
  } //	get_Translation

  /** Return the key used in the translation cache */
  protected String getTrlCacheKey(String columnName, String AD_Language) {
    return get_TableName() + "." + columnName + "|" + getId() + "|" + AD_Language;
  }

  /**
   * Get Translation of column
   *
   * @param columnName
   */
  public String get_Translation(String columnName) {
    return get_Translation(columnName, true);
  }

  /**
   * Get Translation of column
   *
   * @param columnName
   * @param AD_Language
   * @param reload don't use cache, reload from DB
   */
  public String get_Translation(String columnName, String AD_Language, boolean reload) {
    return get_Translation(columnName, AD_Language, reload, true);
  }

  /**
   * Get Translation of column
   *
   * @param columnName
   * @param fallback fallback to base if no translation found
   * @return translation
   */
  public String get_Translation(String columnName, boolean fallback) {
    return get_Translation(columnName, Env.getADLanguage(getCtx()), false, fallback);
  }

  /**
   * Is there a Change to be saved?
   *
   * @return true if record changed
   */
  public boolean is_Changed() {
    int size = get_ColumnCount();
    for (int i = 0; i < size; i++) {
      // Test if the column has changed - teo_sarca [ 1704828 ]
      if (is_ValueChanged(i)) return true;
    }
    // there are custom columns modified
    return m_custom != null && m_custom.size() > 0;
  } //	is_Change

  /**
   * Called before Save for Pre-Save Operation
   *
   * @param newRecord new record
   * @return true if record can be saved
   */
  protected boolean beforeSave(boolean newRecord) {
    /**
     * Prevents saving log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@
     * = @C_Currency_ID@")); log.saveError("FillMandatory", Msg.getElement(getCtx(),
     * "PriceEntered")); /** Issues message log.saveWarning(AD_Message, message); log.saveInfo
     * (AD_Message, message);
     */
    return true;
  } //	beforeSave

  /**
   * Called after Save for Post-Save Operation
   *
   * @param newRecord new record
   * @param success true if save operation was success
   * @return if save was a success
   */
  protected boolean afterSave(boolean newRecord, boolean success) {
    return success;
  } //	afterSave

  protected boolean isLogSQLScript() {
    return false;
  }

  protected boolean isUseTimeoutForUpdate() {
    return "true".equalsIgnoreCase(System.getProperty(USE_TIMEOUT_FOR_UPDATE, "false"))
        && isQueryTimeoutSupported();
  }

  /**
   * Get ID for new record during save. You can overwrite this to explicitly set the ID
   *
   * @return ID to be used or 0 for default logic
   */
  protected int saveNew_getID() {
    int result = getId();
    if (result > 0 && result < 999999) // 2Pack assigns official ID's when importing
    return result;
    return 0;
  } //	saveNew_getID

  /**
   * Save Special Data. To be extended by sub-classes
   *
   * @param value value
   * @param index index
   * @return SQL code for INSERT VALUES clause
   */
  protected String saveNewSpecial(Object value, int index) {
    POInfo p_info = super.getP_info();
    String colName = p_info.getColumnName(index);
    String colClass = p_info.getColumnClass(index).toString();
    String colValue = value == null ? "null" : value.getClass().toString();
    //		int dt = p_info.getColumnDisplayType(index);

    log.log(
        Level.SEVERE,
        "Unknown class for column " + colName + " (" + colClass + ") - Value=" + colValue);

    if (value == null) return "NULL";
    return value.toString();
  } //  saveNewSpecial

  /**
   * Encrypt data. Not: LOB, special values/Objects
   *
   * @param index index
   * @param xx data
   * @return xx
   */
  protected Object encrypt(int index, Object xx) {
    if (xx == null) return null;
    POInfo p_info = super.getP_info();
    if (index != -1 && p_info.isEncrypted(index)) {
      return SecureEngine.encrypt(xx, getClientId());
    }
    return xx;
  } //	encrypt

  /**
   * Executed before Delete operation.
   *
   * @return true if record can be deleted
   */
  protected boolean beforeDelete() {
    //	log.saveError("Error", Msg.getMsg(getCtx(), "CannotDelete"));
    return true;
  } //	beforeDelete

  /**
   * Executed after Delete operation.
   *
   * @param success true if record deleted
   * @return true if delete is a success
   */
  protected boolean afterDelete(boolean success) {
    return success;
  } //	afterDelete

  /**
   * Delete Translation Records
   *
   * @param trxName transaction
   * @return false if error (true if no translation or success)
   */
  protected boolean deleteTranslations(String trxName) {
    POInfo p_info = super.getP_info();
    //	Not a translation table
    if (getIds().length > 1
        || getIds()[0].equals(I_ZERO)
        || !(getIds()[0] instanceof Integer)
        || !p_info.isTranslated()) return true;
    //
    String tableName = p_info.getTableName();
    String keyColumn = getM_keyColumns()[0];
    String sql = "DELETE  FROM  " + tableName + "_Trl WHERE " + keyColumn + "=" + getId();
    int no = executeUpdate(sql, trxName);
    if (log.isLoggable(Level.FINE)) log.fine("#" + no);
    return no >= 0;
  } //	deleteTranslations

  /**
   * Delete Accounting records. NOP - done by database constraints
   *
   * @param acctTable accounting sub table
   * @return true
   */
  @Deprecated // see IDEMPIERE-2088
  protected boolean delete_Accounting(String acctTable) {
    return true;
  } //	delete_Accounting

  /** Returns the summary node from C_ElementValue with the corresponding value */
  protected int retrieveIdOfElementValue(
      String value, int clientID, int elementID, String trxName) {
    String sql =
        "SELECT C_ElementValue_ID FROM C_ElementValue WHERE IsSummary='Y' AND AD_Client_ID=? AND C_Element_ID=? AND Value=?";
    int pos = value.length() - 1;
    while (pos > 0) {
      String testParentValue = value.substring(0, pos);
      int parentID = getSQLValueEx(trxName, sql, clientID, elementID, testParentValue);
      if (parentID > 0) return parentID;
      pos--;
    }
    return 0; // rootID
  }

  /**
   * ************************************************************************ Lock it.
   *
   * @return true if locked
   */
  public boolean lock() {
    int index = get_ProcessingIndex();
    POInfo p_info = super.getP_info();
    Object[] newValues = getNewValues();
    if (index != -1) {
      newValues[index] = Boolean.TRUE; // 	direct
      String sql =
          "UPDATE "
              + p_info.getTableName()
              + " SET Processing='Y' WHERE (Processing='N' OR Processing IS NULL) AND "
              + get_WhereClause(true);
      boolean success = false;
      if (isUseTimeoutForUpdate())
        success = executeUpdateEx(sql, null, QUERY_TIME_OUT) == 1; // 	outside trx
      else success = executeUpdate(sql, null) == 1; // 	outside trx
      if (success) log.fine("success");
      else log.log(Level.WARNING, "failed");
      return success;
    }
    return false;
  } //	lock

  /**
   * Get the Column Processing index
   *
   * @return index or -1
   */
  private int get_ProcessingIndex() {

    POInfo p_info = super.getP_info();
    return p_info.getColumnIndex("Processing");
  } //	getProcessingIndex

  /**
   * UnLock it
   *
   * @param trxName transaction
   * @return true if unlocked (false only if unlock fails)
   */
  public boolean unlock(String trxName) {
    //	log.warning(trxName);
    int index = get_ProcessingIndex();
    POInfo p_info = super.getP_info();
    Object[] newValues = getNewValues();
    if (index != -1) {
      newValues[index] = Boolean.FALSE; // 	direct
      String sql =
          "UPDATE " + p_info.getTableName() + " SET Processing='N' WHERE " + get_WhereClause(true);
      boolean success = false;
      if (isUseTimeoutForUpdate()) success = executeUpdateEx(sql, trxName, QUERY_TIME_OUT) == 1;
      else success = executeUpdate(sql, trxName) == 1;
      if (success) {
        if (log.isLoggable(Level.FINE))
          log.fine("success" + (trxName == null ? "" : "[" + trxName + "]"));
      } else {
        log.log(Level.WARNING, "failed" + (trxName == null ? "" : " [" + trxName + "]"));
      }
      return success;
    }
    return true;
  } //	unlock

  /**
   * Get Trx
   *
   * @return transaction
   */
  public String get_TrxName() {
    return m_trxName;
  } //	getTrx

  /**
   * Set Trx
   *
   * @param trxName transaction
   */
  public void set_TrxName(String trxName) {
    m_trxName = trxName;
  } //	setTrx

  /** ********************************************************************** Dump Record */
  public void dump() {
    if (CLogMgt.isLevelFinest()) {
      log.finer(get_WhereClause(true));
      for (int i = 0; i < get_ColumnCount(); i++) dump(i);
    }
  } //  dump

  /**
   * Dump column
   *
   * @param index index
   */
  public void dump(int index) {
    StringBuilder sb = new StringBuilder(" ").append(index);
    if (index < 0 || index >= get_ColumnCount()) {
      if (log.isLoggable(Level.FINEST)) log.finest(sb.append(": invalid").toString());
      return;
    }
    Object[] newValues = getNewValues();
    sb.append(": ")
        .append(get_ColumnName(index))
        .append(" = ")
        .append(getOldValues()[index])
        .append(" (")
        .append(newValues[index])
        .append(")");
    if (log.isLoggable(Level.FINEST)) log.finest(sb.toString());
  } //  dump

  /** Reset LOB info */
  protected void lobReset() {
    m_lobInfo = null;
  } //	resetLOB

  /**
   * Prepare LOB save
   *
   * @param value value
   * @param index index
   * @param displayType display type
   */
  protected void lobAdd(Object value, int index, int displayType) {
    if (log.isLoggable(Level.FINEST)) log.finest("Value=" + value);
    POInfo p_info = super.getP_info();
    PO_LOB lob =
        new PO_LOB(
            p_info.getTableName(),
            get_ColumnName(index),
            get_WhereClause(true),
            displayType,
            value);
    if (m_lobInfo == null) m_lobInfo = new ArrayList<PO_LOB>();
    m_lobInfo.add(lob);
  } //	lobAdd

  /**
   * Save LOB
   *
   * @return true if saved or ok
   */
  protected boolean lobSave() {
    if (m_lobInfo == null) return true;
    boolean retValue = true;
    for (int i = 0; i < m_lobInfo.size(); i++) {
      PO_LOB lob = m_lobInfo.get(i);
      if (!lob.save(get_TrxName())) {
        retValue = false;
        break;
      }
    } //	for all LOBs
    lobReset();
    return retValue;
  } //	saveLOB

  /**
   * Get Object xml representation as string
   *
   * @param xml optional string buffer
   * @return updated/new string buffer header is only added once
   */
  public StringBuffer get_xmlString(StringBuffer xml) {
    if (xml == null) xml = new StringBuffer();
    else xml.append(Env.NL);
    //
    try {
      StringWriter writer = new StringWriter();
      StreamResult result = new StreamResult(writer);
      DOMSource source = new DOMSource(get_xmlDocument(xml.length() != 0));
      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer transformer = tFactory.newTransformer();
      transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
      transformer.transform(source, result);
      StringBuffer newXML = writer.getBuffer();
      //
      if (xml.length() != 0) { // 	//	<?xml version="1.0" encoding="UTF-8"?>
        int tagIndex = newXML.indexOf("?>");
        if (tagIndex != -1) xml.append(newXML.substring(tagIndex + 2));
        else xml.append(newXML);
      } else xml.append(newXML);
    } catch (Exception e) {
      log.log(Level.SEVERE, "", e);
    }
    return xml;
  } //	get_xmlString

  /**
   * Get XML Document representation
   *
   * @param noComment do not add comment
   * @return XML document
   */
  public Document get_xmlDocument(boolean noComment) {
    Document document = null;
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      document = builder.newDocument();
      /*if (!noComment)
      document.appendChild(document.createComment(Adempiere.getI().getSummaryAscii())); DAP */
    } catch (Exception e) {
      log.log(Level.SEVERE, "", e);
    }
    //	Root
    Element root = document.createElement(get_TableName());
    root.setAttribute(XML_ATTRIBUTE_AD_Table_ID, String.valueOf(getTableId()));
    root.setAttribute(XML_ATTRIBUTE_Record_ID, String.valueOf(getId()));
    document.appendChild(root);
    //	Columns
    int size = get_ColumnCount();
    POInfo p_info = super.getP_info();
    for (int i = 0; i < size; i++) {
      if (p_info.isVirtualColumn(i)) continue;

      Element col = document.createElement(p_info.getColumnName(i));
      //
      Object value = get_Value(i);
      //	Display Type
      int dt = p_info.getColumnDisplayType(i);
      //  Based on class of definition, not class of value
      Class<?> c = p_info.getColumnClass(i);
      if (value == null || value.equals(Null.NULL)) ;
      else if (c == Object.class) col.appendChild(document.createCDATASection(value.toString()));
      else if (value instanceof Integer || value instanceof BigDecimal)
        col.appendChild(document.createTextNode(value.toString()));
      else if (c == Boolean.class) {
        boolean bValue = false;
        if (value instanceof Boolean) bValue = (Boolean) value;
        else bValue = "Y".equals(value);
        col.appendChild(document.createTextNode(bValue ? "Y" : "N"));
      } else if (value instanceof Timestamp)
        col.appendChild(document.createTextNode(value.toString()));
      else if (c == String.class) col.appendChild(document.createCDATASection((String) value));
      else if (DisplayType.isLOB(dt))
        col.appendChild(document.createCDATASection(value.toString()));
      else col.appendChild(document.createCDATASection(value.toString()));
      //
      root.appendChild(col);
    }
    //	Custom Columns
    if (m_custom != null) {
      Iterator<String> it = m_custom.keySet().iterator();
      while (it.hasNext()) {
        String columnName = it.next();
        //				int index = p_info.getColumnIndex(columnName);
        String value = m_custom.get(columnName);
        //
        Element col = document.createElement(columnName);
        if (value != null) col.appendChild(document.createTextNode(value));
        root.appendChild(col);
      }
      m_custom = null;
    }
    return document;
  } //	getDocument

  public boolean isReplication() {
    return m_isReplication;
  }

  public void setReplication(boolean isFromReplication) {
    m_isReplication = isFromReplication;
  }

  /**
   * Get Integer Value
   *
   * @param columnName
   * @return int value
   */
  public int get_ValueAsInt(String columnName) {
    int idx = get_ColumnIndex(columnName);
    if (idx < 0) {
      return 0;
    }
    return get_ValueAsInt(idx);
  }

  /**
   * Get value as Boolean
   *
   * @param columnName
   * @return boolean value
   */
  public boolean get_ValueAsBoolean(String columnName) {
    Object oo = get_Value(columnName);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /** @return uuid column name */
  public String getUUIDColumnName() {
    return PO.getUUIDColumnName(get_TableName());
  }

  /*
  @Override
  protected Object clone() throws CloneNotSupportedException {
    PO clone = (PO) super.clone();
    clone.m_trxName = null;
    if (m_custom != null) {
      clone.m_custom = new HashMap<String, String>();
      clone.m_custom.putAll(m_custom);
    }
    Object[] newValues = getNewValues();
    if (newValues != null) {
      clone.newValues = new Object[newValues.length];
      for (int i = 0; i < newValues.length; i++) {
        clone.newValues[i] = newValues[i];
      }
    }
    if (getOldValues() != null) {
      for (int i = 0; i < getOldValues().length; i++) {
        clone.getOldValues()[i] = getOldValues()[i];
      }
    }
    if (getIds() != null) {
      for (int i = 0; i < getIds().length; i++) {
        clone.getIds()[i] = getIds()[i];
      }
    }
    clone.m_lobInfo = null;
    clone.m_isReplication = false;
    return clone;
  }*/

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
    // default deserialization
    ois.defaultReadObject();
    log = CLogger.getCLogger(getClass());
  }

  public void set_Attribute(String columnName, Object value) {
    if (m_attributes == null) m_attributes = new HashMap<String, Object>();
    m_attributes.put(columnName, value);
  }

  public Object get_Attribute(String columnName) {
    if (m_attributes != null) return m_attributes.get(columnName);
    return null;
  }

  public HashMap<String, Object> get_Attributes() {
    return m_attributes;
  }

  protected void checkValidContext() {
    if (getCtx().isEmpty() && getCtx().getProperty("#AD_Client_ID") == null)
      throw new AdempiereException("Context lost");
  }

  /**
   * Set Value if updateable and correct class. (and to NULL if not mandatory)
   *
   * @param index index
   * @param value value
   * @param checkWritable
   * @return true if value set
   */
  protected boolean set_Value(int index, Object value, boolean checkWritable) {
    if (index < 0 || index >= get_ColumnCount()) {
      log.log(Level.WARNING, "Index invalid - " + index);
      return false;
    }
    POInfo p_info = super.getP_info();
    String ColumnName = p_info.getColumnName(index);
    String colInfo = " - " + ColumnName;
    //
    m_setErrors[index] = null;
    if (checkWritable) {
      if (p_info.isVirtualColumn(index)) {
        log.log(Level.WARNING, "Virtual Column" + colInfo);
        log.saveError("VirtualColumn", "Virtual Column" + colInfo);
        m_setErrors[index] = new ValueNamePair("VirtualColumn", "Virtual Column" + colInfo);
        return false;
      }

      //
      // globalqss -- Bug 1618469 - is throwing not updateable even on new records
      // if (!p_info.isColumnUpdateable(index))
      if ((!p_info.isColumnUpdateable(index)) && (!is_new())) {
        colInfo += " - NewValue=" + value + " - OldValue=" + get_Value(index);
        log.log(Level.WARNING, "Column not updateable" + colInfo);
        log.saveError("ColumnReadonly", "Column not updateable" + colInfo);
        m_setErrors[index] = new ValueNamePair("ColumnReadonly", "Column not updateable" + colInfo);
        return false;
      }
    }
    //
    Object[] newValues = getNewValues();
    if (value == null) {
      if (checkWritable && p_info.isColumnMandatory(index)) {
        log.saveError("FillMandatory", ColumnName + " is mandatory.");
        m_setErrors[index] = new ValueNamePair("FillMandatory", ColumnName + " is mandatory.");
        return false;
      }
      newValues[index] = Null.NULL; //  correct
      if (log.isLoggable(Level.FINER)) log.finer(ColumnName + " = null");
    } else {
      //  matching class or generic object
      if (value.getClass().equals(p_info.getColumnClass(index))
          || p_info.getColumnClass(index) == Object.class) newValues[index] = value; //  correct
      //  Integer can be set as BigDecimal
      else if (value.getClass() == BigDecimal.class
          && p_info.getColumnClass(index) == Integer.class)
        newValues[index] = ((BigDecimal) value).intValue();
      //	Set Boolean
      else if (p_info.getColumnClass(index) == Boolean.class
          && ("Y".equals(value) || "N".equals(value))) newValues[index] = "Y".equals(value);
      // added by vpj-cd
      // To solve BUG [ 1618423 ] Set Project Type button in Project window throws warning
      // generated because C_Project.C_Project_Type_ID is defined as button in dictionary
      // although is ID (integer) in database
      else if (value.getClass() == Integer.class && p_info.getColumnClass(index) == String.class)
        newValues[index] = value;
      else if (value.getClass() == String.class && p_info.getColumnClass(index) == Integer.class)
        try {
          newValues[index] = Integer.valueOf((String) value);
        } catch (NumberFormatException e) {
          String errmsg =
              ColumnName
                  + " - Class invalid: "
                  + value.getClass().toString()
                  + ", Should be "
                  + p_info.getColumnClass(index).toString()
                  + ": "
                  + value;
          log.log(Level.SEVERE, errmsg);
          log.saveError("WrongDataType", errmsg);
          m_setErrors[index] = new ValueNamePair("WrongDataType", errmsg);
          return false;
        }
      else {
        String errmsg =
            ColumnName
                + " - Class invalid: "
                + value.getClass().toString()
                + ", Should be "
                + p_info.getColumnClass(index).toString()
                + ": "
                + value;
        log.log(Level.SEVERE, errmsg);
        log.saveError("WrongDataType", errmsg);
        m_setErrors[index] = new ValueNamePair("WrongDataType", errmsg);
        return false;
      }
      //	Validate (Min/Max)
      String error = p_info.validate(index, value);
      if (error != null) {
        log.log(Level.WARNING, ColumnName + "=" + value + " - " + error);
        int separatorIndex = error.indexOf(";");
        if (separatorIndex > 0) {
          log.saveError(error.substring(0, separatorIndex), error.substring(separatorIndex + 1));
          m_setErrors[index] =
              new ValueNamePair(
                  error.substring(0, separatorIndex), error.substring(separatorIndex + 1));
        } else {
          log.saveError(error, ColumnName);
          m_setErrors[index] = new ValueNamePair(error, ColumnName);
        }
        return false;
      }
      //	Length for String
      if (p_info.getColumnClass(index) == String.class) {
        String stringValue = value.toString();
        int length = p_info.getFieldLength(index);
        if (stringValue.length() > length && length > 0) {
          log.warning(ColumnName + " - Value too long - truncated to length=" + length);
          newValues[index] = stringValue.substring(0, length);
        }
      }
      if (log.isLoggable(Level.FINEST))
        log.finest(
            ColumnName + " = " + newValues[index] + " (OldValue=" + getOldValues()[index] + ")");
    }
    set_Keys(ColumnName, newValues[index]);

    return true;
  } //  setValue

  /**
   * ************************************************************************ Set Value
   *
   * @param ColumnName column name
   * @param value value
   * @param checkWritable
   * @return true if value set
   */
  protected final boolean set_Value(String ColumnName, Object value, boolean checkWritable) {
    if (value instanceof String
        && ColumnName.equals("WhereClause")
        && value.toString().toUpperCase().contains("=NULL"))
      log.warning("Invalid Null Value - " + ColumnName + "=" + value);

    int index = get_ColumnIndex(ColumnName);
    if (index < 0) {
      log.log(Level.SEVERE, "Column not found - " + ColumnName);
      log.saveError("ColumnNotFound", "Column not found - " + ColumnName);
      return false;
    }
    if (ColumnName.endsWith("_ID") && value instanceof String) {
      // Convert to Integer only if info class is Integer - teo_sarca [ 2859125 ]
      POInfo p_info = super.getP_info();
      Class<?> clazz = p_info.getColumnClass(p_info.getColumnIndex(ColumnName));
      if (Integer.class == clazz) {
        log.severe("Invalid Data Type for " + ColumnName + "=" + value);
        value = Integer.parseInt((String) value);
      }
    }

    return set_Value(index, value, checkWritable);
  } //  setValue

  /**
   * ************************************************************************ Set Value
   *
   * @param ColumnName column name
   * @param value value
   * @return true if value set
   */
  protected boolean set_Value(String ColumnName, Object value) {
    return set_Value(ColumnName, value, true);
  }

  /**
   * Set Active
   *
   * @param active active
   */
  public final void setIsActive(boolean active) {
    set_Value("IsActive", active);
  } //	setActive

  /**
   * Set AD_Org
   *
   * @param AD_Org_ID org
   */
  public void setAD_Org_ID(int AD_Org_ID) {
    set_ValueNoCheck("AD_Org_ID", AD_Org_ID);
  } //	setAD_Org_ID

  /**
   * Set Value w/o check (update, r/o, ..). Used when Column is R/O Required for key and parent
   * values
   *
   * @param ColumnName column name
   * @param value value
   * @return true if value set
   */
  public final boolean set_ValueNoCheck(String ColumnName, Object value) {
    return set_Value(ColumnName, value, false);
  } //  set_ValueNoCheck

  /*
   * Classes which override save() method:
   * org.compiere.process.DocActionTemplate
   * MClient
   * MClientInfo
   * MSystem
   */

  /**
   * ************************************************************************ Update Value or create
   * new record. To reload call load() - not updated
   *
   * @return true if saved
   */
  public boolean save() {
    checkValidContext();
    CLogger.resetLast();
    POInfo p_info = super.getP_info();
    boolean newRecord = is_new(); // 	save locally as load resets
    if (!newRecord && !is_Changed()) {
      if (log.isLoggable(Level.FINE)) log.fine("Nothing changed - " + p_info.getTableName());
      return true;
    }

    for (int i = 0; i < m_setErrors.length; i++) {
      ValueNamePair setError = m_setErrors[i];
      if (setError != null) {
        log.saveError(
            setError.getValue(),
            Msg.getElement(getCtx(), p_info.getColumnName(i)) + " - " + setError.getName());
        return false;
      }
    }

    //	Organization Check
    if (getOrgId() == 0 && (getAccessLevel() == ACCESSLEVEL_ORG)) {
      log.saveError("FillMandatory", Msg.getElement(getCtx(), "AD_Org_ID"));
      return false;
    }
    //	Should be Org 0
    if (getOrgId() != 0) {
      boolean reset = getAccessLevel() == ACCESSLEVEL_SYSTEM;
      if (!reset && false) // isOrgLevelOnly default is false
      {
        reset =
            getAccessLevel() == ACCESSLEVEL_CLIENT
                || getAccessLevel() == ACCESSLEVEL_SYSTEMCLIENT
                || getAccessLevel() == ACCESSLEVEL_ALL
                || getAccessLevel() == ACCESSLEVEL_CLIENTORG;
      }
      if (reset) {
        log.warning("Set Org to 0");
        setAD_Org_ID(0);
      }
    }

    Trx localTrx = null;
    Trx trx = null;
    Savepoint savepoint = null;
    if (m_trxName == null) {
      StringBuilder l_trxname = new StringBuilder(LOCAL_TRX_PREFIX).append(get_TableName());
      if (l_trxname.length() > 23) l_trxname.setLength(23);
      m_trxName = Trx.createTrxName(l_trxname.toString());
      localTrx = Trx.get(m_trxName, true);
      localTrx.setDisplayName(getClass().getName() + "_save");
      localTrx.getConnection();
    } else {
      trx = Trx.get(m_trxName, false);
      if (trx == null) {
        // Using a trx that was previously closed or never opened
        // Creating and starting the transaction right here, but please note
        // that this is not a good practice
        trx = Trx.get(m_trxName, true);
        log.severe(
            "Transaction closed or never opened ("
                + m_trxName
                + ") => starting now --> "
                + toString());
      }
    }

    //	Before Save
    try {
      // If not a localTrx we need to set a savepoint for rollback
      if (localTrx == null) savepoint = trx.setSavepoint(null);

      if (!beforeSave(newRecord)) {
        log.warning("beforeSave failed - " + toString());
        if (localTrx != null) {
          localTrx.rollback();
          localTrx.close();
          m_trxName = null;
        } else {
          trx.rollback(savepoint);
          savepoint = null;
        }
        return false;
      }
    } catch (Exception e) {
      log.log(Level.WARNING, "beforeSave - " + toString(), e);
      String msg = org.idempiere.common.exceptions.DBException.getDefaultDBExceptionMessage(e);
      log.saveError(msg != null ? msg : "Error", e, false);
      if (localTrx != null) {
        localTrx.rollback();
        localTrx.close();
        m_trxName = null;
      } else if (savepoint != null) {
        try {
          trx.rollback(savepoint);
        } catch (SQLException e1) {
        }
        savepoint = null;
      }
      return false;
    }

    try {
      //	Save
      if (newRecord) {
        boolean b = saveNew();
        if (b) {
          if (localTrx != null) return localTrx.commit();
          else return b;
        } else {
          if (localTrx != null) localTrx.rollback();
          else trx.rollback(savepoint);
          return b;
        }
      } else {
        boolean b = saveUpdate();
        if (b) {
          if (localTrx != null) return localTrx.commit();
          else return b;
        } else {
          if (localTrx != null) localTrx.rollback();
          else trx.rollback(savepoint);
          return b;
        }
      }
    } catch (Exception e) {
      log.log(Level.WARNING, "afterSave - " + toString(), e);
      String msg = DBException.getDefaultDBExceptionMessage(e);
      log.saveError(msg != null ? msg : "Error", e);
      if (localTrx != null) {
        localTrx.rollback();
      } else if (savepoint != null) {
        try {
          trx.rollback(savepoint);
        } catch (SQLException e1) {
        }
        savepoint = null;
      }
      return false;
    } finally {
      if (localTrx != null) {
        localTrx.close();
        m_trxName = null;
      } else {
        if (savepoint != null) {
          try {
            trx.releaseSavepoint(savepoint);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
        savepoint = null;
        trx = null;
      }
    }
  }

  protected boolean saveNew() {
    POInfo p_info = super.getP_info();
    //  Set ID for single key - Multi-Key values need explicitly be set previously
    if (getIds().length == 1
        && p_info.getHasKeyColumn()
        && getM_keyColumns()[0].endsWith("_ID")) // 	AD_Language, EntityType
    {
      int no = saveNew_getID();
      if (no <= 0) throw new AdempiereException("no <= 0");
      // the primary key is not overwrite with the local sequence
      if (isReplication()) {
        if (getId() > 0) {
          no = getId();
        }
      }
      if (no <= 0) {
        log.severe("No NextID (" + no + ")");
        return saveFinish(true, false);
      }
      getIds()[0] = no;
      set_ValueNoCheck(getM_keyColumns()[0], getIds()[0]);
    }
    // uuid secondary key
    int uuidIndex = p_info.getColumnIndex(getUUIDColumnName());
    if (uuidIndex >= 0) {
      String value = (String) get_Value(uuidIndex);
      if (p_info.getColumn(uuidIndex).FieldLength == 36 && (value == null || value.length() == 0)) {
        UUID uuid = UUID.randomUUID();
        set_ValueNoCheck(p_info.getColumnName(uuidIndex), uuid.toString());
      }
    }
    if (m_trxName == null) {
      if (log.isLoggable(Level.FINE))
        log.fine(p_info.getTableName() + " - " + get_WhereClause(true));
    } else {
      if (log.isLoggable(Level.FINE))
        log.fine("[" + m_trxName + "] - " + p_info.getTableName() + " - " + get_WhereClause(true));
    }

    boolean ok = doInsert(isLogSQLScript());
    return saveFinish(true, ok);
  } //  saveNew

  /**
   * Finish Save Process
   *
   * @param newRecord new
   * @param success success
   * @return true if saved
   */
  protected boolean saveFinish(boolean newRecord, boolean success) {
    //
    try {
      success = afterSave(newRecord, success);
    } catch (Exception e) {
      log.log(Level.WARNING, "afterSave", e);
      log.saveError("Error", e, false);
      success = false;
      //	throw new DBException(e);
    }
    POInfo p_info = super.getP_info();
    //	OK
    if (success) {
      // post osgi event
      String topic = newRecord ? IEventTopics.PO_POST_CREATE : IEventTopics.PO_POST_UPADTE;
      IEvent event = EventManager.newEvent(topic, this);
      EventManager.getInstance().postEvent(event);

      if (s_docWFMgr == null) {
        try {
          Class.forName("org.compiere.wf.DocWorkflowManager");
        } catch (Exception e) {
        }
      }
      if (s_docWFMgr != null) s_docWFMgr.process(this, p_info.getAD_Table_ID());

      Object[] newValues = getNewValues();
      //	Copy to Old values
      int size = p_info.getColumnCount();
      for (int i = 0; i < size; i++) {
        if (newValues[i] != null) {
          if (newValues[i] == Null.NULL) getOldValues()[i] = null;
          else getOldValues()[i] = newValues[i];
        }
      }
      clearNewValues();
      setCreateNew(false);
    }
    if (!newRecord) CacheMgt.get().reset(p_info.getTableName());
    else if (getId() > 0 && success) CacheMgt.get().newRecord(p_info.getTableName(), getId());

    return success;
  } //	saveFinish

  /**
   * Update Record directly
   *
   * @return true if updated
   */
  protected boolean saveUpdate() {
    boolean ok = doUpdate(isLogSQLScript());

    return saveFinish(false, ok);
  } //  saveUpdate

  protected boolean doUpdate(boolean withValues) {
    // params for insert statement
    List<Object> params = new ArrayList<Object>();

    String where = get_WhereClause(true);
    //
    boolean changes = false;
    StringBuilder sql = new StringBuilder("UPDATE ");
    POInfo p_info = super.getP_info();
    sql.append(p_info.getTableName()).append(" SET ");
    boolean updated = false;
    boolean updatedBy = false;
    lobReset();

    int size = get_ColumnCount();
    Object[] newValues = getNewValues();
    for (int i = 0; i < size; i++) {
      Object value = newValues[i];
      if (value == null || p_info.isVirtualColumn(i)) continue;
      //  we have a change
      Class<?> c = p_info.getColumnClass(i);
      int dt = p_info.getColumnDisplayType(i);
      String columnName = p_info.getColumnName(i);
      //
      //	updated/by
      if (columnName.equals("UpdatedBy")) {
        if (updatedBy) //	explicit
        continue;
        updatedBy = true;
      } else if (columnName.equals("Updated")) {
        if (updated) continue;
        updated = true;
      }
      if (DisplayType.isLOB(dt)) {
        lobAdd(value, i, dt);
        //	If no changes set UpdatedBy explicitly to ensure commit of lob
        if (!changes && !updatedBy) {
          int AD_User_ID = Env.getContextAsInt(getCtx(), "#AD_User_ID");
          set_ValueNoCheck("UpdatedBy", AD_User_ID);
          sql.append("UpdatedBy=").append(AD_User_ID);
          changes = true;
          updatedBy = true;
        }
        continue;
      }
      //	Update Document No
      if (columnName.equals("DocumentNo")) {
        String strValue = (String) value;
        if (strValue.startsWith("<") && strValue.endsWith(">")) {
          value = null;
          int AD_Client_ID = getClientId();
          int index = p_info.getColumnIndex("C_DocTypeTarget_ID");
          if (index == -1) index = p_info.getColumnIndex("C_DocType_ID");
        } else if (log.isLoggable(Level.INFO))
          log.info("DocumentNo updated: " + getOldValues()[i] + " -> " + value);
      }

      if (changes) sql.append(", ");
      changes = true;
      sql.append(columnName).append("=");

      if (withValues) {
        //  values
        if (value == Null.NULL) sql.append("NULL");
        else if (value instanceof Integer || value instanceof BigDecimal) sql.append(value);
        else if (c == Boolean.class) {
          boolean bValue = false;
          if (value instanceof Boolean) bValue = (Boolean) value;
          else bValue = "Y".equals(value);
          sql.append(encrypt(i, bValue ? "'Y'" : "'N'"));
        } else if (value instanceof Timestamp)
          sql.append(
              TO_DATE(
                  (Timestamp) encrypt(i, value),
                  p_info.getColumnDisplayType(i) == DisplayType.Date));
        else {
          if (value.toString().length() == 0) {
            // [ 1722057 ] Encrypted columns throw error if saved as null
            // don't encrypt NULL
            sql.append(TO_STRING(value.toString()));
          } else {
            sql.append(encrypt(i, TO_STRING(value.toString())));
          }
        }
      } else {
        if (value instanceof Timestamp && dt == DisplayType.Date)
          sql.append("trunc(cast(? as date))");
        else sql.append("?");

        if (value == Null.NULL) {
          params.add(null);
        } else if (c == Boolean.class) {
          boolean bValue = false;
          if (value instanceof Boolean) bValue = (Boolean) value;
          else bValue = "Y".equals(value);
          params.add(encrypt(i, bValue ? "Y" : "N"));
        } else if (c == String.class) {
          if (value.toString().length() == 0) {
            // [ 1722057 ] Encrypted columns throw error if saved as null
            // don't encrypt NULL
            params.add(null);
          } else {
            params.add(encrypt(i, value));
          }
        } else {
          params.add(value);
        }
      }
    } //   for all fields

    //	Custom Columns (cannot be logged as no column)
    if (m_custom != null) {
      for (String s : m_custom.keySet()) {
        if (changes) sql.append(", ");
        changes = true;
        //
        String column = s;
        String value = m_custom.get(column);
        int index = p_info.getColumnIndex(column);
        if (withValues) {
          sql.append(column).append("=").append(encrypt(index, value));
        } else {
          sql.append(column).append("=?");
          if (value == null || value.length() == 0) {
            params.add(null);
          } else {
            params.add(encrypt(index, value));
          }
        }
      }
      m_custom = null;
    }

    //	Something changed
    if (changes) {
      if (m_trxName == null) {
        if (log.isLoggable(Level.FINE)) log.fine(p_info.getTableName() + "." + where);
      } else {
        if (log.isLoggable(Level.FINE))
          log.fine("[" + m_trxName + "] - " + p_info.getTableName() + "." + where);
      }
      if (!updated) //	Updated not explicitly set
      {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        set_ValueNoCheck("Updated", now);
        if (withValues) {
          sql.append(",Updated=").append(TO_DATE(now, false));
        } else {
          sql.append(",Updated=?");
          params.add(now);
        }
      }
      if (!updatedBy) //	UpdatedBy not explicitly set
      {
        int AD_User_ID = Env.getContextAsInt(getCtx(), "#AD_User_ID");
        set_ValueNoCheck("UpdatedBy", AD_User_ID);
        if (withValues) {
          sql.append(",UpdatedBy=").append(AD_User_ID);
        } else {
          sql.append(",UpdatedBy=?");
          params.add(AD_User_ID);
        }
      }
      sql.append(" WHERE ").append(where);
      /** @todo status locking goes here */
      if (log.isLoggable(Level.FINEST)) log.finest(sql.toString());
      int no = 0;
      if (isUseTimeoutForUpdate())
        no =
            withValues
                ? executeUpdateEx(sql.toString(), m_trxName, QUERY_TIME_OUT)
                : executeUpdateEx(sql.toString(), params, m_trxName, QUERY_TIME_OUT);
      else
        no =
            withValues
                ? executeUpdate(sql.toString(), m_trxName)
                : executeUpdate(sql.toString(), params, false, m_trxName);
      boolean ok = no == 1;
      if (ok) ok = lobSave();
      else {
        if (m_trxName == null)
          log.saveError(
              "SaveError",
              "Update return "
                  + no
                  + " instead of 1"
                  + " - "
                  + p_info.getTableName()
                  + "."
                  + where);
        else
          log.saveError(
              "SaveError",
              "Update return "
                  + no
                  + " instead of 1"
                  + " - ["
                  + m_trxName
                  + "] - "
                  + p_info.getTableName()
                  + "."
                  + where);
      }
      return ok;
    } else {
      // nothing changed, so OK
      return true;
    }
  }

  protected boolean doInsert(boolean withValues) {
    int index;
    lobReset();

    // params for insert statement
    List<Object> params = new ArrayList<Object>();
    POInfo p_info = super.getP_info();
    //	SQL
    StringBuilder sqlInsert = new StringBuilder("INSERT INTO ");
    sqlInsert.append(p_info.getTableName()).append(" (");
    StringBuilder sqlValues = new StringBuilder(") VALUES (");
    int size = get_ColumnCount();
    boolean doComma = false;
    for (int i = 0; i < size; i++) {
      Object value = get_Value(i);
      //	Don't insert NULL values (allows Database defaults)
      if (value == null || p_info.isVirtualColumn(i)) continue;

      //	Display Type
      int dt = p_info.getColumnDisplayType(i);
      if (DisplayType.isLOB(dt)) {
        lobAdd(value, i, dt);
        continue;
      }

      //	** add column **
      if (doComma) {
        sqlInsert.append(",");
        sqlValues.append(",");
      } else doComma = true;
      sqlInsert.append(p_info.getColumnName(i));
      //
      //  Based on class of definition, not class of value
      Class<?> c = p_info.getColumnClass(i);
      if (withValues) {
        try {
          if (c == Object.class) //  may have need to deal with null values differently
          sqlValues.append(saveNewSpecial(value, i));
          else if (value == null || value.equals(Null.NULL)) sqlValues.append("NULL");
          else if (value instanceof Integer || value instanceof BigDecimal) sqlValues.append(value);
          else if (c == Boolean.class) {
            boolean bValue = false;
            if (value instanceof Boolean) bValue = (Boolean) value;
            else bValue = "Y".equals(value);
            sqlValues.append(encrypt(i, bValue ? "'Y'" : "'N'"));
          } else if (value instanceof Timestamp)
            sqlValues.append(
                TO_DATE(
                    (Timestamp) encrypt(i, value),
                    p_info.getColumnDisplayType(i) == DisplayType.Date));
          else if (c == String.class) sqlValues.append(encrypt(i, TO_STRING((String) value)));
          else if (DisplayType.isLOB(dt)) sqlValues.append("null"); // 	no db dependent stuff here
          else sqlValues.append(saveNewSpecial(value, i));
        } catch (Exception e) {
          String msg = "";
          if (m_trxName != null) msg = "[" + m_trxName + "] - ";
          msg +=
              p_info.toString(i)
                  + " - Value="
                  + value
                  + "("
                  + (value == null ? "null" : value.getClass().getName())
                  + ")";
          log.log(Level.SEVERE, msg, e);
          throw new DBException(e); // 	fini
        }
      } else {
        if (value instanceof Timestamp && dt == DisplayType.Date)
          sqlValues.append("trunc(cast(? as date))");
        else sqlValues.append("?");

        if (DisplayType.isLOB(dt)) {
          params.add(null);
        } else if (value == null || value.equals(Null.NULL)) {
          params.add(null);
        } else if (c == Boolean.class) {
          boolean bValue = false;
          if (value instanceof Boolean) bValue = (Boolean) value;
          else bValue = "Y".equals(value);
          params.add(encrypt(i, bValue ? "Y" : "N"));
        } else if (c == String.class) {
          if (value.toString().length() == 0) {
            params.add(null);
          } else {
            params.add(encrypt(i, value));
          }
        } else {
          params.add(value);
        }
      }
    }
    //	Custom Columns
    if (m_custom != null) {
      for (String column : m_custom.keySet()) {
        index = p_info.getColumnIndex(column);
        String value = m_custom.get(column);
        if (value == null) continue;
        if (doComma) {
          sqlInsert.append(",");
          sqlValues.append(",");
        } else doComma = true;
        sqlInsert.append(column);
        if (withValues) {
          sqlValues.append(encrypt(index, value));
        } else {
          sqlValues.append("?");
          if (value == null || value.length() == 0) {
            params.add(null);
          } else {
            params.add(encrypt(index, value));
          }
        }
      }
      m_custom = null;
    }
    sqlInsert.append(sqlValues).append(")");
    //
    int no =
        withValues
            ? DB.Companion.getCurrent().run(queryOf(sqlInsert.toString()).getAsUpdate())
            : DB.Companion.getCurrent()
                .run(queryOf(sqlInsert.toString(), params.toArray()).getAsUpdate());
    boolean ok = no == 1;
    if (ok) {
      ok = lobSave();
      if (!load(m_trxName)) // 	re-read Info
      {
        if (m_trxName == null) log.log(Level.SEVERE, "reloading");
        else log.log(Level.SEVERE, "[" + m_trxName + "] - reloading");
        ok = false;
      }
    } else {
      String msg = "Not inserted - ";
      if (CLogMgt.isLevelFiner()) msg += sqlInsert.toString();
      else msg += get_TableName();
      if (m_trxName == null) log.log(Level.WARNING, msg);
      else log.log(Level.WARNING, "[" + m_trxName + "]" + msg);
    }
    return ok;
  }
} //  PO
