package com.ocdsoft.bacta.engine.network.router;

public interface IntMessageRouter<C, D> extends MessageRouter {
	void routeMessage(int opcode, C client, D message);
}
