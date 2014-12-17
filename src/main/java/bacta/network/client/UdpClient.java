package bacta.network.client;

import lombok.Getter;
import lombok.Setter;

import java.net.InetSocketAddress;

public abstract class UdpClient {

	@Getter @Setter protected InetSocketAddress remoteAddress;

    public abstract void disconnect();

    public void setLinkDead() { };
    public abstract void setClientState(ClientState state);

    public abstract ClientState getClientState();
}
