// Generic PO.
package org.compiere.orm;

// import for GenericPO
import java.sql.ResultSet;
import java.util.Properties;
import org.idempiere.orm.POInfo;

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
    super(new PropertiesWrapper(ctx, tableName), ID, null, null);
  }

  /**
   * @param tableName
   * @param ctx
   * @param rs
   */
  public GenericPO(String tableName, Properties ctx, ResultSet rs) {
    super(new PropertiesWrapper(ctx, tableName), 0, null, rs);
  }

  /**
   * @param tableName
   * @param ctx
   * @param ID
   * @param trxName
   */
  public GenericPO(String tableName, Properties ctx, int ID, String trxName) {
    super(new PropertiesWrapper(ctx, tableName), ID, trxName, null);
  }

  /**
   * @param tableName
   * @param ctx
   * @param rs
   * @param trxName
   */
  public GenericPO(String tableName, Properties ctx, ResultSet rs, String trxName) {
    super(new PropertiesWrapper(ctx, tableName), 0, trxName, rs);
  }

  /**
   * We must not use variable initializer here since the 2 variable below will be initialize inside
   * the initPO method called by the parent constructor.
   */
  private int tableID;

  private String tableName;

  /** Load Meta Data */
  protected POInfo initPO(Properties ctx) {
    PropertiesWrapper wrapper = (PropertiesWrapper) ctx;
    p_ctx = wrapper.source;
    tableName = wrapper.tableName;
    tableID = MTable.getTable_ID(tableName);
    // log.info("Table_ID: "+Table_ID);
    POInfo poi = POInfo.getPOInfo(ctx, tableID, this.get_TrxName());
    return poi;
  }

  public String toString() {
    StringBuffer sb =
        new StringBuffer("GenericPO[Table=")
            .append("" + tableID + ",ID=")
            .append(getId())
            .append("]");
    return sb.toString();
  }

  public static final int AD_ORGTRX_ID_AD_Reference_ID = 130;

  /** Set Trx Organization. Performing or initiating organization */
  public void setAD_OrgTrx_ID(int AD_OrgTrx_ID) {
    if (AD_OrgTrx_ID == 0) set_Value("AD_OrgTrx_ID", null);
    else set_Value("AD_OrgTrx_ID", new Integer(AD_OrgTrx_ID));
  }

  /** Get Trx Organization. Performing or initiating organization */
  public int getAD_OrgTrx_ID() {
    Integer ii = (Integer) get_Value("AD_OrgTrx_ID");
    if (ii == null) return 0;
    return ii;
  }

  @Override
  protected int getAccessLevel() {
    return Integer.parseInt(p_info.getAccessLevel());
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

  protected Properties source;
  protected String tableName;

  PropertiesWrapper(Properties source, String tableName) {
    this.source = source;
    this.tableName = tableName;
  }
}
