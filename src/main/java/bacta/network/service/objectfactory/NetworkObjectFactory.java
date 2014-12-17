package bacta.network.service.objectfactory;

import bacta.network.object.NetworkObject;

/**
 * Created by kburkhardt on 2/23/14.
 */
public interface NetworkObjectFactory {

    <T extends NetworkObject> T createNetworkObject(Class<T> clazz);
}
