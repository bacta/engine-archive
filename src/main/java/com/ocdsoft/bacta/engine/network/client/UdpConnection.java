package com.ocdsoft.bacta.engine.network.client;

import lombok.Getter;
import lombok.Setter;

import java.net.InetSocketAddress;

public abstract class UdpConnection {

	@Getter @Setter protected InetSocketAddress remoteAddress;

    public abstract void setConnectionState(ConnectionState state);
    public abstract ConnectionState getConnectionState();
}
