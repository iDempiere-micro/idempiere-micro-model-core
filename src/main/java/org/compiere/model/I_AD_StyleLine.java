package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_StyleLine
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_StyleLine {

  /** TableName=AD_StyleLine */
  public static final String Table_Name = "AD_StyleLine";

  /** AD_Table_ID=200208 */
  public static final int Table_ID = 200208;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_Style_ID */
  public static final String COLUMNNAME_AD_Style_ID = "AD_Style_ID";

  /** Set Style. CSS style for field and label */
  public void setAD_Style_ID(int AD_Style_ID);

  /** Get Style. CSS style for field and label */
  public int getAD_Style_ID();

  public I_AD_Style getAD_Style() throws RuntimeException;

  /** Column name AD_StyleLine_ID */
  public static final String COLUMNNAME_AD_StyleLine_ID = "AD_StyleLine_ID";

  /** Set Style Line. CSS Style Line */
  public void setAD_StyleLine_ID(int AD_StyleLine_ID);

  /** Get Style Line. CSS Style Line */
  public int getAD_StyleLine_ID();

  /** Column name AD_StyleLine_UU */
  public static final String COLUMNNAME_AD_StyleLine_UU = "AD_StyleLine_UU";

  /** Set Style Line. CSS Style Line */
  public void setAD_StyleLine_UU(String AD_StyleLine_UU);

  /** Get Style Line. CSS Style Line */
  public String getAD_StyleLine_UU();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name DisplayLogic */
  public static final String COLUMNNAME_DisplayLogic = "DisplayLogic";

  /**
   * Set Display Logic. If the Field is displayed, the result determines if the field is actually
   * displayed
   */
  public void setDisplayLogic(String DisplayLogic);

  /**
   * Get Display Logic. If the Field is displayed, the result determines if the field is actually
   * displayed
   */
  public String getDisplayLogic();

  /** Column name InlineStyle */
  public static final String COLUMNNAME_InlineStyle = "InlineStyle";

  /** Set Inline Style. CSS Inline Style */
  public void setInlineStyle(String InlineStyle);

  /** Get Inline Style. CSS Inline Style */
  public String getInlineStyle();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name Line */
  public static final String COLUMNNAME_Line = "Line";

  /** Set Line No. Unique line for this document */
  public void setLine(int Line);

  /** Get Line No. Unique line for this document */
  public int getLine();

  /** Column name Theme */
  public static final String COLUMNNAME_Theme = "Theme";

  /** Set Theme. Theme name */
  public void setTheme(String Theme);

  /** Get Theme. Theme name */
  public String getTheme();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
