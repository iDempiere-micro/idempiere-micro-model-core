package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.Tree;

/**
 * Generated Model for AD_Tree
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Tree extends BasePOName implements Tree {
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Tree(int AD_Tree_ID) {
        super(AD_Tree_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Tree(Row row) {
        super(row);
    }

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_AD_Tree[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Table.
     *
     * @return Database Table information
     */
    public int getTreeTableId() {
        Integer ii = getValue(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Tree.
     *
     * @return Identifies a Tree
     */
    public int getTreeId() {
        Integer ii = getValue(COLUMNNAME_AD_Tree_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return (String) getValue(COLUMNNAME_Description);
    }

    /**
     * Set All Nodes.
     *
     * @param IsAllNodes All Nodes are included (Complete Tree)
     */
    public void setIsAllNodes(boolean IsAllNodes) {
        setValue(COLUMNNAME_IsAllNodes, Boolean.valueOf(IsAllNodes));
    }

    /**
     * Get All Nodes.
     *
     * @return All Nodes are included (Complete Tree)
     */
    public boolean isAllNodes() {
        Object oo = getValue(COLUMNNAME_IsAllNodes);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Default.
     *
     * @param IsDefault Default value
     */
    public void setIsDefault(boolean IsDefault) {
        setValue(COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
    }

    /**
     * Set Driven by Search Key.
     *
     * @param IsTreeDrivenByValue Driven by Search Key
     */
    public void setIsTreeDrivenByValue(boolean IsTreeDrivenByValue) {
        setValue(COLUMNNAME_IsTreeDrivenByValue, Boolean.valueOf(IsTreeDrivenByValue));
    }

    /**
     * Get Driven by Search Key.
     *
     * @return Driven by Search Key
     */
    public boolean isTreeDrivenByValue() {
        Object oo = getValue(COLUMNNAME_IsTreeDrivenByValue);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Type | Area.
     *
     * @return Element this tree is built on (i.e Product, Business Partner)
     */
    public String getTreeType() {
        return (String) getValue(COLUMNNAME_TreeType);
    }

    /**
     * Set Type | Area.
     *
     * @param TreeType Element this tree is built on (i.e Product, Business Partner)
     */
    public void setTreeType(String TreeType) {

        setValueNoCheck(COLUMNNAME_TreeType, TreeType);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
