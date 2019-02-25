package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Client;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Client
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Client extends BasePONameValue implements I_AD_Client {

    /**
     * None = N
     */
    public static final String AUTOARCHIVE_None = "N";
    /**
     * FiFo = F
     */
    public static final String MMPOLICY_FiFo = "F";
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Client(Properties ctx, int AD_Client_ID) {
        super(ctx, AD_Client_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Client(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_AD_Client(Properties ctx, Row row) {
        super(ctx, row);
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
        return (String) getValue(COLUMNNAME_AD_Language);
    }

    /**
     * Set Language.
     *
     * @param AD_Language Language for this entity
     */
    public void setADLanguage(String AD_Language) {

        set_Value(COLUMNNAME_AD_Language, AD_Language);
    }

    /**
     * Set Auto Archive.
     *
     * @param AutoArchive Enable and level of automatic Archive of documents
     */
    public void setAutoArchive(String AutoArchive) {

        set_Value(COLUMNNAME_AutoArchive, AutoArchive);
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
        set_Value(COLUMNNAME_IsPostImmediate, IsPostImmediate);
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
        set_Value(COLUMNNAME_IsServerEMail, IsServerEMail);
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
        set_Value(COLUMNNAME_IsUseBetaFunctions, IsUseBetaFunctions);
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
        return (String) getValue(COLUMNNAME_MMPolicy);
    }

    /**
     * Set Material Policy.
     *
     * @param MMPolicy Material Movement Policy
     */
    public void setMMPolicy(String MMPolicy) {

        set_Value(COLUMNNAME_MMPolicy, MMPolicy);
    }

    /**
     * Get Model Validation Classes.
     *
     * @return List of data model validation classes separated by ;
     */
    public String getModelValidationClasses() {
        return (String) getValue(COLUMNNAME_ModelValidationClasses);
    }

    /**
     * Get Request EMail.
     *
     * @return EMail address to send automated mails from or receive mails for automated processing
     * (fully qualified)
     */
    public String getRequestEMail() {
        return (String) getValue(COLUMNNAME_RequestEMail);
    }

    /**
     * Get Request User.
     *
     * @return User Name (ID) of the email owner
     */
    public String getRequestUser() {
        return (String) getValue(COLUMNNAME_RequestUser);
    }

    /**
     * Get Request User Password.
     *
     * @return Password of the user name (ID) for mail processing
     */
    public String getRequestUserPW() {
        return (String) getValue(COLUMNNAME_RequestUserPW);
    }

    /**
     * Get Mail Host.
     *
     * @return Hostname of Mail Server for SMTP and IMAP
     */
    public String getSMTPHost() {
        return (String) getValue(COLUMNNAME_SMTPHost);
    }

    /**
     * Get SMTP Port.
     *
     * @return SMTP Port Number
     */
    public int getSMTPPort() {
        Integer ii = (Integer) getValue(COLUMNNAME_SMTPPort);
        if (ii == null) return 0;
        return ii;
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
