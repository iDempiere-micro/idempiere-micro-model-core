package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.TreeNodeModelMenu;

/**
 * Generated Model for AD_TreeNodeMM
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_TreeNodeMM extends PO implements TreeNodeModelMenu {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_TreeNodeMM(int AD_TreeNodeMM_ID) {
        super(AD_TreeNodeMM_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_TreeNodeMM(Row row) {
        super(row);
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
        return "X_AD_TreeNodeMM[" + getId() + "]";
    }

    /**
     * Set Tree.
     *
     * @param AD_Tree_ID Identifies a Tree
     */
    public void setTreeId(int AD_Tree_ID) {
        if (AD_Tree_ID < 1) setValueNoCheck(COLUMNNAME_AD_Tree_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Tree_ID, AD_Tree_ID);
    }

    /**
     * Set Node.
     *
     * @param Node_ID Node
     */
    public void setNodeId(int Node_ID) {
        if (Node_ID < 0) setValueNoCheck(COLUMNNAME_Node_ID, null);
        else setValueNoCheck(COLUMNNAME_Node_ID, Node_ID);
    }

    /**
     * Set Parent.
     *
     * @param Parent_ID Parent of Entity
     */
    public void setParentId(int Parent_ID) {
        if (Parent_ID < 1) setValue(COLUMNNAME_Parent_ID, null);
        else setValue(COLUMNNAME_Parent_ID, Parent_ID);
    }

    /**
     * Set Sequence.
     *
     * @param SeqNo Method of ordering records; lowest number comes first
     */
    public void setSeqNo(int SeqNo) {
        setValue(COLUMNNAME_SeqNo, SeqNo);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
