package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.StorageProvider;

/**
 * Generated Model for AD_StorageProvider
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_StorageProvider extends PO implements StorageProvider {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_StorageProvider(int AD_StorageProvider_ID) {
        super(AD_StorageProvider_ID);
        /** if (AD_StorageProvider_ID == 0) { setAD_StorageProvider_ID (0); setName (null); } */
    }

    /**
     * Load Constructor
     */
    public X_AD_StorageProvider(Row row) {
        super(row);
    }

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_AD_StorageProvider[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Method.
     *
     * @return Method
     */
    public String getMethod() {
        return (String) getValue(COLUMNNAME_Method);
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
