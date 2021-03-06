package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.Element;

/**
 * Generated Model for AD_Element
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Element extends BasePOName implements Element {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Element(int AD_Element_ID) {
        super(AD_Element_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Element(Row row) {
        super(row);
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
        StringBuffer sb = new StringBuffer("X_AD_Element[").append(getId()).append("]");
        return sb.toString();
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
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return getValue(COLUMNNAME_Description);
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
     * Get Comment/Help.
     *
     * @return Comment or Hint
     */
    public String getHelp() {
        return getValue(COLUMNNAME_Help);
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
     * Get Print Text.
     *
     * @return The label text to be printed on a document or correspondence.
     */
    public String getPrintName() {
        return getValue(COLUMNNAME_PrintName);
    }

    /**
     * Set Print Text.
     *
     * @param PrintName The label text to be printed on a document or correspondence.
     */
    public void setPrintName(String PrintName) {
        setValue(COLUMNNAME_PrintName, PrintName);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
