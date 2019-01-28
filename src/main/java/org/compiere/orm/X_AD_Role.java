package org.compiere.orm;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_AD_Role;
import org.idempiere.common.util.Env;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Role
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Role extends BasePOName implements I_AD_Role, I_Persistent {

    /** Client = C */
  public static final String PREFERENCETYPE_Client = "C";
  /** Organization = O */
  public static final String PREFERENCETYPE_Organization = "O";
    /** None = N */
  public static final String PREFERENCETYPE_None = "N";
    /** System = S */
  public static final String USERLEVEL_System = "S  ";
  /** Client = C */
  public static final String USERLEVEL_Client = " C ";
  /** Organization = O */
  public static final String USERLEVEL_Organization = "  O";
  /** Client+Organization = CO */
  public static final String USERLEVEL_ClientPlusOrganization = " CO";
  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Role(Properties ctx, int AD_Role_ID, String trxName) {
    super(ctx, AD_Role_ID, trxName);
    /**
     * if (AD_Role_ID == 0) { setAD_Role_ID (0); setAllow_Info_Account (true); // Y
     * setAllow_Info_Asset (true); // Y setAllow_Info_BPartner (true); // Y setAllow_Info_InOut
     * (true); // Y setAllow_Info_Invoice (true); // Y setAllow_Info_Order (true); // Y
     * setAllow_Info_Payment (true); // Y setAllow_Info_Product (true); // Y setAllow_Info_Resource
     * (true); // Y setAllow_Info_Schedule (true); // Y setConfirmQueryRecords (0); // 0
     * setIsAccessAllOrgs (false); // N setIsCanApproveOwnDoc (false); setIsCanExport (true); // Y
     * setIsCanReport (true); // Y setIsChangeLog (false); // N setIsDiscountAllowedOnTotal (false);
     * setIsDiscountUptoLimitPrice (false); setIsManual (true); // Y setIsMasterRole (false); // N
     * setIsMenuAutoExpand (false); // N setIsPersonalAccess (false); // N setIsPersonalLock
     * (false); // N setIsShowAcct (false); // N setIsUseUserOrgAccess (false); // N
     * setMaxQueryRecords (0); // 0 setName (null); setOverwritePriceLimit (false); // N
     * setPreferenceType (null); // O setUserLevel (null); }
     */
  }

  /** Load Constructor */
  public X_AD_Role(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  public X_AD_Role(Properties ctx, Row row) {
    super(ctx, row);
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
    StringBuffer sb = new StringBuffer("X_AD_Role[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Get Role.
   *
   * @return Responsibility Role
   */
  public int getAD_Role_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Role_ID);
    if (ii == null) return 0;
    return ii;
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
   * Get Approval Amount.
   *
   * @return The approval amount limit for this role
   */
  public BigDecimal getAmtApproval() {
    BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_AmtApproval);
    if (bd == null) return Env.ZERO;
    return bd;
  }

    /**
   * Get Approval Amount Accumulated.
   *
   * @return The approval amount limit for this role accumulated on a period
   */
  public BigDecimal getAmtApprovalAccum() {
    BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_AmtApprovalAccum);
    if (bd == null) return Env.ZERO;
    return bd;
  }

    /**
   * Get Currency.
   *
   * @return The Currency for this record
   */
  public int getC_Currency_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Currency_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Confirm Query Records.
   *
   * @return Require Confirmation if more records will be returned by the query (If not defined 500)
   */
  public int getConfirmQueryRecords() {
    Integer ii = (Integer) get_Value(COLUMNNAME_ConfirmQueryRecords);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Confirm Query Records.
   *
   * @param ConfirmQueryRecords Require Confirmation if more records will be returned by the query
   *     (If not defined 500)
   */
  public void setConfirmQueryRecords(int ConfirmQueryRecords) {
    set_Value(COLUMNNAME_ConfirmQueryRecords, Integer.valueOf(ConfirmQueryRecords));
  }

  /**
   * Get Days Approval Accumulated.
   *
   * @return The days approval indicates the days to take into account to verify the accumulated
   *     approval amount.
   */
  public int getDaysApprovalAccum() {
    Integer ii = (Integer) get_Value(COLUMNNAME_DaysApprovalAccum);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Set Access Advanced .
   *
   * @param IsAccessAdvanced Access Advanced
   */
  public void setIsAccessAdvanced(boolean IsAccessAdvanced) {
    set_Value(COLUMNNAME_IsAccessAdvanced, Boolean.valueOf(IsAccessAdvanced));
  }

  /**
   * Get Access Advanced .
   *
   * @return Access Advanced
   */
  public boolean isAccessAdvanced() {
    Object oo = get_Value(COLUMNNAME_IsAccessAdvanced);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(COLUMNNAME_IsAccessAllOrgs, Boolean.valueOf(IsAccessAllOrgs));
  }

  /**
   * Get Access all Orgs.
   *
   * @return Access all Organizations (no org access control) of the client
   */
  public boolean isAccessAllOrgs() {
    Object oo = get_Value(COLUMNNAME_IsAccessAllOrgs);
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
    Object oo = get_Value(COLUMNNAME_IsCanApproveOwnDoc);
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
    set_Value(COLUMNNAME_IsCanExport, Boolean.valueOf(IsCanExport));
  }

  /**
   * Get Can Export.
   *
   * @return Users with this role can export data
   */
  public boolean isCanExport() {
    Object oo = get_Value(COLUMNNAME_IsCanExport);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Can Report.
   *
   * @param IsCanReport Users with this role can create reports
   */
  public void setIsCanReport(boolean IsCanReport) {
    set_Value(COLUMNNAME_IsCanReport, Boolean.valueOf(IsCanReport));
  }

  /**
   * Get Can Report.
   *
   * @return Users with this role can create reports
   */
  public boolean isCanReport() {
    Object oo = get_Value(COLUMNNAME_IsCanReport);
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
    set_Value(COLUMNNAME_IsChangeLog, Boolean.valueOf(IsChangeLog));
  }

    /**
   * Set Manual.
   *
   * @param IsManual This is a manual process
   */
  public void setIsManual(boolean IsManual) {
    set_Value(COLUMNNAME_IsManual, Boolean.valueOf(IsManual));
  }

  /**
   * Get Manual.
   *
   * @return This is a manual process
   */
  public boolean isManual() {
    Object oo = get_Value(COLUMNNAME_IsManual);
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
   *     option and documents and inherit to other roles
   */
  public boolean isMasterRole() {
    Object oo = get_Value(COLUMNNAME_IsMasterRole);
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
    Object oo = get_Value(COLUMNNAME_IsMenuAutoExpand);
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
    set_Value(COLUMNNAME_IsPersonalAccess, Boolean.valueOf(IsPersonalAccess));
  }

  /**
   * Get Personal Access.
   *
   * @return Allow access to all personal records
   */
  public boolean isPersonalAccess() {
    Object oo = get_Value(COLUMNNAME_IsPersonalAccess);
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
    set_Value(COLUMNNAME_IsPersonalLock, Boolean.valueOf(IsPersonalLock));
  }

    /**
   * Set Show Accounting.
   *
   * @param IsShowAcct Users with this role can see accounting information
   */
  public void setIsShowAcct(boolean IsShowAcct) {
    set_Value(COLUMNNAME_IsShowAcct, Boolean.valueOf(IsShowAcct));
  }

    /**
   * Set Use User Org Access.
   *
   * @param IsUseUserOrgAccess Use Org Access defined by user instead of Role Org Access
   */
  public void setIsUseUserOrgAccess(boolean IsUseUserOrgAccess) {
    set_Value(COLUMNNAME_IsUseUserOrgAccess, Boolean.valueOf(IsUseUserOrgAccess));
  }

  /**
   * Get Use User Org Access.
   *
   * @return Use Org Access defined by user instead of Role Org Access
   */
  public boolean isUseUserOrgAccess() {
    Object oo = get_Value(COLUMNNAME_IsUseUserOrgAccess);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Get Max Query Records.
   *
   * @return If defined, you cannot query more records as defined - the query criteria needs to be
   *     changed to query less records
   */
  public int getMaxQueryRecords() {
    Integer ii = (Integer) get_Value(COLUMNNAME_MaxQueryRecords);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Max Query Records.
   *
   * @param MaxQueryRecords If defined, you cannot query more records as defined - the query
   *     criteria needs to be changed to query less records
   */
  public void setMaxQueryRecords(int MaxQueryRecords) {
    set_Value(COLUMNNAME_MaxQueryRecords, Integer.valueOf(MaxQueryRecords));
  }

  /**
   * Get Overwrite Price Limit.
   *
   * @return Overwrite Price Limit if the Price List enforces the Price Limit
   */
  public boolean isOverwritePriceLimit() {
    Object oo = get_Value(COLUMNNAME_OverwritePriceLimit);
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
    set_Value(COLUMNNAME_OverwritePriceLimit, OverwritePriceLimit);
  }

  /**
   * Get Preference Level.
   *
   * @return Determines what preferences the user can set
   */
  public String getPreferenceType() {
    return (String) get_Value(COLUMNNAME_PreferenceType);
  }

  /**
   * Set Preference Level.
   *
   * @param PreferenceType Determines what preferences the user can set
   */
  public void setPreferenceType(String PreferenceType) {

    set_Value(COLUMNNAME_PreferenceType, PreferenceType);
  }

    /**
   * Get User Level.
   *
   * @return System Client Organization
   */
  public String getUserLevel() {
    return (String) get_Value(COLUMNNAME_UserLevel);
  }

  /**
   * Set User Level.
   *
   * @param UserLevel System Client Organization
   */
  public void setUserLevel(String UserLevel) {

    set_Value(COLUMNNAME_UserLevel, UserLevel);
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
