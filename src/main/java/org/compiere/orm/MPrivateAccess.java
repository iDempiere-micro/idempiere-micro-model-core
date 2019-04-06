package org.compiere.orm;

import kotliquery.Row;

/**
 * Private Access
 *
 * @author Jorg Janke
 * @version $Id: MPrivateAccess.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
public class MPrivateAccess extends X_AD_Private_Access {
    /**
     *
     */
    private static final long serialVersionUID = -5649529789751432279L;

    /**
     * Persistency Constructor
     *
     * @param ignored ignored
     */
    public MPrivateAccess(int ignored) {
        super(0);
        if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
    } //	MPrivateAccess

    /**
     * Load Constructor
     */
    public MPrivateAccess(Row row) {
        super(row);
    } //	MPrivateAccess

    /**
     * New Constructor
     *
     * @param AD_User_ID  user
     * @param AD_Table_ID table
     * @param Record_ID   record
     */
    public MPrivateAccess(int AD_User_ID, int AD_Table_ID, int Record_ID) {
        super(0);
        setUserId(AD_User_ID);
        setTableId(AD_Table_ID);
        setRecordId(Record_ID);
    } //	MPrivateAccess

    /**
     * Get Where Clause of Locked Records for Table
     *
     * @param AD_Table_ID table
     * @param AD_User_ID  user requesting info
     * @return "<>1" or " NOT IN (1,2)" or null
     */
    public static String getLockedRecordWhere(int AD_Table_ID, int AD_User_ID) {
        String whereClause =
                " NOT IN ( SELECT Record_ID FROM AD_Private_Access WHERE AD_Table_ID = "
                        + AD_Table_ID
                        + " AND AD_User_ID <> "
                        + AD_User_ID
                        + " AND IsActive = 'Y' )";
        return whereClause;
    } //	get
} //	MPrivateAccess
