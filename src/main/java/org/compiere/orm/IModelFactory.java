package org.compiere.orm;

import java.sql.ResultSet;
import org.idempiere.orm.PO;

/**
 * Model factory interface, extracted from legacy code in MTable.
 *
 * @author hengsin
 */
public interface IModelFactory {
  /**
   * Get Persistence Class for Table
   *
   * @param tableName table name
   * @return class or null
   */
  public Class<?> getClass(String tableName);

  /**
   * ************************************************************************ Get PO Class Instance
   *
   * @param tableName
   * @param Record_ID record
   * @param trxName
   * @return PO for Record or null
   */
  public PO getPO(String tableName, int Record_ID, String trxName);

  /**
   * Get PO Class Instance
   *
   * @param tableName
   * @param rs result set
   * @param trxName transaction
   * @return PO for Record or null
   */
  public PO getPO(String tableName, ResultSet rs, String trxName);

  /**
   * Get PO Class Instance
   *
   * @param tableName
   * @param rs result set
   * @param trxName transaction
   * @return PO for Record or null
   */
  public PO getPO(String tableName, ResultSet rs, String trxName, String columnNamePrefix);
}
