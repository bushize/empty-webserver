

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(5000);
		while(true){
			Socket socket = ss.accept();
        	Thread process = new ProcessThread(socket);
        	process.start();
		}		
	}
}
