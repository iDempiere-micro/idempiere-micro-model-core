package org.compiere.orm;

import org.compiere.model.I_AD_Role_Included;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Role_Included
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Role_Included extends PO implements I_AD_Role_Included, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Role_Included(Properties ctx, int AD_Role_Included_ID) {
        super(ctx, AD_Role_Included_ID);
        /**
         * if (AD_Role_Included_ID == 0) { setAD_Role_ID (0); setIncluded_Role_ID (0); setSeqNo (0);
         * // @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_Role_Included WHERE
         * AD_Role_ID=@AD_Role_ID@ }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_Role_Included(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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
     * Get Role.
     *
     * @return Responsibility Role
     */
    public int getAD_Role_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_Role_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Included Role.
     *
     * @return Included Role
     */
    public int getIncluded_Role_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_Included_Role_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Sequence.
     *
     * @return Method of ordering records; lowest number comes first
     */
    public int getSeqNo() {
        Integer ii = (Integer) get_Value(COLUMNNAME_SeqNo);
        if (ii == null) return 0;
        return ii;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
