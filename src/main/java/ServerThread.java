import java.io.IOException;
import java.net.Socket;


public class ServerThread extends Thread {
	private SocketHttpServer server;
	private Socket client;
	
	public ServerThread(SocketHttpServer server, Socket socket) {
	     this.server = server;
	     this.client = socket;
	     start(); 
	}
	
	public void run() {
		try {
			server.run(client);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
