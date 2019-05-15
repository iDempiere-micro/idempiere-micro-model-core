package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.AccountingElementValue;
import org.compiere.util.MsgKt;
import org.idempiere.common.exceptions.AdempiereException;
import org.idempiere.common.exceptions.DBException;
import org.idempiere.common.util.AdempiereUserError;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.ValueNamePair;
import org.idempiere.icommon.model.PersistentObject;
import org.idempiere.orm.Null;
import org.idempiere.orm.POInfo;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;

import static software.hsharp.core.orm.POKt.I_ZERO;
import static software.hsharp.core.util.DBKt.executeUpdate;
import static software.hsharp.core.util.DBKt.executeUpdateEx;
import static software.hsharp.core.util.DBKt.getSQLValue;
import static software.hsharp.core.util.DBKt.getSQLValueEx;
import static software.hsharp.core.util.DBKt.isGenerateUUIDSupported;

public abstract class PO extends org.idempiere.orm.PO {

    public PO(int ID) {
        super(ID);
    }

    public PO(Row row) {
        super(row);
    }

    public PO(Row row, int ID) {
        super(row, ID);
    }


    /**
     * Copy old values of From to new values of To. Does not copy Keys
     *
     * @param from         old, existing & unchanged PO
     * @param to           new, not saved PO
     * @param AD_Client_ID client
     * @param AD_Org_ID    org
     */
    public static void copyValues(PO from, PO to, int AD_Client_ID, int AD_Org_ID) {
        Companion.copyValues(from, to);
        to.setClientId(AD_Client_ID);
        to.setOrgId(AD_Org_ID);
    } //	copyValues

    /**
     * Copy old values of From to new values of To. Does not copy Keys
     *
     * @param from old, existing & unchanged PO
     * @param to   new, not saved PO
     */
    public static void copyValues(PO from, PO to) {
        Companion.copyValues(from, to);
    } //	copyValues

    /**
     * Is new record
     *
     * @return true if new
     */
    public boolean isNew() {
        if (getCreateNew()) return true;
        //
        for (int i = 0; i < getIds().length; i++) {
            if (getIds()[i].equals(I_ZERO) || getIds()[i] == Null.NULL) continue;
            return false; //	one value is non-zero
        }
        return !MTableKt.isZeroIDTable(getTableName());
    } //	isNew

    /**
     * Overwrite Client Org if different
     *
     * @param AD_Client_ID client
     * @param AD_Org_ID    org
     */
    public void setClientOrg(int AD_Client_ID, int AD_Org_ID) {
        if (AD_Client_ID != getClientId()) setClientId(AD_Client_ID);
        if (AD_Org_ID != getOrgId()) setOrgId(AD_Org_ID);
    } //	setClientOrg

    /**
     * ************************************************************************ Set AD_Client
     *
     * @param AD_Client_ID client
     */
    public void setClientId(int AD_Client_ID) {
        setValueNoCheck("AD_Client_ID", AD_Client_ID);
    } //	setClientId

    /**
     * Overwrite Client Org if different
     *
     * @param po persistent object
     */
    public void setClientOrg(PersistentObject po) {
        setClientOrg(po.getClientId(), po.getOrgId());
    } //	setClientOrg

    /**
     * Update Value or create new record.
     *
     * @throws AdempiereException
     * @see #save()
     */
    public void saveEx() throws AdempiereException {
        if (!save()) {
            String msg = null;
            ValueNamePair err = CLogger.retrieveError();
            String val = err != null ? MsgKt.translate(err.getValue()) : "";
            if (err != null) msg = (val != null ? val + ": " : "") + err.getName();
            if (msg == null || msg.length() == 0) msg = "SaveError";
            throw new AdempiereException(msg);
        }
    }

    @Override
    protected boolean saveNew() {
        POInfo p_info = super.getP_info();
        //  Set ID for single key - Multi-Key values need explicitly be set previously
        if (getIds().length == 1
                && p_info.getHasKeyColumn()
                && getM_keyColumns()[0].endsWith("_ID")) // 	AD_Language, EntityType
        {
            int no = saveNew_getID();
            if (no <= 0) no = MSequence.getNextID(getClientId(), p_info.getTableName());
            // the primary key is not overwrite with the local sequence
            if (isReplication() && getId() > 0) {
                no = getId();
            }
            if (no <= 0) {
                log.severe("No NextID (" + no + ")");
                return saveFinish(true, false);
            }
            getIds()[0] = no;
            setValueNoCheck(getM_keyColumns()[0], getIds()[0]);
        }
        // uuid secondary key
        int uuidIndex = p_info.getColumnIndex(getUUIDColumnName());
        if (uuidIndex >= 0) {
            String value = getValue(uuidIndex);
            if (p_info.getColumn(uuidIndex).FieldLength == 36 && (value == null || value.length() == 0)) {
                UUID uuid = UUID.randomUUID();
                setValueNoCheck(p_info.getColumnName(uuidIndex), uuid.toString());
            }
        }
        if (log.isLoggable(Level.FINE))
            log.fine(p_info.getTableName() + " - " + getWhereClause(true));

        //	Set new DocumentNo
        String columnName = "DocumentNo";
        int index = p_info.getColumnIndex(columnName);
        if (index != -1) {
            String value = getValue(index);
            if (value != null && value.startsWith("<") && value.endsWith(">")) value = null;
            if (value == null || value.length() == 0) {
                int dt = p_info.getColumnIndex("C_DocTypeTarget_ID");
                if (dt == -1) dt = p_info.getColumnIndex("C_DocType_ID");
                if (dt != -1) // 	get based on Doc Type (might return null)
                    value = MSequence.getDocumentNo(getValueAsInt(dt), false, this);
                if (value == null) // 	not overwritten by DocType and not manually entered
                    value = MSequence.getDocumentNo(p_info.getTableName(), this);
                setValueNoCheck(columnName, value);
            }
        }

        boolean ok = doInsert(isLogSQLScript());
        return saveFinish(true, ok);
    } //  saveNew

    /**
     * Insert id data into Tree
     *
     * @param treeType MTree TREETYPE_*
     * @return true if inserted
     */
    protected boolean insertTree(String treeType) {
        return insertTree(treeType, 0);
    } //	insertTree

    /**
     * Insert id data into Tree
     *
     * @param treeType     MTree TREETYPE_*
     * @param C_Element_ID element for accounting element values
     * @return true if inserted
     */
    protected boolean insertTree(String treeType, int C_Element_ID) {
        String tableName = MTree_BaseKt.getNodeTableName(treeType);

        // check whether db have working generate_uuid function.
        boolean uuidFunction = isGenerateUUIDSupported();

        // uuid column
        int uuidColumnId =
                getSQLValue(
                        "SELECT col.AD_Column_ID FROM AD_Column col INNER JOIN AD_Table tbl ON col.AD_Table_ID = tbl.AD_Table_ID WHERE tbl.TableName=? AND col.ColumnName=?",
                        tableName,
                        org.idempiere.orm.PO.getUUIDColumnName(tableName));

        StringBuilder sb =
                new StringBuilder("INSERT INTO ")
                        .append(tableName)
                        .append(
                                " (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, "
                                        + "AD_Tree_ID, Node_ID, Parent_ID, SeqNo");
        if (uuidColumnId > 0 && uuidFunction)
            sb.append(", ").append(org.idempiere.orm.PO.getUUIDColumnName(tableName)).append(") ");
        else sb.append(") ");
        sb.append("SELECT t.AD_Client_ID, 0, 'Y', SysDate, ").append(getUpdatedBy()).append(", SysDate, ").append(getUpdatedBy()).append(",").append("t.AD_Tree_ID, ")
                .append(getId())
                .append(", 0, 999");
        if (uuidColumnId > 0 && uuidFunction) sb.append(", Generate_UUID() ");
        else sb.append(" ");
        sb.append("FROM AD_Tree t " + "WHERE t.AD_Client_ID=")
                .append(getClientId())
                .append(" AND t.IsActive='Y'");
        //	Account Element Value handling
        if (C_Element_ID != 0)
            sb.append(" AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=")
                    .append(C_Element_ID)
                    .append(" AND t.AD_Tree_ID=ae.AD_Tree_ID)");
        else //	std trees
            sb.append(" AND t.IsAllNodes='Y' AND t.TreeType='").append(treeType).append("'");
        if (MTree_Base.TREETYPE_CustomTable.equals(treeType))
            sb.append(" AND t.AD_Table_ID=").append(getTableId());
        //	Duplicate Check
        sb.append(" AND NOT EXISTS (SELECT * FROM ").append(MTree_BaseKt.getNodeTableName(treeType)).append(" e ").append("WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=")
                .append(getId())
                .append(")");
        int no = executeUpdate(sb.toString());
        if (no > 0) {
            if (log.isLoggable(Level.FINE)) log.fine("#" + no + " - TreeType=" + treeType);
        } else {
            if (!MTree_Base.TREETYPE_CustomTable.equals(treeType))
                log.warning("#" + no + " - TreeType=" + treeType);
        }

        return no > 0;
    } //	insertTree

    /**
     * Update parent key and seqno based on value if the tree is driven by value
     *
     * @param treeType MTree TREETYPE_*
     * @return true if inserted
     */
    public void updateTree(String treeType) {
        int idxValueCol = getColumnIndex("Value");
        if (idxValueCol < 0) return;
        int idxValueIsSummary = getColumnIndex("IsSummary");
        if (idxValueIsSummary < 0) return;
        String value = getValue(idxValueCol).toString();
        if (value == null) return;

        String tableName = MTree_BaseKt.getNodeTableName(treeType);
        String sourceTableName;
        String whereTree;
        Object[] parameters;
        if (MTree_Base.TREETYPE_CustomTable.equals(treeType)) {
            sourceTableName = this.getTableName();
            whereTree = "TreeType=? AND AD_Table_ID=?";
            parameters = new Object[]{treeType, this.getTableId()};
        } else {
            sourceTableName = MTree_BaseKt.getSourceTableName(treeType);
            if (MTree_Base.TREETYPE_ElementValue.equals(treeType) && this instanceof AccountingElementValue) {
                whereTree = "TreeType=? AND AD_Tree_ID=?";
                parameters =
                        new Object[]{treeType, ((AccountingElementValue) this).getElement().getTreeId()};
            } else {
                whereTree = "TreeType=?";
                parameters = new Object[]{treeType};
            }
        }
        String updateSeqNo =
                "UPDATE "
                        + tableName
                        + " SET SeqNo=SeqNo+1 WHERE Parent_ID=? AND SeqNo>=? AND AD_Tree_ID=?";
        String update =
                "UPDATE " + tableName + " SET SeqNo=?, Parent_ID=? WHERE Node_ID=? AND AD_Tree_ID=?";
        String selMinSeqNo =
                "SELECT COALESCE(MIN(tn.SeqNo),-1) FROM AD_TreeNode tn JOIN "
                        + sourceTableName
                        + " n ON (tn.Node_ID=n."
                        + sourceTableName
                        + "_ID) WHERE tn.Parent_ID=? AND tn.AD_Tree_ID=? AND n.Value>?";
        String selMaxSeqNo =
                "SELECT COALESCE(MAX(tn.SeqNo)+1,999) FROM AD_TreeNode tn JOIN "
                        + sourceTableName
                        + " n ON (tn.Node_ID=n."
                        + sourceTableName
                        + "_ID) WHERE tn.Parent_ID=? AND tn.AD_Tree_ID=? AND n.Value<?";

        List<X_AD_Tree> trees =
                new Query(MTree_Base.Table_Name, whereTree)
                        .setClientId()
                        .setOnlyActiveRecords(true)
                        .setParameters(parameters)
                        .list();

        for (X_AD_Tree tree : trees) {
            if (tree.isTreeDrivenByValue()) {
                int newParentID;
                if (AccountingElementValue.Table_Name.equals(sourceTableName)) {
                    newParentID =
                            retrieveIdOfElementValue(
                                    value,
                                    getClientId(),
                                    ((AccountingElementValue) this).getElement().getElementId()
                            );
                } else {
                    newParentID = retrieveIdOfParentValue(value, sourceTableName, getClientId());
                }
                int seqNo = getSQLValueEx(selMinSeqNo, newParentID, tree.getTreeId(), value);
                if (seqNo == -1)
                    seqNo = getSQLValueEx(selMaxSeqNo, newParentID, tree.getTreeId(), value);
                executeUpdateEx(updateSeqNo, new Object[]{newParentID, seqNo, tree.getTreeId()});
                executeUpdateEx(
                        update, new Object[]{seqNo, newParentID, getId(), tree.getTreeId()});
            }
        }
    } //	updateTree

    /**
     * Delete ID Tree Nodes
     *
     * @param treeType MTree TREETYPE_*
     * @return true if deleted
     */
    protected boolean deleteTree(String treeType) {
        int id = getId();
        if (id == 0) id = getDeletedSingleKeyRecordId();

        // IDEMPIERE-2453
        StringBuilder countSql =
                new StringBuilder("SELECT COUNT(*) FROM ")
                        .append(MTree_BaseKt.getNodeTableName(treeType))
                        .append(" n JOIN AD_Tree t ON n.AD_Tree_ID=t.AD_Tree_ID")
                        .append(" WHERE Parent_ID=? AND t.TreeType=?");
        if (MTree_Base.TREETYPE_CustomTable.equals(treeType))
            countSql.append(" AND t.AD_Table_ID=").append(getTableId());
        int cnt = getSQLValueEx(countSql.toString(), id, treeType);
        if (cnt > 0)
            throw new AdempiereException(MsgKt.getMsg("NoParentDelete", new Object[]{cnt}));

        StringBuilder sb =
                new StringBuilder("DELETE FROM ")
                        .append(MTree_BaseKt.getNodeTableName(treeType))
                        .append(" n WHERE Node_ID=")
                        .append(id)
                        .append(
                                " AND EXISTS (SELECT * FROM AD_Tree t "
                                        + "WHERE t.AD_Tree_ID=n.AD_Tree_ID AND t.TreeType='")
                        .append(treeType)
                        .append("'");
        if (MTree_Base.TREETYPE_CustomTable.equals(treeType))
            sb.append(" AND t.AD_Table_ID=").append(getTableId());
        sb.append(")");
        int no = executeUpdate(sb.toString());
        if (no > 0) {
            if (log.isLoggable(Level.FINE)) log.fine("#" + no + " - TreeType=" + treeType);
        } else {
            if (!MTree_Base.TREETYPE_CustomTable.equals(treeType))
                log.warning("#" + no + " - TreeType=" + treeType);
        }
        return no > 0;
    } //	deleteTree

    /**
     * ************************************************************************ Delete Current Record
     *
     * @param force delete also processed records
     * @return true if deleted
     */
    public boolean delete(boolean force) {
        checkValidContext();
        CLogger.resetLast();
        if (isNew()) return true;
        POInfo p_info = super.getP_info();

        if (!force) {
            int iProcessed = getColumnIndex("Processed");
            if (iProcessed != -1) {
                Boolean processed = getValue(iProcessed);
                if (processed != null && processed) {
                    log.warning("Record processed"); // 	CannotDeleteTrx
                    log.saveError("Processed", "Processed", false);
                    return false;
                }
            } //	processed
        } //	force

        // Carlos Ruiz - globalqss - IDEMPIERE-111
        // Check if the role has access to this client
        // Don't check role System as webstore works with this role - see IDEMPIERE-401
        if ( /*(Env.getRoleId(Env.getCtx()) != 0) DAP
                &&*/ 0 != 0 && !MRoleKt.getDefaultRole().isClientAccess(getClientId(), true)) {
            log.warning("You cannot delete this record, role doesn't have access");
            log.saveError("AccessCannotDelete", "", false);
            return false;
        }

        boolean success;

        if (!beforeDelete()) {
            log.warning("beforeDelete failed");
            throw new Error("beforeDelete failed");
        }
        setReplication(false); // @Trifon

        try {
            //
            if (getColumnIndex("IsSummary") >= 0) {
                deleteTree(MTree_Base.TREETYPE_CustomTable);
            }

            //	The Delete Statement
            StringBuilder sql =
                    new StringBuilder("DELETE FROM ") // jz why no FROM??
                            .append(p_info.getTableName())
                            .append(" WHERE ")
                            .append(getWhereClause(true));
            int no;
            if (isUseTimeoutForUpdate()) no = executeUpdateEx(sql.toString());
            else no = executeUpdate(sql.toString());
            success = no == 1;
        } catch (Exception e) {
            String msg = DBException.getDefaultDBExceptionMessage(e);
            log.saveError(msg != null ? msg : e.getLocalizedMessage(), e);
            success = false;
        }

        //	Save ID
        m_idOld = getId();
        //
        if (!success) {
            log.warning("Not deleted");
            throw new Error("Not deleted");
        }

        try {
            success = afterDelete(success);
        } catch (Exception e) {
            log.log(Level.WARNING, "afterDelete", e);
            String msg = DBException.getDefaultDBExceptionMessage(e);
            log.saveError(msg != null ? msg : "Error", e, false);
            success = false;
            //	throw new DBException(e);
        }

        if (!success) {
            throw new Error("not success");
        }
        //	Reset
        // osgi event handler

        m_idOld = 0;
        clearNewValues();
        // DAP: CacheMgt.get().reset();
        return success;
    } //	delete

    /**
     * Set UpdatedBy
     *
     * @param AD_User_ID user
     */
    protected void setUpdatedBy(int AD_User_ID) {
        setValueNoCheck("UpdatedBy", AD_User_ID);
    } //	setUserId

    /**
     * Set value of Column
     *
     * @param columnName
     * @param value
     */
    public final void setValueOfColumn(String columnName, Object value) {
        setValueOfColumnReturningBoolean(columnName, value);
    }

    /**
     * Set value of Column returning boolean
     *
     * @param columnName
     * @param value
     * @returns boolean indicating success or failure
     */
    public final boolean setValueOfColumnReturningBoolean(String columnName, Object value) {
        POInfo p_info = super.getP_info();
        int AD_Column_ID = p_info.getColumnId(columnName);
        if (AD_Column_ID > 0) return setValueOfColumnReturningBoolean(AD_Column_ID, value);
        else return false;
    }

    /**
     * Set Value of Column
     *
     * @param AD_Column_ID column
     * @param value        value
     * @returns boolean indicating success or failure
     */
    public final boolean setValueOfColumnReturningBoolean(int AD_Column_ID, Object value) {
        POInfo p_info = super.getP_info();
        int index = p_info.getColumnIndex(AD_Column_ID);
        if (index < 0) throw new AdempiereUserError("Not found - AD_Column_ID=" + AD_Column_ID);
        String ColumnName = p_info.getColumnName(index);
        if (Objects.equals(ColumnName, "IsApproved")) return setValueNoCheck(ColumnName, value);
        else return setValue(index, value);
    } //  setValueOfColumn

    /**
     * Set Value if updateable and correct class. (and to NULL if not mandatory)
     *
     * @param index index
     * @param value value
     * @return true if value set
     */
    protected boolean setValue(int index, Object value) {
        return setValue(index, value, true);
    }

    @Override
    public boolean setValue(String ColumnName, Object value) {
        return super.setValue(ColumnName, value);
    }

    /**
     * Delete Current Record
     *
     * @param force delete also processed records
     * @throws AdempiereException
     * @see #delete(boolean)
     */
    public void deleteEx(boolean force) throws AdempiereException {
        if (!delete(force)) {
            String msg = null;
            ValueNamePair err = CLogger.retrieveError();
            if (err != null) msg = err.getName();
            if (msg == null || msg.length() == 0) msg = "DeleteError";
            throw new AdempiereException(msg);
        }
    }
}
