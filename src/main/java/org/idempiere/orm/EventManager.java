package org.idempiere.orm;

import org.idempiere.common.util.CLogger;

/**
 * Simple wrapper for the osgi event admin service. Usage:
 * EventManager.getInstance().sendEvent/postEvent
 *
 * @author hengsin
 */
public abstract class EventManager implements IEventManager {

  protected static IEventManager instance = null;
  protected static final CLogger log = CLogger.getCLogger(EventManager.class);

  protected static final Object mutex = new Object();

  /**
   * Get the singleton instance created by the osgi service framework
   *
   * @return EventManager
   */
  public static IEventManager getInstance() {
    return instance;
  }

  /* (non-Javadoc)
   * @see org.idempiere.app.event.IEventManager#register(java.lang.String, org.osgi.service.event.EventHandler)
   */
  @Override
  public boolean register(String topic, IEventHandler eventHandler) {
    return register(topic, null, eventHandler);
  }

  /* (non-Javadoc)
   * @see org.idempiere.app.event.IEventManager#register(java.lang.String[], org.osgi.service.event.EventHandler)
   */
  @Override
  public boolean register(String[] topics, IEventHandler eventHandler) {
    return register(topics, null, eventHandler);
  }

  /* (non-Javadoc)
   * @see org.idempiere.app.event.IEventManager#register(java.lang.String, java.lang.String, org.osgi.service.event.EventHandler)
   */
  @Override
  public boolean register(String topic, String filter, IEventHandler eventHandler) {
    String[] topics = new String[] {topic};
    return register(topics, filter, eventHandler);
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
}
