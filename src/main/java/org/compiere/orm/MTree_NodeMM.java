package org.compiere.orm;

import kotliquery.Row;

/**
 * (Disk) Tree Node Model Menu
 *
 * @author Jorg Janke
 * @version $Id: MTree_NodeMM.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MTree_NodeMM extends X_AD_TreeNodeMM {
    /**
     *
     */
    private static final long serialVersionUID = 3786314890360604549L;

    /**
     * Load Constructor
     *
     */
    public MTree_NodeMM(Row row) {
        super(row);
    } //	MTree_NodeMM

    /**
     * Full Constructor
     *
     * @param tree    tree
     * @param Node_ID node
     */
    public MTree_NodeMM(MTree_Base tree, int Node_ID) {
        super(0);
        setClientOrg(tree);
        setTreeId(tree.getTreeId());
        setNodeId(Node_ID);
        //	Add to root
        setParentId(0);
        setSeqNo(0);
    } //	MTree_NodeMM
} //	MTree_NodeMM
