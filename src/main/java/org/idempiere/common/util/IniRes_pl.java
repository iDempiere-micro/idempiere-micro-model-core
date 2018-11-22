package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Adam Bodurka
 * @version $Id: IniRes_pl.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_pl extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Akceptacja Licencji"},
        {"Do_you_accept", "Czy akceptujesz licencj\u0119 ?"},
        {"No", "Nie"},
        {"Yes_I_Understand", "Tak, Rozumiem i akceptuj\u0119"},
        {"license_htm", "../license.htm"},
        {"License_rejected", "Licencja anulowana lub wygas\u0142a"}
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
