package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.Client;

/**
 * Generated Model for AD_Client
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Client extends BasePONameValue implements Client {
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Client(int AD_Client_ID) {
        super(AD_Client_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Client(Row row) {
        super(row);
    } //	MClient

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_AD_Client[" + getId() + "]";
    }

    /**
     * Get Language.
     *
     * @return Language for this entity
     */
    public String getADLanguage() {
        return getValue(COLUMNNAME_AD_Language);
    }

    /**
     * Set Language.
     *
     * @param AD_Language Language for this entity
     */
    public void setADLanguage(String AD_Language) {

        setValue(COLUMNNAME_AD_Language, AD_Language);
    }

    /**
     * Set Auto Archive.
     *
     * @param AutoArchive Enable and level of automatic Archive of documents
     */
    public void setAutoArchive(String AutoArchive) {

        setValue(COLUMNNAME_AutoArchive, AutoArchive);
    }

    /**
     * Set Multi Lingual Documents.
     *
     * @param IsMultiLingualDocument Documents are Multi Lingual
     */
    public void setIsMultiLingualDocument(boolean IsMultiLingualDocument) {
        setValue(COLUMNNAME_IsMultiLingualDocument, IsMultiLingualDocument);
    }

    /**
     * Get Multi Lingual Documents.
     *
     * @return Documents are Multi Lingual
     */
    public boolean isMultiLingualDocument() {
        Object oo = getValue(COLUMNNAME_IsMultiLingualDocument);
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
        setValue(COLUMNNAME_IsPostImmediate, IsPostImmediate);
    }

    /**
     * Get SMTP SSL/TLS.
     *
     * @return Use SSL/TLS for SMTP
     */
    public boolean isSecureSMTP() {
        Object oo = getValue(COLUMNNAME_IsSecureSMTP);
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
        setValue(COLUMNNAME_IsServerEMail, IsServerEMail);
    }

    /**
     * Set SMTP Authentication.
     *
     * @param IsSmtpAuthorization Your mail server requires Authentication
     */
    public void setIsSmtpAuthorization(boolean IsSmtpAuthorization) {
        setValue(COLUMNNAME_IsSmtpAuthorization, IsSmtpAuthorization);
    }

    /**
     * Get SMTP Authentication.
     *
     * @return Your mail server requires Authentication
     */
    public boolean isSmtpAuthorization() {
        Object oo = getValue(COLUMNNAME_IsSmtpAuthorization);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get IsUseASP.
     *
     * @return IsUseASP
     */
    public boolean isUseASP() {
        Object oo = getValue(COLUMNNAME_IsUseASP);
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
        setValue(COLUMNNAME_IsUseBetaFunctions, IsUseBetaFunctions);
    }

    /**
     * Get Use Beta Functions.
     *
     * @return Enable the use of Beta Functionality
     */
    public boolean isUseBetaFunctions() {
        Object oo = getValue(COLUMNNAME_IsUseBetaFunctions);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Material Policy.
     *
     * @return Material Movement Policy
     */
    public String getMMPolicy() {
        return getValue(COLUMNNAME_MMPolicy);
    }

    /**
     * Set Material Policy.
     *
     * @param MMPolicy Material Movement Policy
     */
    public void setMMPolicy(String MMPolicy) {

        setValue(COLUMNNAME_MMPolicy, MMPolicy);
    }

    /**
     * Get Model Validation Classes.
     *
     * @return List of data model validation classes separated by ;
     */
    public String getModelValidationClasses() {
        return getValue(COLUMNNAME_ModelValidationClasses);
    }

    /**
     * Get Request EMail.
     *
     * @return EMail address to send automated mails from or receive mails for automated processing
     * (fully qualified)
     */
    public String getRequestEMail() {
        return getValue(COLUMNNAME_RequestEMail);
    }

    /**
     * Get Request User.
     *
     * @return User Name (ID) of the email owner
     */
    public String getRequestUser() {
        return getValue(COLUMNNAME_RequestUser);
    }

    /**
     * Get Request User Password.
     *
     * @return Password of the user name (ID) for mail processing
     */
    public String getRequestUserPW() {
        return getValue(COLUMNNAME_RequestUserPW);
    }

    /**
     * Get Mail Host.
     *
     * @return Hostname of Mail Server for SMTP and IMAP
     */
    public String getSMTPHost() {
        return getValue(COLUMNNAME_SMTPHost);
    }

    /**
     * Get SMTP Port.
     *
     * @return SMTP Port Number
     */
    public int getSMTPPort() {
        Integer ii = getValue(COLUMNNAME_SMTPPort);
        if (ii == null) return 0;
        return ii;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
