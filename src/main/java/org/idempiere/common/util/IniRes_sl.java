package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Matja\u017e Godec
 * @version $Id: IniRes_sl.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_sl extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Sporazum o licenci"},
        {"Do_you_accept", "Ali se strinjate z licenco"},
        {"No", "Ne"},
        {"Yes_I_Understand", "Ja, licenco razumem in se z njo strinjam"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Licenca je zavr\u017eena ali potekla"}
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
