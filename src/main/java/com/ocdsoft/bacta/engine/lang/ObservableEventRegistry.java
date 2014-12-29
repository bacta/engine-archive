package com.ocdsoft.bacta.engine.lang;

import java.lang.ref.WeakReference;
import java.util.*;

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
            list = Collections.synchronizedList(new ArrayList<WeakReference<Observer>>());
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
            for(WeakReference<Observer> observerRef : list) {
                Observer observer = observerRef.get();
                if(observer != null) {
                    observer.update(event);
                }
            }
        }
    }
}
