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
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.*;

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
 * <li>FR: [ 2214883 ] - to introduce .setClient_ID
 */
public class Query extends BaseQuery {
    public static final String AGGREGATE_COUNT = "COUNT";
    public static final String AGGREGATE_SUM = "SUM";

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
     * Limit current query rows return.
     */
    private int pageSize;

    /**
     * Number of pages will be skipped on query run.
     */
    private int pagesToSkip;

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
        String[] keys = table.getKeyColumns();
        if (keys.length != 1) {
            throw new DBException("Table " + table + " has 0 or more than 1 key columns");
        }

        StringBuilder selectClause = new StringBuilder("SELECT ");
        if (!joinClauseList.isEmpty()) selectClause.append(table.getTableName()).append(".");
        selectClause.append(keys[0]);
        selectClause.append(" FROM ").append(table.getTableName());
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
                        .append(table.getTableName());

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
        String sql = buildSQL(new StringBuilder("SELECT 1 FROM ").append(table.getTableName()), false);
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
     * Return an Iterator implementation to fetch one PO at a time. The implementation first retrieve
     * all IDS that match the query criteria and issue sql query to fetch the PO when caller want to
     * fetch the next PO. This minimize memory usage but it is slower than the list method.
     *
     * @return Iterator
     * @throws DBException
     */
    public <T extends PO> Iterator<T> iterate() throws DBException {
        MTable table = super.getTable();
        String[] keys = table.getKeyColumns();
        StringBuilder sqlBuffer = new StringBuilder(" SELECT ");
        for (int i = 0; i < keys.length; i++) {
            if (i > 0) sqlBuffer.append(", ");
            if (!joinClauseList.isEmpty()) sqlBuffer.append(table.getTableName()).append(".");
            sqlBuffer.append(keys[i]);
        }
        sqlBuffer.append(" FROM ").append(table.getTableName());
        String sql = buildSQL(sqlBuffer, true);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Object[]> idList = new ArrayList<Object[]>();
        try {
            pstmt = prepareStatement(sql);
            rs = createResultSet(pstmt);
            while (rs.next()) {
                Object[] ids = new Object[keys.length];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = rs.getObject(i + 1);
                }
                idList.add(ids);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, sql, e);
            throw new DBException(e, sql);
        } finally {
            rs = null;
            pstmt = null;
        }
        return new POIterator<T>(table, idList);
    }

    /**
     * Return a simple wrapper over a jdbc resultset. It is the caller responsibility to call the
     * close method to release the underlying database resources.
     *
     * @return POResultSet
     * @throws DBException
     */
    public <T extends PO> POResultSet<T> scroll() throws DBException {
        MTable table = super.getTable();
        String sql = buildSQL(null, true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        POResultSet<T> rsPO = null;
        try {
            pstmt = prepareStatement(sql);
            rs = createResultSet(pstmt);
            rsPO = new POResultSet<T>(table, pstmt, rs);
            rsPO.setCloseOnError(true);
            return rsPO;
        } catch (SQLException e) {
            log.log(Level.SEVERE, sql, e);
            throw new DBException(e, sql);
        } finally {
            // If there was an error, then close the statement and resultset
            if (rsPO == null) {
                rs = null;
                pstmt = null;
            }
        }
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
            POInfo info = POInfo.getPOInfo(this.getCtx(), table.getAD_Table_ID());
            if (info == null) {
                throw new IllegalStateException(
                        "No POInfo found for AD_Table_ID=" + table.getAD_Table_ID());
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
            if (!joinClauseList.isEmpty()) whereBuffer.append(table.getTableName()).append(".");
            whereBuffer.append("IsActive=?");
        }
        boolean onlyClient_ID = super.getOnlyClient_ID();
        if (onlyClient_ID) // red1
        {
            if (whereBuffer.length() > 0) whereBuffer.append(" AND ");
            if (!joinClauseList.isEmpty()) whereBuffer.append(table.getTableName()).append(".");
            whereBuffer.append("AD_Client_ID=?");
        }
        if (this.onlySelection_ID > 0) {
            String[] keys = table.getKeyColumns();
            if (keys.length != 1) {
                throw new DBException("Table " + table + " has 0 or more than 1 key columns");
            }
            //
            if (whereBuffer.length() > 0) whereBuffer.append(" AND ");
            whereBuffer.append(
                    " EXISTS (SELECT 1 FROM T_Selection s WHERE s.AD_PInstance_ID=?"
                            + " AND s.T_Selection_ID="
                            + table.getTableName()
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
                            sql, table.getTableName(), applyAccessFilterFullyQualified, applyAccessFilterRW);
        }
        if (forUpdate) {
            sql = sql + " FOR UPDATE";
            if (isPostgreSQL()) sql = sql + " OF " + table.getTableName();
        }

        // If have pagination
        if (pageSize > 0) {
            sql = appendPagination(sql);
        }

        if (log.isLoggable(Level.FINEST))
            log.finest(
                    "TableName = "
                            + table.getTableName()
                            + "... SQL = "
                            + sql); // red1 - to assist in debugging SQL

        return sql;
    }

    /**
     * If top is bigger than 0 set the pagination on query
     *
     * @param query    SQL String
     * @param pageSize number
     * @param skip     number
     */
    private String appendPagination(String pQuery) {

        String query = pQuery;

        if (pageSize > 0) {
            if (isPagingSupported()) {
                query = addPagingSQL(query, (pageSize * pagesToSkip) + 1, pageSize * (pagesToSkip + 1));
            } else {
                throw new IllegalArgumentException("Pagination not supported by database");
            }
        }

        return query;
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
        boolean onlyClient_ID = super.getOnlyClient_ID();
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
