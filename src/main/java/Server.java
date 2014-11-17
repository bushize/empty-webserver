import java.io.IOException;


public class Server {
	
	private static SocketHttpServer server;
	private static int PORT = 5000;
	private static String ROOT_DIRECTORY = "/Users/sunny.wang/java_training/cob_spec/public";
    
	public static void main(String[] args) throws IOException {		
		server = new SocketHttpServer(PORT);
		server.setRootDirectory(ROOT_DIRECTORY);
		server.start();
	}	
}