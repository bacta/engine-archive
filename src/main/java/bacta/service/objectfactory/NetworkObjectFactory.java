package bacta.service.objectfactory;

import bacta.object.NetworkObject;

/**
 * Created by kburkhardt on 2/23/14.
 */
public interface NetworkObjectFactory {

    <T extends NetworkObject> T createNetworkObject(Class<T> clazz);
}
