package org.compiere.orm;

import org.idempiere.common.exceptions.DBException;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Util;
import org.idempiere.orm.POInfo;
import software.hsharp.core.orm.BaseQuery;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.isPostgreSQL;
import static software.hsharp.core.util.DBKt.prepareStatement;
import static software.hsharp.core.util.DBKt.setParameter;

/**
 * @author Low Heng Sin
 * @author Teo Sarca, www.arhipac.ro
 * <li>FR [ 1981760 ] Improve Query class
 * <li>BF [ 2030280 ] Query apply access filter issue
 * <li>FR [ 2041894 ] Add Query.match() method
 * <li>FR [ 2107068 ] Query.setOrderBy should be more error tolerant
 * <li>FR [ 2107109 ] Add method Query.setOnlyActiveRecords
 * <li>FR [ 2421313 ] Introduce Query.firstOnly convenient method
 * <li>FR [ 2546052 ] Introduce Query aggregate methods
 * <li>FR [ 2726447 ] Query aggregate methods for all return types
 * <li>FR [ 2818547 ] Implement Query.setOnlySelection
 * https://sourceforge.net/tracker/?func=detail&aid=2818547&group_id=176962&atid=879335
 * <li>FR [ 2818646 ] Implement Query.firstId/firstIdOnly
 * https://sourceforge.net/tracker/?func=detail&aid=2818646&group_id=176962&atid=879335
 * @author Redhuan D. Oon
 * <li>FR: [ 2214883 ] Remove SQL code and Replace for Query // introducing SQL String prompt in
 * log.info
 * <li>FR: [ 2214883 ] - to introduce .setClientId
 */
public class Query extends BaseQuery {
    public static final String AGGREGATE_COUNT = "COUNT";

    private static CLogger log = CLogger.getCLogger(Query.class);

    private String whereClause = null;
    private String orderBy = null;
    private boolean applyAccessFilter = false;
    private boolean applyAccessFilterRW = false;
    private boolean applyAccessFilterFullyQualified = true;
    private int onlySelection_ID = -1;
    private boolean forUpdate = false;
    private boolean noVirtualColumn = false;
    private int queryTimeout = 0;
    private List<String> joinClauseList = new ArrayList<String>();

    /**
     * @param table
     * @param whereClause
     * @deprecated Use {@link #Query(Properties, MTable, String)} instead because this method
     * is security error prone
     */
    public Query(MTable table, String whereClause) {
        super(table.getCtx(), table);
        this.whereClause = whereClause;
    }

    /**
     * @param ctx         context
     * @param table
     * @param whereClause
     */
    public Query(Properties ctx, MTable table, String whereClause) {
        super(ctx, table);
        this.whereClause = whereClause;
    }

    /**
     * @param ctx
     * @param tableName
     * @param whereClause
     */
    public Query(Properties ctx, String tableName, String whereClause) {
        this(ctx, MTable.get(ctx, tableName), whereClause);
        MTable table = super.getTable();
        if (table == null) throw new IllegalArgumentException("Table Name Not Found - " + tableName);
    }

    /**
     * Set order by clause. If the string starts with "ORDER BY" then "ORDER BY" keywords will be
     * discarded.
     *
     * @param orderBy SQL ORDER BY clause
     */
    public Query setOrderBy(String orderBy) {
        this.orderBy = orderBy != null ? orderBy.trim() : null;
        if (this.orderBy != null && this.orderBy.toUpperCase().startsWith("ORDER BY")) {
            this.orderBy = this.orderBy.substring(8);
        }
        return this;
    }

    /**
     * Turn on data access filter with controls
     *
     * @param flag
     */
    public Query setApplyAccessFilter(boolean fullyQualified, boolean RW) {
        this.applyAccessFilter = true;
        this.applyAccessFilterFullyQualified = fullyQualified;
        this.applyAccessFilterRW = RW;
        return this;
    }

    /**
     * Add FOR UPDATE clause
     *
     * @param forUpdate
     */
    public Query setForUpdate(boolean forUpdate) {
        this.forUpdate = forUpdate;
        return this;
    }

    public Query setQueryTimeout(int seconds) {
        this.queryTimeout = seconds;
        return this;
    }

    public Query addJoinClause(String joinClause) {
        joinClauseList.add(joinClause);
        return this;
    }

    /**
     * Return first ID
     *
     * @return first ID or -1 if not found
     * @throws DBException
     */
    public int firstId() throws DBException {
        return firstId(false);
    }

    private int firstId(boolean assumeOnlyOneResult) throws DBException {
        MTable table = super.getTable();
        String[] keys = table.getTableKeyColumns();
        if (keys.length != 1) {
            throw new DBException("Table " + table + " has 0 or more than 1 key columns");
        }

        StringBuilder selectClause = new StringBuilder("SELECT ");
        if (!joinClauseList.isEmpty()) selectClause.append(table.getDbTableName()).append(".");
        selectClause.append(keys[0]);
        selectClause.append(" FROM ").append(table.getDbTableName());
        String sql = buildSQL(selectClause, true);

        int id = -1;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = prepareStatement(sql);
            rs = createResultSet(pstmt);
            if (rs.next()) {
                id = rs.getInt(1);
            }
            if (assumeOnlyOneResult && rs.next()) {
                throw new DBException("QueryMoreThanOneRecordsFound"); // TODO : translate
            }
        } catch (SQLException e) {
            throw new DBException(e, sql);
        } finally {
            rs = null;
            pstmt = null;
        }
        //
        return id;
    }

    /**
     * Aggregate given expression on this criteria
     *
     * @param sqlExpression
     * @param sqlFunction
     * @return aggregated value
     * @throws DBException
     */
    public BigDecimal aggregate(String sqlExpression, String sqlFunction) throws DBException {
        return aggregate(sqlExpression, sqlFunction, BigDecimal.class);
    }

    /**
     * Aggregate given expression on this criteria
     *
     * @param <T>
     * @param sqlExpression
     * @param sqlFunction
     * @param returnType
     * @return aggregated value
     * @throws DBException
     */
    @SuppressWarnings("unchecked")
    public <T> T aggregate(String sqlExpression, String sqlFunction, Class<T> returnType)
            throws DBException {
        if (Util.isEmpty(sqlFunction, true)) {
            throw new DBException("No Aggregate Function defined");
        }
        if (Util.isEmpty(sqlExpression, true)) {
            if (AGGREGATE_COUNT == sqlFunction) {
                sqlExpression = "*";
            } else {
                throw new DBException("No Expression defined");
            }
        }
        MTable table = super.getTable();

        StringBuilder sqlSelect =
                new StringBuilder("SELECT ")
                        .append(sqlFunction)
                        .append("(")
                        .append(sqlExpression)
                        .append(")")
                        .append(" FROM ")
                        .append(table.getDbTableName());

        T value = null;
        T defaultValue = null;

        String sql = buildSQL(sqlSelect, false);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = prepareStatement(sql);
            rs = createResultSet(pstmt);
            if (rs.next()) {
                if (returnType.isAssignableFrom(BigDecimal.class)) {
                    value = (T) rs.getBigDecimal(1);
                    defaultValue = (T) Env.ZERO;
                } else if (returnType.isAssignableFrom(Double.class)) {
                    value = (T) Double.valueOf(rs.getDouble(1));
                    defaultValue = (T) Double.valueOf(0.00);
                } else if (returnType.isAssignableFrom(Integer.class)) {
                    value = (T) Integer.valueOf(rs.getInt(1));
                    defaultValue = (T) Integer.valueOf(0);
                } else if (returnType.isAssignableFrom(Timestamp.class)) {
                    value = (T) rs.getTimestamp(1);
                } else if (returnType.isAssignableFrom(Boolean.class)) {
                    value = (T) Boolean.valueOf("Y".equals(rs.getString(1)));
                    defaultValue = (T) Boolean.FALSE;
                } else {
                    value = (T) rs.getObject(1);
                }
            }
            if (rs.next()) {
                throw new DBException("QueryMoreThanOneRecordsFound"); // TODO : translate
            }
        } catch (SQLException e) {
            throw new DBException(e, sql);
        } finally {
            rs = null;
            pstmt = null;
        }
        //
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    /**
     * Count items that match query criteria
     *
     * @return count
     * @throws DBException
     */
    public int count() throws DBException {
        return aggregate("*", AGGREGATE_COUNT).intValue();
    }

    /**
     * Check if there items for query criteria
     *
     * @return true if exists, false otherwise
     * @throws DBException
     */
    public boolean match() throws DBException {
        MTable table = super.getTable();
        String sql = buildSQL(new StringBuilder("SELECT 1 FROM ").append(table.getDbTableName()), false);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = prepareStatement(sql);
            rs = createResultSet(pstmt);
            if (rs.next()) return true;
        } catch (SQLException e) {
            throw new DBException(e, sql);
        } finally {
        }
        return false;
    }

    /**
     * Build SQL Clause
     *
     * @param selectClause optional; if null the select clause will be build according to POInfo
     * @return final SQL
     */
    protected final String buildSQL(StringBuilder selectClause, boolean useOrderByClause) {
        MTable table = super.getTable();
        if (selectClause == null) {
            POInfo info = POInfo.getPOInfo(this.getCtx(), table.getTableTableId());
            if (info == null) {
                throw new IllegalStateException(
                        "No POInfo found for AD_Table_ID=" + table.getTableTableId());
            }
            selectClause = info.buildSelect(!joinClauseList.isEmpty(), noVirtualColumn);
        }
        if (!joinClauseList.isEmpty()) {
            for (String joinClause : joinClauseList) {
                selectClause.append(" ").append(joinClause);
            }
        }

        StringBuilder whereBuffer = new StringBuilder();
        if (!Util.isEmpty(this.whereClause, true)) {
            if (whereBuffer.length() > 0) whereBuffer.append(" AND ");
            whereBuffer.append("(").append(this.whereClause).append(")");
        }
        boolean onlyActiveRecords = super.getOnlyActiveRecords();
        if (onlyActiveRecords) {
            if (whereBuffer.length() > 0) whereBuffer.append(" AND ");
            if (!joinClauseList.isEmpty()) whereBuffer.append(table.getDbTableName()).append(".");
            whereBuffer.append("IsActive=?");
        }
        boolean onlyClient_ID = super.getOnlyClientId();
        if (onlyClient_ID) // red1
        {
            if (whereBuffer.length() > 0) whereBuffer.append(" AND ");
            if (!joinClauseList.isEmpty()) whereBuffer.append(table.getDbTableName()).append(".");
            whereBuffer.append("AD_Client_ID=?");
        }
        if (this.onlySelection_ID > 0) {
            String[] keys = table.getTableKeyColumns();
            if (keys.length != 1) {
                throw new DBException("Table " + table + " has 0 or more than 1 key columns");
            }
            //
            if (whereBuffer.length() > 0) whereBuffer.append(" AND ");
            whereBuffer.append(
                    " EXISTS (SELECT 1 FROM T_Selection s WHERE s.AD_PInstance_ID=?"
                            + " AND s.T_Selection_ID="
                            + table.getDbTableName()
                            + "."
                            + keys[0]
                            + ")");
        }

        StringBuilder sqlBuffer = new StringBuilder(selectClause);
        if (whereBuffer.length() > 0) {
            sqlBuffer.append(" WHERE ").append(whereBuffer);
        }
        if (useOrderByClause && !Util.isEmpty(orderBy, true)) {
            sqlBuffer.append(" ORDER BY ").append(orderBy);
        }
        String sql = sqlBuffer.toString();
        if (applyAccessFilter) {
            MRole role = MRole.getDefault(this.getCtx(), false);
            sql =
                    role.addAccessSQL(
                            sql, table.getDbTableName(), applyAccessFilterFullyQualified, applyAccessFilterRW);
        }
        if (forUpdate) {
            sql = sql + " FOR UPDATE";
            if (isPostgreSQL()) sql = sql + " OF " + table.getDbTableName();
        }

        // TODO If have pagination

        if (log.isLoggable(Level.FINEST))
            log.finest(
                    "TableName = "
                            + table.getDbTableName()
                            + "... SQL = "
                            + sql); // red1 - to assist in debugging SQL

        return sql;
    }

    private final ResultSet createResultSet(PreparedStatement pstmt) throws SQLException {
        Object[] parameters = super.getParameters();
        setParameters(pstmt, parameters);
        int i = 1 + (parameters != null ? parameters.length : 0);

        boolean onlyActiveRecords = super.getOnlyActiveRecords();
        if (onlyActiveRecords) {
            setParameter(pstmt, i++, true);
            if (log.isLoggable(Level.FINEST)) log.finest("Parameter IsActive = Y");
        }
        boolean onlyClient_ID = super.getOnlyClientId();
        if (onlyClient_ID) {
            int AD_Client_ID = Env.getClientId(this.getCtx());
            setParameter(pstmt, i++, AD_Client_ID);
            if (log.isLoggable(Level.FINEST)) log.finest("Parameter AD_Client_ID = " + AD_Client_ID);
        }
        if (this.onlySelection_ID > 0) {
            setParameter(pstmt, i++, this.onlySelection_ID);
            if (log.isLoggable(Level.FINEST))
                log.finest("Parameter Selection AD_PInstance_ID = " + this.onlySelection_ID);
        }
        if (queryTimeout > 0) {
            pstmt.setQueryTimeout(queryTimeout);
        }
        return pstmt.executeQuery();
    }

}
