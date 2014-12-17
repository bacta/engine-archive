package bacta.network.lang;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.Observer;

/**
 * Created by Kyle on 9/5/2014.
 */
public final class ObservableEventRegistry<T> {

    private final Map<T, List<WeakReference<Observer>>> registry;

    public ObservableEventRegistry() {
        registry = new HashMap<>();
    }

    public void register(Observer obj, T event) {
        List<WeakReference<Observer>> list = registry.get(event);
        if(list == null) {
            list = Collections.synchronizedList(new ArrayList<>());
            registry.put(event, list);
        }

        if (!list.contains(obj)) {
            list.add(new WeakReference<Observer>(obj));
        }

    }

    public void unregister(Observer obj, T event) {
        List<WeakReference<Observer>> list = registry.get(event);
        if(list != null) {
            list.remove(obj);
        }
    }

    public void notifyObservers(T event) {
        List<WeakReference<Observer>> list = registry.get(event);
        if(list != null) {
            list.forEach(s -> {
                Observer observer = s.get();
                if(observer != null) {
                    observer.update(event);
                }
            });
        }
    }
}
