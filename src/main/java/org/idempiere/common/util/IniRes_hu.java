package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Jorg Janke
 * @version $Id: IniRes.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_hu extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Licensz szerződés"},
        {"Do_you_accept", "Elfogadja a licensz feltételeit?"},
        {"No", "Nem"},
        {"Yes_I_Understand", "Igen, Megértettem és Elfogadom"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Licensz elutasítva, vagy lejárt"}
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
