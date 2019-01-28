package org.idempiere.orm;

/** @author hengsin */
public interface IEventManager {

  String EVENT_DATA = "event.data";
  String EVENT_ERROR_MESSAGES = "event.errorMessages";

  /**
   * Initiate asynchronous delivery of an event. This method returns to the caller before delivery
   * of the event is completed.
   *
   * @param event The event to send to all listeners which subscribe to the topic of the event.
   * @throws SecurityException If the caller does not have <code>TopicPermission[topic,PUBLISH]
   *                           </code> for the topic specified in the event.
   */
  boolean postEvent(IEvent event);

  /**
   * Initiate synchronous delivery of an event. This method does not return to the caller until
   * delivery of the event is completed.
   *
   * @param event The event to send to all listeners which subscribe to the topic of the event.
   * @throws SecurityException If the caller does not have <code>TopicPermission[topic,PUBLISH]
   *                           </code> for the topic specified in the event.
   */
  boolean sendEvent(IEvent event);

    /**
   * register a new event handler
   *
   * @param topics
   * @param filter
   * @param eventHandler
   * @return true if registration is successful, false otherwise
   */
  boolean register(String[] topics, String filter, IEventHandler eventHandler);

    IEvent createNewEvent(String topic, Object data);

  IEvent createNewEvent(String topic, EventProperty... properties);
}
