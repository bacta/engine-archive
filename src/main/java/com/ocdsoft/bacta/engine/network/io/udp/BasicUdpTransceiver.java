package com.ocdsoft.bacta.engine.network.io.udp;

import com.ocdsoft.bacta.engine.network.client.UdpClient;

import java.net.InetAddress;

public abstract class BasicUdpTransceiver extends UdpTransceiver<UdpClient> {

	public BasicUdpTransceiver(InetAddress bindAddress, int port) {
		super(bindAddress, port);
	}
}
