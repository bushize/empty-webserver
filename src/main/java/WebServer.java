import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class WebServer {
	
	ServerSocket serverSocket; 
	Socket clientSocket; // = serverSocket.accept();
	PrintWriter out; // = new PrintWriter(clientSocket.getOutputStream(), true);
	
	public WebServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
	}
	
	public void sendResponse() throws IOException {
		String response = "HTTP/1.1 404 Not Found\r\n" +
			    "Content-Length: 22\r\n" +
			    "Content-Type: text/html\r\n\r\n" +
			    "<h1>404 Not Found</h1>";
		
		out = new PrintWriter(clientSocket.getOutputStream(), true);	 
		out.write(response);
		out.flush();		
	}	 
}
