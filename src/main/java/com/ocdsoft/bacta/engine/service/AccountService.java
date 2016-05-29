package com.ocdsoft.bacta.engine.service;

import com.ocdsoft.bacta.engine.object.account.Account;

import java.net.InetAddress;

public interface AccountService<T extends Account> {

	boolean authenticate(T account, String password);

	T validateSession(InetAddress address, String authToken);

    T createAccount(String username, String password);

    T getAccount(String username);

    void createAuthToken(InetAddress address, T account);

    void updateAccount(T account);
}
