package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Vyacheslav Pedak
 * @version $Id: IniRes_ru.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_ru extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {
          "Adempiere_License",
          "\u041b\u0438\u0446\u0435\u043d\u0437\u0438\u043e\u043d\u043d\u043e\u0435 \u0441\u043e\u0433\u043b\u0430\u0448\u0435\u043d\u0438\u0435"
        },
        {
          "Do_you_accept",
          "\u0412\u044b \u043f\u0440\u0438\u043d\u0438\u043c\u0430\u0435\u0442\u0435 \u043b\u0438\u0446\u0435\u043d\u0437\u0438\u043e\u043d\u043d\u043e\u0435 \u0441\u043e\u0433\u043b\u0430\u0448\u0435\u043d\u0438\u0435?"
        },
        {"No", "\u041d\u0435\u0442"},
        {
          "Yes_I_Understand",
          "\u0414\u0430, \u042f \u043f\u0440\u0438\u043d\u0438\u043c\u0430\u044e"
        },
        {"license_htm", "org/idempiere/license.htm"},
        {
          "License_rejected",
          "\u041b\u0438\u0446\u0435\u043d\u0437\u0438\u044f \u043d\u0435 \u043f\u0440\u0438\u043d\u044f\u0442\u0430 \u0438\u043b\u0438 \u0438\u0441\u0442\u0435\u043a \u0441\u0440\u043e\u043a \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u044f"
        }
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
