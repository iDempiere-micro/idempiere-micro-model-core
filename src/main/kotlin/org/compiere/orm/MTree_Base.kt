package org.compiere.orm

import kotliquery.Row
import org.compiere.orm.MTree_Base.Companion.TREETYPE_Activity
import org.compiere.orm.MTree_Base.Companion.TREETYPE_BPartner
import org.compiere.orm.MTree_Base.Companion.TREETYPE_BoM
import org.compiere.orm.MTree_Base.Companion.TREETYPE_CMContainer
import org.compiere.orm.MTree_Base.Companion.TREETYPE_CMContainerStage
import org.compiere.orm.MTree_Base.Companion.TREETYPE_CMMedia
import org.compiere.orm.MTree_Base.Companion.TREETYPE_CMTemplate
import org.compiere.orm.MTree_Base.Companion.TREETYPE_Campaign
import org.compiere.orm.MTree_Base.Companion.TREETYPE_ElementValue
import org.compiere.orm.MTree_Base.Companion.TREETYPE_Menu
import org.compiere.orm.MTree_Base.Companion.TREETYPE_Organization
import org.compiere.orm.MTree_Base.Companion.TREETYPE_Product
import org.compiere.orm.MTree_Base.Companion.TREETYPE_ProductCategory
import org.compiere.orm.MTree_Base.Companion.TREETYPE_Project
import org.compiere.orm.MTree_Base.Companion.TREETYPE_SalesRegion
import org.compiere.orm.MTree_Base.Companion.TREETYPE_User1
import org.compiere.orm.MTree_Base.Companion.TREETYPE_User2
import org.compiere.orm.MTree_Base.Companion.TREETYPE_User3
import org.compiere.orm.MTree_Base.Companion.TREETYPE_User4
import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing
import software.hsharp.core.orm.getTable

private val treeFactory = factory { MTree_Base(it) }

/**
 * Get MTree_Base from Cache
 *
 * @param treeId id
 * @return MTree_Base
 */
fun getTree(id: Int): MTree_Base = id loadUsing treeFactory

/**
 * ************************************************************************ Get Node TableName
 *
 * @param treeType tree type
 * @return node table name, e.g. AD_TreeNode
 */
fun getNodeTableName(treeType: String): String {
    var nodeTableName = "AD_TreeNode"
    if (TREETYPE_Menu == treeType)
        nodeTableName += "MM"
    else if (TREETYPE_BPartner == treeType)
        nodeTableName += "BP"
    else if (TREETYPE_Product == treeType)
        nodeTableName += "PR"
    else if (TREETYPE_CMContainer == treeType)
        nodeTableName += "CMC"
    else if (TREETYPE_CMContainerStage == treeType)
        nodeTableName += "CMS"
    else if (TREETYPE_CMMedia == treeType)
        nodeTableName += "CMM"
    else if (TREETYPE_CMTemplate == treeType)
        nodeTableName += "CMT"
    else if (TREETYPE_User1 == treeType)
        nodeTableName += "U1"
    else if (TREETYPE_User2 == treeType)
        nodeTableName += "U2"
    else if (TREETYPE_User3 == treeType)
        nodeTableName += "U3"
    else if (TREETYPE_User4 == treeType) nodeTableName += "U4" //
    //
    return nodeTableName
} // 	getNodeTableName

/**
 * Get Source TableName
 *
 * @param treeType tree typw
 * @return source table name, e.g. AD_Org or null
 */
fun getSourceTableName(treeType: String?): String? {
    if (treeType == null) return null
    var sourceTable: String? = null
    when (treeType) {
        TREETYPE_Menu -> sourceTable = "AD_Menu"
        TREETYPE_Organization -> sourceTable = "AD_Org"
        TREETYPE_Product -> sourceTable = "M_Product"
        TREETYPE_ProductCategory -> sourceTable = "M_Product_Category"
        TREETYPE_BoM -> sourceTable = "M_BOM"
        TREETYPE_ElementValue -> sourceTable = "C_ElementValue"
        TREETYPE_BPartner -> sourceTable = "C_BPartner"
        TREETYPE_Campaign -> sourceTable = "C_Campaign"
        TREETYPE_Project -> sourceTable = "C_Project"
        TREETYPE_Activity -> sourceTable = "C_Activity"
        TREETYPE_SalesRegion -> sourceTable = "C_SalesRegion"
        //
        TREETYPE_CMContainer -> sourceTable = "CM_Container"
        TREETYPE_CMContainerStage -> sourceTable = "CM_CStage"
        TREETYPE_CMMedia -> sourceTable = "CM_Media"
        TREETYPE_CMTemplate -> sourceTable = "CM_Template"
        // 	User Trees
        // afalcone [Bugs #1837219]
        TREETYPE_User1, TREETYPE_User2, TREETYPE_User3, TREETYPE_User4 -> sourceTable = "C_ElementValue"
    }

    return sourceTable
} // 	getSourceTableName

/**
 * Base Tree Model. (see also MTree in project base)
 *
 * @author Jorg Janke
 * @version $Id: MTree_Base.java,v 1.2 2006/07/30 00:58:37 jjanke Exp $
 */
open class MTree_Base : X_AD_Tree {

    /**
     * Get Node TableName
     *
     * @return node table name, e.g. AD_TreeNode
     */
    val nodeTableName: String
        get() = getNodeTableName(treeType) // 	getNodeTableName

    /**
     * Get fully qualified Name of Action/Color Column
     *
     * @return NULL or Action or Color
     */
    val actionColorName: String
        get() {
            val tableName = getSourceTableName(treeType)
            if ("AD_Menu" == tableName) return "t.Action"
            return if ("M_Product" == tableName ||
                    "C_BPartner" == tableName ||
                    "AD_Org" == tableName ||
                    "C_Campaign" == tableName) "x.AD_PrintColor_ID" else "NULL"
        } // 	getSourceTableName

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param AD_Tree_ID id
     */
    constructor(AD_Tree_ID: Int) : super(AD_Tree_ID) {
        if (AD_Tree_ID == 0) {
            setIsAllNodes(true) // 	complete tree
            setIsDefault(false)
        }
    } // 	MTree_Base

    /**
     * Load Constructor
     */
    constructor(row: Row) : super(row) {} // 	MTree_Base

    /**
     * Parent Constructor
     *
     * @param client client
     * @param name name
     * @param treeType
     */
    constructor(client: MClient, name: String, treeType: String) : this(0) {
        setClientOrg(client)
        this.name = name
        setTreeType(treeType)
    } // 	MTree_Base

    /**
     * Full Constructor
     *
     * @param Name name
     * @param TreeType tree type
     */
    constructor(Name: String, TreeType: String) : super(0) {
        name = Name
        treeType = TreeType
        setIsAllNodes(true) // 	complete tree
        setIsDefault(false)
    } // 	MTree_Base

    /**
     * Get Source TableName (i.e. where to get the name and color)
     *
     * @param tableNameOnly if false return From clause (alias = t)
     * @return source table name, e.g. AD_Org or null
     */
    fun getSourceTableName(tableNameOnly: Boolean): String? {
        var tableName = getSourceTableName(treeType)
        if (tableName == null) {
            if (treeTableId > 0) tableName = getDbTableName(treeTableId)
        }
        if (tableNameOnly) return tableName
        if ("M_Product" == tableName)
            return "M_Product t INNER JOIN M_Product_Category x ON (t.M_Product_Category_ID=x.M_Product_Category_ID)"
        if ("C_BPartner" == tableName)
            return "C_BPartner t INNER JOIN C_BP_Group x ON (t.C_BP_Group_ID=x.C_BP_Group_ID)"
        if ("AD_Org" == tableName)
            return "AD_Org t INNER JOIN AD_OrgInfo i ON (t.AD_Org_ID=i.AD_Org_ID) " + "LEFT OUTER JOIN AD_OrgType x ON (i.AD_OrgType_ID=x.AD_OrgType_ID)"
        if ("C_Campaign" == tableName)
            return "C_Campaign t LEFT OUTER JOIN C_Channel x ON (t.C_Channel_ID=x.C_Channel_ID)"
        if (tableName != null) tableName += " t"
        return tableName
    } // 	getSourceTableName

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    override fun beforeSave(newRecord: Boolean): Boolean {
        if (!isActive() || !isAllNodes) setIsDefault(false)

        val tableName = getSourceTableName(true) ?: throw AdempiereSystemError("Table not found")
        val table = getTable(tableName)
        if (table.getDbColumnIndex("IsSummary") < 0) {
            // IsSummary is mandatory column to have a tree
            log.saveError("Error", "IsSummary column required for tree tables")
            return false
        }
        if (isTreeDrivenByValue) {
            if (table.getDbColumnIndex("Value") < 0) {
                // Value is mandatory column to have a tree driven by Value
                setIsTreeDrivenByValue(false)
            }
        }

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
        if (!success) return success
        if (newRecord)
        // 	Base Node
        {
            if (TREETYPE_BPartner == treeType) {
                val ndBP = MTree_NodeBP(this, 0)
                ndBP.saveEx()
            } else if (TREETYPE_Menu == treeType) {
                val ndMM = MTree_NodeMM(this, 0)
                ndMM.saveEx()
            } else if (TREETYPE_Product == treeType) {
                val ndPR = MTree_NodePR(this, 0)
                ndPR.saveEx()
            } else {
                val nd = MTree_Node(this, 0)
                nd.saveEx()
            }
        }

        return success
    } // 	afterSave

    companion object {
        private val serialVersionUID = -7657958239525901547L

        /**
         * Organization = OO
         */
        const val TREETYPE_Organization = "OO"
        /**
         * Menu = MM
         */
        const val TREETYPE_Menu = "MM"
        /**
         * Element Value = EV
         */
        const val TREETYPE_ElementValue = "EV"
        /**
         * Product = PR
         */
        const val TREETYPE_Product = "PR"
        /**
         * BPartner = BP
         */
        const val TREETYPE_BPartner = "BP"
        /**
         * BoM = BB
         */
        const val TREETYPE_BoM = "BB"
        /**
         * Project = PJ
         */
        const val TREETYPE_Project = "PJ"
        /**
         * Sales Region = SR
         */
        const val TREETYPE_SalesRegion = "SR"
        /**
         * Product Category = PC
         */
        const val TREETYPE_ProductCategory = "PC"
        /**
         * Campaign = MC
         */
        const val TREETYPE_Campaign = "MC"
        /**
         * Activity = AY
         */
        const val TREETYPE_Activity = "AY"
        /**
         * User 1 = U1
         */
        const val TREETYPE_User1 = "U1"
        /**
         * User 2 = U2
         */
        const val TREETYPE_User2 = "U2"
        /**
         * User 3 = U3
         */
        const val TREETYPE_User3 = "U3"
        /**
         * User 4 = U4
         */
        const val TREETYPE_User4 = "U4"
        /**
         * CM Container = CC
         */
        const val TREETYPE_CMContainer = "CC"
        /**
         * CM Container Stage = CS
         */
        const val TREETYPE_CMContainerStage = "CS"
        /**
         * CM Template = CT
         */
        const val TREETYPE_CMTemplate = "CT"
        /**
         * CM Media = CM
         */
        const val TREETYPE_CMMedia = "CM"
        /**
         * Custom Table = TL
         */
        const val TREETYPE_CustomTable = "TL"
    }
} // 	MTree_Base
