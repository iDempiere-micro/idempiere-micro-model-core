package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.ClientInfo;

/**
 * Generated Model for AD_ClientInfo
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_ClientInfo extends PO implements ClientInfo {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_ClientInfo(int AD_ClientInfo_ID) {
        super(AD_ClientInfo_ID);
        /**
         * if (AD_ClientInfo_ID == 0) { setIsConfirmOnDocClose (false); // N setIsConfirmOnDocVoid
         * (false); // N setIsDiscountLineAmt (false); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_ClientInfo(Row row) {
        super(row);
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
    public int getStorageProviderId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_StorageProvider_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Activity Tree.
     *
     * @param AD_Tree_Activity_ID Trees are used for (financial) reporting
     */
    public void setTreeActivityId(int AD_Tree_Activity_ID) {
        if (AD_Tree_Activity_ID < 1) setValueNoCheck(COLUMNNAME_AD_Tree_Activity_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Tree_Activity_ID, Integer.valueOf(AD_Tree_Activity_ID));
    }

    /**
     * Set BPartner Tree.
     *
     * @param AD_Tree_BPartner_ID Trees are used for (financial) reporting
     */
    public void setTreeBPartnerId(int AD_Tree_BPartner_ID) {
        if (AD_Tree_BPartner_ID < 1) setValueNoCheck(COLUMNNAME_AD_Tree_BPartner_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Tree_BPartner_ID, Integer.valueOf(AD_Tree_BPartner_ID));
    }

    /**
     * Set Campaign Tree.
     *
     * @param AD_Tree_Campaign_ID Trees are used for (financial) reporting
     */
    public void setTreeCampaignId(int AD_Tree_Campaign_ID) {
        if (AD_Tree_Campaign_ID < 1) setValueNoCheck(COLUMNNAME_AD_Tree_Campaign_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Tree_Campaign_ID, Integer.valueOf(AD_Tree_Campaign_ID));
    }

    /**
     * Set Menu Tree.
     *
     * @param AD_Tree_Menu_ID Tree of the menu
     */
    public void setTreeMenuId(int AD_Tree_Menu_ID) {
        if (AD_Tree_Menu_ID < 1) setValueNoCheck(COLUMNNAME_AD_Tree_Menu_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Tree_Menu_ID, Integer.valueOf(AD_Tree_Menu_ID));
    }

    /**
     * Get Organization Tree.
     *
     * @return Trees are used for (financial) reporting and security access (via role)
     */
    public int getTreeOrgId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Tree_Org_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Organization Tree.
     *
     * @param AD_Tree_Org_ID Trees are used for (financial) reporting and security access (via role)
     */
    public void setTreeOrgId(int AD_Tree_Org_ID) {
        if (AD_Tree_Org_ID < 1) setValueNoCheck(COLUMNNAME_AD_Tree_Org_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Tree_Org_ID, Integer.valueOf(AD_Tree_Org_ID));
    }

    /**
     * Set Product Tree.
     *
     * @param AD_Tree_Product_ID Trees are used for (financial) reporting
     */
    public void setTreeProductId(int AD_Tree_Product_ID) {
        if (AD_Tree_Product_ID < 1) setValueNoCheck(COLUMNNAME_AD_Tree_Product_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Tree_Product_ID, Integer.valueOf(AD_Tree_Product_ID));
    }

    /**
     * Set Project Tree.
     *
     * @param AD_Tree_Project_ID Trees are used for (financial) reporting
     */
    public void setTreeProjectId(int AD_Tree_Project_ID) {
        if (AD_Tree_Project_ID < 1) setValueNoCheck(COLUMNNAME_AD_Tree_Project_ID, null);
        else setValueNoCheck(COLUMNNAME_AD_Tree_Project_ID, Integer.valueOf(AD_Tree_Project_ID));
    }

    /**
     * Set Sales Region Tree.
     *
     * @param AD_Tree_SalesRegion_ID Trees are used for (financial) reporting
     */
    public void setTreeSalesRegionId(int AD_Tree_SalesRegion_ID) {
        if (AD_Tree_SalesRegion_ID < 1) setValueNoCheck(COLUMNNAME_AD_Tree_SalesRegion_ID, null);
        else
            setValueNoCheck(COLUMNNAME_AD_Tree_SalesRegion_ID, Integer.valueOf(AD_Tree_SalesRegion_ID));
    }

    /**
     * Get Primary Accounting Schema.
     *
     * @return Primary rules for accounting
     */
    public int getAcctSchema1Id() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_AcctSchema1_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Template B.Partner.
     *
     * @return Business Partner used for creating new Business Partners on the fly
     */
    public int getBPartnerCashTrxId() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_BPartnerCashTrx_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Calendar.
     *
     * @return Accounting Calendar Name
     */
    public int getCalendarId() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_Calendar_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Charge for Freight.
     *
     * @return Charge for Freight
     */
    public int getChargeFreightId() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_ChargeFreight_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get UOM for Length.
     *
     * @return Standard Unit of Measure for Length
     */
    public int getUOMLengthId() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_UOM_Length_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get UOM for Weight.
     *
     * @return Standard Unit of Measure for Weight
     */
    public int getUOMWeightId() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_UOM_Weight_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Discount calculated from Line Amounts.
     *
     * @param IsDiscountLineAmt Payment Discount calculation does not include Taxes and Charges
     */
    public void setIsDiscountLineAmt(boolean IsDiscountLineAmt) {
        setValue(COLUMNNAME_IsDiscountLineAmt, Boolean.valueOf(IsDiscountLineAmt));
    }

    /**
     * Get Product for Freight.
     *
     * @return Product for Freight
     */
    public int getProductFreightId() {
        Integer ii = (Integer) getValue(COLUMNNAME_M_ProductFreight_ID);
        if (ii == null) return 0;
        return ii;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
