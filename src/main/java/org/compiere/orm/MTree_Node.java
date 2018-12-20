package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.idempiere.common.util.CLogger;

/**
 * (Disk) Tree Node Model
 *
 * @author Jorg Janke
 * @version $Id: MTree_Node.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MTree_Node extends X_AD_TreeNode {
  /** */
  private static final long serialVersionUID = 5473815124433234331L;

  /** Static Logger */
  private static CLogger s_log = CLogger.getCLogger(MTree_Node.class);

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MTree_Node(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } //	MTree_Node

  /**
   * Full Constructor
   *
   * @param tree tree
   * @param Node_ID node
   */
  public MTree_Node(MTree_Base tree, int Node_ID) {
    super(tree.getCtx(), 0, null);
    setClientOrg(tree);
    setAD_Tree_ID(tree.getAD_Tree_ID());
    setNode_ID(Node_ID);
    //	Add to root
    setParent_ID(0);
    setSeqNo(0);
  } //	MTree_Node
} //	MTree_Node
