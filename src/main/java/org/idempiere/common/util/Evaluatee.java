package org.idempiere.common.util;

/**
 * Evaluator source
 *
 * @author Jorg Janke
 * @version $Id: Evaluatee.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 */
public interface Evaluatee {
  /**
   * Get Variable Value
   *
   * @param variableName name
   * @return value
   */
  String get_ValueAsString(String variableName);
} //	Evaluatee
