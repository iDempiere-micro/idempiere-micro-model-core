package org.compiere.model;

public interface IPODoc extends IDocLine {
  /**
   * Set the accounting document associated to the PO - for use in POST ModelValidator
   *
   * @param doc Document
   */
  void setDoc(IDoc doc);

  boolean load(String trxName);

  void set_TrxName(String m_trxName);

  boolean isActive();

  int getUpdatedBy();

  void saveEx();

  void setProcessedOn(String processed, boolean b, boolean b1);
}
