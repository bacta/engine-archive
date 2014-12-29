package com.ocdsoft.bacta.engine.object.account;

import java.io.Serializable;

public interface Account extends Serializable {

	int getId();

	void setUsername(String username);
	String getUsername();

	void setPassword(String password);
	String getPassword();

    void setAuthToken(String token);
    String getAuthToken();

    void setAuthExpiration(long expiration);
    long getAuthExpiration();
}
