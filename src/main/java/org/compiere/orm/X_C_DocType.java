package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_C_DocType;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for C_DocType
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_DocType extends BasePOName implements I_C_DocType, I_Persistent {

  /** DocBaseType AD_Reference_ID=183 */
  public static final int DOCBASETYPE_AD_Reference_ID = 183;
  /** GL Journal = GLJ */
  public static final String DOCBASETYPE_GLJournal = "GLJ";
  /** GL Document = GLD */
  public static final String DOCBASETYPE_GLDocument = "GLD";
  /** AP Invoice = API */
  public static final String DOCBASETYPE_APInvoice = "API";
  /** AP Payment = APP */
  public static final String DOCBASETYPE_APPayment = "APP";
  /** AR Invoice = ARI */
  public static final String DOCBASETYPE_ARInvoice = "ARI";
  /** AR Receipt = ARR */
  public static final String DOCBASETYPE_ARReceipt = "ARR";
  /** Sales Order = SOO */
  public static final String DOCBASETYPE_SalesOrder = "SOO";
  /** Material Delivery = MMS */
  public static final String DOCBASETYPE_MaterialDelivery = "MMS";
  /** Material Receipt = MMR */
  public static final String DOCBASETYPE_MaterialReceipt = "MMR";
  /** Material Movement = MMM */
  public static final String DOCBASETYPE_MaterialMovement = "MMM";
  /** Purchase Order = POO */
  public static final String DOCBASETYPE_PurchaseOrder = "POO";
  /** Purchase Requisition = POR */
  public static final String DOCBASETYPE_PurchaseRequisition = "POR";
  /** Material Physical Inventory = MMI */
  public static final String DOCBASETYPE_MaterialPhysicalInventory = "MMI";
  /** AP Credit Memo = APC */
  public static final String DOCBASETYPE_APCreditMemo = "APC";
  /** AR Credit Memo = ARC */
  public static final String DOCBASETYPE_ARCreditMemo = "ARC";
  /** Bank Statement = CMB */
  public static final String DOCBASETYPE_BankStatement = "CMB";
  /** Cash Journal = CMC */
  public static final String DOCBASETYPE_CashJournal = "CMC";
  /** Payment Allocation = CMA */
  public static final String DOCBASETYPE_PaymentAllocation = "CMA";
  /** Material Production = MMP */
  public static final String DOCBASETYPE_MaterialProduction = "MMP";
  /** Match Invoice = MXI */
  public static final String DOCBASETYPE_MatchInvoice = "MXI";
  /** Match PO = MXP */
  public static final String DOCBASETYPE_MatchPO = "MXP";
  /** Project Issue = PJI */
  public static final String DOCBASETYPE_ProjectIssue = "PJI";
  /** Maintenance Order = MOF */
  public static final String DOCBASETYPE_MaintenanceOrder = "MOF";
  /** Manufacturing Order = MOP */
  public static final String DOCBASETYPE_ManufacturingOrder = "MOP";
  /** Quality Order = MQO */
  public static final String DOCBASETYPE_QualityOrder = "MQO";
  /** Payroll = HRP */
  public static final String DOCBASETYPE_Payroll = "HRP";
  /** Distribution Order = DOO */
  public static final String DOCBASETYPE_DistributionOrder = "DOO";
  /** Manufacturing Cost Collector = MCC */
  public static final String DOCBASETYPE_ManufacturingCostCollector = "MCC";
  /** Physical Inventory = PI */
  public static final String DOCSUBTYPEINV_PhysicalInventory = "PI";
  /** Internal Use Inventory = IU */
  public static final String DOCSUBTYPEINV_InternalUseInventory = "IU";
  /** Cost Adjustment = CA */
  public static final String DOCSUBTYPEINV_CostAdjustment = "CA";
  /** On Credit Order = WI */
  public static final String DOCSUBTYPESO_OnCreditOrder = "WI";
  /** POS Order = WR */
  public static final String DOCSUBTYPESO_POSOrder = "WR";
  /** Warehouse Order = WP */
  public static final String DOCSUBTYPESO_WarehouseOrder = "WP";
  /** Standard Order = SO */
  public static final String DOCSUBTYPESO_StandardOrder = "SO";
  /** Proposal = ON */
  public static final String DOCSUBTYPESO_Proposal = "ON";
  /** Quotation = OB */
  public static final String DOCSUBTYPESO_Quotation = "OB";
  /** Return Material = RM */
  public static final String DOCSUBTYPESO_ReturnMaterial = "RM";
  /** Prepay Order = PR */
  public static final String DOCSUBTYPESO_PrepayOrder = "PR";
  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_DocType(Properties ctx, int C_DocType_ID, String trxName) {
    super(ctx, C_DocType_ID, trxName);
  }

  /** Load Constructor */
  public X_C_DocType(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }
  public X_C_DocType(Properties ctx, Row row) {
    super(ctx, row);
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
    StringBuffer sb = new StringBuffer("X_C_DocType[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Get Difference Document.
   *
   * @return Document type for generating in dispute Shipments
   */
  public int getC_DocTypeDifference_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_DocTypeDifference_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Get Document Type.
   *
   * @return Document type or rules
   */
  public int getC_DocType_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_DocType_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Get Document Type for Invoice.
   *
   * @return Document type used for invoices generated from this sales document
   */
  public int getC_DocTypeInvoice_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_DocTypeInvoice_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Document Type for Invoice.
   *
   * @param C_DocTypeInvoice_ID Document type used for invoices generated from this sales document
   */
  public void setC_DocTypeInvoice_ID(int C_DocTypeInvoice_ID) {
    if (C_DocTypeInvoice_ID < 1) set_Value(COLUMNNAME_C_DocTypeInvoice_ID, null);
    else set_Value(COLUMNNAME_C_DocTypeInvoice_ID, Integer.valueOf(C_DocTypeInvoice_ID));
  }

  /**
   * Get Document Type for ProForma.
   *
   * @return Document type used for pro forma invoices generated from this sales document
   */
  public int getC_DocTypeProforma_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_DocTypeProforma_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Get Document Type for Shipment.
   *
   * @return Document type used for shipments generated from this sales document
   */
  public int getC_DocTypeShipment_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_DocTypeShipment_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Document Type for Shipment.
   *
   * @param C_DocTypeShipment_ID Document type used for shipments generated from this sales document
   */
  public void setC_DocTypeShipment_ID(int C_DocTypeShipment_ID) {
    if (C_DocTypeShipment_ID < 1) set_Value(COLUMNNAME_C_DocTypeShipment_ID, null);
    else set_Value(COLUMNNAME_C_DocTypeShipment_ID, Integer.valueOf(C_DocTypeShipment_ID));
  }

  /**
   * Get Definite Sequence.
   *
   * @return Definite Sequence
   */
  public int getDefiniteSequence_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_DefiniteSequence_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Get Document BaseType.
   *
   * @return Logical type of document
   */
  public String getDocBaseType() {
    return (String) get_Value(COLUMNNAME_DocBaseType);
  }

  /**
   * Set Document BaseType.
   *
   * @param DocBaseType Logical type of document
   */
  public void setDocBaseType(String DocBaseType) {

    set_Value(COLUMNNAME_DocBaseType, DocBaseType);
  }

  /**
   * Get Document Sequence.
   *
   * @return Document sequence determines the numbering of documents
   */
  public int getDocNoSequence_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_DocNoSequence_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Document Sequence.
   *
   * @param DocNoSequence_ID Document sequence determines the numbering of documents
   */
  public void setDocNoSequence_ID(int DocNoSequence_ID) {
    if (DocNoSequence_ID < 1) set_Value(COLUMNNAME_DocNoSequence_ID, null);
    else set_Value(COLUMNNAME_DocNoSequence_ID, Integer.valueOf(DocNoSequence_ID));
  }

  /**
   * Get Inv Sub Type.
   *
   * @return Inventory Sub Type
   */
  public String getDocSubTypeInv() {
    return (String) get_Value(COLUMNNAME_DocSubTypeInv);
  }

  /**
   * Set Inv Sub Type.
   *
   * @param DocSubTypeInv Inventory Sub Type
   */
  public void setDocSubTypeInv(String DocSubTypeInv) {

    set_Value(COLUMNNAME_DocSubTypeInv, DocSubTypeInv);
  }

  /**
   * Get SO Sub Type.
   *
   * @return Sales Order Sub Type
   */
  public String getDocSubTypeSO() {
    return (String) get_Value(COLUMNNAME_DocSubTypeSO);
  }

  /**
   * Set SO Sub Type.
   *
   * @param DocSubTypeSO Sales Order Sub Type
   */
  public void setDocSubTypeSO(String DocSubTypeSO) {

    set_Value(COLUMNNAME_DocSubTypeSO, DocSubTypeSO);
  }

  /**
   * Set Document Copies.
   *
   * @param DocumentCopies Number of copies to be printed
   */
  public void setDocumentCopies(int DocumentCopies) {
    set_Value(COLUMNNAME_DocumentCopies, Integer.valueOf(DocumentCopies));
  }

  /**
   * Get GL Category.
   *
   * @return General Ledger Category
   */
  public int getGL_Category_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_GL_Category_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set GL Category.
   *
   * @param GL_Category_ID General Ledger Category
   */
  public void setGL_Category_ID(int GL_Category_ID) {
    if (GL_Category_ID < 1) set_Value(COLUMNNAME_GL_Category_ID, null);
    else set_Value(COLUMNNAME_GL_Category_ID, Integer.valueOf(GL_Category_ID));
  }

  /**
   * Set Charges.
   *
   * @param HasCharges Charges can be added to the document
   */
  public void setHasCharges(boolean HasCharges) {
    set_Value(COLUMNNAME_HasCharges, Boolean.valueOf(HasCharges));
  }

  /**
   * Get Mandatory Charge or Product.
   *
   * @return Mandatory Charge or Product
   */
  public boolean isChargeOrProductMandatory() {
    Object oo = get_Value(COLUMNNAME_IsChargeOrProductMandatory);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(COLUMNNAME_IsCreateCounter, Boolean.valueOf(IsCreateCounter));
  }

  /**
   * Get Create Counter Document.
   *
   * @return Create Counter Document
   */
  public boolean isCreateCounter() {
    Object oo = get_Value(COLUMNNAME_IsCreateCounter);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
  }

  /**
   * Get Default.
   *
   * @return Default value
   */
  public boolean isDefault() {
    Object oo = get_Value(COLUMNNAME_IsDefault);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(COLUMNNAME_IsDefaultCounterDoc, Boolean.valueOf(IsDefaultCounterDoc));
  }

  /**
   * Get Default Counter Document.
   *
   * @return The document type is the default counter document type
   */
  public boolean isDefaultCounterDoc() {
    Object oo = get_Value(COLUMNNAME_IsDefaultCounterDoc);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(COLUMNNAME_IsDocNoControlled, Boolean.valueOf(IsDocNoControlled));
  }

  /**
   * Get Document is Number Controlled.
   *
   * @return The document has a document sequence
   */
  public boolean isDocNoControlled() {
    Object oo = get_Value(COLUMNNAME_IsDocNoControlled);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(COLUMNNAME_IsIndexed, Boolean.valueOf(IsIndexed));
  }

  /**
   * Get In Transit.
   *
   * @return Movement is in transit
   */
  public boolean isInTransit() {
    Object oo = get_Value(COLUMNNAME_IsInTransit);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    Object oo = get_Value(COLUMNNAME_IsOverwriteDateOnComplete);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    Object oo = get_Value(COLUMNNAME_IsOverwriteSeqOnComplete);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(COLUMNNAME_IsPickQAConfirm, Boolean.valueOf(IsPickQAConfirm));
  }

  /**
   * Get Pick/QA Confirmation.
   *
   * @return Require Pick or QA Confirmation before processing
   */
  public boolean isPickQAConfirm() {
    Object oo = get_Value(COLUMNNAME_IsPickQAConfirm);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    Object oo = get_Value(COLUMNNAME_IsPrepareSplitDocument);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(COLUMNNAME_IsShipConfirm, Boolean.valueOf(IsShipConfirm));
  }

  /**
   * Get Ship/Receipt Confirmation.
   *
   * @return Require Ship or Receipt Confirmation before processing
   */
  public boolean isShipConfirm() {
    Object oo = get_Value(COLUMNNAME_IsShipConfirm);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(COLUMNNAME_IsSOTrx, Boolean.valueOf(IsSOTrx));
  }

  /**
   * Get Sales Transaction.
   *
   * @return This is a Sales Transaction
   */
  public boolean isSOTrx() {
    Object oo = get_Value(COLUMNNAME_IsSOTrx);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(COLUMNNAME_IsSplitWhenDifference, Boolean.valueOf(IsSplitWhenDifference));
  }

  /**
   * Get Split when Difference.
   *
   * @return Split document when there is a difference
   */
  public boolean isSplitWhenDifference() {
    Object oo = get_Value(COLUMNNAME_IsSplitWhenDifference);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    return (String) get_Value(COLUMNNAME_PrintName);
  }

  /**
   * Set Print Text.
   *
   * @param PrintName The label text to be printed on a document or correspondence.
   */
  public void setPrintName(String PrintName) {
    set_Value(COLUMNNAME_PrintName, PrintName);
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
