package org.compiere.orm;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_AD_ClientInfo;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_ClientInfo
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_ClientInfo extends PO implements I_AD_ClientInfo, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_ClientInfo(Properties ctx, int AD_ClientInfo_ID, String trxName) {
    super(ctx, AD_ClientInfo_ID, trxName);
    /**
     * if (AD_ClientInfo_ID == 0) { setIsConfirmOnDocClose (false); // N setIsConfirmOnDocVoid
     * (false); // N setIsDiscountLineAmt (false); }
     */
  }

  /** Load Constructor */
  public X_AD_ClientInfo(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  public X_AD_ClientInfo(Properties ctx, Row row) {
    super(ctx, row);
  } //	MClientInfo

  /**
   * AccessLevel
   *
   * @return 6 - System - Client
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("X_AD_ClientInfo[").append(getId()).append("]");
    return sb.toString();
  }

    /**
   * Get Storage Provider.
   *
   * @return Storage Provider
   */
  public int getAD_StorageProvider_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_StorageProvider_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Activity Tree.
   *
   * @return Trees are used for (financial) reporting
   */
  public int getAD_Tree_Activity_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Tree_Activity_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Activity Tree.
   *
   * @param AD_Tree_Activity_ID Trees are used for (financial) reporting
   */
  public void setAD_Tree_Activity_ID(int AD_Tree_Activity_ID) {
    if (AD_Tree_Activity_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Tree_Activity_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Tree_Activity_ID, Integer.valueOf(AD_Tree_Activity_ID));
  }

    /**
   * Get BPartner Tree.
   *
   * @return Trees are used for (financial) reporting
   */
  public int getAD_Tree_BPartner_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Tree_BPartner_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set BPartner Tree.
   *
   * @param AD_Tree_BPartner_ID Trees are used for (financial) reporting
   */
  public void setAD_Tree_BPartner_ID(int AD_Tree_BPartner_ID) {
    if (AD_Tree_BPartner_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Tree_BPartner_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Tree_BPartner_ID, Integer.valueOf(AD_Tree_BPartner_ID));
  }

    /**
   * Get Campaign Tree.
   *
   * @return Trees are used for (financial) reporting
   */
  public int getAD_Tree_Campaign_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Tree_Campaign_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Campaign Tree.
   *
   * @param AD_Tree_Campaign_ID Trees are used for (financial) reporting
   */
  public void setAD_Tree_Campaign_ID(int AD_Tree_Campaign_ID) {
    if (AD_Tree_Campaign_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Tree_Campaign_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Tree_Campaign_ID, Integer.valueOf(AD_Tree_Campaign_ID));
  }

    /**
   * Get Menu Tree.
   *
   * @return Tree of the menu
   */
  public int getAD_Tree_Menu_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Tree_Menu_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Menu Tree.
   *
   * @param AD_Tree_Menu_ID Tree of the menu
   */
  public void setAD_Tree_Menu_ID(int AD_Tree_Menu_ID) {
    if (AD_Tree_Menu_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Tree_Menu_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Tree_Menu_ID, Integer.valueOf(AD_Tree_Menu_ID));
  }

    /**
   * Get Organization Tree.
   *
   * @return Trees are used for (financial) reporting and security access (via role)
   */
  public int getAD_Tree_Org_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Tree_Org_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Organization Tree.
   *
   * @param AD_Tree_Org_ID Trees are used for (financial) reporting and security access (via role)
   */
  public void setAD_Tree_Org_ID(int AD_Tree_Org_ID) {
    if (AD_Tree_Org_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Tree_Org_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Tree_Org_ID, Integer.valueOf(AD_Tree_Org_ID));
  }

    /**
   * Get Product Tree.
   *
   * @return Trees are used for (financial) reporting
   */
  public int getAD_Tree_Product_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Tree_Product_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Product Tree.
   *
   * @param AD_Tree_Product_ID Trees are used for (financial) reporting
   */
  public void setAD_Tree_Product_ID(int AD_Tree_Product_ID) {
    if (AD_Tree_Product_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Tree_Product_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Tree_Product_ID, Integer.valueOf(AD_Tree_Product_ID));
  }

    /**
   * Get Project Tree.
   *
   * @return Trees are used for (financial) reporting
   */
  public int getAD_Tree_Project_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Tree_Project_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Project Tree.
   *
   * @param AD_Tree_Project_ID Trees are used for (financial) reporting
   */
  public void setAD_Tree_Project_ID(int AD_Tree_Project_ID) {
    if (AD_Tree_Project_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Tree_Project_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Tree_Project_ID, Integer.valueOf(AD_Tree_Project_ID));
  }

    /**
   * Get Sales Region Tree.
   *
   * @return Trees are used for (financial) reporting
   */
  public int getAD_Tree_SalesRegion_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Tree_SalesRegion_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Sales Region Tree.
   *
   * @param AD_Tree_SalesRegion_ID Trees are used for (financial) reporting
   */
  public void setAD_Tree_SalesRegion_ID(int AD_Tree_SalesRegion_ID) {
    if (AD_Tree_SalesRegion_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Tree_SalesRegion_ID, null);
    else
      set_ValueNoCheck(COLUMNNAME_AD_Tree_SalesRegion_ID, Integer.valueOf(AD_Tree_SalesRegion_ID));
  }

  /**
   * Get Primary Accounting Schema.
   *
   * @return Primary rules for accounting
   */
  public int getC_AcctSchema1_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_AcctSchema1_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Template B.Partner.
   *
   * @return Business Partner used for creating new Business Partners on the fly
   */
  public int getC_BPartnerCashTrx_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_BPartnerCashTrx_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Calendar.
   *
   * @return Accounting Calendar Name
   */
  public int getC_Calendar_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Calendar_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Charge for Freight.
   *
   * @return Charge for Freight
   */
  public int getC_ChargeFreight_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_ChargeFreight_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get UOM for Length.
   *
   * @return Standard Unit of Measure for Length
   */
  public int getC_UOM_Length_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_UOM_Length_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get UOM for Weight.
   *
   * @return Standard Unit of Measure for Weight
   */
  public int getC_UOM_Weight_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_UOM_Weight_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Set Discount calculated from Line Amounts.
   *
   * @param IsDiscountLineAmt Payment Discount calculation does not include Taxes and Charges
   */
  public void setIsDiscountLineAmt(boolean IsDiscountLineAmt) {
    set_Value(COLUMNNAME_IsDiscountLineAmt, Boolean.valueOf(IsDiscountLineAmt));
  }

    /**
   * Get Product for Freight.
   *
   * @return Product for Freight
   */
  public int getM_ProductFreight_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_M_ProductFreight_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Archive Store.
   *
   * @return Archive Store
   */
  public int getStorageArchive_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_StorageArchive_ID);
    if (ii == null) return 0;
    return ii;
  }

    @Override
  public int getTableId() {
    return Table_ID;
  }
}
