package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.Attachment;

/**
 * Generated Model for AD_Attachment
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Attachment extends PO {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Attachment(int AD_Attachment_ID) {
        super(AD_Attachment_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Attachment(Row row) {
        super(row);
    }

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return Attachment.accessLevel.intValue();
    }

    public String toString() {
        return "X_AD_Attachment[" + getId() + "]";
    }

    /**
     * Get Attachment.
     *
     * @return Attachment for the document
     */
    public int getAD_AttachmentId() {
        Integer ii = getValue(Attachment.COLUMNNAME_AD_Attachment_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Table.
     *
     * @param AD_Table_ID Database Table information
     */
    public void setRowTableId(int AD_Table_ID) {
        if (AD_Table_ID < 1) setValueNoCheck(Attachment.COLUMNNAME_AD_Table_ID, null);
        else setValueNoCheck(Attachment.COLUMNNAME_AD_Table_ID, AD_Table_ID);
    }

    /**
     * Set Record ID.
     *
     * @param Record_ID Direct internal record ID
     */
    public void setRecordId(int Record_ID) {
        if (Record_ID < 0) setValueNoCheck(Attachment.COLUMNNAME_Record_ID, null);
        else setValueNoCheck(Attachment.COLUMNNAME_Record_ID, Record_ID);
    }

    /**
     * Set Text Message.
     *
     * @param TextMsg Text Message
     */
    public void setTextMsg(String TextMsg) {
        setValue(Attachment.COLUMNNAME_TextMsg, TextMsg);
    }

    /**
     * Get Title.
     *
     * @return Name this entity is referred to as
     */
    public String getTitle() {
        return (String) getValue(Attachment.COLUMNNAME_Title);
    }

    /**
     * Set Title.
     *
     * @param Title Name this entity is referred to as
     */
    public void setTitle(String Title) {
        setValue(Attachment.COLUMNNAME_Title, Title);
    }

    @Override
    public int getTableId() {
        return Attachment.Table_ID;
    }

    /**
     * Set Binary Data.
     *
     * @param BinaryData Binary Data
     */
    public void setBinaryData(byte[] BinaryData) {
        setValueNoCheck(Attachment.COLUMNNAME_BinaryData, BinaryData);
    }


}
