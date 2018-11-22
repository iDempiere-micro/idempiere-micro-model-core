package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.idempiere.common.util.KeyNamePair;

/**
 * Generated Interface for HR_Job
 *
 * @author iDempiere (generated)
 * @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_HR_Job {

  /** TableName=HR_Job */
  public static final String Table_Name = "HR_Job";

  /** AD_Table_ID=53089 */
  public static final int Table_ID = 53089;

  KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

  /** AccessLevel = 3 - Client - Org */
  BigDecimal accessLevel = BigDecimal.valueOf(3);

  /** Load Meta Data */

  /** Get Client. Client/Tenant for this installation. */
  public int getADClientID();

  /** Column name AD_Org_ID */
  public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

  /** Set Organization. Organizational entity within client */
  public void setAD_Org_ID(int AD_Org_ID);

  /** Get Organization. Organizational entity within client */
  public int getAD_Org_ID();

  /** Column name Created */
  public static final String COLUMNNAME_Created = "Created";

  /** Get Created. Date this record was created */
  public Timestamp getCreated();

  /** Column name CreatedBy */
  public static final String COLUMNNAME_CreatedBy = "CreatedBy";

  /** Get Created By. User who created this records */
  public int getCreatedBy();

  /** Column name Description */
  public static final String COLUMNNAME_Description = "Description";

  /** Set Description. Optional short description of the record */
  public void setDescription(String Description);

  /** Get Description. Optional short description of the record */
  public String getDescription();

  /** Column name HR_Department_ID */
  public static final String COLUMNNAME_HR_Department_ID = "HR_Department_ID";

  /** Set Payroll Department */
  public void setHR_Department_ID(int HR_Department_ID);

  /** Get Payroll Department */
  public int getHR_Department_ID();

  public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException;

  /** Column name HR_Job_ID */
  public static final String COLUMNNAME_HR_Job_ID = "HR_Job_ID";

  /** Set Payroll Job */
  public void setHR_Job_ID(int HR_Job_ID);

  /** Get Payroll Job */
  public int getHR_Job_ID();

  /** Column name HR_Job_UU */
  public static final String COLUMNNAME_HR_Job_UU = "HR_Job_UU";

  /** Set HR_Job_UU */
  public void setHR_Job_UU(String HR_Job_UU);

  /** Get HR_Job_UU */
  public String getHR_Job_UU();

  /** Column name IsActive */
  public static final String COLUMNNAME_IsActive = "IsActive";

  /** Set Active. The record is active in the system */
  public void setIsActive(boolean IsActive);

  /** Get Active. The record is active in the system */
  public boolean isActive();

  /** Column name IsParent */
  public static final String COLUMNNAME_IsParent = "IsParent";

  /**
   * Set Parent link column. This column is a link to the parent table (e.g. header from lines) -
   * incl. Association key columns
   */
  public void setIsParent(boolean IsParent);

  /**
   * Get Parent link column. This column is a link to the parent table (e.g. header from lines) -
   * incl. Association key columns
   */
  public boolean isParent();

  /** Column name JobCant */
  public static final String COLUMNNAME_JobCant = "JobCant";

  /** Set Job Cant */
  public void setJobCant(int JobCant);

  /** Get Job Cant */
  public int getJobCant();

  /** Set Name. Alphanumeric identifier of the entity */
  public void setName(String Name);

  /** Get Name. Alphanumeric identifier of the entity */
  public String getName();

  /** Column name Next_Job_ID */
  public static final String COLUMNNAME_Next_Job_ID = "Next_Job_ID";

  /** Set Next Job */
  public void setNext_Job_ID(int Next_Job_ID);

  /** Get Next Job */
  public int getNext_Job_ID();

  public org.eevolution.model.I_HR_Job getNext_Job() throws RuntimeException;

  /** Column name Supervisor_ID */
  public static final String COLUMNNAME_Supervisor_ID = "Supervisor_ID";

  /** Set Supervisor. Supervisor for this user/organization - used for escalation and approval */
  public void setSupervisor_ID(int Supervisor_ID);

  /** Get Supervisor. Supervisor for this user/organization - used for escalation and approval */
  public int getSupervisor_ID();

  public org.compiere.model.I_AD_User getSupervisor() throws RuntimeException;

  /** Column name Updated */
  public static final String COLUMNNAME_Updated = "Updated";

  /** Get Updated. Date this record was updated */
  public Timestamp getUpdated();

  /** Column name UpdatedBy */
  public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

  /** Get Updated By. User who updated this records */
  public int getUpdatedBy();

  /** Column name Value */
  public static final String COLUMNNAME_Value = "Value";

  /** Set Search Key. Search key for the record in the format required - must be unique */
  public void setValue(String Value);

  /** Get Search Key. Search key for the record in the format required - must be unique */
  public String getValue();
}
