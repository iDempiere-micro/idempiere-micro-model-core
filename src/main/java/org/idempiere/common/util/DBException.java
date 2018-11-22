package org.idempiere.common.util;

/**
 * This RuntimeException is used to pass SQLException up the chain of calling methods to determine
 * what to do where needed.
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * @deprecated This class was moved to {@link org.idempiere.common.exceptions.DBException} and will
 *     be removed in future.
 */
@Deprecated
public class DBException extends org.idempiere.common.exceptions.DBException {
  /** */
  private static final long serialVersionUID = 7636162347228017698L;

  public DBException(Exception e) {
    super(e);
  }

  public DBException(String msg) {
    super(msg);
  }
}
