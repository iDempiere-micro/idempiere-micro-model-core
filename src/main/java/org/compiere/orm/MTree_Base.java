package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Tree;
import org.idempiere.common.util.CCache;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Base Tree Model. (see also MTree in project base)
 *
 * @author Jorg Janke
 * @version $Id: MTree_Base.java,v 1.2 2006/07/30 00:58:37 jjanke Exp $
 */
public class MTree_Base extends X_AD_Tree {

    /**
     *
     */
    private static final long serialVersionUID = -7657958239525901547L;
    /**
     * Cache
     */
    private static CCache<Integer, MTree_Base> s_cache =
            new CCache<Integer, MTree_Base>(I_AD_Tree.Table_Name, 10);

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ctx        context
     * @param AD_Tree_ID id
     * @param trxName    transaction
     */
    public MTree_Base(Properties ctx, int AD_Tree_ID) {
        super(ctx, AD_Tree_ID);
        if (AD_Tree_ID == 0) {
            //	setName (null);
            //	setTreeType (null);
            setIsAllNodes(true); // 	complete tree
            setIsDefault(false);
        }
    } //	MTree_Base

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MTree_Base(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    } //	MTree_Base

    public MTree_Base(Properties ctx, Row row) {
        super(ctx, row);
    } //	MTree_Base

    /**
     * Parent Constructor
     *
     * @param client   client
     * @param name     name
     * @param treeType
     */
    public MTree_Base(MClient client, String name, String treeType) {
        this(client.getCtx(), 0);
        setClientOrg(client);
        setName(name);
        setTreeType(treeType);
    } //	MTree_Base

    /**
     * Full Constructor
     *
     * @param ctx      context
     * @param Name     name
     * @param TreeType tree type
     * @param trxName  transaction
     */
    public MTree_Base(Properties ctx, String Name, String TreeType) {
        super(ctx, 0);
        setName(Name);
        setTreeType(TreeType);
        setIsAllNodes(true); // 	complete tree
        setIsDefault(false);
    } //	MTree_Base

    /**
     * ************************************************************************ Get Node TableName
     *
     * @param treeType tree type
     * @return node table name, e.g. AD_TreeNode
     */
    public static String getNodeTableName(String treeType) {
        String nodeTableName = "AD_TreeNode";
        if (X_AD_Tree.TREETYPE_Menu.equals(treeType)) nodeTableName += "MM";
        else if (X_AD_Tree.TREETYPE_BPartner.equals(treeType)) nodeTableName += "BP";
        else if (X_AD_Tree.TREETYPE_Product.equals(treeType)) nodeTableName += "PR";
            //
        else if (X_AD_Tree.TREETYPE_CMContainer.equals(treeType)) nodeTableName += "CMC";
        else if (X_AD_Tree.TREETYPE_CMContainerStage.equals(treeType)) nodeTableName += "CMS";
        else if (X_AD_Tree.TREETYPE_CMMedia.equals(treeType)) nodeTableName += "CMM";
        else if (X_AD_Tree.TREETYPE_CMTemplate.equals(treeType)) nodeTableName += "CMT";
            //
        else if (X_AD_Tree.TREETYPE_User1.equals(treeType)) nodeTableName += "U1";
        else if (X_AD_Tree.TREETYPE_User2.equals(treeType)) nodeTableName += "U2";
        else if (X_AD_Tree.TREETYPE_User3.equals(treeType)) nodeTableName += "U3";
        else if (X_AD_Tree.TREETYPE_User4.equals(treeType)) nodeTableName += "U4";
        return nodeTableName;
    } //	getNodeTableName

    /**
     * Get Source TableName
     *
     * @param treeType tree typw
     * @return source table name, e.g. AD_Org or null
     */
    public static String getSourceTableName(String treeType) {
        if (treeType == null) return null;
        String sourceTable = null;
        if (treeType.equals(X_AD_Tree.TREETYPE_Menu)) sourceTable = "AD_Menu";
        else if (treeType.equals(X_AD_Tree.TREETYPE_Organization)) sourceTable = "AD_Org";
        else if (treeType.equals(X_AD_Tree.TREETYPE_Product)) sourceTable = "M_Product";
        else if (treeType.equals(X_AD_Tree.TREETYPE_ProductCategory))
            sourceTable = "M_Product_Category";
        else if (treeType.equals(X_AD_Tree.TREETYPE_BoM)) sourceTable = "M_BOM";
        else if (treeType.equals(X_AD_Tree.TREETYPE_ElementValue)) sourceTable = "C_ElementValue";
        else if (treeType.equals(X_AD_Tree.TREETYPE_BPartner)) sourceTable = "C_BPartner";
        else if (treeType.equals(X_AD_Tree.TREETYPE_Campaign)) sourceTable = "C_Campaign";
        else if (treeType.equals(X_AD_Tree.TREETYPE_Project)) sourceTable = "C_Project";
        else if (treeType.equals(X_AD_Tree.TREETYPE_Activity)) sourceTable = "C_Activity";
        else if (treeType.equals(X_AD_Tree.TREETYPE_SalesRegion)) sourceTable = "C_SalesRegion";
            //
        else if (treeType.equals(X_AD_Tree.TREETYPE_CMContainer)) sourceTable = "CM_Container";
        else if (treeType.equals(X_AD_Tree.TREETYPE_CMContainerStage)) sourceTable = "CM_CStage";
        else if (treeType.equals(X_AD_Tree.TREETYPE_CMMedia)) sourceTable = "CM_Media";
        else if (treeType.equals(X_AD_Tree.TREETYPE_CMTemplate)) sourceTable = "CM_Template";
            //	User Trees
            // afalcone [Bugs #1837219]
        else if (treeType.equals(X_AD_Tree.TREETYPE_User1)
                || treeType.equals(X_AD_Tree.TREETYPE_User2)
                || treeType.equals(X_AD_Tree.TREETYPE_User3)
                || treeType.equals(X_AD_Tree.TREETYPE_User4)) sourceTable = "C_ElementValue";

        return sourceTable;
    } //	getSourceTableName

    /**
     * Get MTree_Base from Cache
     *
     * @param ctx        context
     * @param AD_Tree_ID id
     * @param trxName    transaction
     * @return MTree_Base
     */
    public static MTree_Base get(Properties ctx, int AD_Tree_ID) {
        Integer key = new Integer(AD_Tree_ID);
        MTree_Base retValue = s_cache.get(key);
        if (retValue != null) return retValue;
        retValue = new MTree_Base(ctx, AD_Tree_ID);
        if (retValue.getId() != 0) s_cache.put(key, retValue);
        return retValue;
    } //	get

    /**
     * Get Node TableName
     *
     * @return node table name, e.g. AD_TreeNode
     */
    public String getNodeTableName() {
        return getNodeTableName(getTreeType());
    } //	getNodeTableName

    /**
     * Get Source TableName (i.e. where to get the name and color)
     *
     * @param tableNameOnly if false return From clause (alias = t)
     * @return source table name, e.g. AD_Org or null
     */
    public String getSourceTableName(boolean tableNameOnly) {
        String tableName = getSourceTableName(getTreeType());
        if (tableName == null) {
            if (getAD_Table_ID() > 0) tableName = MTable.getTableName(getCtx(), getAD_Table_ID());
        }
        if (tableNameOnly) return tableName;
        if ("M_Product".equals(tableName))
            return "M_Product t INNER JOIN M_Product_Category x ON (t.M_Product_Category_ID=x.M_Product_Category_ID)";
        if ("C_BPartner".equals(tableName))
            return "C_BPartner t INNER JOIN C_BP_Group x ON (t.C_BP_Group_ID=x.C_BP_Group_ID)";
        if ("AD_Org".equals(tableName))
            return "AD_Org t INNER JOIN AD_OrgInfo i ON (t.AD_Org_ID=i.AD_Org_ID) "
                    + "LEFT OUTER JOIN AD_OrgType x ON (i.AD_OrgType_ID=x.AD_OrgType_ID)";
        if ("C_Campaign".equals(tableName))
            return "C_Campaign t LEFT OUTER JOIN C_Channel x ON (t.C_Channel_ID=x.C_Channel_ID)";
        if (tableName != null) tableName += " t";
        return tableName;
    } //	getSourceTableName

    /**
     * Get fully qualified Name of Action/Color Column
     *
     * @return NULL or Action or Color
     */
    public String getActionColorName() {
        String tableName = getSourceTableName(getTreeType());
        if ("AD_Menu".equals(tableName)) return "t.Action";
        if ("M_Product".equals(tableName)
                || "C_BPartner".equals(tableName)
                || "AD_Org".equals(tableName)
                || "C_Campaign".equals(tableName)) return "x.AD_PrintColor_ID";
        return "NULL";
    } //	getSourceTableName

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    protected boolean beforeSave(boolean newRecord) {
        if (!isActive() || !isAllNodes()) setIsDefault(false);

        String tableName = getSourceTableName(true);
        MTable table = MTable.get(getCtx(), tableName);
        if (table.getColumnIndex("IsSummary") < 0) {
            // IsSummary is mandatory column to have a tree
            log.saveError("Error", "IsSummary column required for tree tables");
            return false;
        }
        if (isTreeDrivenByValue()) {
            if (table.getColumnIndex("Value") < 0) {
                // Value is mandatory column to have a tree driven by Value
                setIsTreeDrivenByValue(false);
            }
        }

        return true;
    } //	beforeSave

    /**
     * After Save
     *
     * @param newRecord new
     * @param success   success
     * @return success
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        if (!success) return success;
        if (newRecord) //	Base Node
        {
            if (X_AD_Tree.TREETYPE_BPartner.equals(getTreeType())) {
                MTree_NodeBP ndBP = new MTree_NodeBP(this, 0);
                ndBP.saveEx();
            } else if (X_AD_Tree.TREETYPE_Menu.equals(getTreeType())) {
                MTree_NodeMM ndMM = new MTree_NodeMM(this, 0);
                ndMM.saveEx();
            } else if (X_AD_Tree.TREETYPE_Product.equals(getTreeType())) {
                MTree_NodePR ndPR = new MTree_NodePR(this, 0);
                ndPR.saveEx();
            } else {
                MTree_Node nd = new MTree_Node(this, 0);
                nd.saveEx();
            }
        }

        return success;
    } //	afterSave
} //	MTree_Base
