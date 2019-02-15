package org.compiere.orm;

import java.sql.ResultSet;
import java.util.Properties;
import org.idempiere.common.util.CLogger;

/**
 * Private Access
 *
 * @author Jorg Janke
 * @version $Id: MPrivateAccess.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
public class MPrivateAccess extends X_AD_Private_Access {
  /** */
  private static final long serialVersionUID = -5649529789751432279L;

    /**
   * Persistency Constructor
   *
   * @param ctx context
   * @param ignored ignored
   * @param trxName transaction
   */
  public MPrivateAccess(Properties ctx, int ignored) {
    super(ctx, 0);
    if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
  } //	MPrivateAccess

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MPrivateAccess(Properties ctx, ResultSet rs) {
    super(ctx, rs);
  } //	MPrivateAccess

  /**
   * New Constructor
   *
   * @param ctx context
   * @param AD_User_ID user
   * @param AD_Table_ID table
   * @param Record_ID record
   */
  public MPrivateAccess(Properties ctx, int AD_User_ID, int AD_Table_ID, int Record_ID) {
    super(ctx, 0);
    setAD_User_ID(AD_User_ID);
    setAD_Table_ID(AD_Table_ID);
    setRecord_ID(Record_ID);
  } //	MPrivateAccess

  /**
   * Get Where Clause of Locked Records for Table
   *
   * @param AD_Table_ID table
   * @param AD_User_ID user requesting info
   * @return "<>1" or " NOT IN (1,2)" or null
   */
  public static String getLockedRecordWhere(int AD_Table_ID, int AD_User_ID) {
    // [ 1644094 ] MPrivateAccess.getLockedRecordWhere inefficient
    /*
    ArrayList<Integer> list = new ArrayList<Integer>();
    PreparedStatement pstmt = null;
    String sql = "SELECT Record_ID FROM AD_Private_Access WHERE AD_Table_ID=? AND AD_User_ID<>? AND IsActive='Y'";
    try
    {
    	pstmt =prepareStatement(sql, null);
    	pstmt.setInt(1, AD_Table_ID);
    	pstmt.setInt(2, AD_User_ID);
    	ResultSet rs = pstmt.executeQuery();
    	while (rs.next())
    		list.add(new Integer(rs.getInt(1)));
    	rs.close();
    	pstmt.close();
    	pstmt = null;
    }
    catch (Exception e)
    {
    	s_log.log(Level.SEVERE, sql, e);
    }
    try
    {
    	if (pstmt != null)
    		pstmt.close();
    	pstmt = null;
    }
    catch (Exception e)
    {
    	pstmt = null;
    }
    //
    if (list.size() == 0)
    	return null;
    if (list.size() == 1)
    	return "<>" + list.get(0);
    //
    StringBuffer sb = new StringBuffer(" NOT IN(");
    for (int i = 0; i < list.size(); i++)
    {
    	if (i > 0)
    		sb.append(",");
    	sb.append(list.get(i));
    }
    sb.append(")");
    return sb.toString();*/
    String whereClause =
        " NOT IN ( SELECT Record_ID FROM AD_Private_Access WHERE AD_Table_ID = "
            + AD_Table_ID
            + " AND AD_User_ID <> "
            + AD_User_ID
            + " AND IsActive = 'Y' )";
    return whereClause;
  } //	get
} //	MPrivateAccess
