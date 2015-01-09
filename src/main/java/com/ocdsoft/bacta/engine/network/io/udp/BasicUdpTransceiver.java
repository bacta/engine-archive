package com.ocdsoft.bacta.engine.network.io.udp;

import com.ocdsoft.bacta.engine.network.client.UdpConnection;

import java.net.InetAddress;

public abstract class BasicUdpTransceiver extends UdpTransceiver<UdpConnection> {

	public BasicUdpTransceiver(InetAddress bindAddress, int port) {
		super(bindAddress, port);
	}
}
