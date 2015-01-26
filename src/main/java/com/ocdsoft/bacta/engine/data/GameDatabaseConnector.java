package com.ocdsoft.bacta.engine.data;

import com.ocdsoft.bacta.engine.object.NetworkObject;

/**
 * Created by kburkhardt on 1/23/15.
 */
public interface GameDatabaseConnector {

    <T extends NetworkObject> T getNetworkObject(String key);

    <T extends NetworkObject> T getNetworkObject(long key);

    <T extends NetworkObject> void createNetworkObject(T object);

    <T extends NetworkObject> void updateNetworkObject(T object);

    long nextId();
}
