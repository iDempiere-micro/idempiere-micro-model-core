package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_AD_OrgInfo;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_OrgInfo
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_OrgInfo extends PO implements I_AD_OrgInfo, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_OrgInfo(Properties ctx, int AD_OrgInfo_ID, String trxName) {
    super(ctx, AD_OrgInfo_ID, trxName);
    /**
     * if (AD_OrgInfo_ID == 0) { setDUNS (null); setReceiptFooterMsg (null); // 1 setTaxID (null); }
     */
  }

  /** Load Constructor */
  public X_AD_OrgInfo(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  public X_AD_OrgInfo(Properties ctx, Row row) {
    super(ctx, row);
  } //	MOrgInfo

  /**
   * AccessLevel
   *
   * @return 7 - System - Client - Org
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("X_AD_OrgInfo[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Get AD_OrgInfo_UU.
   *
   * @return AD_OrgInfo_UU
   */
  public String getAD_OrgInfo_UU() {
    return (String) get_Value(COLUMNNAME_AD_OrgInfo_UU);
  }

  /**
   * Set AD_OrgInfo_UU.
   *
   * @param AD_OrgInfo_UU AD_OrgInfo_UU
   */
  public void setAD_OrgInfo_UU(String AD_OrgInfo_UU) {
    set_Value(COLUMNNAME_AD_OrgInfo_UU, AD_OrgInfo_UU);
  }

  /**
   * Get Organization Type.
   *
   * @return Organization Type
   */
  public int getAD_OrgType_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_OrgType_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Organization Type.
   *
   * @param AD_OrgType_ID Organization Type
   */
  public void setAD_OrgType_ID(int AD_OrgType_ID) {
    if (AD_OrgType_ID < 1) set_Value(COLUMNNAME_AD_OrgType_ID, null);
    else set_Value(COLUMNNAME_AD_OrgType_ID, Integer.valueOf(AD_OrgType_ID));
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
   * Get Address.
   *
   * @return Location or Address
   */
  public int getC_Location_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Location_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Address.
   *
   * @param C_Location_ID Location or Address
   */
  public void setC_Location_ID(int C_Location_ID) {
    if (C_Location_ID < 1) set_Value(COLUMNNAME_C_Location_ID, null);
    else set_Value(COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
  }

  /**
   * Get Drop Ship Warehouse.
   *
   * @return The (logical) warehouse to use for recording drop ship receipts and shipments.
   */
  public int getDropShip_Warehouse_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_DropShip_Warehouse_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Drop Ship Warehouse.
   *
   * @param DropShip_Warehouse_ID The (logical) warehouse to use for recording drop ship receipts
   *     and shipments.
   */
  public void setDropShip_Warehouse_ID(int DropShip_Warehouse_ID) {
    if (DropShip_Warehouse_ID < 1) set_Value(COLUMNNAME_DropShip_Warehouse_ID, null);
    else set_Value(COLUMNNAME_DropShip_Warehouse_ID, Integer.valueOf(DropShip_Warehouse_ID));
  }

  /**
   * Get D-U-N-S.
   *
   * @return Dun & Bradstreet Number
   */
  public String getDUNS() {
    return (String) get_Value(COLUMNNAME_DUNS);
  }

  /**
   * Set D-U-N-S.
   *
   * @param DUNS Dun & Bradstreet Number
   */
  public void setDUNS(String DUNS) {
    set_Value(COLUMNNAME_DUNS, DUNS);
  }

  /**
   * Get EMail Address.
   *
   * @return Electronic Mail Address
   */
  public String getEMail() {
    return (String) get_Value(COLUMNNAME_EMail);
  }

  /**
   * Set EMail Address.
   *
   * @param EMail Electronic Mail Address
   */
  public void setEMail(String EMail) {
    set_Value(COLUMNNAME_EMail, EMail);
  }

  /**
   * Get Fax.
   *
   * @return Facsimile number
   */
  public String getFax() {
    return (String) get_Value(COLUMNNAME_Fax);
  }

  /**
   * Set Fax.
   *
   * @param Fax Facsimile number
   */
  public void setFax(String Fax) {
    set_Value(COLUMNNAME_Fax, Fax);
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
   * Get Warehouse.
   *
   * @return Storage Warehouse and Service Point
   */
  public int getM_Warehouse_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_M_Warehouse_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Warehouse.
   *
   * @param M_Warehouse_ID Storage Warehouse and Service Point
   */
  public void setM_Warehouse_ID(int M_Warehouse_ID) {
    if (M_Warehouse_ID < 1) set_Value(COLUMNNAME_M_Warehouse_ID, null);
    else set_Value(COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
  }

  /**
   * Get Parent Organization.
   *
   * @return Parent (superior) Organization
   */
  public int getParent_Org_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_Parent_Org_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Parent Organization.
   *
   * @param Parent_Org_ID Parent (superior) Organization
   */
  public void setParent_Org_ID(int Parent_Org_ID) {
    if (Parent_Org_ID < 1) set_Value(COLUMNNAME_Parent_Org_ID, null);
    else set_Value(COLUMNNAME_Parent_Org_ID, Integer.valueOf(Parent_Org_ID));
  }

  /**
   * Get Phone.
   *
   * @return Identifies a telephone number
   */
  public String getPhone() {
    return (String) get_Value(COLUMNNAME_Phone);
  }

  /**
   * Set Phone.
   *
   * @param Phone Identifies a telephone number
   */
  public void setPhone(String Phone) {
    set_Value(COLUMNNAME_Phone, Phone);
  }

  /**
   * Get 2nd Phone.
   *
   * @return Identifies an alternate telephone number.
   */
  public String getPhone2() {
    return (String) get_Value(COLUMNNAME_Phone2);
  }

  /**
   * Set 2nd Phone.
   *
   * @param Phone2 Identifies an alternate telephone number.
   */
  public void setPhone2(String Phone2) {
    set_Value(COLUMNNAME_Phone2, Phone2);
  }

  /**
   * Get Receipt Footer Msg.
   *
   * @return This message will be displayed at the bottom of a receipt when doing a sales or
   *     purchase
   */
  public String getReceiptFooterMsg() {
    return (String) get_Value(COLUMNNAME_ReceiptFooterMsg);
  }

  /**
   * Set Receipt Footer Msg.
   *
   * @param ReceiptFooterMsg This message will be displayed at the bottom of a receipt when doing a
   *     sales or purchase
   */
  public void setReceiptFooterMsg(String ReceiptFooterMsg) {
    set_Value(COLUMNNAME_ReceiptFooterMsg, ReceiptFooterMsg);
  }

  /**
   * Get Supervisor.
   *
   * @return Supervisor for this user/organization - used for escalation and approval
   */
  public int getSupervisor_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_Supervisor_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Supervisor.
   *
   * @param Supervisor_ID Supervisor for this user/organization - used for escalation and approval
   */
  public void setSupervisor_ID(int Supervisor_ID) {
    if (Supervisor_ID < 1) set_Value(COLUMNNAME_Supervisor_ID, null);
    else set_Value(COLUMNNAME_Supervisor_ID, Integer.valueOf(Supervisor_ID));
  }

  /**
   * Get Tax ID.
   *
   * @return Tax Identification
   */
  public String getTaxID() {
    return (String) get_Value(COLUMNNAME_TaxID);
  }

  /**
   * Set Tax ID.
   *
   * @param TaxID Tax Identification
   */
  public void setTaxID(String TaxID) {
    set_Value(COLUMNNAME_TaxID, TaxID);
  }

  /**
   * Get Bank for transfers.
   *
   * @return Bank account depending on currency will be used from this bank for doing transfers
   */
  public int getTransferBank_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_TransferBank_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Bank for transfers.
   *
   * @param TransferBank_ID Bank account depending on currency will be used from this bank for doing
   *     transfers
   */
  public void setTransferBank_ID(int TransferBank_ID) {
    if (TransferBank_ID < 1) set_Value(COLUMNNAME_TransferBank_ID, null);
    else set_Value(COLUMNNAME_TransferBank_ID, Integer.valueOf(TransferBank_ID));
  }

  /**
   * Get CashBook for transfers.
   *
   * @return CashBook for transfers
   */
  public int getTransferCashBook_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_TransferCashBook_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set CashBook for transfers.
   *
   * @param TransferCashBook_ID CashBook for transfers
   */
  public void setTransferCashBook_ID(int TransferCashBook_ID) {
    if (TransferCashBook_ID < 1) set_Value(COLUMNNAME_TransferCashBook_ID, null);
    else set_Value(COLUMNNAME_TransferCashBook_ID, Integer.valueOf(TransferCashBook_ID));
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
