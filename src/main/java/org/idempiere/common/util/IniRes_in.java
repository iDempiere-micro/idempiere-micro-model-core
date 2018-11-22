package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Halim Englen
 * @version $Id: IniRes_in.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_in extends ListResourceBundle {
  /** Translation Content */
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Persetujuan Lisensi"},
        {"Do_you_accept", "Apakah anda menerima persetujuan lisensi ini ?"},
        {"No", "Tidak"},
        {"Yes_I_Understand", "Ya, Saya Mengerti dan Menerima sepenuhnya"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Lisensi telah ditolak atau habis masa berlakunya"}
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
