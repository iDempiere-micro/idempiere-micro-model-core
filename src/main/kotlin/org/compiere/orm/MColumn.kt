package org.compiere.orm

import kotliquery.Row
import org.compiere.model.I_AD_Column
import org.compiere.model.I_AD_Table
import org.compiere.util.DisplayType
import org.compiere.util.getElementTranslation
import org.idempiere.common.exceptions.DBException
import org.idempiere.common.util.Env
import org.idempiere.common.util.Util
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing
import org.idempiere.orm.PO
import software.hsharp.core.util.getSQLValue
import software.hsharp.core.util.getSQLValueEx
import java.math.BigDecimal
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

private val columnFactory = factory { MColumn(it) }

/**
 * Get currency by Id
 */
fun getColumn(id: Int) = id loadUsing columnFactory

/**
 * Get MColumn given TableName and ColumnName
 *
 * @return MColumn
 */
fun getColumn(tableName: String, columnName: String): MColumn? {
    val table = MTable.get(tableName)
    return table.getColumn(columnName)
} // 	get

/**
 * Get Column Name
 *
 * @param AD_Column_ID id
 * @return Column Name or null
 */
fun getColumnName(AD_Column_ID: Int): String? {
    val col = getColumn(AD_Column_ID)
    return if (col.id == 0) null else col.columnName
} // 	getColumnName

/**
 * Persistent Column Model
 *
 * @author Jorg Janke
 * @version $Id: MColumn.java,v 1.6 2006/08/09 05:23:49 jjanke Exp $
 */
class MColumn : X_AD_Column {

    /**
     * Is Standard Column
     *
     * @return true for AD_Client_ID, etc.
     */
    val isStandardColumn: Boolean
        get() {
            val columnName = columnName
            return (columnName == "AD_Client_ID" ||
                    columnName == "AD_Org_ID" ||
                    columnName == "IsActive" ||
                    columnName == "Processing" ||
                    columnName == "Created" ||
                    columnName == "CreatedBy" ||
                    columnName == "Updated" ||
                    columnName == "UpdatedBy")
        } // 	isStandardColumn

    /**
     * Is UUID Column
     *
     * @return true for UUID column
     */
    val isUUIDColumn: Boolean
        get() = columnName == PO.getUUIDColumnName(columnTable.dbTableName)

    /**
     * Is Virtual Column
     *
     * @return true if virtual column
     */
    val isVirtualColumn: Boolean
        get() {
            val s = columnSQL
            return s != null && s.length > 0
        } // 	isVirtualColumn

    /**
     * Is the Column Encrypted?
     *
     * @return true if encrypted
     */
    /**
     * Set Encrypted
     *
     * @param IsEncrypted encrypted
     */
    var isEncrypted: Boolean
        get() {
            val s = getIsEncrypted()
            return "Y" == s
        }
        set(IsEncrypted) = setIsEncrypted(if (IsEncrypted) "Y" else "N") // 	isEncrypted
    // 	setIsEncrypted

    /**
     * Get SQL Data Type
     *
     * @return e.g. NVARCHAR2(60)
     */
    val sqlDataType: String
        get() {
            val columnName = columnName
            val dt = referenceId
            return DisplayType.getSQLDataType(dt, columnName, fieldLength)
        } // 	getSQLDataType

    val referenceTableName: String?
        get() {
            var foreignTable: String? = null
            val refid = referenceId
            if (DisplayType.TableDir == refid || DisplayType.Search == refid && referenceValueId == 0) {
                foreignTable = columnName.substring(0, columnName.length - 3)
            } else if (DisplayType.Table == refid || DisplayType.Search == refid) {
                val ref = X_AD_Reference(referenceValueId)
                if (X_AD_Reference.VALIDATIONTYPE_TableValidation == ref.validationType) {
                    val cnt = getSQLValueEx(
                        "SELECT COUNT(*) FROM AD_Ref_Table WHERE AD_Reference_ID=?",
                        referenceValueId
                    )
                    if (cnt == 1) {
                        val rt = MRefTable(referenceValueId)
                        foreignTable = rt.table.dbTableName
                    }
                }
            } else if (DisplayType.List == refid || DisplayType.Payment == refid) {
                foreignTable = "AD_Ref_List"
            } else if (DisplayType.Location == refid) {
                foreignTable = "C_Location"
            } else if (DisplayType.Account == refid) {
                foreignTable = "C_ValidCombination"
            } else if (DisplayType.Locator == refid) {
                foreignTable = "M_Locator"
            } else if (DisplayType.PAttribute == refid) {
                foreignTable = "M_AttributeSetInstance"
            } else if (DisplayType.Assignment == refid) {
                foreignTable = "S_ResourceAssignment"
            } else if (DisplayType.Image == refid) {
                foreignTable = "AD_Image"
            } else if (DisplayType.Color == refid) {
                foreignTable = "AD_Color"
            } else if (DisplayType.Chart == refid) {
                foreignTable = "AD_Chart"
            }

            return foreignTable
        }

    /**
     * Is Advanced
     *
     * @return true if the column has any field marked as advanced or part of an advanced tab
     */
    val isAdvanced: Boolean
        get() {
            val sql = ("" +
                    "SELECT COUNT(*) " +
                    "FROM   AD_Tab t " +
                    "       JOIN AD_Field f ON ( f.AD_Tab_ID = t.AD_Tab_ID ) " +
                    "WHERE  f.AD_Column_ID = ? " +
                    "       AND ( t.IsAdvancedTab = 'Y' OR f.IsAdvancedField = 'Y' )")
            val cnt = getSQLValueEx(sql, columnId)
            return cnt > 0
        }

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param AD_Column_ID
     */
    constructor(AD_Column_ID: Int) : super(AD_Column_ID) {
        if (AD_Column_ID == 0) {
            setIsAlwaysUpdateable(false) // N
            isEncrypted = false
            setIsIdentifier(false)
            setIsKey(false)
            setIsMandatory(false)
            setIsParent(false)
            setIsSelectionColumn(false)
            setIsTranslated(false)
            setIsUpdateable(true) // Y
            setVersion(Env.ZERO)
        }
    } // 	MColumn

    /**
     * Load Constructor
     *
     */
    constructor(row: Row) : super(row) {}

    /**
     * Parent Constructor
     *
     * @param parent table
     */
    constructor(parent: MTable) : this(0) {
        setClientOrg(parent)
        columnTableId = parent.tableTableId
        setEntityType(parent.entityType)
    } // 	MColumn

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    override fun beforeSave(newRecord: Boolean): Boolean {
        val displayType = referenceId
        if (DisplayType.isLOB(displayType))
        // 	LOBs are 0
        {
            if (fieldLength != 0) fieldLength = 0
        } else if (fieldLength == 0) {
            if (DisplayType.isID(displayType))
                fieldLength = 10
            else if (DisplayType.isNumeric(displayType))
                fieldLength = 14
            else if (DisplayType.isDate(displayType))
                fieldLength = 7
            else {
                log.saveError("FillMandatory", getElementTranslation("FieldLength"))
                return false
            }
        }

        if (displayType != DisplayType.Button) {
            if (X_AD_Column.ISTOOLBARBUTTON_Window != isToolbarButton) {
                isToolbarButton = X_AD_Column.ISTOOLBARBUTTON_Window
            }
        }

        if (!isVirtualColumn && valueMax != null && valueMin != null) {
            try {
                val valueMax = BigDecimal(valueMax)
                val valueMin = BigDecimal(valueMin)
                if (valueMax.compareTo(valueMin) < 0) {
                    log.saveError(
                        "MaxLessThanMin", getElementTranslation(I_AD_Column.COLUMNNAME_ValueMax)
                    )
                    return false
                }
            } catch (ignored: Exception) {
            }
        }

        /* Diego Ruiz - globalqss - BF [1651899] - AD_Column: Avoid dup. SeqNo for IsIdentifier='Y' */
        if (isIdentifier) {
            val cnt = getSQLValue(
                "SELECT COUNT(*) FROM AD_Column " +
                        "WHERE AD_Table_ID=?" +
                        " AND AD_Column_ID!=?" +
                        " AND IsIdentifier='Y'" +
                        " AND SeqNo=?",
                columnTableId,
                columnId,
                seqNo
            )
            if (cnt > 0) {
                log.saveError(
                    DBException.SAVE_ERROR_NOT_UNIQUE_MSG,
                    getElementTranslation(I_AD_Column.COLUMNNAME_SeqNo)
                )
                return false
            }
        }

        // 	Virtual Column
        if (isVirtualColumn) {
            if (isMandatory) setIsMandatory(false)
            if (isUpdateable) setIsUpdateable(false)
        }
        // 	Updateable
        if (isParent || isKey) setIsUpdateable(false)
        if (isAlwaysUpdateable && !isUpdateable) setIsAlwaysUpdateable(false)
        // 	Encrypted
        val colname = columnName
        if (isEncrypted) {
            val dt = referenceId
            if (isKey ||
                isParent ||
                isStandardColumn ||
                isVirtualColumn ||
                isIdentifier ||
                isTranslated ||
                isUUIDColumn ||
                DisplayType.isLookup(dt) ||
                DisplayType.isLOB(dt) ||
                "DocumentNo".equals(colname, ignoreCase = true) ||
                "Value".equals(colname, ignoreCase = true) ||
                "Name".equals(colname, ignoreCase = true)
            ) {
                log.warning("Encryption not sensible - $colname")
                isEncrypted = false
            }
        }

        // 	Sync Terminology
        if ((newRecord || isValueChanged("AD_Element_ID")) && elementId != 0) {
            val element = M_Element(elementId)
            columnName = element.columnName
            name = element.name
            setDescription(element.description)
            setHelp(element.help)
        }

        // Validations for IsAllowCopy - some columns must never be set as allowed copying
        if (isAllowCopy) {
            if (isKey || isVirtualColumn || isUUIDColumn || isStandardColumn)
                setIsAllowCopy(false)
        }

        // validate FormatPattern
        val pattern = formatPattern
        if (!Util.isEmpty(pattern, true)) {
            if (DisplayType.isNumeric(referenceId)) {
                val format = NumberFormat.getNumberInstance(Locale.US) as DecimalFormat
                try {
                    format.applyPattern(pattern)
                } catch (e: IllegalArgumentException) {
                    log.saveError("SaveError", "Invalid number format: $pattern")
                    return false
                }
            } else if (DisplayType.isDate(referenceId)) {
                val format = DateFormat.getInstance() as SimpleDateFormat
                try {
                    format.applyPattern(pattern)
                } catch (e: IllegalArgumentException) {
                    log.saveError("SaveError", "Invalid date pattern: $pattern")
                    return false
                }
            } else {
                formatPattern = null
            }
        }

        return true
    } // 	beforeSave

    /**
     * After Save
     *
     * @param newRecord new
     * @param success success
     * @return success
     */
    override fun afterSave(newRecord: Boolean, success: Boolean): Boolean {
        return success
    } // 	afterSave

    /**
     * String Representation
     *
     * @return info
     */
    override fun toString(): String {
        return "MColumn[" + id + "-" + columnName + "]"
    } // 	toString

    @Throws(RuntimeException::class)
    override fun getColumnTable(): I_AD_Table {
        return MTable.get(columnTableId)
    }

    companion object {
        private val serialVersionUID = -6914331394933196295L
    }
} // 	MColumn