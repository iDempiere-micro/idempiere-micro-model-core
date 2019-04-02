package org.compiere.orm;

import kotliquery.Row;

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
     * @param ctx context
     */
    public MTree_Node(Row row) {
        super(row);
    } //	MTree_Node

    /**
     * Full Constructor
     *
     * @param tree    tree
     * @param Node_ID node
     */
    public MTree_Node(MTree_Base tree, int Node_ID) {
        super(0);
        setClientOrg(tree);
        setTreeId(tree.getTreeId());
        setNodeId(Node_ID);
        //	Add to root
        setParentId(0);
        setSeqNo(0);
    } //	MTree_Node
} //	MTree_Node
