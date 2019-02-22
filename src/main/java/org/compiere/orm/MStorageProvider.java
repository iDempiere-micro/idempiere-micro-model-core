package org.compiere.orm;

import org.compiere.model.IAttachmentStore;
import org.compiere.model.I_AD_StorageProvider;

import java.util.Properties;

public class MStorageProvider extends X_AD_StorageProvider implements I_AD_StorageProvider {
    /**
     *
     */
    private static final long serialVersionUID = -5889682671195395536L;

    public MStorageProvider(Properties ctx, int AD_StorageProvider_ID) {
        super(ctx, AD_StorageProvider_ID);
    }

    public IAttachmentStore getAttachmentStore() {
        return new AttachmentDBSystem();
    }

}
