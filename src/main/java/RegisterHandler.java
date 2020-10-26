import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

class RegisterHandler implements HttpHandler {
	private final Server server;

	@Override
	public void handle(HttpExchange exchange) throws IOException {

	}

	RegisterHandler(Server server) {
		this.server = server;
	}
}