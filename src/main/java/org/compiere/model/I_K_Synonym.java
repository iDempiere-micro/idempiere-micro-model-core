package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for K_Synonym
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_K_Synonym {

  /** TableName=K_Synonym */
  public static final String Table_Name = "K_Synonym";

  /** AD_Table_ID=608 */
  public static final int Table_ID = 608;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 3 - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(3);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Language */
  public static final String COLUMNNAME_AD_Language = "AD_Language";

  /** Set Language. Language for this entity */
  public void setADLanguage(String AD_Language);

  /** Get Language. Language for this entity */
  public String getADLanguage();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name K_Synonym_ID */
  public static final String COLUMNNAME_K_Synonym_ID = "K_Synonym_ID";

  /** Set Knowledge Synonym. Knowledge Keyword Synonym */
  public void setK_Synonym_ID(int K_Synonym_ID);

  /** Get Knowledge Synonym. Knowledge Keyword Synonym */
  public int getK_Synonym_ID();

  /** Column name K_Synonym_UU */
  public static final String COLUMNNAME_K_Synonym_UU = "K_Synonym_UU";

  /** Set K_Synonym_UU */
  public void setK_Synonym_UU(String K_Synonym_UU);

  /** Get K_Synonym_UU */
  public String getK_Synonym_UU();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name SynonymName */
  public static final String COLUMNNAME_SynonymName = "SynonymName";

  /** Set Synonym Name. The synonym for the name */
  public void setSynonymName(String SynonymName);

  /** Get Synonym Name. The synonym for the name */
  public String getSynonymName();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
