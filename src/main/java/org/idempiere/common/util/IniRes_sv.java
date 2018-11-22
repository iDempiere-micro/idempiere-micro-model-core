package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * Swedish License Dialog Translation
 *
 * @author Thomas Dilts
 */
public class IniRes_sv extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Licensavtal"},
        {"Do_you_accept", "Godk\u00e4nner du licensen?"},
        {"No", "Nej"},
        {"Yes_I_Understand", "Ja, jag f\u00f6rst\u00e5r och godk\u00e4nner"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Licens inte godk\u00e4nd eller l\u00f6pt ut"}
      };

  /**
   * Get Content
   *
   * @return Content
   */
  public Object[][] getContents() {
    return contents;
  } //  getContent
} //  IniRes
