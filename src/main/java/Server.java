import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private String directory;
	private int port;
	private ServerSocket ss;
	private Thread process;
	private Socket socket;

	public Server(int port, String directory) throws IOException {
		this.port = port;
		this.directory = directory;
		this.ss = new ServerSocket(port);
	}
	
	public void begin() throws IOException {
		try {
			this.socket = ss.accept();
	        process = new Process(socket, directory);
	        process.start();		
	        ss.close();
		}
		catch (Exception e) {			
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			Thread.currentThread().interrupt();
	
		}		
	}
	
	public String getServerDirectory() {
		return directory;
	}
	
	public int getPort() {
		return port;
	}

	public void setClientSocket(Socket Client){
		this.socket = Client;
	}


	public String ServerStatus(){
		return process.getState().toString();
	}

	public Boolean ServerHealthCheck(){
		return process.isAlive();
	}

	public void close() throws IOException {
		ss.close();
		Thread.currentThread().interrupt();
	}

	public static void main(String[] args) throws IOException {
		while (true) {
			Server s = new Server(Integer.parseInt(args[1]), args[3]);		
			s.begin();		
		}
	}
}
