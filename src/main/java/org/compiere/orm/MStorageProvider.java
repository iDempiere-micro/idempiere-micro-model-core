package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.IArchiveStore;
import org.compiere.model.IAttachmentStore;
import org.compiere.model.I_AD_StorageProvider;
import org.idempiere.common.base.Service;
import org.idempiere.common.base.ServiceQuery;
import org.idempiere.common.exceptions.AdempiereException;

public class MStorageProvider extends X_AD_StorageProvider implements I_AD_StorageProvider {
    /**
     *
     */
    private static final long serialVersionUID = -5889682671195395536L;

    public MStorageProvider(Properties ctx, int AD_StorageProvider_ID, String trxName) {
        super(ctx, AD_StorageProvider_ID, trxName);
    }

    public MStorageProvider(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public IAttachmentStore getAttachmentStore() {
        ServiceQuery query = new ServiceQuery();
        String method = this.getMethod();
        if (method == null) method = "DB";
        query.put("method", method);
        IAttachmentStore store =
                Service.Companion.locator().locate(IAttachmentStore.class, query).getService();
        if (store == null) {
            throw new AdempiereException("No attachment storage provider found");
        }
        return store;
    }

    public IArchiveStore getArchiveStore() {
        ServiceQuery query = new ServiceQuery();
        String method = this.getMethod();
        if (method == null) method = "DB";
        query.put("method", method);
        IArchiveStore store =
                Service.Companion.locator().locate(IArchiveStore.class, query).getService();
        if (store == null) {
            throw new AdempiereException("No archive storage provider found");
        }
        return store;
    }
}
