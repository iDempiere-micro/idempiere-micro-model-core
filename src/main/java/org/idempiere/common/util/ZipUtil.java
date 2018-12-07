package org.idempiere.common.util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.jar.JarEntry;
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
     * Get Manifest
     *
     * @param fileName jar file
     * @param jarEntry jar entry
     * @return Manifest
     */
    public static JarEntry getJarEntry(String fileName, String jarEntry) {
        if (fileName == null) return null;
        JarFile jar = getJar(fileName);
        if (jar == null) return null;
        return jar.getJarEntry(jarEntry);
    } //	getManifest

    /**
     * Dump Manifest to
     *
     * @param fileName zip/jar file
     */
    public static void dumpManifest(String fileName) {
        Manifest mf = getManifest(fileName);
        if (mf == null) {
            System.out.println("No Jar file: " + fileName);
            return;
        }
        //
        System.out.println(mf.getEntries());
    } //	dumpManifest

    /**
     * Get Zip Entry time
     *
     * @param fileName  zip file
     * @param entryName entry
     * @return Time as String or null
     */
    public static String getEntryTime(String fileName, String entryName) {
        ZipEntry entry = getEntry(fileName, entryName);
        if (entry == null) return null;
        Timestamp ts = new Timestamp(entry.getTime());
        return ts.toString();
    } //	getEntryTime

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
     * Is it a Jar
     *
     * @return true if yes
     */
    public boolean isJar() {
        return (m_zipFile != null && m_zipFile instanceof JarFile);
    } //	isJar

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
     * ************************************************************************ Get Content as sorted
     * String Array
     *
     * @return content
     */
    public String[] getContent() {
        if (!isOpen()) return null;
        Enumeration<? extends ZipEntry> e = m_zipFile.entries();
        ArrayList<ZipEntry> list = new ArrayList<ZipEntry>();
        while (e.hasMoreElements()) list.add(e.nextElement());
        //	return sorted array
        String[] retValue = new String[list.size()];
        for (int i = 0; i < retValue.length; i++) retValue[i] = list.get(i).getName();
        Arrays.sort(retValue);
        return retValue;
    } //	getContent

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

    /**
     * Get File Info
     *
     * @param name name
     * @return time and size
     */
    public String getEntryInfo(String name) {
        StringBuilder sb = new StringBuilder(name);
        ZipEntry e = getEntry(name);
        if (e == null) sb.append(": -");
        else {
            Timestamp ts = new Timestamp(e.getTime());
            sb.append(": ").append(ts).append(" - ").append(e.getSize());
        }
        return sb.toString();
    } //	getEntryInfo

    /**
     * Get Manifest if a Jar
     *
     * @return Manifest if exists or null
     */
    public Manifest getManifest() {
        try {
            JarFile jar = getJar();
            if (jar != null) return jar.getManifest();
        } catch (IOException ex) {
            System.err.println("ZipUtil.getManifest - " + ex);
        }
        return null;
    } //	getManifest
} //	ZipUtil
