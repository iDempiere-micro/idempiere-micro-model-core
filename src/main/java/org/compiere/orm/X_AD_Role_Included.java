package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.RoleIncluded;

/**
 * Generated Model for AD_Role_Included
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Role_Included extends PO implements RoleIncluded {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Role_Included(int AD_Role_Included_ID) {
        super(AD_Role_Included_ID);
        /**
         * if (AD_Role_Included_ID == 0) { setRoleId (0); setIncluded_Role_ID (0); setSeqNo (0);
         * // @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_Role_Included WHERE
         * AD_Role_ID=@AD_Role_ID@ }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_Role_Included(Row row) {
        super(row);
    }

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_AD_Role_Included[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Included Role.
     *
     * @return Included Role
     */
    public int getIncludedRoleId() {
        Integer ii = (Integer) getValue(COLUMNNAME_Included_Role_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Sequence.
     *
     * @return Method of ordering records; lowest number comes first
     */
    public int getSeqNo() {
        Integer ii = (Integer) getValue(COLUMNNAME_SeqNo);
        if (ii == null) return 0;
        return ii;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
