package com.ocdsoft.bacta.engine.lang;

/**
 * Created by Kyle on 9/5/2014.
 */
public interface Observer<EventType> {

    //method to update the observer, used by subject
    public void update(EventType eventType);

    //attach with subject to observe
    public void setSubject(Subject sub);
}