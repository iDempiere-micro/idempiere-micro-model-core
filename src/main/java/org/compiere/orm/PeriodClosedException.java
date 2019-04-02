package org.compiere.orm;

import org.idempiere.common.exceptions.AdempiereException;

import java.sql.Timestamp;

/**
 * Period Closed Exception. This exception is throwed by
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * <li>FR [ 2520591 ] Support multiples calendar for Org
 * @see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962
 */
public class PeriodClosedException extends AdempiereException {
    /**
     *
     */
    private static final long serialVersionUID = -2798371272365454799L;

    /**
     *
     */
    public PeriodClosedException(Timestamp dateAcct, String docBaseType) {
        super(
                "@PeriodClosed@ @Date@="
                        + dateAcct
                        + ", @DocBaseType@="
                        + MRefList.getListName(
                        X_C_DocType.DOCBASETYPE_AD_Reference_ID, docBaseType));
    }
}
