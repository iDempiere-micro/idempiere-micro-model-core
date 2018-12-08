package org.compiere.orm;

import static software.hsharp.core.orm.MBaseTableKt.getFactoryList;
import static software.hsharp.core.orm.MBaseTableKt.getTableCache;
import static software.hsharp.core.util.DBKt.close;
import static software.hsharp.core.util.DBKt.prepareStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.logging.Level;
import kotliquery.Row;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.POInfo;
import software.hsharp.core.orm.MBaseTable;
import software.hsharp.core.orm.MBaseTableKt;

/**
 * Persistent Table Model
 *
 * <p>Change log:
 *
 * <ul>
 *   <li>2007-02-01 - teo_sarca - [ 1648850 ] MTable.getClass works incorrect for table "Fact_Acct"
 * </ul>
 *
 * <ul>
 *   <li>2007-08-30 - vpj-cd - [ 1784588 ] Use ModelPackage of EntityType to Find Model Class
 * </ul>
 *
 * @author Jorg Janke
 * @author Teo Sarca, teo.sarca@gmail.com
 *     <li>BF [ 3017117 ] MTable.getClass returns bad class
 *         https://sourceforge.net/tracker/?func=detail&aid=3017117&group_id=176962&atid=879332
 * @version $Id: MTable.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public class MTable extends MBaseTable {
  public static final int MAX_OFFICIAL_ID = 999999;
  /** */
  private static final long serialVersionUID = -8757836873040013402L;
  /** Static Logger */
  private static CLogger s_log = CLogger.getCLogger(MTable.class);
  /** View Components */
  private MViewComponent[] m_viewComponents = null;

  /**
   * ************************************************************************ Standard Constructor
   *
   * @param ctx context
   * @param AD_Table_ID id
   * @param trxName transaction
   */
  public MTable(Properties ctx, int AD_Table_ID, String trxName) {
    super(ctx, AD_Table_ID, trxName);
    if (AD_Table_ID == 0) {
      //	setName (null);
      //	setTableName (null);
      setTableAccessLevel(X_AD_Table.ACCESSLEVEL_SystemOnly); // 4
      setEntityType(org.idempiere.orm.PO.ENTITYTYPE_UserMaintained); // U
      setIsChangeLog(false);
      setIsDeleteable(false);
      setIsHighVolume(false);
      setIsSecurityEnabled(false);
      setIsView(false); // N
      setReplicationType(X_AD_Table.REPLICATIONTYPE_Local);
    }
  } //	MTable

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MTable(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } //	MTable

  public MTable(Properties ctx, Row row) {
    super(ctx, row);
  }

  /**
   * Get Table from Cache
   *
   * @param ctx context
   * @param AD_Table_ID id
   * @return MTable
   */
  public static MTable get(Properties ctx, int AD_Table_ID) {
    return get(ctx, AD_Table_ID, null);
  } //	get

  /**
   * Get Table from Cache
   *
   * @param ctx context
   * @param AD_Table_ID id
   * @param trxName transaction
   * @return MTable
   */
  public static MTable get(Properties ctx, int AD_Table_ID, String trxName) {
    Integer key = Integer.valueOf(AD_Table_ID);
    MTable retValue = getTableCache().get(key);
    if (retValue != null && retValue.getCtx() == ctx) {
      if (trxName != null) retValue.set_TrxName(trxName);
      return retValue;
    }
    retValue = new MTable(ctx, AD_Table_ID, trxName);
    if (retValue.getId() != 0) {
      getTableCache().put(key, retValue);
    }
    return retValue;
  } //	get

  /**
   * Get Table from Cache
   *
   * @param ctx context
   * @param tableName case insensitive table name
   * @return Table
   */
  public static MTable get(Properties ctx, String tableName) {
    if (tableName == null) return null;
    return MBaseTableKt.get(ctx, tableName);
  } //	get

  /**
   * Get Table Name
   *
   * @param ctx context
   * @param AD_Table_ID table
   * @return tavle name
   */
  public static String getTableName(Properties ctx, int AD_Table_ID) {
    return MTable.get(ctx, AD_Table_ID).getTableName();
  } //	getTableName

  /**
   * Get Persistence Class for Table
   *
   * @param tableName table name
   * @return class or null
   */
  public static Class<?> getClass(String tableName) {
    IModelFactory[] factoryList = getFactoryList();
    if (factoryList == null) return null;
    for (IModelFactory factory : factoryList) {
      Class<?> clazz = factory.getClass(tableName);
      if (clazz != null) return clazz;
    }
    return null;
  } //	getClass

  /**
   * Grant independence to GenerateModel from AD_Table_ID
   *
   * @param String tableName
   * @return int retValue
   */
  public static int getTable_ID(String tableName) {
    int retValue = 0;
    String SQL = "SELECT AD_Table_ID FROM AD_Table WHERE tablename = ?";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = prepareStatement(SQL, null);
      pstmt.setString(1, tableName);
      rs = pstmt.executeQuery();
      if (rs.next()) retValue = rs.getInt(1);
    } catch (Exception e) {
      s_log.log(Level.SEVERE, SQL, e);
      retValue = -1;
    } finally {
      close(rs, pstmt);
      rs = null;
      pstmt = null;
    }
    return retValue;
  }

  /**
   * Verify if the table contains ID=0
   *
   * @return true if table has zero ID
   */
  public static boolean isZeroIDTable(String tablename) {
    return (tablename.equals("AD_Org")
        || tablename.equals("AD_OrgInfo")
        || tablename.equals("AD_Client")
        || // IDEMPIERE-668
        tablename.equals("AD_ReportView")
        || tablename.equals("AD_Role")
        || tablename.equals("AD_System")
        || tablename.equals("AD_User")
        || tablename.equals("C_DocType")
        || tablename.equals("GL_Category")
        || tablename.equals("M_AttributeSet")
        || tablename.equals("M_AttributeSetInstance"));
  }

  /**
   * Get Column
   *
   * @param columnName (case insensitive)
   * @return column if found
   */
  public MColumn getColumn(String columnName) {
    if (columnName == null || columnName.length() == 0) return null;
    MColumn[] m_columns = getColumns(false);
    //
    for (int i = 0; i < m_columns.length; i++) {
      if (columnName.equalsIgnoreCase(m_columns[i].getColumnName())) return m_columns[i];
    }
    return null;
  } //	getColumn

  /**
   * Get Column Index
   *
   * @param ColumnName column name
   * @return index of column with ColumnName or -1 if not found
   */
  public synchronized int getColumnIndex(String ColumnName) {
    MColumn[] m_columns = super.getM_columns();
    if (m_columns == null) getColumns(false);
    Integer i = getM_columnNameMap().get(ColumnName.toUpperCase());
    if (i != null) return i.intValue();

    return -1;
  } //  getColumnIndex

  /**
   * Get Column Index
   *
   * @param AD_Column_ID column
   * @return index of column with ColumnName or -1 if not found
   */
  public synchronized int getColumnIndex(int AD_Column_ID) {
    MColumn[] m_columns = super.getM_columns();
    if (m_columns == null) getColumns(false);
    Integer i = getM_columnIdMap().get(AD_Column_ID);
    if (i != null) return i.intValue();

    return -1;
  } //  getColumnIndex

  /**
   * Table has a single Key
   *
   * @return true if table has single key column
   */
  public boolean isSingleKey() {
    String[] keys = getKeyColumns();
    return keys.length == 1;
  } //	isSingleKey

  /**
   * Get Key Columns of Table
   *
   * @return key columns
   */
  public String[] getKeyColumns() {
    MColumn[] m_columns = getColumns(false);
    ArrayList<String> list = new ArrayList<String>();
    //
    for (int i = 0; i < m_columns.length; i++) {
      MColumn column = m_columns[i];
      if (column.isKey()) return new String[] {column.getColumnName()};
      if (column.isParent()) list.add(column.getColumnName());
    }
    String[] retValue = new String[list.size()];
    retValue = list.toArray(retValue);
    return retValue;
  } //	getKeyColumns

  /**
   * Get Identifier Columns of Table
   *
   * @return Identifier columns
   */
  public String[] getIdentifierColumns() {
    ArrayList<KeyNamePair> listkn = new ArrayList<KeyNamePair>();
    for (MColumn column : getColumns(false)) {
      if (column.isIdentifier())
        listkn.add(new KeyNamePair(column.getSeqNo(), column.getColumnName()));
    }
    // Order by SeqNo
    Collections.sort(
        listkn,
        new Comparator<KeyNamePair>() {
          public int compare(KeyNamePair s1, KeyNamePair s2) {
            if (s1.getKey() < s2.getKey()) return -1;
            else if (s1.getKey() > s2.getKey()) return 1;
            else return 0;
          }
        });
    String[] retValue = new String[listkn.size()];
    for (int i = 0; i < listkn.size(); i++) {
      retValue[i] = listkn.get(i).getName();
    }
    return retValue;
  } //	getIdentifierColumns

  /**
   * ************************************************************************ Get PO Class Instance
   *
   * @param Record_ID record
   * @param trxName
   * @return PO for Record or null
   */
  public org.idempiere.orm.PO getPO(int Record_ID, String trxName) {
    if (Record_ID == 0) {
      return null;
    }

    String tableName = getTableName();
    if (Record_ID != 0 && !isSingleKey()) {
      log.log(Level.WARNING, "(id) - Multi-Key " + tableName);
      return null;
    }

    org.idempiere.orm.PO po = null;
    IModelFactory[] factoryList = getFactoryList();
    if (factoryList != null) {
      for (IModelFactory factory : factoryList) {
        po = factory.getPO(tableName, Record_ID, trxName);
        if (po != null) {
          if (po.getId() != Record_ID && Record_ID > 0) po = null;
          else break;
        }
      }
    }

    if (po == null) {
      po = new GenericPO(tableName, getCtx(), Record_ID, trxName);
      if (po.getId() != Record_ID && Record_ID > 0) po = null;
    }

    return po;
  } //	getPO

  /**
   * Get PO Class Instance
   *
   * @param rs result set
   * @param trxName transaction
   * @return PO for Record or null
   */
  public org.idempiere.orm.PO getPO(ResultSet rs, String trxName) {
    String tableName = getTableName();

    org.idempiere.orm.PO po = null;
    IModelFactory[] factoryList = getFactoryList();
    if (factoryList != null) {
      for (IModelFactory factory : factoryList) {
        po = factory.getPO(tableName, rs, trxName);
        if (po != null) break;
      }
    }

    if (po == null) {
      po = new GenericPO(tableName, getCtx(), rs, trxName);
    }

    return po;
  } //	getPO

  /**
   * Get PO Class Instance
   *
   * @param whereClause where clause
   * @param trxName transaction
   * @return PO for Record or null
   */
  public PO getPO(String whereClause, String trxName) {
    return (PO) getPO(whereClause, null, trxName);
  } //	getPO

  /**
   * Get PO class instance
   *
   * @param whereClause
   * @param params
   * @param trxName
   * @return
   */
  public org.idempiere.orm.PO getPO(String whereClause, Object[] params, String trxName) {
    if (whereClause == null || whereClause.length() == 0) return null;
    //
    org.idempiere.orm.PO po = null;
    POInfo info = POInfo.getPOInfo(getCtx(), getAD_Table_ID(), trxName);
    if (info == null) return null;
    StringBuilder sqlBuffer = info.buildSelect();
    sqlBuffer.append(" WHERE ").append(whereClause);
    String sql = sqlBuffer.toString();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = prepareStatement(sql, trxName);
      if (params != null && params.length > 0) {
        for (int i = 0; i < params.length; i++) {
          pstmt.setObject(i + 1, params[i]);
        }
      }
      rs = pstmt.executeQuery();
      if (rs.next()) {
        po = getPO(rs, trxName);
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, sql, e);
      log.saveError("Error", e);
    } finally {
      close(rs, pstmt);
      rs = null;
      pstmt = null;
    }

    return po;
  }

  /**
   * Before Save
   *
   * @param newRecord new
   * @return true
   */
  protected boolean beforeSave(boolean newRecord) {
    if (isView() && isDeleteable()) setIsDeleteable(false);
    //
    return true;
  } //	beforeSave

  // globalqss

  /**
   * After Save
   *
   * @param newRecord new
   * @param success success
   * @return success
   */
  protected boolean afterSave(boolean newRecord, boolean success) {
    if (!success) return success;
    //	Sync Table ID
    MSequence seq = MSequence.get(getCtx(), getTableName(), get_TrxName());
    if (seq == null || seq.getId() == 0)
      MSequence.createTableSequence(getCtx(), getTableName(), get_TrxName());
    else if (!seq.getName().equals(getTableName())) {
      seq.setName(getTableName());
      seq.saveEx();
    }

    return success;
  } //	afterSave

  /**
   * Get SQL Create
   *
   * @return create table DDL
   */
  public String getSQLCreate() {
    StringBuffer sb = new StringBuffer("CREATE TABLE ").append(getTableName()).append(" (");
    //
    // boolean hasPK = false;
    // boolean hasParents = false;
    StringBuffer constraints = new StringBuffer();
    MColumn[] m_columns = getColumns(true);
    boolean columnAdded = false;
    for (int i = 0; i < m_columns.length; i++) {
      MColumn column = m_columns[i];
      String colSQL = column.getSQLDDL();
      if (colSQL != null) {
        if (columnAdded) sb.append(", ");
        else columnAdded = true;
        sb.append(column.getSQLDDL());
      } else // virtual column
      continue;
      //
      // if (column.isKey())
      //	hasPK = true;
      // if (column.isParent())
      //	hasParents = true;
      String constraint = column.getConstraint(getTableName());
      if (constraint != null && constraint.length() > 0)
        constraints.append(", ").append(constraint);
    }
    /* IDEMPIERE-1901 - deprecate code that create composite primary key
    //	Multi Column PK
    if (!hasPK && hasParents)
    {
    	StringBuffer cols = new StringBuffer();
    	for (int i = 0; i < columns.length; i++)
    	{
    		MColumn column = columns[i];
    		if (!column.isParent())
    			continue;
    		if (cols.length() > 0)
    			cols.append(", ");
    		cols.append(column.getColumnName());
    	}
    	sb.append(", CONSTRAINT ")
    		.append(getTableName()).append("_Key PRIMARY KEY (")
    		.append(cols).append(")");
    }
    */

    sb.append(constraints).append(")");
    return sb.toString();
  } //	getSQLCreate

  /**
   * Create query to retrieve one or more PO.
   *
   * @param whereClause
   * @param trxName
   * @return Query
   */
  public Query createQuery(String whereClause, String trxName) {
    return new Query(this.getCtx(), this, whereClause, trxName);
  }

  /**
   * Get view components
   *
   * @param reload reload data
   * @return array of view component
   */
  public MViewComponent[] getViewComponent(boolean reload) {
    if (!isView() || !isActive()) return null;

    if (m_viewComponents != null && !reload) return m_viewComponents;

    Query query =
        new Query(
            getCtx(),
            MViewComponent.Table_Name,
            MViewComponent.COLUMNNAME_AD_Table_ID + "=?",
            get_TrxName());
    query.setParameters(getAD_Table_ID());
    query.setOrderBy(MViewComponent.COLUMNNAME_SeqNo);
    query.setOnlyActiveRecords(true);
    List<MTableIndex> list = query.list();

    m_viewComponents = new MViewComponent[list.size()];
    list.toArray(m_viewComponents);
    return m_viewComponents;
  }

  /**
   * String Representation
   *
   * @return info
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("MTable[");
    sb.append(getId()).append("-").append(getTableName()).append("]");
    return sb.toString();
  } //	toString
} //	MTable
