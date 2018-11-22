package org.idempiere.common.util;

/** @author hengsin */
public interface TrxEventListener {
  public void afterCommit(Trx trx, boolean success);

  public void afterRollback(Trx trx, boolean success);

  public void afterClose(Trx trx);
}
