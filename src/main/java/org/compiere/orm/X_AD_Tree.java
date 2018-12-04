package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_AD_Tree;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Tree
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Tree extends BasePOName implements I_AD_Tree, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Tree(Properties ctx, int AD_Tree_ID, String trxName) {
    super(ctx, AD_Tree_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_Tree(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
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

  public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException {
    return (org.compiere.model.I_AD_Table)
        MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
            .getPO(getAD_Table_ID(), get_TrxName());
  }

  /**
   * Set Table.
   *
   * @param AD_Table_ID Database Table information
   */
  public void setAD_Table_ID(int AD_Table_ID) {
    if (AD_Table_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Table_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
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
   * Set Tree.
   *
   * @param AD_Tree_ID Identifies a Tree
   */
  public void setAD_Tree_ID(int AD_Tree_ID) {
    if (AD_Tree_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Tree_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Tree_ID, Integer.valueOf(AD_Tree_ID));
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
   * Set AD_Tree_UU.
   *
   * @param AD_Tree_UU AD_Tree_UU
   */
  public void setAD_Tree_UU(String AD_Tree_UU) {
    set_Value(COLUMNNAME_AD_Tree_UU, AD_Tree_UU);
  }

  /**
   * Get AD_Tree_UU.
   *
   * @return AD_Tree_UU
   */
  public String getAD_Tree_UU() {
    return (String) get_Value(COLUMNNAME_AD_Tree_UU);
  }

  /**
   * Set Description.
   *
   * @param Description Optional short description of the record
   */
  public void setDescription(String Description) {
    set_Value(COLUMNNAME_Description, Description);
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
   * Get Default.
   *
   * @return Default value
   */
  public boolean isDefault() {
    Object oo = get_Value(COLUMNNAME_IsDefault);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Loads directly all nodes.
   *
   * @param IsLoadAllNodesImmediately If checked, all nodes are loaded before tree is displayed
   */
  public void setIsLoadAllNodesImmediately(boolean IsLoadAllNodesImmediately) {
    set_Value(COLUMNNAME_IsLoadAllNodesImmediately, Boolean.valueOf(IsLoadAllNodesImmediately));
  }

  /**
   * Get Loads directly all nodes.
   *
   * @return If checked, all nodes are loaded before tree is displayed
   */
  public boolean isLoadAllNodesImmediately() {
    Object oo = get_Value(COLUMNNAME_IsLoadAllNodesImmediately);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Set Process Now.
   *
   * @param Processing Process Now
   */
  public void setProcessing(boolean Processing) {
    set_Value(COLUMNNAME_Processing, Boolean.valueOf(Processing));
  }

  /**
   * Get Process Now.
   *
   * @return Process Now
   */
  public boolean isProcessing() {
    Object oo = get_Value(COLUMNNAME_Processing);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /** TreeType AD_Reference_ID=120 */
  public static final int TREETYPE_AD_Reference_ID = 120;
  /** Menu = MM */
  public static final String TREETYPE_Menu = "MM";
  /** Element Value = EV */
  public static final String TREETYPE_ElementValue = "EV";
  /** Product = PR */
  public static final String TREETYPE_Product = "PR";
  /** BPartner = BP */
  public static final String TREETYPE_BPartner = "BP";
  /** Organization = OO */
  public static final String TREETYPE_Organization = "OO";
  /** BoM = BB */
  public static final String TREETYPE_BoM = "BB";
  /** Project = PJ */
  public static final String TREETYPE_Project = "PJ";
  /** Sales Region = SR */
  public static final String TREETYPE_SalesRegion = "SR";
  /** Product Category = PC */
  public static final String TREETYPE_ProductCategory = "PC";
  /** Campaign = MC */
  public static final String TREETYPE_Campaign = "MC";
  /** Activity = AY */
  public static final String TREETYPE_Activity = "AY";
  /** User 1 = U1 */
  public static final String TREETYPE_User1 = "U1";
  /** User 2 = U2 */
  public static final String TREETYPE_User2 = "U2";
  /** User 3 = U3 */
  public static final String TREETYPE_User3 = "U3";
  /** User 4 = U4 */
  public static final String TREETYPE_User4 = "U4";
  /** CM Container = CC */
  public static final String TREETYPE_CMContainer = "CC";
  /** CM Container Stage = CS */
  public static final String TREETYPE_CMContainerStage = "CS";
  /** CM Template = CT */
  public static final String TREETYPE_CMTemplate = "CT";
  /** CM Media = CM */
  public static final String TREETYPE_CMMedia = "CM";
  /** Custom Table = TL */
  public static final String TREETYPE_CustomTable = "TL";
  /**
   * Set Type | Area.
   *
   * @param TreeType Element this tree is built on (i.e Product, Business Partner)
   */
  public void setTreeType(String TreeType) {

    set_ValueNoCheck(COLUMNNAME_TreeType, TreeType);
  }

  /**
   * Get Type | Area.
   *
   * @return Element this tree is built on (i.e Product, Business Partner)
   */
  public String getTreeType() {
    return (String) get_Value(COLUMNNAME_TreeType);
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
