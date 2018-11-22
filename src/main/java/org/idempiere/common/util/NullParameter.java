package org.idempiere.common.util;

import java.io.Serializable;

/**
 * Null Parameter for CPreparedStatement
 *
 * @author Jorg Janke
 * @version $Id: NullParameter.java,v 1.2 2006/07/30 00:54:36 jjanke Exp $
 */
public class NullParameter implements Serializable {
  /** */
  private static final long serialVersionUID = 5098594046699488306L;

  /**
   * Cosntructor
   *
   * @param type SQL Type java.sql.Types.*
   */
  public NullParameter(int type) {
    m_type = type;
  } //	NullParameter

  private int m_type = -1;

  /**
   * Get Type
   *
   * @return type
   */
  public int getType() {
    return m_type;
  } //	getType

  /**
   * String representation
   *
   * @return info
   */
  public String toString() {
    return "NullParameter Type=" + m_type;
  } //	toString
} //	NullParameter
