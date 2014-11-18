import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private String directory;
	private int port;
	private ServerSocket ss;
	
	public Server(int port, String directory) throws IOException {
		this.port = port;
		this.directory = directory;
		this.ss = new ServerSocket(port);
	}
	
	public void begin() throws IOException {
		try {			
			Socket socket = ss.accept();
	        Thread process = new Process(socket, directory);
	        process.start();	
	        
	        ss.close();
		}
		catch (Exception e) {			
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
	
		}		
	}
	
	public String getServerDirectory() {
		return directory;
	}
	
	public int getPort() {
		return port;
	}
	
	public static void main(String[] args) throws IOException {
		while (true) {
			Server s = new Server(Integer.parseInt(args[1]), args[3]);		
			s.begin();		
		}
	}
}
