package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_Language
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_Language {

  /** TableName=AD_Language */
  public static final String Table_Name = "AD_Language";

  /** AD_Table_ID=111 */
  public static final int Table_ID = 111;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Language */
  public static final String COLUMNNAME_AD_Language = "AD_Language";

  /** Set Language. Language for this entity */
  public void setADLanguage(String AD_Language);

  /** Get Language. Language for this entity */
  public String getADLanguage();

  /** Column name AD_Language_ID */
  public static final String COLUMNNAME_AD_Language_ID = "AD_Language_ID";

  /** Set Language ID */
  public void setADLanguage_ID(int AD_Language_ID);

  /** Get Language ID */
  public int getAD_Language_ID();

  /** Column name AD_Language_UU */
  public static final String COLUMNNAME_AD_Language_UU = "AD_Language_UU";

  /** Set AD_Language_UU */
  public void setADLanguage_UU(String AD_Language_UU);

  /** Get AD_Language_UU */
  public String getAD_Language_UU();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_PrintPaper_ID */
  public static final String COLUMNNAME_AD_PrintPaper_ID = "AD_PrintPaper_ID";

  /** Set Print Paper. Printer paper definition */
  public void setAD_PrintPaper_ID(int AD_PrintPaper_ID);

  /** Get Print Paper. Printer paper definition */
  public int getAD_PrintPaper_ID();

  public I_AD_PrintPaper getAD_PrintPaper() throws RuntimeException;

  /** Column name CountryCode */
  public static final String COLUMNNAME_CountryCode = "CountryCode";

  /**
   * Set ISO Country Code. Upper-case two-letter alphanumeric ISO Country code according to ISO
   * 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
   */
  public void setCountryCode(String CountryCode);

  /**
   * Get ISO Country Code. Upper-case two-letter alphanumeric ISO Country code according to ISO
   * 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
   */
  public String getCountryCode();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name DatePattern */
  public static final String COLUMNNAME_DatePattern = "DatePattern";

  /** Set Date Pattern. Java Date Pattern */
  public void setDatePattern(String DatePattern);

  /** Get Date Pattern. Java Date Pattern */
  public String getDatePattern();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name IsBaseLanguage */
  public static final String COLUMNNAME_IsBaseLanguage = "IsBaseLanguage";

  /** Set Base Language. The system information is maintained in this language */
  public void setIsBaseLanguage(boolean IsBaseLanguage);

  /** Get Base Language. The system information is maintained in this language */
  public boolean isBaseLanguage();

  /** Column name IsDecimalPoint */
  public static final String COLUMNNAME_IsDecimalPoint = "IsDecimalPoint";

  /** Set Decimal Point. The number notation has a decimal point (no decimal comma) */
  public void setIsDecimalPoint(boolean IsDecimalPoint);

  /** Get Decimal Point. The number notation has a decimal point (no decimal comma) */
  public boolean isDecimalPoint();

  /** Column name IsLoginLocale */
  public static final String COLUMNNAME_IsLoginLocale = "IsLoginLocale";

  /** Set Login Locale */
  public void setIsLoginLocale(boolean IsLoginLocale);

  /** Get Login Locale */
  public boolean isLoginLocale();

  /** Column name IsSystemLanguage */
  public static final String COLUMNNAME_IsSystemLanguage = "IsSystemLanguage";

  /** Set System Language. The screens, etc. are maintained in this Language */
  public void setIsSystemLanguage(boolean IsSystemLanguage);

  /** Get System Language. The screens, etc. are maintained in this Language */
  public boolean isSystemLanguage();

  /** Column name LanguageISO */
  public static final String COLUMNNAME_LanguageISO = "LanguageISO";

  /**
   * Set ISO Language Code. Lower-case two-letter ISO-3166 code -
   * http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt
   */
  public void setLanguageISO(String LanguageISO);

  /**
   * Get ISO Language Code. Lower-case two-letter ISO-3166 code -
   * http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt
   */
  public String getLanguageISO();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name PrintName */
  public static final String COLUMNNAME_PrintName = "PrintName";

  /** Set Print Text. The label text to be printed on a document or correspondence. */
  public void setPrintName(String PrintName);

  /** Get Print Text. The label text to be printed on a document or correspondence. */
  public String getPrintName();

  /** Column name Processing */
  public static final String COLUMNNAME_Processing = "Processing";

  /** Set Process Now */
  public void setProcessing(boolean Processing);

  /** Get Process Now */
  public boolean isProcessing();

  /** Column name TimePattern */
  public static final String COLUMNNAME_TimePattern = "TimePattern";

  /** Set Time Pattern. Java Time Pattern */
  public void setTimePattern(String TimePattern);

  /** Get Time Pattern. Java Time Pattern */
  public String getTimePattern();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  void saveEx(String trxName);
}
