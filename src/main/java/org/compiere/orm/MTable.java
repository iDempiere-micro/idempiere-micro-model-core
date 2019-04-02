package org.compiere.orm;

import kotliquery.Row;
import org.idempiere.common.util.CLogger;
import software.hsharp.core.orm.MBaseTable;
import software.hsharp.core.orm.MBaseTableKt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static software.hsharp.core.orm.MBaseTableKt.getFactoryList;
import static software.hsharp.core.orm.MBaseTableKt.getTableCache;
import static software.hsharp.core.util.DBKt.prepareStatement;

/**
 * Persistent Table Model
 *
 * <p>Change log:
 *
 * <ul>
 * <li>2007-02-01 - teo_sarca - [ 1648850 ] MTable.getClass works incorrect for table "Fact_Acct"
 * </ul>
 *
 * <ul>
 * <li>2007-08-30 - vpj-cd - [ 1784588 ] Use ModelPackage of EntityType to Find Model Class
 * </ul>
 *
 * @author Jorg Janke
 * @author Teo Sarca, teo.sarca@gmail.com
 * <li>BF [ 3017117 ] MTable.getClass returns bad class
 * https://sourceforge.net/tracker/?func=detail&aid=3017117&group_id=176962&atid=879332
 * @version $Id: MTable.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public class MTable extends MBaseTable {
    /**
     *
     */
    private static final long serialVersionUID = -8757836873040013402L;
    /**
     * Static Logger
     */
    private static CLogger s_log = CLogger.getCLogger(MTable.class);
    /**
     * View Components
     */
    private MViewComponent[] m_viewComponents = null;

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param AD_Table_ID id
     */
    public MTable(int AD_Table_ID) {
        super(AD_Table_ID);
        if (AD_Table_ID == 0) {
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
     */
    public MTable(Row row) {
        super(row);
    }

    /**
     * Get Table from Cache
     *
     * @param AD_Table_ID id
     * @return MTable
     */
    public static MTable get(int AD_Table_ID) {
        Integer key = AD_Table_ID;
        MTable retValue = getTableCache().get(key);
        if (retValue != null) {
            return retValue;
        }
        retValue = new MTable(AD_Table_ID);
        if (retValue.getId() != 0) {
            getTableCache().put(key, retValue);
        }
        return retValue;
    } //	get

    /**
     * Get Table from Cache
     *
     * @param tableName case insensitive table name
     * @return Table
     */
    public static MTable get(String tableName) {
        if (tableName == null) return null;
        return MBaseTableKt.get(tableName);
    } //	get

    /**
     * Get Table Name
     *
     * @param AD_Table_ID table
     * @return tavle name
     */
    public static String getDbTableName(int AD_Table_ID) {
        return MTable.get(AD_Table_ID).getDbTableName();
    } //	getTableName

    /**
     * Grant independence to GenerateModel from AD_Table_ID
     *
     * @return int retValue
     */
    public static int getTableId(String tableName) {
        int retValue = 0;
        String SQL = "SELECT AD_Table_ID FROM AD_Table WHERE tablename = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = prepareStatement(SQL);
            pstmt.setString(1, tableName);
            rs = pstmt.executeQuery();
            if (rs.next()) retValue = rs.getInt(1);
        } catch (Exception e) {
            s_log.log(Level.SEVERE, SQL, e);
            retValue = -1;
        } finally {
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
    public synchronized int getDbColumnIndex(String ColumnName) {
        MColumn[] m_columns = super.getColumns();
        if (m_columns == null) getColumns(false);
        Integer i = getColumnNameMap().get(ColumnName.toUpperCase());
        if (i != null) return i.intValue();

        return -1;
    } //  getColumnIndex

    /**
     * Table has a single Key
     *
     * @return true if table has single key column
     */
    public boolean isSingleKey() {
        String[] keys = getTableKeyColumns();
        return keys.length == 1;
    } //	isSingleKey

    /**
     * Get Key Columns of Table
     *
     * @return key columns
     */
    public String[] getTableKeyColumns() {
        MColumn[] m_columns = getColumns(false);
        ArrayList<String> list = new ArrayList<String>();
        //
        for (int i = 0; i < m_columns.length; i++) {
            MColumn column = m_columns[i];
            if (column.isKey()) return new String[]{column.getColumnName()};
            if (column.isParent()) list.add(column.getColumnName());
        }
        String[] retValue = new String[list.size()];
        retValue = list.toArray(retValue);
        return retValue;
    } //	getKeyColumns

    /**
     * ************************************************************************ Get PO Class Instance
     *
     * @param Record_ID record
     * @return PO for Record or null
     */
    public org.idempiere.orm.PO getPO(int Record_ID) {
        if (Record_ID == 0) {
            return null;
        }

        String tableName = getDbTableName();
        if (Record_ID != 0 && !isSingleKey()) {
            log.log(Level.WARNING, "(id) - Multi-Key " + tableName);
            return null;
        }

        org.idempiere.orm.PO po = null;
        IModelFactory[] factoryList = getFactoryList();
        if (factoryList != null) {
            for (IModelFactory factory : factoryList) {
                po = factory.getPO(tableName, Record_ID);
                if (po != null) {
                    if (po.getId() != Record_ID && Record_ID > 0) po = null;
                    else break;
                }
            }
        }

        if (po == null) {
            po = new GenericPO(tableName, Record_ID);
            if (po.getId() != Record_ID && Record_ID > 0) po = null;
        }

        return po;
    } //	getPO

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    protected boolean beforeSave(boolean newRecord) {
        if (isView() && isDeletable()) setIsDeleteable(false);
        //
        return true;
    } //	beforeSave

    // globalqss

    /**
     * After Save
     *
     * @param newRecord new
     * @param success   success
     * @return success
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        if (!success) return success;
        //	Sync Table ID
        MSequence seq = MSequence.get(getDbTableName());
        if (seq == null || seq.getId() == 0)
            MSequence.createTableSequence(getDbTableName());
        else if (!seq.getName().equals(getDbTableName())) {
            seq.setName(getDbTableName());
            seq.saveEx();
        }

        return success;
    } //	afterSave

    /**
     * Create query to retrieve one or more PO.
     *
     * @param whereClause
     * @return Query
     */
    public Query createQuery(String whereClause) {
        return new Query(this, whereClause);
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
                        MViewComponent.Table_Name,
                        MViewComponent.COLUMNNAME_AD_Table_ID + "=?"
                );
        query.setParameters(getTableTableId());
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
        sb.append(getId()).append("-").append(getDbTableName()).append("]");
        return sb.toString();
    } //	toString
} //	MTable
