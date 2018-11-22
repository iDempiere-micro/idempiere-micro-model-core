package org.idempiere.icommon.model;

import java.util.Properties;

// to be used instead of PO
public interface IBasePO {
  int getId();

  String get_TrxName();

  int getADClientID();

  int getAD_Org_ID();

  Properties getCtx();

  int get_Table_ID();
}
