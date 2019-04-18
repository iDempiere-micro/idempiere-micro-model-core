package software.hsharp.core.orm

import kotliquery.Row
import org.compiere.orm.ModelFactory
import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.icommon.model.PersistentObject

class BaseSimpleModelFactory : ModelFactory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : PersistentObject> getPO(tableName: String, recordId: Int): T {
        return when (tableName) {
            "AD_Private_Access" -> org.compiere.orm.MPrivateAccess(recordId) as T
            "AD_User_OrgAccess" -> org.compiere.orm.MUserOrgAccess(recordId) as T
            "AD_Sequence_No" -> org.compiere.orm.X_AD_Sequence_No(recordId) as T
            "AD_TreeNodeBP" -> org.compiere.orm.X_AD_TreeNodeBP(recordId) as T
            "AD_TreeNodeMM" -> org.compiere.orm.X_AD_TreeNodeMM(recordId) as T
            "AD_TreeNodePR" -> org.compiere.orm.X_AD_TreeNodePR(recordId) as T
            "AD_Ref_Table" -> org.compiere.orm.MRefTable(recordId) as T
            "AD_Table_Access" -> org.compiere.orm.MTableAccess(recordId) as T
            "AD_Column_Access" -> org.compiere.orm.MColumnAccess(recordId) as T
            "AD_User_Roles" -> org.compiere.orm.MUserRoles(recordId) as T
            "AD_Role_Included" -> org.compiere.orm.X_AD_Role_Included(recordId) as T
            "AD_Role_OrgAccess" -> org.compiere.orm.MRoleOrgAccess(recordId) as T
            "AD_PInstance_Log" -> org.compiere.orm.X_AD_PInstance_Log(recordId) as T
            "AD_Tree" -> org.compiere.orm.MTree_Base(recordId) as T
            "AD_Element" -> org.compiere.orm.M_Element(recordId) as T
            "AD_Table" -> org.compiere.orm.MTable(recordId) as T
            "AD_Column" -> org.compiere.orm.MColumn(recordId) as T
            "C_DocType" -> org.compiere.orm.MDocType(recordId) as T
            "AD_Ref_List" -> org.compiere.orm.MRefList(recordId) as T
            "AD_Role" -> org.compiere.orm.MRole(recordId) as T
            "AD_StorageProvider" -> org.compiere.orm.X_AD_StorageProvider(recordId) as T
            "AD_EntityType" -> org.compiere.orm.MEntityType(recordId) as T
            "AD_Reference" -> org.compiere.orm.X_AD_Reference(recordId) as T
            "AD_Org" -> org.compiere.orm.MOrg(recordId) as T
            "AD_Sequence" -> org.compiere.orm.MSequence(recordId) as T
            "AD_SysConfig" -> org.compiere.orm.MSysConfig(recordId) as T
            "AD_ViewColumn" -> org.compiere.orm.MViewColumn(recordId) as T
            "AD_TableIndex" -> org.compiere.orm.MTableIndex(recordId) as T
            "AD_IndexColumn" -> org.compiere.orm.MIndexColumn(recordId) as T
            "AD_ViewComponent" -> org.compiere.orm.MViewComponent(recordId) as T
            else -> throw AdempiereSystemError("Unknown table '$tableName'")
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : PersistentObject> getPO(tableName: String, row: Row): T {
        return when (tableName) {
            "AD_Private_Access" -> org.compiere.orm.MPrivateAccess(row) as T
            "AD_User_OrgAccess" -> org.compiere.orm.MUserOrgAccess(row) as T
            "AD_Sequence_No" -> org.compiere.orm.X_AD_Sequence_No(row) as T
            "AD_TreeNodeBP" -> org.compiere.orm.X_AD_TreeNodeBP(row) as T
            "AD_TreeNodeMM" -> org.compiere.orm.X_AD_TreeNodeMM(row) as T
            "AD_TreeNodePR" -> org.compiere.orm.X_AD_TreeNodePR(row) as T
            "AD_Ref_Table" -> org.compiere.orm.MRefTable(row) as T
            "AD_Table_Access" -> org.compiere.orm.MTableAccess(row) as T
            "AD_Column_Access" -> org.compiere.orm.MColumnAccess(row) as T
            "AD_User_Roles" -> org.compiere.orm.MUserRoles(row) as T
            "AD_Role_Included" -> org.compiere.orm.X_AD_Role_Included(row) as T
            "AD_Role_OrgAccess" -> org.compiere.orm.MRoleOrgAccess(row) as T
            "AD_PInstance_Log" -> org.compiere.orm.X_AD_PInstance_Log(row) as T
            "AD_Tree" -> org.compiere.orm.MTree_Base(row) as T
            "AD_Element" -> org.compiere.orm.M_Element(row) as T
            "AD_Table" -> org.compiere.orm.MTable(row) as T
            "AD_Column" -> org.compiere.orm.MColumn(row) as T
            "C_DocType" -> org.compiere.orm.MDocType(row) as T
            "AD_Ref_List" -> org.compiere.orm.MRefList(row) as T
            "AD_Role" -> org.compiere.orm.MRole(row) as T
            "AD_StorageProvider" -> org.compiere.orm.X_AD_StorageProvider(row) as T
            "AD_EntityType" -> org.compiere.orm.MEntityType(row) as T
            "AD_Reference" -> org.compiere.orm.X_AD_Reference(row) as T
            "AD_Org" -> org.compiere.orm.MOrg(row) as T
            "AD_Sequence" -> org.compiere.orm.MSequence(row) as T
            "AD_SysConfig" -> org.compiere.orm.MSysConfig(row) as T
            "AD_ViewColumn" -> org.compiere.orm.MViewColumn(row) as T
            "AD_TableIndex" -> org.compiere.orm.MTableIndex(row) as T
            "AD_IndexColumn" -> org.compiere.orm.MIndexColumn(row) as T
            "AD_ViewComponent" -> org.compiere.orm.MViewComponent(row) as T
            else -> throw AdempiereSystemError("Unknown table '$tableName'")
        } }
}