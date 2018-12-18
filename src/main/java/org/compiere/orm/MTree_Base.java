package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Tree;
import org.idempiere.common.util.CCache;

import java.sql.ResultSet;
import java.util.Properties;

import static software.hsharp.core.util.DBKt.getSQLValueStringEx;

/**
 * Base Tree Model. (see also MTree in project base)
 *
 * @author Jorg Janke
 * @version $Id: MTree_Base.java,v 1.2 2006/07/30 00:58:37 jjanke Exp $
 */
public class MTree_Base extends X_AD_Tree {

  /** */
  private static final long serialVersionUID = -7657958239525901547L;
  /** Cache */
  private static CCache<Integer, MTree_Base> s_cache =
      new CCache<Integer, MTree_Base>(I_AD_Tree.Table_Name, 10);

  /**
   * ************************************************************************ Standard Constructor
   *
   * @param ctx context
   * @param AD_Tree_ID id
   * @param trxName transaction
   */
  public MTree_Base(Properties ctx, int AD_Tree_ID, String trxName) {
    super(ctx, AD_Tree_ID, trxName);
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
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MTree_Base(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } //	MTree_Base

  public MTree_Base(Properties ctx, Row row) {
    super(ctx, row);
  } //	MTree_Base

  /**
   * Parent Constructor
   *
   * @param client client
   * @param name name
   * @param treeType
   */
  public MTree_Base(MClient client, String name, String treeType) {
    this(client.getCtx(), 0, null);
    setClientOrg(client);
    setName(name);
    setTreeType(treeType);
  } //	MTree_Base

  /**
   * Full Constructor
   *
   * @param ctx context
   * @param Name name
   * @param TreeType tree type
   * @param trxName transaction
   */
  public MTree_Base(Properties ctx, String Name, String TreeType, String trxName) {
    super(ctx, 0, trxName);
    setName(Name);
    setTreeType(TreeType);
    setIsAllNodes(true); // 	complete tree
    setIsDefault(false);
  } //	MTree_Base

  /**
   * Add Node to correct tree
   *
   * @param ctx cpntext
   * @param treeType tree type
   * @param Record_ID id
   * @param trxName transaction
   * @return true if node added
   */
  public static boolean addNode(Properties ctx, String treeType, int Record_ID, String trxName) {
    //	Get Tree
    int AD_Tree_ID = 0;
    MClient client = MClient.get(ctx);
    MClientInfo ci = client.getInfo();

    if (X_AD_Tree.TREETYPE_Activity.equals(treeType)) AD_Tree_ID = ci.getAD_Tree_Activity_ID();
    else if (X_AD_Tree.TREETYPE_BoM.equals(treeType))
      throw new IllegalArgumentException("BoM Trees not supported");
    else if (X_AD_Tree.TREETYPE_BPartner.equals(treeType)) AD_Tree_ID = ci.getAD_Tree_BPartner_ID();
    else if (X_AD_Tree.TREETYPE_Campaign.equals(treeType)) AD_Tree_ID = ci.getAD_Tree_Campaign_ID();
    else if (X_AD_Tree.TREETYPE_ElementValue.equals(treeType))
      throw new IllegalArgumentException("ElementValue cannot use this API");
    else if (X_AD_Tree.TREETYPE_Menu.equals(treeType)) AD_Tree_ID = ci.getAD_Tree_Menu_ID();
    else if (X_AD_Tree.TREETYPE_Organization.equals(treeType)) AD_Tree_ID = ci.getAD_Tree_Org_ID();
    else if (X_AD_Tree.TREETYPE_Product.equals(treeType)) AD_Tree_ID = ci.getAD_Tree_Product_ID();
    else if (X_AD_Tree.TREETYPE_ProductCategory.equals(treeType))
      throw new IllegalArgumentException("Product Category Trees not supported");
    else if (X_AD_Tree.TREETYPE_Project.equals(treeType)) AD_Tree_ID = ci.getAD_Tree_Project_ID();
    else if (X_AD_Tree.TREETYPE_SalesRegion.equals(treeType))
      AD_Tree_ID = ci.getAD_Tree_SalesRegion_ID();

    if (AD_Tree_ID == 0) throw new IllegalArgumentException("No Tree found");
    MTree_Base tree = MTree_Base.get(ctx, AD_Tree_ID, trxName);
    if (tree.getId() != AD_Tree_ID)
      throw new IllegalArgumentException("Tree found AD_Tree_ID=" + AD_Tree_ID);

    //	Insert Tree in correct tree
    boolean saved = false;
    if (X_AD_Tree.TREETYPE_Menu.equals(treeType)) {
      MTree_NodeMM node = new MTree_NodeMM(tree, Record_ID);
      saved = node.save();
    } else if (X_AD_Tree.TREETYPE_BPartner.equals(treeType)) {
      MTree_NodeBP node = new MTree_NodeBP(tree, Record_ID);
      saved = node.save();
    } else if (X_AD_Tree.TREETYPE_Product.equals(treeType)) {
      MTree_NodePR node = new MTree_NodePR(tree, Record_ID);
      saved = node.save();
    } else {
      MTree_Node node = new MTree_Node(tree, Record_ID);
      saved = node.save();
    }
    return saved;
  } //	addNode

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
   * @param ctx context
   * @param AD_Tree_ID id
   * @param trxName transaction
   * @return MTree_Base
   */
  public static MTree_Base get(Properties ctx, int AD_Tree_ID, String trxName) {
    Integer key = new Integer(AD_Tree_ID);
    MTree_Base retValue = s_cache.get(key);
    if (retValue != null) return retValue;
    retValue = new MTree_Base(ctx, AD_Tree_ID, trxName);
    if (retValue.getId() != 0) s_cache.put(key, retValue);
    return retValue;
  } //	get

  /** Returns true if should load all tree nodes immediately */
  public static boolean isLoadAllNodesImmediately(int treeID, String trxName) {
    return getSQLValueStringEx(
            trxName, "SELECT IsLoadAllNodesImmediately FROM AD_Tree WHERE AD_Tree_ID = ?", treeID)
        .equals("Y");
  }

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
   * @param success success
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
