package bacta.network.io.udp;

/**
 * 
 * @author Kyle Burkhardt
 * @since 1.0
 *
 * @param <Data> Data type the implementation writes to the socket is.
 */
public interface UdpEmitter<Client, Data> {

	void sendMessage(Client client, Data msg);

}
