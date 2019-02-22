package org.compiere.orm;

import org.compiere.model.HasName;
import org.compiere.model.I_AD_StorageProvider;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_StorageProvider
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_StorageProvider extends PO implements I_AD_StorageProvider, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_StorageProvider(Properties ctx, int AD_StorageProvider_ID) {
        super(ctx, AD_StorageProvider_ID);
        /** if (AD_StorageProvider_ID == 0) { setAD_StorageProvider_ID (0); setName (null); } */
    }

    /**
     * Load Constructor
     */
    public X_AD_StorageProvider(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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
        return (String) get_Value(COLUMNNAME_Method);
    }

    /**
     * Get Name.
     *
     * @return Alphanumeric identifier of the entity
     */
    public String getName() {
        return (String) get_Value(HasName.Companion.getCOLUMNNAME_Name());
    }

    @Override
    public int getTableId() {
        return Table_ID;
    }
}
