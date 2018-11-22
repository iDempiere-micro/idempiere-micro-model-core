package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author ZhaoXing Meng
 * @version $Id: IniRes_zh_CN.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_zh_CN extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "\u6700\u7ec8\u7528\u6237\u8bb8\u53ef\u534f\u8bae"},
        {"Do_you_accept", "\u60a8\u540c\u610f\u6b64\u534f\u8bae\u5417\uff1f"},
        {"No", "\u4e0d"},
        {"Yes_I_Understand", "\u662f\uff0c\u6211\u540c\u610f\u5e76\u4e14\u63a5\u53d7"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "\u6743\u9650\u88ab\u62d2\u7edd\u6216\u5df2\u8fc7\u671f"}
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
