package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for AD_CtxHelpMsg
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
public interface I_AD_CtxHelpMsg {

  /** TableName=AD_CtxHelpMsg */
  public static final String Table_Name = "AD_CtxHelpMsg";

  /** AD_Table_ID=200064 */
  public static final int Table_ID = 200064;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 7 - System - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(7);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_CtxHelp_ID */
  public static final String COLUMNNAME_AD_CtxHelp_ID = "AD_CtxHelp_ID";

  /** Set Context Help */
  public void setAD_CtxHelp_ID(int AD_CtxHelp_ID);

  /** Get Context Help */
  public int getAD_CtxHelp_ID();

  public I_AD_CtxHelp getAD_CtxHelp() throws RuntimeException;

  /** Column name AD_CtxHelpMsg_ID */
  public static final String COLUMNNAME_AD_CtxHelpMsg_ID = "AD_CtxHelpMsg_ID";

  /** Set Context Help Message */
  public void setAD_CtxHelpMsg_ID(int AD_CtxHelpMsg_ID);

  /** Get Context Help Message */
  public int getAD_CtxHelpMsg_ID();

  /** Column name AD_CtxHelpMsg_UU */
  public static final String COLUMNNAME_AD_CtxHelpMsg_UU = "AD_CtxHelpMsg_UU";

  /** Set AD_CtxHelpMsg_UU */
  public void setAD_CtxHelpMsg_UU(String AD_CtxHelpMsg_UU);

  /** Get AD_CtxHelpMsg_UU */
  public String getAD_CtxHelpMsg_UU();

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

  /** Column name MsgText */
  public static final String COLUMNNAME_MsgText = "MsgText";

  /** Set Message Text. Textual Informational, Menu or Error Message */
  public void setMsgText(String MsgText);

  /** Get Message Text. Textual Informational, Menu or Error Message */
  public String getMsgText();

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();
}
