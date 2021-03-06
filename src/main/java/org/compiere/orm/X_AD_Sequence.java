package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.Sequence;

/**
 * Generated Model for AD_Sequence
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Sequence extends BasePOName implements Sequence {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Sequence(int AD_Sequence_ID) {
        super(AD_Sequence_ID);
    }

    public X_AD_Sequence(Row row) {
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
        StringBuffer sb = new StringBuffer("X_AD_Sequence[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Sequence.
     *
     * @return Document Sequence
     */
    public int getSequenceId() {
        Integer ii = getValue(COLUMNNAME_AD_Sequence_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Current Next.
     *
     * @return The next number to be used
     */
    public int getCurrentNext() {
        Integer ii = getValue(COLUMNNAME_CurrentNext);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Current Next.
     *
     * @param CurrentNext The next number to be used
     */
    public void setCurrentNext(int CurrentNext) {
        setValue(COLUMNNAME_CurrentNext, Integer.valueOf(CurrentNext));
    }

    /**
     * Get Current Next (System).
     *
     * @return Next sequence for system use
     */
    public int getCurrentNextSys() {
        Integer ii = getValue(COLUMNNAME_CurrentNextSys);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Current Next (System).
     *
     * @param CurrentNextSys Next sequence for system use
     */
    public void setCurrentNextSys(int CurrentNextSys) {
        setValue(COLUMNNAME_CurrentNextSys, Integer.valueOf(CurrentNextSys));
    }

    /**
     * Get Date Column.
     *
     * @return Fully qualified date column
     */
    public String getDateColumn() {
        return getValue(COLUMNNAME_DateColumn);
    }

    /**
     * Get Decimal Pattern.
     *
     * @return Java Decimal Pattern
     */
    public String getDecimalPattern() {
        return getValue(COLUMNNAME_DecimalPattern);
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
     * Get Increment.
     *
     * @return The number to increment the last document number by
     */
    public int getIncrementNo() {
        Integer ii = getValue(COLUMNNAME_IncrementNo);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Increment.
     *
     * @param IncrementNo The number to increment the last document number by
     */
    public void setIncrementNo(int IncrementNo) {
        setValue(COLUMNNAME_IncrementNo, Integer.valueOf(IncrementNo));
    }

    /**
     * Set Activate Audit.
     *
     * @param IsAudited Activate Audit Trail of what numbers are generated
     */
    public void setIsAudited(boolean IsAudited) {
        setValue(COLUMNNAME_IsAudited, Boolean.valueOf(IsAudited));
    }

    /**
     * Set Auto numbering.
     *
     * @param IsAutoSequence Automatically assign the next number
     */
    public void setIsAutoSequence(boolean IsAutoSequence) {
        setValue(COLUMNNAME_IsAutoSequence, Boolean.valueOf(IsAutoSequence));
    }

    /**
     * Get Organization level.
     *
     * @return This sequence can be defined for each organization
     */
    public boolean isOrgLevelSequence() {
        Object oo = getValue(COLUMNNAME_IsOrgLevelSequence);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Used for Record ID.
     *
     * @param IsTableID The document number will be used as the record key
     */
    public void setIsTableID(boolean IsTableID) {
        setValue(COLUMNNAME_IsTableID, Boolean.valueOf(IsTableID));
    }

    /**
     * Get Used for Record ID.
     *
     * @return The document number will be used as the record key
     */
    public boolean isTableID() {
        Object oo = getValue(COLUMNNAME_IsTableID);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Org Column.
     *
     * @return Fully qualified Organization column (AD_Org_ID)
     */
    public String getOrgColumn() {
        return getValue(COLUMNNAME_OrgColumn);
    }

    /**
     * Get Prefix.
     *
     * @return Prefix before the sequence number
     */
    public String getPrefix() {
        return getValue(COLUMNNAME_Prefix);
    }

    /**
     * Get Restart sequence every month.
     *
     * @return Restart sequence every month
     */
    public boolean isStartNewMonth() {
        Object oo = getValue(COLUMNNAME_StartNewMonth);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Restart sequence every month.
     *
     * @param StartNewMonth Restart sequence every month
     */
    public void setStartNewMonth(boolean StartNewMonth) {
        setValue(COLUMNNAME_StartNewMonth, Boolean.valueOf(StartNewMonth));
    }

    /**
     * Get Restart sequence every Year.
     *
     * @return Restart the sequence with Start on every 1/1
     */
    public boolean isStartNewYear() {
        Object oo = getValue(COLUMNNAME_StartNewYear);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Restart sequence every Year.
     *
     * @param StartNewYear Restart the sequence with Start on every 1/1
     */
    public void setStartNewYear(boolean StartNewYear) {
        setValue(COLUMNNAME_StartNewYear, Boolean.valueOf(StartNewYear));
    }

    /**
     * Get Start No.
     *
     * @return Starting number/position
     */
    public int getStartNo() {
        Integer ii = getValue(COLUMNNAME_StartNo);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Start No.
     *
     * @param StartNo Starting number/position
     */
    public void setStartNo(int StartNo) {
        setValue(COLUMNNAME_StartNo, Integer.valueOf(StartNo));
    }

    /**
     * Get Suffix.
     *
     * @return Suffix after the number
     */
    public String getSuffix() {
        return getValue(COLUMNNAME_Suffix);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
