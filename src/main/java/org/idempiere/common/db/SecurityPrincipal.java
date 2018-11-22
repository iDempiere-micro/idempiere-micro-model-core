package org.idempiere.common.db;

import java.io.Serializable;

/**
 * Use by CConnection to store authentication info for application server.
 *
 * @author Low Heng Sin
 */
public class SecurityPrincipal implements Serializable {
  /** generated serial version id */
  private static final long serialVersionUID = -6924078376448056295L;

  public String identity;
  public char[] secret;
}
