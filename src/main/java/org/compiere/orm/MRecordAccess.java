package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.SqlTableInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.prepareStatement;

/**
 * Record Access Model
 *
 * @author Jorg Janke
 * @version $Id: MRecordAccess.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MRecordAccess extends X_AD_Record_Access {
    /**
     *
     */
    private static final long serialVersionUID = -5115765616266528435L;
    //	Key Column Name			*/
    private String m_keyColumnName = null;

    /**
     * Load Constructor
     *
     */
    public MRecordAccess(Row row) {
        super(row);
    } //	MRecordAccess

    /**
     * Get Key Column Name
     *
     * @return Key Column Name
     */
    public String getKeyColumnName() {
        if (m_keyColumnName != null) return m_keyColumnName;
        //
        String sql =
                "SELECT ColumnName "
                        + "FROM AD_Column "
                        + "WHERE AD_Table_ID=? AND IsKey='Y' AND IsActive='Y'";
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            pstmt = prepareStatement(sql);
            pstmt.setInt(1, getRecordTableId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String s = rs.getString(1);
                if (m_keyColumnName == null) m_keyColumnName = s;
                else log.log(Level.SEVERE, "More than one key = " + s);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, sql, e);
        }
        if (m_keyColumnName == null)
            log.log(Level.SEVERE, "Record Access requires Table with one key column");
        //
        return m_keyColumnName;
    } //	getKeyColumnName

    /**
     * Get Synonym of Column
     *
     * @return Synonym Column Name
     */
    public String getSynonym() {
        if ("AD_User_ID".equals(getKeyColumnName())) return "SalesRep_ID";
        else if ("C_ElementValue_ID".equals(getKeyColumnName())) return "Account_ID";
        //
        return null;
    } //	getSynonym

    /**
     * Get Key Column Name with consideration of Synonym
     *
     * @param tableInfo
     * @return key column name
     */
    public String getKeyColumnName(SqlTableInfo[] tableInfo) {
        String columnSyn = getSynonym();
        if (columnSyn == null) return m_keyColumnName;
        //	We have a synonym - ignore it if base table inquired
        for (SqlTableInfo info : tableInfo) {
            //	List of tables where not to use Account_ID
            if ("AD_User_ID".equals(m_keyColumnName)) {
                //	List of tables where not to use SalesRep_ID
                if ("AD_User".equals(info.getTableName())) return m_keyColumnName;
            } else if ("AD_ElementValue_ID".equals(m_keyColumnName) && "AD_ElementValue".equals(info.getTableName()))
                return m_keyColumnName;
        } //	tables to be ignored
        return columnSyn;
    } //	getKeyColumnInfo

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        return "MRecordAccess[AD_Role_ID=" +
                getRoleId() +
                ",AD_Table_ID=" +
                getRecordTableId() +
                ",Record_ID=" +
                getRecordId() +
                ",Active=" +
                isActive() +
                ",Exclude=" +
                isExclude() +
                ",ReadOnly=" +
                super.isReadOnly() +
                ",Dependent=" +
                isDependentEntities() +
                "]";
    } //	toString

} //	MRecordAccess
