package org.compiere.orm;

import org.compiere.model.IAttachmentStore;
import org.compiere.model.I_AD_Attachment;
import org.compiere.model.I_AD_AttachmentEntry;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.getSQLValue;

/**
 * Attachment Model. One Attachment can have multiple entries
 *
 * @author Jorg Janke
 * @author Silvano Trinchero
 *     <li>BF [ 2992291] MAttachment.addEntry not closing streams if an exception occur
 *         http://sourceforge.net/tracker/?func=detail&aid=2992291&group_id=176962&atid=879332
 * @version $Id: MAttachment.java,v 1.4 2006/07/30 00:58:37 jjanke Exp $
 */
public class MAttachment extends X_AD_Attachment implements I_AD_Attachment {
  /** Indicator for no data */
  public static final String NONE = ".";
    /** */
  private static final long serialVersionUID = -8261865873158774665L;
  /** Static Logger */
  @SuppressWarnings("unused")
  private static CLogger s_log = CLogger.getCLogger(MAttachment.class);
    /** List of Entry Data */
  public ArrayList<I_AD_AttachmentEntry> m_items = null;

  private MStorageProvider provider;

  /**
   * ************************************************************************ Standard Constructor
   *
   * @param ctx context
   * @param AD_Attachment_ID id
   * @param trxName transaction
   */
  public MAttachment(Properties ctx, int AD_Attachment_ID) {
    super(ctx, AD_Attachment_ID);
    initAttachmentStoreDetails(ctx);
  } //	MAttachment

  /**
   * New Constructor
   *
   * @param ctx context
   * @param AD_Table_ID table
   * @param Record_ID record
   * @param trxName transaction
   */
  public MAttachment(Properties ctx, int AD_Table_ID, int Record_ID) {
    this(
        ctx,
        MAttachment.getID(AD_Table_ID, Record_ID) > 0
            ? MAttachment.getID(AD_Table_ID, Record_ID)
            : 0);
    if (getId() == 0) {
      setAD_Table_ID(AD_Table_ID);
      setRecord_ID(Record_ID);
    }
  } //	MAttachment

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MAttachment(Properties ctx, ResultSet rs) {
    super(ctx, rs);
    initAttachmentStoreDetails(ctx);
  } //	MAttachment

  /**
   * Get Attachment (if there are more than one attachment it gets the first in no specific order)
   *
   * @param ctx context
   * @param AD_Table_ID table
   * @param Record_ID record
   * @param trxName
   * @return attachment or null
   */
  public static MAttachment get(Properties ctx, int AD_Table_ID, int Record_ID) {
    final String whereClause =
        I_AD_Attachment.COLUMNNAME_AD_Table_ID
            + "=? AND "
            + I_AD_Attachment.COLUMNNAME_Record_ID
            + "=?";
    MAttachment retValue =
        new Query(ctx, I_AD_Attachment.Table_Name, whereClause)
            .setParameters(AD_Table_ID, Record_ID)
            .first();
    return retValue;
  } //	get

  /**
   * IDEMPIERE-530 Get the attachment ID based on table_id and record_id
   *
   * @param AD_Table_ID
   * @param Record_ID
   * @return AD_Attachment_ID
   */
  public static int getID(int Table_ID, int Record_ID) {
    String sql = "SELECT AD_Attachment_ID FROM AD_Attachment WHERE AD_Table_ID=? AND Record_ID=?";
    int attachid = getSQLValue(sql, Table_ID, Record_ID);
    return attachid;
  }

  /**
   * Get the isStoreAttachmentsOnFileSystem and attachmentPath for the client.
   *
   * @param ctx
   * @param trxName
   */
  private void initAttachmentStoreDetails(Properties ctx) {
    MClientInfo clientInfo = MClientInfo.get(ctx, getClientId());
    provider = new MStorageProvider(ctx, clientInfo.getAD_StorageProvider_ID());
  }

  /**
   * Set Client Org
   *
   * @param AD_Client_ID client
   * @param AD_Org_ID org
   */
  public void setClientOrg(int AD_Client_ID, int AD_Org_ID) {
    super.setClientOrg(AD_Client_ID, AD_Org_ID);
    initAttachmentStoreDetails(getCtx());
  } //	setClientOrg

    /**
   * Get Text Msg
   *
   * @return trimmed message
   */
  public String getTextMsg() {
    String msg = super.getTextMsg();
    if (msg == null) return null;
    return msg.trim();
  } //	setTextMsg

  @Override
  public ArrayList<I_AD_AttachmentEntry> getItems() {
    return m_items;
  }

  @Override
  public void setItems(ArrayList<I_AD_AttachmentEntry> items) {
    m_items = items;
  }

  /**
   * String Representation
   *
   * @return info
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("MAttachment[");
    sb.append(getAD_Attachment_ID())
        .append(",Title=")
        .append(getTitle())
        .append(",Entries=")
        .append(getEntryCount());
    for (int i = 0; i < getEntryCount(); i++) {
      if (i == 0) sb.append(":");
      else sb.append(",");
      sb.append(getEntryName(i));
    }
    sb.append("]");
    return sb.toString();
  } //	toString

  /**
   * Add new Data Entry
   *
   * @param file file
   * @return true if added
   */
  public boolean addEntry(File file) {

    if (file == null) {
      log.warning("No File");
      return false;
    }
    if (!file.exists() || file.isDirectory() || !file.canRead()) {
      log.warning(
          "not added - "
              + file
              + ", Exists="
              + file.exists()
              + ", Directory="
              + file.isDirectory());
      return false;
    }
    if (log.isLoggable(Level.FINE)) log.fine("addEntry - " + file);
    //
    String name = file.getName();
    byte[] data = null;

    // F3P: BF [2992291] modified to be able to close streams in "finally" block

    FileInputStream fis = null;
    ByteArrayOutputStream os = null;

    try {
      fis = new FileInputStream(file);
      os = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024 * 8]; //  8kB
      int length = -1;
      while ((length = fis.read(buffer)) != -1) os.write(buffer, 0, length);

      data = os.toByteArray();
    } catch (IOException ioe) {
      log.log(Level.SEVERE, "(file)", ioe);
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException ex) {
          log.log(Level.SEVERE, "(file)", ex);
        }
      }

      if (os != null) {
        try {
          os.close();
        } catch (IOException ex) {
          log.log(Level.SEVERE, "(file)", ex);
        }
      }
    }

    return addEntry(name, data);
  } //	addEntry

  /**
   * Add new Data Entry
   *
   * @param name name
   * @param data data
   * @return true if added
   */
  public boolean addEntry(String name, byte[] data) {
    if (name == null || data == null) return false;
    return addEntry(new MAttachmentEntry(name, data)); // 	random index
  } //	addEntry

  /**
   * Add Entry
   *
   * @param item attachment entry
   * @return true if added
   */
  public boolean addEntry(MAttachmentEntry item) {
    boolean replaced = false;
    boolean retValue = false;
    if (item == null) return false;
    if (m_items == null) loadLOBData();
    for (int i = 0; i < m_items.size(); i++) {
      if (m_items.get(i).getName().equals(item.getName())) {
        m_items.set(i, item);
        replaced = true;
      }
    }
    if (!replaced) {
      retValue = m_items.add(item);
      item.setIndex(m_items.size());
    }
    if (log.isLoggable(Level.FINE)) log.fine(item.toStringX());
    setBinaryData(new byte[0]); // ATTENTION! HEAVY HACK HERE... Else it will not save :(
    return retValue || replaced;
  } //	addEntry

  /**
   * Get Attachment Entry
   *
   * @param index index of the item
   * @return Entry or null
   */
  public MAttachmentEntry getEntry(int index) {
    if (m_items == null) loadLOBData();
    if (index < 0 || index >= m_items.size()) return null;
    return (MAttachmentEntry) m_items.get(index);
  } //	getEntry

    /**
   * Get Entry Count
   *
   * @return number of entries
   */
  public int getEntryCount() {
    if (m_items == null) loadLOBData();
    return m_items.size();
  } //	getEntryCount

  /**
   * Get Entry Name
   *
   * @param index index
   * @return name or null
   */
  public String getEntryName(int index) {
    String method = provider.getMethod();
    if (method == null) method = "DB";
    MAttachmentEntry item = getEntry(index);
    if (item != null) {
      // strip path
      String name = item.getName();
      if (name != null && "FileSystem".equals(method)) {
        name = name.substring(name.lastIndexOf(File.separator) + 1);
      }
      return name;
    }
    return null;
  } // getEntryName

    /**
   * Save Entry Data in Zip File format
   *
   * @return true if saved
   */
  private boolean saveLOBData() {
    IAttachmentStore prov = provider.getAttachmentStore();
    if (prov != null) return prov.save(this, provider);
    return false;
  }

  /**
   * Load Data into local m_data
   *
   * @return true if success
   */
  private boolean loadLOBData() {
    IAttachmentStore prov = provider.getAttachmentStore();
    if (prov != null) return prov.loadLOBData(this, provider);
    return false;
  }

  /**
   * Before Save
   *
   * @param newRecord new
   * @return true if can be saved
   */
  protected boolean beforeSave(boolean newRecord) {
    if (Util.isEmpty(getTitle())) setTitle(NONE);
    return saveLOBData(); //	save in BinaryData
  } //	beforeSave

  /**
   * Executed before Delete operation.
   *
   * @return true if record can be deleted
   */
  protected boolean beforeDelete() {
    return deleteLOBData();
  }

  /**
   * Delete Entry Data in Zip File format
   *
   * @return true if saved
   */
  private boolean deleteLOBData() {
    if (m_items == null) loadLOBData();
    IAttachmentStore prov = provider.getAttachmentStore();
    if (prov != null) return prov.delete(this, provider);
    return false;
  } //	beforeDelete

    /**
   * Update existing entry
   *
   * @param i
   * @param data
   * @return true if success, false otherwise
   */
  public boolean updateEntry(int i, byte[] data) {
    MAttachmentEntry entry = getEntry(i);
    if (entry == null) return false;
    entry.setData(data);
    return true;
  }

} //	MAttachment
