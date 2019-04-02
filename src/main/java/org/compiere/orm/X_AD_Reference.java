package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Reference;

/**
 * Generated Model for AD_Reference
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Reference extends BasePOName implements I_AD_Reference {

    /**
     * Table Validation = T
     */
    public static final String VALIDATIONTYPE_TableValidation = "T";
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Reference(int AD_Reference_ID) {
        super(AD_Reference_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Reference(Row row) {
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
        StringBuffer sb = new StringBuffer("X_AD_Reference[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Validation type.
     *
     * @return Different method of validating data
     */
    public String getValidationType() {
        return (String) getValue(COLUMNNAME_ValidationType);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
