package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_User_Roles;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_User_Roles
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_User_Roles extends PO implements I_AD_User_Roles, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_User_Roles(Properties ctx, int AD_User_Roles_ID) {
    super(ctx, AD_User_Roles_ID);
    /** if (AD_User_Roles_ID == 0) { setAD_Role_ID (0); setAD_User_ID (0); } */
  }

  /** Load Constructor */
  public X_AD_User_Roles(Properties ctx, ResultSet rs) {
    super(ctx, rs);
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
    StringBuffer sb = new StringBuffer("X_AD_User_Roles[").append(getId()).append("]");
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
   * Set Role.
   *
   * @param AD_Role_ID Responsibility Role
   */
  public void setAD_Role_ID(int AD_Role_ID) {
    if (AD_Role_ID < 0) set_ValueNoCheck(COLUMNNAME_AD_Role_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
  }

  /**
   * Get User/Contact.
   *
   * @return User within the system - Internal or Business Partner Contact
   */
  public int getAD_User_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_User_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set User/Contact.
   *
   * @param AD_User_ID User within the system - Internal or Business Partner Contact
   */
  public void setAD_User_ID(int AD_User_ID) {
    if (AD_User_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_User_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
  }

    @Override
  public int getTableId() {
    return Table_ID;
  }
}
