package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for W_Basket
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_W_Basket {

  /** TableName=W_Basket */
  public static final String Table_Name = "W_Basket";

  /** AD_Table_ID=402 */
  public static final int Table_ID = 402;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 4 - System */
  BigDecimal accessLevel = BigDecimal.valueOf(4);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name AD_User_ID */
  public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

  /** Set User/Contact. User within the system - Internal or Business Partner Contact */
  public void setAD_User_ID(int AD_User_ID);

  /** Get User/Contact. User within the system - Internal or Business Partner Contact */
  public int getAD_User_ID();

  public I_AD_User getAD_User() throws RuntimeException;

  /** Column name C_BPartner_ID */
  public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

  /** Set Business Partner . Identifies a Business Partner */
  public void setC_BPartner_ID(int C_BPartner_ID);

  /** Get Business Partner . Identifies a Business Partner */
  public int getC_BPartner_ID();

  public I_C_BPartner getC_BPartner() throws RuntimeException;

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name EMail */
  public static final String COLUMNNAME_EMail = "EMail";

  /** Set EMail Address. Electronic Mail Address */
  public void setEMail(String EMail);

  /** Get EMail Address. Electronic Mail Address */
  public String getEMail();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name M_PriceList_ID */
  public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

  /** Set Price List. Unique identifier of a Price List */
  public void setM_PriceList_ID(int M_PriceList_ID);

  /** Get Price List. Unique identifier of a Price List */
  public int getM_PriceList_ID();

  public I_M_PriceList getM_PriceList() throws RuntimeException;

  /** Column name Session_ID */
  public static final String COLUMNNAME_Session_ID = "Session_ID";

  /** Set Session ID */
  public void setSession_ID(int Session_ID);

  /** Get Session ID */
  public int getSession_ID();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name W_Basket_ID */
  public static final String COLUMNNAME_W_Basket_ID = "W_Basket_ID";

  /** Set Basket. Web Basket */
  public void setW_Basket_ID(int W_Basket_ID);

  /** Get Basket. Web Basket */
  public int getW_Basket_ID();

  /** Column name W_Basket_UU */
  public static final String COLUMNNAME_W_Basket_UU = "W_Basket_UU";

  /** Set W_Basket_UU */
  public void setW_Basket_UU(String W_Basket_UU);

  /** Get W_Basket_UU */
  public String getW_Basket_UU();
}
