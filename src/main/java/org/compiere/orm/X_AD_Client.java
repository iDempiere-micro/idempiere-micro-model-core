package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_Client;
import org.idempiere.orm.I_Persistent;
import org.idempiere.orm.POInfo;

/**
 * Generated Model for AD_Client
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Client extends BasePONameValue implements I_AD_Client, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Client(Properties ctx, int AD_Client_ID, String trxName) {
    super(ctx, AD_Client_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_Client(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * AccessLevel
   *
   * @return 6 - System - Client
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  /** Load Meta Data */
  protected POInfo initPO(Properties ctx) {
    return POInfo.getPOInfo(ctx, Table_ID, get_TrxName());
  }

  public String toString() {
    return "X_AD_Client[" + getId() + "]";
  }

  /**
   * Set AD_Client_UU.
   *
   * @param AD_Client_UU AD_Client_UU
   */
  public void setADClientUU(String AD_Client_UU) {
    set_Value(COLUMNNAME_AD_Client_UU, AD_Client_UU);
  }

  /**
   * Get AD_Client_UU.
   *
   * @return AD_Client_UU
   */
  public String getADClientUU() {
    return (String) get_Value(COLUMNNAME_AD_Client_UU);
  }

  /** AD_Language AD_Reference_ID=327 */
  public static final int AD_LANGUAGE_AD_Reference_ID = 327;

  /**
   * Set Language.
   *
   * @param AD_Language Language for this entity
   */
  public void setADLanguage(String AD_Language) {

    set_Value(COLUMNNAME_AD_Language, AD_Language);
  }

  /**
   * Get Language.
   *
   * @return Language for this entity
   */
  public String getADLanguage() {
    return (String) get_Value(COLUMNNAME_AD_Language);
  }

  public org.compiere.model.I_AD_PasswordRule getADPasswordRule() throws RuntimeException {
    return (org.compiere.model.I_AD_PasswordRule)
        MTable.get(getCtx(), org.compiere.model.I_AD_PasswordRule.Table_Name)
            .getPO(getADPasswordRule_ID(), get_TrxName());
  }

  /**
   * Set Password Policies.
   *
   * @param AD_PasswordRule_ID Password Policies
   */
  public void setAD_PasswordRule_ID(int AD_PasswordRule_ID) {
    if (AD_PasswordRule_ID < 1) set_Value(COLUMNNAME_AD_PasswordRule_ID, null);
    else set_Value(COLUMNNAME_AD_PasswordRule_ID, AD_PasswordRule_ID);
  }

  /**
   * Get Password Policies.
   *
   * @return Password Policies
   */
  public int getADPasswordRule_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_PasswordRule_ID);
    if (ii == null) return 0;
    return ii;
  }

  public org.compiere.model.I_AD_ReplicationStrategy getADReplicationStrategy()
      throws RuntimeException {
    return (org.compiere.model.I_AD_ReplicationStrategy)
        MTable.get(getCtx(), org.compiere.model.I_AD_ReplicationStrategy.Table_Name)
            .getPO(getADReplicationStrategyID(), get_TrxName());
  }

  /**
   * Set Replication Strategy.
   *
   * @param AD_ReplicationStrategy_ID Data Replication Strategy
   */
  public void setADReplicationStrategyID(int AD_ReplicationStrategy_ID) {
    if (AD_ReplicationStrategy_ID < 1) set_Value(COLUMNNAME_AD_ReplicationStrategy_ID, null);
    else set_Value(COLUMNNAME_AD_ReplicationStrategy_ID, AD_ReplicationStrategy_ID);
  }

  /**
   * Get Replication Strategy.
   *
   * @return Data Replication Strategy
   */
  public int getADReplicationStrategyID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_ReplicationStrategy_ID);
    if (ii == null) return 0;
    return ii;
  }

  /** AutoArchive AD_Reference_ID=334 */
  public static final int AUTOARCHIVE_AD_Reference_ID = 334;
  /** None = N */
  public static final String AUTOARCHIVE_None = "N";
  /** All (Reports, Documents) = 1 */
  public static final String AUTOARCHIVE_AllReportsDocuments = "1";
  /** Documents = 2 */
  public static final String AUTOARCHIVE_Documents = "2";
  /** External Documents = 3 */
  public static final String AUTOARCHIVE_ExternalDocuments = "3";

  /**
   * Set Auto Archive.
   *
   * @param AutoArchive Enable and level of automatic Archive of documents
   */
  public void setAutoArchive(String AutoArchive) {

    set_Value(COLUMNNAME_AutoArchive, AutoArchive);
  }

  /**
   * Get Auto Archive.
   *
   * @return Enable and level of automatic Archive of documents
   */
  public String getAutoArchive() {
    return (String) get_Value(COLUMNNAME_AutoArchive);
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

  /**
   * Set Document Directory.
   *
   * @param DocumentDir Directory for documents from the application server
   */
  public void setDocumentDir(String DocumentDir) {
    set_Value(COLUMNNAME_DocumentDir, DocumentDir);
  }

  /**
   * Get Document Directory.
   *
   * @return Directory for documents from the application server
   */
  public String getDocumentDir() {
    return (String) get_Value(COLUMNNAME_DocumentDir);
  }

  /**
   * Set EMail Test.
   *
   * @param EMailTest Test EMail
   */
  public void setEMailTest(String EMailTest) {
    set_Value(COLUMNNAME_EMailTest, EMailTest);
  }

  /**
   * Get EMail Test.
   *
   * @return Test EMail
   */
  public String getEMailTest() {
    return (String) get_Value(COLUMNNAME_EMailTest);
  }

  /**
   * Set Multi Lingual Documents.
   *
   * @param IsMultiLingualDocument Documents are Multi Lingual
   */
  public void setIsMultiLingualDocument(boolean IsMultiLingualDocument) {
    set_Value(COLUMNNAME_IsMultiLingualDocument, IsMultiLingualDocument);
  }

  /**
   * Get Multi Lingual Documents.
   *
   * @return Documents are Multi Lingual
   */
  public boolean isMultiLingualDocument() {
    Object oo = get_Value(COLUMNNAME_IsMultiLingualDocument);
    if (oo != null) {
      if (oo instanceof Boolean) return (Boolean) oo;
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Post Immediately (Deprecated).
   *
   * @param IsPostImmediate Post the accounting immediately for testing (Deprecated)
   */
  public void setIsPostImmediate(boolean IsPostImmediate) {
    set_Value(COLUMNNAME_IsPostImmediate, IsPostImmediate);
  }

  /**
   * Get Post Immediately (Deprecated).
   *
   * @return Post the accounting immediately for testing (Deprecated)
   */
  public boolean isPostImmediate() {
    Object oo = get_Value(COLUMNNAME_IsPostImmediate);
    if (oo != null) {
      if (oo instanceof Boolean) return (Boolean) oo;
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set SMTP SSL/TLS.
   *
   * @param IsSecureSMTP Use SSL/TLS for SMTP
   */
  public void setIsSecureSMTP(boolean IsSecureSMTP) {
    set_Value(COLUMNNAME_IsSecureSMTP, IsSecureSMTP);
  }

  /**
   * Get SMTP SSL/TLS.
   *
   * @return Use SSL/TLS for SMTP
   */
  public boolean isSecureSMTP() {
    Object oo = get_Value(COLUMNNAME_IsSecureSMTP);
    if (oo != null) {
      if (oo instanceof Boolean) return (Boolean) oo;
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Server EMail.
   *
   * @param IsServerEMail Send EMail from Server
   */
  public void setIsServerEMail(boolean IsServerEMail) {
    set_Value(COLUMNNAME_IsServerEMail, IsServerEMail);
  }

  /**
   * Get Server EMail.
   *
   * @return Send EMail from Server
   */
  public boolean isServerEMail() {
    Object oo = get_Value(COLUMNNAME_IsServerEMail);
    if (oo != null) {
      if (oo instanceof Boolean) return (Boolean) oo;
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set SMTP Authentication.
   *
   * @param IsSmtpAuthorization Your mail server requires Authentication
   */
  public void setIsSmtpAuthorization(boolean IsSmtpAuthorization) {
    set_Value(COLUMNNAME_IsSmtpAuthorization, IsSmtpAuthorization);
  }

  /**
   * Get SMTP Authentication.
   *
   * @return Your mail server requires Authentication
   */
  public boolean isSmtpAuthorization() {
    Object oo = get_Value(COLUMNNAME_IsSmtpAuthorization);
    if (oo != null) {
      if (oo instanceof Boolean) return (Boolean) oo;
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set IsUseASP.
   *
   * @param IsUseASP IsUseASP
   */
  public void setIsUseASP(boolean IsUseASP) {
    set_Value(COLUMNNAME_IsUseASP, IsUseASP);
  }

  /**
   * Get IsUseASP.
   *
   * @return IsUseASP
   */
  public boolean isUseASP() {
    Object oo = get_Value(COLUMNNAME_IsUseASP);
    if (oo != null) {
      if (oo instanceof Boolean) return (Boolean) oo;
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Use Beta Functions.
   *
   * @param IsUseBetaFunctions Enable the use of Beta Functionality
   */
  public void setIsUseBetaFunctions(boolean IsUseBetaFunctions) {
    set_Value(COLUMNNAME_IsUseBetaFunctions, IsUseBetaFunctions);
  }

  /**
   * Get Use Beta Functions.
   *
   * @return Enable the use of Beta Functionality
   */
  public boolean isUseBetaFunctions() {
    Object oo = get_Value(COLUMNNAME_IsUseBetaFunctions);
    if (oo != null) {
      if (oo instanceof Boolean) return (Boolean) oo;
      return "Y".equals(oo);
    }
    return false;
  }

  /** MMPolicy AD_Reference_ID=335 */
  public static final int MMPOLICY_AD_Reference_ID = 335;
  /** LiFo = L */
  public static final String MMPOLICY_LiFo = "L";
  /** FiFo = F */
  public static final String MMPOLICY_FiFo = "F";

  /**
   * Set Material Policy.
   *
   * @param MMPolicy Material Movement Policy
   */
  public void setMMPolicy(String MMPolicy) {

    set_Value(COLUMNNAME_MMPolicy, MMPolicy);
  }

  /**
   * Get Material Policy.
   *
   * @return Material Movement Policy
   */
  public String getMMPolicy() {
    return (String) get_Value(COLUMNNAME_MMPolicy);
  }

  /**
   * Set Model Validation Classes.
   *
   * @param ModelValidationClasses List of data model validation classes separated by ;
   */
  public void setModelValidationClasses(String ModelValidationClasses) {
    set_Value(COLUMNNAME_ModelValidationClasses, ModelValidationClasses);
  }

  /**
   * Get Model Validation Classes.
   *
   * @return List of data model validation classes separated by ;
   */
  public String getModelValidationClasses() {
    return (String) get_Value(COLUMNNAME_ModelValidationClasses);
  }

  /**
   * Set Request EMail.
   *
   * @param RequestEMail EMail address to send automated mails from or receive mails for automated
   *     processing (fully qualified)
   */
  public void setRequestEMail(String RequestEMail) {
    set_Value(COLUMNNAME_RequestEMail, RequestEMail);
  }

  /**
   * Get Request EMail.
   *
   * @return EMail address to send automated mails from or receive mails for automated processing
   *     (fully qualified)
   */
  public String getRequestEMail() {
    return (String) get_Value(COLUMNNAME_RequestEMail);
  }

  /**
   * Set Request Folder.
   *
   * @param RequestFolder EMail folder to process incoming emails; if empty INBOX is used
   */
  public void setRequestFolder(String RequestFolder) {
    set_Value(COLUMNNAME_RequestFolder, RequestFolder);
  }

  /**
   * Get Request Folder.
   *
   * @return EMail folder to process incoming emails; if empty INBOX is used
   */
  public String getRequestFolder() {
    return (String) get_Value(COLUMNNAME_RequestFolder);
  }

  /**
   * Set Request User.
   *
   * @param RequestUser User Name (ID) of the email owner
   */
  public void setRequestUser(String RequestUser) {
    set_Value(COLUMNNAME_RequestUser, RequestUser);
  }

  /**
   * Get Request User.
   *
   * @return User Name (ID) of the email owner
   */
  public String getRequestUser() {
    return (String) get_Value(COLUMNNAME_RequestUser);
  }

  /**
   * Set Request User Password.
   *
   * @param RequestUserPW Password of the user name (ID) for mail processing
   */
  public void setRequestUserPW(String RequestUserPW) {
    set_Value(COLUMNNAME_RequestUserPW, RequestUserPW);
  }

  /**
   * Get Request User Password.
   *
   * @return Password of the user name (ID) for mail processing
   */
  public String getRequestUserPW() {
    return (String) get_Value(COLUMNNAME_RequestUserPW);
  }

  /**
   * Set Mail Host.
   *
   * @param SMTPHost Hostname of Mail Server for SMTP and IMAP
   */
  public void setSMTPHost(String SMTPHost) {
    set_Value(COLUMNNAME_SMTPHost, SMTPHost);
  }

  /**
   * Get Mail Host.
   *
   * @return Hostname of Mail Server for SMTP and IMAP
   */
  public String getSMTPHost() {
    return (String) get_Value(COLUMNNAME_SMTPHost);
  }

  /**
   * Set SMTP Port.
   *
   * @param SMTPPort SMTP Port Number
   */
  public void setSMTPPort(int SMTPPort) {
    set_Value(COLUMNNAME_SMTPPort, SMTPPort);
  }

  /**
   * Get SMTP Port.
   *
   * @return SMTP Port Number
   */
  public int getSMTPPort() {
    Integer ii = (Integer) get_Value(COLUMNNAME_SMTPPort);
    if (ii == null) return 0;
    return ii;
  }
}
