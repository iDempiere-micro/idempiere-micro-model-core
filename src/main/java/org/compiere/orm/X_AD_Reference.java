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
   * Get Validation type.
   *
   * @return Different method of validating data
   */
  public String getValidationType() {
    return (String) get_Value(COLUMNNAME_ValidationType);
  }

    @Override
  public int getTableId() {
    return Table_ID;
  }
}
