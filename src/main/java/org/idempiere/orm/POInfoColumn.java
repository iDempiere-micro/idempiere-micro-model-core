package org.idempiere.orm;

import org.idempiere.common.util.CLogger;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Level;

/**
 * PO Info Column Info Value Object
 *
 * @author Jorg Janke
 * @version $Id: POInfoColumn.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public class POInfoColumn implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3882249785085847367L;

    /**
     * Used by Remote FinReport
     */
    public static Class<?> ColumnClass_String = String.class;

    public static Class<?> ColumnClass_Boolean = Boolean.class;
    public static Class<?> ColumnClass_Integer = Integer.class;
    /**
     * Column ID
     */
    public int AD_Column_ID;
    /**
     * Column Name
     */
    public String ColumnName;
    /**
     * Virtual Column
     */
    public String ColumnSQL;
    /**
     * Display Type
     */
    public int DisplayType;
    /**
     * Data Type
     */
    public Class<?> ColumnClass;
    /**
     * Mandatory
     */
    public boolean IsMandatory;
    /**
     * Default Value
     */
    public String DefaultLogic;
    /**
     * Updateable
     */
    public boolean IsUpdateable;
    /**
     * Label
     */
    public String ColumnLabel;
    /**
     * Description
     */
    public String ColumnDescription;
    /**
     * PK
     */
    public boolean IsKey;
    /**
     * FK to Parent
     */
    public boolean IsParent;
    /**
     * Translated
     */
    public boolean IsTranslated;
    /**
     * Encrypted
     */
    public boolean IsEncrypted;
    /**
     * Allow Logging
     */
    public boolean IsAllowLogging;
    /**
     * Allow Copy
     */
    public boolean IsAllowCopy;
    /**
     * Field Length
     */
    public int FieldLength;
    /**
     * Min Value
     */
    public String ValueMin;
    /**
     * Max Value
     */
    public String ValueMax;
    /**
     * Min Value
     */
    public BigDecimal ValueMin_BD = null;
    /**
     * Max Value
     */
    public BigDecimal ValueMax_BD = null;

    /**
     * Constructor
     *
     * @param ad_Column_ID          Column ID
     * @param columnName            Column name
     * @param columnSQL             virtual column
     * @param displayType           Display Type
     * @param isMandatory           Mandatory
     * @param isUpdateable          Updateable
     * @param defaultLogic          Default Logic
     * @param columnLabel           Column Label
     * @param columnDescription     Column Description
     * @param isKey                 true if key
     * @param isParent              true if parent
     * @param ad_Reference_Value_ID reference value
     * @param validationCode        sql validation code
     * @param fieldLength           Field Length
     * @param valueMin              minimal value
     * @param valueMax              maximal value
     * @param isTranslated          translated
     * @param isEncrypted           encrypted
     * @param isAllowLogging        allow logging
     * @param isAllowCopy           allow copy
     */
    public POInfoColumn(
            int ad_Column_ID,
            String columnName,
            String columnSQL,
            int displayType,
            boolean isMandatory,
            boolean isUpdateable,
            String defaultLogic,
            String columnLabel,
            String columnDescription,
            boolean isKey,
            boolean isParent,
            int ad_Reference_Value_ID,
            String validationCode,
            int fieldLength,
            String valueMin,
            String valueMax,
            boolean isTranslated,
            boolean isEncrypted,
            boolean isAllowLogging,
            boolean isAllowCopy) {
        AD_Column_ID = ad_Column_ID;
        ColumnName = columnName;
        ColumnSQL = columnSQL;
        DisplayType = displayType;
        if (columnName.equals("AD_Language") || columnName.equals("EntityType")) {
            DisplayType = org.compiere.util.DisplayType.String;
            ColumnClass = String.class;
        } else if (columnName.equals("Posted")
                || columnName.equals("Processed")
                || columnName.equals("Processing")) {
            ColumnClass = Boolean.class;
        } else if (columnName.equals("Record_ID")) {
            DisplayType = org.compiere.util.DisplayType.ID;
            ColumnClass = Integer.class;
        } else ColumnClass = org.compiere.util.DisplayType.getClass(displayType, true);
        IsMandatory = isMandatory;
        IsUpdateable = isUpdateable;
        DefaultLogic = defaultLogic;
        ColumnLabel = columnLabel;
        ColumnDescription = columnDescription;
        IsKey = isKey;
        IsParent = isParent;
        //
        //
        FieldLength = fieldLength;
        ValueMin = valueMin;
        try {
            if (valueMin != null && valueMin.length() > 0) ValueMin_BD = new BigDecimal(valueMin);
        } catch (Exception ex) {
            CLogger.get().log(Level.SEVERE, "ValueMin=" + valueMin, ex);
        }
        ValueMax = valueMax;
        try {
            if (valueMax != null && valueMax.length() > 0) ValueMax_BD = new BigDecimal(valueMax);
        } catch (Exception ex) {
            CLogger.get().log(Level.SEVERE, "ValueMax=" + valueMax, ex);
        }
        IsTranslated = isTranslated;
        IsEncrypted = isEncrypted;
        IsAllowLogging = isAllowLogging;
        IsAllowCopy = isAllowCopy;
    } //  Column

    /**
     * String representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("POInfo.Column[");
        sb.append(ColumnName)
                .append(",ID=")
                .append(AD_Column_ID)
                .append(",DisplayType=")
                .append(DisplayType)
                .append(",ColumnClass=")
                .append(ColumnClass);
        sb.append("]");
        return sb.toString();
    } //	toString
} //	POInfoColumn
