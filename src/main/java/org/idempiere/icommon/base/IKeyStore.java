package org.idempiere.icommon.base;

import javax.crypto.SecretKey;

/** @author deepak */
public interface IKeyStore {

  /**
   * @param AD_Client_ID
   * @return secret key
   */
  SecretKey getKey(int AD_Client_ID);

  /** @return encryption algorithm id, for e.g AES */
  String getAlgorithm();
}
