package org.idempiere.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Adempiere Cache Management
 *
 * @author Jorg Janke
 * @version $Id: CacheMgt.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 */
public class CacheMgt {
    public static int MAX_SIZE = 1000;
    /**
     * Singleton
     */
    private static CacheMgt s_cache = null;

    static {
        try {
            String maxSize = System.getProperty("Cache.MaxSize");
            if (maxSize != null && maxSize.trim().length() > 0) {
                int max = 0;
                try {
                    max = Integer.parseInt(maxSize.trim());
                } catch (Throwable ignored) {
                }
                if (max > 0) MAX_SIZE = max;
            }
        } catch (Throwable ignored) {
        }
    }

    /**
     * List of Instances
     */
    private ArrayList<CacheInterface> m_instances = new ArrayList<>();
    /**
     * List of Table Names
     */
    private ArrayList<String> m_tableNames = new ArrayList<>();

    /**
     * Private Constructor
     */
    private CacheMgt() {
    } // 	CacheMgt

    /**
     * Get Cache Management
     *
     * @return Cache Manager
     */
    public static synchronized CacheMgt get() {
        if (s_cache == null) s_cache = new CacheMgt();
        return s_cache;
    } //	get

    /**
     * ************************************************************************ Create Cache Instance
     *
     * @param instance    Cache
     * @return true if added
     */
    public synchronized <K, V> Map<K, V> register(CCache<K, V> instance) {
        if (instance == null) return null;

        String tableName = instance.getTableName();
        if (tableName != null) m_tableNames.add(tableName);

        m_instances.add(instance);

        return Collections.synchronizedMap(new MaxSizeHashMap<>(instance.getMaxSize()));
    } //	register

    /**
     * @return
     */
    protected synchronized CacheInterface[] getInstancesAsArray() {
        return m_instances.toArray(new CacheInterface[0]);
    }


    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        return "CacheMgt[" + "Instances=" + m_instances.size() + "]";
    } //	toString

    private static class MaxSizeHashMap<K, V> extends LinkedHashMap<K, V> {
        /**
         * generated serial id
         */
        private static final long serialVersionUID = 5532596165440544235L;

        private final int maxSize;

        public MaxSizeHashMap(int maxSize) {
            this.maxSize = maxSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return maxSize > 0 && size() > maxSize;
        }
    }
} //	CCache
