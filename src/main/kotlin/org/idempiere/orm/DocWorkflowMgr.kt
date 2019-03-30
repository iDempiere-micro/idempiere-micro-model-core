package org.idempiere.orm

/**
 * Document Value Workflow Manager
 *
 * @author Jorg Janke
 * @version $Id: DocWorkflowMgr.java,v 1.2 2006/07/30 00:58:18 jjanke Exp $
 */
interface DocWorkflowMgr {
    /**
     * Process Document Value Workflow
     *
     * @param document document
     * @param AD_Table_ID table
     * @return true if WF started
     */
    fun process(document: PO, AD_Table_ID: Int): Boolean
} // 	DocWorkflowMgr
