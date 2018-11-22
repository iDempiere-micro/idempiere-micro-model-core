package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * Notwegian License Dialog Translation
 *
 * @author Olaf Slazak L�ken
 * @version $Id: IniRes_no.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_no extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Lisense Avtale"},
        {"Do_you_accept", "Aksepterer du lisense avtalen ?"},
        {"No", "Nei"},
        {"Yes_I_Understand", "Ja, Jeg Forst�r og Aksepterer"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Lisensen avist eller utg�tt"}
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
