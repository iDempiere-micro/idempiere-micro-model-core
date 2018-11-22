package org.idempiere.common.util;

import java.util.ListResourceBundle;

/**
 * License Dialog Translation
 *
 * @author Jesse Jr
 * @version $Id: IniRes_pt.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class IniRes_pt extends ListResourceBundle {
  /** Translation Content */
  // Characters encoded to UTF8 Hex, so no more problems with svn commits
  // Fernando Lucktemberg - CenturuyOn Consultoria
  static final Object[][] contents =
      new String[][] {
        {"Adempiere_License", "Acordo de Licen\u00e7a"},
        {"Do_you_accept", "Voc\u00ea aceita os termos da Licen\u00e7a ?"},
        {"No", "Licen\u00e7a"},
        {"Yes_I_Understand", "Sim, eu Entendi e Aceito"},
        {"license_htm", "org/idempiere/license.htm"},
        {"License_rejected", "Licen\u00e7a rejeitada ou expirada"}
      };

  /**
   * Get Content
   *
   * @return Content
   */
  public Object[][] getContents() {
    return contents;
  } //  getContent
} //  IniRes_pt
