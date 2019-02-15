package org.compiere.orm;

import static software.hsharp.core.util.DBKt.prepareStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.I_AD_Ref_List;
import org.idempiere.common.util.CCache;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;
import org.idempiere.orm.PO;

/**
 * Reference List Value
 *
 * @author Jorg Janke
 * @author Teo Sarca, www.arhipac.ro
 *     <li>BF [ 1748449 ] Info Account - Posting Type is not translated
 *     <li>FR [ 2694043 ] Query. first/firstOnly usage best practice
 * @version $Id: MRefList.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
public class MRefList extends X_AD_Ref_List {
  /** */
  private static final long serialVersionUID = -3612793187620297377L;
  /** Logger */
  private static CLogger s_log = CLogger.getCLogger(MRefList.class);
  /** Value Cache */
  private static CCache<String, String> s_cache =
      new CCache<String, String>(I_AD_Ref_List.Table_Name, 20);

  /**
   * ************************************************************************ Persistency
   * Constructor
   *
   * @param ctx context
   * @param AD_Ref_List_ID id
   * @param trxName transaction
   */
  public MRefList(Properties ctx, int AD_Ref_List_ID) {
    super(ctx, AD_Ref_List_ID);
    if (AD_Ref_List_ID == 0) {
      //	setReferenceId (0);
      //	setAD_Ref_List_ID (0);
      setEntityType(PO.ENTITYTYPE_UserMaintained); // U
      //	setName (null);
      //	setValue (null);
    }
  } //	MRef_List

  /**
   * Load Contructor
   *
   * @param ctx context
   * @param rs result
   * @param trxName transaction
   */
  public MRefList(Properties ctx, ResultSet rs) {
    super(ctx, rs);
  } //	MRef_List

    /**
   * Get Reference List Value Name (cached)
   *
   * @param ctx context
   * @param AD_Reference_ID reference
   * @param Value value
   * @return List or ""
   */
  public static String getListName(Properties ctx, int AD_Reference_ID, String Value) {
    String AD_Language = Env.getADLanguage(ctx);
    return getListName(AD_Language, AD_Reference_ID, Value);
  }

  /**
   * Get Reference List Value Name (cached)
   *
   * @param Language
   * @param AD_Reference_ID reference
   * @param Value value
   * @return List or ""
   */
  public static String getListName(String AD_Language, int AD_Reference_ID, String Value) {
    String key = AD_Language + "_" + AD_Reference_ID + "_" + Value;
    String retValue = s_cache.get(key);
    if (retValue != null) return retValue;

    boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, "AD_Ref_List");
    String sql =
        isBaseLanguage
            ? "SELECT Name FROM AD_Ref_List " + "WHERE AD_Reference_ID=? AND Value=?"
            : "SELECT t.Name FROM AD_Ref_List_Trl t"
                + " INNER JOIN AD_Ref_List r ON (r.AD_Ref_List_ID=t.AD_Ref_List_ID) "
                + "WHERE r.AD_Reference_ID=? AND r.Value=? AND t.AD_Language=?";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = prepareStatement(sql);
      pstmt.setInt(1, AD_Reference_ID);
      pstmt.setString(2, Value);
      if (!isBaseLanguage) pstmt.setString(3, AD_Language);
      rs = pstmt.executeQuery();
      if (rs.next()) retValue = rs.getString(1);
    } catch (SQLException ex) {
      s_log.log(Level.SEVERE, sql + " -- " + key, ex);
    } finally {
      rs = null;
      pstmt = null;
    }

    //	Save into Cache
    if (retValue == null) {
      retValue = "";
      s_log.warning("Not found " + key);
    }
    s_cache.put(key, retValue);
    //
    return retValue;
  } //	getListName

    /**
   * Get Reference List Value Description (cached)
   *
   * @param Language
   * @param ListName reference
   * @param Value value
   * @return List or null
   */
  public static String getListDescription(String AD_Language, String ListName, String Value) {
    String key = AD_Language + "_" + ListName + "_" + Value;
    String retValue = s_cache.get(key);
    if (retValue != null) return retValue;

    boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, "AD_Ref_List");
    String sql =
        isBaseLanguage
            ? "SELECT a.Description FROM AD_Ref_List a, AD_Reference b"
                + " WHERE b.Name=? AND a.Value=?"
                + " AND a.AD_Reference_ID = b.AD_Reference_ID"
            : "SELECT t.Description FROM AD_Reference r"
                + " INNER JOIN AD_Ref_List rl ON (r.AD_Reference_ID=rl.AD_Reference_ID)"
                + " INNER JOIN AD_Ref_List_Trl t ON (t.AD_Ref_List_ID=rl.AD_Ref_List_ID)"
                + " WHERE r.Name=? AND rl.Value=? AND t.AD_Language=?";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = prepareStatement(sql);
      pstmt.setString(1, ListName);
      pstmt.setString(2, Value);
      if (!isBaseLanguage) pstmt.setString(3, AD_Language);
      rs = pstmt.executeQuery();
      if (rs.next()) retValue = rs.getString(1);
    } catch (SQLException ex) {
      s_log.log(Level.SEVERE, sql + " -- " + key, ex);
    } finally {
      rs = null;
      pstmt = null;
    }

    //	Save into Cache
    if (retValue == null) {
      retValue = "";
      if (s_log.isLoggable(Level.INFO)) s_log.info("getListDescription - Not found " + key);
    }
    s_cache.put(key, retValue);
    //
    return retValue;
  } //	getListDescription

    /**
   * String Representation
   *
   * @return Name
   */
  public String toString() {
    return getName();
  } //	toString
} //	MRef_List
