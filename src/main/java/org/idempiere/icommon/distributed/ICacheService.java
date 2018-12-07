package org.idempiere.icommon.distributed;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICacheService {

    <K, V> Map<K, V> getMap(String name);

    <K> List<K> getList(String name);

    <K> Set<K> getSet(String name);
}
