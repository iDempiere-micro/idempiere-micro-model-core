package org.compiere.orm;

import kotliquery.Row;
import org.compiere.util.Msg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.prepareStatement;

/**
 * Column Access Model
 *
 * @author Jorg Janke
 * @version $Id: MColumnAccess.java,v 1.3 2006/07/30 00:54:54 jjanke Exp $
 */
public class MColumnAccess extends X_AD_Column_Access {
    /**
     *
     */
    private static final long serialVersionUID = -2362624234744824977L;
    /**
     * TableName
     */
    private String m_tableName;
    /**
     * ColumnName
     */
    private String m_columnName;

    /**
     * Persistency Constructor
     *
     * @param ctx     context
     * @param ignored ignored
     * @param trxName transaction
     */
    public MColumnAccess(Properties ctx, int ignored) {
        super(ctx, 0);
        if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
    } //	MColumnAccess

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MColumnAccess(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    } //	MColumnAccess

    public MColumnAccess(Properties ctx, Row row) {
        super(ctx, row);
    } //	MColumnAccess

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("MColumnAccess[");
        sb.append("AD_Role_ID=")
                .append(getRoleId())
                .append(",AD_Table_ID=")
                .append(getColumnTableId())
                .append(",AD_Column_ID=")
                .append(getColumnId())
                .append(",Exclude=")
                .append(isExclude());
        sb.append("]");
        return sb.toString();
    } //	toString

    /**
     * Get Table Name
     *
     * @param ctx context for translatioin
     * @return table name
     */
    public String getTableName(Properties ctx) {
        if (m_tableName == null) getColumnName(ctx);
        return m_tableName;
    } //	getTableName

    /**
     * Get Column Name
     *
     * @param ctx context for translatioin
     * @return column name
     */
    public String getColumnName(Properties ctx) {
        if (m_columnName == null) {
            String sql =
                    "SELECT t.TableName,c.ColumnName, t.AD_Table_ID "
                            + "FROM AD_Table t INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID) "
                            + "WHERE AD_Column_ID=?";
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                pstmt = prepareStatement(sql);
                pstmt.setInt(1, getColumnId());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    m_tableName = rs.getString(1);
                    m_columnName = rs.getString(2);
                    if (rs.getInt(3) != getColumnTableId())
                        log.log(
                                Level.SEVERE,
                                "AD_Table_ID inconsistent - Access="
                                        + getColumnTableId()
                                        + " - Table="
                                        + rs.getInt(3));
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, sql, e);
            } finally {
                rs = null;
                pstmt = null;
            }
            //	Get Clear Text
            StringBuilder msgrn = new StringBuilder(m_tableName).append("_ID");
            String realName = Msg.translate(ctx, msgrn.toString());
            if (!realName.equals(msgrn.toString())) m_tableName = realName;
            m_columnName = Msg.translate(ctx, m_columnName);
        }
        return m_columnName;
    } //	getColumnName
} //	MColumnAccess
