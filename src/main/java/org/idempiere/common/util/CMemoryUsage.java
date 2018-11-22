package org.idempiere.common.util;

import java.lang.management.MemoryUsage;

/**
 * Memory Usage Info
 *
 * @author Jorg Janke
 * @version $Id: CMemoryUsage.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 */
public class CMemoryUsage extends MemoryUsage {
  /**
   * Detail Constructor
   *
   * @param init init
   * @param used used
   * @param committed committed
   * @param max max
   */
  public CMemoryUsage(long init, long used, long committed, long max) {
    super(init, used, committed, max);
  } //	CMemoryUsage

  /**
   * Parent Constructor
   *
   * @param usage usage
   */
  public CMemoryUsage(MemoryUsage usage) {
    super(usage.getInit(), usage.getUsed(), usage.getCommitted(), usage.getMax());
  } //	CMemoryUsage

  /**
   * Get Free (Committed-Used) Memory
   *
   * @return memory
   */
  public long getFree() {
    return getCommitted() - getUsed();
  } //	getFree

  /**
   * Get Free (Committed-Used) Memory Percent
   *
   * @return memory
   */
  public int getFreePercent() {
    long base = getCommitted();
    long no = getFree() * 100;
    if (no == 0) return 0;
    long percent = no / base;
    return (int) percent;
  } //	getFree

  /**
   * Get Committed (Max-Committed) Memory Percent
   *
   * @return memory
   */
  public int getCommittedPercent() {
    long base = getMax();
    long no = getCommitted() * 100;
    if (no == 0) return 0;
    long percent = no / base;
    return (int) percent;
  } //	getCommittedPercent

  /**
   * Format k/M
   *
   * @param info
   * @return string info
   */
  private String format(long info) {
    long infoK = info / 1024;
    if (infoK == 0) return String.valueOf(info);
    long infoM = infoK / 1024;
    // DAP
    if (infoM == 0) return Long.toString(info);
    return Long.toString(infoK) + "k";
  } //	format

  /**
   * String Representation
   *
   * @return info
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Init=")
        .append(format(getInit()))
        .append(", Used=")
        .append(format(getUsed()))
        .append(", Free=")
        .append(format(getFree()))
        .append(" ")
        .append(getFreePercent())
        .append("%, Committed=")
        .append(format(getCommitted()))
        .append(" ")
        .append(getCommittedPercent())
        .append("%, Max=")
        .append(format(getMax()));
    return sb.toString();
  } //	toString
} //	CMemoryUsage
