package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.Role;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;

import java.math.BigDecimal;

/**
 * Generated Model for AD_Role
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public abstract class X_AD_Role extends BasePOName implements Role {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Role(int AD_Role_ID) {
        super(AD_Role_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Role(Row row) {
        super(row);
    } //	MRole

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_AD_Role[" + getId() + "]";
    }

    /**
     * Get Role.
     *
     * @return Responsibility Role
     */
    public int getRoleId() {
        Integer ii = getValue(COLUMNNAME_AD_Role_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Organization Tree.
     *
     * @return Trees are used for (financial) reporting and security access (via role)
     */
    public int getTreeOrgId() {
        Integer ii = getValue(COLUMNNAME_AD_Tree_Org_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Approval Amount.
     *
     * @return The approval amount limit for this role
     */
    public BigDecimal getAmtApproval() {
        BigDecimal bd = getValue(COLUMNNAME_AmtApproval);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Get Approval Amount Accumulated.
     *
     * @return The approval amount limit for this role accumulated on a period
     */
    public BigDecimal getAmtApprovalAccum() {
        BigDecimal bd = getValue(COLUMNNAME_AmtApprovalAccum);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Get Currency.
     *
     * @return The Currency for this record
     */
    public int getCurrencyId() {
        Integer ii = getValue(COLUMNNAME_C_Currency_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Confirm Query Records.
     *
     * @return Require Confirmation if more records will be returned by the query (If not defined 500)
     */
    public int getConfirmQueryRecords() {
        Integer ii = getValue(COLUMNNAME_ConfirmQueryRecords);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Confirm Query Records.
     *
     * @param ConfirmQueryRecords Require Confirmation if more records will be returned by the query
     *                            (If not defined 500)
     */
    public void setConfirmQueryRecords(int ConfirmQueryRecords) {
        setValue(COLUMNNAME_ConfirmQueryRecords, ConfirmQueryRecords);
    }

    /**
     * Get Days Approval Accumulated.
     *
     * @return The days approval indicates the days to take into account to verify the accumulated
     * approval amount.
     */
    public int getDaysApprovalAccum() {
        Integer ii = getValue(COLUMNNAME_DaysApprovalAccum);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Access Advanced .
     *
     * @param IsAccessAdvanced Access Advanced
     */
    public void setIsAccessAdvanced(boolean IsAccessAdvanced) {
        setValue(COLUMNNAME_IsAccessAdvanced, IsAccessAdvanced);
    }

    /**
     * Get Access Advanced .
     *
     * @return Access Advanced
     */
    public boolean isAccessAdvanced() {
        Object oo = getValue(COLUMNNAME_IsAccessAdvanced);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Access all Orgs.
     *
     * @param IsAccessAllOrgs Access all Organizations (no org access control) of the client
     */
    public void setIsAccessAllOrgs(boolean IsAccessAllOrgs) {
        setValue(COLUMNNAME_IsAccessAllOrgs, IsAccessAllOrgs);
    }

    /**
     * Get Access all Orgs.
     *
     * @return Access all Organizations (no org access control) of the client
     */
    public boolean isAccessAllOrgs() {
        Object oo = getValue(COLUMNNAME_IsAccessAllOrgs);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Approve own Documents.
     *
     * @return Users with this role can approve their own documents
     */
    public boolean isCanApproveOwnDoc() {
        Object oo = getValue(COLUMNNAME_IsCanApproveOwnDoc);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Can Export.
     *
     * @param IsCanExport Users with this role can export data
     */
    public void setIsCanExport(boolean IsCanExport) {
        setValue(COLUMNNAME_IsCanExport, Boolean.valueOf(IsCanExport));
    }

    /**
     * Set Can Report.
     *
     * @param IsCanReport Users with this role can create reports
     */
    public void setIsCanReport(boolean IsCanReport) {
        setValue(COLUMNNAME_IsCanReport, Boolean.valueOf(IsCanReport));
    }

    /**
     * Get Can Report.
     *
     * @return Users with this role can create reports
     */
    public boolean isCanReport() {
        Object oo = getValue(COLUMNNAME_IsCanReport);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Maintain Change Log.
     *
     * @param IsChangeLog Maintain a log of changes
     */
    public void setIsChangeLog(boolean IsChangeLog) {
        setValue(COLUMNNAME_IsChangeLog, Boolean.valueOf(IsChangeLog));
    }

    /**
     * Set Manual.
     *
     * @param IsManual This is a manual process
     */
    public void setIsManual(boolean IsManual) {
        setValue(COLUMNNAME_IsManual, Boolean.valueOf(IsManual));
    }

    /**
     * Get Manual.
     *
     * @return This is a manual process
     */
    public boolean isManual() {
        Object oo = getValue(COLUMNNAME_IsManual);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Master Role.
     *
     * @return A master role cannot be assigned to users, it is intended to define access to menu
     * option and documents and inherit to other roles
     */
    public boolean isMasterRole() {
        Object oo = getValue(COLUMNNAME_IsMasterRole);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Auto expand menu.
     *
     * @return If ticked, the menu is automatically expanded
     */
    public boolean isMenuAutoExpand() {
        Object oo = getValue(COLUMNNAME_IsMenuAutoExpand);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Personal Access.
     *
     * @param IsPersonalAccess Allow access to all personal records
     */
    public void setIsPersonalAccess(boolean IsPersonalAccess) {
        setValue(COLUMNNAME_IsPersonalAccess, Boolean.valueOf(IsPersonalAccess));
    }

    /**
     * Get Personal Access.
     *
     * @return Allow access to all personal records
     */
    public boolean isPersonalAccess() {
        Object oo = getValue(COLUMNNAME_IsPersonalAccess);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Personal Lock.
     *
     * @param IsPersonalLock Allow users with role to lock access to personal records
     */
    public void setIsPersonalLock(boolean IsPersonalLock) {
        setValue(COLUMNNAME_IsPersonalLock, Boolean.valueOf(IsPersonalLock));
    }

    /**
     * Set Show Accounting.
     *
     * @param IsShowAcct Users with this role can see accounting information
     */
    public void setIsShowAcct(boolean IsShowAcct) {
        setValue(COLUMNNAME_IsShowAcct, Boolean.valueOf(IsShowAcct));
    }

    /**
     * Set Use User Org Access.
     *
     * @param IsUseUserOrgAccess Use Org Access defined by user instead of Role Org Access
     */
    public void setIsUseUserOrgAccess(boolean IsUseUserOrgAccess) {
        setValue(COLUMNNAME_IsUseUserOrgAccess, Boolean.valueOf(IsUseUserOrgAccess));
    }

    /**
     * Get Use User Org Access.
     *
     * @return Use Org Access defined by user instead of Role Org Access
     */
    public boolean isUseUserOrgAccess() {
        Object oo = getValue(COLUMNNAME_IsUseUserOrgAccess);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Max Query Records.
     *
     * @param MaxQueryRecords If defined, you cannot query more records as defined - the query
     *                        criteria needs to be changed to query less records
     */
    public void setMaxQueryRecords(int MaxQueryRecords) {
        setValue(COLUMNNAME_MaxQueryRecords, Integer.valueOf(MaxQueryRecords));
    }

    /**
     * Get Overwrite Price Limit.
     *
     * @return Overwrite Price Limit if the Price List enforces the Price Limit
     */
    public boolean isOverwritePriceLimit() {
        Object oo = getValue(COLUMNNAME_OverwritePriceLimit);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Overwrite Price Limit.
     *
     * @param OverwritePriceLimit Overwrite Price Limit if the Price List enforces the Price Limit
     */
    public void setOverwritePriceLimit(boolean OverwritePriceLimit) {
        setValue(COLUMNNAME_OverwritePriceLimit, OverwritePriceLimit);
    }

    /**
     * Set Preference Level.
     *
     * @param PreferenceType Determines what preferences the user can set
     */
    public void setPreferenceType(String PreferenceType) {

        setValue(COLUMNNAME_PreferenceType, PreferenceType);
    }

    /**
     * Get User Level.
     *
     * @return System Client Organization
     */
    public String getUserLevel() {
        return getValue(COLUMNNAME_UserLevel);
    }

    /**
     * Set User Level.
     *
     * @param UserLevel System Client Organization
     */
    public void setUserLevel(String UserLevel) {

        setValue(COLUMNNAME_UserLevel, UserLevel);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }

    protected CLogger getMyLog() {
        return super.log;
    }

}
