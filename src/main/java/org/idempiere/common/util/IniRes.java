package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Jorg Janke
 * @version $Id: IniRes.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "License Agreement"},
        {"Do_you_accept", "Do you accept the License ?"},
        {"No", "No"},
        {"Yes_I_Understand", "Yes, I Understand and  Accept"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "License rejected or expired"}
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
