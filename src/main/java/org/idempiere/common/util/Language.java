package org.idempiere.common.util;

import static software.hsharp.core.util.DBKt.getSQLValueStringEx;
import static software.hsharp.core.util.DBKt.isConnected;

import java.awt.ComponentOrientation;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import javax.print.attribute.standard.MediaSize;

/**
 * Language Management.
 *
 * @author Jorg Janke
 * @version $Id: Language.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class Language implements Serializable {
  /** */
  private static final long serialVersionUID = 8855937839841807335L;

  /** */
  /**
   * ************************************************************************ Languages
   * http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt Countries
   * http://www.iso.org/iso/country_codes/iso_3166_code_lists/english_country_names_and_code_elements.htm
   * ***********************************************************************
   */

  /** Base Language */
  private static final String AD_Language_en_US = "en_US";

  /**
   * * System Languages. If you want to add a language, use the method getLanguage which extends the
   * array
   */
  private static List<Language> s_languages = new ArrayList<Language>();

  /** Base Language */
  private static Language s_baseLanguage = null;

  private static boolean isBaseLanguageSet = false;
  /** Logger */
  private static CLogger log = CLogger.getCLogger(Language.class.getName());

  static {
    s_languages.add(
        new Language("English", AD_Language_en_US, Locale.US, null, null, MediaSize.NA.LETTER));
    s_baseLanguage = s_languages.get(0);
  }

  /** Name */
  private String m_name;
  /** Language (key) */
  private String m_AD_Language;
  /** Locale */
  private Locale m_locale;
  //
  private Boolean m_decimalPoint;
  private Boolean m_leftToRight;
  private SimpleDateFormat m_dateFormat;
  private MediaSize m_mediaSize = MediaSize.ISO.A4;
  private boolean m_fromDB = false;

  /**
   * ************************************************************************ Define Language
   *
   * @param name - displayed value, e.g. English
   * @param AD_Language - the code of system supported language, e.g. en_US (might be different than
   *     Locale - i.e. if the system does not support the language)
   * @param locale - the Locale, e.g. Locale.US
   * @param decimalPoint true if Decimal Point - if null, derived from Locale
   * @param javaDatePattern Java date pattern as not all locales are defined - if null, derived from
   *     Locale
   * @param mediaSize default media size
   */
  public Language(
      String name,
      String AD_Language,
      Locale locale,
      Boolean decimalPoint,
      String javaDatePattern,
      MediaSize mediaSize) {
    if (name == null || AD_Language == null || locale == null)
      throw new IllegalArgumentException("Language - parameter is null");
    m_name = name;
    m_AD_Language = AD_Language;
    m_locale = locale;
    //
    m_decimalPoint = decimalPoint;
    setDateFormat(javaDatePattern);
    setMediaSize(mediaSize);
  } //  Language

  /**
   * Define Language with A4 and default decimal point and date format
   *
   * @param name - displayed value, e.g. English
   * @param AD_Language - the code of system supported language, e.g. en_US (might be different than
   *     Locale - i.e. if the system does not support the language)
   * @param locale - the Locale, e.g. Locale.US
   */
  public Language(String name, String AD_Language, Locale locale) {
    this(name, AD_Language, locale, null, null, null);
  } //	Language

  /**
   * Get Number of Languages
   *
   * @return Language count
   */
  public static int getLanguageCount() {
    return s_languages.size();
  } //  getLanguageCount

  /**
   * Get Language
   *
   * @param index index
   * @return Language
   */
  public static Language getLanguage(int index) {
    if (index < 0 || index >= s_languages.size()) return s_baseLanguage;
    return s_languages.get(index);
  } //  getLanguage

  /**
   * ************************************************************************ Get Language. If
   * language does not exist, create it on the fly assuming that it is valid
   *
   * @param langInfo either language (en) or locale (en-US) or display name
   * @return Name (e.g. Deutsch)
   */
  public static synchronized Language getLanguage(String langInfo) {
    int idxReplace = -1;
    String lang = langInfo;
    if (lang == null || lang.length() == 0) lang = System.getProperty("user.language", "");

    //	Search existing Languages
    for (int i = 0; i < s_languages.size(); i++) {
      if (lang.equals(s_languages.get(i).getADLanguage())
          || lang.equals(s_languages.get(i).getLanguageCode())
          || lang.equals(s_languages.get(i).getName())) {
        if (!s_languages.get(i).m_fromDB && isConnected()) {
          // if language was not get from DB and now we're connected
          idxReplace = i;
          break;
        } else {
          return s_languages.get(i);
        }
      }
    }

    //	Create Language on the fly
    if (lang.length() == 5) // 	standard format <language>_<Country>
    {
      Language ll = null;
      String language = lang.substring(0, 2);
      String country = lang.substring(3);
      Locale locale = new Locale(language, country);
      /* DAP TODO
      if (isConnected()) {
      	// first time connected?
      	if (!isBaseLanguageSet) {
      		setBaseLanguage();
      	}
      	MLanguage dblang = MLanguage.get(Env.getCtx(), langInfo);
      	if (dblang != null) {
      		if (!(   language.equals(dblang.getLanguageISO())
      			  && country.equals(dblang.getCountryCode())
      			 )
      			) {
      			locale = new Locale(dblang.getLanguageISO(), dblang.getCountryCode());
      		}
      		MediaSize mediaSize = MediaSize.ISO.A4;
      		ll = new Language(dblang.getPrintName(), langInfo, locale, null, dblang.getDatePattern(), mediaSize);
      		ll.m_fromDB = true;
      		if (dblang.isBaseLanguage()) {
      			idxReplace = 0;
      			if (dblang.isSystemLanguage()) {
      				// base language is uploaded also as System language, don't use base language but the corresponding translation
      				s_baseLanguage = new Language ("no-base", "xx_XX", locale);
      			} else {
      				s_baseLanguage = ll;
      			}
      		}
      	}
      }*/
      if (ll == null) {
        ll = new Language(lang, lang, locale);
      }
      if (log.isLoggable(Level.INFO)) {
        StringBuilder msglog =
            new StringBuilder("Adding Language=")
                .append(language)
                .append(", Country=")
                .append(country)
                .append(", Locale=")
                .append(locale);
        log.info(msglog.toString());
      }
      if (idxReplace >= 0) {
        s_languages.set(idxReplace, ll);
      } else {
        s_languages.add(ll);
      }
      return ll;
    }
    //	Get the default one
    return s_baseLanguage;
  } //  getLanguage

  private static void setBaseLanguage() {
    isBaseLanguageSet = true;
    String baselang =
        getSQLValueStringEx(
            null,
            "SELECT AD_Language FROM AD_Language WHERE IsActive='Y' AND IsBaseLanguage = 'Y'");
    if (baselang != null) {
      getLanguage(baselang);
    }
  }

  /**
   * Is it the base language
   *
   * @param langInfo either language (en) or locale (en-US) or display name
   * @return true if base language
   */
  public static boolean isBaseLanguage(String langInfo) {
    return langInfo == null
        || langInfo.length() == 0
        || langInfo.equals(s_baseLanguage.getName())
        || langInfo.equals(s_baseLanguage.getLanguageCode())
        || langInfo.equals(s_baseLanguage.getADLanguage());
  } //  isBaseLanguage

  /**
   * Get Base Language
   *
   * @return Base Language
   */
  public static Language getBaseLanguage() {
    return s_baseLanguage;
  } //  getBase

  /**
   * Get Base Language code. (e.g. en-US)
   *
   * @return Base Language
   */
  public static String getBaseAD_Language() {
    return s_baseLanguage.getADLanguage();
  } //  getBase

  /**
   * Get Supported Locale
   *
   * @param langInfo either language (en) or locale (en-US) or display name
   * @return Supported Locale
   */
  public static Locale getLocale(String langInfo) {
    return getLanguage(langInfo).getLocale();
  } //  getLocale

  /**
   * Get Supported Language
   *
   * @param langInfo either language (en) or locale (en-US) or display name
   * @return AD_Language (e.g. en-US)
   */
  public static String getADLanguage(String langInfo) {
    return getLanguage(langInfo).getADLanguage();
  } //  getADLanguage

  /**
   * Get Supported Language
   *
   * @param locale Locale
   * @return AD_Language (e.g. en-US)
   */
  public static String getADLanguage(Locale locale) {
    if (locale != null) {
      for (int i = 0; i < s_languages.size(); i++) {
        if (locale.getLanguage().equals(s_languages.get(i).getLocale().getLanguage()))
          return s_languages.get(i).getADLanguage();
      }
    }
    return s_baseLanguage.getADLanguage();
  } //  getLocale

  /**
   * Get Language Name
   *
   * @param langInfo either language (en) or locale (en-US) or display name
   * @return Language Name (e.g. English)
   */
  public static String getName(String langInfo) {
    return getLanguage(langInfo).getName();
  } //  getADLanguage

  /**
   * Returns true if Decimal Point (not comma)
   *
   * @param langInfo either language (en) or locale (en-US) or display name
   * @return use of decimal point
   */
  public static boolean isDecimalPoint(String langInfo) {
    return getLanguage(langInfo).isDecimalPoint();
  } //  getADLanguage

  /**
   * Get Display names of supported languages
   *
   * @return Array of Language names
   */
  public static String[] getNames() {
    String[] retValue = new String[s_languages.size()];
    for (int i = 0; i < s_languages.size(); i++) retValue[i] = s_languages.get(i).getName();
    return retValue;
  } //  getNames

  /**
   * ************************************************************************ Get Current Login
   * Language
   *
   * @return login language
   */
  public static Language getLoginLanguage() {
    return Env.getLanguage(Env.getCtx());
  } //  getLanguage

  /**
   * Set Current Login Language
   *
   * @param language language
   */
  public static void setLoginLanguage(Language language) {
    if (language != null) {
      Env.setContext(Env.getCtx(), Env.LANGUAGE, language.getADLanguage());
      if (log.isLoggable(Level.CONFIG)) log.config(language.toString());
    }
  } //  setLanguage

  /**
   * Get Language Name. e.g. English
   *
   * @return name
   */
  public String getName() {
    return m_name;
  } //  getName

  /**
   * Get Application Dictionary Language (system supported). e.g. en-US
   *
   * @return AD_Language
   */
  public String getADLanguage() {
    return m_AD_Language;
  } //  getADLanguage

  /**
   * Set Application Dictionary Language (system supported).
   *
   * @param AD_Language e.g. en-US
   */
  public void setADLanguage(String AD_Language) {
    if (AD_Language != null) {
      m_AD_Language = AD_Language;
      if (log.isLoggable(Level.CONFIG)) log.config(toString());
    }
  } //  getADLanguage

  /**
   * Get Locale
   *
   * @return locale
   */
  public Locale getLocale() {
    return m_locale;
  } //  getLocale

  /**
   * Overwrite Locale
   *
   * @param locale locale
   */
  public void setLocale(Locale locale) {
    if (locale == null) return;
    m_locale = locale;
    m_decimalPoint = null; //  reset
  } //  getLocale

  /**
   * Get Language Code. e.g. en - derived from Locale
   *
   * @return language code
   */
  public String getLanguageCode() {
    return m_locale.getLanguage();
  } //  getLanguageCode

  /**
   * Component orientation is Left To Right
   *
   * @return true if left-to-right
   */
  public boolean isLeftToRight() {
    if (m_leftToRight == null)
      //  returns true if language not iw, ar, fa, ur
      m_leftToRight = new Boolean(ComponentOrientation.getOrientation(m_locale).isLeftToRight());
    return m_leftToRight.booleanValue();
  } //  isLeftToRight

  /**
   * Returns true if Decimal Point (not comma)
   *
   * @return use of decimal point
   */
  public boolean isDecimalPoint() {
    if (m_decimalPoint == null) {
      DecimalFormatSymbols dfs = new DecimalFormatSymbols(m_locale);
      m_decimalPoint = new Boolean(dfs.getDecimalSeparator() == '.');
    }
    return m_decimalPoint.booleanValue();
  } //  isDecimalPoint

  /**
   * Is This the Base Language
   *
   * @return true if base Language
   */
  public boolean isBaseLanguage() {
    return this.equals(getBaseLanguage());
  } //	isBaseLanguage

  public static void setBaseLanguage(String baselang) {
    Language lang = getLanguage(baselang);
    s_baseLanguage = lang;
  }

  /**
   * Get (Short) Date Format. The date format must parseable by org.idempiere.grid.ed.MDocDate i.e.
   * leading zero for date and month
   *
   * @return date format MM/dd/yyyy - dd.MM.yyyy
   */
  public SimpleDateFormat getDateFormat() {
    if (m_dateFormat == null) {
      m_dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, m_locale);
      String sFormat = m_dateFormat.toPattern();
      //	some short formats have only one M and/or d (e.g. ths US)
      if (sFormat.indexOf("MM") == -1 || sFormat.indexOf("dd") == -1) {
        sFormat = sFormat.replaceFirst("d+", "dd");
        sFormat = sFormat.replaceFirst("M+", "MM");
        //	log.finer(sFormat + " => " + nFormat);
        m_dateFormat.applyPattern(sFormat);
      }
      //	Unknown short format => use JDBC
      if (m_dateFormat.toPattern().length() != 8) m_dateFormat.applyPattern("yyyy-MM-dd");

      //	4 digit year
      if (m_dateFormat.toPattern().indexOf("yyyy") == -1) {
        sFormat = m_dateFormat.toPattern();
        StringBuilder nFormat = new StringBuilder();
        for (int i = 0; i < sFormat.length(); i++) {
          if (sFormat.charAt(i) == 'y') nFormat.append("yy");
          else nFormat.append(sFormat.charAt(i));
        }
        m_dateFormat.applyPattern(nFormat.toString());
      }
      m_dateFormat.setLenient(true);
    }
    return m_dateFormat;
  } //  getDateFormat

  /**
   * Set Date Pattern. The date format is not checked for correctness
   *
   * @param javaDatePattern for details see java.text.SimpleDateFormat, format must be able to be
   *     converted to database date format by using the upper case function. It also must have
   *     leading zero for day and month.
   */
  public void setDateFormat(String javaDatePattern) {
    if (javaDatePattern == null) return;
    m_dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, m_locale);
    try {
      m_dateFormat.applyPattern(javaDatePattern);
    } catch (Exception e) {
      log.severe(javaDatePattern + " - " + e);
      m_dateFormat = null;
    }
  } //  setDateFormat

  /**
   * Get Date Time Format. Used for Display only
   *
   * @return Date Time format MMM d, yyyy h:mm:ss a z -or- dd.MM.yyyy HH:mm:ss z -or- j nnn aaaa, H'
   *     ?????? 'm' ????'
   */
  public SimpleDateFormat getDateTimeFormat() {
    SimpleDateFormat retValue =
        (SimpleDateFormat)
            DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG, m_locale);
    //	if (log.isLoggable(Level.FINER)) log.finer("Pattern=" + retValue.toLocalizedPattern() + ",
    // Loc=" + retValue.toLocalizedPattern());
    return retValue;
  } //	getDateTimeFormat

  /**
   * Get Time Format. Used for Display only
   *
   * @return Time format h:mm:ss z or HH:mm:ss z
   */
  public SimpleDateFormat getTimeFormat() {
    return (SimpleDateFormat) DateFormat.getTimeInstance(DateFormat.LONG, m_locale);
  } //	getTimeFormat

  /**
   * Get Database Date Pattern. Derive from date pattern (make upper case)
   *
   * @return date pattern
   */
  public String getDBdatePattern() {
    return getDateFormat().toPattern().toUpperCase(m_locale);
  } //  getDBdatePattern

  /**
   * Get default MediaSize
   *
   * @return media size
   */
  public MediaSize getMediaSize() {
    return m_mediaSize;
  } //	getMediaSize

  /**
   * Set default MediaSize
   *
   * @param size media size
   */
  public void setMediaSize(MediaSize size) {
    if (size != null) m_mediaSize = size;
  } //	setMediaSize

  /**
   * String Representation
   *
   * @return string representation
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("Language=[");
    sb.append(m_name)
        .append(",Locale=")
        .append(m_locale.toString())
        .append(",AD_Language=")
        .append(m_AD_Language)
        .append(",DatePattern=")
        .append(getDBdatePattern())
        .append(",DecimalPoint=")
        .append(isDecimalPoint())
        .append("]");
    return sb.toString();
  } //  toString

  /**
   * Hash Code
   *
   * @return hashcode
   */
  public int hashCode() {
    return m_AD_Language.hashCode();
  } //	hashcode

  /**
   * Equals. Two languages are equal, if they have the same AD_Language
   *
   * @param obj compare
   * @return true if AD_Language is the same
   */
  public boolean equals(Object obj) {
    if (obj instanceof Language) {
      Language cmp = (Language) obj;
      return cmp.getADLanguage().equals(m_AD_Language);
    }
    return false;
  } //	equals
} //  Language
