package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation (French)
 *
 * @author Jean-Luc SCHEIDEGGER
 * @version $Id: IniRes_fr.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_fr extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Licence Adempiere"},
        {"Do_you_accept", "Accepter vous?"},
        {"No", "Non"},
        {"Yes_I_Understand", "Oui, je comprend et j'accepte"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "License rejet�e ou expir�e"}
      };

  /**
   * Get Content
   *
   * @return Content
   */
  public Object[][] getContents() {
    return contents;
  } //  getContent
} //  IniRes_fr
