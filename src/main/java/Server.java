
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static ServerSocket serverSocket;

    public Server() throws IOException {
		serverSocket = new ServerSocket(); 
	}

	public int getPort() {
		return serverSocket.getLocalPort();
	}
    
	public static void main(String[] args) throws IOException {		 
		int port = Integer.parseInt(args[1]);
		serverSocket = new ServerSocket(port);
		//serverSocket.bind(new InetSocketAddress("127.0.0.1", port));
		Socket clientSocket = serverSocket.accept();
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		
		
		/*String file = "GET /Users/sunny.wang/java_training/cob_spec/public HTTP/1.1";
		out.print(file);*/
		
		
		String response = "HTTP/1.1 404 Not Found\r\n" +
			    "Content-Length: 22\r\n" +
			    "Content-Type: text/html\r\n\r\n" +
			    "<h1>404 Not Found</h1>";
			
			out.write(response);			
			out.flush();
	}

	public void setPort(int port) throws IOException {
		serverSocket.bind(new InetSocketAddress("127.0.0.1", port));
		serverSocket.accept();
	}

	public void setDirectory(String string) {
		//this.serverSocket.
	}

	public String getDirectory() {
		// TODO Auto-generated method stub
		return "/Users/sunny.wang/java_training/cob_spec/public";
	}
	
	

}