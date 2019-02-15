// Generic PO.
package org.compiere.orm;

// import for GenericPO

import kotliquery.Row;
import org.idempiere.orm.POInfo;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generic PO implementation, this can be use together with ModelValidator as alternative to the
 * classic generated model class and extend ( X_ & M_ ) approach.
 *
 * <p>Originally for used to insert/update data from adempieredata.xml file in 2pack.
 *
 * @author Marco LOMBARDO
 * @contributor Low Heng Sin
 */
public class GenericPO extends PO {

    /** */
  private static final long serialVersionUID = -6558017105997010172L;

    /**
   * @param tableName
   * @param ctx
   * @param ID
   */
  public GenericPO(String tableName, Properties ctx, int ID) {
    super(new PropertiesWrapper(ctx, tableName), ID, null);
  }

  /**
   * @param tableName
   * @param ctx
   * @param rs
   */
  public GenericPO(String tableName, Properties ctx, ResultSet rs) {
    super(new PropertiesWrapper(ctx, tableName), 0, rs);
  }

  public GenericPO(String tableName, Properties ctx, Row row) {
    super(new PropertiesWrapper(ctx, tableName), row);
  }

  public String toString() {
    StringBuffer sb =
        new StringBuffer("GenericPO[Table=")
            .append("" + getTableId() + ",ID=")
            .append(getId())
            .append("]");
    return sb.toString();
  }

    @Override
  protected int getAccessLevel() {
    POInfo p_info = super.getP_info();
    return Integer.parseInt(p_info.getAccessLevel());
  }

  @Override
  public int getTableId() {
    return 0;
  }
} // GenericPO

/**
 * Wrapper class to workaround the limit of PO constructor that doesn't take a tableName or tableID
 * parameter. Note that in the generated class scenario ( X_ ), tableName and tableId is generated
 * as a static field.
 *
 * @author Low Heng Sin
 */
class PropertiesWrapper extends Properties {
  /** */
  private static final long serialVersionUID = 8887531951501323594L;

    PropertiesWrapper(Properties source, String tableName) {
    }
}
