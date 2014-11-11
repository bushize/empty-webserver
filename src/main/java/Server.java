

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		/*
		ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[1])); 
		Socket clientSocket = serverSocket.accept();
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		 
		String response = "HTTP/1.1 404 Not Found\r\n" +
		    "Content-Length: 22\r\n" +
		    "Content-Type: text/html\r\n\r\n" +
		    "<h1>404 Not Found</h1>";
		 
		out.write(response);
		out.flush();
		*/
		
		WebServer ws = new WebServer(Integer.parseInt(args[1]));
		ws.sendResponse();
	}
}
