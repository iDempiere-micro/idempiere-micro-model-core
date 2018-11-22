package org.idempiere.icommon.model;

// to be used instead of PO
public interface IPO extends IBasePO {
  String[] get_KeyColumns();

  Object get_ValueOfColumn(int ad_column_id);

  int get_ColumnIndex(String token);

  Object get_Value(String columnName);

  String get_TableName();

  boolean is_new();
}
