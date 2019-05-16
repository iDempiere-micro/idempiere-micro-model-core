package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.DocumentType;

/**
 * Generated Model for C_DocType
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_DocType extends BasePOName implements DocumentType {

    /**
     * DocBaseType AD_Reference_ID=183
     */
    public static final int DOCBASETYPE_AD_Reference_ID = 183;

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_DocType(int C_DocType_ID) {
        super(C_DocType_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_DocType(Row row) {
        super(row);
    } //	MDocType

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_C_DocType[" + getId() + "]";
    }

    /**
     * Get Difference Document.
     *
     * @return Document type for generating in dispute Shipments
     */
    public int getDocTypeDifferenceId() {
        Integer ii = getValue(COLUMNNAME_C_DocTypeDifference_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Document Type.
     *
     * @return Document type or rules
     */
    public int getDocTypeId() {
        Integer ii = getValue(COLUMNNAME_C_DocType_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Document Type for Invoice.
     *
     * @return Document type used for invoices generated from this sales document
     */
    public int getDocTypeInvoiceId() {
        Integer ii = getValue(COLUMNNAME_C_DocTypeInvoice_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Document Type for Invoice.
     *
     * @param C_DocTypeInvoice_ID Document type used for invoices generated from this sales document
     */
    public void setDocTypeInvoiceId(int C_DocTypeInvoice_ID) {
        if (C_DocTypeInvoice_ID < 1) setValue(COLUMNNAME_C_DocTypeInvoice_ID, null);
        else setValue(COLUMNNAME_C_DocTypeInvoice_ID, C_DocTypeInvoice_ID);
    }

    /**
     * Get Document Type for Shipment.
     *
     * @return Document type used for shipments generated from this sales document
     */
    public int getDocTypeShipmentId() {
        Integer ii = getValue(COLUMNNAME_C_DocTypeShipment_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Document Type for Shipment.
     *
     * @param C_DocTypeShipment_ID Document type used for shipments generated from this sales document
     */
    public void setDocTypeShipmentId(int C_DocTypeShipment_ID) {
        if (C_DocTypeShipment_ID < 1) setValue(COLUMNNAME_C_DocTypeShipment_ID, null);
        else setValue(COLUMNNAME_C_DocTypeShipment_ID, C_DocTypeShipment_ID);
    }

    /**
     * Get Definite Sequence.
     *
     * @return Definite Sequence
     */
    public int getDefiniteSequenceId() {
        Integer ii = getValue(COLUMNNAME_DefiniteSequence_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Document BaseType.
     *
     * @return Logical type of document
     */
    public String getDocBaseType() {
        return getValue(COLUMNNAME_DocBaseType);
    }

    /**
     * Set Document BaseType.
     *
     * @param DocBaseType Logical type of document
     */
    public void setDocBaseType(String DocBaseType) {

        setValue(COLUMNNAME_DocBaseType, DocBaseType);
    }

    /**
     * Get Document Sequence.
     *
     * @return Document sequence determines the numbering of documents
     */
    public int getDocNoSequenceId() {
        Integer ii = getValue(COLUMNNAME_DocNoSequence_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Document Sequence.
     *
     * @param DocNoSequence_ID Document sequence determines the numbering of documents
     */
    public void setDocNoSequenceId(int DocNoSequence_ID) {
        if (DocNoSequence_ID < 1) setValue(COLUMNNAME_DocNoSequence_ID, null);
        else setValue(COLUMNNAME_DocNoSequence_ID, DocNoSequence_ID);
    }

    /**
     * Get Inv Sub Type.
     *
     * @return Inventory Sub Type
     */
    public String getDocSubTypeInv() {
        return getValue(COLUMNNAME_DocSubTypeInv);
    }

    /**
     * Set Inv Sub Type.
     *
     * @param DocSubTypeInv Inventory Sub Type
     */
    public void setDocSubTypeInv(String DocSubTypeInv) {

        setValue(COLUMNNAME_DocSubTypeInv, DocSubTypeInv);
    }

    /**
     * Get SO Sub Type.
     *
     * @return Sales Order Sub Type
     */
    public String getDocSubTypeSO() {
        return getValue(COLUMNNAME_DocSubTypeSO);
    }

    /**
     * Set SO Sub Type.
     *
     * @param DocSubTypeSO Sales Order Sub Type
     */
    public void setDocSubTypeSO(String DocSubTypeSO) {

        setValue(COLUMNNAME_DocSubTypeSO, DocSubTypeSO);
    }

    /**
     * Set Document Copies.
     *
     * @param DocumentCopies Number of copies to be printed
     */
    public void setDocumentCopies(int DocumentCopies) {
        setValue(COLUMNNAME_DocumentCopies, DocumentCopies);
    }

    /**
     * Get GL Category.
     *
     * @return General Ledger Category
     */
    public int getGLCategoryId() {
        Integer ii = getValue(COLUMNNAME_GL_Category_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set GL Category.
     *
     * @param GL_Category_ID General Ledger Category
     */
    public void setGLCategoryId(int GL_Category_ID) {
        if (GL_Category_ID < 1) setValue(COLUMNNAME_GL_Category_ID, null);
        else setValue(COLUMNNAME_GL_Category_ID, GL_Category_ID);
    }

    /**
     * Set Charges.
     *
     * @param HasCharges Charges can be added to the document
     */
    public void setHasCharges(boolean HasCharges) {
        setValue(COLUMNNAME_HasCharges, HasCharges);
    }

    /**
     * Get Mandatory Charge or Product.
     *
     * @return Mandatory Charge or Product
     */
    public boolean isChargeOrProductMandatory() {
        Object oo = getValue(COLUMNNAME_IsChargeOrProductMandatory);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Create Counter Document.
     *
     * @param IsCreateCounter Create Counter Document
     */
    public void setIsCreateCounter(boolean IsCreateCounter) {
        setValue(COLUMNNAME_IsCreateCounter, IsCreateCounter);
    }

    /**
     * Get Create Counter Document.
     *
     * @return Create Counter Document
     */
    public boolean isCreateCounter() {
        Object oo = getValue(COLUMNNAME_IsCreateCounter);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Default.
     *
     * @param IsDefault Default value
     */
    public void setIsDefault(boolean IsDefault) {
        setValue(COLUMNNAME_IsDefault, IsDefault);
    }

    /**
     * Get Default.
     *
     * @return Default value
     */
    public boolean isDefault() {
        Object oo = getValue(COLUMNNAME_IsDefault);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Default Counter Document.
     *
     * @param IsDefaultCounterDoc The document type is the default counter document type
     */
    public void setIsDefaultCounterDoc(boolean IsDefaultCounterDoc) {
        setValue(COLUMNNAME_IsDefaultCounterDoc, IsDefaultCounterDoc);
    }

    /**
     * Get Default Counter Document.
     *
     * @return The document type is the default counter document type
     */
    public boolean isDefaultCounterDoc() {
        Object oo = getValue(COLUMNNAME_IsDefaultCounterDoc);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Document is Number Controlled.
     *
     * @param IsDocNoControlled The document has a document sequence
     */
    public void setIsDocNoControlled(boolean IsDocNoControlled) {
        setValue(COLUMNNAME_IsDocNoControlled, IsDocNoControlled);
    }

    /**
     * Get Document is Number Controlled.
     *
     * @return The document has a document sequence
     */
    public boolean isDocNoControlled() {
        Object oo = getValue(COLUMNNAME_IsDocNoControlled);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Indexed.
     *
     * @param IsIndexed Index the document for the internal search engine
     */
    public void setIsIndexed(boolean IsIndexed) {
        setValue(COLUMNNAME_IsIndexed, IsIndexed);
    }

    /**
     * Get In Transit.
     *
     * @return Movement is in transit
     */
    public boolean isInTransit() {
        Object oo = getValue(COLUMNNAME_IsInTransit);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Overwrite Date on Complete.
     *
     * @return Overwrite Date on Complete
     */
    public boolean isOverwriteDateOnComplete() {
        Object oo = getValue(COLUMNNAME_IsOverwriteDateOnComplete);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Overwrite Sequence on Complete.
     *
     * @return Overwrite Sequence on Complete
     */
    public boolean isOverwriteSeqOnComplete() {
        Object oo = getValue(COLUMNNAME_IsOverwriteSeqOnComplete);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Pick/QA Confirmation.
     *
     * @param IsPickQAConfirm Require Pick or QA Confirmation before processing
     */
    public void setIsPickQAConfirm(boolean IsPickQAConfirm) {
        setValue(COLUMNNAME_IsPickQAConfirm, IsPickQAConfirm);
    }

    /**
     * Get Pick/QA Confirmation.
     *
     * @return Require Pick or QA Confirmation before processing
     */
    public boolean isPickQAConfirm() {
        Object oo = getValue(COLUMNNAME_IsPickQAConfirm);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Prepare Split Document.
     *
     * @return Prepare generated split shipment/receipt document
     */
    public boolean isPrepareSplitDocument() {
        Object oo = getValue(COLUMNNAME_IsPrepareSplitDocument);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Ship/Receipt Confirmation.
     *
     * @param IsShipConfirm Require Ship or Receipt Confirmation before processing
     */
    public void setIsShipConfirm(boolean IsShipConfirm) {
        setValue(COLUMNNAME_IsShipConfirm, IsShipConfirm);
    }

    /**
     * Get Ship/Receipt Confirmation.
     *
     * @return Require Ship or Receipt Confirmation before processing
     */
    public boolean isShipConfirm() {
        Object oo = getValue(COLUMNNAME_IsShipConfirm);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Sales Transaction.
     *
     * @param IsSOTrx This is a Sales Transaction
     */
    public void setIsSOTrx(boolean IsSOTrx) {
        setValue(COLUMNNAME_IsSOTrx, IsSOTrx);
    }

    /**
     * Get Sales Transaction.
     *
     * @return This is a Sales Transaction
     */
    public boolean isSOTrx() {
        Object oo = getValue(COLUMNNAME_IsSOTrx);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Split when Difference.
     *
     * @param IsSplitWhenDifference Split document when there is a difference
     */
    public void setIsSplitWhenDifference(boolean IsSplitWhenDifference) {
        setValue(COLUMNNAME_IsSplitWhenDifference, IsSplitWhenDifference);
    }

    /**
     * Get Split when Difference.
     *
     * @return Split document when there is a difference
     */
    public boolean isSplitWhenDifference() {
        Object oo = getValue(COLUMNNAME_IsSplitWhenDifference);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Print Text.
     *
     * @return The label text to be printed on a document or correspondence.
     */
    public String getPrintName() {
        return getValue(COLUMNNAME_PrintName);
    }

    /**
     * Set Print Text.
     *
     * @param PrintName The label text to be printed on a document or correspondence.
     */
    public void setPrintName(String PrintName) {
        setValue(COLUMNNAME_PrintName, PrintName);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
