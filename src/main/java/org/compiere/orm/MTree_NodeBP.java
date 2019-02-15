package org.compiere.orm;

import org.idempiere.common.util.CLogger;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * (Disk) Tree Node Model BPartner
 *
 * @author Jorg Janke
 * @version $Id: MTree_NodeBP.java,v 1.3 2006/07/30 00:58:38 jjanke Exp $
 */
public class MTree_NodeBP extends X_AD_TreeNodeBP {
  /** */
  private static final long serialVersionUID = 5103486471442008006L;

    /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MTree_NodeBP(Properties ctx, ResultSet rs) {
    super(ctx, rs);
  } //	MTree_NodeBP

  /**
   * Full Constructor
   *
   * @param tree tree
   * @param Node_ID node
   */
  public MTree_NodeBP(MTree_Base tree, int Node_ID) {
    super(tree.getCtx(), 0);
    setClientOrg(tree);
    setAD_Tree_ID(tree.getAD_Tree_ID());
    setNode_ID(Node_ID);
    //	Add to root
    setParent_ID(0);
    setSeqNo(0);
  } //	MTree_NodeBP
} //	MTree_NodeBP
