package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Jorg Janke
 * @version $Id: IniRes_ro.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_ro extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Licen\u0163iere"},
        {"Do_you_accept", "Accepta\u0163i termenii de licen\u0163iere?"},
        {"No", "Nu"},
        {"Yes_I_Understand", "Da, \u00een\u0163eleg \u015fi accept"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Licen\u0163a a fost respins\u0103 sau a expirat"}
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
