package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation (Thai)
 *
 * @author Sureeraya Limpaibul
 * @version $Id: IniRes_th.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_th extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {
          "Adempiere_License",
          "\u0e02\u0e49\u0e2d\u0e15\u0e01\u0e25\u0e07\u0e17\u0e32\u0e07\u0e25\u0e34\u0e02\u0e2a\u0e34\u0e17\u0e18\u0e34\u0e4c"
        },
        {
          "Do_you_accept",
          "\u0e17\u0e48\u0e32\u0e19\u0e44\u0e14\u0e49\u0e22\u0e2d\u0e21\u0e23\u0e31\u0e1a\u0e25\u0e34\u0e02\u0e2a\u0e34\u0e17\u0e18\u0e34\u0e4c\u0e2b\u0e23\u0e37\u0e2d\u0e44\u0e21\u0e48"
        },
        {"No", "\u0e44\u0e21\u0e48\u0e22\u0e2d\u0e21\u0e23\u0e31\u0e1a"},
        {
          "Yes_I_Understand",
          "\u0e22\u0e2d\u0e21\u0e23\u0e31\u0e1a, \u0e02\u0e49\u0e32\u0e1e\u0e40\u0e08\u0e49\u0e32\u0e21\u0e35\u0e04\u0e27\u0e32\u0e21\u0e40\u0e02\u0e49\u0e32\u0e43\u0e08\u0e41\u0e25\u0e30\u0e22\u0e2d\u0e21\u0e23\u0e31\u0e1a\u0e15\u0e32\u0e21\u0e02\u0e49\u0e2d\u0e15\u0e01\u0e25\u0e07\u0e17\u0e32\u0e07\u0e25\u0e34\u0e02\u0e2a\u0e34\u0e17\u0e18\u0e34\u0e4c\u0e19\u0e35\u0e49"
        },
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "\u0e25\u0e34\u0e02\u0e2a\u0e34\u0e17\u0e18\u0e34\u0e4c"}
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
