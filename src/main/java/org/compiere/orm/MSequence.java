package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.I_AD_Sequence;
import org.idempiere.common.exceptions.AdempiereException;
import org.idempiere.common.util.CLogMgt;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Util;
import org.idempiere.icommon.model.IPO;
import software.hsharp.core.orm.MBaseSequence;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;

import static software.hsharp.core.orm.MBaseSequenceKt.PREFIX_DOCSEQ;
import static software.hsharp.core.orm.MBaseSequenceKt.doGetDocumentNoFromSeq;
import static software.hsharp.core.orm.MBaseSequenceKt.doGetNextIDImpl;
import static software.hsharp.core.util.DBKt.NYI;
import static software.hsharp.core.util.DBKt.getSQLValue;
import static software.hsharp.core.util.DBKt.getSQLValueString;

/**
 * Sequence Model.
 *
 * @author Jorg Janke
 * @version $Id: MSequence.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 * @see org.compiere.process.SequenceCheck
 */
public class MSequence extends MBaseSequence {
    /**
     * Start Number
     */
    public static final int INIT_NO = 1000000; // 	1M
    /**
     * Start System Number
     */
    // public static final int		INIT_SYS_NO = 100; // start number for Compiere
    // public static final int		INIT_SYS_NO = 50000;   // start number for Adempiere
    public static final int INIT_SYS_NO = 200000; // start number for iDempiere
    /**
     *
     */
    private static final long serialVersionUID = 7331047665037991960L;
    /**
     * Log Level for Next ID Call
     */
    private static final Level LOGLEVEL = Level.ALL;

    /**
     * Static Logger
     */
    private static CLogger s_log = CLogger.getCLogger(MSequence.class);
    /**
     * Test
     */
    private static Vector<Integer> s_list = null;

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ctx            context
     * @param AD_Sequence_ID id
     */
    public MSequence(int AD_Sequence_ID) {
        super(AD_Sequence_ID);
        if (AD_Sequence_ID == 0) {
            //	setName (null);
            //
            setIsTableID(false);
            setStartNo(INIT_NO);
            setCurrentNext(INIT_NO);
            setCurrentNextSys(INIT_SYS_NO);
            setIncrementNo(1);
            setIsAutoSequence(true);
            setIsAudited(false);
            setStartNewYear(false);
        }
    } //	MSequence

    /**
     * Load Constructor
     */
    public MSequence(Row row) {
        super(row);
    }

    /**
     * New Document Sequence Constructor
     *
     * @param ctx          context
     * @param AD_Client_ID owner
     * @param tableName    name
     */
    public MSequence(int AD_Client_ID, String tableName) {
        this(0);
        setClientOrg(AD_Client_ID, 0); // 	Client Ownership
        setName(PREFIX_DOCSEQ + tableName);
        setDescription("DocumentNo/Value for Table " + tableName);
    } //	MSequence;

    /**
     * New Document Sequence Constructor
     *
     * @param ctx          context
     * @param AD_Client_ID owner
     * @param sequenceName name
     * @param StartNo      start
     */
    public MSequence(
            int AD_Client_ID, String sequenceName, int StartNo) {
        this(0);
        setClientOrg(AD_Client_ID, 0); // 	Client Ownership
        setName(sequenceName);
        setDescription(sequenceName);
        setStartNo(StartNo);
        setCurrentNext(StartNo);
        setCurrentNextSys(StartNo / 10);
    } //	MSequence;

    /**
     * Get next number for Key column = 0 is Error.
     *
     * @param AD_Client_ID client
     * @param TableName    table name
     * @return next no
     */
    @SuppressWarnings("deprecation")
    public static int getNextID(int AD_Client_ID, String TableName) {
        boolean SYSTEM_NATIVE_SEQUENCE =
                MSysConfig.getBooleanValue(MSysConfig.SYSTEM_NATIVE_SEQUENCE, false);
        //	Check AdempiereSys
        boolean adempiereSys = false;
        String sysProperty = Env.getCtx().getProperty("AdempiereSys", "N");
        adempiereSys = "y".equalsIgnoreCase(sysProperty) || "true".equalsIgnoreCase(sysProperty);

        if (SYSTEM_NATIVE_SEQUENCE && !adempiereSys) {
            int m_sequence_id = software.hsharp.core.util.DBKt.getNextID(TableName + "_SQ");
            if (m_sequence_id == -1) {
                // try to create the sequence and try again
                MSequence.createTableSequence(TableName, true);
                m_sequence_id = software.hsharp.core.util.DBKt.getNextID(TableName + "_SQ");
            }
            return m_sequence_id;
        }

        return MSequence.getNextIDImpl(
                AD_Client_ID, TableName); // it is ok to call deprecated method here
    } //	getNextID

    /**
     * Get next number for Key column = 0 is Error.
     *
     * @param AD_Client_ID client
     * @param TableName    table name
     * @return next no or (-1=not found, -2=error)
     * <p>WARNING!! This method doesn't take into account the native sequence setting, it's just
     * to be called fromgetNextID()
     * @deprecated please usegetNextID (int, String, String)
     */
    private static int getNextIDImpl(int AD_Client_ID, String TableName) {
        return doGetNextIDImpl(TableName);
    } //	getNextID

    /**
     * ************************************************************************ Get Document No from
     * table (when the document doesn't have a c_doctype)
     *
     * @param TableName table name
     * @return document no or null
     */
    public static String getDocumentNo(String TableName, PO po) {
        if (TableName == null || TableName.length() == 0)
            throw new IllegalArgumentException("TableName missing");

        MSequence seq = get(TableName,  /*tableID=*/ false);
        if (seq == null || seq.getId() == 0) {
            if (!MSequence.createTableSequence(TableName,  /*tableID=*/ false))
                throw new AdempiereException("Could not create table sequence");
            seq = get(TableName,  /*tableID=*/ false);
            if (seq == null || seq.getId() == 0)
                throw new AdempiereException("Could not find table sequence");
        }

        return getDocumentNoFromSeq(seq, po);
    } //	getDocumentNo

    /**
     * Parse expression, replaces global or PO properties @tag@ with actual value.
     *
     * @param expression
     * @param po
     * @param trxName
     * @return String
     */
    public static String parseVariable(
            String expression, IPO po, String trxName, boolean keepUnparseable) {
        if (expression == null || expression.length() == 0) return "";

        String token;
        String inStr = expression;
        StringBuilder outStr = new StringBuilder();

        int i = inStr.indexOf('@');
        while (i != -1) {
            outStr.append(inStr, 0, i); // up to @
            inStr = inStr.substring(i + 1); // from first @

            int j = inStr.indexOf('@'); // next @
            if (j < 0) {
                s_log.log(Level.SEVERE, "No second tag: " + inStr);
                return ""; //	no second tag
            }

            token = inStr.substring(0, j);

            // format string
            String format = "";
            int f = token.indexOf('<');
            if (f > 0 && token.endsWith(">")) {
                format = token.substring(f + 1, token.length() - 1);
                token = token.substring(0, f);
            }


            if (token.startsWith("#") || token.startsWith("$")) {
                // take from context
                String v = Env.getContext(token);
                if (v != null && v.length() > 0) outStr.append(v);
                else if (keepUnparseable) {
                    outStr.append("@").append(token);
                    if (!Util.isEmpty(format)) outStr.append("<").append(format).append(">");
                    outStr.append("@");
                }
            } else if (po != null) {
                // take from po
                if (po.getColumnIndex(token) >= 0) {
                    Object v = po.getValue(token);
                    MColumn colToken = MColumn.get(po.getTableName(), token);
                    String foreignTable = colToken.getReferenceTableName();
                    if (v != null) {
                        if (format != null && format.length() > 0) {
                            if (v instanceof Integer && (Integer) v > 0 && !Util.isEmpty(foreignTable)) {
                                int tblIndex = format.indexOf(".");
                                String tableName = null;
                                if (tblIndex > 0) tableName = format.substring(0, tblIndex);
                                else tableName = foreignTable;
                                MTable table = MTable.get(tableName);
                                if (table != null && tableName.equalsIgnoreCase(foreignTable)) {
                                    String columnName = tblIndex > 0 ? format.substring(tblIndex + 1) : format;
                                    MColumn column = table.getColumn(columnName);
                                    if (column != null) {
                                        if (column.isSecure()) {
                                            outStr.append("********");
                                        } else {
                                            String value =
                                                    getSQLValueString(
                                                            "SELECT "
                                                                    + columnName
                                                                    + " FROM "
                                                                    + tableName
                                                                    + " WHERE "
                                                                    + tableName
                                                                    + "_ID = ?",
                                                            (Integer) v);
                                            if (value != null) outStr.append(value);
                                        }
                                    }
                                }
                            } else if (v instanceof Date) {
                                SimpleDateFormat df = new SimpleDateFormat(format);
                                outStr.append(df.format((Date) v));
                            } else if (v instanceof Number) {
                                DecimalFormat df = new DecimalFormat(format);
                                outStr.append(df.format(((Number) v).doubleValue()));
                            } else {
                                MessageFormat mf = new MessageFormat(format);
                                outStr.append(mf.format(v));
                            }
                        } else {
                            if (colToken != null && colToken.isSecure()) {
                                v = "********";
                            }
                            outStr.append(v.toString());
                        }
                    }
                } else if (keepUnparseable) {
                    outStr.append("@").append(token);
                    if (!Util.isEmpty(format)) outStr.append("<").append(format).append(">");
                    outStr.append("@");
                }
            } else if (keepUnparseable) {
                outStr.append("@" + token);
                if (format.length() > 0) outStr.append("<" + format + ">");
                outStr.append("@");
            }

            inStr = inStr.substring(j + 1); // from second @
            i = inStr.indexOf('@');
        }
        outStr.append(inStr); // add the rest of the string

        return outStr.toString();
    }

    public static String getDocumentNoFromSeq(MSequence seq, PO po) {
        return doGetDocumentNoFromSeq(seq, po);
    }

    /**
     * Get Document No based on Document Type
     *
     * @param C_DocType_ID document type
     * @return document no or null
     * @deprecated
     */
    public static String getDocumentNo(int C_DocType_ID) {
        return getDocumentNo(C_DocType_ID, false);
    } //	getDocumentNo

    /**
     * Get Document No based on Document Type
     *
     * @param C_DocType_ID document type
     * @param definite     asking for a definitive or temporary sequence
     * @return document no or null
     */
    public static String getDocumentNo(int C_DocType_ID, boolean definite) {
        return getDocumentNo(C_DocType_ID, definite, null);
    }

    /**
     * Get Document No based on Document Type
     *
     * @param C_DocType_ID document type
     * @param definite     asking for a definitive or temporary sequence
     * @param po
     * @return document no or null
     */
    public static String getDocumentNo(int C_DocType_ID, boolean definite, PO po) {
        if (C_DocType_ID == 0) {
            s_log.severe("C_DocType_ID=0");
            return null;
        }

        MDocType dt = MDocType.get(C_DocType_ID); // 	wrong for SERVER, but r/o
        if (dt != null && !dt.isDocNoControlled()) {
            if (s_log.isLoggable(Level.FINER))
                s_log.finer("DocType_ID=" + C_DocType_ID + " Not DocNo controlled");
            return null;
        }
        if (definite && !dt.isOverwriteSeqOnComplete()) {
            s_log.warning("DocType_ID=" + C_DocType_ID + " Not Sequence Overwrite on Complete");
            return null;
        }
        if (dt == null || dt.getDocNoSequenceId() == 0) {
            s_log.warning("No Sequence for DocType - " + dt);
            return null;
        }
        if (definite && dt.getDefiniteSequenceId() == 0) {
            s_log.warning("No Definite Sequence for DocType - " + dt);
            return null;
        }
        int seqID = (definite ? dt.getDefiniteSequenceId() : dt.getDocNoSequenceId());
        MSequence seq = new MSequence(seqID);

        if (CLogMgt.isLevel(LOGLEVEL))
            s_log.log(LOGLEVEL, "DocType_ID=" + C_DocType_ID);

        return getDocumentNoFromSeq(seq, po);
    } //	getDocumentNo

    public static boolean createTableSequence(String TableName) {
        return createTableSequence(TableName, true);
    }

    /**
     * Create Table ID Sequence
     *
     * @param TableName table name
     * @return true if created
     */
    public static boolean createTableSequence(
            String TableName, boolean tableID) {
        boolean SYSTEM_NATIVE_SEQUENCE =
                MSysConfig.getBooleanValue(MSysConfig.SYSTEM_NATIVE_SEQUENCE, false);

        if (tableID && SYSTEM_NATIVE_SEQUENCE) {
            throw new IllegalArgumentException(NYI);
        }

        MSequence seq = new MSequence(0);
        if (tableID) seq.setClientOrg(0, 0);
        else seq.setClientOrg(Env.getClientId(), 0);

        if (tableID) {
            seq.setName(TableName);
            seq.setDescription("Table " + TableName);
        } else {
            seq.setName(PREFIX_DOCSEQ + TableName);
            seq.setDescription("DocumentNo/Value for Table " + TableName);
        }
        seq.setIsTableID(tableID);
        seq.saveEx();

        return true;
    } //	createTableSequence

    /* Get the tableID sequence based on the TableName */
    public static MSequence get(String tableName) {
        return get(tableName, true);
    }

    /**
     * Get Sequence
     *
     * @param tableName table name
     * @param tableID
     * @return Sequence
     */
    public static MSequence get(String tableName, boolean tableID) {
        return software.hsharp.core.orm.MBaseSequenceKt.get(tableName, tableID);
    } //	get

    /**
     * Validate Table Sequence Values
     *
     * @return true if updated
     */
    public String validateTableIDValue() {
        if (!isTableID()) return null;
        String tableName = getName();
        int AD_Column_ID =
                getSQLValue(
                        "SELECT MAX(c.AD_Column_ID) "
                                + "FROM AD_Table t"
                                + " INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID) "
                                + "WHERE t.TableName='"
                                + tableName
                                + "'"
                                + " AND t.IsView='N'" // ignore for views -- IDEMPIERE-2513
                                + " AND c.ColumnName='"
                                + tableName
                                + "_ID'");
        if (AD_Column_ID <= 0) return null;
        //
        MSystem system = MSystem.get();
        int IDRangeEnd = 0;
        if (system.getIDRangeEnd() != null) IDRangeEnd = system.getIDRangeEnd().intValue();

        String changeMsg = null;
        String info = null;

        //	Current Next
        String sql = "SELECT MAX(" + tableName + "_ID) FROM " + tableName;
        if (IDRangeEnd > 0) sql += " WHERE " + tableName + "_ID < " + IDRangeEnd;
        int maxTableID = getSQLValue(sql);
        if (maxTableID < INIT_NO) maxTableID = INIT_NO - 1;
        maxTableID++; //	Next

        int currentNextValue = getCurrentNext();
        if (currentNextValue < maxTableID) {
            setCurrentNext(maxTableID);
            info = "CurrentNext=" + maxTableID;
            changeMsg = getName() + " ID  " + currentNextValue + " -> " + maxTableID;
        }

        //	Get Max System_ID used in Table
        sql =
                "SELECT MAX("
                        + tableName
                        + "_ID) FROM "
                        + tableName
                        + " WHERE "
                        + tableName
                        + "_ID < "
                        + INIT_NO;
        int maxTableSysID = getSQLValue(sql);
        if (maxTableSysID <= 0) maxTableSysID = INIT_SYS_NO;
        int currentNextSysValue = getCurrentNextSys();
        if (currentNextSysValue < maxTableSysID) {
            setCurrentNextSys(maxTableSysID);
            if (info == null) info = "CurrentNextSys=" + maxTableSysID;
            else info += " - CurrentNextSys=" + maxTableSysID;

            if (changeMsg == null)
                changeMsg = getName() + " Sys " + currentNextSysValue + " -> " + maxTableSysID;
            else changeMsg += " - " + getName() + " Sys " + currentNextSysValue + " -> " + maxTableSysID;
        }
        if (info != null) if (log.isLoggable(Level.FINE)) log.fine(getName() + " - " + info);

        return changeMsg;
    } //	validate

    @Override
    public int getCurrentNext() {
        if (MSysConfig.getBooleanValue(MSysConfig.SYSTEM_NATIVE_SEQUENCE, false) && isTableID()) {
            return MSequence.getNextID(getClientId(), getName());
        } else {
            return super.getCurrentNext();
        }
    }

    @Override
    public void setCurrentNext(int CurrentNext) {
        if (MSysConfig.getBooleanValue(MSysConfig.SYSTEM_NATIVE_SEQUENCE, false) && isTableID()) {
            while (true) {
                int id = MSequence.getNextID(getClientId(), getName());
                if (id < 0 || id >= (CurrentNext - 1)) break;
            }
        } else {
            super.setCurrentNext(CurrentNext);
        }
    }

    @Override
    protected boolean beforeSave(boolean newRecord) {
        if (isStartNewMonth() && !isStartNewYear()) setStartNewMonth(false);
        return true;
    }

    @Override
    public String getOrgColumn() {
        if (super.getOrgColumn() == null) return I_AD_Sequence.COLUMNNAME_AD_Org_ID;
        else return super.getOrgColumn();
    }

    /**
     * Test Sequence - Get IDs
     *
     * @author Jorg Janke
     * @version $Id: MSequence.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
     */
    public static class GetIDs implements Runnable {
        @SuppressWarnings("unused")
        private int m_i;

        /**
         * Run
         */
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    int no = MSequence.getNextID(0, "Test");
                    s_list.add(no);
                    //	System.out.println("#" + m_i + ": " + no);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    } //	GetIDs
} //	MSequence
