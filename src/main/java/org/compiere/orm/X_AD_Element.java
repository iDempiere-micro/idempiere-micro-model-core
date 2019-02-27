package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Element;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Element
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Element extends BasePOName implements I_AD_Element {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Element(Properties ctx, int AD_Element_ID) {
        super(ctx, AD_Element_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Element(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_AD_Element(Properties ctx, Row row) {
        super(ctx, row);
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
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Element_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get DB Column Name.
     *
     * @return Name of the column in the database
     */
    public String getColumnName() {
        return (String) getValue(COLUMNNAME_ColumnName);
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
        return (String) getValue(COLUMNNAME_Description);
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
        return (String) getValue(COLUMNNAME_Help);
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
        return (String) getValue(COLUMNNAME_PrintName);
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
