package org.compiere.orm;

import kotliquery.Row;

/**
 * @author Jorg Janke
 * @version $Id: MTableAccess.java,v 1.3 2006/07/30 00:58:38 jjanke Exp $
 */
public class MTableAccess extends X_AD_Table_Access {
    /**
     *
     */
    private static final long serialVersionUID = -3747261579266442904L;

    /**
     * Persistency Constructor
     *
     * @param ctx     context
     * @param ignored ignored
     * @param trxName transaction
     */
    public MTableAccess(int ignored) {
        super(0);
        if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
    } //	MTableAccess

    /**
     * Load Constructor
     *
     * @param ctx context
     */
    public MTableAccess(Row row) {
        super(row);
    } //	MTableAccess

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("MTableAccess[");
        sb.append("AD_Role_ID=")
                .append(getRoleId())
                .append(",AD_Table_ID=")
                .append(getAccessTableId())
                .append(",Exclude=")
                .append(isExclude())
                .append(",Type=")
                .append(getAccessTypeRule());
        if (X_AD_Table_Access.ACCESSTYPERULE_Accessing.equals(getAccessTypeRule()))
            sb.append("-ReadOnly=").append(isReadOnly());
        else if (X_AD_Table_Access.ACCESSTYPERULE_Exporting.equals(getAccessTypeRule()))
            sb.append("-CanExport=").append(!isExclude());
        else if (X_AD_Table_Access.ACCESSTYPERULE_Reporting.equals(getAccessTypeRule()))
            sb.append("-CanReport=").append(!isExclude());
        sb.append("]");
        return sb.toString();
    } //	toString

} //	MTableAccess
