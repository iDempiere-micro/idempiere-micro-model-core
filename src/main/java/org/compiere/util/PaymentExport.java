package org.compiere.util;

import java.io.File;
import org.compiere.model.I_C_PaySelectionCheck;

/**
 * Custom Payment Export Interface
 *
 * @author Carlos Ruiz - GlobalQSS
 *     <p>Contributors: Markus Bozem - IDEMPIERE-1546 / IDEMPIERE-3286
 * @version PaymentExport.java
 */
public interface PaymentExport {

  /**
   * ************************************************************************ Export to File
   *
   * @param checks array of checks
   * @param file file to export checks
   * @return number of lines
   *     <p>This method is preserved for backward compatibility (old non-OSGi way via fragment), new
   *     interfaces can leave this method unimplemented and must implement the other methods
   */
  public default int exportToFile(I_C_PaySelectionCheck[] checks, File file, StringBuffer err) {
    return exportToFile(checks, false, (String) null, file, err);
  }

  /**
   * ************************************************************************ Export to file
   *
   * @param checks array of checks
   * @param depositBatch create deposit batch
   * @param file file to export checks
   * @return number of lines
   */
  public default int exportToFile(
      I_C_PaySelectionCheck[] checks,
      boolean depositBatch,
      String paymentRule,
      File file,
      StringBuffer err) {
    return exportToFile(checks, file, err);
  }

  /**
   * Get the filename prefix from plugin e.g. "SEPA-Credit-Transfer-"
   *
   * @return prefix for filename
   */
  public default String getFilenamePrefix() {
    return "paymentExport";
  }

  /**
   * Get the filename suffix from plugin e.g. ".xml"
   *
   * @return suffix for filename
   */
  public default String getFilenameSuffix() {
    return ".txt";
  }

  /**
   * Get the content type from plugin e.g. "text/xml" or "text/csv"
   *
   * @return content type delivered to browser
   */
  public default String getContentType() {
    return "text/plain";
  }

  /**
   * Plugin supports deposit batch
   *
   * @return true if supported
   */
  public default boolean supportsDepositBatch() {
    return false;
  }

  /**
   * Plugin supports booking payments separate on bank statement (no deposit batch)
   *
   * @return true if supported
   */
  public default boolean supportsSeparateBooking() {
    return false;
  }

  /**
   * Default if supportsDepositBatch is true and supportsSeparateBooking is true
   *
   * @return true if deposit batch should be selected on default, false if deposit batch should not
   *     be selected
   */
  public default boolean getDefaultDepositBatch() {
    return false;
  }
} //	PaymentExport
