package org.idempiere.common.util;

public interface IEnvEventListener {

  void onClearWindowContext(int windowNo);

  void onReset(boolean finalCall);
}
