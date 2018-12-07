package org.compiere.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.idempiere.common.util.Language;

/**
 * @author Jan Thielemann - jan.thielemann@evenos-consulting.de
 * @author evenos Consulting GmbH - www.evenos.org
 */
public interface IDisplayTypeFactory {

    boolean isID(int displayType);

    boolean isNumeric(int displayType);

    Integer getDefaultPrecision(int displayType);

    boolean isText(int displayType);

    boolean isDate(int displayType);

    boolean isLookup(int displayType);

    boolean isLOB(int displayType);

    DecimalFormat getNumberFormat(int displayType, Language language, String pattern);

    SimpleDateFormat getDateFormat(int displayType, Language language, String pattern);

    Class<?> getClass(int displayType, boolean yesNoAsBoolean);

    String getSQLDataType(int displayType, String columnName, int fieldLength);

    String getDescription(int displayType);
}
