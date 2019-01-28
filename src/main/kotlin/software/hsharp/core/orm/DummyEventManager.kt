package software.hsharp.core.orm

import org.idempiere.orm.EventManager
import org.idempiere.orm.EventProperty
import org.idempiere.orm.IEvent
import org.idempiere.orm.IEventHandler

class DummyEvent : IEvent {
    override fun getProperty(eventErrorMessages: String?): MutableList<String> {
        return mutableListOf()
    }
}

class DummyEventManager : EventManager() {

    init {
        instance = this
    }

    override fun postEvent(event: IEvent?): Boolean {
        return true
    }

    override fun sendEvent(event: IEvent?): Boolean {
        return true
    }

    override fun register(topics: Array<out String>?, filter: String?, eventHandler: IEventHandler?): Boolean {
        return true
    }

    override fun createNewEvent(topic: String?, data: Any?): IEvent {
        return DummyEvent()
    }

    override fun createNewEvent(topic: String?, vararg properties: EventProperty?): IEvent {
        return DummyEvent()
    }
}