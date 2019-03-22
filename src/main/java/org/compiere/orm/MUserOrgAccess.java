package org.compiere.orm;

import kotliquery.Row;
import org.idempiere.common.util.CLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.prepareStatement;

/**
 * User Org Access
 *
 * @author Jorg Janke
 * @version $Id: MUserOrgAccess.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MUserOrgAccess extends X_AD_User_OrgAccess {
    /**
     *
     */
    private static final long serialVersionUID = 11601583764711895L;

    private String m_clientName;

    /**
     * ************************************************************************ Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MUserOrgAccess(Properties ctx, Row row) {
        super(ctx, row);
    } //	MUserOrgAccess

    /**
     * Persistency Constructor
     *
     * @param ctx     context
     * @param ignored ignored
     * @param trxName transaction
     */
    public MUserOrgAccess(Properties ctx, int ignored) {
        super(ctx, 0);
        if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
        setIsReadOnly(false);
    } //	MUserOrgAccess

    /**
     * Organization Constructor
     *
     * @param org        org
     * @param AD_User_ID role
     */
    public MUserOrgAccess(MOrg org, int AD_User_ID) {
        this(org.getCtx(), 0);
        setClientOrg(org);
        setUserId(AD_User_ID);
    } //	MUserOrgAccess

    /**
     * User Constructor param user user param AD_Org_ID org
     *
     * <p>public MUserOrgAccess (MUser user, int AD_Org_ID) { this (user.getCtx(), 0, user.null);
     * setClientOrg (user.getClientId(), AD_Org_ID); setUserId (user.getUserId()); } //
     * MUserOrgAccess
     *
     * <p>/** String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("MUserOrgAccess[");
        sb.append("AD_User_ID=")
                .append(getUserId())
                .append(",AD_Client_ID=")
                .append(getClientId())
                .append(",AD_Org_ID=")
                .append(getOrgId())
                .append(",RO=")
                .append(isReadOnly());
        sb.append("]");
        return sb.toString();
    } //	toString

    /**
     * Get Client Name
     *
     * @return name
     */
    public String getClientName() {
        if (m_clientName == null) {
            String sql =
                    "SELECT c.Name, o.Name "
                            + "FROM AD_Client c INNER JOIN AD_Org o ON (c.AD_Client_ID=o.AD_Client_ID) "
                            + "WHERE o.AD_Org_ID=?";
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                pstmt = prepareStatement(sql);
                pstmt.setInt(1, getOrgId());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    m_clientName = rs.getString(1);
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, sql, e);
            } finally {
                rs = null;
                pstmt = null;
            }
        }
        return m_clientName;
    } //	getClientName

} //	MUserOrgAccess
