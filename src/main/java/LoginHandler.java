import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

class LoginHandler implements HttpHandler {
	private final Server server;

	@Override
	public void handle(HttpExchange exchange) throws IOException {

	}

	LoginHandler(Server server) {
		this.server = server;
	}
}