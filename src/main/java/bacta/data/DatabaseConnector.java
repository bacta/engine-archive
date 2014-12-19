package bacta.data;

import bacta.object.NetworkObject;
import bacta.object.account.Account;

import java.util.Set;

/**
 * Created by kburkhardt on 2/23/14.
 */
public interface DatabaseConnector {

    <T extends NetworkObject> T getNetworkObject(String key);

    <T extends NetworkObject> T getNetworkObject(long key);

    <T extends NetworkObject> void createNetworkObject(T object);

    <T extends NetworkObject> void updateNetworkObject(T object);

    <T> void createAdminObject(String key, T object);

    <T> void updateAdminObject(String key, T object);

    <T> T getAdminObject(Class<T> clazz, String key);

    <T extends Account> T lookupSession(Class<T> accountClass, String authToken);

    public Set<String> getClusterCharacterSet(int clusterId);

    long nextId();

    long nextClusterId();

    int nextAccountId();
}
