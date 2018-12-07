package org.idempiere.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import javax.activation.DataSource;

/**
 * A DataSource based on the Java Mail Example. This class implements a DataSource from: an
 * InputStream a byte array a String
 *
 * @author John Mani
 * @author Bill Shannon
 * @author Max Spivak
 */
public class ByteArrayDataSource implements DataSource {
  /** Logger */
  private static CLogger log = CLogger.getCLogger(ByteArrayDataSource.class);
  /** Data * */
  private byte[] m_data = null;
  /** Content Type * */
  private String m_type = "text/plain";
  /** Name * */
  private String m_name = null;
  /**
   * Create a DataSource from an input stream
   *
   * @param is stream
   * @param type optional MIME type e.g. text/html
   */
  public ByteArrayDataSource(InputStream is, String type) {
    try {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      int ch;

      while ((ch = is.read()) != -1) {
        // XXX - must be made more efficient by
        // doing buffered reads, rather than one byte reads
        os.write(ch);
      }
      m_data = os.toByteArray();
    } catch (IOException ioex) {
      log.log(Level.WARNING, "", ioex);
    }
    if (type != null && type.length() > 0) m_type = type;
  } //	ByteArrayDataSource
  /**
   * Create a DataSource from a byte array
   *
   * @param data data
   * @param type type e.g. text/html
   */
  public ByteArrayDataSource(byte[] data, String type) {
    m_data = data;
    if (type != null && type.length() > 0) m_type = type;
  } //	ByteArrayDataSource

  /**
   * Create a DataSource from a String
   *
   * @param stringData content
   * @param charSetName optional if null/empty uses UTF-8
   * @param type optional MIME type e.g. text/html
   */
  public ByteArrayDataSource(String stringData, String charSetName, String type) {
    if (charSetName == null || charSetName.length() == 0)
      charSetName = "UTF-8"; // WebEnv.ENCODING - alternatibe iso-8859-1
    try {
      m_data = stringData.getBytes(charSetName);
    } catch (UnsupportedEncodingException uex) {
      log.log(Level.WARNING, "CharSetName=" + charSetName, uex);
    }
    if (type != null && type.length() > 0) m_type = type;
  } //	ByteArrayDataSource

  /**
   * Return an InputStream for the data.
   *
   * @return inputstream
   * @throws IOException
   */
  public InputStream getInputStream() throws IOException {
    if (m_data == null) throw new IOException("no data");
    //	a new stream must be returned each time.
    return new ByteArrayInputStream(m_data);
  } //	getInputStream

  /**
   * Throws exception
   *
   * @return null
   * @throws IOException
   */
  public OutputStream getOutputStream() throws IOException {
    throw new IOException("cannot do this");
  } //	getOutputStream

  /**
   * Get Content Type
   *
   * @return MIME type e.g. text/html
   */
  public String getContentType() {
    return m_type;
  } //	getContentType

  /**
   * Return Name or Class Name & Content Type
   *
   * @return dummy
   */
  public String getName() {
    if (m_name != null) return m_name;
    return "ByteArrayDataStream " + m_type;
  } //	getName

  /**
   * Set Name
   *
   * @param name name
   * @return this
   */
  public ByteArrayDataSource setName(String name) {
    m_name = name;
    return this;
  } //	setName
} //	ByteArrayDataStream
