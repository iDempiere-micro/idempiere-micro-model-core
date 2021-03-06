package org.idempiere.common.exceptions;

import org.idempiere.common.util.CLogMgt;

import java.sql.SQLException;

import static software.hsharp.core.util.DBKt.isPostgreSQL;

/**
 * This RuntimeException is used to pass SQLException up the chain of calling methods to determine
 * what to do where needed.
 *
 * @author Vincent Harcq
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * @author Armen Rizal, GOODWILL CONSULTING FR [2789943] Better DBException handling for PostgreSQL
 * https://sourceforge.net/tracker/?func=detail&aid=2789943&group_id=176962&atid=879335
 * @version $Id: DBException.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 */
public class DBException extends AdempiereException {
    public static final String DATABASE_OPERATION_TIMEOUT_MSG = "DatabaseOperationTimeout";
    public static final String DELETE_ERROR_DEPENDENT_MSG = "DeleteErrorDependent";
    public static final String SAVE_ERROR_NOT_UNIQUE_MSG = "SaveErrorNotUnique";
    /**
     *
     */
    private static final long serialVersionUID = 4264201718343118625L;

    /**
     * Create a new DBException based on a SQLException
     *
     * @param e Specicy the Exception cause
     */
    public DBException(Exception e) {
        super(e);
        if (CLogMgt.isLevelFinest()) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new DBException
     *
     * @param msg Message
     */
    public DBException(String msg) {
        super(msg);
    }

    private static final boolean isErrorCode(Exception e, int errorCode) {
        if (e == null) {
            return false;
        } else if (e instanceof SQLException) {
            return ((SQLException) e).getErrorCode() == errorCode;
        } else if (e instanceof DBException) {
            SQLException sqlEx = ((DBException) e).getSQLException();
            if (sqlEx != null) return sqlEx.getErrorCode() == errorCode;
            else return false;
        }
        return false;
    }

    private static final boolean isSQLState(Exception e, String SQLState) {
        if (e == null) {
            return false;
        } else if (e instanceof SQLException) {
            return ((SQLException) e).getSQLState().equals(SQLState);
        } else if (e instanceof DBException) {
            SQLException sqlEx = ((DBException) e).getSQLException();
            if (sqlEx != null) return sqlEx.getSQLState().equals(SQLState);
            else return false;
        }
        return false;
    }

    /**
     * Check if Unique Constraint Exception (aka ORA-00001)
     *
     * @param e exception
     */
    public static boolean isUniqueContraintError(Exception e) {
        if (isPostgreSQL()) return isSQLState(e, "23505");
        //
        return isErrorCode(e, 1);
    }

    /**
     * Check if "child record found" exception (aka ORA-02292)
     *
     * @param e exception
     */
    public static boolean isChildRecordFoundError(Exception e) {
        if (isPostgreSQL()) return isSQLState(e, "23503");
        return isErrorCode(e, 2292);
    }

    /**
     * Check if "time out" exception (aka ORA-01013)
     *
     * @param e
     */
    public static boolean isTimeout(Exception e) {
        if (isPostgreSQL()) return isSQLState(e, "57014");
        return isErrorCode(e, 1013);
    }

    /**
     * @param e
     */
    public static String getDefaultDBExceptionMessage(Exception e) {
        if (isUniqueContraintError(e)) {
            return SAVE_ERROR_NOT_UNIQUE_MSG;
        } else if (isChildRecordFoundError(e)) {
            return DELETE_ERROR_DEPENDENT_MSG;
        } else if (isTimeout(e)) {
            return DATABASE_OPERATION_TIMEOUT_MSG;
        } else {
            return null;
        }
    }

    /**
     * @return Wrapped SQLException or null
     */
    public SQLException getSQLException() {
        Throwable cause = getCause();
        if (cause instanceof SQLException) return (SQLException) cause;
        return null;
    }

} //	DBException
