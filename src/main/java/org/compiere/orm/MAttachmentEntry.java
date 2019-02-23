package org.compiere.orm;

import org.compiere.model.I_AD_AttachmentEntry;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.MimeType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;

/**
 * Individual Attachment Entry of MAttachment
 *
 * @author Jorg Janke
 * @version $Id: MAttachmentEntry.java,v 1.2 2006/07/30 00:58:18 jjanke Exp $
 */
public class MAttachmentEntry implements I_AD_AttachmentEntry {
    /**
     * Random Seed
     */
    private static long s_seed = System.currentTimeMillis();
    /**
     * Logger
     */
    protected CLogger log = CLogger.getCLogger(getClass());
    /**
     * The Name
     */
    private String m_name = "?";
    /**
     * The Data
     */
    private byte[] m_data = null;

    /**
     * Attachment Entry
     *
     * @param name  name
     * @param data  binary data
     * @param index optional index
     */
    public MAttachmentEntry(String name, byte[] data, int index) {
        super();
        setName(name);
        setData(data);
        if (index <= 0) {
            long now = System.currentTimeMillis();
            if (s_seed + 3600000l < now) // 	older then 1 hour
            {
                s_seed = now;
            }
        }
    } //	MAttachmentItem

    /**
     * Attachment Entry
     *
     * @param name name
     * @param data binary data
     */
    public MAttachmentEntry(String name, byte[] data) {
        this(name, data, 0);
    } //	MAttachmentItem

    /**
     * @return Returns the data.
     */
    public byte[] getData() {
        return m_data;
    }

    /**
     * @param data The data to set.
     */
    public void setData(byte[] data) {
        m_data = data;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return m_name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        if (name != null) m_name = name;
        if (m_name == null) m_name = "?";
    } //	setName

    public void setIndex(int index) {
    }

    /**
     * To String
     *
     * @return name
     */
    public String toString() {
        return m_name;
    } //	toString

    /**
     * To String Extended
     *
     * @return name (length)
     */
    public String toStringX() {
        StringBuilder sb = new StringBuilder(m_name);
        if (m_data != null) {
            sb.append(" (");
            //
            float size = m_data.length;
            if (size <= 1024) sb.append(m_data.length).append(" B");
            else {
                size /= 1024;
                if (size > 1024) {
                    size /= 1024;
                    sb.append(size).append(" MB");
                } else sb.append(size).append(" kB");
            }
            //
            sb.append(")");
        }
        sb.append(" - ").append(getContentType());
        return sb.toString();
    } //	toStringX

    /**
     * Get Content (Mime) Type
     *
     * @return content type
     */
    public String getContentType() {
        return MimeType.getMimeType(m_name);
    } //	getContentType

} //	MAttachmentItem
