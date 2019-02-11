package org.compiere.orm;

import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.I_C_DocType;
import org.idempiere.common.util.CCache;
import org.idempiere.common.util.Env;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.executeUpdate;
import static software.hsharp.core.util.DBKt.getSQLValue;

/**
 * Document Type Model
 *
 * @author Jorg Janke
 * @author Karsten Thiemann FR [ 1782412 ]
 * @author Teo Sarca, www.arhipac.ro
 *     <li>BF [ 2476824 ] MDocType.getOfDocBaseType should return ONLY active records
 *     <li>BF [ - ] MDocType.getOfClient should return ONLY active records. See
 *         https://sourceforge.net/forum/message.php?msg_id=6499893
 * @version $Id: MDocType.java,v 1.3 2006/07/30 00:54:54 jjanke Exp $
 */
public class MDocType extends X_C_DocType {
  /** */
  private static final long serialVersionUID = -6556521509479670059L;
  /** Cache */
  private static CCache<Integer, MDocType> s_cache =
      new CCache<Integer, MDocType>(I_C_DocType.Table_Name, 20);

  /**
   * ************************************************************************ Standard Constructor
   *
   * @param ctx context
   * @param C_DocType_ID id
   * @param trxName transaction
   */
  public MDocType(Properties ctx, int C_DocType_ID, String trxName) {
    super(ctx, C_DocType_ID, trxName);
    if (C_DocType_ID == 0) {
      //	setName (null);
      //	setPrintName (null);
      //	setDocBaseType (null);
      //	setGL_Category_ID (0);
      setDocumentCopies(0);
      setHasCharges(false);
      setIsDefault(false);
      setIsDocNoControlled(false);
      setIsSOTrx(false);
      setIsPickQAConfirm(false);
      setIsShipConfirm(false);
      setIsSplitWhenDifference(false);
      //
      setIsCreateCounter(true);
      setIsDefaultCounterDoc(false);
      setIsIndexed(true);
    }
  } //	MDocType

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MDocType(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } //	MDocType
  public MDocType(Properties ctx, Row row) {
    super(ctx, row);
  } //	MDocType

  /**
   * New Constructor
   *
   * @param ctx context
   * @param DocBaseType document base type
   * @param Name name
   * @param trxName transaction
   */
  public MDocType(Properties ctx, String DocBaseType, String Name, String trxName) {
    this(ctx, 0, trxName);
    setAD_Org_ID(0);
    setDocBaseType(DocBaseType);
    setName(Name);
    setPrintName(Name);
    setGL_Category_ID();
  } //	MDocType

    /**
   * Return the first Doc Type for this BaseType
   *
   * @param DocBaseType
   * @return
   */
  public static int getDocType(String DocBaseType) {
    MDocType[] doc = MDocType.getOfDocBaseType(Env.getCtx(), DocBaseType);
    return doc.length > 0 ? doc[0].getId() : 0;
  }

  /**
   * Get Client Document Type with DocBaseType
   *
   * @param ctx context
   * @param DocBaseType base document type
   * @return array of doc types
   */
  public static MDocType[] getOfDocBaseType(Properties ctx, String DocBaseType) {
    final String whereClause = "AD_Client_ID=? AND DocBaseType=?";
    List<MDocType> list =
        new Query(ctx, I_C_DocType.Table_Name, whereClause, null)
            .setParameters(Env.getClientId(ctx), DocBaseType)
            .setOnlyActiveRecords(true)
            .setOrderBy("IsDefault DESC, C_DocType_ID")
            .list();
    return list.toArray(new MDocType[list.size()]);
  } //	getOfDocBaseType

  /**
   * Get Client Document Types
   *
   * @param ctx context
   * @return array of doc types
   */
  public static MDocType[] getOfClient(Properties ctx) {
    List<MDocType> list =
        new Query(ctx, I_C_DocType.Table_Name, null, null)
            .setClient_ID()
            .setOnlyActiveRecords(true)
            .list();
    return list.toArray(new MDocType[list.size()]);
  } //	getOfClient

  /**
   * Get Document Type (cached)
   *
   * @param ctx context
   * @param C_DocType_ID id
   * @return document type
   */
  public static MDocType get(Properties ctx, int C_DocType_ID) {
    MDocType retValue = s_cache.get(C_DocType_ID);
    if (retValue == null) {
      retValue = new MDocType(ctx, C_DocType_ID, null);
      s_cache.put(C_DocType_ID, retValue);
    }
    return retValue;
  } //	get

  /** Set Default GL Category */
  public void setGL_Category_ID() {
    final String sql =
        "SELECT GL_Category_ID FROM GL_Category"
            + " WHERE AD_Client_ID=?"
            + " ORDER BY IsDefault DESC, GL_Category_ID";
    int GL_Category_ID = getSQLValue(sql, getClientId());
    setGL_Category_ID(GL_Category_ID);
  } //	setGL_Category_ID

  /** Set SOTrx based on document base type */
  public void setIsSOTrx() {
    boolean isSOTrx =
        X_C_DocType.DOCBASETYPE_SalesOrder.equals(getDocBaseType())
            || X_C_DocType.DOCBASETYPE_MaterialDelivery.equals(getDocBaseType())
            || getDocBaseType().startsWith("AR");
    super.setIsSOTrx(isSOTrx);
  } //	setIsSOTrx

  /**
   * String Representation
   *
   * @return info
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("MDocType[");
    sb.append(getId())
        .append("-")
        .append(getName())
        .append(",DocNoSequence_ID=")
        .append(getDocNoSequence_ID())
        .append("]");
    return sb.toString();
  } //	toString

    /**
   * Is this a Proposal (Not binding)
   *
   * @return true if proposal
   */
  public boolean isProposal() {
    return X_C_DocType.DOCSUBTYPESO_Proposal.equals(getDocSubTypeSO())
        && X_C_DocType.DOCBASETYPE_SalesOrder.equals(getDocBaseType());
  } //	isProposal

  /**
   * Is this a Proposal or Quotation
   *
   * @return true if proposal or quotation
   */
  public boolean isOffer() {
    return (X_C_DocType.DOCSUBTYPESO_Proposal.equals(getDocSubTypeSO())
            || X_C_DocType.DOCSUBTYPESO_Quotation.equals(getDocSubTypeSO()))
        && X_C_DocType.DOCBASETYPE_SalesOrder.equals(getDocBaseType());
  } //	isOffer

  /**
   * Get Print Name
   *
   * @param AD_Language language
   * @return print Name if available translated
   */
  public String getPrintName(String AD_Language) {
    if (AD_Language == null || AD_Language.length() == 0) return super.getPrintName();
    return get_Translation(I_C_DocType.COLUMNNAME_PrintName, AD_Language);
  } //	getPrintName

  /**
   * Before Save
   *
   * @param newRecord new
   * @return true
   */
  protected boolean beforeSave(boolean newRecord) {
    /*if (getOrgId() != 0)
    setAD_Org_ID(0);*/
    return true;
  } //	beforeSave

  /**
   * After Save
   *
   * @param newRecord new
   * @param success success
   * @return success
   */
  protected boolean afterSave(boolean newRecord, boolean success) {
    if (newRecord && success) {
      //	Add doctype/docaction access to all roles of client
      StringBuilder sqlDocAction =
          new StringBuilder("INSERT INTO AD_Document_Action_Access ")
              .append("(AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,")
              .append("C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) ")
              .append("(SELECT ")
              .append(getClientId())
              .append(",0,'Y', SysDate,")
              .append(getUpdatedBy())
              .append(", SysDate,")
              .append(getUpdatedBy())
              .append(", doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID ")
              .append("FROM AD_Client client ")
              .append("INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) ")
              .append("INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) ")
              .append("INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) ")
              .append("WHERE client.AD_Client_ID=")
              .append(getClientId())
              .append(" AND doctype.C_DocType_ID=")
              .append(getId())
              .append(" AND rol.IsManual='N'")
              .append(")");

      int docact = executeUpdate(sqlDocAction.toString());
      if (log.isLoggable(Level.FINE)) log.fine("AD_Document_Action_Access=" + docact);
    }
    return success;
  } //	afterSave

  /**
   * Executed before Delete operation.
   *
   * @return true if delete is a success
   */
  protected boolean beforeDelete() {
    // delete access records
    StringBuilder msgdb =
        new StringBuilder("DELETE FROM AD_Document_Action_Access WHERE C_DocType_ID=")
            .append(getId());
    int docactDel = executeUpdate(msgdb.toString());
    if (log.isLoggable(Level.FINE))
      log.fine("Delete AD_Document_Action_Access=" + docactDel + " for C_DocType_ID: " + getId());
    return docactDel >= 0;
  } //  beforeDelete

  /**
   * Get translated doctype name
   *
   * @return Name if available translated
   */
  public String getNameTrl() {
    // warning: to cache this translation you need to change the cache to include language (see i.e.
    // MWFNode)
    return get_Translation(HasName.Companion.getCOLUMNNAME_Name(), Env.getADLanguage(getCtx()));
  } //	getNameTrl
} //	MDocType
