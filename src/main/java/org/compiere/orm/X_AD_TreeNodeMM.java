package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_TreeNodeMM;

import java.util.Properties;

/**
 * Generated Model for AD_TreeNodeMM
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_TreeNodeMM extends PO implements I_AD_TreeNodeMM {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_TreeNodeMM(Properties ctx, int AD_TreeNodeMM_ID) {
        super(ctx, AD_TreeNodeMM_ID);
        /** if (AD_TreeNodeMM_ID == 0) { setTreeId (0); setNodeId (0); } */
    }

    /**
     * Load Constructor
     */
    public X_AD_TreeNodeMM(Properties ctx, Row row) {
        super(ctx, row);
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
     * Set Tree.
     *
     * @param AD_Tree_ID Identifies a Tree
     */
    public void setTreeId(int AD_Tree_ID) {
        if (AD_Tree_ID < 1) setValueNoCheck(COLUMNNAME_AD_Tree_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Tree_ID, Integer.valueOf(AD_Tree_ID));
    }

    /**
     * Set Node.
     *
     * @param Node_ID Node
     */
    public void setNodeId(int Node_ID) {
        if (Node_ID < 0) setValueNoCheck(COLUMNNAME_Node_ID, null);
        else setValueNoCheck(COLUMNNAME_Node_ID, Integer.valueOf(Node_ID));
    }

    /**
     * Set Parent.
     *
     * @param Parent_ID Parent of Entity
     */
    public void setParentId(int Parent_ID) {
        if (Parent_ID < 1) setValue(COLUMNNAME_Parent_ID, null);
        else setValue(COLUMNNAME_Parent_ID, Integer.valueOf(Parent_ID));
    }

    /**
     * Set Sequence.
     *
     * @param SeqNo Method of ordering records; lowest number comes first
     */
    public void setSeqNo(int SeqNo) {
        setValue(COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
