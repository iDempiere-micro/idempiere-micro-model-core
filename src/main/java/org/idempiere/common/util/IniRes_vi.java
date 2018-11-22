package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Bui Chi Trung
 * @version $Id: IniRes_vi.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_vi extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Gi\u1EA5y ph�p s\u1EED d\u1EE5ng"},
        {"Do_you_accept", "B\u1EA1n c� ch\u1EA5p nh\u1EADn gi\u1EA5y ph�p n�y kh�ng?"},
        {"No", "Kh�ng"},
        {"Yes_I_Understand", "V�ng, t�i hi\u1EC3u v� ch\u1EA5p nh\u1EADn gi\u1EA5p ph�p n�y"},
        {"license_htm", "org/idempiere/license.htm"},
        {
          "License_rejected",
          "Gi\u1EA5y ph�p b\u1ECB kh\u01B0\u1EDBc t\u1EEB ho\u1EB7c h\u1EBFt h\u1EA1n"
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
