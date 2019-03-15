package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_System;
import org.idempiere.common.util.Env;

import java.math.BigDecimal;
import java.util.Properties;

/**
 * Generated Model for AD_System
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_System extends BasePOName implements I_AD_System {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_System(Properties ctx, int AD_System_ID) {
        super(ctx, AD_System_ID);
        /**
         * if (AD_System_ID == 0) { setAD_System_ID (0); // 0 setInfo (null); setIsAllowStatistics
         * (false); setIsAutoErrorReport (true); // Y setIsFailOnBuildDiffer (false); // N
         * setIsFailOnMissingModelValidator (true); // Y setName (null); setPassword (null);
         * setReplicationType (null); // L setSystemStatus (null); // E setUserName (null); setVersion
         * (null); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_System(Properties ctx, Row row) {
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
        StringBuffer sb = new StringBuffer("X_AD_System[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get ID Range End.
     *
     * @return End if the ID Range used
     */
    public BigDecimal getIDRangeEnd() {
        BigDecimal bd = (BigDecimal) getValue(COLUMNNAME_IDRangeEnd);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
