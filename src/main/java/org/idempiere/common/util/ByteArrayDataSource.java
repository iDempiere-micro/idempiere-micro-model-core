package org.idempiere.common.util;

import java.io.*;
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

} //	ByteArrayDataStream
