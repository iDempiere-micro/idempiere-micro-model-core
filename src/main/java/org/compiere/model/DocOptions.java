package org.compiere.model;

/**
 * Document Options Interface
 *
 * @author Carlos Ruiz
 */
public interface DocOptions {
  /**
   * Customize Valid Actions
   *
   * @param docStatus
   * @param processing
   * @param orderType
   * @param isSOTrx
   * @param AD_Table_ID
   * @param docAction
   * @param options
   * @param index
   * @return Number of valid options
   */
  public int customizeValidActions(
      String docStatus,
      Object processing,
      String orderType,
      String isSOTrx,
      int AD_Table_ID,
      String[] docAction,
      String[] options,
      int index);
} //	DocAction
