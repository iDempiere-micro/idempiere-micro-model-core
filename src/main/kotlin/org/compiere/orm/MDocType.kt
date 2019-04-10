package org.compiere.orm

import kotliquery.Row
import org.compiere.model.HasName
import org.compiere.model.I_C_DocType
import org.idempiere.common.util.Env
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing
import java.util.logging.Level

import software.hsharp.core.util.executeUpdate
import software.hsharp.core.util.getSQLValue

private val documentTypeFactory = factory { MDocType(it) }

/**
 * Get currency by Id
 */
fun getDocumentType(id: Int) = id loadUsing documentTypeFactory

/**
 * Return the first Doc Type for this BaseType
 *
 * @param DocBaseType
 * @return
 */
fun getDocumentTypeDocType(DocBaseType: String): Int {
    val doc = getDocumentTypeOfDocBaseType(DocBaseType)
    return if (doc.isNotEmpty()) doc[0].id else 0
}

/**
 * Get Client Document Type with DocBaseType
 *
 * @param DocBaseType base document type
 * @return array of doc types
 */
fun getDocumentTypeOfDocBaseType(DocBaseType: String): Array<MDocType> {
    val whereClause = "AD_Client_ID=? AND DocBaseType=?"
    val list = Query(I_C_DocType.Table_Name, whereClause)
        .setParameters(Env.getClientId(), DocBaseType)
        .setOnlyActiveRecords(true)
        .setOrderBy("IsDefault DESC, C_DocType_ID")
        .list<MDocType>()
    return list.toTypedArray()
} // 	getOfDocBaseType

/**
 * Get Client Document Types
 *
 * @return array of doc types
 */
val getClientDocumentTypes: Array<MDocType>
    get() {
        val list = Query(I_C_DocType.Table_Name, null)
            .setClientId()
            .setOnlyActiveRecords(true)
            .list<MDocType>()
        return list.toTypedArray()
    } // 	getOfClient

/**
 * Document Type Model
 *
 * @author Jorg Janke
 * @author Karsten Thiemann FR [ 1782412 ]
 * @author Teo Sarca, www.arhipac.ro
 *  * BF [ 2476824 ] MDocTypeKt.getDocumentTypeOfDocBaseType should return ONLY active records
 *  * BF [ - ] MDocTypeKt.getDocumentTypeOfClient should return ONLY active records. See
 * https://sourceforge.net/forum/message.php?msg_id=6499893
 * @version $Id: MDocType.java,v 1.3 2006/07/30 00:54:54 jjanke Exp $
 */
class MDocType : X_C_DocType {

    /**
     * Is this a Proposal (Not binding)
     *
     * @return true if proposal
     */
    val isProposal: Boolean
        get() = DOCSUBTYPESO_Proposal == docSubTypeSO && DOCBASETYPE_SalesOrder == docBaseType // 	isProposal

    /**
     * Is this a Proposal or Quotation
     *
     * @return true if proposal or quotation
     */
    val isOffer: Boolean
        get() = (DOCSUBTYPESO_Proposal == docSubTypeSO || DOCSUBTYPESO_Quotation == docSubTypeSO) && DOCBASETYPE_SalesOrder == docBaseType // 	isOffer

    /**
     * Get translated doctype name
     *
     * @return Name if available translated
     */
    // warning: to cache this translation you need to change the cache to include language (see i.e.
    // MWFNode)
    val nameTrl: String
        get() = get_Translation(HasName.COLUMNNAME_Name, Env.getADLanguage()) // 	getNameTrl

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param C_DocType_ID id
     */
    constructor(C_DocType_ID: Int) : super(C_DocType_ID) {
        if (C_DocType_ID == 0) {
            setDocumentCopies(0)
            setHasCharges(false)
            setIsDefault(false)
            setIsDocNoControlled(false)
            setIsSOTrx(false)
            setIsPickQAConfirm(false)
            setIsShipConfirm(false)
            setIsSplitWhenDifference(false)
            //
            setIsCreateCounter(true)
            setIsDefaultCounterDoc(false)
            setIsIndexed(true)
        }
    } // 	MDocType

    /**
     * Load Constructor
     */
    constructor(row: Row) : super(row) {} // 	MDocType

    /**
     * New Constructor
     *
     * @param DocBaseType document base type
     * @param Name name
     */
    constructor(DocBaseType: String, Name: String) : this(0) {
        setOrgId(0)
        docBaseType = DocBaseType
        name = Name
        printName = Name
        setGLCategoryId()
    } // 	MDocType

    /**
     * Set Default GL Category
     */
    fun setGLCategoryId() {
        val sql = ("SELECT GL_Category_ID FROM GL_Category" +
                " WHERE AD_Client_ID=?" +
                " ORDER BY IsDefault DESC, GL_Category_ID")
        val GL_Category_ID = getSQLValue(sql, clientId)
        glCategoryId = GL_Category_ID
    } // 	setGLCategoryId

    /**
     * Set SOTrx based on document base type
     */
    fun setIsSOTrx() {
        val isSOTrx = (DOCBASETYPE_SalesOrder == docBaseType ||
                DOCBASETYPE_MaterialDelivery == docBaseType ||
                docBaseType.startsWith("AR"))
        super.setIsSOTrx(isSOTrx)
    } // 	setIsSOTrx

    /**
     * String Representation
     *
     * @return info
     */
    override fun toString(): String {
        return "MDocType[" + id +
                "-" +
                name +
                ",DocNoSequence_ID=" +
                docNoSequenceId +
                "]"
    } // 	toString

    /**
     * Get Print Name
     *
     * @param AD_Language language
     * @return print Name if available translated
     */
    fun getPrintName(AD_Language: String?): String {
        return if (AD_Language == null || AD_Language.length == 0) super.getPrintName() else get_Translation(
            I_C_DocType.COLUMNNAME_PrintName,
            AD_Language
        )
    } // 	getPrintName

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    override fun beforeSave(newRecord: Boolean): Boolean {
        return true
    } // 	beforeSave

    /**
     * After Save
     *
     * @param newRecord new
     * @param success success
     * @return success
     */
    override fun afterSave(newRecord: Boolean, success: Boolean): Boolean {
        if (newRecord && success) {
            // 	Add doctype/docaction access to all roles of client

            val sqlDocAction = "INSERT INTO AD_Document_Action_Access " +
                    "(AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
                    "C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) " +
                    "(SELECT " +
                    clientId +
                    ",0,'Y', SysDate," +
                    getUpdatedBy() +
                    ", SysDate," +
                    getUpdatedBy() +
                    ", doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID " +
                    "FROM AD_Client client " +
                    "INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) " +
                    "INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) " +
                    "INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) " +
                    "WHERE client.AD_Client_ID=" +
                    clientId +
                    " AND doctype.C_DocType_ID=" +
                    id +
                    " AND rol.IsManual='N'" +
                    ")"
            val docact = executeUpdate(sqlDocAction)
            if (log.isLoggable(Level.FINE)) log.fine("AD_Document_Action_Access=$docact")
        }
        return success
    } // 	afterSave

    /**
     * Executed before Delete operation.
     *
     * @return true if delete is a success
     */
    override fun beforeDelete(): Boolean {
        // delete access records
        val msgdb = "DELETE FROM AD_Document_Action_Access WHERE C_DocType_ID=" + id
        val docactDel = executeUpdate(msgdb)
        if (log.isLoggable(Level.FINE))
            log.fine("Delete AD_Document_Action_Access=" + docactDel + " for C_DocType_ID: " + id)
        return docactDel >= 0
    } //  beforeDelete

    companion object {
        private val serialVersionUID = -6556521509479670059L

        /**
         * GL Journal = GLJ
         */
        const val DOCBASETYPE_GLJournal = "GLJ"
        /**
         * GL Document = GLD
         */
        const val DOCBASETYPE_GLDocument = "GLD"
        /**
         * AP Invoice = API
         */
        const val DOCBASETYPE_APInvoice = "API"
        /**
         * AP Payment = APP
         */
        const val DOCBASETYPE_APPayment = "APP"
        /**
         * AR Invoice = ARI
         */
        const val DOCBASETYPE_ARInvoice = "ARI"
        /**
         * AR Receipt = ARR
         */
        const val DOCBASETYPE_ARReceipt = "ARR"
        /**
         * Sales Order = SOO
         */
        const val DOCBASETYPE_SalesOrder = "SOO"
        /**
         * Material Delivery = MMS
         */
        const val DOCBASETYPE_MaterialDelivery = "MMS"
        /**
         * Material Receipt = MMR
         */
        const val DOCBASETYPE_MaterialReceipt = "MMR"
        /**
         * Material Movement = MMM
         */
        const val DOCBASETYPE_MaterialMovement = "MMM"
        /**
         * Purchase Order = POO
         */
        const val DOCBASETYPE_PurchaseOrder = "POO"
        /**
         * Purchase Requisition = POR
         */
        const val DOCBASETYPE_PurchaseRequisition = "POR"
        /**
         * Material Physical Inventory = MMI
         */
        const val DOCBASETYPE_MaterialPhysicalInventory = "MMI"
        /**
         * AP Credit Memo = APC
         */
        const val DOCBASETYPE_APCreditMemo = "APC"
        /**
         * AR Credit Memo = ARC
         */
        const val DOCBASETYPE_ARCreditMemo = "ARC"
        /**
         * Bank Statement = CMB
         */
        const val DOCBASETYPE_BankStatement = "CMB"
        /**
         * Cash Journal = CMC
         */
        const val DOCBASETYPE_CashJournal = "CMC"
        /**
         * Payment Allocation = CMA
         */
        const val DOCBASETYPE_PaymentAllocation = "CMA"
        /**
         * Material Production = MMP
         */
        const val DOCBASETYPE_MaterialProduction = "MMP"
        /**
         * Match Invoice = MXI
         */
        const val DOCBASETYPE_MatchInvoice = "MXI"
        /**
         * Match PO = MXP
         */
        const val DOCBASETYPE_MatchPO = "MXP"
        /**
         * Project Issue = PJI
         */
        const val DOCBASETYPE_ProjectIssue = "PJI"
        /**
         * Maintenance Order = MOF
         */
        const val DOCBASETYPE_MaintenanceOrder = "MOF"
        /**
         * Manufacturing Order = MOP
         */
        const val DOCBASETYPE_ManufacturingOrder = "MOP"
        /**
         * Quality Order = MQO
         */
        const val DOCBASETYPE_QualityOrder = "MQO"
        /**
         * Payroll = HRP
         */
        const val DOCBASETYPE_Payroll = "HRP"
        /**
         * Distribution Order = DOO
         */
        const val DOCBASETYPE_DistributionOrder = "DOO"
        /**
         * Manufacturing Cost Collector = MCC
         */
        const val DOCBASETYPE_ManufacturingCostCollector = "MCC"
        /**
         * Physical Inventory = PI
         */
        const val DOCSUBTYPEINV_PhysicalInventory = "PI"
        /**
         * Internal Use Inventory = IU
         */
        const val DOCSUBTYPEINV_InternalUseInventory = "IU"
        /**
         * Cost Adjustment = CA
         */
        const val DOCSUBTYPEINV_CostAdjustment = "CA"
        /**
         * On Credit Order = WI
         */
        const val DOCSUBTYPESO_OnCreditOrder = "WI"
        /**
         * POS Order = WR
         */
        const val DOCSUBTYPESO_POSOrder = "WR"
        /**
         * Warehouse Order = WP
         */
        const val DOCSUBTYPESO_WarehouseOrder = "WP"
        /**
         * Standard Order = SO
         */
        const val DOCSUBTYPESO_StandardOrder = "SO"
        /**
         * Proposal = ON
         */
        const val DOCSUBTYPESO_Proposal = "ON"
        /**
         * Quotation = OB
         */
        const val DOCSUBTYPESO_Quotation = "OB"
        /**
         * Return Material = RM
         */
        const val DOCSUBTYPESO_ReturnMaterial = "RM"
        /**
         * Prepay Order = PR
         */
        const val DOCSUBTYPESO_PrepayOrder = "PR"
    }
} // 	MDocType
