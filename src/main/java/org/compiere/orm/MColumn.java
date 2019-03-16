package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Table;
import org.compiere.util.DisplayType;
import org.compiere.util.Msg;
import org.idempiere.common.exceptions.DBException;
import org.idempiere.common.util.CCache;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Util;
import org.idempiere.orm.PO;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;

import static software.hsharp.core.util.DBKt.getSQLValue;
import static software.hsharp.core.util.DBKt.getSQLValueEx;

/**
 * Persistent Column Model
 *
 * @author Jorg Janke
 * @version $Id: MColumn.java,v 1.6 2006/08/09 05:23:49 jjanke Exp $
 */
public class MColumn extends X_AD_Column {
    /**
     *
     */
    private static final long serialVersionUID = -6914331394933196295L;
    /**
     * Cache
     */
    private static CCache<Integer, MColumn> s_cache =
            new CCache<Integer, MColumn>(I_AD_Column.Table_Name, 20);

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ctx          context
     * @param AD_Column_ID
     * @param trxName      transaction
     */
    public MColumn(Properties ctx, int AD_Column_ID) {
        super(ctx, AD_Column_ID);
        if (AD_Column_ID == 0) {
            //	setElementId (0);
            //	setReferenceId (0);
            //	setColumnName (null);
            //	setName (null);
            //	setEntityType (null);	// U
            setIsAlwaysUpdateable(false); // N
            setIsEncrypted(false);
            setIsIdentifier(false);
            setIsKey(false);
            setIsMandatory(false);
            setIsParent(false);
            setIsSelectionColumn(false);
            setIsTranslated(false);
            setIsUpdateable(true); // Y
            setVersion(Env.ZERO);
        }
    } //	MColumn

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MColumn(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * Parent Constructor
     *
     * @param parent table
     */
    public MColumn(MTable parent) {
        this(parent.getCtx(), 0);
        setClientOrg(parent);
        setColumnTableId(parent.getTableTableId());
        setEntityType(parent.getEntityType());
    } //	MColumn

    /**
     * Get MColumn from Cache
     *
     * @param ctx          context
     * @param AD_Column_ID id
     * @return MColumn
     */
    public static MColumn get(Properties ctx, int AD_Column_ID) {
        Integer key = new Integer(AD_Column_ID);
        MColumn retValue = s_cache.get(key);
        if (retValue != null) {
            return retValue;
        }
        retValue = new MColumn(ctx, AD_Column_ID);
        if (retValue.getId() != 0) s_cache.put(key, retValue);
        return retValue;
    } //	get

    /**
     * Get MColumn given TableName and ColumnName
     *
     * @param ctx        context
     * @param TableName
     * @param ColumnName
     * @return MColumn
     */
    public static MColumn get(Properties ctx, String tableName, String columnName) {
        MTable table = MTable.get(ctx, tableName);
        return table.getColumn(columnName);
    } //	get

    /**
     * Get Column Name
     *
     * @param ctx          context
     * @param AD_Column_ID id
     * @param trxName      transaction
     * @return Column Name or null
     */
    public static String getColumnName(Properties ctx, int AD_Column_ID) {
        MColumn col = MColumn.get(ctx, AD_Column_ID);
        if (col.getId() == 0) return null;
        return col.getColumnName();
    } //	getColumnName

    /**
     * Is Standard Column
     *
     * @return true for AD_Client_ID, etc.
     */
    public boolean isStandardColumn() {
        String columnName = getColumnName();
        return columnName.equals("AD_Client_ID")
                || columnName.equals("AD_Org_ID")
                || columnName.equals("IsActive")
                || columnName.equals("Processing")
                || columnName.equals("Created")
                || columnName.equals("CreatedBy")
                || columnName.equals("Updated")
                || columnName.equals("UpdatedBy");
    } //	isStandardColumn

    /**
     * Is UUID Column
     *
     * @return true for UUID column
     */
    public boolean isUUIDColumn() {
        return getColumnName().equals(PO.getUUIDColumnName(getColumnTable().getDbTableName()));
    }

    /**
     * Is Virtual Column
     *
     * @return true if virtual column
     */
    public boolean isVirtualColumn() {
        String s = getColumnSQL();
        return s != null && s.length() > 0;
    } //	isVirtualColumn

    /**
     * Is the Column Encrypted?
     *
     * @return true if encrypted
     */
    public boolean isEncrypted() {
        String s = getIsEncrypted();
        return "Y".equals(s);
    } //	isEncrypted

    /**
     * Set Encrypted
     *
     * @param IsEncrypted encrypted
     */
    public void setIsEncrypted(boolean IsEncrypted) {
        setIsEncrypted(IsEncrypted ? "Y" : "N");
    } //	setIsEncrypted

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    protected boolean beforeSave(boolean newRecord) {
        int displayType = getReferenceId();
        if (DisplayType.isLOB(displayType)) // 	LOBs are 0
        {
            if (getFieldLength() != 0) setFieldLength(0);
        } else if (getFieldLength() == 0) {
            if (DisplayType.isID(displayType)) setFieldLength(10);
            else if (DisplayType.isNumeric(displayType)) setFieldLength(14);
            else if (DisplayType.isDate(displayType)) setFieldLength(7);
            else {
                log.saveError("FillMandatory", Msg.getElement(getCtx(), "FieldLength"));
                return false;
            }
        }

        if (displayType != DisplayType.Button) {
            if (!X_AD_Column.ISTOOLBARBUTTON_Window.equals(getIsToolbarButton())) {
                setIsToolbarButton(X_AD_Column.ISTOOLBARBUTTON_Window);
            }
        }

        if (!isVirtualColumn() && getValueMax() != null && getValueMin() != null) {
            try {
                BigDecimal valueMax = new BigDecimal(getValueMax());
                BigDecimal valueMin = new BigDecimal(getValueMin());
                if (valueMax.compareTo(valueMin) < 0) {
                    log.saveError(
                            "MaxLessThanMin", Msg.getElement(getCtx(), I_AD_Column.COLUMNNAME_ValueMax));
                    return false;
                }
            } catch (Exception e) {
            }
        }

        /**
         * Views are not updateable UPDATE AD_Column c SET IsUpdateable='N', IsAlwaysUpdateable='N'
         * WHERE AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Table WHERE IsView='Y')
         */

        /* Diego Ruiz - globalqss - BF [1651899] - AD_Column: Avoid dup. SeqNo for IsIdentifier='Y' */
        if (isIdentifier()) {
            int cnt =
                    getSQLValue(
                            "SELECT COUNT(*) FROM AD_Column "
                                    + "WHERE AD_Table_ID=?"
                                    + " AND AD_Column_ID!=?"
                                    + " AND IsIdentifier='Y'"
                                    + " AND SeqNo=?",
                            getColumnTableId(),
                            getColumnId(),
                            getSeqNo());
            if (cnt > 0) {
                log.saveError(
                        DBException.SAVE_ERROR_NOT_UNIQUE_MSG,
                        Msg.getElement(getCtx(), I_AD_Column.COLUMNNAME_SeqNo));
                return false;
            }
        }

        //	Virtual Column
        if (isVirtualColumn()) {
            if (isMandatory()) setIsMandatory(false);
            if (isUpdateable()) setIsUpdateable(false);
        }
        //	Updateable
        if (isParent() || isKey()) setIsUpdateable(false);
        if (isAlwaysUpdateable() && !isUpdateable()) setIsAlwaysUpdateable(false);
        //	Encrypted
        String colname = getColumnName();
        if (isEncrypted()) {
            int dt = getReferenceId();
            if (isKey()
                    || isParent()
                    || isStandardColumn()
                    || isVirtualColumn()
                    || isIdentifier()
                    || isTranslated()
                    || isUUIDColumn()
                    || DisplayType.isLookup(dt)
                    || DisplayType.isLOB(dt)
                    || "DocumentNo".equalsIgnoreCase(colname)
                    || "Value".equalsIgnoreCase(colname)
                    || "Name".equalsIgnoreCase(colname)) {
                log.warning("Encryption not sensible - " + colname);
                setIsEncrypted(false);
            }
        }

        //	Sync Terminology
        if ((newRecord || isValueChanged("AD_Element_ID")) && getElementId() != 0) {
            M_Element element = new M_Element(getCtx(), getElementId());
            setColumnName(element.getColumnName());
            setName(element.getName());
            setDescription(element.getDescription());
            setHelp(element.getHelp());
        }

        // Validations for IsAllowCopy - some columns must never be set as allowed copying
        if (isAllowCopy()) {
            if (isKey() || isVirtualColumn() || isUUIDColumn() || isStandardColumn())
                setIsAllowCopy(false);
        }

        // validate FormatPattern
        String pattern = getFormatPattern();
        if (!Util.isEmpty(pattern, true)) {
            if (DisplayType.isNumeric(getReferenceId())) {
                DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
                try {
                    format.applyPattern(pattern);
                } catch (IllegalArgumentException e) {
                    log.saveError("SaveError", "Invalid number format: " + pattern);
                    return false;
                }
            } else if (DisplayType.isDate(getReferenceId())) {
                SimpleDateFormat format = (SimpleDateFormat) DateFormat.getInstance();
                try {
                    format.applyPattern(pattern);
                } catch (IllegalArgumentException e) {
                    log.saveError("SaveError", "Invalid date pattern: " + pattern);
                    return false;
                }
            } else {
                setFormatPattern(null);
            }
        }

        return true;
    } //	beforeSave

    /**
     * After Save
     *
     * @param newRecord new
     * @param success   success
     * @return success
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        if (!success) return success;

        /* Fields must inherit translation from element, not from column
         * changing it here is useless as SynchronizeTerminology get trl from column */
    /*
    //	Update Fields
    if (!newRecord)
    {
    	if (   isValueChanged(MColumn.HasName.Companion.getCOLUMNNAME_Name())
    		|| isValueChanged(MColumn.COLUMNNAME_Description)
    		|| isValueChanged(MColumn.COLUMNNAME_Help)
    		) {
    		StringBuilder sql = new StringBuilder("UPDATE AD_Field SET Name=")
    			.append(TO_STRING(getName()))
    			.append(", Description=").append(TO_STRING(getDescription()))
    			.append(", Help=").append(TO_STRING(getHelp()))
    			.append(" WHERE AD_Column_ID=").append(getId())
    			.append(" AND IsCentrallyMaintained='Y'");
    		int no =executeUpdate(sql.toString(), null);
    		if (log.isLoggable(Level.FINE)) log.fine("afterSave - Fields updated #" + no);
    	}
    }
    */

        return success;
    } //	afterSave

    /**
     * Get SQL Data Type
     *
     * @return e.g. NVARCHAR2(60)
     */
  /*
  private String getSQLDataType()
  {
  	int dt = getReferenceId();
  	if (DisplayType.isID(dt) || dt == DisplayType.Integer)
  		return "NUMBER(10)";
  	if (DisplayType.isDate(dt))
  		return "DATE";
  	if (DisplayType.isNumeric(dt))
  		return "NUMBER";
  	if (dt == DisplayType.Binary)
  		return "BLOB";
  	if (dt == DisplayType.TextLong)
  		return "CLOB";
  	if (dt == DisplayType.YesNo)
  		return "CHAR(1)";
  	if (dt == DisplayType.List)
  		return "NVARCHAR2(" + getFieldLength() + ")";
  	if (dt == DisplayType.Button)
  		return "CHAR(" + getFieldLength() + ")";
  	else if (!DisplayType.isText(dt))
  		log.severe("Unhandled Data Type = " + dt);

  	return "NVARCHAR2(" + getFieldLength() + ")";
  }	//	getSQLDataType
  */

    /**
     * Get SQL Data Type
     *
     * @return e.g. NVARCHAR2(60)
     */
    public String getSQLDataType() {
        String columnName = getColumnName();
        int dt = getReferenceId();
        return DisplayType.getSQLDataType(dt, columnName, getFieldLength());
    } //	getSQLDataType

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("MColumn[");
        sb.append(getId()).append("-").append(getColumnName()).append("]");
        return sb.toString();
    } //	toString

    public String getReferenceTableName() {
        String foreignTable = null;
        int refid = getReferenceId();
        if (DisplayType.TableDir == refid
                || (DisplayType.Search == refid && getReferenceValueId() == 0)) {
            foreignTable = getColumnName().substring(0, getColumnName().length() - 3);
        } else if (DisplayType.Table == refid || DisplayType.Search == refid) {
            X_AD_Reference ref = new X_AD_Reference(getCtx(), getReferenceValueId());
            if (X_AD_Reference.VALIDATIONTYPE_TableValidation.equals(ref.getValidationType())) {
                int cnt =
                        getSQLValueEx(
                                "SELECT COUNT(*) FROM AD_Ref_Table WHERE AD_Reference_ID=?",
                                getReferenceValueId());
                if (cnt == 1) {
                    MRefTable rt = new MRefTable(getCtx(), getReferenceValueId());
                    if (rt != null) foreignTable = rt.getTable().getDbTableName();
                }
            }
        } else if (DisplayType.List == refid || DisplayType.Payment == refid) {
            foreignTable = "AD_Ref_List";
        } else if (DisplayType.Location == refid) {
            foreignTable = "C_Location";
        } else if (DisplayType.Account == refid) {
            foreignTable = "C_ValidCombination";
        } else if (DisplayType.Locator == refid) {
            foreignTable = "M_Locator";
        } else if (DisplayType.PAttribute == refid) {
            foreignTable = "M_AttributeSetInstance";
        } else if (DisplayType.Assignment == refid) {
            foreignTable = "S_ResourceAssignment";
        } else if (DisplayType.Image == refid) {
            foreignTable = "AD_Image";
        } else if (DisplayType.Color == refid) {
            foreignTable = "AD_Color";
        } else if (DisplayType.Chart == refid) {
            foreignTable = "AD_Chart";
        }

        return foreignTable;
    }

    @Override
    public I_AD_Table getColumnTable() throws RuntimeException {
        MTable table = MTable.get(getCtx(), getColumnTableId());
        return table;
    }

    /**
     * Is Advanced
     *
     * @return true if the column has any field marked as advanced or part of an advanced tab
     */
    public boolean isAdvanced() {
        final String sql =
                ""
                        + "SELECT COUNT(*) "
                        + "FROM   AD_Tab t "
                        + "       JOIN AD_Field f ON ( f.AD_Tab_ID = t.AD_Tab_ID ) "
                        + "WHERE  f.AD_Column_ID = ? "
                        + "       AND ( t.IsAdvancedTab = 'Y' OR f.IsAdvancedField = 'Y' )";
        int cnt = getSQLValueEx(sql, getColumnId());
        return cnt > 0;
    }
} //	MColumn
