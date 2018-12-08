package org.idempiere.common.util;

/** @author hengsin */
public interface TrxEventListener {
  void afterCommit(Trx trx, boolean success);

  void afterRollback(Trx trx, boolean success);

  void afterClose(Trx trx);
}
