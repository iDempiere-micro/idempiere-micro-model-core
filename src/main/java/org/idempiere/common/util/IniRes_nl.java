package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Eldir Tomassen
 * @version $Id: IniRes_nl.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_nl extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Licentieovereenkomst"},
        {"Do_you_accept", "Accepteert u de licentieovereenkomst"},
        {"No", "Nee"},
        {"Yes_I_Understand", "Ja, Ik heb de licentieovereenkomst gelezen en ga hiermee accoord."},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Licentieovereenkomst afgewezen of verlopen."}
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
