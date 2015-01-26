package com.ocdsoft.bacta.engine.data;

import com.ocdsoft.bacta.engine.object.account.Account;

import java.util.Set;

/**
 * Created by kburkhardt on 2/23/14.
 */
public interface ConnectionDatabaseConnector {

    <T> void createObject(String key, T object);

    <T> void updateObject(String key, T object);

    <T> T getObject(String key, Class<T> clazz);

    <T extends Account> T lookupSession(String authToken, Class<T> clazz);

    public Set<String> getClusterCharacterSet(int clusterId);

    long nextClusterId();

    int nextAccountId();
}
