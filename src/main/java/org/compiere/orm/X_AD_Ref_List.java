package org.compiere.orm;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.I_AD_Ref_List;
import org.idempiere.orm.I_Persistent;

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

  public String toString() {
    StringBuffer sb = new StringBuffer("X_AD_Ref_List[").append(getId()).append("]");
    return sb.toString();
  }

    /**
   * Get Reference.
   *
   * @return System Reference and Validation
   */
  public int getReferenceId() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Reference_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Set Entity Type.
   *
   * @param EntityType Dictionary Entity Type; Determines ownership and synchronization
   */
  public void setEntityType(String EntityType) {

    set_Value(COLUMNNAME_EntityType, EntityType);
  }

    @Override
  public int getTableId() {
    return Table_ID;
  }
}
