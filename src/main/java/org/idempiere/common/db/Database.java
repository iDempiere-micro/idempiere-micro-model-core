package org.idempiere.common.db;

import java.util.ArrayList;
import java.util.List;
import org.idempiere.common.base.Service;
import org.idempiere.common.util.CLogger;
import org.idempiere.icommon.db.AdempiereDatabase;

/**
 * General Database Constants and Utilities
 *
 * @author Jorg Janke
 * @version $Id: Database.java,v 1.3 2006/07/30 00:55:13 jjanke Exp $
 */
public class Database {
  /** Logger */
  private static CLogger log = CLogger.getCLogger(Database.class);

  /** Oracle ID */
  public static String DB_ORACLE = "Oracle";
  /** PostgreSQL ID */
  public static String DB_POSTGRESQL = "PostgreSQL";

  /** Connection Timeout in seconds */
  public static int CONNECTION_TIMEOUT = 10;

  /** Default Port */
  public static final int DB_ORACLE_DEFAULT_PORT = 1521;
  /** Default Connection Manager Port */
  public static final int DB_ORACLE_DEFAULT_CM_PORT = 1630;

  /** Default Port */
  public static final int DB_POSTGRESQL_DEFAULT_PORT = 5432;

  protected static Database instance = null;

  public Database() {
    instance = this;
  }

  protected static AdempiereDatabase databaseService = null;

  public void setDatabase(AdempiereDatabase databaseService) {
    Database.databaseService = databaseService;
  }

  /**
   * Get Database by database Id.
   *
   * @return database
   */
  public static AdempiereDatabase getDatabase(String type) {
    return databaseService;
  }

  private String[] doGetDatabaseNames() {
    List<String> names = new ArrayList<String>();
    List<AdempiereDatabase> services =
        Service.Companion.locator().list(AdempiereDatabase.class).getServices();
    for (AdempiereDatabase db : services) {
      names.add(db.getName());
    }
    return names.toArray(new String[0]);
  }

  public static String[] getDatabaseNames() {
    return instance.doGetDatabaseNames();
  }

  private AdempiereDatabase doGetDatabaseFromURL(String url) {
    if (url == null) {
      log.severe("No Database URL");
      return null;
    }
    if (url.indexOf("oracle") != -1) return getDatabase(DB_ORACLE);
    if (url.indexOf("postgresql") != -1) return getDatabase(DB_POSTGRESQL);

    log.severe("No Database for " + url);
    return null;
  }

  /**
   * Get Database Driver by url string. Access to database specific functionality.
   *
   * @param URL JDBC connection url
   * @return Adempiere Database Driver
   */
  public static AdempiereDatabase getDatabaseFromURL(String url) {
    return instance.doGetDatabaseFromURL(url);
  }
} //  Database
