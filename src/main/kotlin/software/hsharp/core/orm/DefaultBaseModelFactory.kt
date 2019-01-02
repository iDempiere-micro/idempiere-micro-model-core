package software.hsharp.core.orm

import kotliquery.Row
import mu.KotlinLogging
import org.compiere.orm.*
import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.CCache
import org.idempiere.common.util.Env
import org.idempiere.common.util.Util
import org.idempiere.icommon.model.IPO
import java.util.*
import kotlin.collections.set

private val log = KotlinLogging.logger {}

internal val s_classCache = CCache<String, Class<*>>(null, "PO_Class", 20, false)

/** Special Classes  */
private val s_special = arrayOf(
    "AD_Element" to M_Element::class.qualifiedName,
    "AD_Tree" to MTree_Base::class.qualifiedName,
    "AD_Registration" to "M_Registration",
    "R_Category" to "MRequestCategory",
    "GL_Category" to "MGLCategory",
    "K_Category" to "MKCategory",
    "C_ValidCombination" to "MAccount",
    "C_Phase" to "MProjectTypePhase",
    "C_Task" to "MProjectTypeTask"
) // 	AD_Attribute_Value, AD_TreeNode

/** Packages for Model Classes  */
private val s_packages = arrayOf(
    "org.compiere.model",
    "org.compiere.impexp",
    "compiere.model", // 	globalqss allow compatibility with other plugins
    "adempiere.model", // 	Extensions
    "org.adempiere.model",
    "org.compiere.impl", // order is important this must be BEFORE the bundles
    "org.compiere.bank",
    "org.compiere.bo",
    "org.compiere.conversionrate",
    "org.compiere.crm",
    "org.compiere.accounting",
    "org.compiere.invoicing",
    "org.compiere.production",
    "org.compiere.order",
    "org.compiere.orm",
    "org.compiere.process",
    "org.compiere.product",
    "org.compiere.tax",
    "org.compiere.wf",
    "org.compiere.validation"
)

abstract class DefaultBaseModelFactory : IModelFactory {

    /**
     * Get PO class
     *
     * @param className fully qualified class name
     * @param tableName Optional. If specified, the loaded class will be validated for that table name
     * @return class or null
     */
    private fun getPOclass(className: String, tableName: String?): Class<*>? {
        try {
            val clazz = Class.forName(className)
            // Validate if the class is for specified tableName
            if (tableName != null) {
                val classTableName = clazz.getField("Table_Name").get(null).toString()
                if (tableName != classTableName) {
                    log.trace(
                        "Invalid class for table: " +
                                className +
                                " (tableName=" +
                                tableName +
                                ", classTableName=" +
                                classTableName +
                                ")"
                    )
                    return null
                }
            }
            // 	Make sure that it is a PO class
            if (IPO::class.java.isAssignableFrom(clazz)) {
                log.trace("Use: $className")
                return clazz
            } else {
                //log.trace("Not found IPO-assignable: $className")
                return null
            }
        } catch (e: Exception) {
            //log.trace("Not found: $className with $e")
        }

        return null
    } // 	getPOclass

    fun getClass(tableName: String?, useCache: Boolean?): Class<*>? {
        // 		Not supported
        if (tableName == null || tableName.endsWith("_Trl")) return null

        // check cache
        if (useCache!!) {
            val cache = s_classCache.get(tableName)
            if (cache != null) {
                // Object.class indicate no generated PO class for tableName
                return if (cache == Any::class.java)
                    null
                else
                    cache
            }
        }

        val table = MTable.get(Env.getCtx(), tableName)
        val entityType = table.entityType

        // 	Import Tables (Name conflict)
        //  Import Tables doesn't manage model M classes, just X_
        if (tableName.startsWith("I_")) {
            val et = MEntityType.get(Env.getCtx(), entityType)
            var etmodelpackage: String? = et!!.modelPackage
            if (etmodelpackage == null || MEntityType.ENTITYTYPE_Dictionary == entityType)
                etmodelpackage = "org.compiere.impl" // fallback for dictionary or empty model package on entity type
            val clazz = getPOclass("$etmodelpackage.X_$tableName", tableName)
            if (clazz != null) {
                s_classCache[tableName] = clazz
                return clazz
            }
            log.warn("No class for table: $tableName")
            return null
        }

        val special = s_special.find { it.first == tableName }
        if (special != null) {
            val clazz = getPOclass(special.second ?: "", tableName)
            if (clazz != null) {
                s_classCache[tableName] = clazz
                return clazz
            }
        }

        // begin [ 1784588 ] Use ModelPackage of EntityType to Find Model Class - vpj-cd
        if (MEntityType.ENTITYTYPE_Dictionary != entityType) {
            val et = MEntityType.get(Env.getCtx(), entityType)
            val etmodelpackage = et!!.modelPackage
            if (etmodelpackage != null) {
                var clazz: Class<*>? = null
                clazz = getPOclass(etmodelpackage + ".M" + Util.replace(tableName, "_", ""), tableName)
                if (clazz != null) {
                    s_classCache.put(tableName, clazz)
                    return clazz
                }
                clazz = getPOclass("$etmodelpackage.X_$tableName", tableName)
                if (clazz != null) {
                    s_classCache.put(tableName, clazz)
                    return clazz
                }
                log.warn("No class for table with it entity: $tableName")
            }
        }
        // end [ 1784588 ]

        // 	Strip table name prefix (e.g. AD_) Customizations are 3/4
        var className: String = tableName
        val index = className.indexOf('_')
        if (index > 0) {
            if (index < 3)
            // 	AD_, A_
                className = className.substring(index + 1)
        }
        // 	Remove underlines
        val classNameWOU = Util.replace(className, "_", "")

        // 	Search packages
        for (i in s_packages.indices) {
            var name = StringBuffer(s_packages[i]).append(".M").append(classNameWOU)
            var clazz = getPOclass(name.toString(), tableName)
            if (clazz != null) {
                s_classCache.put(tableName, clazz)
                return clazz
            }
            name = StringBuffer(s_packages[i]).append(".X_").append(tableName) // X_C_ContactActivity
            clazz = getPOclass(name.toString(), tableName)
            if (clazz != null) {
                s_classCache.put(tableName, clazz)
                return clazz
            }
        }

        // Object.class to indicate no PO class for tableName
        s_classCache.put(tableName, Any::class.java)
        return null
    }

    override fun <T> getPO(tableName: String, row: Row): T {
        val clazz = getClass(tableName)

        val constructor = clazz.getDeclaredConstructor(
            Properties::class.java, Row::class.java
        )
        try {
            return constructor.newInstance(Env.getCtx(), row) as T
        } catch (e: Exception) {
            throw AdempiereSystemError("Unable to load PO $clazz from $tableName", e)
        }
    }
}