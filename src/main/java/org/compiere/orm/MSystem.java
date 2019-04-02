package org.compiere.orm;

import org.idempiere.common.util.CCache;

public class MSystem extends X_AD_System {
    /**
     * System - cached
     */
    private static CCache<Integer, MSystem> s_system =
            new CCache<>(Table_Name, 1, -1, true);

    /**
     * ************************************************************************ Default Constructor
     *
     */
    public MSystem() {
        super(0);

        load(); // 	load ID=0
        s_system.putIfAbsent(0, this);
    } //	MSystem

    /**
     * Load System Record
     *
     * @return System
     */
    public static synchronized MSystem get() {
        if (s_system.get(0) != null) return s_system.get(0);
        //
        MSystem system =
                new Query(Table_Name, null).setOrderBy(COLUMNNAME_AD_System_ID).firstOnly();
        if (system == null) return null;
        //
        s_system.put(0, system);
        return system;
    } //	get

}
