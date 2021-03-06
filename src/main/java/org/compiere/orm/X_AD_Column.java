package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.Column;
import org.compiere.model.Reference;
import org.compiere.model.Table;
import software.hsharp.core.orm.MBaseTableKt;

import java.math.BigDecimal;

/**
 * Generated Model for AD_Column
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public abstract class X_AD_Column extends PO implements Column {

    /**
     * Window = N
     */
    public static final String ISTOOLBARBUTTON_Window = "N";
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Load Constructor
     */
    public X_AD_Column(Row row) {
        super(row);
    }

    /**
     * Standard Constructor
     */
    public X_AD_Column(int AD_Column_ID) {
        super(AD_Column_ID);
    }


    /**
     * AccessLevel
     *
     * @return 4 - System
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_AD_Column[" + getId() + "]";
    }

    /**
     * Get Column.
     *
     * @return Column in the table
     */
    public int getColumnId() {
        Integer ii = getValue(COLUMNNAME_AD_Column_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get System Element.
     *
     * @return System Element enables the central maintenance of column description and help.
     */
    public int getElementId() {
        Integer ii = getValue(COLUMNNAME_AD_Element_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set System Element.
     *
     * @param AD_Element_ID System Element enables the central maintenance of column description and
     *                      help.
     */
    public void setElementId(int AD_Element_ID) {
        if (AD_Element_ID < 1) setValue(COLUMNNAME_AD_Element_ID, null);
        else setValue(COLUMNNAME_AD_Element_ID, AD_Element_ID);
    }

    /**
     * Get Process.
     *
     * @return Process or Report
     */
    public int getProcessId() {
        Integer ii = getValue(COLUMNNAME_AD_Process_ID);
        if (ii == null) return 0;
        return ii;
    }

    public Reference getReference() throws RuntimeException {
        return (Reference)
                MBaseTableKt.getTable(Reference.Table_Name).getPO(getReferenceId());
    }

    /**
     * Get Reference.
     *
     * @return System Reference and Validation
     */
    public int getReferenceId() {
        Integer ii = getValue(COLUMNNAME_AD_Reference_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Reference.
     *
     * @param AD_Reference_ID System Reference and Validation
     */
    public void setReferenceId(int AD_Reference_ID) {
        if (AD_Reference_ID < 1) setValue(COLUMNNAME_AD_Reference_ID, null);
        else setValue(COLUMNNAME_AD_Reference_ID, AD_Reference_ID);
    }

    /**
     * Get Reference Key.
     *
     * @return Required to specify, if data type is Table or List
     */
    public int getReferenceValueId() {
        Integer ii = getValue(COLUMNNAME_AD_Reference_Value_ID);
        if (ii == null) return 0;
        return ii;
    }

    public Table getColumnTable() throws RuntimeException {
        return (Table) MBaseTableKt.getTable(Table.Table_Name).getPO(getColumnTableId());
    }

    /**
     * Get Table.
     *
     * @return Database Table information
     */
    public int getColumnTableId() {
        Integer ii = getValue(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Table.
     *
     * @param AD_Table_ID Database Table information
     */
    public void setColumnTableId(int AD_Table_ID) {
        if (AD_Table_ID < 1) setValueNoCheck(COLUMNNAME_AD_Table_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Table_ID, AD_Table_ID);
    }

    /**
     * Get DB Column Name.
     *
     * @return Name of the column in the database
     */
    public String getColumnName() {
        return getValue(COLUMNNAME_ColumnName);
    }

    /**
     * Set DB Column Name.
     *
     * @param ColumnName Name of the column in the database
     */
    public void setColumnName(String ColumnName) {
        setValue(COLUMNNAME_ColumnName, ColumnName);
    }

    /**
     * Get Column SQL.
     *
     * @return Virtual Column (r/o)
     */
    public String getColumnSQL() {
        return getValue(COLUMNNAME_ColumnSQL);
    }

    /**
     * Get Default Logic.
     *
     * @return Default value hierarchy, separated by ;
     */
    public String getDefaultValue() {
        return getValue(COLUMNNAME_DefaultValue);
    }

    /**
     * Set Description.
     *
     * @param Description Optional short description of the record
     */
    public void setDescription(String Description) {
        setValue(COLUMNNAME_Description, Description);
    }

    /**
     * Set Entity Type.
     *
     * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
     */
    public void setEntityType(String EntityType) {

        setValue(COLUMNNAME_EntityType, EntityType);
    }

    /**
     * Get Length.
     *
     * @return Length of the column in the database
     */
    public int getFieldLength() {
        Integer ii = getValue(COLUMNNAME_FieldLength);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Length.
     *
     * @param FieldLength Length of the column in the database
     */
    public void setFieldLength(int FieldLength) {
        setValue(COLUMNNAME_FieldLength, FieldLength);
    }

    /**
     * Get Format Pattern.
     *
     * @return The pattern used to format a number or date.
     */
    public String getFormatPattern() {
        return getValue(COLUMNNAME_FormatPattern);
    }

    /**
     * Set Format Pattern.
     *
     * @param FormatPattern The pattern used to format a number or date.
     */
    public void setFormatPattern(String FormatPattern) {
        setValue(COLUMNNAME_FormatPattern, FormatPattern);
    }

    /**
     * Set Comment/Help.
     *
     * @param Help Comment or Hint
     */
    public void setHelp(String Help) {
        setValue(COLUMNNAME_Help, Help);
    }

    /**
     * Set Allow Copy.
     *
     * @param IsAllowCopy Determine if a column must be copied when pushing the button to copy record
     */
    public void setIsAllowCopy(boolean IsAllowCopy) {
        setValue(COLUMNNAME_IsAllowCopy, IsAllowCopy);
    }

    /**
     * Get Allow Copy.
     *
     * @return Determine if a column must be copied when pushing the button to copy record
     */
    public boolean isAllowCopy() {
        Object oo = getValue(COLUMNNAME_IsAllowCopy);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Always Updatable.
     *
     * @param IsAlwaysUpdateable The column is always updateable, even if the record is not active or
     *                           processed
     */
    public void setIsAlwaysUpdateable(boolean IsAlwaysUpdateable) {
        setValue(COLUMNNAME_IsAlwaysUpdateable, IsAlwaysUpdateable);
    }

    /**
     * Get Always Updatable.
     *
     * @return The column is always updateable, even if the record is not active or processed
     */
    public boolean isAlwaysUpdateable() {
        Object oo = getValue(COLUMNNAME_IsAlwaysUpdateable);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Encrypted.
     *
     * @return Display or Storage is encrypted
     */
    public String getIsEncrypted() {
        return getValue(COLUMNNAME_IsEncrypted);
    }

    /**
     * Set Encrypted.
     *
     * @param IsEncrypted Display or Storage is encrypted
     */
    public void setIsEncrypted(String IsEncrypted) {

        setValue(COLUMNNAME_IsEncrypted, IsEncrypted);
    }

    /**
     * Set Identifier.
     *
     * @param IsIdentifier This column is part of the record identifier
     */
    public void setIsIdentifier(boolean IsIdentifier) {
        setValue(COLUMNNAME_IsIdentifier, IsIdentifier);
    }

    /**
     * Get Identifier.
     *
     * @return This column is part of the record identifier
     */
    public boolean isIdentifier() {
        Object oo = getValue(COLUMNNAME_IsIdentifier);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Key column.
     *
     * @param IsKey This column is the key in this table
     */
    public void setIsKey(boolean IsKey) {
        setValue(COLUMNNAME_IsKey, IsKey);
    }

    /**
     * Get Key column.
     *
     * @return This column is the key in this table
     */
    public boolean isKey() {
        Object oo = getValue(COLUMNNAME_IsKey);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Mandatory.
     *
     * @param IsMandatory Data entry is required in this column
     */
    public void setIsMandatory(boolean IsMandatory) {
        setValue(COLUMNNAME_IsMandatory, IsMandatory);
    }

    /**
     * Get Mandatory.
     *
     * @return Data entry is required in this column
     */
    public boolean isMandatory() {
        Object oo = getValue(COLUMNNAME_IsMandatory);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Parent link column.
     *
     * @param IsParent This column is a link to the parent table (e.g. header from lines) - incl.
     *                 Association key columns
     */
    public void setIsParent(boolean IsParent) {
        setValue(COLUMNNAME_IsParent, IsParent);
    }

    /**
     * Get Parent link column.
     *
     * @return This column is a link to the parent table (e.g. header from lines) - incl. Association
     * key columns
     */
    public boolean isParent() {
        Object oo = getValue(COLUMNNAME_IsParent);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Secure content.
     *
     * @return Defines whether content must be treated as secure
     */
    public boolean isSecure() {
        Object oo = getValue(COLUMNNAME_IsSecure);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Selection Column.
     *
     * @param IsSelectionColumn Is this column used for finding rows in windows
     */
    public void setIsSelectionColumn(boolean IsSelectionColumn) {
        setValue(COLUMNNAME_IsSelectionColumn, IsSelectionColumn);
    }

    /**
     * Get Toolbar Button.
     *
     * @return Show the button on the toolbar, the window, or both
     */
    public String getIsToolbarButton() {
        return getValue(COLUMNNAME_IsToolbarButton);
    }

    /**
     * Set Toolbar Button.
     *
     * @param IsToolbarButton Show the button on the toolbar, the window, or both
     */
    public void setIsToolbarButton(String IsToolbarButton) {

        setValue(COLUMNNAME_IsToolbarButton, IsToolbarButton);
    }

    /**
     * Set Translated.
     *
     * @param IsTranslated This column is translated
     */
    public void setIsTranslated(boolean IsTranslated) {
        setValue(COLUMNNAME_IsTranslated, IsTranslated);
    }

    /**
     * Get Translated.
     *
     * @return This column is translated
     */
    public boolean isTranslated() {
        Object oo = getValue(COLUMNNAME_IsTranslated);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Updatable.
     *
     * @param IsUpdateable Determines, if the field can be updated
     */
    public void setIsUpdateable(boolean IsUpdateable) {
        setValue(COLUMNNAME_IsUpdateable, IsUpdateable);
    }

    /**
     * Get Updatable.
     *
     * @return Determines, if the field can be updated
     */
    public boolean isUpdateable() {
        Object oo = getValue(COLUMNNAME_IsUpdateable);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Name.
     *
     * @return Alphanumeric identifier of the entity
     */
    public String getName() {
        return getValue(HasName.COLUMNNAME_Name);
    }

    /**
     * Set Name.
     *
     * @param Name Alphanumeric identifier of the entity
     */
    public void setName(String Name) {
        setValue(HasName.COLUMNNAME_Name, Name);
    }

    /**
     * Get Sequence.
     *
     * @return Method of ordering records; lowest number comes first
     */
    public int getSeqNo() {
        Integer ii = getValue(COLUMNNAME_SeqNo);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Max. Value.
     *
     * @return Maximum Value for a field
     */
    public String getValueMax() {
        return getValue(COLUMNNAME_ValueMax);
    }

    /**
     * Get Min. Value.
     *
     * @return Minimum Value for a field
     */
    public String getValueMin() {
        return getValue(COLUMNNAME_ValueMin);
    }

    /**
     * Set Version.
     *
     * @param Version Version of the table definition
     */
    public void setVersion(BigDecimal Version) {
        setValue(COLUMNNAME_Version, Version);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
