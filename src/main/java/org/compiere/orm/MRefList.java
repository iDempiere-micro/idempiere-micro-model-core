package org.compiere.orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.I_AD_Ref_List;
import org.idempiere.common.util.CCache;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.DB;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.ValueNamePair;
import org.idempiere.orm.PO;

/**
 * Reference List Value
 *
 * @author Jorg Janke
 * @version $Id: MRefList.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 * @author Teo Sarca, www.arhipac.ro
 *     <li>BF [ 1748449 ] Info Account - Posting Type is not translated
 *     <li>FR [ 2694043 ] Query. first/firstOnly usage best practice
 */
public class MRefList extends X_AD_Ref_List {
  /** */
  private static final long serialVersionUID = -3612793187620297377L;

  /**
   * Get Reference List
   *
   * @param ctx context
   * @param AD_Reference_ID reference
   * @param Value value
   * @param trxName transaction
   * @return List or null
   */
  public static MRefList get(Properties ctx, int AD_Reference_ID, String Value, String trxName) {
    return new Query(ctx, I_AD_Ref_List.Table_Name, "AD_Reference_ID=? AND Value=?", trxName)
        .setParameters(AD_Reference_ID, Value)
        .firstOnly();
  } //	get

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
    String retValue = (String) s_cache.get(key);
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
      pstmt = DB.prepareStatement(sql, null);
      pstmt.setInt(1, AD_Reference_ID);
      pstmt.setString(2, Value);
      if (!isBaseLanguage) pstmt.setString(3, AD_Language);
      rs = pstmt.executeQuery();
      if (rs.next()) retValue = rs.getString(1);
    } catch (SQLException ex) {
      s_log.log(Level.SEVERE, sql + " -- " + key, ex);
    } finally {
      DB.close(rs, pstmt);
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
   * @param ctx context
   * @param ListName reference
   * @param Value value
   * @return List or null
   */
  public static String getListDescription(Properties ctx, String ListName, String Value) {
    String AD_Language = Env.getADLanguage(ctx);
    return getListDescription(AD_Language, ListName, Value);
  }

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
      pstmt = DB.prepareStatement(sql, null);
      pstmt.setString(1, ListName);
      pstmt.setString(2, Value);
      if (!isBaseLanguage) pstmt.setString(3, AD_Language);
      rs = pstmt.executeQuery();
      if (rs.next()) retValue = rs.getString(1);
    } catch (SQLException ex) {
      s_log.log(Level.SEVERE, sql + " -- " + key, ex);
    } finally {
      DB.close(rs, pstmt);
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
   * Get Reference List (translated)
   *
   * @param ctx context
   * @param AD_Reference_ID reference
   * @param optional if true add "",""
   * @return List or null
   */
  public static ValueNamePair[] getList(Properties ctx, int AD_Reference_ID, boolean optional) {
    String ad_language = Env.getADLanguage(ctx);
    boolean isBaseLanguage = Env.isBaseLanguage(ad_language, "AD_Ref_List");
    String sql =
        isBaseLanguage
            ? "SELECT Value, Name FROM AD_Ref_List WHERE AD_Reference_ID=? AND IsActive='Y' ORDER BY Name"
            : "SELECT r.Value, t.Name FROM AD_Ref_List_Trl t"
                + " INNER JOIN AD_Ref_List r ON (r.AD_Ref_List_ID=t.AD_Ref_List_ID)"
                + " WHERE r.AD_Reference_ID=? AND t.AD_Language=? AND r.IsActive='Y'"
                + " ORDER BY t.Name";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList<ValueNamePair> list = new ArrayList<ValueNamePair>();
    if (optional) list.add(new ValueNamePair("", ""));
    try {
      pstmt = DB.prepareStatement(sql, null);
      pstmt.setInt(1, AD_Reference_ID);
      if (!isBaseLanguage) pstmt.setString(2, ad_language);
      rs = pstmt.executeQuery();
      while (rs.next()) list.add(new ValueNamePair(rs.getString(1), rs.getString(2)));
    } catch (SQLException e) {
      s_log.log(Level.SEVERE, sql, e);
    } finally {
      DB.close(rs, pstmt);
      rs = null;
      pstmt = null;
    }
    ValueNamePair[] retValue = new ValueNamePair[list.size()];
    list.toArray(retValue);
    return retValue;
  } //	getList

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
  public MRefList(Properties ctx, int AD_Ref_List_ID, String trxName) {
    super(ctx, AD_Ref_List_ID, trxName);
    if (AD_Ref_List_ID == 0) {
      //	setAD_Reference_ID (0);
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
  public MRefList(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } //	MRef_List

  /**
   * String Representation
   *
   * @return Name
   */
  public String toString() {
    return getName();
  } //	toString
} //	MRef_List
