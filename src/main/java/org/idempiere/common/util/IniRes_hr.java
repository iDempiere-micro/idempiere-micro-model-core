package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Marko Bubalo, Mislav KaÅ¡ner
 * @version $Id: IniRes_hr.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_hr extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Sporazum o licenci"},
        {"Do_you_accept", "Da li prihva\u0107ate licencu"},
        {"No", "Ne"},
        {"Yes_I_Understand", "Da, razumijem i prihva\u0107am"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Licenca odbijena ili istekla"}
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
