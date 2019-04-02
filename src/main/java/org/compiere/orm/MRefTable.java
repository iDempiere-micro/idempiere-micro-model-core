package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Table;
import org.idempiere.orm.PO;

public class MRefTable extends X_AD_Ref_Table {
    /**
     *
     */
    private static final long serialVersionUID = 380648726485603193L;

    /**
     * Standard Constructor
     *
     * @param ctx             context
     * @param AD_Reference_ID id warning if you are referring to reference list or table type should
     *                        be used AD_Reference_Value_ID
     * @param trxName         trx
     */
    public MRefTable(int AD_Reference_ID) {
        super(AD_Reference_ID);
        if (AD_Reference_ID == 0) {
            //	setColumnTableId (0);
            //	setAD_Display (0);
            //	setAD_Key (0);
            setEntityType(PO.ENTITYTYPE_UserMaintained); // U
            setIsValueDisplayed(false);
        }
    } //	MRefTable

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName trx
     */
    public MRefTable(Row row) {
        super(row);
    } //	MRefTable

    @Override
    public I_AD_Table getTable() throws RuntimeException {
        MTable table = MTable.get(getRefTableId());
        return table;
    }
} //	MRefTable
