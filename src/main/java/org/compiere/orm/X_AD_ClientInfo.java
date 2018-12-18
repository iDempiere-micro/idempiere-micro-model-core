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
   * Get AD_ClientInfo_UU.
   *
   * @return AD_ClientInfo_UU
   */
  public String getAD_ClientInfo_UU() {
    return (String) get_Value(COLUMNNAME_AD_ClientInfo_UU);
  }

  /**
   * Set AD_ClientInfo_UU.
   *
   * @param AD_ClientInfo_UU AD_ClientInfo_UU
   */
  public void setAD_ClientInfo_UU(String AD_ClientInfo_UU) {
    set_Value(COLUMNNAME_AD_ClientInfo_UU, AD_ClientInfo_UU);
  }

  public org.compiere.model.I_AD_StorageProvider getAD_StorageProvider() throws RuntimeException {
    return (org.compiere.model.I_AD_StorageProvider)
        MTable.get(getCtx(), org.compiere.model.I_AD_StorageProvider.Table_Name)
            .getPO(getAD_StorageProvider_ID(), null);
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
   * Set Storage Provider.
   *
   * @param AD_StorageProvider_ID Storage Provider
   */
  public void setAD_StorageProvider_ID(int AD_StorageProvider_ID) {
    if (AD_StorageProvider_ID < 1) set_Value(COLUMNNAME_AD_StorageProvider_ID, null);
    else set_Value(COLUMNNAME_AD_StorageProvider_ID, Integer.valueOf(AD_StorageProvider_ID));
  }

  public org.compiere.model.I_AD_Tree getAD_Tree_Activity() throws RuntimeException {
    return (org.compiere.model.I_AD_Tree)
        MTable.get(getCtx(), org.compiere.model.I_AD_Tree.Table_Name)
            .getPO(getAD_Tree_Activity_ID(), null);
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

  public org.compiere.model.I_AD_Tree getAD_Tree_BPartner() throws RuntimeException {
    return (org.compiere.model.I_AD_Tree)
        MTable.get(getCtx(), org.compiere.model.I_AD_Tree.Table_Name)
            .getPO(getAD_Tree_BPartner_ID(), null);
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

  public org.compiere.model.I_AD_Tree getAD_Tree_Campaign() throws RuntimeException {
    return (org.compiere.model.I_AD_Tree)
        MTable.get(getCtx(), org.compiere.model.I_AD_Tree.Table_Name)
            .getPO(getAD_Tree_Campaign_ID(), null);
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

  public org.compiere.model.I_AD_Tree getAD_Tree_Menu() throws RuntimeException {
    return (org.compiere.model.I_AD_Tree)
        MTable.get(getCtx(), org.compiere.model.I_AD_Tree.Table_Name)
            .getPO(getAD_Tree_Menu_ID(), null);
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

  public org.compiere.model.I_AD_Tree getAD_Tree_Org() throws RuntimeException {
    return (org.compiere.model.I_AD_Tree)
        MTable.get(getCtx(), org.compiere.model.I_AD_Tree.Table_Name)
            .getPO(getAD_Tree_Org_ID(), null);
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

  public org.compiere.model.I_AD_Tree getAD_Tree_Product() throws RuntimeException {
    return (org.compiere.model.I_AD_Tree)
        MTable.get(getCtx(), org.compiere.model.I_AD_Tree.Table_Name)
            .getPO(getAD_Tree_Product_ID(), null);
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

  public org.compiere.model.I_AD_Tree getAD_Tree_Project() throws RuntimeException {
    return (org.compiere.model.I_AD_Tree)
        MTable.get(getCtx(), org.compiere.model.I_AD_Tree.Table_Name)
            .getPO(getAD_Tree_Project_ID(), null);
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

  public org.compiere.model.I_AD_Tree getAD_Tree_SalesRegion() throws RuntimeException {
    return (org.compiere.model.I_AD_Tree)
        MTable.get(getCtx(), org.compiere.model.I_AD_Tree.Table_Name)
            .getPO(getAD_Tree_SalesRegion_ID(), null);
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
   * Set Primary Accounting Schema.
   *
   * @param C_AcctSchema1_ID Primary rules for accounting
   */
  public void setC_AcctSchema1_ID(int C_AcctSchema1_ID) {
    if (C_AcctSchema1_ID < 1) set_ValueNoCheck(COLUMNNAME_C_AcctSchema1_ID, null);
    else set_ValueNoCheck(COLUMNNAME_C_AcctSchema1_ID, Integer.valueOf(C_AcctSchema1_ID));
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
   * Set Template B.Partner.
   *
   * @param C_BPartnerCashTrx_ID Business Partner used for creating new Business Partners on the fly
   */
  public void setC_BPartnerCashTrx_ID(int C_BPartnerCashTrx_ID) {
    if (C_BPartnerCashTrx_ID < 1) set_Value(COLUMNNAME_C_BPartnerCashTrx_ID, null);
    else set_Value(COLUMNNAME_C_BPartnerCashTrx_ID, Integer.valueOf(C_BPartnerCashTrx_ID));
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
   * Set Calendar.
   *
   * @param C_Calendar_ID Accounting Calendar Name
   */
  public void setC_Calendar_ID(int C_Calendar_ID) {
    if (C_Calendar_ID < 1) set_Value(COLUMNNAME_C_Calendar_ID, null);
    else set_Value(COLUMNNAME_C_Calendar_ID, Integer.valueOf(C_Calendar_ID));
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
   * Set Charge for Freight.
   *
   * @param C_ChargeFreight_ID Charge for Freight
   */
  public void setC_ChargeFreight_ID(int C_ChargeFreight_ID) {
    if (C_ChargeFreight_ID < 1) set_Value(COLUMNNAME_C_ChargeFreight_ID, null);
    else set_Value(COLUMNNAME_C_ChargeFreight_ID, Integer.valueOf(C_ChargeFreight_ID));
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
   * Set UOM for Length.
   *
   * @param C_UOM_Length_ID Standard Unit of Measure for Length
   */
  public void setC_UOM_Length_ID(int C_UOM_Length_ID) {
    if (C_UOM_Length_ID < 1) set_Value(COLUMNNAME_C_UOM_Length_ID, null);
    else set_Value(COLUMNNAME_C_UOM_Length_ID, Integer.valueOf(C_UOM_Length_ID));
  }

  /**
   * Get UOM for Time.
   *
   * @return Standard Unit of Measure for Time
   */
  public int getC_UOM_Time_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_UOM_Time_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set UOM for Time.
   *
   * @param C_UOM_Time_ID Standard Unit of Measure for Time
   */
  public void setC_UOM_Time_ID(int C_UOM_Time_ID) {
    if (C_UOM_Time_ID < 1) set_Value(COLUMNNAME_C_UOM_Time_ID, null);
    else set_Value(COLUMNNAME_C_UOM_Time_ID, Integer.valueOf(C_UOM_Time_ID));
  }

  /**
   * Get UOM for Volume.
   *
   * @return Standard Unit of Measure for Volume
   */
  public int getC_UOM_Volume_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_UOM_Volume_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set UOM for Volume.
   *
   * @param C_UOM_Volume_ID Standard Unit of Measure for Volume
   */
  public void setC_UOM_Volume_ID(int C_UOM_Volume_ID) {
    if (C_UOM_Volume_ID < 1) set_Value(COLUMNNAME_C_UOM_Volume_ID, null);
    else set_Value(COLUMNNAME_C_UOM_Volume_ID, Integer.valueOf(C_UOM_Volume_ID));
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
   * Set UOM for Weight.
   *
   * @param C_UOM_Weight_ID Standard Unit of Measure for Weight
   */
  public void setC_UOM_Weight_ID(int C_UOM_Weight_ID) {
    if (C_UOM_Weight_ID < 1) set_Value(COLUMNNAME_C_UOM_Weight_ID, null);
    else set_Value(COLUMNNAME_C_UOM_Weight_ID, Integer.valueOf(C_UOM_Weight_ID));
  }

  /**
   * Get Financial Year Start Date.
   *
   * @return Financial Year Start Date
   */
  public Timestamp getFY_StartDate() {
    return (Timestamp) get_Value(COLUMNNAME_FY_StartDate);
  }

  /**
   * Set Financial Year Start Date.
   *
   * @param FY_StartDate Financial Year Start Date
   */
  public void setFY_StartDate(Timestamp FY_StartDate) {
    set_Value(COLUMNNAME_FY_StartDate, FY_StartDate);
  }

  /**
   * Set Show Confirmation On Document Action Close.
   *
   * @param IsConfirmOnDocClose Show Confirmation On Document Action Close
   */
  public void setIsConfirmOnDocClose(boolean IsConfirmOnDocClose) {
    set_Value(COLUMNNAME_IsConfirmOnDocClose, Boolean.valueOf(IsConfirmOnDocClose));
  }

  /**
   * Get Show Confirmation On Document Action Close.
   *
   * @return Show Confirmation On Document Action Close
   */
  public boolean isConfirmOnDocClose() {
    Object oo = get_Value(COLUMNNAME_IsConfirmOnDocClose);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Show Confirmation On Document Action Void.
   *
   * @param IsConfirmOnDocVoid Show Confirmation On Document Action Void
   */
  public void setIsConfirmOnDocVoid(boolean IsConfirmOnDocVoid) {
    set_Value(COLUMNNAME_IsConfirmOnDocVoid, Boolean.valueOf(IsConfirmOnDocVoid));
  }

  /**
   * Get Show Confirmation On Document Action Void.
   *
   * @return Show Confirmation On Document Action Void
   */
  public boolean isConfirmOnDocVoid() {
    Object oo = get_Value(COLUMNNAME_IsConfirmOnDocVoid);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get Discount calculated from Line Amounts.
   *
   * @return Payment Discount calculation does not include Taxes and Charges
   */
  public boolean isDiscountLineAmt() {
    Object oo = get_Value(COLUMNNAME_IsDiscountLineAmt);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Get Days to keep Log.
   *
   * @return Number of days to keep the log entries
   */
  public int getKeepLogDays() {
    Integer ii = (Integer) get_Value(COLUMNNAME_KeepLogDays);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Days to keep Log.
   *
   * @param KeepLogDays Number of days to keep the log entries
   */
  public void setKeepLogDays(int KeepLogDays) {
    set_Value(COLUMNNAME_KeepLogDays, Integer.valueOf(KeepLogDays));
  }

  /**
   * Get Logo.
   *
   * @return Logo
   */
  public int getLogo_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_Logo_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Logo.
   *
   * @param Logo_ID Logo
   */
  public void setLogo_ID(int Logo_ID) {
    if (Logo_ID < 1) set_Value(COLUMNNAME_Logo_ID, null);
    else set_Value(COLUMNNAME_Logo_ID, Integer.valueOf(Logo_ID));
  }

  /**
   * Get Logo Report.
   *
   * @return Logo Report
   */
  public int getLogoReport_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_LogoReport_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Logo Report.
   *
   * @param LogoReport_ID Logo Report
   */
  public void setLogoReport_ID(int LogoReport_ID) {
    if (LogoReport_ID < 1) set_Value(COLUMNNAME_LogoReport_ID, null);
    else set_Value(COLUMNNAME_LogoReport_ID, Integer.valueOf(LogoReport_ID));
  }

  /**
   * Get Logo Web.
   *
   * @return Logo Web
   */
  public int getLogoWeb_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_LogoWeb_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Logo Web.
   *
   * @param LogoWeb_ID Logo Web
   */
  public void setLogoWeb_ID(int LogoWeb_ID) {
    if (LogoWeb_ID < 1) set_Value(COLUMNNAME_LogoWeb_ID, null);
    else set_Value(COLUMNNAME_LogoWeb_ID, Integer.valueOf(LogoWeb_ID));
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
   * Set Product for Freight.
   *
   * @param M_ProductFreight_ID Product for Freight
   */
  public void setM_ProductFreight_ID(int M_ProductFreight_ID) {
    if (M_ProductFreight_ID < 1) set_Value(COLUMNNAME_M_ProductFreight_ID, null);
    else set_Value(COLUMNNAME_M_ProductFreight_ID, Integer.valueOf(M_ProductFreight_ID));
  }

  public org.compiere.model.I_AD_StorageProvider getStorageArchive() throws RuntimeException {
    return (org.compiere.model.I_AD_StorageProvider)
        MTable.get(getCtx(), org.compiere.model.I_AD_StorageProvider.Table_Name)
            .getPO(getStorageArchive_ID(), null);
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

  /**
   * Set Archive Store.
   *
   * @param StorageArchive_ID Archive Store
   */
  public void setStorageArchive_ID(int StorageArchive_ID) {
    if (StorageArchive_ID < 1) set_Value(COLUMNNAME_StorageArchive_ID, null);
    else set_Value(COLUMNNAME_StorageArchive_ID, Integer.valueOf(StorageArchive_ID));
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
