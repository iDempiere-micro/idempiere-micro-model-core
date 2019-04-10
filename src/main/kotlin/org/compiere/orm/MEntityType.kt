package org.compiere.orm

import kotliquery.Row

import org.compiere.util.SystemIDs.ENTITYTYPE_ADEMPIERE
import org.compiere.util.SystemIDs.ENTITYTYPE_DICTIONARY
import org.idempiere.common.util.factoryString
import org.compiere.model.I_AD_EntityType
import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.loadUsing

private fun load(entityType: String): I_AD_EntityType = Query(I_AD_EntityType.Table_Name, "EntityType=?")
    .setParameters(entityType)
    .firstOnly() ?: throw AdempiereSystemError("Entity type $entityType not found.")
private val entityTypeFactory = factoryString { load(it) }

/**
 * Get currency by Id
 */
fun getEntityType(string: String) = string loadUsing entityTypeFactory

/**
 * Enitity Type Model
 *
 * @author Jorg Janke
 * @author Teo Sarca
 *  * BF [ 2827777 ] MEntityType.isSystemMaintained not working well
 * https://sourceforge.net/tracker/?func=detail&aid=2827777&group_id=176962&atid=879332
 *  * FR [ 2827786 ] Introduce MEntityType.get(String entityType)
 * https://sourceforge.net/tracker/?func=detail&aid=2827786&group_id=176962&atid=879335
 *  * BF [ 2861194 ] EntityType is not using normal PO framework for getting IDs
 * https://sourceforge.net/tracker/?func=detail&aid=2861194&group_id=176962&atid=879332
 * @version $Id: MEntityType.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
class MEntityType : X_AD_EntityType {

    /**
     * Is System Maintained. Any Entity Type with ID < 1000000.
     *
     * @return true if D/C/U/CUST/A/EXT/XX (ID < 1000000)
     */
    val isSystemMaintained: Boolean
        get() {
            val id = entityTypeId
            return id < s_maxAD_EntityType_ID
        } // 	isSystemMaintained

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param AD_EntityType_ID id
     */
    constructor(AD_EntityType_ID: Int) : super(AD_EntityType_ID) {} // 	MEntityType

    /**
     * Load Constructor
     *
     */
    constructor(row: Row) : super(row) {} // 	MEntityType

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true if it can be saved
     */
    override fun beforeSave(newRecord: Boolean): Boolean {
        if (!newRecord) {
            val id = entityTypeId
            var systemMaintained = id == ENTITYTYPE_DICTIONARY || id == ENTITYTYPE_ADEMPIERE // 	C/D
            if (systemMaintained) {
                log.saveError("Error", "You cannot modify a System maintained entity")
                return false
            }
            systemMaintained = isValueChanged("EntityType")
            if (systemMaintained) {
                log.saveError("Error", "You cannot modify EntityType")
                return false
            }
            systemMaintained = isSystemMaintained && (isValueChanged("Name") ||
                    isValueChanged("Description") ||
                    isValueChanged("Help") ||
                    isValueChanged("IsActive"))
            if (systemMaintained) {
                log.saveError("Error", "You cannot modify Name,Description,Help")
                return false
            }
        } // 	new
        return true
    } // 	beforeSave

    /**
     * Before Delete
     *
     * @return true if it can be deleted
     */
    override fun beforeDelete(): Boolean {
        if (isSystemMaintained)
        // 	all pre-defined
        {
            log.saveError("Error", "You cannot delete a System maintained entity")
            return false
        }
        return true
    } // 	beforeDelete

    companion object {
        private val serialVersionUID = -8449015496292546851L
        private val s_maxAD_EntityType_ID = 1000000
    }
} // 	MEntityType
