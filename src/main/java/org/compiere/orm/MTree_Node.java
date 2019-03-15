package org.compiere.orm;

import kotliquery.Row;

import java.util.Properties;

/**
 * (Disk) Tree Node Model
 *
 * @author Jorg Janke
 * @version $Id: MTree_Node.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MTree_Node extends X_AD_TreeNode {
    /**
     *
     */
    private static final long serialVersionUID = 5473815124433234331L;

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MTree_Node(Properties ctx, Row row) {
        super(ctx, row);
    } //	MTree_Node

    /**
     * Full Constructor
     *
     * @param tree    tree
     * @param Node_ID node
     */
    public MTree_Node(MTree_Base tree, int Node_ID) {
        super(tree.getCtx(), 0);
        setClientOrg(tree);
        setTreeId(tree.getTreeId());
        setNodeId(Node_ID);
        //	Add to root
        setParentId(0);
        setSeqNo(0);
    } //	MTree_Node
} //	MTree_Node
