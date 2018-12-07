package org.idempiere.icommon.distributed;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author hengsin
 */
public interface IClusterService {

    /**
     * @return Collection of cluster member
     */
    Collection<IClusterMember> getMembers();

    /**
     * @return Local node
     */
    IClusterMember getLocalMember();

    /**
     * @param task
     * @param member
     * @return Future
     */
    <V> Future<V> execute(Callable<V> task, IClusterMember member);

    /**
     * @param task
     * @param members
     * @return Map of IClusterMember and Future
     */
    <V> Map<IClusterMember, Future<V>> execute(
            Callable<V> task, Collection<IClusterMember> members);
}
