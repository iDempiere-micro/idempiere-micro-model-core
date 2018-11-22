package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Stefan Christians
 * @version $Id: IniRes_ja.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_ja extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "\u30e9\u30a4\u30bb\u30f3\u30b9"},
        {
          "Do_you_accept",
          "\u3053\u306e\u30e9\u30a4\u30bb\u30f3\u30b9\u306b\u540c\u610f\u3057\u307e\u3059\u304b\uff1f"
        },
        {"No", "\u3044\u3044\u3048"},
        {"Yes_I_Understand", "\u306f\u3044"},
        {"license_htm", "org/idempiere/license.htm"},
        {
          "License_rejected",
          "\u30e9\u30a4\u30bb\u30f3\u30b9\u306b\u540c\u610f\u3057\u307e\u305b\u3093\u3067\u3057\u305f"
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
