package com.ocdsoft.bacta.engine.security.authenticator;

import com.ocdsoft.bacta.engine.object.account.Account;

public interface AccountService<T extends Account> {

	boolean authenticate(T account, String password);

	T validateSession(String authToken);

    T createAccount(String username, String password);

    T getAccount(String username);

    void createAuthToken(T account);

    void updateAccount(T account);
}
