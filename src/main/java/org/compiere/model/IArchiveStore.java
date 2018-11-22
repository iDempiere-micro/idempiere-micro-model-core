package org.compiere.model;

public interface IArchiveStore {

  public byte[] loadLOBData(I_AD_Archive archive, I_AD_StorageProvider prov);

  public void save(I_AD_Archive archive, I_AD_StorageProvider prov, byte[] inflatedData);

  public boolean deleteArchive(I_AD_Archive archive, I_AD_StorageProvider prov);
}
