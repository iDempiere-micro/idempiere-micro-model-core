package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Nikola Petkov
 * @version $Id: patch-java-trl-sr_RS.txt,v 1.1 2008/12/05 11:39:16 mgifos Exp $
 */
public class IniRes_sr extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {
          "Adempiere_License",
          "\u0421\u043F\u043E\u0440\u0430\u0437\u0443\u043C \u043E \u043B\u0438\u0446\u0435\u043D\u0446\u0438"
        },
        {
          "Do_you_accept",
          "\u0414\u0430 \u043B\u0438 \u043F\u0440\u0438\u0445\u0432\u0430\u0442\u0430\u0442\u0435 \u043B\u0438\u0446\u0435\u043D\u0446\u0443"
        },
        {"No", "\u041D\u0435"},
        {
          "Yes_I_Understand",
          "\u0414\u0430, \u0440\u0430\u0437\u0443\u043C\u0435\u043C \u0438 \u043F\u0440\u0438\u0445\u0432\u0430\u0442\u0430\u043C"
        },
        {"license_htm", "org/idempiere/license.htm"},
        {
          "License_rejected",
          "\u041B\u0438\u0446\u0435\u043D\u0446\u0430 \u0458\u0435 \u043E\u0434\u0431\u0438\u0458\u0435\u043D\u0430 \u0438\u043B\u0438 \u0438\u0441\u0442\u0435\u043A\u043B\u0430"
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
