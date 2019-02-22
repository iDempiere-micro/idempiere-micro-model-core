package org.compiere.orm;

import java.util.Properties;

import org.compiere.model.IAttachmentStore;
import org.compiere.model.I_AD_StorageProvider;

public class MStorageProvider extends X_AD_StorageProvider implements I_AD_StorageProvider {
  /** */
  private static final long serialVersionUID = -5889682671195395536L;

  public MStorageProvider(Properties ctx, int AD_StorageProvider_ID) {
    super(ctx, AD_StorageProvider_ID);
  }

    public IAttachmentStore getAttachmentStore() {
      return new AttachmentDBSystem();
    }

}
