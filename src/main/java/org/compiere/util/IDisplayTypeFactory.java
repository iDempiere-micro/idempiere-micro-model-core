package org.compiere.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.idempiere.common.util.Language;

/**
 * @author Jan Thielemann - jan.thielemann@evenos-consulting.de
 * @author evenos Consulting GmbH - www.evenos.org
 */
public interface IDisplayTypeFactory {

  public boolean isID(int displayType);

  public boolean isNumeric(int displayType);

  public Integer getDefaultPrecision(int displayType);

  public boolean isText(int displayType);

  public boolean isDate(int displayType);

  public boolean isLookup(int displayType);

  public boolean isLOB(int displayType);

  public DecimalFormat getNumberFormat(int displayType, Language language, String pattern);

  public SimpleDateFormat getDateFormat(int displayType, Language language, String pattern);

  public Class<?> getClass(int displayType, boolean yesNoAsBoolean);

  public String getSQLDataType(int displayType, String columnName, int fieldLength);

  public String getDescription(int displayType);
}
