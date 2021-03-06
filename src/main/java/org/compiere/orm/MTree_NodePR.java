package org.compiere.orm;

import kotliquery.Row;

/**
 * (Disk) Tree Node Model Product
 *
 * @author Jorg Janke
 * @version $Id: MTree_NodePR.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MTree_NodePR extends X_AD_TreeNodePR {
    /**
     *
     */
    private static final long serialVersionUID = 7949499593365182416L;

    /**
     * Load Constructor
     *
     */
    public MTree_NodePR(Row row) {
        super(row);
    } //	MTree_NodePR

    /**
     * Full Constructor
     *
     * @param tree    tree
     * @param Node_ID node
     */
    public MTree_NodePR(MTree_Base tree, int Node_ID) {
        super(0);
        setClientOrg(tree);
        setTreeId(tree.getTreeId());
        setNodeId(Node_ID);
        //	Add to root
        setParentId(0);
        setSeqNo(0);
    } //	MTree_NodePR

} //	MTree_NodePR
