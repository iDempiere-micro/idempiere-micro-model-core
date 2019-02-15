package org.compiere.orm;

import java.util.Properties;
import org.idempiere.common.util.CCache;

public class MSystem extends X_AD_System {
  /** System - cached */
  private static CCache<Integer, MSystem> s_system =
      new CCache<Integer, MSystem>(Table_Name, 1, -1, true);

  /**
   * ************************************************************************ Default Constructor
   *
   * @param ctx context
   * @param ignored id
   * @param mtrxName transaction
   */
  public MSystem(Properties ctx, int ignored, String mtrxName) {
    super(ctx, 0);
    String trxName = null;
    load(); // 	load ID=0
    if (s_system.get(0) == null) s_system.put(0, this);
  } //	MSystem

    /**
   * Load System Record
   *
   * @param ctx context
   * @return System
   */
  public static synchronized MSystem get(Properties ctx) {
    if (s_system.get(0) != null) return s_system.get(0);
    //
    MSystem system =
        new Query(ctx, Table_Name, null).setOrderBy(COLUMNNAME_AD_System_ID).firstOnly();
    if (system == null) return null;
    //
    s_system.put(0, system);
    return system;
  } //	get

}
