package com.ocdsoft.bacta.engine.network.io.udp;

import com.ocdsoft.bacta.engine.network.client.UdpConnection;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * 
 * @author Kyle Burkhardt
 * @since 1.0
 *
  */
public abstract class UdpTransceiver<Connection extends UdpConnection> implements UdpReceiver<InetSocketAddress, ByteBuffer>, UdpEmitter<Connection, ByteBuffer>, Runnable {

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

    protected final void handleIncoming(DatagramPacket datagramPacket) {

        /// The data comes in as a direct buffer, and we need to
        /// bring it into java space for array access

        ByteBuffer buffer = ByteBuffer.allocate(datagramPacket.content().readableBytes());

        datagramPacket.content().getBytes(0, buffer);
        buffer.rewind();

        receiveMessage(datagramPacket.sender(), buffer);
    }

    protected final void handleOutgoing(ByteBuffer buffer, InetSocketAddress address) {
        ctx.writeAndFlush(new DatagramPacket(Unpooled.wrappedBuffer(buffer), address));
    }

}
