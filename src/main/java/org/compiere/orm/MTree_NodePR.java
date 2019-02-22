package org.compiere.orm;

import org.idempiere.common.util.CLogger;

import java.sql.ResultSet;
import java.util.Properties;

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
     * Static Logger
     */
    private static CLogger s_log = CLogger.getCLogger(MTree_NodePR.class);

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MTree_NodePR(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    } //	MTree_NodePR

    /**
     * Full Constructor
     *
     * @param tree    tree
     * @param Node_ID node
     */
    public MTree_NodePR(MTree_Base tree, int Node_ID) {
        super(tree.getCtx(), 0);
        setClientOrg(tree);
        setAD_Tree_ID(tree.getAD_Tree_ID());
        setNode_ID(Node_ID);
        //	Add to root
        setParent_ID(0);
        setSeqNo(0);
    } //	MTree_NodePR

} //	MTree_NodePR
