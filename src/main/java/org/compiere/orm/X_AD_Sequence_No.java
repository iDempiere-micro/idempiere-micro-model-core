package org.compiere.orm;

import org.compiere.model.I_AD_Sequence_No;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Sequence_No
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Sequence_No extends PO implements I_AD_Sequence_No {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Sequence_No(Properties ctx, int AD_Sequence_No_ID) {
        super(ctx, AD_Sequence_No_ID);
        /**
         * if (AD_Sequence_No_ID == 0) { setSequenceId (0); setCalendarYearMonth (null);
         * setCurrentNext (0); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_Sequence_No(Properties ctx, ResultSet rs) {
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
        StringBuffer sb = new StringBuffer("X_AD_Sequence_No[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Set Sequence.
     *
     * @param AD_Sequence_ID Document Sequence
     */
    public void setSequenceId(int AD_Sequence_ID) {
        if (AD_Sequence_ID < 1) setValueNoCheck(COLUMNNAME_AD_Sequence_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Sequence_ID, Integer.valueOf(AD_Sequence_ID));
    }

    /**
     * Set YearMonth.
     *
     * @param CalendarYearMonth YYYYMM
     */
    public void setCalendarYearMonth(String CalendarYearMonth) {
        setValueNoCheck(COLUMNNAME_CalendarYearMonth, CalendarYearMonth);
    }

    /**
     * Set Current Next.
     *
     * @param CurrentNext The next number to be used
     */
    public void setCurrentNext(int CurrentNext) {
        setValue(COLUMNNAME_CurrentNext, Integer.valueOf(CurrentNext));
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
