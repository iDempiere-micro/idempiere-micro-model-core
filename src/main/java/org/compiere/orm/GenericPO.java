// Generic PO.
package org.compiere.orm;

// import for GenericPO

import kotliquery.Row;
import org.idempiere.orm.POInfo;

/**
 * Generic PO implementation, this can be use together with ModelValidator as alternative to the
 * classic generated model class and extend ( X_ & M_ ) approach.
 *
 * <p>Originally for used to insert/update data from adempieredata.xml file in 2pack.
 *
 * @author Marco LOMBARDO
 * @contributor Low Heng Sin
 */
public class GenericPO extends PO {

    /**
     *
     */
    private static final long serialVersionUID = -6558017105997010172L;

    /**
     * @param ID
     */
    public GenericPO(int ID) {
        super(ID);
    }

    /**
     */
    public GenericPO(Row row) {
        super(row);
    }

    public String toString() {
        return "GenericPO[Table=" +
                "" + getTableId() + ",ID=" +
                getId() +
                "]";
    }

    @Override
    protected int getAccessLevel() {
        POInfo p_info = super.getP_info();
        return Integer.parseInt(p_info.getAccessLevel());
    }

    @Override
    public int getTableId() {
        return 0;
    }
} // GenericPO

