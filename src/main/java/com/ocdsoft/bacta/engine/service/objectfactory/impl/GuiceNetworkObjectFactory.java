package com.ocdsoft.bacta.engine.service.objectfactory.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.ocdsoft.bacta.engine.data.DatabaseConnector;
import com.ocdsoft.bacta.engine.object.NetworkIdGenerator;
import com.ocdsoft.bacta.engine.object.NetworkObject;
import com.ocdsoft.bacta.engine.service.objectfactory.NetworkObjectFactory;

/**
 * Created by kburkhardt on 2/24/14.
 */
@Singleton
public class GuiceNetworkObjectFactory implements NetworkObjectFactory {

    private final Injector injector;
    private final NetworkIdGenerator idGenerator;

    @Inject
    public GuiceNetworkObjectFactory(Injector injector, NetworkIdGenerator idGenerator, DatabaseConnector dbConnector) {
        this.injector = injector;
        this.idGenerator = idGenerator;
    }

    @Override
    public <T extends NetworkObject> T createNetworkObject(Class<T> clazz) {
        T newObject = injector.getInstance(clazz);
        newObject.setNetworkId(idGenerator.next() );
        return newObject;
    }
}
