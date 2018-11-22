package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation (German)
 *
 * @author Jorg Janke
 * @version $Id: IniRes_de.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_de extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Lizenzvereinbarung"},
        {"Do_you_accept", "Akzeptieren Sie die Lizenzvereinbarung?"},
        {"No", "Nein"},
        {"Yes_I_Understand", "Ja, ich verstehe und akzeptiere"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Lizenz abgelehnt oder abgelaufen"}
      };

  /**
   * Get Content
   *
   * @return Content
   */
  public Object[][] getContents() {
    return contents;
  } //  getContent
} //  IniRes_de
