package org.compiere.model;

import org.idempiere.icommon.model.IPO;

public interface IDocLine extends IPO {
  void setAD_Org_ID(int ad_org_id);

  Object get_Value(int index);
}
