package org.compiere.orm;

import static software.hsharp.core.util.DBKt.getSQLValue;

import java.sql.ResultSet;
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
    super(ctx, 0, mtrxName);
    String trxName = null;
    load(trxName); // 	load ID=0
    if (s_system.get(0) == null) s_system.put(0, this);
  } //	MSystem

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MSystem(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
    if (s_system.get(0) == null) s_system.put(0, this);
  } //	MSystem

  /** Constructor */
  public MSystem() {
    this(new Properties(), 0, null);
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
        new Query(ctx, Table_Name, null, null).setOrderBy(COLUMNNAME_AD_System_ID).firstOnly();
    if (system == null) return null;
    //
    s_system.put(0, system);
    return system;
  } //	get

  /** Set Internal User Count */
  private void setInternalUsers() {
    final String sql =
        "SELECT COUNT(DISTINCT (u.AD_User_ID)) AS iu "
            + "FROM AD_User u"
            + " INNER JOIN AD_User_Roles ur ON (u.AD_User_ID=ur.AD_User_ID) "
            + "WHERE u.clientId<>11" //	no Demo
            + " AND u.AD_User_ID NOT IN (0,100)"; //	no System/SuperUser
    int internalUsers = getSQLValue(null, sql);
    setSupportUnits(internalUsers);
  } //	setInternalUsers
}
