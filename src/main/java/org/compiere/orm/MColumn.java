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
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;

import static software.hsharp.core.util.DBKt.*;

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
            //	setAD_Element_ID (0);
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
    public MColumn(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    } //	MColumn

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
        setAD_Table_ID(parent.getAD_Table_ID());
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
        return getColumnName().equals(PO.getUUIDColumnName(getAD_Table().getTableName()));
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
                            getAD_Table_ID(),
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
        if ((newRecord || is_ValueChanged("AD_Element_ID")) && getAD_Element_ID() != 0) {
            M_Element element = new M_Element(getCtx(), getAD_Element_ID());
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
    	if (   is_ValueChanged(MColumn.HasName.Companion.getCOLUMNNAME_Name())
    		|| is_ValueChanged(MColumn.COLUMNNAME_Description)
    		|| is_ValueChanged(MColumn.COLUMNNAME_Help)
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
     * Get SQL DDL
     *
     * @return columnName datataype ..
     */
    public String getSQLDDL() {
        if (isVirtualColumn()) return null;

        StringBuilder sql =
                new StringBuilder().append(getColumnName()).append(" ").append(getSQLDataType());

        //	Default
        String defaultValue = getDefaultValue();
        if (defaultValue != null
                && defaultValue.length() > 0
                && defaultValue.indexOf('@') == -1 // 	no variables
                && (!(DisplayType.isID(getReferenceId())
                && defaultValue.equals("-1")))) // not for ID's with default -1
        {
            if (DisplayType.isText(getReferenceId())
                    || getReferenceId() == DisplayType.List
                    || getReferenceId() == DisplayType.YesNo
                    // Two special columns: Defined as Table but DB Type is String
                    || getColumnName().equals("EntityType")
                    || getColumnName().equals("AD_Language")
                    || (getReferenceId() == DisplayType.Button && !(getColumnName().endsWith("_ID")))) {
                if (!defaultValue.startsWith("'") && !defaultValue.endsWith("'"))
                    defaultValue = TO_STRING(defaultValue);
            }
            sql.append(" DEFAULT ").append(defaultValue);
        } else {
            if (!isMandatory()) sql.append(" DEFAULT NULL ");
            defaultValue = null;
        }

        //	Inline Constraint
        if (getReferenceId() == DisplayType.YesNo)
            sql.append(" CHECK (").append(getColumnName()).append(" IN ('Y','N'))");

        //	Null
        if (isMandatory()) sql.append(" NOT NULL");
        return sql.toString();
    } //	getSQLDDL

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
     * Get Table Constraint
     *
     * @param tableName table name
     * @return table constraint
     */
    public String getConstraint(String tableName) {
        if (isKey()) {
            StringBuilder constraintName;
            if (tableName.length() > 26)
                // Oracle restricts object names to 30 characters
                constraintName = new StringBuilder(tableName.substring(0, 26)).append("_Key");
            else constraintName = new StringBuilder(tableName).append("_Key");
            StringBuilder msgreturn =
                    new StringBuilder("CONSTRAINT ")
                            .append(constraintName)
                            .append(" PRIMARY KEY (")
                            .append(getColumnName())
                            .append(")");
            return msgreturn.toString();
        }
        /**
         * if (getReferenceId() == DisplayType.TableDir || getReferenceId() == DisplayType.Search)
         * return "CONSTRAINT " ADTable_ADTableTrl + " FOREIGN KEY (" + getColumnName() + ") REFERENCES
         * " + AD_Table(AD_Table_ID) ON DELETE CASCADE
         */
        // IDEMPIERE-965
        if (getColumnName().equals(PO.getUUIDColumnName(tableName))) {
            StringBuilder indexName = new StringBuilder().append(getColumnName()).append("_idx");
            if (indexName.length() > 30) {
                indexName = new StringBuilder().append(getColumnName(), 0, 25);
                indexName.append("uuidx");
            }
            StringBuilder msgreturn =
                    new StringBuilder("CONSTRAINT ")
                            .append(indexName)
                            .append(" UNIQUE (")
                            .append(getColumnName())
                            .append(")");
            return msgreturn.toString();
        }
        return "";
    } //	getConstraint

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
                || (DisplayType.Search == refid && getAD_Reference_Value_ID() == 0)) {
            foreignTable = getColumnName().substring(0, getColumnName().length() - 3);
        } else if (DisplayType.Table == refid || DisplayType.Search == refid) {
            X_AD_Reference ref = new X_AD_Reference(getCtx(), getAD_Reference_Value_ID());
            if (X_AD_Reference.VALIDATIONTYPE_TableValidation.equals(ref.getValidationType())) {
                int cnt =
                        getSQLValueEx(
                                "SELECT COUNT(*) FROM AD_Ref_Table WHERE AD_Reference_ID=?",
                                getAD_Reference_Value_ID());
                if (cnt == 1) {
                    MRefTable rt = new MRefTable(getCtx(), getAD_Reference_Value_ID());
                    if (rt != null) foreignTable = rt.getAD_Table().getTableName();
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
    public I_AD_Table getAD_Table() throws RuntimeException {
        MTable table = MTable.get(getCtx(), getAD_Table_ID());
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
