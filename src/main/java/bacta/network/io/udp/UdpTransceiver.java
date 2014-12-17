package bacta.network.io.udp;

import bacta.network.client.UdpClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;

/**
 * 
 * @author Kyle Burkhardt
 * @since 1.0
 *
 * @param <Client> The client type this UDP server will be handling, Must extend {@link UdpClient}
 * @param <MessageType> Data type being received from networking implementation, ie. {@link java.net.DatagramPacket}
 */
public abstract class UdpTransceiver<Client extends UdpClient, MessageType> implements UdpReceiver<Client, MessageType>, UdpEmitter<Client, MessageType>, Runnable {

    /**
     * Udp Server to receive messages
     */
    private final UdpServer udpServer;

    /**
     * Channel Context reference
     */
    @Setter
    @Getter
    protected ChannelHandlerContext ctx;

    /**
	 * This constructor takes an instance of the client type to be created
     * @param port Port transceiver will listen on
	 * @throws SecurityException
	 * @since 1.0
	 */
	public UdpTransceiver(InetAddress bindAddress, int port) {
		try {

            udpServer = new UdpServer(bindAddress, port, new UdpHandler(this));

        } catch (SecurityException e) {

			throw new RuntimeException("Unable to start udp server", e);
		}
	}

    @Override
    public final void run() {
        udpServer.run();
    }

    protected abstract void handleIncoming(DatagramPacket msg);

    protected void handleOutgoing(DatagramPacket msg) {
        ctx.writeAndFlush(msg);
    }
}
