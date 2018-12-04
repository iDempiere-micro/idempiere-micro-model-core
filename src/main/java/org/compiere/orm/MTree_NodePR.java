package org.compiere.orm;

import static software.hsharp.core.util.DBKt.close;
import static software.hsharp.core.util.DBKt.prepareStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.idempiere.common.util.CLogger;

/**
 * (Disk) Tree Node Model Product
 *
 * @author Jorg Janke
 * @version $Id: MTree_NodePR.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MTree_NodePR extends X_AD_TreeNodePR {
  /** */
  private static final long serialVersionUID = 7949499593365182416L;

  /**
   * Get Tree Node
   *
   * @param tree tree
   * @param Node_ID node
   * @return node or null
   */
  public static MTree_NodePR get(MTree_Base tree, int Node_ID) {
    MTree_NodePR retValue = null;
    String sql = "SELECT * FROM AD_TreeNodePR WHERE AD_Tree_ID=? AND Node_ID=?";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = prepareStatement(sql, tree.get_TrxName());
      pstmt.setInt(1, tree.getAD_Tree_ID());
      pstmt.setInt(2, Node_ID);
      rs = pstmt.executeQuery();
      if (rs.next()) retValue = new MTree_NodePR(tree.getCtx(), rs, tree.get_TrxName());
    } catch (Exception e) {
      s_log.log(Level.SEVERE, sql, e);
    } finally {
      close(rs, pstmt);
      rs = null;
      pstmt = null;
    }
    return retValue;
  } //	get

  /** Static Logger */
  private static CLogger s_log = CLogger.getCLogger(MTree_NodePR.class);

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MTree_NodePR(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } //	MTree_NodePR

  /**
   * Full Constructor
   *
   * @param tree tree
   * @param Node_ID node
   */
  public MTree_NodePR(MTree_Base tree, int Node_ID) {
    super(tree.getCtx(), 0, tree.get_TrxName());
    setClientOrg(tree);
    setAD_Tree_ID(tree.getAD_Tree_ID());
    setNode_ID(Node_ID);
    //	Add to root
    setParent_ID(0);
    setSeqNo(0);
  } //	MTree_NodePR
} //	MTree_NodePR
