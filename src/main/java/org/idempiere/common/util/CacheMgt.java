package org.idempiere.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;

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
    /**
     * Logger
     */
    private static CLogger log = CLogger.getCLogger(CacheMgt.class);

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
     * do a cluster wide cache reset for tableName with recordId key
     *
     * @return number of deleted cache entries
     */
    private int clusterReset() {
        return 0;
    }

    /**
     * do a cluster wide cache reset for tableName with recordId key
     *
     * @return number of deleted cache entries
     */
    private void clusterNewRecord() {
    }

    /**
     * do a cluster wide cache reset
     *
     * @return number of deleted cache entries
     */
    public int reset() {
        return clusterReset();
    }

    /**
     * ************************************************************************ Reset local Cache
     *
     * @return number of deleted cache entries
     */
    public int resetLocalCache() {
        int counter = 0;
        int total = 0;
        CacheInterface[] instances = getInstancesAsArray();
        for (CacheInterface stored : instances) {
            if (stored != null && stored.size() > 0) {
                if (log.isLoggable(Level.FINE)) log.fine(stored.toString());
                total += stored.reset();
                counter++;
            }
        }
        if (log.isLoggable(Level.FINE)) log.fine("#" + counter + " (" + total + ")");
        return total;
    }

    /**
     * @return
     */
    protected synchronized CacheInterface[] getInstancesAsArray() {
        return m_instances.toArray(new CacheInterface[0]);
    }

    /**
     * Reset local Cache
     *
     * @param tableName table name
     * @param Record_ID record if applicable or 0 for all
     * @return number of deleted cache entries
     */
    protected int resetLocalCache(String tableName, int Record_ID) {
        if (tableName == null) return resetLocalCache();
        if (!m_tableNames.contains(tableName)) return 0;
        //
        int counter = 0;
        int total = 0;
        CacheInterface[] instances = getInstancesAsArray();
        for (CacheInterface stored : instances) {
            if (stored != null && stored instanceof CCache) {
                CCache<?, ?> cc = (CCache<?, ?>) stored;
                if (cc.getTableName() != null
                        && cc.getTableName().startsWith(tableName)) // 	reset lines/dependent too
                {
                    {
                        if (log.isLoggable(Level.FINE)) log.fine("(all) - " + stored);
                        total += stored.reset(Record_ID);
                        counter++;
                    }
                }
            }
        }
        if (log.isLoggable(Level.FINE)) log.fine(tableName + ": #" + counter + " (" + total + ")");

        return total;
    }

    /**
     * Reset local Cache
     *
     * @param tableName table name
     * @param Record_ID record if applicable or 0 for all
     * @return number of deleted cache entries
     */
    protected void localNewRecord(String tableName, int Record_ID) {
        if (tableName == null) return;

        if (!m_tableNames.contains(tableName)) return;
        //
        CacheInterface[] instances = getInstancesAsArray();
        for (CacheInterface stored : instances) {
            if (stored != null && stored instanceof CCache) {
                CCache<?, ?> cc = (CCache<?, ?>) stored;
                if (cc.getTableName() != null
                        && cc.getTableName().startsWith(tableName)) // 	reset lines/dependent too
                {
                    {
                        stored.newRecord(Record_ID);
                    }
                }
            }
        }
    }

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        return "CacheMgt[" + "Instances=" + m_instances.size() + "]";
    } //	toString

    public void newRecord() {
        clusterNewRecord();
    }

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
