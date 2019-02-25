package org.idempiere.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Zip/Jar File Utilities
 *
 * @author Jorg Janke
 * @version $Id: ZipUtil.java,v 1.2 2006/07/30 00:54:36 jjanke Exp $
 */
public class ZipUtil {
    @SuppressWarnings("unused")
    private File m_file;

    private ZipFile m_zipFile;

    /**
     * Empty Constructor, need to open explicitly.
     */
    public ZipUtil() {
    } // 	ZipUtil

    /**
     * Open zip file.
     *
     * @param fileName zip file name
     */
    public ZipUtil(String fileName) {
        open(fileName);
    } //	ZipUtil

    /**
     * Open zip file.
     *
     * @param file zip file
     */
    public ZipUtil(File file) {
        open(file);
    } //	ZipUtil

    /**
     * ************************************************************************ Get Zip Entry
     *
     * @param fileName  zip/jar file
     * @param entryName entry
     * @return ZipEntry
     */
    public static ZipEntry getEntry(String fileName, String entryName) {
        if (fileName == null || entryName == null) return null;
        //	File
        File file = new File(fileName);
        if (!file.exists()) {
            String fn = findInPath(fileName);
            if (fn == null) return null; // 	file not found
            file = new File(fn);
        }
        ZipUtil zu = new ZipUtil(file);
        if (!zu.isOpen()) return null;
        //	Entry
        ZipEntry retValue = zu.getEntry(entryName);
        if (retValue == null) {
            Enumeration<?> e = zu.entries();
            while (e.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                if (entry.getName().indexOf(entryName) != -1) {
                    retValue = entry;
                    break;
                }
            }
        }
        zu.close();
        return retValue;
    } //	getEntry

    /**
     * Get Jar File
     *
     * @param fileName zip/jar file
     * @return Jar
     */
    public static JarFile getJar(String fileName) {
        if (fileName == null) return null;
        //	File
        File file = new File(fileName);
        if (!file.exists()) {
            String fn = findInPath(fileName);
            if (fn == null) return null; // 	file not found
            file = new File(fn);
        }
        ZipUtil zu = new ZipUtil(file);
        return zu.getJar();
    } //	getJar

    /**
     * Get Manifest
     *
     * @param fileName zip/jar file
     * @return Manifest or null
     */
    public static Manifest getManifest(String fileName) {
        if (fileName == null) return null;
        JarFile jar = getJar(fileName);
        if (jar == null) return null;
        try {
            return jar.getManifest();
        } catch (IOException ex) {
            System.err.println("ZipUtil.getManifest - " + ex);
        }
        return null;
    } //	getManifest

    /**
     * Get Fill name of jarfile in path
     *
     * @param jarFile name
     * @return full name or null if not found
     */
    public static String findInPath(String jarFile) {
        String path = System.getProperty("java.class.path");
        String[] pathEntries = path.split(System.getProperty("path.separator"));
        for (int i = 0; i < pathEntries.length; i++) {
            //	System.out.println(pathEntries[i]);
            if (pathEntries[i].indexOf(jarFile) != -1) return pathEntries[i];
        }
        path = System.getProperty("sun.boot.class.path");
        pathEntries = path.split(System.getProperty("path.separator"));
        for (int i = 0; i < pathEntries.length; i++) {
            //	System.out.println(pathEntries[i]);
            if (pathEntries[i].indexOf(jarFile) != -1) return pathEntries[i];
        }
        return null;
    } //	findInPath

    /**
     * Open the Zip File for reading
     *
     * @param fileName zip file
     * @return true if opened
     */
    public boolean open(String fileName) {
        if (fileName == null) return false;
        try {
            return open(new File(fileName));
        } catch (Exception ex) {
            System.err.println("ZipUtil.open - " + ex);
        }
        return false;
    } //	open

    /**
     * Open the Zip File for reading
     *
     * @param file zip file
     * @return true if opened
     */
    public boolean open(File file) {
        if (file == null) return false;
        m_file = file;
        try {
            if (file.getName().endsWith("jar")) m_zipFile = new JarFile(file, false, JarFile.OPEN_READ);
            else m_zipFile = new ZipFile(file, ZipFile.OPEN_READ);
        } catch (IOException ex) {
            System.err.println("ZipUtil.open - " + ex);
            m_zipFile = null;
            return false;
        }
        return true;
    } //	open

    /**
     * Close Zip File
     */
    public void close() {
        try {
            if (m_zipFile != null) m_zipFile.close();
        } catch (IOException ex) {
            System.err.println("ZipUtil.close - " + ex);
        }
        m_zipFile = null;
    } //	close

    /**
     * Is the Zip File Open
     *
     * @return true if yes
     */
    public boolean isOpen() {
        return m_zipFile != null;
    } //	isOpen

    /**
     * Get it as Jar if it is a Jar
     *
     * @return jar or null if not a jar
     */
    public JarFile getJar() {
        if (m_zipFile != null && m_zipFile instanceof JarFile) return (JarFile) m_zipFile;
        return null;
    } //	getJar

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        if (m_zipFile != null) return m_zipFile.toString();
        return "ZipUtil";
    } //	toString

    /**
     * Get ZipEntries as Enumeration
     *
     * @return entries
     */
    public Enumeration<?> entries() {
        if (!isOpen()) return null;
        return m_zipFile.entries();
    } //	entries

    /**
     * Get Zip Entry
     *
     * @param name entry name
     * @return ZipEntry or null if not found
     */
    public ZipEntry getEntry(String name) {
        if (!isOpen()) return null;
        return m_zipFile.getEntry(name);
    } //	getEntry

} //	ZipUtil
