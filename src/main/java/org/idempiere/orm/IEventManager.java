package org.idempiere.orm;

/** @author hengsin */
public interface IEventManager {

  public static final String EVENT_DATA = "event.data";
  public static final String EVENT_ERROR_MESSAGES = "event.errorMessages";

  /**
   * Initiate asynchronous delivery of an event. This method returns to the caller before delivery
   * of the event is completed.
   *
   * @param event The event to send to all listeners which subscribe to the topic of the event.
   * @throws SecurityException If the caller does not have <code>TopicPermission[topic,PUBLISH]
   *     </code> for the topic specified in the event.
   */
  public abstract boolean postEvent(IEvent event);

  /**
   * Initiate synchronous delivery of an event. This method does not return to the caller until
   * delivery of the event is completed.
   *
   * @param event The event to send to all listeners which subscribe to the topic of the event.
   * @throws SecurityException If the caller does not have <code>TopicPermission[topic,PUBLISH]
   *     </code> for the topic specified in the event.
   */
  public abstract boolean sendEvent(IEvent event);

  /**
   * register a new event handler
   *
   * @param topic
   * @param eventHandler
   * @return true if registration is successful, false otherwise
   */
  public abstract boolean register(String topic, IEventHandler eventHandler);

  /**
   * register a new event handler
   *
   * @param topics
   * @param eventHandler
   * @return true if registration is successful, false otherwise
   */
  public abstract boolean register(String[] topics, IEventHandler eventHandler);

  /**
   * register a new event handler
   *
   * @param topic
   * @param filter
   * @param eventHandler
   * @return true if registration is successful, false otherwise
   */
  public abstract boolean register(String topic, String filter, IEventHandler eventHandler);

  /**
   * register a new event handler
   *
   * @param topics
   * @param filter
   * @param eventHandler
   * @return true if registration is successful, false otherwise
   */
  public abstract boolean register(String[] topics, String filter, IEventHandler eventHandler);

  /**
   * un-register an event handler
   *
   * @param eventHandler
   * @return true if unregistration is done, false otherwise
   */
  public abstract boolean unregister(IEventHandler eventHandler);

  public abstract IEvent createNewEvent(String topic, Object data);

  public abstract IEvent createNewEvent(String topic, EventProperty... properties);
}
