package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Jaume Teixi
 * @author Jordi Luna
 * @version $Id: IniRes_ca.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_ca extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Acord de Llic\u00e8ncia"},
        {"Do_you_accept", "Accepteu la Llic\u00e8ncia ?"},
        {"No", "No"},
        {"Yes_I_Understand", "Si, l'entenc i l'accepto"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Llic\u00e8ncia rebutjada o caducada"}
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
