package bacta.network.security.authenticator;

import bacta.network.object.account.Account;

public interface AccountService<T extends Account> {

	boolean authenticate(T account, String password);

	T validateSession(String authToken);

    T createAccount(String username, String password);

    T getAccount(String username);

    void createAuthToken(T account);

    void updateAccount(T account);
}
