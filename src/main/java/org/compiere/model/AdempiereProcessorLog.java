package org.compiere.model;

import java.sql.Timestamp;

/**
 * Processor Log Interface
 *
 * @author Jorg Janke
 * @version $Id: AdempiereProcessorLog.java,v 1.3 2006/07/30 00:51:04 jjanke Exp $
 */
public interface AdempiereProcessorLog {
  /**
   * Get Created Date
   *
   * @return created
   */
  public Timestamp getCreated();

  /**
   * Get Summary. Textual summary of this request
   *
   * @return Summary
   */
  public String getSummary();

  /**
   * Get Description. Optional short description of the record
   *
   * @return description
   */
  public String getDescription();

  /**
   * Get Error. An Error occured in the execution
   *
   * @return true if error
   */
  public boolean isError();

  /**
   * Get Reference. Reference for this record
   *
   * @return reference
   */
  public String getReference();

  /**
   * Get Text Message. Text Message
   *
   * @return text message
   */
  public String getTextMsg();
} //	AdempiereProcessorLog
