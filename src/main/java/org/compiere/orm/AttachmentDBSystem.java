package org.compiere.orm;

import org.compiere.model.IAttachmentStore;
import org.compiere.model.I_AD_Attachment;
import org.compiere.model.I_AD_AttachmentEntry;
import org.compiere.model.I_AD_StorageProvider;
import org.idempiere.common.util.CLogger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class AttachmentDBSystem implements IAttachmentStore {

    /**
     * Indicator for zip data
     */
    public static final String ZIP = "zip";
    private final CLogger log = CLogger.getCLogger(getClass());


    @Override
    public boolean loadLOBData(I_AD_Attachment attach, I_AD_StorageProvider prov) {
//		Reset
        attach.setItems(new ArrayList<I_AD_AttachmentEntry>());
        //
        byte[] data = attach.getBinaryData();
        if (data == null)
            return true;
        if (log.isLoggable(Level.FINE)) log.fine("ZipSize=" + data.length);
        if (data.length == 0)
            return true;

        ArrayList<I_AD_AttachmentEntry> m_items = attach.getItems();

        //	Old Format - single file
        if (!ZIP.equals(attach.getTitle())) {
            m_items.add(new MAttachmentEntry(attach.getTitle(), data, 1));
            return true;
        }

        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ZipInputStream zip = new ZipInputStream(in);
            ZipEntry entry = zip.getNextEntry();
            while (entry != null) {
                String name = entry.getName();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[2048];
                int length = zip.read(buffer);
                while (length != -1) {
                    out.write(buffer, 0, length);
                    length = zip.read(buffer);
                }
                //
                byte[] dataEntry = out.toByteArray();
                if (log.isLoggable(Level.FINE)) log.fine(name
                        + " - size=" + dataEntry.length + " - zip="
                        + entry.getCompressedSize() + "(" + entry.getSize() + ") "
                        + (entry.getCompressedSize() * 100 / entry.getSize()) + "%");
                //
                m_items.add(new MAttachmentEntry(name, dataEntry, m_items.size() + 1));
                entry = zip.getNextEntry();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "loadLOBData", e);
            attach.setItems(null);
            return false;
        }
        return true;
    }

    @Override
    public boolean save(I_AD_Attachment attach, I_AD_StorageProvider prov) {
        ArrayList<I_AD_AttachmentEntry> m_items = attach.getItems();
        if (m_items == null || m_items.size() == 0) {
            attach.setBinaryData(null);
            return true;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(out);
        zip.setMethod(ZipOutputStream.DEFLATED);
        zip.setLevel(Deflater.BEST_COMPRESSION);
        zip.setComment("iDempiere");
        //
        try {
            for (int i = 0; i < m_items.size(); i++) {
                I_AD_AttachmentEntry item = attach.getEntry(i);
                ZipEntry entry = new ZipEntry(item.getName());
                entry.setTime(System.currentTimeMillis());
                entry.setMethod(ZipEntry.DEFLATED);
                zip.putNextEntry(entry);
                byte[] data = item.getData();
                zip.write(data, 0, data.length);
                zip.closeEntry();
                if (log.isLoggable(Level.FINE)) log.fine(entry.getName() + " - "
                        + entry.getCompressedSize() + " (" + entry.getSize() + ") "
                        + (entry.getCompressedSize() * 100 / entry.getSize()) + "%");
            }
            //	zip.finish();
            zip.close();
            byte[] zipData = out.toByteArray();
            if (log.isLoggable(Level.FINE)) log.fine("Length=" + zipData.length);
            attach.setBinaryData(zipData);
            attach.setTitle(I_AD_Attachment.ZIP);
            return true;
        } catch (Exception e) {
            log.log(Level.SEVERE, "saveLOBData", e);
        }
        attach.setBinaryData(null);
        return false;
    }

    @Override
    public boolean delete(I_AD_Attachment attach, I_AD_StorageProvider prov) {
        // nothing todo - deleting the db record deletes the items
        return true;
    }

}
