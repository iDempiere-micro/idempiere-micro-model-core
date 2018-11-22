package org.compiere.orm;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.I_AD_Ref_List;
import org.idempiere.orm.I_Persistent;
import org.idempiere.orm.POInfo;

/**
 * Generated Model for AD_Ref_List
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Ref_List extends BasePONameValue implements I_AD_Ref_List, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Ref_List(Properties ctx, int AD_Ref_List_ID, String trxName) {
    super(ctx, AD_Ref_List_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_Ref_List(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * AccessLevel
   *
   * @return 4 - System
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  /** Load Meta Data */
  protected POInfo initPO(Properties ctx) {
    POInfo poi = POInfo.getPOInfo(ctx, Table_ID, get_TrxName());
    return poi;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("X_AD_Ref_List[").append(getId()).append("]");
    return sb.toString();
  }

  public org.compiere.model.I_AD_Reference getAD_Reference() throws RuntimeException {
    return (org.compiere.model.I_AD_Reference)
        MTable.get(getCtx(), org.compiere.model.I_AD_Reference.Table_Name)
            .getPO(getAD_Reference_ID(), get_TrxName());
  }

  /**
   * Set Reference.
   *
   * @param AD_Reference_ID System Reference and Validation
   */
  public void setAD_Reference_ID(int AD_Reference_ID) {
    if (AD_Reference_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Reference_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Reference_ID, Integer.valueOf(AD_Reference_ID));
  }

  /**
   * Get Reference.
   *
   * @return System Reference and Validation
   */
  public int getAD_Reference_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Reference_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Reference List.
   *
   * @param AD_Ref_List_ID Reference List based on Table
   */
  public void setAD_Ref_List_ID(int AD_Ref_List_ID) {
    if (AD_Ref_List_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Ref_List_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Ref_List_ID, Integer.valueOf(AD_Ref_List_ID));
  }

  /**
   * Get Reference List.
   *
   * @return Reference List based on Table
   */
  public int getAD_Ref_List_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Ref_List_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set AD_Ref_List_UU.
   *
   * @param AD_Ref_List_UU AD_Ref_List_UU
   */
  public void setAD_Ref_List_UU(String AD_Ref_List_UU) {
    set_Value(COLUMNNAME_AD_Ref_List_UU, AD_Ref_List_UU);
  }

  /**
   * Get AD_Ref_List_UU.
   *
   * @return AD_Ref_List_UU
   */
  public String getAD_Ref_List_UU() {
    return (String) get_Value(COLUMNNAME_AD_Ref_List_UU);
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

  /** EntityType AD_Reference_ID=389 */
  public static final int ENTITYTYPE_AD_Reference_ID = 389;
  /**
   * Set Entity Type.
   *
   * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
   */
  public void setEntityType(String EntityType) {

    set_Value(COLUMNNAME_EntityType, EntityType);
  }

  /**
   * Get Entity Type.
   *
   * @return Dictionary Entity Type; Determines ownership and synchronization
   */
  public String getEntityType() {
    return (String) get_Value(COLUMNNAME_EntityType);
  }

  /**
   * Set Valid from.
   *
   * @param ValidFrom Valid from including this date (first day)
   */
  public void setValidFrom(Timestamp ValidFrom) {
    set_Value(COLUMNNAME_ValidFrom, ValidFrom);
  }

  /**
   * Get Valid from.
   *
   * @return Valid from including this date (first day)
   */
  public Timestamp getValidFrom() {
    return (Timestamp) get_Value(COLUMNNAME_ValidFrom);
  }

  /**
   * Set Valid to.
   *
   * @param ValidTo Valid to including this date (last day)
   */
  public void setValidTo(Timestamp ValidTo) {
    set_Value(COLUMNNAME_ValidTo, ValidTo);
  }

  /**
   * Get Valid to.
   *
   * @return Valid to including this date (last day)
   */
  public Timestamp getValidTo() {
    return (Timestamp) get_Value(COLUMNNAME_ValidTo);
  }
}
