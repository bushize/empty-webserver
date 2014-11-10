

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	ServerSocket ss;
	
	public Server(ServerSocket ss) throws IOException {
		this.ss = ss;

	}
	
	public int getPort() {
		return ss.getLocalPort();
	}
	
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(); 
		//int port = Integer.parseInt(args[0]);
		serverSocket.bind(new InetSocketAddress("127.0.0.1", 5000));
		Socket clientSocket = serverSocket.accept();
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		 
		String response = "HTTP/1.1 404 Not Found\r\n" +
		    "Content-Length: 22\r\n" +
		    "Content-Type: text/html\r\n\r\n" +
		    "<h1>404 Not Found</h1>";
		 
		out.write(response);
		out.flush();
		}
}
