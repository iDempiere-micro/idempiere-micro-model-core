package org.idempiere.common.util;

import java.io.Serializable;
import java.util.concurrent.Callable;

/** @author hengsin */
public class ResetCacheCallable implements Callable<Integer>, Serializable {

  /** generated serial id */
  private static final long serialVersionUID = 6669645804211785491L;

  private String tableName;
  private int Record_ID;

  protected ResetCacheCallable(String tableName, int Record_ID) {
    this.tableName = tableName;
    this.Record_ID = Record_ID;
  }

  @Override
  public Integer call() throws Exception {
    return CacheMgt.get().resetLocalCache(tableName, Record_ID);
  }
}
