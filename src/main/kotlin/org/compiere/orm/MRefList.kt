package org.compiere.orm

import kotliquery.Row
import org.idempiere.common.util.Env
import org.idempiere.common.util.memoize
import org.idempiere.orm.PO

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import software.hsharp.core.util.prepareStatement

/**
 * Get Reference List Value Name (cached)
 *
 * @param AD_Reference_ID reference
 * @param Value value
 * @return List or ""
 */
private fun doGetListName(AD_Language: String, AD_Reference_ID: Int, Value: String): String {
    val key = AD_Language + "_" + AD_Reference_ID + "_" + Value

    val isBaseLanguage = Env.isBaseLanguage(AD_Language, "AD_Ref_List")
    val sql = if (isBaseLanguage)
        "SELECT Name FROM AD_Ref_List " + "WHERE AD_Reference_ID=? AND Value=?"
    else
        "SELECT t.Name FROM AD_Ref_List_Trl t" +
                " INNER JOIN AD_Ref_List r ON (r.AD_Ref_List_ID=t.AD_Ref_List_ID) " +
                "WHERE r.AD_Reference_ID=? AND r.Value=? AND t.AD_Language=?"
    val pstmt: PreparedStatement?
    val rs: ResultSet
    var retValue: String? = null
    try {
        pstmt = prepareStatement(sql)
        pstmt!!.setInt(1, AD_Reference_ID)
        pstmt.setString(2, Value)
        if (!isBaseLanguage) pstmt.setString(3, AD_Language)
        rs = pstmt.executeQuery()
        if (rs.next()) retValue = rs.getString(1)
    } catch (ex: SQLException) {
    }

    //
    return retValue ?: ""
} // 	getListName

val listNameFactory = { AD_Language: String, AD_Reference_ID: Int, Value: String -> doGetListName(AD_Language, AD_Reference_ID, Value) }.memoize()

fun getListName(AD_Language: String, AD_Reference_ID: Int, Value: String): String = listNameFactory(AD_Language, AD_Reference_ID, Value)

/**
 * Get Reference List Value Name (cached)
 *
 * @param AD_Reference_ID reference
 * @param Value value
 * @return List or ""
 */
fun getListName(AD_Reference_ID: Int, Value: String): String {
    val AD_Language = Env.getADLanguage()
    return getListName(AD_Language, AD_Reference_ID, Value)
}

/**
 * Reference List Value
 *
 * @author Jorg Janke
 * @author Teo Sarca, www.arhipac.ro
 *  * BF [ 1748449 ] Info Account - Posting Type is not translated
 *  * FR [ 2694043 ] Query. first/firstOnly usage best practice
 * @version $Id: MRefList.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
class MRefList : X_AD_Ref_List {

    /**
     * ************************************************************************ Persistency
     * Constructor
     *
     * @param AD_Ref_List_ID id
     */
    constructor(AD_Ref_List_ID: Int) : super(AD_Ref_List_ID) {
        if (AD_Ref_List_ID == 0) {
            setEntityType(PO.ENTITYTYPE_UserMaintained) // U
        }
    } // 	MRef_List

    /**
     * Load Contructor
     *
     */
    constructor(row: Row) : super(row) {} // 	MRef_List

    /**
     * String Representation
     *
     * @return Name
     */
    override fun toString(): String {
        return name
    } // 	toString

    companion object {
        private val serialVersionUID = -3612793187620297377L
    }
} // 	MRef_List
