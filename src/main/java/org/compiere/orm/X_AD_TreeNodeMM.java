package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_TreeNodeMM;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_TreeNodeMM
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_TreeNodeMM extends PO implements I_AD_TreeNodeMM, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_TreeNodeMM(Properties ctx, int AD_TreeNodeMM_ID) {
    super(ctx, AD_TreeNodeMM_ID);
    /** if (AD_TreeNodeMM_ID == 0) { setAD_Tree_ID (0); setNode_ID (0); } */
  }

  /** Load Constructor */
  public X_AD_TreeNodeMM(Properties ctx, ResultSet rs) {
    super(ctx, rs);
  }

  /**
   * AccessLevel
   *
   * @return 7 - System - Client - Org
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("X_AD_TreeNodeMM[").append(getId()).append("]");
    return sb.toString();
  }

    /**
   * Get Tree.
   *
   * @return Identifies a Tree
   */
  public int getAD_Tree_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Tree_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Tree.
   *
   * @param AD_Tree_ID Identifies a Tree
   */
  public void setAD_Tree_ID(int AD_Tree_ID) {
    if (AD_Tree_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Tree_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Tree_ID, Integer.valueOf(AD_Tree_ID));
  }

    /**
   * Set Node.
   *
   * @param Node_ID Node
   */
  public void setNode_ID(int Node_ID) {
    if (Node_ID < 0) set_ValueNoCheck(COLUMNNAME_Node_ID, null);
    else set_ValueNoCheck(COLUMNNAME_Node_ID, Integer.valueOf(Node_ID));
  }

    /**
   * Set Parent.
   *
   * @param Parent_ID Parent of Entity
   */
  public void setParent_ID(int Parent_ID) {
    if (Parent_ID < 1) set_Value(COLUMNNAME_Parent_ID, null);
    else set_Value(COLUMNNAME_Parent_ID, Integer.valueOf(Parent_ID));
  }

    /**
   * Set Sequence.
   *
   * @param SeqNo Method of ordering records; lowest number comes first
   */
  public void setSeqNo(int SeqNo) {
    set_Value(COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
