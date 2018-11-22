package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Kirin Lin
 * @version $Id: IniRes_zh.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_zh extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "\u7248\u6b0a\u5ba3\u544a"},
        {"Do_you_accept", "\u60a8\u540c\u610f\u9019\u500b\u5ba3\u544a\u55ce?"},
        {"No", "\u4e0d"},
        {"Yes_I_Understand", "\u662f\u7684, \u6211\u540c\u610f\u4e26\u4e14\u63a5\u53d7"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "\u6388\u6b0a\u88ab\u62d2\u7d55\u6216\u5df2\u904e\u671f"}
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
