package org.compiere.model;

/** @author hengsin */
public interface AdempiereProcessor2 {
  /**
   * @return true if previous DateNextRun should be use as base to calculate the new DateNextRun
   *     value. False to follow the legacy behaviour where current server time is use as the base to
   *     the new DateNextRun value.
   */
  public boolean isIgnoreProcessingTime();

  // IDEMPIERE-391
  public int getAD_Schedule_ID();
}
