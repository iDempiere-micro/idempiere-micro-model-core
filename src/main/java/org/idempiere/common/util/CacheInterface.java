package org.idempiere.common.util;

/**
 * Adempiere Cache Interface
 *
 * @author Jorg Janke
 * @version $Id: CacheInterface.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 */
public interface CacheInterface {
  /**
   * Reset Cache
   *
   * @return number of items reset
   */
  public int reset();

  /**
   * Reset Cache
   *
   * @return number of items reset
   */
  public int reset(int recordId);

  /**
   * Get Size of Cache
   *
   * @return number of items
   */
  public int size();

  /**
   * New record created notification
   *
   * @param record_ID
   */
  public void newRecord(int record_ID);
} //	CacheInterface
