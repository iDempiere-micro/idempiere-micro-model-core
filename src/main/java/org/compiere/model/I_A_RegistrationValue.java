package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for A_RegistrationValue
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_A_RegistrationValue {

  /** TableName=A_RegistrationValue */
  public static final String Table_Name = "A_RegistrationValue";

  /** AD_Table_ID=653 */
  public static final int Table_ID = 653;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 3 - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(3);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name A_RegistrationAttribute_ID */
  public static final String COLUMNNAME_A_RegistrationAttribute_ID = "A_RegistrationAttribute_ID";

  /** Set Registration Attribute. Asset Registration Attribute */
  public void setA_RegistrationAttribute_ID(int A_RegistrationAttribute_ID);

  /** Get Registration Attribute. Asset Registration Attribute */
  public int getA_RegistrationAttribute_ID();

  public I_A_RegistrationAttribute getA_RegistrationAttribute() throws RuntimeException;

  /** Column name A_Registration_ID */
  public static final String COLUMNNAME_A_Registration_ID = "A_Registration_ID";

  /** Set Registration. User Asset Registration */
  public void setA_Registration_ID(int A_Registration_ID);

  /** Get Registration. User Asset Registration */
  public int getA_Registration_ID();

  public I_A_Registration getA_Registration() throws RuntimeException;

  /** Column name A_RegistrationValue_UU */
  public static final String COLUMNNAME_A_RegistrationValue_UU = "A_RegistrationValue_UU";

  /** Set A_RegistrationValue_UU */
  public void setA_RegistrationValue_UU(String A_RegistrationValue_UU);

  /** Get A_RegistrationValue_UU */
  public String getA_RegistrationValue_UU();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name Description */
  public static final String COLUMNNAME_Description = "Description";

  /** Set Description. Optional short description of the record */
  public void setDescription(String Description);

  /** Get Description. Optional short description of the record */
  public String getDescription();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
