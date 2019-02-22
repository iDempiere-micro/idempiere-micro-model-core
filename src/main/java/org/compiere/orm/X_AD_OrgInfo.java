package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_OrgInfo;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_OrgInfo
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_OrgInfo extends PO implements I_AD_OrgInfo, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_OrgInfo(Properties ctx, int AD_OrgInfo_ID) {
        super(ctx, AD_OrgInfo_ID);
        /**
         * if (AD_OrgInfo_ID == 0) { setDUNS (null); setReceiptFooterMsg (null); // 1 setTaxID (null); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_OrgInfo(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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
     * Set D-U-N-S.
     *
     * @param DUNS Dun & Bradstreet Number
     */
    public void setDUNS(String DUNS) {
        set_Value(COLUMNNAME_DUNS, DUNS);
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
     * Set Fax.
     *
     * @param Fax Facsimile number
     */
    public void setFax(String Fax) {
        set_Value(COLUMNNAME_Fax, Fax);
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
     * Set Phone.
     *
     * @param Phone Identifies a telephone number
     */
    public void setPhone(String Phone) {
        set_Value(COLUMNNAME_Phone, Phone);
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
     * Set Tax ID.
     *
     * @param TaxID Tax Identification
     */
    public void setTaxID(String TaxID) {
        set_Value(COLUMNNAME_TaxID, TaxID);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
