package org.compiere.orm;

import org.compiere.model.I_AD_PInstance_Log;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_PInstance_Log
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_PInstance_Log extends PO implements I_AD_PInstance_Log {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_PInstance_Log(Properties ctx, int AD_PInstance_Log_ID) {
        super(ctx, AD_PInstance_Log_ID);
        /** if (AD_PInstance_Log_ID == 0) { setAD_PInstance_ID (0); setLog_ID (0); } */
    }

    /**
     * Load Constructor
     */
    public X_AD_PInstance_Log(Properties ctx, ResultSet rs) {
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
        StringBuffer sb = new StringBuffer("X_AD_PInstance_Log[").append(getId()).append("]");
        return sb.toString();
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
