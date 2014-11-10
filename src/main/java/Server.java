

import java.net.ServerSocket;

public class Server {

	ServerSocket ss;
	
	public Server(ServerSocket ss) {
		this.ss = ss;
	}
	
	public int getPort() {
		return ss.getLocalPort();
	}
}
