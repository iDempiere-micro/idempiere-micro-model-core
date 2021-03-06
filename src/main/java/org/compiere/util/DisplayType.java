package org.compiere.util;

import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Language;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;

/**
 * System Display Types.
 *
 * <pre>
 * SELECT AD_Reference_ID, Name FROM AD_Reference WHERE ValidationType = 'D'
 *  </pre>
 *
 * @author Jorg Janke
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * <li>BF [ 1810632 ] PricePrecision error in InfoProduct (and similar)
 * @version $Id: DisplayType.java,v 1.6 2006/08/30 20:30:44 comdivision Exp $
 */
public final class DisplayType {
    /**
     * Display Type 10 String
     */
    public static final int String = SystemIDs.REFERENCE_DATATYPE_STRING;
    /**
     * Display Type 11 Integer
     */
    public static final int Integer = SystemIDs.REFERENCE_DATATYPE_INTEGER;
    /**
     * Display Type 12 Amount
     */
    public static final int Amount = SystemIDs.REFERENCE_DATATYPE_AMOUNT;
    /**
     * Display Type 13 ID
     */
    public static final int ID = SystemIDs.REFERENCE_DATATYPE_ID;
    /**
     * Display Type 14 Text
     */
    public static final int Text = SystemIDs.REFERENCE_DATATYPE_TEXT;
    /**
     * Display Type 15 Date
     */
    public static final int Date = SystemIDs.REFERENCE_DATATYPE_DATE;
    /**
     * Display Type 16 DateTime
     */
    public static final int DateTime = SystemIDs.REFERENCE_DATATYPE_DATETIME;
    /**
     * Display Type 17 List
     */
    public static final int List = SystemIDs.REFERENCE_DATATYPE_LIST;
    /**
     * Display Type 18 Table
     */
    public static final int Table = SystemIDs.REFERENCE_DATATYPE_TABLE;
    /**
     * Display Type 19 TableDir
     */
    public static final int TableDir = SystemIDs.REFERENCE_DATATYPE_TABLEDIR;
    /**
     * Display Type 20 YN
     */
    public static final int YesNo = SystemIDs.REFERENCE_DATATYPE_YES_NO;
    /**
     * Display Type 21 Location
     */
    public static final int Location = SystemIDs.REFERENCE_DATATYPE_LOCATION;
    /**
     * Display Type 22 Number
     */
    public static final int Number = SystemIDs.REFERENCE_DATATYPE_NUMBER;
    /**
     * Display Type 23 BLOB
     */
    public static final int Binary = SystemIDs.REFERENCE_DATATYPE_BINARY;
    /**
     * Display Type 24 Time
     */
    public static final int Time = SystemIDs.REFERENCE_DATATYPE_TIME;
    /**
     * Display Type 25 Account
     */
    public static final int Account = SystemIDs.REFERENCE_DATATYPE_ACCOUNT;
    /**
     * Display Type 27 Color
     */
    public static final int Color = SystemIDs.REFERENCE_DATATYPE_COLOR;
    /**
     * Display Type 28 Button
     */
    public static final int Button = SystemIDs.REFERENCE_DATATYPE_BUTTON;
    /**
     * Display Type 29 Quantity
     */
    public static final int Quantity = SystemIDs.REFERENCE_DATATYPE_QUANTITY;
    /**
     * Display Type 30 Search
     */
    public static final int Search = SystemIDs.REFERENCE_DATATYPE_SEARCH;
    /**
     * Display Type 31 Locator
     */
    public static final int Locator = SystemIDs.REFERENCE_DATATYPE_LOCATOR;
    /**
     * Display Type 32 Image
     */
    public static final int Image = SystemIDs.REFERENCE_DATATYPE_IMAGE;
    /**
     * Display Type 33 Assignment
     */
    public static final int Assignment = SystemIDs.REFERENCE_DATATYPE_ASSIGNMENT;
    /**
     * Display Type 34 Memo
     */
    public static final int Memo = SystemIDs.REFERENCE_DATATYPE_MEMO;
    /**
     * Display Type 35 PAttribute
     */
    public static final int PAttribute = SystemIDs.REFERENCE_DATATYPE_PRODUCTATTRIBUTE;
    /**
     * Display Type 36 CLOB
     */
    public static final int TextLong = SystemIDs.REFERENCE_DATATYPE_TEXTLONG;
    /**
     * Display Type 37 CostPrice
     */
    public static final int CostPrice = SystemIDs.REFERENCE_DATATYPE_COSTPRICE;
    /**
     * Display Type 38 File Path
     */
    public static final int FilePath = SystemIDs.REFERENCE_DATATYPE_FILEPATH;
    /**
     * Display Type 39 File Name
     */
    public static final int FileName = SystemIDs.REFERENCE_DATATYPE_FILENAME;
    /**
     * Display Type 40 URL
     */
    public static final int URL = SystemIDs.REFERENCE_DATATYPE_URL;
    /**
     * Display Type 42 PrinterName
     */
    public static final int PrinterName = SystemIDs.REFERENCE_DATATYPE_PRINTNAME;
    //	Candidates:
    /**
     * Display Type 200012 Payment
     */
    public static final int Payment = SystemIDs.REFERENCE_DATATYPE_PAYMENT;

    public static final int Chart = SystemIDs.REFERENCE_DATATYPE_CHART;

    public static final int SingleSelectionGrid = SystemIDs.REFERENCE_DATATYPE_SINGLE_SELECTION_GRID;

    public static final int MultipleSelectionGrid =
            SystemIDs.REFERENCE_DATATYPE_MULTIPLE_SELECTION_GRID;

    /**
     * - New Display Type INSERT INTO AD_REFERENCE (AD_REFERENCE_ID,
     * AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY, NAME,DESCRIPTION,HELP,
     * VALIDATIONTYPE,VFORMAT,ENTITYTYPE) VALUES (35, 0,0,'Y',SysDate,0,SysDate,0,
     * 'PAttribute','Product Attribute',null,'D',null,'D');
     *
     * <p>- org.compiere.model.MModel (??) - org.compiere.grid.ed.VEditor/Dialog -
     * org.compiere.grid.ed.VEditorFactory - RColumn, WWindow add/check 0_cleanupAD.sql
     */

    //  See DBA_DisplayType.sql ----------------------------------------------
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    public static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * Maximum number of digits
     */
    private static final int MAX_DIGITS = 28; //  Oracle Standard Limitation 38 digits
    /**
     * Digits of an Integer
     */
    private static final int INTEGER_DIGITS = 10;
    /**
     * Maximum number of fractions
     */
    private static final int MAX_FRACTION = 12;
    /**
     * Default Amount Precision
     */
    private static final int AMOUNT_FRACTION = 2;
    /**
     * Logger
     */
    private static CLogger s_log = CLogger.getCLogger(DisplayType.class);

    /**
     * Returns true if (numeric) ID (Table, Search, Account, ..). (stored as Integer)
     *
     * @param displayType Display Type
     * @return true if ID
     */
    public static boolean isID(int displayType) {
        return displayType == ID
                || displayType == Table
                || displayType == TableDir
                || displayType == Search
                || displayType == Location
                || displayType == Locator
                || displayType == Account
                || displayType == Assignment
                || displayType == PAttribute
                || displayType == Image
                || displayType == Chart
                || displayType == Color;

    } //	isID

    /**
     * Returns true, if DisplayType is numeric (Amount, Number, Quantity, Integer). (stored as
     * BigDecimal)
     *
     * @param displayType Display Type
     * @return true if numeric
     */
    public static boolean isNumeric(int displayType) {
        return displayType == Amount
                || displayType == Number
                || displayType == CostPrice
                || displayType == Integer
                || displayType == Quantity;

    } //	isNumeric

    /**
     * Returns true, if DisplayType is text (String, Text, TextLong, Memo).
     *
     * @param displayType Display Type
     * @return true if text
     */
    public static boolean isText(int displayType) {
        return displayType == String
                || displayType == Text
                || displayType == TextLong
                || displayType == Memo
                || displayType == FilePath
                || displayType == FileName
                || displayType == URL
                || displayType == PrinterName
                || displayType == SingleSelectionGrid
                || displayType == Color
                || displayType == MultipleSelectionGrid;

    } //	isText

    /**
     * Returns true if DisplayType is a Date. (stored as Timestamp)
     *
     * @param displayType Display Type
     * @return true if date
     */
    public static boolean isDate(int displayType) {
        return displayType == Date || displayType == DateTime || displayType == Time;

    } //	isDate

    /**
     * Returns true if DisplayType is a VLookup (List, Table, TableDir, Search). (stored as Integer)
     *
     * @param displayType Display Type
     * @return true if Lookup
     */
    public static boolean isLookup(int displayType) {
        return displayType == List
                || displayType == Table
                || displayType == TableDir
                || displayType == Search;

    } //	isLookup

    /**
     * Returns true if DisplayType is a Large Object
     *
     * @param displayType Display Type
     * @return true if LOB
     */
    public static boolean isLOB(int displayType) {
        return displayType == Binary || displayType == TextLong;

    } //	isLOB

    /**
     * ************************************************************************ Return Format for
     * numeric DisplayType
     *
     * @param displayType Display Type (default Number)
     * @param language    Language
     * @param pattern     Java Number Format pattern e.g. "#,##0.00"
     * @return number format
     */
    public static DecimalFormat getNumberFormat(int displayType, Language language, String pattern) {
        Language myLanguage = language;
        if (myLanguage == null) myLanguage = Language.getLoginLanguage();
        Locale locale = myLanguage.getLocale();
        DecimalFormat format = null;
        if (locale != null) format = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        else format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        //
        if (pattern != null && pattern.length() > 0) {
            try {
                format.applyPattern(pattern);
                return format;
            } catch (IllegalArgumentException e) {
                s_log.log(Level.WARNING, "Invalid number format: " + pattern);
            }
        } else if (displayType == Integer) {
            format.setParseIntegerOnly(true);
            format.setMaximumIntegerDigits(INTEGER_DIGITS);
            format.setMaximumFractionDigits(0);
        } else if (displayType == Quantity) {
            format.setMaximumIntegerDigits(MAX_DIGITS);
            format.setMaximumFractionDigits(MAX_FRACTION);
        } else if (displayType == Amount) {
            format.setMaximumIntegerDigits(MAX_DIGITS);
            format.setMaximumFractionDigits(MAX_FRACTION);
            format.setMinimumFractionDigits(AMOUNT_FRACTION);
        } else if (displayType == CostPrice) {
            format.setMaximumIntegerDigits(MAX_DIGITS);
            format.setMaximumFractionDigits(MAX_FRACTION);
            format.setMinimumFractionDigits(AMOUNT_FRACTION);
        } else {
            format.setMaximumIntegerDigits(MAX_DIGITS);
            format.setMaximumFractionDigits(MAX_FRACTION);
            format.setMinimumFractionDigits(1);
        }
        return format;
    } //	getDecimalFormat

    /**
     * ************************************************************************ Return Format for
     * numeric DisplayType
     *
     * @param displayType Display Type (default Number)
     * @param language    Language
     * @return number format
     */
    public static DecimalFormat getNumberFormat(int displayType, Language language) {
        return getNumberFormat(displayType, language, null);
    }

    /**
     * Return Format for numeric DisplayType
     *
     * @param displayType Display Type
     * @return number format
     */
    public static DecimalFormat getNumberFormat(int displayType) {
        return getNumberFormat(displayType, null);
    } //  getNumberFormat

    /**
     * *********************************************************************** Return Date Format
     *
     * @return date format
     */
    public static SimpleDateFormat getDateFormat() {
        return getDateFormat(DisplayType.Date, null);
    } //  getDateFormat

    /**
     * Return format for date displayType
     *
     * @param displayType Display Type
     * @return date format
     */
    public static SimpleDateFormat getDateFormat(int displayType) {
        return getDateFormat(displayType, null);
    } //  getDateFormat

    /**
     * Return format for date displayType
     *
     * @param displayType Display Type (default Date)
     * @param language    Language
     * @return date format
     */
    public static SimpleDateFormat getDateFormat(int displayType, Language language) {
        return getDateFormat(displayType, language, null);
    }

    /**
     * Return format for date displayType
     *
     * @param displayType Display Type (default Date)
     * @param language    Language
     * @param pattern     Java Simple Date Format pattern e.g. "dd/MM/yy"
     * @return date format
     */
    public static SimpleDateFormat getDateFormat(int displayType, Language language, String pattern) {
        Language myLanguage = language;
        if (myLanguage == null) myLanguage = Env.getLanguage();
        //
        if (pattern != null && pattern.length() > 0) {
            SimpleDateFormat format =
                    (SimpleDateFormat)
                            DateFormat.getDateTimeInstance(
                                    DateFormat.SHORT, DateFormat.SHORT, language.getLocale());
            try {
                format.applyPattern(pattern);
                return format;
            } catch (IllegalArgumentException e) {
                s_log.log(Level.WARNING, "Invalid date pattern: " + pattern);
            }
        }

        if (displayType == DateTime) return myLanguage.getDateTimeFormat();
        else if (displayType == Time) return myLanguage.getTimeFormat();

        //	else if (displayType == Date)
        return myLanguage.getDateFormat(); // 	default
    } //	getDateFormat

    /**
     * JDBC Date Format YYYY-MM-DD
     *
     * @return date format
     */
    public static SimpleDateFormat getDateFormatInJDBC() {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    } //  getDateFormatInJDBC

    /**
     * JDBC Timestamp Format yyyy-mm-dd hh:mm:ss
     *
     * @return timestamp format
     */
    public static SimpleDateFormat getDefaultTimestampFormat() {
        return new SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT);
    } //  getTimestampFormat_JDBC

    public static SimpleDateFormat getDefaultTimeFormat() {
        return new SimpleDateFormat(DEFAULT_TIME_FORMAT);
    } //  getDefaultTimeFormat

    /**
     * Return Storage Class. (used for MiniTable)
     *
     * @param displayType    Display Type
     * @param yesNoAsBoolean - yes or no as boolean
     * @return class Integer - BigDecimal - Timestamp - String - Boolean
     */
    public static Class<?> getClass(int displayType, boolean yesNoAsBoolean) {
        if (isText(displayType) || displayType == List || displayType == Payment) return String.class;
        else if (isID(displayType) || displayType == Integer) //  note that Integer is stored as BD
            return Integer.class;
        else if (isNumeric(displayType)) return java.math.BigDecimal.class;
        else if (isDate(displayType)) return java.sql.Timestamp.class;
        else if (displayType == YesNo) return yesNoAsBoolean ? Boolean.class : String.class;
        else if (displayType == Button) return String.class;
        else if (isLOB(displayType)) // 	CLOB is String
            return byte[].class;
        //
        return Object.class;
    } //  getClass

    /**
     * Get SQL DataType
     *
     * @param displayType AD_Reference_ID
     * @param columnName  name
     * @param fieldLength length
     * @return SQL Data Type in Oracle Notation
     */
    public static String getSQLDataType(int displayType, String columnName, int fieldLength) {
        if ("EntityType".equals(columnName) || "AD_Language".equals(columnName))
            return "VARCHAR2(" + fieldLength + ")";
        //	ID
        if (DisplayType.isID(displayType)) {
            if (displayType == DisplayType.Image // 	FIXTHIS
                    && "BinaryData".equals(columnName)) return "BLOB";
                //	ID, CreatedBy/UpdatedBy, Acct
            else if (columnName.endsWith("_ID")
                    || columnName.endsWith("tedBy")
                    || columnName.endsWith("_Acct")) return "NUMBER(10)";
            else if (fieldLength < 4) return "CHAR(" + fieldLength + ")";
            else //	EntityType, AD_Language	fallback
                return "VARCHAR2(" + fieldLength + ")";
        }
        //
        if (displayType == DisplayType.Integer) return "NUMBER(10)";
        if (DisplayType.isDate(displayType)) return "DATE";
        if (DisplayType.isNumeric(displayType)) return "NUMBER";
        if (displayType == DisplayType.Binary) return "BLOB";
        if (displayType == DisplayType.TextLong
                || (displayType == DisplayType.Text && fieldLength >= 4000)) return "CLOB";
        if (displayType == DisplayType.YesNo) return "CHAR(1)";
        if (displayType == DisplayType.List || displayType == DisplayType.Payment) {
            if (fieldLength == 1) return "CHAR(" + fieldLength + ")";
            else return "VARCHAR2(" + fieldLength + ")";
        }
        if (displayType
                == DisplayType.Color) // this condition is never reached - filtered above in isID
        {
            if (columnName.endsWith("_ID")) return "NUMBER(10)";
            else return "CHAR(" + fieldLength + ")";
        }
        if (displayType == DisplayType.Button) {
            if (columnName.endsWith("_ID")) return "NUMBER(10)";
            else return "CHAR(" + fieldLength + ")";
        }

        if (!DisplayType.isText(displayType)) s_log.severe("Unhandled Data Type = " + displayType);

        return "VARCHAR2(" + fieldLength + ")";
    } //	getSQLDataType

} //	DisplayType
