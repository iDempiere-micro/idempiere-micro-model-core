package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_Reference;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Reference
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Reference extends BasePOName implements I_AD_Reference, I_Persistent {

  /** EntityType AD_Reference_ID=389 */
  public static final int ENTITYTYPE_AD_Reference_ID = 389;
  /** ValidationType AD_Reference_ID=2 */
  public static final int VALIDATIONTYPE_AD_Reference_ID = 2;
  /** List Validation = L */
  public static final String VALIDATIONTYPE_ListValidation = "L";
  /** DataType = D */
  public static final String VALIDATIONTYPE_DataType = "D";
  /** Table Validation = T */
  public static final String VALIDATIONTYPE_TableValidation = "T";
  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Reference(Properties ctx, int AD_Reference_ID, String trxName) {
    super(ctx, AD_Reference_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_Reference(Properties ctx, ResultSet rs, String trxName) {
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

  public String toString() {
    StringBuffer sb = new StringBuffer("X_AD_Reference[").append(getId()).append("]");
    return sb.toString();
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
   * Set Reference.
   *
   * @param AD_Reference_ID System Reference and Validation
   */
  public void setAD_Reference_ID(int AD_Reference_ID) {
    if (AD_Reference_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Reference_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Reference_ID, Integer.valueOf(AD_Reference_ID));
  }

  /**
   * Get AD_Reference_UU.
   *
   * @return AD_Reference_UU
   */
  public String getAD_Reference_UU() {
    return (String) get_Value(COLUMNNAME_AD_Reference_UU);
  }

  /**
   * Set AD_Reference_UU.
   *
   * @param AD_Reference_UU AD_Reference_UU
   */
  public void setAD_Reference_UU(String AD_Reference_UU) {
    set_Value(COLUMNNAME_AD_Reference_UU, AD_Reference_UU);
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
   * Set Description.
   *
   * @param Description Optional short description of the record
   */
  public void setDescription(String Description) {
    set_Value(COLUMNNAME_Description, Description);
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
   * Set Entity Type.
   *
   * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
   */
  public void setEntityType(String EntityType) {

    set_Value(COLUMNNAME_EntityType, EntityType);
  }

  /**
   * Get Comment/Help.
   *
   * @return Comment or Hint
   */
  public String getHelp() {
    return (String) get_Value(COLUMNNAME_Help);
  }

  /**
   * Set Comment/Help.
   *
   * @param Help Comment or Hint
   */
  public void setHelp(String Help) {
    set_Value(COLUMNNAME_Help, Help);
  }

  /**
   * Set Order By Value.
   *
   * @param IsOrderByValue Order list using the value column instead of the name column
   */
  public void setIsOrderByValue(boolean IsOrderByValue) {
    set_Value(COLUMNNAME_IsOrderByValue, Boolean.valueOf(IsOrderByValue));
  }

  /**
   * Get Order By Value.
   *
   * @return Order list using the value column instead of the name column
   */
  public boolean isOrderByValue() {
    Object oo = get_Value(COLUMNNAME_IsOrderByValue);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Get Validation type.
   *
   * @return Different method of validating data
   */
  public String getValidationType() {
    return (String) get_Value(COLUMNNAME_ValidationType);
  }

  /**
   * Set Validation type.
   *
   * @param ValidationType Different method of validating data
   */
  public void setValidationType(String ValidationType) {

    set_Value(COLUMNNAME_ValidationType, ValidationType);
  }

  /**
   * Get Value Format.
   *
   * @return Format of the value; Can contain fixed format elements, Variables: "_lLoOaAcCa09"
   */
  public String getVFormat() {
    return (String) get_Value(COLUMNNAME_VFormat);
  }

  /**
   * Set Value Format.
   *
   * @param VFormat Format of the value; Can contain fixed format elements, Variables:
   *     "_lLoOaAcCa09"
   */
  public void setVFormat(String VFormat) {
    set_Value(COLUMNNAME_VFormat, VFormat);
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
