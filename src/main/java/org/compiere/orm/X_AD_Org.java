package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Org;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Org
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Org extends BasePONameValue implements I_AD_Org, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Org(Properties ctx, int AD_Org_ID) {
        super(ctx, AD_Org_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Org(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_AD_Org(Properties ctx, Row row) {
        super(ctx, row);
    } //	MOrg

    /**
     * AccessLevel
     *
     * @return 7 - System - Client - Org
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_AD_Org[" + getId() + "]";
    }

    /**
     * Set Description.
     *
     * @param Description Optional short description of the record
     */
    public void setDescription(String Description) {
        set_Value(COLUMNNAME_Description, Description);
    }

    /**
     * Set Summary Level.
     *
     * @param IsSummary This is a summary entity
     */
    public void setIsSummary(boolean IsSummary) {
        set_Value(COLUMNNAME_IsSummary, IsSummary);
    }

    /**
     * Get Summary Level.
     *
     * @return This is a summary entity
     */
    public boolean isSummary() {
        Object oo = getValue(COLUMNNAME_IsSummary);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
