package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_AD_Attachment;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Attachment
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Attachment extends PO implements I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Attachment(Properties ctx, int AD_Attachment_ID, String trxName) {
        super(ctx, AD_Attachment_ID, trxName);
        /**
         * if (AD_Attachment_ID == 0) { setAD_Attachment_ID (0); setAD_Table_ID (0); setRecord_ID (0);
         * setTitle (null); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_Attachment(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return I_AD_Attachment.accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_AD_Attachment[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Attachment.
     *
     * @return Attachment for the document
     */
    public int getAD_Attachment_ID() {
        Integer ii = (Integer) get_Value(I_AD_Attachment.COLUMNNAME_AD_Attachment_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Attachment.
     *
     * @param AD_Attachment_ID Attachment for the document
     */
    public void setAD_Attachment_ID(int AD_Attachment_ID) {
        if (AD_Attachment_ID < 1) set_ValueNoCheck(I_AD_Attachment.COLUMNNAME_AD_Attachment_ID, null);
        else
            set_ValueNoCheck(
                    I_AD_Attachment.COLUMNNAME_AD_Attachment_ID, Integer.valueOf(AD_Attachment_ID));
    }

    /**
     * Get AD_Attachment_UU.
     *
     * @return AD_Attachment_UU
     */
    public String getAD_Attachment_UU() {
        return (String) get_Value(I_AD_Attachment.COLUMNNAME_AD_Attachment_UU);
    }

    /**
     * Set AD_Attachment_UU.
     *
     * @param AD_Attachment_UU AD_Attachment_UU
     */
    public void setAD_Attachment_UU(String AD_Attachment_UU) {
        set_Value(I_AD_Attachment.COLUMNNAME_AD_Attachment_UU, AD_Attachment_UU);
    }

    public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException {
        return (org.compiere.model.I_AD_Table)
                MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
                        .getPO(getAD_Table_ID(), get_TrxName());
    }

    /**
     * Get Table.
     *
     * @return Database Table information
     */
    public int getAD_Table_ID() {
        Integer ii = (Integer) get_Value(I_AD_Attachment.COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Table.
     *
     * @param AD_Table_ID Database Table information
     */
    public void setAD_Table_ID(int AD_Table_ID) {
        if (AD_Table_ID < 1) set_ValueNoCheck(I_AD_Attachment.COLUMNNAME_AD_Table_ID, null);
        else set_ValueNoCheck(I_AD_Attachment.COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
    }

    /**
     * Get Binary Data.
     *
     * @return Binary Data
     */
    public byte[] getBinaryData() {
        return (byte[]) get_Value(I_AD_Attachment.COLUMNNAME_BinaryData);
    }

    /**
     * Set Binary Data.
     *
     * @param BinaryData Binary Data
     */
    public void setBinaryData(byte[] BinaryData) {
        set_ValueNoCheck(I_AD_Attachment.COLUMNNAME_BinaryData, BinaryData);
    }

    /**
     * Get Record ID.
     *
     * @return Direct internal record ID
     */
    public int getRecord_ID() {
        Integer ii = (Integer) get_Value(I_AD_Attachment.COLUMNNAME_Record_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Record ID.
     *
     * @param Record_ID Direct internal record ID
     */
    public void setRecord_ID(int Record_ID) {
        if (Record_ID < 0) set_ValueNoCheck(I_AD_Attachment.COLUMNNAME_Record_ID, null);
        else set_ValueNoCheck(I_AD_Attachment.COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
    }

    /**
     * Get Text Message.
     *
     * @return Text Message
     */
    public String getTextMsg() {
        return (String) get_Value(I_AD_Attachment.COLUMNNAME_TextMsg);
    }

    /**
     * Set Text Message.
     *
     * @param TextMsg Text Message
     */
    public void setTextMsg(String TextMsg) {
        set_Value(I_AD_Attachment.COLUMNNAME_TextMsg, TextMsg);
    }

    /**
     * Get Title.
     *
     * @return Name this entity is referred to as
     */
    public String getTitle() {
        return (String) get_Value(I_AD_Attachment.COLUMNNAME_Title);
    }

    /**
     * Set Title.
     *
     * @param Title Name this entity is referred to as
     */
    public void setTitle(String Title) {
        set_Value(I_AD_Attachment.COLUMNNAME_Title, Title);
    }

    /**
     * Get Record ID/ColumnName
     *
     * @return ID/ColumnName pair
     */
    public KeyNamePair getKeyNamePair() {
        return new KeyNamePair(getId(), getTitle());
    }

    @Override
    public int getTableId() {
        return I_AD_Attachment.Table_ID;
    }
}
