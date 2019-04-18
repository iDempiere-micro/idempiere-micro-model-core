package org.compiere.orm;

import org.idempiere.common.util.CLogger;
import org.idempiere.orm.PO;
import software.hsharp.core.orm.DefaultBaseModelFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Default model factory implementation base on legacy code in MTable.
 *
 * @author Jorg Janke
 * @author hengsin
 */
public class DefaultModelFactory extends DefaultBaseModelFactory implements ModelFactory {

    private static final CLogger s_log = CLogger.getCLogger(DefaultModelFactory.class);

    @Override
    public PO getPO(String tableName, int recordId) {
        Class<?> clazz = getClass(tableName, true);
        if (clazz == null) {
            s_log.warning("No class for table: " + tableName + " called with Record_ID");
            return null;
        }

        boolean errorLogged = false;
        try {
            Constructor<?> constructor = null;
            try {
                try {
                    constructor = clazz.getDeclaredConstructor(int.class);
                    if (constructor != null && Modifier.isPrivate(constructor.getModifiers())) {
                        constructor.setAccessible(true);
                    }
                    try {
                        return constructor != null
                                ? (PO)
                                constructor.newInstance(
                                        new Object[]{recordId})
                                : null;
                    } catch (Exception ex) {
                        s_log.warning(
                                "PO FAILED for table '"
                                        + tableName
                                        + "', Record_ID:"
                                        + recordId
                                        + " and clazz '"
                                        + clazz.getCanonicalName()
                                        + "'");
                        throw ex;
                    }
                } catch (Exception e) {
                    // still can have the second constructor
                }


                constructor = clazz.getDeclaredConstructor(Properties.class, int.class);
                if (constructor != null && Modifier.isPrivate(constructor.getModifiers())) {
                    constructor.setAccessible(true);
                }
            } catch (Exception e) {
                String msg = e.getMessage();
                if (msg == null) msg = e.toString();
                s_log.warning("No transaction Constructor for " + clazz + " (" + msg + ")");
            }

            try {
                return constructor != null
                        ? (PO)
                        constructor.newInstance(
                                new Object[]{recordId})
                        : null;
            } catch (Exception ex) {
                s_log.warning(
                        "PO FAILED for table '"
                                + tableName
                                + "', Record_ID:"
                                + recordId
                                + " and clazz '"
                                + clazz.getCanonicalName()
                                + "'");
                throw ex;
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() != null) {
                Throwable t = e.getCause();
                s_log.log(Level.SEVERE, "(id) - Table=" + tableName + ",Class=" + clazz, t);
                errorLogged = true;
                if (t instanceof Exception) s_log.saveError("Error", (Exception) e.getCause());
                else s_log.saveError("Error", "Table=" + tableName + ",Class=" + clazz);
            } else {
                s_log.log(Level.SEVERE, "(id) - Table=" + tableName + ",Class=" + clazz, e);
                errorLogged = true;
                s_log.saveError("Error", "Table=" + tableName + ",Class=" + clazz);
            }
        }
        if (!errorLogged)
            s_log.log(Level.SEVERE, "(id) - Not found - Table=" + tableName + ", Record_ID=" + recordId);
        return null;
    }
}
