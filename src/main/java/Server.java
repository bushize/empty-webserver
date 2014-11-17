import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		
		ServerSocket ss;
		String path;
		
		while(true) {
		
			try {
				ss = new ServerSocket(Integer.parseInt(args[1]));
				path = args[3];
				
				Socket socket = ss.accept();
		        Thread process = new Process(socket, path);
		        process.start();
		        
		        ss.close();
			}
			catch (Exception e) {			
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
				break;
			}
		
		}
	}
}
