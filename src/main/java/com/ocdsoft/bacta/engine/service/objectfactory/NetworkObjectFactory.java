package com.ocdsoft.bacta.engine.service.objectfactory;

import com.ocdsoft.bacta.engine.object.NetworkObject;

/**
 * Created by kburkhardt on 2/23/14.
 */
public interface NetworkObjectFactory<Template> {

    <T extends NetworkObject> T createNetworkObject(Class<T> clazz, Template template);
}
