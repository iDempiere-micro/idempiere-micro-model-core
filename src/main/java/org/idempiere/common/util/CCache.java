package org.idempiere.common.util;

import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Cache for table.
 *
 * @param <K> Key
 * @param <V> Value
 * @author Jorg Janke
 * @version $Id: CCache.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 */
public class CCache<K, V> implements CacheInterface, Map<K, V>, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2268565219001179841L;
    /**
     * Vetoable Change Support Name
     */
    private static String PROPERTYNAME = "cache";

    private Map<K, V> cache = null;
    private Set<K> nullList = null;
    private String m_tableName;

    private int m_maxSize = 0;
    /**
     * Name
     */
    private String m_name = null;
    /**
     * Expire after minutes
     */
    private int m_expire = 0;
    /**
     * Time
     */
    private volatile long m_timeExp = 0;
    /**
     * Just reset - not used
     */
    private boolean m_justReset = true;
    /**
     * Vetoable Change Support
     */
    private VetoableChangeSupport m_changeSupport = null;

    public CCache(String name) {
        this(name, name);
    }

    public CCache(String name, int expireMinutes) {
        this(name, name, expireMinutes);
    }

    public CCache(
            String name, int expireMinutes, int maxSize) {
        this(name, name, expireMinutes, maxSize);
    }

    public CCache(String tableName, String name) {
        this(tableName, name, 60);
    }

    public CCache(
            String tableName, String name, int expireMinutes) {
        this(tableName, name, expireMinutes, CacheMgt.MAX_SIZE);
    }

    /**
     * Adempiere Cache
     * @param name            (table) name of the cache
     * @param expireMinutes   expire after minutes (0=no expire)
     * @param maxSize         ignore if distributed=true
     */
    public CCache(
            String tableName,
            String name,
            int expireMinutes,
            int maxSize) {
        m_name = name;
        m_tableName = tableName;
        setExpireMinutes(expireMinutes);
        m_maxSize = maxSize;
        cache = CacheMgt.get().register(this);

        if (nullList == null) {
            nullList = Collections.synchronizedSet(new HashSet<K>());
        }
    } //	CCache

    /**
     * Get (table) Name
     *
     * @return name
     */
    public String getName() {
        return m_name;
    } //	getName

    public String getTableName() {
        return m_tableName;
    }

    /**
     * Get Expire Minutes
     *
     * @return expire minutes
     */
    public int getExpireMinutes() {
        return m_expire;
    } //	getExpireMinutes

    /**
     * Set Expire Minutes and start it
     *
     * @param expireMinutes minutes or 0
     */
    public void setExpireMinutes(int expireMinutes) {
        if (expireMinutes > 0) {
            m_expire = expireMinutes;
            long addMS = 60000L * m_expire;
            m_timeExp = System.currentTimeMillis() + addMS;
        } else {
            m_expire = 0;
            m_timeExp = 0;
        }
    } //	setExpireMinutes

    /**
     * Cache was reset
     *
     * @return true if reset
     */
    public boolean isReset() {
        return m_justReset;
    } //	isReset

    /**
     * Reset Cache
     *
     * @return number of items cleared
     * @see org.compiere.util.CacheInterface#reset()
     */
    public int reset() {
        int no = cache.size() + nullList.size();
        clear();
        return no;
    } //	reset

    /**
     * Expire Cache if enabled
     */
    private void expire() {
        if (m_expire != 0 && m_timeExp < System.currentTimeMillis()) {
            //	System.out.println ("------------ Expired: " + getName() + " --------------------");
            reset();
        }
    } //	expire

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        return "CCache[" + m_name + ",Exp=" + getExpireMinutes() + ", #" + cache.size() + "]";
    } //	toString

    /**
     * Clear cache and calculate new expiry time
     *
     * @see java.util.Map#clear()
     */
    public void clear() {
        if (m_changeSupport != null) {
            try {
                m_changeSupport.fireVetoableChange(PROPERTYNAME, cache.size(), 0);
            } catch (Exception e) {
                System.out.println("CCache.clear - " + e);
                return;
            }
        }
        //	Clear
        cache.clear();
        nullList.clear();
        if (m_expire != 0) {
            long addMS = 60000L * m_expire;
            m_timeExp = System.currentTimeMillis() + addMS;
        }
        m_justReset = true;
    } //	clear

    /**
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key) {
        expire();
        return cache.containsKey(key) || nullList.contains(key);
    } //	containsKey

    /**
     * @see java.util.Map#containsValue(java.lang.Object)
     */
    public boolean containsValue(Object value) {
        expire();
        return cache.containsValue(value);
    } //	containsValue

    /**
     * The return entry set exclude entries that contains null value
     *
     * @see java.util.Map#entrySet()
     */
    public Set<Map.Entry<K, V>> entrySet() {
        expire();
        return cache.entrySet();
    } //	entrySet

    /**
     * @see java.util.Map#get(java.lang.Object)
     */
    public V get(Object key) {
        expire();
        return cache.get(key);
    } //	get

    /**
     * Put value
     *
     * @param key   key
     * @param value value
     * @return previous value
     */
    public V put(K key, V value) {
        expire();
        m_justReset = false;
        if (value == null) {
            cache.remove(key);
            nullList.add(key);
            return null;
        } else if (!nullList.isEmpty()) {
            nullList.remove(key);
        }
        return cache.put(key, value);
    } // put

    /**
     * Put All
     *
     * @param m map
     */
    public void putAll(Map<? extends K, ? extends V> m) {
        expire();
        m_justReset = false;
        cache.putAll(m);
    } //	putAll

    /**
     * @see java.util.Map#isEmpty()
     */
    public boolean isEmpty() {
        expire();
        return cache.isEmpty() && nullList.isEmpty();
    } // isEmpty

    /**
     * The return key set excludes key that map to null value
     *
     * @see java.util.Map#keySet()
     */
    public Set<K> keySet() {
        expire();
        return cache.keySet();
    } //	keySet

    /**
     * @see java.util.Map#size()
     */
    public int size() {
        expire();
        return cache.size() + nullList.size();
    } //	size

    /**
     * Get Size w/o Expire
     *
     * @return size
     * @see java.util.Map#size()
     */
    public int sizeNoExpire() {
        return cache.size() + nullList.size();
    } //	size

    /**
     * The return values collection exclude null value entries
     *
     * @see java.util.Map#values()
     */
    public Collection<V> values() {
        expire();
        return cache.values();
    } //	values

    @Override
    public V remove(Object key) {
        if (!nullList.isEmpty()) {
            if (nullList.remove(key)) return null;
        }
        return cache.remove(key);
    }

    @Override
    public int reset(int recordId) {
        if (recordId <= 0) return reset();

        if (!nullList.isEmpty() && nullList.remove(recordId)) return 1;
        V removed = cache.remove(recordId);
        return removed != null ? 1 : 0;
    }

    public int getMaxSize() {
        return m_maxSize;
    }
} //	CCache
