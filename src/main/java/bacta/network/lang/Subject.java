package bacta.network.lang;

/**
 * Created by Kyle on 9/5/2014.
 */
public interface Subject<EventType> {

    public void register(Observer obj, EventType eventType);
    public void unregister(Observer obj, EventType eventType);

    public void notifyObservers(EventType eventType);

    public Object getUpdate(Observer obj);

}
