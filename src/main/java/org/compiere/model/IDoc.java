package org.compiere.model;

public interface IDoc {
  String post(boolean force, boolean repost);

  String getPostStatus();
}
