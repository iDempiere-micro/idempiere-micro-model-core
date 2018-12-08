package org.idempiere.common.util;

/**
 * Defines an object that can be ran into an transaction, using {@link Trx#run(TrxRunnable)} or
 * {@link Trx#run(String, TrxRunnable)} methods.
 *
 * @author Teo Sarca, http://www.arhipac.ro
 */
public interface TrxRunnable {
    void run(String trxName);
}
