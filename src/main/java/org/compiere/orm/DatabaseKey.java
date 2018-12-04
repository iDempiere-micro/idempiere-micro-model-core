package org.compiere.orm;

/** @author Elaine */
public class DatabaseKey {
  private String keyName;
  private String keyTable;
  private String[] keyColumns;
  private short deleteRule;

  public DatabaseKey(String keyName, String keyTable, String[] keyColumns, short deleteRule) {
    this.keyName = keyName;
    this.keyTable = keyTable;
    this.keyColumns = keyColumns;
    this.deleteRule = deleteRule;
  }

  public String getKeyName() {
    return keyName;
  }

  public void setKeyName(String keyName) {
    this.keyName = keyName;
  }

  public String getKeyTable() {
    return keyTable;
  }

  public void setKeyTable(String keyTable) {
    this.keyTable = keyTable;
  }

  public String[] getKeyColumns() {
    return keyColumns;
  }

  public void setKeyColumns(String[] keyColumns) {
    this.keyColumns = keyColumns;
  }

  public short getDeleteRule() {
    return deleteRule;
  }

  public void setDeleteRule(short deleteRule) {
    this.deleteRule = deleteRule;
  }
}
