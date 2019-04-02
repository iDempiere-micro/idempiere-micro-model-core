package org.compiere.orm;

import kotliquery.Row;

/**
 * (Disk) Tree Node Model BPartner
 *
 * @author Jorg Janke
 * @version $Id: MTree_NodeBP.java,v 1.3 2006/07/30 00:58:38 jjanke Exp $
 */
public class MTree_NodeBP extends X_AD_TreeNodeBP {
    /**
     *
     */
    private static final long serialVersionUID = 5103486471442008006L;

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MTree_NodeBP(Row row) {
        super(row);
    } //	MTree_NodeBP

    /**
     * Full Constructor
     *
     * @param tree    tree
     * @param Node_ID node
     */
    public MTree_NodeBP(MTree_Base tree, int Node_ID) {
        super(0);
        setClientOrg(tree);
        setTreeId(tree.getTreeId());
        setNodeId(Node_ID);
        //	Add to root
        setParentId(0);
        setSeqNo(0);
    } //	MTree_NodeBP
} //	MTree_NodeBP
