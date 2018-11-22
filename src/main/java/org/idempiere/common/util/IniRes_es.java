package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Erwin Cortes
 * @author Jordi Luna
 * @version $Id: IniRes_es.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_es extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Acuerdo de Licencia"},
        {"Do_you_accept", "Acepta la Licencia?"},
        {"No", "No"},
        {"Yes_I_Understand", "S\u00ed, entiendo y acepto"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Licencia rechazada o caducada"}
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
