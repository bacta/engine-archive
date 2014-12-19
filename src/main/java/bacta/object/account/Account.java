package bacta.object.account;

import java.io.Serializable;

public interface Account extends Serializable {

	int getId();

	void setUsername(String username);
	String getUsername();

	void setPassword(String string);
	String getPassword();
}
