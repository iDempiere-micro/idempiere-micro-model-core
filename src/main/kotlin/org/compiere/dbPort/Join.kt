package org.compiere.dbPort

import org.idempiere.common.util.Util

/**
 * Join Clause.
 *
 * <pre>
 * f.AD_Column_ID = c.AD_ColumnId(+)
</pre> *
 *
 * @author Jorg Janke
 * @version $Id: Join.java,v 1.2 2006/07/30 00:55:04 jjanke Exp $
 */
class Join
/**
 * Constructor
 *
 * @param joinClause
 */
    (joinClause: String?) {
    private var m_joinClause: String? = null
    /**
     * Get Main Table Name
     *
     * @return Main Table Name
     */
    /**
     * Set Main Table Name. If table name equals alias, the alias is set to ""
     *
     * @param mainTable
     */
    var mainTable: String? = null
        set(mainTable) {
            if (mainTable == null || //  did not find Table from "Alias"
                mainTable.isEmpty()
            ) return
            field = mainTable
            if (mainAlias == mainTable) mainAlias = ""
        } //  getMainTable
    //  setMainTable
    /**
     * Get Main Table Alias
     *
     * @return Main Table Alias
     */
    var mainAlias: String? = null
        private set //  getMainAlias
    /**
     * Get Join Table Name
     *
     * @return Join Table Name
     */
    /**
     * Set Main Table Name. If table name equals alias, the alias is set to ""
     *
     * @param joinTable
     */
    var joinTable: String? = null
        set(joinTable) {
            if (joinTable == null || joinTable.isEmpty()) return
            field = joinTable
            if (joinAlias == joinTable) joinAlias = ""
        } //  getJoinTable
    //  setJoinTable
    /**
     * Get Join Table Alias
     *
     * @return Join Table Alias
     */
    var joinAlias: String? = null
        private set //  getJoinAlias
    /**
     * Is Left Aouter Join
     *
     * @return true if left outer join
     */
    var isLeft: Boolean = false
        private set //  isLeft
    /**
     * Get Join condition. e.g. f.AD_Column_ID = c.AD_Column_ID
     *
     * @return join condition
     */
    var condition: String? = null
        private set //  getCondition

    init {
        if (joinClause == null) throw IllegalArgumentException("Join - clause cannot be null")
        evaluate(joinClause)
    } //  Join

    /**
     * Evaluate the clause. e.g. tb.AD_UserId(+)=? f.AD_Column_ID = c.AD_ColumnId(+)
     *
     * @param joinClause
     */
    private fun evaluate(joinClause: String) {
        m_joinClause = joinClause
        val indexEqual = joinClause.indexOf('=')
        isLeft = indexEqual < joinClause.indexOf("(+)") //  converts to LEFT if true
        //  get table alias of it
        if (isLeft)
        //  f.AD_Column_ID = c.AD_ColumnId(+)  => f / c
        {
            mainAlias = joinClause.substring(0, Util.findIndexOf(joinClause, '.', '=')).trim { it <= ' ' } //  f
            var end = joinClause.indexOf('.', indexEqual)
            if (end == -1)
            //  no alias
                end = joinClause.indexOf('(', indexEqual)
            joinAlias = joinClause.substring(indexEqual + 1, end).trim { it <= ' ' } //  c
        } else
        //  f.AD_ColumnId(+) = c.AD_Column_ID  => c / f
        {
            var end = joinClause.indexOf('.', indexEqual)
            if (end == -1)
            //  no alias
                end = joinClause.length
            mainAlias = joinClause.substring(indexEqual + 1, end).trim { it <= ' ' } //  c
            joinAlias = joinClause.substring(0, Util.findIndexOf(joinClause, '.', '(')).trim { it <= ' ' } //  f
        }
        condition = Util.replace(joinClause, "(+)", "").trim { it <= ' ' }
    } //  evaluate

    /**
     * This Join is a condition of the first Join. e.g. tb.AD_UserId(+)=? or tb.AD_UserId(+)='123'
     *
     * @param first
     * @return true if condition
     */
    fun isConditionOf(first: Join): Boolean {
        //  same main table
        return mainTable == null && (first.joinTable == joinTable || //  same join table
                first.mainAlias == joinTable)
    } //  isConditionOf

    /**
     * String representation
     *
     * @return info
     */
    override fun toString(): String {
        return "Join[" + m_joinClause +
                " - Main=" +
                mainTable +
                "/" +
                mainAlias +
                ", Join=" +
                joinTable +
                "/" +
                joinAlias +
                ", Left=" +
                isLeft +
                ", Condition=" +
                condition +
                "]"
    } //  toString
} //  Join
