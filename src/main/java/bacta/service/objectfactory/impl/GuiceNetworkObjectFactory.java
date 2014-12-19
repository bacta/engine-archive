package bacta.service.objectfactory.impl;

import bacta.data.DatabaseConnector;
import bacta.object.NetworkIdGenerator;
import bacta.object.NetworkObject;
import bacta.service.objectfactory.NetworkObjectFactory;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

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
