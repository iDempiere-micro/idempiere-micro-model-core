package org.idempiere.orm;

/** @author hengsin */
public class EventProperty {
  public String name;
  public Object value;

  /**
   * @param name
   * @param value
   */
  public EventProperty(String name, Object value) {
    super();
    this.name = name;
    this.value = value;
  }
}
