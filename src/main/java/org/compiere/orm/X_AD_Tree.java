package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Tree;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Tree
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Tree extends BasePOName implements I_AD_Tree, I_Persistent {

    /**
     * Menu = MM
     */
    public static final String TREETYPE_Menu = "MM";
    /**
     * Element Value = EV
     */
    public static final String TREETYPE_ElementValue = "EV";
    /**
     * Product = PR
     */
    public static final String TREETYPE_Product = "PR";
    /**
     * BPartner = BP
     */
    public static final String TREETYPE_BPartner = "BP";
    /**
     * Organization = OO
     */
    public static final String TREETYPE_Organization = "OO";
    /**
     * BoM = BB
     */
    public static final String TREETYPE_BoM = "BB";
    /**
     * Project = PJ
     */
    public static final String TREETYPE_Project = "PJ";
    /**
     * Sales Region = SR
     */
    public static final String TREETYPE_SalesRegion = "SR";
    /**
     * Product Category = PC
     */
    public static final String TREETYPE_ProductCategory = "PC";
    /**
     * Campaign = MC
     */
    public static final String TREETYPE_Campaign = "MC";
    /**
     * Activity = AY
     */
    public static final String TREETYPE_Activity = "AY";
    /**
     * User 1 = U1
     */
    public static final String TREETYPE_User1 = "U1";
    /**
     * User 2 = U2
     */
    public static final String TREETYPE_User2 = "U2";
    /**
     * User 3 = U3
     */
    public static final String TREETYPE_User3 = "U3";
    /**
     * User 4 = U4
     */
    public static final String TREETYPE_User4 = "U4";
    /**
     * CM Container = CC
     */
    public static final String TREETYPE_CMContainer = "CC";
    /**
     * CM Container Stage = CS
     */
    public static final String TREETYPE_CMContainerStage = "CS";
    /**
     * CM Template = CT
     */
    public static final String TREETYPE_CMTemplate = "CT";
    /**
     * CM Media = CM
     */
    public static final String TREETYPE_CMMedia = "CM";
    /**
     * Custom Table = TL
     */
    public static final String TREETYPE_CustomTable = "TL";
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Tree(Properties ctx, int AD_Tree_ID) {
        super(ctx, AD_Tree_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Tree(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_AD_Tree(Properties ctx, Row row) {
        super(ctx, row);
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
    public int getAD_Table_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_Table_ID);
        if (ii == null) return 0;
        return ii;
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
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return (String) get_Value(COLUMNNAME_Description);
    }

    /**
     * Set All Nodes.
     *
     * @param IsAllNodes All Nodes are included (Complete Tree)
     */
    public void setIsAllNodes(boolean IsAllNodes) {
        set_Value(COLUMNNAME_IsAllNodes, Boolean.valueOf(IsAllNodes));
    }

    /**
     * Get All Nodes.
     *
     * @return All Nodes are included (Complete Tree)
     */
    public boolean isAllNodes() {
        Object oo = get_Value(COLUMNNAME_IsAllNodes);
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
        set_Value(COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
    }

    /**
     * Set Driven by Search Key.
     *
     * @param IsTreeDrivenByValue Driven by Search Key
     */
    public void setIsTreeDrivenByValue(boolean IsTreeDrivenByValue) {
        set_Value(COLUMNNAME_IsTreeDrivenByValue, Boolean.valueOf(IsTreeDrivenByValue));
    }

    /**
     * Get Driven by Search Key.
     *
     * @return Driven by Search Key
     */
    public boolean isTreeDrivenByValue() {
        Object oo = get_Value(COLUMNNAME_IsTreeDrivenByValue);
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
        return (String) get_Value(COLUMNNAME_TreeType);
    }

    /**
     * Set Type | Area.
     *
     * @param TreeType Element this tree is built on (i.e Product, Business Partner)
     */
    public void setTreeType(String TreeType) {

        set_ValueNoCheck(COLUMNNAME_TreeType, TreeType);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
