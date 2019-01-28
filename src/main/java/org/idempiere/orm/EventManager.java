package org.idempiere.orm;

/**
 * Simple wrapper for the osgi event admin service. Usage:
 * EventManager.getInstance().sendEvent/postEvent
 *
 * @author hengsin
 */
public abstract class EventManager implements IEventManager {

    protected static IEventManager instance = null;

  /**
   * Get the singleton instance created by the osgi service framework
   *
   * @return EventManager
   */
  public static IEventManager getInstance() {
    return instance;
  }

  /**
   * @param topic
   * @param parameter
   */
  public static IEvent newEvent(String topic, Object data) {
    return getInstance().createNewEvent(topic, data);
  }

  /**
   * @param topic
   * @param properties
   * @return event object
   */
  public static IEvent newEvent(String topic, EventProperty... properties) {
    return getInstance().createNewEvent(topic, properties);
  }

    /* (non-Javadoc)
   * @see org.idempiere.app.event.IEventManager#register(java.lang.String, java.lang.String, org.osgi.service.event.EventHandler)
   */
  private boolean register(String topic, String filter, IEventHandler eventHandler) {
    String[] topics = new String[] {topic};
    return register(topics, filter, eventHandler);
  }
}
