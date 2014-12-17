package bacta.network.client;

import lombok.Getter;

public enum ConnectionState {
	OFFLINE (0, "Offline"),
    LOADING (1, "Loading"),
    ONLINE (2, "Online"),
    LOCKED(3, "Locked"),
    RESTRICTED(4, "Restricted"),
    FULL(4, "Full");

    @Getter
    private int value;

    @Getter
    private String name;

    ConnectionState(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
