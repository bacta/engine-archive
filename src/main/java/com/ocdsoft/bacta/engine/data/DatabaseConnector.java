package com.ocdsoft.bacta.engine.data;

import com.ocdsoft.bacta.engine.object.NetworkObject;
import com.ocdsoft.bacta.engine.object.account.Account;

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

    <T> T getAdminObject(String key, Class<T> clazz);

    <T extends Account> T lookupSession(String authToken, Class<T> clazz);

    public Set<String> getClusterCharacterSet(int clusterId);

    long nextId();

    long nextClusterId();

    int nextAccountId();
}
