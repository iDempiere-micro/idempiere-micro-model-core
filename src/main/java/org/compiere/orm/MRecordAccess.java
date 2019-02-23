package org.compiere.orm;

import org.compiere.util.Msg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
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
     * TableName
     */
    private String m_tableName;

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MRecordAccess(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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
        PreparedStatement pstmt = null;
        ResultSet rs = null;
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
        } finally {
            rs = null;
            pstmt = null;
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
    public String getKeyColumnName(AccessSqlParser.TableInfo[] tableInfo) {
        String columnSyn = getSynonym();
        if (columnSyn == null) return m_keyColumnName;
        //	We have a synonym - ignore it if base table inquired
        for (int i = 0; i < tableInfo.length; i++) {
            if (m_keyColumnName.equals("AD_User_ID")) {
                //	List of tables where not to use SalesRep_ID
                if (tableInfo[i].getTableName().equals("AD_User")) return m_keyColumnName;
            } else if (m_keyColumnName.equals("AD_ElementValue_ID")) {
                //	List of tables where not to use Account_ID
                if (tableInfo[i].getTableName().equals("AD_ElementValue")) return m_keyColumnName;
            }
        } //	tables to be ignored
        return columnSyn;
    } //	getKeyColumnInfo

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuffer sb =
                new StringBuffer("MRecordAccess[AD_Role_ID=")
                        .append(getRoleId())
                        .append(",AD_Table_ID=")
                        .append(getRecordTableId())
                        .append(",Record_ID=")
                        .append(getRecordId())
                        .append(",Active=")
                        .append(isActive())
                        .append(",Exclude=")
                        .append(isExclude())
                        .append(",ReadOnly=")
                        .append(super.isReadOnly())
                        .append(",Dependent=")
                        .append(isDependentEntities())
                        .append("]");
        return sb.toString();
    } //	toString

    /**
     * Get Table Name
     *
     * @param ctx context
     * @return table name
     */
    public String getTableName(Properties ctx) {
        if (m_tableName == null) {
            String sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                pstmt = prepareStatement(sql);
                pstmt.setInt(1, getRecordTableId());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    m_tableName = rs.getString(1);
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, sql, e);
            } finally {
                rs = null;
                pstmt = null;
            }
            //	Get Clear Text
            String realName = Msg.translate(ctx, m_tableName + "_ID");
            if (!realName.equals(m_tableName + "_ID")) m_tableName = realName;
        }
        return m_tableName;
    } //	getTableName
} //	MRecordAccess
