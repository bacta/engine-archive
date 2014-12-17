package bacta.network.io.udp;

import bacta.network.client.UdpClient;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetAddress;

public abstract class BasicUdpTransceiver extends UdpTransceiver<UdpClient, DatagramPacket> {

	public BasicUdpTransceiver(InetAddress bindAddress, int port) {
		super(bindAddress, port);
	}
}
