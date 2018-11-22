package org.idempiere.common.util;

import java.io.File;
import java.io.Serializable;
import javax.swing.filechooser.FileFilter;

/**
 * Extension File Chooser
 *
 * @author Jorg Janke
 * @version $Id: ExtensionFileFilter.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class ExtensionFileFilter extends FileFilter implements Serializable {
  /** */
  private static final long serialVersionUID = 1653311024184813302L;

  /** Constructor */
  public ExtensionFileFilter() {
    this("", "");
  } //	ExtensionFileFilter

  /**
   * Constructor
   *
   * @param extension extension
   * @param description description
   */
  public ExtensionFileFilter(String extension, String description) {
    setDescription(description);
    setExtension(extension);
  } //	ExtensionFileFilter

  /** Extension */
  private String m_extension;
  //
  private String m_description;

  /**
   * Description
   *
   * @return description
   */
  public String getDescription() {
    return m_description;
  }
  /**
   * Set Description
   *
   * @param newDescription description
   */
  public void setDescription(String newDescription) {
    m_description = newDescription;
  } //	setDescription

  /**
   * Extension
   *
   * @param newExtension ext
   */
  public void setExtension(String newExtension) {
    m_extension = newExtension;
  }
  /**
   * Get Extension
   *
   * @return extension
   */
  public String getExtension() {
    return m_extension;
  }

  /**
   * Accept File
   *
   * @param file file to be tested
   * @return true if OK
   */
  public boolean accept(File file) {
    //	Need to accept directories
    if (file.isDirectory()) return true;

    String ext = file.getName();
    int pos = ext.lastIndexOf('.');

    //	No extension
    if (pos == -1) return false;

    ext = ext.substring(pos + 1);

    if (m_extension.equalsIgnoreCase(ext)) return true;

    return false;
  } //	accept

  /**
   * Verify file name with filer
   *
   * @param file file
   * @param filter filter
   * @return file name
   */
  public static String getFileName(File file, FileFilter filter) {
    return getFile(file, filter).getAbsolutePath();
  } //	getFileName

  /**
   * Verify file with filter
   *
   * @param file file
   * @param filter filter
   * @return file
   */
  public static File getFile(File file, FileFilter filter) {
    String fName = file.getAbsolutePath();
    if (fName == null || fName.equals("")) fName = "Adempiere";
    //
    ExtensionFileFilter eff = null;
    if (filter instanceof ExtensionFileFilter) eff = (ExtensionFileFilter) filter;
    else return file;
    //
    int pos = fName.lastIndexOf('.');

    //	No extension
    if (pos == -1) {
      fName += '.' + eff.getExtension();
      return new File(fName);
    }

    String ext = fName.substring(pos + 1);

    //	correct extension
    if (ext.equalsIgnoreCase(eff.getExtension())) return file;

    fName += '.' + eff.getExtension();
    return new File(fName);
  } //	getFile
} //	ExtensionFileFilter
