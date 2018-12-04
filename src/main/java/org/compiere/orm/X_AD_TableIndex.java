package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_TableIndex;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_TableIndex
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_TableIndex extends BasePOName implements I_AD_TableIndex, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_TableIndex(Properties ctx, int AD_TableIndex_ID, String trxName) {
    super(ctx, AD_TableIndex_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_TableIndex(Properties ctx, ResultSet rs, String trxName) {
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
    StringBuffer sb = new StringBuffer("X_AD_TableIndex[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Set Message.
   *
   * @param AD_Message_ID System Message
   */
  public void setAD_Message_ID(int AD_Message_ID) {
    if (AD_Message_ID < 1) set_Value(COLUMNNAME_AD_Message_ID, null);
    else set_Value(COLUMNNAME_AD_Message_ID, Integer.valueOf(AD_Message_ID));
  }

  /**
   * Get Message.
   *
   * @return System Message
   */
  public int getAD_Message_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Message_ID);
    if (ii == null) return 0;
    return ii;
  }

  public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException {
    return (org.compiere.model.I_AD_Table)
        MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
            .getPO(getAD_Table_ID(), get_TrxName());
  }

  /**
   * Set Table.
   *
   * @param AD_Table_ID Database Table information
   */
  public void setAD_Table_ID(int AD_Table_ID) {
    if (AD_Table_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Table_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
  }

  /**
   * Get Table.
   *
   * @return Database Table information
   */
  public int getAD_Table_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Table_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Table Index.
   *
   * @param AD_TableIndex_ID Table Index
   */
  public void setAD_TableIndex_ID(int AD_TableIndex_ID) {
    if (AD_TableIndex_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_TableIndex_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_TableIndex_ID, Integer.valueOf(AD_TableIndex_ID));
  }

  /**
   * Get Table Index.
   *
   * @return Table Index
   */
  public int getAD_TableIndex_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_TableIndex_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set AD_TableIndex_UU.
   *
   * @param AD_TableIndex_UU AD_TableIndex_UU
   */
  public void setAD_TableIndex_UU(String AD_TableIndex_UU) {
    set_Value(COLUMNNAME_AD_TableIndex_UU, AD_TableIndex_UU);
  }

  /**
   * Get AD_TableIndex_UU.
   *
   * @return AD_TableIndex_UU
   */
  public String getAD_TableIndex_UU() {
    return (String) get_Value(COLUMNNAME_AD_TableIndex_UU);
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
   * Set Comment/Help.
   *
   * @param Help Comment or Hint
   */
  public void setHelp(String Help) {
    set_Value(COLUMNNAME_Help, Help);
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
   * Set Create Constraint.
   *
   * @param IsCreateConstraint Create Constraint
   */
  public void setIsCreateConstraint(boolean IsCreateConstraint) {
    set_Value(COLUMNNAME_IsCreateConstraint, Boolean.valueOf(IsCreateConstraint));
  }

  /**
   * Get Create Constraint.
   *
   * @return Create Constraint
   */
  public boolean isCreateConstraint() {
    Object oo = get_Value(COLUMNNAME_IsCreateConstraint);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Key column.
   *
   * @param IsKey This column is the key in this table
   */
  public void setIsKey(boolean IsKey) {
    set_Value(COLUMNNAME_IsKey, Boolean.valueOf(IsKey));
  }

  /**
   * Get Key column.
   *
   * @return This column is the key in this table
   */
  public boolean isKey() {
    Object oo = get_Value(COLUMNNAME_IsKey);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Unique.
   *
   * @param IsUnique Unique
   */
  public void setIsUnique(boolean IsUnique) {
    set_Value(COLUMNNAME_IsUnique, Boolean.valueOf(IsUnique));
  }

  /**
   * Get Unique.
   *
   * @return Unique
   */
  public boolean isUnique() {
    Object oo = get_Value(COLUMNNAME_IsUnique);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Process Now.
   *
   * @param Processing Process Now
   */
  public void setProcessing(boolean Processing) {
    set_Value(COLUMNNAME_Processing, Boolean.valueOf(Processing));
  }

  /**
   * Get Process Now.
   *
   * @return Process Now
   */
  public boolean isProcessing() {
    Object oo = get_Value(COLUMNNAME_Processing);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Drop table index.
   *
   * @param TableIndexDrop Drop table index
   */
  public void setTableIndexDrop(String TableIndexDrop) {
    set_Value(COLUMNNAME_TableIndexDrop, TableIndexDrop);
  }

  /**
   * Get Drop table index.
   *
   * @return Drop table index
   */
  public String getTableIndexDrop() {
    return (String) get_Value(COLUMNNAME_TableIndexDrop);
  }

  @Override
  public int getTableId() {
    return Table_ID;
  }
}
