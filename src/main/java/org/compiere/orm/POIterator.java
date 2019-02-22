package org.compiere.orm;

import java.util.Iterator;
import java.util.List;

/**
 * Iterator implementation to fetch PO one at a time using a prefetch ID list.
 *
 * @author Low Heng Sin
 */
public class POIterator<T extends PO> implements Iterator<T> {

    private MTable table;
    private List<Object[]> idList;

    private int iteratorIndex = -1;
    private String trxName;

    private String keyWhereClause = null;

    /**
     * @param table
     * @param idList
     * @param trxName
     */
    public POIterator(MTable table, List<Object[]> idList) {
        this.table = table;
        this.idList = idList;
        this.trxName = trxName;
    }

    /**
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext() {
        return (iteratorIndex < (idList.size() - 1));
    }

    /**
     * @see java.util.Iterator#next()
     */
    public T next() {
        if (iteratorIndex < (idList.size() - 1)) {
            iteratorIndex++;
            return get(iteratorIndex);
        } else {
            return null;
        }
    }

    /**
     * not supported.
     */
    public void remove() {
        throw new UnsupportedOperationException("Remove operatiotn not supported.");
    }

    /**
     * @param index
     * @return PO or null if index is invalid
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index <= (idList.size() - 1)) {
            Object[] ids = idList.get(index);
            if (ids.length == 1 && (ids[0] instanceof Number)) {
                return (T) table.getPO(((Number) ids[0]).intValue());
            } else {
                if (keyWhereClause == null) {
                    String[] keys = table.getKeyColumns();
                    StringBuilder sqlBuffer = new StringBuilder();
                    for (int i = 0; i < keys.length; i++) {
                        if (i > 0) sqlBuffer.append(" AND ");
                        sqlBuffer.append(keys[i]).append(" = ? ");
                    }
                    keyWhereClause = sqlBuffer.toString();
                }
                return (T) table.getPO(keyWhereClause, ids);
            }
        } else {
            return null;
        }
    }
}
