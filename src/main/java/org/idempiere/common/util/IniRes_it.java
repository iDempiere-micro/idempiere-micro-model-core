package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Gabriele Vivinetto - gabriele.mailing@rvmgroup.it
 * @version $Id: IniRes_it.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_it extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        // { "Adempiere_License",   "License Agreement" },
        {"Adempiere_License", "Accordo di Licenza"},
        // { "Do_you_accept",      "Do you accept the License ?" },
        {"Do_you_accept", "Accettate la Licenza ?"},
        // { "No",                 "No" },
        {"No", "No"},
        // { "Yes_I_Understand",   "Yes, I Understand and  Accept" },
        {"Yes_I_Understand", "Si, comprendiamo ed accettiamo"},
        // { "license_htm",        "org/idempiere/license.htm" },
        {"license_htm", "org/idempiere/license.htm"},
        // { "License_rejected",   "License rejected or expired" }
        {"License_rejected", "Licenza rifiutata o scaduta"}
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
