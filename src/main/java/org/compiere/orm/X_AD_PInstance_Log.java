package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.ProcessInstanceLog;

/**
 * Generated Model for AD_PInstance_Log
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_PInstance_Log extends PO implements ProcessInstanceLog {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_PInstance_Log(int AD_PInstance_Log_ID) {
        super(AD_PInstance_Log_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_PInstance_Log(Row row) {
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
        return "X_AD_PInstance_Log[" + getId() + "]";
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
