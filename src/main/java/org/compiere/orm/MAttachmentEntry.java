package org.compiere.orm;

import java.io.*;
import java.util.Random;
import java.util.logging.Level;
import org.compiere.model.I_AD_AttachmentEntry;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.MimeType;

/**
 * Individual Attachment Entry of MAttachment
 *
 * @author Jorg Janke
 * @version $Id: MAttachmentEntry.java,v 1.2 2006/07/30 00:58:18 jjanke Exp $
 */
public class MAttachmentEntry implements I_AD_AttachmentEntry {
  /** Random Seed */
  private static long s_seed = System.currentTimeMillis();
  /** Random Number */
  private static Random s_random = new Random(s_seed);
  /** Logger */
  protected CLogger log = CLogger.getCLogger(getClass());
  /** The Name */
  private String m_name = "?";
  /** The Data */
  private byte[] m_data = null;
  /** Index */
  private int m_index = 0;
  /**
   * Attachment Entry
   *
   * @param name name
   * @param data binary data
   * @param index optional index
   */
  public MAttachmentEntry(String name, byte[] data, int index) {
    super();
    setName(name);
    setData(data);
    if (index > 0) m_index = index;
    else {
      long now = System.currentTimeMillis();
      if (s_seed + 3600000l < now) // 	older then 1 hour
      {
        s_seed = now;
        s_random = new Random(s_seed);
      }
      m_index = s_random.nextInt();
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

  /** @return Returns the data. */
  public byte[] getData() {
    return m_data;
  }

  /** @param data The data to set. */
  public void setData(byte[] data) {
    m_data = data;
  }

  /** @return Returns the name. */
  public String getName() {
    return m_name;
  }

  /** @param name The name to set. */
  public void setName(String name) {
    if (name != null) m_name = name;
    if (m_name == null) m_name = "?";
  } //	setName

  /**
   * Get Attachment Index
   *
   * @return timestamp
   */
  public int getIndex() {
    return m_index;
  } //	getIndex

  public void setIndex(int index) {
    m_index = index;
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

  /** Dump Data */
  public void dump() {
    StringBuilder hdr = new StringBuilder("----- ").append(getName()).append(" -----");
    System.out.println(hdr.toString());
    if (m_data == null) {
      System.out.println("----- no data -----");
      return;
    }
    //	raw data
    for (int i = 0; i < m_data.length; i++) {
      char data = (char) m_data[i];
      System.out.print(data);
    }

    System.out.println();
    System.out.println(hdr.toString());
    //	Count nulls at end
    int ii = m_data.length - 1;
    int nullCount = 0;
    while (m_data[ii--] == 0) nullCount++;
    StringBuilder msgout =
        new StringBuilder("----- Length=")
            .append(m_data.length)
            .append(", EndNulls=")
            .append(nullCount)
            .append(", RealLength=")
            .append((m_data.length - nullCount));
    System.out.println(msgout.toString());
    /**
     * // Dump w/o nulls if (nullCount > 0) { for (int i = 0; i < m_data.length-nullCount; i++)
     * System.out.print((char)m_data[i]); System.out.println (); System.out.println (hdr); } /** *
     */
  } //	dump

  /**
   * Get File with default name
   *
   * @return File
   */
  public File getFile() {
    return getFile(getName());
  } //	getFile

  /**
   * Get File with name
   *
   * @param fileName optional file name
   * @return file
   */
  public File getFile(String fileName) {
    if (fileName == null || fileName.length() == 0) fileName = getName();
    return getFile(new File(System.getProperty("java.io.tmpdir") + File.separator + fileName));
  } //	getFile

  /**
   * Get File
   *
   * @param file out file
   * @return file
   */
  public File getFile(File file) {
    if (m_data == null || m_data.length == 0) return null;
    try {
      FileOutputStream fos = new FileOutputStream(file);
      fos.write(m_data);
      fos.close();
    } catch (IOException ioe) {
      log.log(Level.SEVERE, "getFile", ioe);
      throw new RuntimeException(ioe);
    }
    return file;
  } //	getFile

  /**
   * Is attachment entry a PDF
   *
   * @return true if PDF
   */
  public boolean isPDF() {
    return m_name.toLowerCase().endsWith(".pdf");
  } //	isPDF

  /**
   * Is attachment entry a Graphic
   *
   * @return true if *.gif, *.jpg, *.png
   */
  public boolean isGraphic() {
    String m_lowname = m_name.toLowerCase();
    return m_lowname.endsWith(".gif") || m_lowname.endsWith(".jpg") || m_lowname.endsWith(".png");
  } //	isGraphic

  /**
   * Get Content (Mime) Type
   *
   * @return content type
   */
  public String getContentType() {
    return MimeType.getMimeType(m_name);
  } //	getContentType

  /**
   * Get Data as Input Stream
   *
   * @return input stream
   */
  public InputStream getInputStream() {
    if (m_data == null) return null;
    return new ByteArrayInputStream(m_data);
  } //	getInputStream
} //	MAttachmentItem
