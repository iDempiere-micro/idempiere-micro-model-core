package org.idempiere.common.db;

import java.lang.reflect.Proxy;
import org.idempiere.common.util.CCallableStatement;
import org.idempiere.common.util.CPreparedStatement;
import org.idempiere.common.util.CStatement;
import org.idempiere.common.util.CStatementVO;

/**
 * Factory class to instantiate dynamic proxy for CStatement, CPreparedStatement and
 * CCallableStatement
 *
 * @author Low Heng Sin
 */
public class ProxyFactory {

  /**
   * @param resultSetType
   * @param resultSetConcurrency
   * @param trxName
   * @return CStatement proxy
   */
  public static CStatement newCStatement(
      int resultSetType, int resultSetConcurrency, String trxName) {
    return (CStatement)
        Proxy.newProxyInstance(
            CStatement.class.getClassLoader(),
            new Class[] {CStatement.class},
            new StatementProxy(resultSetType, resultSetConcurrency, trxName));
  }

  /**
   * @param resultSetType
   * @param resultSetConcurrency
   * @param sql
   * @param trxName
   * @return CPreparedStatement proxy
   */
  public static CPreparedStatement newCPreparedStatement(
      int resultSetType, int resultSetConcurrency, String sql, String trxName) {
    return (CPreparedStatement)
        Proxy.newProxyInstance(
            CPreparedStatement.class.getClassLoader(),
            new Class[] {CPreparedStatement.class},
            new PreparedStatementProxy(resultSetType, resultSetConcurrency, sql, trxName));
  }

  /**
   * @param resultSetType
   * @param resultSetConcurrency
   * @param sql
   * @param trxName
   * @return CCallableStatement proxy
   */
  public static CCallableStatement newCCallableStatement(
      int resultSetType, int resultSetConcurrency, String sql, String trxName) {
    return (CCallableStatement)
        Proxy.newProxyInstance(
            CCallableStatement.class.getClassLoader(),
            new Class[] {CCallableStatement.class},
            new CallableStatementProxy(resultSetType, resultSetConcurrency, sql, trxName));
  }

  /**
   * @param info
   * @return CStatement proxy
   */
  public static CStatement newCStatement(CStatementVO info) {
    return (CStatement)
        Proxy.newProxyInstance(
            CStatement.class.getClassLoader(),
            new Class[] {CStatement.class},
            new StatementProxy(info));
  }

  /**
   * @param info
   * @return CPreparedStatement proxy
   */
  public static CPreparedStatement newCPreparedStatement(CStatementVO info) {
    return (CPreparedStatement)
        Proxy.newProxyInstance(
            CPreparedStatement.class.getClassLoader(),
            new Class[] {CPreparedStatement.class},
            new PreparedStatementProxy(info));
  }

  /**
   * @param info
   * @return CCallableStatement proxy
   */
  public static CCallableStatement newCCallableStatement(CStatementVO info) {
    return (CCallableStatement)
        Proxy.newProxyInstance(
            CCallableStatement.class.getClassLoader(),
            new Class[] {CCallableStatement.class},
            new CallableStatementProxy(info));
  }
}
