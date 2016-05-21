package com.ocdsoft.bacta.engine.service.object;

/**
 * Created by Kyle on 3/24/14.
 */

public interface ObjectService<C> {

    <T extends C> T createObject(String templatePath, C parent);

    <T extends C> T createObject(String templatePath);

    <T extends C> T get(long key);

    <T extends C> T get(C requester, long key);

    <T extends C> void updateObject(T object);
}
