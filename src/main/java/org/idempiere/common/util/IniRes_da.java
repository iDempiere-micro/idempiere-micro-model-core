package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Jorg Janke
 * @version $Id: IniRes_da.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_da extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Licensaftale"},
        {"Do_you_accept", "Accept�r licensbetingelser?"},
        {"No", "Nej"},
        {"Yes_I_Understand", "Ja, jeg accepterer"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Licens afvist eller udl�bet"}
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
