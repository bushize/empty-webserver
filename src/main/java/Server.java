

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket(Integer.parseInt(args[1]));
		String path = args[3];
		try {
			Socket socket = ss.accept();
	        Thread process = new ProcessThread(socket, path);
	        process.start();
		}
		finally {
			ss.close();
		}
		
//		int port = Integer.parseInt(args[1]);
//		String path = args[3];
//		
//        Thread process = new ProcessThread(port, path);
//        process.start();
		
	}
}
