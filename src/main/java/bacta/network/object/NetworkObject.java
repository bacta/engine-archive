package bacta.network.object;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by kburkhardt on 2/23/14.
 */

public abstract class NetworkObject<T extends NetworkObject> implements Comparable<NetworkObject> {
    @Getter @Setter
    protected long networkId;

    @Getter @Setter
    protected transient boolean dirty = false;

    @Override
    public final int compareTo(NetworkObject o) {
        if(o.networkId == networkId) {
            return 0;
        }

        if(networkId > o.networkId) {
            return -1;
        }

        return 1;
    }
}
